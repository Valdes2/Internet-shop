package mate.acadamy.internetshop.dao;

import mate.acadamy.internetshop.model.Bucket;

public interface BucketDao {

    Bucket create(Bucket bucket);

    Bucket get(Long id);

    Bucket update(Bucket bucket);

    Bucket delete(Long id);

}
