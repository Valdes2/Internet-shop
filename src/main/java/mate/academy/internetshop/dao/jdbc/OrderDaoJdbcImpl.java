package mate.academy.internetshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.model.Order;
import org.apache.log4j.Logger;

@Dao
public class OrderDaoJdbcImpl extends AbstractDao implements OrderDao {
    private static Logger logger = Logger.getLogger(OrderDaoJdbcImpl.class);

    public OrderDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Order create(Order order) {
        String query = "INSERT INTO orders (user_id) VALUE (?);";
        try (PreparedStatement preparedStatement
                     = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, order.getUserId());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                order.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            logger.error("Can`t create order", e);
            return null;
        }

        String addItemQuery = "INSERT INTO orders_items (order_id, item_id) VALUES (?, ?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(addItemQuery)) {
            for (Item item: order.getOrderedItems()) {
                preparedStatement.setLong(1, order.getId());
                preparedStatement.setLong(2, item.getId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            logger.error("Can't add items to order with id: " + order.getId(), e);
            return null;
        }
        return order;
    }

    @Override
    public Order get(Long orderId) {
        String query = "SELECT * FROM orders WHERE order_id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, orderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long userId = resultSet.getLong("user_id");
                Order order = new Order(userId, getOrderedItems(orderId));
                order.setId(resultSet.getLong("order_id"));
                return order;
            }
        } catch (SQLException e) {
            logger.error("Can`t get order by id:" + orderId, e);
        }
        return null;
    }

    @Override
    public Order update(Order order) {
        String query = "UPDATE orders SET user_id = ? WHERE order_id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, order.getUserId());
            preparedStatement.setLong(2, order.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can`t update order", e);
        }
        return order;
    }

    @Override
    public Order delete(Long orderId) {
        String query = "DELETE FROM orders WHERE order_id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, orderId);
            Order deletedOrder = get(orderId);
            preparedStatement.executeUpdate();
            return deletedOrder;
        } catch (SQLException e) {
            logger.error("Can`t delete bucket", e);
        }
        return null;
    }

    public List<Item> getOrderedItems(Long orderId) {
        List<Item> orderedItems = new ArrayList<>();
        String query
                = "SELECT * FROM items INNER JOIN orders_items "
                + "ON items.item_id = orders_items.item_id WHERE order_id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, orderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                Double price = resultSet.getDouble("price");
                Item item = new Item(name, price);
                item.setId(resultSet.getLong("item_id"));
                orderedItems.add(item);
            }
        } catch (SQLException e) {
            logger.error("Can't get all items from order with id: " + orderId, e);
        }
        return orderedItems;
    }
}
