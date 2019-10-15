package mate.academy.internetshop.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import mate.academy.internetshop.exceptions.AuthenticationException;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.Role;
import mate.academy.internetshop.model.User;

public interface UserService {

    User create(User user);

    User get(Long id);

    User update(User user);

    void delete(Long id);

    List<Order> getOrders(User user);

    List<User> getAllUsers();

    User login(String login, String password) throws AuthenticationException;

    Optional<User> getByToken(String token);

}
