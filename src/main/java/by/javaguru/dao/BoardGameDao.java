package by.javaguru.dao;

import by.javaguru.entity.BoardGame;
import by.javaguru.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BoardGameDao implements Dao<Long, BoardGame> {
    private final Connection connection = ConnectionManager.open();
    private static BoardGameDao INSTANCE = new BoardGameDao();
    private final String SAVE_SQL = """
            INSERT INTO boardgame (name, price, year, language, publisher, min_players, max_players)
            VALUES (?, ?, ?, ?, ?, ?, ?)
            """;
    private final String UPDATE_SQL = """
            UPDATE boardgame
            SET
            name = ?, price = ?, year = ?, language = ?, publisher = ?, min_players = ?, max_players = ?
            WHERE id = ?
            """;

    private final String DELETE_SQL = """
            DELETE FROM boardgame
            WHERE id = ?
            """;

    private final String FIND_ALL_SQL = """
            SELECT id, name, price, year, language, publisher, min_players, max_players
            FROM boardgame
            """;

    private final String FIND_GAMES_BY_USER_ID_SQL = """
            SELECT g.name as name,
                   g.year as year,
                   g.language as language,
                   g.publisher as publisher,
                   g.min_players as min_players,
                   g.max_players as max_players,
                   g.price as price
            FROM user_boardgame u
                     JOIN boardgame g ON u.boardgame_id = g.id
            WHERE user_id = ?
            ORDER BY name;
            """;

    private final String FIND_BY_ID_SQL = FIND_ALL_SQL + "WHERE id = ?";

    private BoardGameDao() {
    }

    public static BoardGameDao getInstance() {
        return INSTANCE;
    }

    @Override
    public BoardGame save(BoardGame boardgame) {
        try (PreparedStatement statement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, boardgame.getName());
            statement.setInt(2, boardgame.getPrice());
            statement.setShort(3, boardgame.getYear());
            statement.setString(4, boardgame.getLanguage());
            statement.setString(5, boardgame.getPublisher());
            statement.setShort(6, boardgame.getMaxPlayers());
            statement.setShort(7, boardgame.getMaxPlayers());

            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();

            if (generatedKeys.next()) {
                boardgame.setId(generatedKeys.getLong("id"));
                return boardgame;
            }

            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(BoardGame boardgame) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_SQL)) {
            statement.setString(1, boardgame.getName());
            statement.setInt(2, boardgame.getPrice());
            statement.setShort(3, boardgame.getYear());
            statement.setString(4, boardgame.getLanguage());
            statement.setString(5, boardgame.getPublisher());
            statement.setShort(6, boardgame.getMaxPlayers());
            statement.setShort(7, boardgame.getMaxPlayers());

            statement.setLong(8, boardgame.getId());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(Long id) {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_SQL)) {
            statement.setLong(1, id);

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<BoardGame> findById(Long id) {
        try (PreparedStatement statement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                BoardGame boardGame = getBoardGameFromResultSet(resultSet);
                return Optional.of(boardGame);
            }

            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<BoardGame> findAll() {
        try (Statement statement = connection.createStatement()) {
            List<BoardGame> boardGames = new ArrayList<>();

            ResultSet resultSet = statement.executeQuery(FIND_ALL_SQL);

            while (resultSet.next()) {
                boardGames.add(getBoardGameFromResultSet(resultSet));
            }

            return boardGames;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<BoardGame> findUserGamesById(Long userId) {
        try (PreparedStatement statement = connection.prepareStatement(FIND_GAMES_BY_USER_ID_SQL)) {
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            List<BoardGame> games = new ArrayList<>();

            while (resultSet.next()) {
                BoardGame boardGame = getBoardGameFromResultSet(resultSet);
                games.add(boardGame);
            }

            return games;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static BoardGame getBoardGameFromResultSet(ResultSet resultSet) throws SQLException {
        return BoardGame.builder()
                .name(resultSet.getString("name"))
                .price(resultSet.getInt("price"))
                .year(resultSet.getShort("year"))
                .language(resultSet.getString("language"))
                .publisher(resultSet.getString("publisher"))
                .minPlayers(resultSet.getShort("min_players"))
                .maxPlayers(resultSet.getShort("max_players"))
                .build();
    }
}
