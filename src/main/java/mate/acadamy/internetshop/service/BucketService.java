package mate.acadamy.internetshop.service;

import java.util.List;

import mate.acadamy.internetshop.model.Bucket;
import mate.acadamy.internetshop.model.Item;

public interface BucketService {

    Bucket create(Bucket bucket);

    Bucket get(Long id);

    Bucket update(Bucket bucket);

    void delete(Long id);

    Bucket addItem(Long bucketId, Long itemId);

    Bucket clear(Long bucketId);

    List<Item> getAllItems(Long bucketId);
}