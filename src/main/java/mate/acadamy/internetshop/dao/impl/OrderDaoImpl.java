package mate.acadamy.internetshop.dao.impl;

import java.util.NoSuchElementException;
import java.util.Optional;

import mate.acadamy.internetshop.dao.OrderDao;
import mate.acadamy.internetshop.db.Storage;
import mate.acadamy.internetshop.lib.Dao;
import mate.acadamy.internetshop.model.Order;

@Dao
public class OrderDaoImpl implements OrderDao {
    @Override
    public Order create(Order order) {
        Optional<Order> currentOrder = Storage.orders
                .stream()
                .filter(x -> x.getId().equals(order.getId()))
                .findAny();
        if (!currentOrder.equals(Optional.of(order))) {
            Storage.orders.add(order);
            return order;
        } else {
            throw new NoSuchElementException("Current order already created");
        }
    }

    @Override
    public Order get(Long id) {
        return Storage.orders
                .stream()
                .filter(x -> x.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Can`t find order with id " + id));
    }

    @Override
    public Order update(Order order) {
        for (int i = 0; i < Storage.orders.size(); i++) {
            if (Storage.orders.get(i).getId().equals(order.getId())) {
                Storage.orders.set(i, order);
                return order;
            }
        }
        throw new NoSuchElementException("Can`t find order");
    }

    @Override
    public void delete(Long id) {
        Optional<Order> currentOrder = Storage.orders
                .stream()
                .filter(x -> x.getId().equals(id))
                .findAny();
        if (!currentOrder.equals(Optional.of(get(id)))) {
            Storage.orders.removeIf(x -> x.getId().equals(id));
        } else {
            throw new NoSuchElementException("Can`t find order");
        }
    }
}
