package mate.academy.internetshop.dao.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.User;

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
    public User delete(Long id) {
        User deletedUser = get(id);
        Storage.users.removeIf(x -> x.getId().equals(id));
        return deletedUser;
    }

    @Override
    public List<User> getAll() {
        return Storage.users;
    }
}
