package mate.academy.internetshop.service;

import java.util.List;

import mate.academy.internetshop.model.Order;

public interface OrderService {

    Order create(Order order);

    Order get(Long id);

    Order update(Order order);

    Order delete(Long id);

    Order deleteUserOrder(Long orderId);

    Order completeOrder(List items, Long userId);
}
