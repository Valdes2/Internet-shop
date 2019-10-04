package mate.academy.internetshop.dao.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;

@Dao
public class BucketDaoImpl implements BucketDao {
    @Override
    public Bucket create(Bucket bucket) {
        Optional<Bucket> currentBucket = Storage.buckets
                .stream()
                .filter(x -> x.getId().equals(bucket.getId()))
                .findAny();
        if (!currentBucket.equals(Optional.of(bucket))) {
            Storage.buckets.add(bucket);
            return bucket;
        } else {
            update(bucket);
            return bucket;
        }
    }

    @Override
    public Bucket get(Long id) {
        return Storage.buckets
                .stream().filter(x -> x.getId().equals(id)).findFirst()
                .orElseThrow(() -> new NoSuchElementException());
    }

    @Override
    public Bucket update(Bucket bucket) {
        for (int i = 0; i < Storage.buckets.size(); i++) {
            if (Storage.buckets.get(i).getId().equals(bucket.getId())) {
                Storage.buckets.set(i, bucket);
                return bucket;
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public Bucket delete(Long id) {
        Bucket deletedBucket = get(id);
        Storage.buckets.removeIf(x -> x.getId().equals(id));
        return deletedBucket;
    }

    @Override
    public Bucket addItemToBucket(Long itemId, Long bucketId) {
        return null;
    }

    @Override
    public Bucket deleteItemFromBucket(Long itemId, Long bucketId) {
        return null;
    }

    @Override
    public Bucket clear(Long bucketId) {
        return null;
    }

    @Override
    public List<Item> getAllItems(Long bucketId) {
        return null;
    }
}
