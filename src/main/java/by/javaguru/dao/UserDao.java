package by.javaguru.dao;

import by.javaguru.entity.User;
import by.javaguru.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao implements Dao<Integer, User> {
    private final Connection connection = ConnectionManager.open();
    private static final UserDao INSTANCE = new UserDao();
    private final String SAVE_SQL = """
            INSERT INTO users (login, password, name, role_id)
            VALUES (?, ?, ?, ?)
            """;
    private final String UPDATE_SQL = """
            UPDATE users
            SET
            login = ?, password = ?, name = ?, role_id = ?
            WHERE id = ?
            """;
    private final String DELETE_SQL = """
            DELETE FROM users
            WHERE id = ?
            """;
    private final String FIND_ALL_SQL = """
            SELECT id, login, password, name, role_id
            FROM users
            """;
    private final String FIND_BY_ID_SQL = FIND_ALL_SQL + "WHERE id = ?";
    private static final String GET_BY_EMAIL_AND_PASSWORD_SQL = """
            SELECT id, login, password, name, role_id
            FROM users
            WHERE
            login = ? AND password = ?
            """;
    private final String SAVE_GAME_TO_USER_SQL = """
            INSERT INTO user_boardgame (user_id, boardgame_id)
            VALUES (?, ?)
            """;
    private final String DELETE_GAME_FROM_USER_SQL = """
            DELETE FROM user_boardgame
            WHERE user_id = ? AND boardgame_id = ?
            """;

    private UserDao() {
    }

    public static UserDao getInstance() {
        return INSTANCE;
    }

    @Override
    public User save(User user) {
        try (PreparedStatement statement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getName());
            statement.setInt(4, user.getRoleId());

            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();

            if (generatedKeys.next()) {
                user.setId(generatedKeys.getLong("id"));
                return user;
            }

            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(User user) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_SQL)) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getName());
            statement.setInt(4, user.getRoleId());
            statement.setLong(5, user.getId());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(Integer id) {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_SQL)) {
            statement.setInt(1, id);

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<User> findById(Integer id) {
        try (PreparedStatement statement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                User user = getUserFromResultSet(resultSet);
                return Optional.of(user);
            }

            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> findAll() {
        try (Statement statement = connection.createStatement()) {
            List<User> users = new ArrayList<>();

            ResultSet resultSet = statement.executeQuery(FIND_ALL_SQL);

            while (resultSet.next()) {
                users.add(getUserFromResultSet(resultSet));
            }

            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<User> getUserByEmailAndPassword(String email, String password) {
        try (PreparedStatement statement = connection.prepareStatement(GET_BY_EMAIL_AND_PASSWORD_SQL)) {
            statement.setString(1, email);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();
            User user = null;

            if (resultSet.next()) {
                user = getUserFromResultSet(resultSet);
            }

            return Optional.ofNullable(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean addGameToUser(User user, Long boardgameId) {
        try (PreparedStatement statement = connection.prepareStatement(SAVE_GAME_TO_USER_SQL)) {
            statement.setLong(1, user.getId());
            statement.setLong(2, boardgameId);

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteGameFromUser(User user, Long boardgameId) {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_GAME_FROM_USER_SQL)) {
            statement.setLong(1, user.getId());
            statement.setLong(2, boardgameId);

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private User getUserFromResultSet(ResultSet resultSet) throws SQLException {
        return User.builder()
                .id(resultSet.getLong("id"))
                .login(resultSet.getString("login"))
                .password(resultSet.getString("password"))
                .name(resultSet.getString("name"))
                .roleId(resultSet.getInt("role_id"))
                .build();
    }
}
