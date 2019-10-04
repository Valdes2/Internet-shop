package mate.academy.internetshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Item;
import org.apache.log4j.Logger;

@Dao
public class ItemDaoJdbcImpl extends AbstractDao<Item> implements ItemDao {
    private static Logger logger = Logger.getLogger(ItemDaoJdbcImpl.class);
    private static final String DB_NAME = "internetshop";

    public ItemDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Item create(Item item) {
        PreparedStatement preparedStatement = null;
        String query = String.format("INSERT INTO %s.items (name, price) values(?, ?)", DB_NAME);

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, item.getName());
            preparedStatement.setDouble(2, item.getPrice());
            preparedStatement.executeUpdate();
            return item;
        } catch (SQLException e) {
            logger.error("Can`t create item", e);
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    logger.error("Can`t close statement", e);
                }
            }
        }

        return null;
    }

    @Override
    public Item get(Long id) {
        PreparedStatement preparedStatement = null;
        String query = String
                .format("SELECT * FROM %s.items WHERE item_id=?", DB_NAME);

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long itemId = resultSet.getLong("item_id");
                String name = resultSet.getString("name");
                Double price = resultSet.getDouble("price");
                Item item = new Item(itemId);
                item.setName(name);
                item.setPrice(price);
                return item;
            }
        } catch (SQLException e) {
            logger.error("Can`t get item by id=" + id, e);
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    logger.error("Can`t close statement", e);
                }
            }
        }

        return null;
    }

    @Override
    public Item update(Item item) {
        PreparedStatement preparedStatement = null;
        String query = String
                .format("UPDATE %s.items SET name = ?, price = ? WHERE item_id = ?", DB_NAME);

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, item.getName());
            preparedStatement.setDouble(2, item.getPrice());
            preparedStatement.setLong(3, item.getId());
            preparedStatement.executeUpdate();
            return item;
        } catch (SQLException e) {
            logger.error("Can`t update item", e);
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    logger.error("Can`t close statement", e);
                }
            }
        }
        return null;
    }

    @Override
    public Item delete(Long id) {
        PreparedStatement preparedStatement = null;
        String query = String
                .format("DELETE FROM %s.items WHERE item_id=?",DB_NAME);

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            Item deletedItem = get(id);
            preparedStatement.executeUpdate();
            return deletedItem;
        } catch (SQLException e) {
            logger.error("Can`t delete item", e);
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    logger.error("Can`t close statement", e);
                }
            }
        }
        return null;
    }

    @Override
    public List<Item> getAll() {
        List<Item> allItems = new ArrayList<>();
        Statement statement = null;
        String query = String
                .format("SELECT item_id FROM %s.items;", DB_NAME);

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Long itemId = resultSet.getLong("item_id");
                allItems.add(get(itemId));
            }
        } catch (SQLException e) {
            logger.error("Can`t get items,", e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    logger.error("Can`t close statement", e);
                }
            }
        }
        return allItems;
    }
}
