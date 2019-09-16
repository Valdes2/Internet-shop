package mate.acadamy.internetshop.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import mate.acadamy.internetshop.dao.BucketDao;
import mate.acadamy.internetshop.dao.ItemDao;
import mate.acadamy.internetshop.dao.impl.BucketDaoImpl;
import mate.acadamy.internetshop.lib.Inject;
import mate.acadamy.internetshop.lib.Service;
import mate.acadamy.internetshop.model.Bucket;
import mate.acadamy.internetshop.model.Item;
import mate.acadamy.internetshop.service.BucketService;

@Service
public class BucketServiceImpl implements BucketService {

    @Inject
    private static BucketDao bucketDao;

    @Inject
    private static ItemDao itemDao;

    @Override
    public Bucket create(Bucket bucket) {
        return bucketDao.create(bucket);
    }

    @Override
    public Bucket get(Long id) {
        return bucketDao.get(id);
    }

    @Override
    public Bucket update(Bucket bucket) {
        return bucketDao.update(bucket);
    }

    @Override
    public void delete(Long id) {
        bucketDao.delete(id);
    }

    @Override
    public Bucket addItem(Long bucketId, Long itemId) {
        Bucket bucket = bucketDao.get(bucketId);
        Item item = itemDao.get(itemId);
        bucket.getItems().add(item);
        return bucketDao.update(bucket);
    }

    @Override
    public Bucket clear(Long bucketId) {
        Bucket bucket = bucketDao.get(bucketId);
        bucket.getItems().clear();
        return bucketDao.update(bucket);
    }

    @Override
    public List<Item> getAllItems(Long bucketId) {
        return bucketDao.get(bucketId).getItems()
                .stream()
                .collect(Collectors.toList());
    }

}
