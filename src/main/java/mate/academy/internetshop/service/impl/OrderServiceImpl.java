package mate.academy.internetshop.service.impl;

import java.util.List;

import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Service;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    @Inject
    private static OrderDao orderDao;
    @Inject
    private static UserDao userDao;

    @Override
    public Order create(Order order) {
        return orderDao.create(order);
    }

    @Override
    public Order get(Long id) {
        return orderDao.get(id);
    }

    @Override
    public Order update(Order order) {
        return orderDao.update(order);
    }

    @Override
    public Order delete(Long id) {
        Order deletedOrder = get(id);
        orderDao.delete(id);
        return deletedOrder;
    }

    @Override
    public Order deleteUserOrder(Long orderId) {
        Order order = get(orderId);
        Long userId = order.getUserId();
        userDao.get(userId).getOrders().remove(order);
        return order;
    }

    @Override
    public Order completeOrder(List items, Long userId) {
        Order completeOrder = orderDao.create(new Order(userId, items));
        User user = userDao.get(userId);
        userDao.addOrder(completeOrder.getId(),userId);
        return completeOrder;
    }
}
