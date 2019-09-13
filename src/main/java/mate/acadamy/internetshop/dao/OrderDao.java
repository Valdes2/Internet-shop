package mate.acadamy.internetshop.dao;

import mate.acadamy.internetshop.model.Order;

public interface OrderDao {

    Order create(Order order);

    Order get(Long id);

    Order update(Order order);

    void delete(Long id);

}
