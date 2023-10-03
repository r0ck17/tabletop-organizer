package by.javaguru.dao;

import by.javaguru.entity.UserBoardGame;
import by.javaguru.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserBoardGameDao {
    private final Connection connection = ConnectionManager.open();
    private static UserBoardGameDao INSTANCE = new UserBoardGameDao();
    private final String SAVE_SQL = """
            INSERT INTO user_boardgame (user_id, boardgame_id)
            VALUES (?, ?)
            """;

    private final String DELETE_SQL = """
            DELETE FROM user_boardgame
            WHERE user_id = ? AND boardgame_id = ?
            """;
    private UserBoardGameDao() {
    }

    public static UserBoardGameDao getInstance() {
        return INSTANCE;
    }

    public UserBoardGame save(Long userId, Long boardGameId) {
        try (PreparedStatement statement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, userId);
            statement.setLong(2, boardGameId);

            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();

            if (generatedKeys.next()) {
                UserBoardGame userBoardGame = new UserBoardGame();
                userBoardGame.setUserId(generatedKeys.getLong("user_id"));
                userBoardGame.setBoardGameId(generatedKeys.getLong("boardgame_id"));
                return userBoardGame;
            }

            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean delete(Long userId, Long boardGameId) {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_SQL)) {
            statement.setLong(1, userId);
            statement.setLong(2, boardGameId);

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
