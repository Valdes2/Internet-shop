package mate.acadamy.internetshop.service.impl;

import java.util.List;

import mate.acadamy.internetshop.dao.OrderDao;
import mate.acadamy.internetshop.dao.UserDao;
import mate.acadamy.internetshop.lib.Inject;
import mate.acadamy.internetshop.lib.Service;
import mate.acadamy.internetshop.model.Order;
import mate.acadamy.internetshop.model.User;
import mate.acadamy.internetshop.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Inject
    private static UserDao userDao;

    @Inject
    private static OrderDao orderDao;

    @Override
    public User create(User user) {
        return userDao.create(user);
    }

    @Override
    public User get(Long id) {
        return userDao.get(id);
    }

    @Override
    public User update(User user) {
        return userDao.update(user);
    }

    @Override
    public void delete(Long id) {
        userDao.delete(id);
    }

    @Override
    public List<Order> getOrders(Long userId) {
        return userDao.get(userId).getOrders();
    }
}
