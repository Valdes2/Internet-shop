package mate.academy.internetshop.dao.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Item;

@Dao
public class ItemDaoImpl implements ItemDao {
    @Override
    public Item create(Item item) {
        Optional<Item> currentItem = Storage.items
                .stream()
                .filter(x -> x.getId().equals(item.getId()))
                .findAny();
        if (!currentItem.equals(Optional.of(item))) {
            Storage.items.add(item);
            return item;
        } else {
            update(item);
            return item;
        }
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
        for (int i = 0; i < Storage.items.size(); i++) {
            if (Storage.items.get(i).getId().equals(item.getId())) {
                Storage.items.set(i, item);
                return item;
            }
        }
        throw new NoSuchElementException("Can`t find item " + item.getName());
    }

    @Override
    public Item delete(Long id) {
        Item deletedItem = get(id);
        Storage.items.removeIf(x -> x.getId().equals(id));
        return deletedItem;
    }

    @Override
    public List<Item> getAll() {
        return Storage.items;
    }
}