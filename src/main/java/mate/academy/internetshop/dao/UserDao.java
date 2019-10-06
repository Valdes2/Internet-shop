package mate.academy.internetshop.dao;

import java.util.List;
import java.util.Optional;

import mate.academy.internetshop.exceptions.AuthenticationException;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.User;

public interface UserDao {

    User create(User user);

    User get(Long id);

    User update(User user);

    User delete(Long id);

    List<User> getAll();

    User login(String login, String password) throws AuthenticationException;

    Optional<User> getByToken(String token);

    byte[] getSaltByLogin(String login);

    void addRole(Long roleId, Long userId);

    void addOrder(Long orderId, Long userId);

    List<Order> getUserOrders(Long userId);
}
