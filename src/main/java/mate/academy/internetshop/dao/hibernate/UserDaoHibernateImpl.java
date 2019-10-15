package mate.academy.internetshop.dao.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.exceptions.AuthenticationException;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.util.HashUtil;
import mate.academy.internetshop.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Dao
public class UserDaoHibernateImpl implements UserDao {
    @Override
    public User create(User user) {
        Long userId = null;
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.sessionFactory().openSession();
            transaction = session.beginTransaction();
            userId = (Long) session.save(user);
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
        user.setId(userId);
        return user;
    }

    @Override
    public User get(Long id) {
        Session session = null;
        try {
            session = HibernateUtil.sessionFactory().openSession();
            User user = session.get(User.class, id);
            return user;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public User update(User user) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.sessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(user);
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
        return user;
    }

    @Override
    public User delete(Long id) {
        User deletedUser = get(id);
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.sessionFactory().openSession();
            transaction = session.beginTransaction();
            session.delete(deletedUser);
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
        return deletedUser;
    }

    @Override
    public List<User> getAll() {
        List<User> allUsers = new ArrayList<>();
        Session session = null;
        try {
            session = HibernateUtil.sessionFactory().openSession();
            allUsers = session.createQuery("FROM User").list();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return allUsers;
    }

    @Override
    public User login(String login, String password) throws AuthenticationException {
        Session session = null;
        try {
            session = HibernateUtil.sessionFactory().openSession();
            Query query = session
                    .createQuery("FROM User WHERE login=:login");
            query.setParameter("login", login);
            User user = (User) query.uniqueResult();
            if (user.getPassword().equals(HashUtil.hashPassword(password, user.getSalt()))) {
                return user;
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        throw new AuthenticationException("Incorrect login or password");
    }

    @Override
    public Optional<User> getByToken(String token) {
        Session session = null;
        try {
            session = HibernateUtil.sessionFactory().openSession();
            Query query = session.createQuery("FROM User WHERE token=:token");
            query.setParameter("token", token);
            List<User> list = query.list();
            return list.stream().findFirst();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

}
