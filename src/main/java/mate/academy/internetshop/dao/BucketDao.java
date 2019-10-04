package mate.academy.internetshop.dao;

import java.util.List;

import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;

public interface BucketDao {

    Bucket create(Bucket bucket);

    Bucket get(Long id);

    Bucket update(Bucket bucket);

    Bucket delete(Long id);

    Bucket addItemToBucket(Long itemId, Long bucketId);

    Bucket deleteItemFromBucket(Long itemId, Long bucketId);

    Bucket clear(Long bucketId);

    List<Item> getAllItems(Long bucketId);

}
