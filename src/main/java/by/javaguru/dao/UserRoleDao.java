package by.javaguru.dao;

import by.javaguru.entity.UserRole;
import by.javaguru.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRoleDao implements Dao<Integer, UserRole> {
    private final Connection connection = ConnectionManager.open();
    private static UserRoleDao INSTANCE = new UserRoleDao();
    private final String SAVE_SQL = """
            INSERT INTO user_role (role)
            VALUES (?)
            """;
    private final String UPDATE_SQL = """
            UPDATE user_role
            SET
            role = ?
            WHERE id = ?
            """;

    private final String DELETE_SQL = """
            DELETE FROM user_role
            WHERE id = ?
            """;

    private final String FIND_ALL_SQL = """
            SELECT id, role
            FROM user_role
            """;

    private final String FIND_BY_ID_SQL = FIND_ALL_SQL + "WHERE id = ?";

    private UserRoleDao() {
    }

    public static UserRoleDao getInstance() {
        return INSTANCE;
    }

    @Override
    public UserRole save(UserRole userRole) {
        try (PreparedStatement statement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, userRole.getRole());

            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();

            if (generatedKeys.next()) {
                UserRole role = new UserRole();
                role.setRole(generatedKeys.getString("role"));
                role.setId(generatedKeys.getInt("id"));
                return role;
            }

            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(UserRole entity) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_SQL)) {
            statement.setString(1, entity.getRole());
            statement.setInt(2, entity.getId());

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
    public Optional<UserRole> findById(Integer id) {
        try (PreparedStatement statement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                UserRole role = new UserRole();
                role.setId(resultSet.getInt("id"));
                role.setRole(resultSet.getString("role"));
                return Optional.of(role);
            }

            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<UserRole> findAll() {
        try (Statement statement = connection.createStatement()) {
            List<UserRole> roles = new ArrayList<>();

            ResultSet resultSet = statement.executeQuery(FIND_BY_ID_SQL);

            while (resultSet.next()) {
                UserRole role = new UserRole();
                role.setId(resultSet.getInt("id"));
                role.setRole(resultSet.getString("role"));

                roles.add(role);
            }

            return roles;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
