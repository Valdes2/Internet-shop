package mate.acadamy.internetshop.model;

import java.util.List;

import mate.acadamy.internetshop.factory.IdGenerator;

public class Order {
    private final Long id;
    private Long userId;
    private List<Item> orderedItems;

    public Order(Long userId, List<Item> items) {
        this.id = IdGenerator.getGeneratedId();
        this.userId = userId;
        this.orderedItems = items;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public List<Item> getOrderedItems() {
        return orderedItems;
    }

    @Override
    public String toString() {
        return "Order[id: " + id + ", userId: " + userId + "] \n";
    }

}
