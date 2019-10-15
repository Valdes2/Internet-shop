package mate.academy.internetshop.dao.hibernate;

import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public class OrderDaoHibernateImpl implements OrderDao {
    @Override
    public Order create(Order order) {
        Long orderId = null;
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.sessionFactory().openSession();
            transaction = session.beginTransaction();
            orderId = (Long) session.save(order);
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
        order.setId(orderId);
        return order;
    }

    @Override
    public Order get(Long id) {
        Session session = null;
        try {
            session = HibernateUtil.sessionFactory().openSession();
            Order order = session.get(Order.class, id);
            return order;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Order update(Order order) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.sessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(order);
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
        return order;
    }

    @Override
    public Order delete(Long id) {
        Order deletedOrder = get(id);
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.sessionFactory().openSession();
            transaction = session.beginTransaction();
            session.delete(deletedOrder);
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
        return deletedOrder;
    }

    public List<Order> getUserOrders(User user) {
        List<Order> userOrders = new ArrayList<>();
        Session session = null;
        try {
            session = HibernateUtil.sessionFactory().openSession();
            Query query = session.createQuery("FROM Order WHERE user=:user");
            query.setParameter("user", user);
            userOrders = query.list();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return userOrders;
    }
}
