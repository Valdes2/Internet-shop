package mate.acadamy.internetshop.service;

import java.util.List;

import mate.acadamy.internetshop.model.Order;

public interface OrderService {

    Order create(Order order);

    Order get(Long id);

    Order update(Order order);

    void delete(Long id);

    Order completeOrder(List items, Long userId);
}
