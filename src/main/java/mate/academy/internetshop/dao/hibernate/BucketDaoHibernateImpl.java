package mate.academy.internetshop.dao.hibernate;

import java.util.List;

import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Dao
public class BucketDaoHibernateImpl implements BucketDao {
    @Override
    public Bucket create(Bucket bucket) {
        Long bucketId = null;
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.sessionFactory().openSession();
            transaction = session.beginTransaction();
            bucketId = (Long) session.save(bucket);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        bucket.setId(bucketId);
        return bucket;
    }

    @Override
    public Bucket get(Long id) {
        Session session = null;
        try {
            session = HibernateUtil.sessionFactory().openSession();
            Bucket bucket = session.get(Bucket.class, id);
            return bucket;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Bucket update(Bucket bucket) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.sessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(bucket);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return bucket;
    }

    @Override
    public Bucket delete(Long id) {
        Bucket deletedBucket = get(id);
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.sessionFactory().openSession();
            transaction = session.beginTransaction();
            session.delete(deletedBucket);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return deletedBucket;
    }

    @Override
    public Bucket addItemToBucket(Long itemId, Long bucketId) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.sessionFactory().openSession();
            transaction = session.beginTransaction();
            Query query = session
                    .createSQLQuery("INSERT INTO buckets_items"
                            + " (bucket_id, item_id) VALUES (?, ?);");
            query.setParameter(1, bucketId);
            query.setParameter(2, itemId);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return get(bucketId);
    }

    @Override
    public Bucket deleteItemFromBucket(Long itemId, Long bucketId) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.sessionFactory().openSession();
            transaction = session.beginTransaction();
            Query query = session
                    .createSQLQuery("DELETE FROM buckets_items"
                            + " WHERE item_id = ? AND bucket_id = ?;");
            query.setParameter(1, itemId);
            query.setParameter(2, bucketId);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return get(bucketId);
    }

    @Override
    public Bucket clear(Long bucketId) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.sessionFactory().openSession();
            transaction = session.beginTransaction();
            Query query = session
                    .createSQLQuery("DELETE FROM buckets_items WHERE bucket_id = ?;");
            query.setParameter(1, bucketId);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return get(bucketId);
    }

    @Override
    public List<Item> getAllItems(Long bucketId) {
        return get(bucketId).getItems();
    }
}
