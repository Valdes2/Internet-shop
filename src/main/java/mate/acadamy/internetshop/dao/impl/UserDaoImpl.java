package mate.acadamy.internetshop.dao.impl;

import java.util.NoSuchElementException;
import java.util.Optional;

import mate.acadamy.internetshop.dao.UserDao;
import mate.acadamy.internetshop.db.Storage;
import mate.acadamy.internetshop.lib.Dao;
import mate.acadamy.internetshop.model.Order;
import mate.acadamy.internetshop.model.User;

@Dao
public class UserDaoImpl implements UserDao {

    @Override
    public User create(User user) {
        Optional<User> currentUser = Storage.users
                .stream()
                .filter(x -> x.getId().equals(user.getId()))
                .findAny();
        if (!currentUser.equals(Optional.of(user))) {
            Storage.users.add(user);
            return user;
        } else {
            throw new NoSuchElementException("Current user already created");
        }
    }

    @Override
    public User get(Long id) {
        return Storage.users
                .stream()
                .filter(x -> x.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Can`t find user with id " + id));
    }

    @Override
    public User update(User user) {
        for (int i = 0; i < Storage.users.size(); i++) {
            if (Storage.users.get(i).getId().equals(user.getId())) {
                Storage.users.set(i, user);
                return user;
            }
        }
        throw new NoSuchElementException("Can`t find user");
    }

    @Override
    public void delete(Long id) {
        Optional<User> currentUser = Storage.users
                .stream()
                .filter(x -> x.getId().equals(id))
                .findAny();
        if (!currentUser.equals(Optional.of(get(id)))) {
            Storage.users.removeIf(x -> x.getId().equals(id));
        } else {
            throw new NoSuchElementException("Can`t find user");
        }
    }
}
