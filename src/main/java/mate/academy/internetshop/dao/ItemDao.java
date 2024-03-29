package mate.academy.internetshop.dao;

import java.util.List;

import mate.academy.internetshop.model.Item;

public interface ItemDao {

    Item create(Item item);

    Item get(Long id);

    Item update(Item item);

    Item delete(Long id);

    List<Item> getAll();
}
