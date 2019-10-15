package mate.academy.internetshop.dao.hibernate;

import java.util.Set;

import mate.academy.internetshop.dao.RoleDao;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Role;
import mate.academy.internetshop.util.HibernateUtil;

import org.hibernate.Session;

@Dao
public class RoleDaoHibernateImpl implements RoleDao {
    @Override
    public Role getRole(Long roleId) {
        Session session = null;
        try {
            session = HibernateUtil.sessionFactory().openSession();
            Role role = session.get(Role.class, roleId);
            return role;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
