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
    private static UserDao INSTANCE = new UserDao();
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

            ResultSet resultSet = statement.executeQuery(FIND_BY_ID_SQL);

            while (resultSet.next()) {
                users.add(getUserFromResultSet(resultSet));
            }

            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static User getUserFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();

        user.setId(resultSet.getLong("id"));
        user.setLogin(resultSet.getString("login"));
        user.setPassword(resultSet.getString("password"));
        user.setName(resultSet.getString("name"));
        user.setRoleId(resultSet.getInt("role_id"));

        return user;
    }
}
