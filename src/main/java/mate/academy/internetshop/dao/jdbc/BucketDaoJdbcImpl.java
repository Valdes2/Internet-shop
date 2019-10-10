package mate.academy.internetshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;
import org.apache.log4j.Logger;

@Dao
public class BucketDaoJdbcImpl extends AbstractDao implements BucketDao {
    private static Logger logger = Logger.getLogger(BucketDaoJdbcImpl.class);

    public BucketDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Bucket create(Bucket bucket) {
        String query = "INSERT INTO buckets (user_id) VALUE (?);";
        try (PreparedStatement preparedStatement
                     = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, bucket.getUser().getId());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                bucket.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            logger.error("Can`t create bucket", e);
            return null;
        }
        return bucket;
    }

    @Override
    public Bucket get(Long id) {
        String query = "SELECT * FROM buckets WHERE bucket_id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Bucket bucket = new Bucket(resultSet.getLong("user_id"));
                bucket.setId(resultSet.getLong("bucket_id"));
                bucket.setItems(getAllItems(id));
                return bucket;
            }
        } catch (SQLException e) {
            logger.error("Can`t get bucket by id:" + id, e);
        }
        return null;
    }

    @Override
    public Bucket update(Bucket bucket) {
        String query = "UPDATE buckets SET user_id = ? WHERE bucket_id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, bucket.getUser().getId());
            preparedStatement.setLong(2, bucket.getId());
            preparedStatement.executeUpdate();
            for (Item item: bucket.getItems()) {
                addItemToBucket(item.getId(), bucket.getId());
            }
        } catch (SQLException e) {
            logger.error("Can`t update bucket", e);
        }
        return bucket;
    }

    @Override
    public Bucket delete(Long id) {
        String query = "DELETE FROM buckets WHERE bucket_id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            Bucket deletedBucket = get(id);
            preparedStatement.executeUpdate();
            return deletedBucket;
        } catch (SQLException e) {
            logger.error("Can`t delete bucket", e);
        }
        return null;
    }

    @Override
    public Bucket addItemToBucket(Long itemId, Long bucketId) {
        String query = "INSERT INTO buckets_items (bucket_id, item_id) VALUES (?, ?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, bucketId);
            preparedStatement.setLong(2, itemId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't add item to bucket with id: " + bucketId, e);
        }
        return get(bucketId);
    }

    @Override
    public Bucket deleteItemFromBucket(Long itemId, Long bucketId) {
        String query = "DELETE FROM buckets_items WHERE item_id = ? AND bucket_id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, itemId);
            preparedStatement.setLong(2, bucketId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't delete item from bucket", e);
        }
        return get(bucketId);
    }

    @Override
    public Bucket clear(Long bucketId) {
        String query = "DELETE FROM buckets_items WHERE bucket_id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, bucketId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't clear bucket with id: " + bucketId, e);
        }
        return get(bucketId);
    }

    @Override
    public List<Item> getAllItems(Long bucketId) {
        List<Item> allItems = new ArrayList<>();
        String query
                = "SELECT * FROM items INNER JOIN buckets_items "
                + "ON items.item_id = buckets_items.item_id WHERE bucket_id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, bucketId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                Double price = resultSet.getDouble("price");
                Item item = new Item(name, price);
                item.setId(resultSet.getLong("item_id"));
                allItems.add(item);
            }
        } catch (SQLException e) {
            logger.error("Can't get all items from bucket with id: " + bucketId, e);
        }
        return allItems;
    }
}
