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

            ResultSet resultSet = statement.executeQuery(FIND_BY_ID_SQL);

            while (resultSet.next()) {
                boardGames.add(getBoardGameFromResultSet(resultSet));
            }

            return boardGames;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static BoardGame getBoardGameFromResultSet(ResultSet resultSet) throws SQLException {
        BoardGame boardgame = new BoardGame();

        boardgame.setName(resultSet.getString("name"));
        boardgame.setPrice(resultSet.getInt("price"));
        boardgame.setYear(resultSet.getShort("year"));
        boardgame.setLanguage(resultSet.getString("language"));
        boardgame.setPublisher(resultSet.getString("publisher"));
        boardgame.setMinPlayers(resultSet.getShort("min_players"));
        boardgame.setMinPlayers(resultSet.getShort("max_players"));

        return boardgame;
    }
}
