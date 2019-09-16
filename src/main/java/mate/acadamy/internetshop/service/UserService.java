package mate.acadamy.internetshop.service;

import java.util.List;

import mate.acadamy.internetshop.lib.Service;
import mate.acadamy.internetshop.model.Order;
import mate.acadamy.internetshop.model.User;

public interface UserService {

    User create(User user);

    User get(Long id);

    User update(User user);

    void delete(Long id);

    List<Order> getOrders(Long userId);

}
