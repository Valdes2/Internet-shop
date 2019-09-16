package mate.acadamy.internetshop.dao;

import mate.acadamy.internetshop.model.Item;

public interface ItemDao {

    Item create(Item item);

    Item get(Long id);

    Item update(Item item);

    Item delete(Long id);
}
