package mate.academy.internetshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import mate.academy.internetshop.dao.RoleDao;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Role;
import org.apache.log4j.Logger;

@Dao
public class RoleDaoJdbcImpl extends AbstractDao implements RoleDao {
    private static Logger logger = Logger.getLogger(RoleDaoJdbcImpl.class);

    public RoleDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Role getRole(Long roleId) {
        String query = "SELECT * FROM roles WHERE role_id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, roleId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Role role = Role.of(resultSet.getString("name"));
                role.setId(resultSet.getLong("role_id"));
                return role;
            }
        } catch (SQLException e) {
            logger.error("Cant get Role with id: " + roleId);
        }
        return null;
    }

    @Override
    public Set<Role> getAllRoles(Long userId) {
        Set<Role> allRoles = new HashSet<>();
        String query = "SELECT * FROM users_roles WHERE user_id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                allRoles.add(getRole(resultSet.getLong("role_id")));
            }
        } catch (SQLException e) {
            logger.error("Can`t get all roles for user with id: " + userId, e);
            return null;
        }
        return allRoles;
    }
}
