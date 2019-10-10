package mate.academy.internetshop.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.util.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class ItemDaoHibernateImpl implements ItemDao {
    @Override
    public Item create(Item item) {
        Long itemId = null;
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.sessionFactory().openSession();
            transaction = session.beginTransaction();
            itemId = (Long) session.save(item);
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
        item.setId(itemId);
        return item;
    }

    @Override
    public Item get(Long id) {
        Session session = null;
        try {
            session = HibernateUtil.sessionFactory().openSession();
            Item item = session.get(Item.class, id);
            return item;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Item update(Item item) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.sessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(item);
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
        return item;
    }

    @Override
    public Item delete(Long id) {
        Item deletedItem = get(id);
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.sessionFactory().openSession();
            transaction = session.beginTransaction();
            session.delete(deletedItem);
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
        return deletedItem;
    }

    @Override
    public List<Item> getAll() {
        List<Item> allItems = new ArrayList<>();
        Session session = null;
        try {
            session = HibernateUtil.sessionFactory().openSession();
            allItems = session.createQuery("FROM Item").list();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return allItems;
    }
}
