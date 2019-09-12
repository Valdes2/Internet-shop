package mate.acadamy.internetshop.dao.impl;

import java.util.NoSuchElementException;

import mate.acadamy.internetshop.dao.ItemDao;
import mate.acadamy.internetshop.dao.Storage;
import mate.acadamy.internetshop.model.Item;

public class ItemDaoImpl implements ItemDao {
    @Override
    public Item create(Item item) {
        return null;
    }

    @Override
    public Item get(Long id) {
        return Storage.items
                .stream()
                .filter(x -> x.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Can`t find item with id " + id));
    }

    @Override
    public Item update(Item item) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void delete(Item item) {

    }
}
