package mate.academy.internetshop.dao;

import java.util.List;

import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.User;

public interface OrderDao {

    Order create(Order order);

    Order get(Long id);

    Order update(Order order);

    Order delete(Long id);

    List<Order> getUserOrders(User user);
}
