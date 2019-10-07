package mate.academy.internetshop.dao.hibernate;

import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

@Dao
public class ItemDaoHibernateImpl implements ItemDao {
    @Override
    public Item create(Item item) {
        Long itemId = null;
        Transaction transaction = null;
        try(Session session = HibernateUtil.sessionFactory().openSession()) {
            transaction = session.beginTransaction();
            itemId =(Long) session.save(item);
            transaction.commit();
        } catch (Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
        }
        item.setId(itemId);
        return item;
    }

    @Override
    public Item get(Long id) {
        try(Session session = HibernateUtil.sessionFactory().openSession()) {
            Item item = session.get(Item.class, id);
            return item;
        }
    }

    @Override
    public Item update(Item item) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.sessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(item);
            transaction.commit();
        } catch (Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
        }
        return item;
    }

    @Override
    public Item delete(Long id) {
        Item deletedItem = get(id);
        Transaction transaction = null;
        try(Session session = HibernateUtil.sessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(deletedItem);
            transaction.commit();
        } catch (Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
        }
        return deletedItem;
    }

    @Override
    public List<Item> getAll() {
        List<Item> allItems = null;
        try(Session session = HibernateUtil.sessionFactory().openSession()) {
            Criteria criteria = session.createCriteria(Item.class);
            allItems = criteria.list();
        }
        return allItems;
    }
}