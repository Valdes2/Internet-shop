package mate.acadamy.internetshop.dao.impl;

import java.util.NoSuchElementException;
import java.util.Optional;

import mate.acadamy.internetshop.dao.BucketDao;
import mate.acadamy.internetshop.db.Storage;
import mate.acadamy.internetshop.lib.Dao;
import mate.acadamy.internetshop.model.Bucket;

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
            throw new NoSuchElementException("Current bucket already created");
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
        Optional<Bucket> currentBucket = Storage.buckets
                .stream()
                .filter(x -> x.getId().equals(id))
                .findAny();
        if (!currentBucket.equals(Optional.of(get(id)))) {
            Bucket deletedBucket = get(id);
            Storage.buckets.removeIf(x -> x.getId().equals(id));
            return deletedBucket;
        } else {
            throw new NoSuchElementException();
        }
    }
}
