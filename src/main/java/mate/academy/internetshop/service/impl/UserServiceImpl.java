package mate.academy.internetshop.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.dao.RoleDao;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.exceptions.AuthenticationException;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Service;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Inject
    private static UserDao userDao;

    @Inject
    private static OrderDao orderDao;

    @Inject
    private static RoleDao roleDao;

    @Inject
    private static BucketDao bucketDao;

    @Override
    public User create(User user) {
        user.setToken(getToken());
        return userDao.create(user);
    }

    private String getToken() {
        return UUID.randomUUID().toString();
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
        User user = userDao.delete(id);
        bucketDao.clear(user.getBucket().getId());
    }

    @Override
    public List<Order> getOrders(User user) {
        return orderDao.getUserOrders(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAll();
    }

    @Override
    public User login(String login, String password) throws AuthenticationException {
        return userDao.login(login, password);
    }

    @Override
    public Optional<User> getByToken(String token) {
        return userDao.getByToken(token);
    }

}
