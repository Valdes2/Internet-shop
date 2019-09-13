package mate.acadamy.internetshop.service.impl;

import java.util.ArrayList;
import java.util.List;

import mate.acadamy.internetshop.dao.OrderDao;
import mate.acadamy.internetshop.dao.UserDao;
import mate.acadamy.internetshop.lib.Inject;
import mate.acadamy.internetshop.lib.Service;
import mate.acadamy.internetshop.model.Item;
import mate.acadamy.internetshop.model.Order;
import mate.acadamy.internetshop.service.OrderService;

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
    public void delete(Long id) {
        orderDao.delete(id);
    }

    @Override
    public Order completeOrder(List items, Long userId) {
        Order completeOrder = new Order(userId, items);
        orderDao.create(completeOrder);
        userDao.get(userId).getOrders().add(completeOrder);
        return completeOrder;
    }


}
