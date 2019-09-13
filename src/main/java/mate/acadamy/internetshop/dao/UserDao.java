package mate.acadamy.internetshop.dao;

import mate.acadamy.internetshop.model.User;

public interface UserDao {

    User create(User user);

    User get(Long id);

    User update(User user);

    void delete(Long id);
}
