package mate.academy.internetshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.exceptions.AuthenticationException;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.util.HashUtil;
import org.apache.log4j.Logger;

@Dao
public class UserDaoJdbcImpl extends AbstractDao implements UserDao {

    @Inject
    private static OrderDao orderDao;

    private static Logger logger = Logger.getLogger(UserDaoJdbcImpl.class);

    public UserDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public User create(User user) {
        String query
                = "INSERT INTO users(name, login, password, salt, token) VALUES (?, ?, ?, ?, ?);";
        try (PreparedStatement preparedStatement
                     = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setBytes(4, user.getSalt());
            preparedStatement.setString(5, user.getToken());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                user.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            logger.error("Can`t create user", e);
            return null;
        }
        return user;
    }

    @Override
    public User get(Long id) {
        String query = "SELECT * FROM users WHERE user_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String login = resultSet.getString("login");
                String pass = resultSet.getString("password");
                User user = new User(name, login, pass);
                user.setId(resultSet.getLong("user_id"));
                user.setBucketId(resultSet.getLong("bucket_id"));
                user.setSalt(resultSet.getBytes("salt"));
                user.setToken(resultSet.getString("token"));
                user.setOrders(getUserOrders(id));
                return user;
            }
        } catch (SQLException e) {
            logger.error("Can`t get user by id:" + id, e);
        }
        return null;
    }

    @Override
    public User update(User user) {
        String query = "UPDATE users SET name = ?, login = ?, password = ?,"
                + " token = ?, bucket_id = ? WHERE user_id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getToken());
            preparedStatement.setLong(5, user.getBucketId());
            preparedStatement.setLong(6, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can`t update user", e);
            return null;
        }
        return user;
    }

    @Override
    public User delete(Long userId) {
        String query = "DELETE FROM users WHERE user_id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, userId);
            User deletedUser = get(userId);
            preparedStatement.executeUpdate();
            deleteRoles(userId);
            return deletedUser;
        } catch (SQLException e) {
            logger.error("Can`t delete user", e);
        }
        return null;
    }

    @Override
    public List<User> getAll() {
        List<User> allUsers = new ArrayList<>();
        String query = "SELECT user_id FROM users;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long userId = resultSet.getLong("user_id");
                allUsers.add(get(userId));
            }
        } catch (SQLException e) {
            logger.error("Can`t get list of users", e);
            return null;
        }
        return allUsers;
    }

    @Override
    public User login(String login, String password) throws AuthenticationException {
        String getSaltQuery = "SELECT salt FROM users WHERE login = ?;";
        byte[] salt = new byte[0];
        try (PreparedStatement preparedStatement = connection.prepareStatement(getSaltQuery)) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                salt = resultSet.getBytes("salt");
            }
        } catch (SQLException e) {
            logger.error("Can`t get salt by login: " + login);
            return null;
        }

        String query = "SELECT * FROM users where login = ? AND password = ?;";
        String hashPass = HashUtil.hashPassword(password, salt);
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, hashPass);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String loginFromDB = resultSet.getString("login");
                String passFromDB = resultSet.getString("password");
                byte[] saltFromDB = resultSet.getBytes("salt");
                if (!login.equals(loginFromDB)
                        || !HashUtil.hashPassword(password, saltFromDB).equals(passFromDB)) {
                    throw new AuthenticationException("incorrect login or password");
                }
                User user = new User(name, login, password);
                user.setId(resultSet.getLong("user_id"));
                user.setToken(resultSet.getString("token"));
                user.setBucketId(resultSet.getLong("bucket_id"));
                return user;
            }
        } catch (SQLException e) {
            logger.error("Can`t find login or password", e);
        }
        throw new AuthenticationException("Incorrect login or password");
    }

    @Override
    public Optional<User> getByToken(String token) {
        String query = "SELECT * FROM users WHERE token = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, token);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                User user = new User(name, login, password);
                user.setId(resultSet.getLong("user_id"));
                user.setToken(resultSet.getString("token"));
                user.setBucketId(resultSet.getLong("bucket_id"));
                return Optional.of(user);
            }
        } catch (SQLException e) {
            logger.error("Can`t find token", e);
        }
        return Optional.empty();
    }

    @Override
    public void addRole(Long roleId, Long userId) {
        String query = "INSERT INTO users_roles (role_id, user_id) VALUES (?, ?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, roleId);
            preparedStatement.setLong(2, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can`t add role to user", e);
        }
    }

    public void deleteRoles(Long userId) {
        String query = "DELETE FROM users_roles WHERE user_id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can`t delete role", e);
        }
    }

    @Override
    public void addOrder(Long orderId, Long userId) {
        String addOrderQuery = "INSERT INTO users_orders (user_id, order_id) VALUES (?, ?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(addOrderQuery)) {
            preparedStatement.setLong(1, userId);
            preparedStatement.setLong(2, orderId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't add item to order with id: " + orderId, e);
        }
    }

    @Override
    public List<Order> getUserOrders(Long userId) {
        List<Order> userOrders = new ArrayList<>();
        String query
                = "SELECT * FROM orders INNER JOIN users_orders "
                + "ON orders.order_id = users_orders.order_id WHERE orders.user_id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Order order = orderDao.get(resultSet.getLong("order_id"));
                userOrders.add(order);
            }
        } catch (SQLException e) {
            logger.error("Can't get all orders for user with id: " + userId, e);
        }
        return userOrders;
    }
}
