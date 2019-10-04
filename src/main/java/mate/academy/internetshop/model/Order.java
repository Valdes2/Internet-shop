package mate.academy.internetshop.model;

import java.util.List;

public class Order {
    private Long id;
    private Long userId;
    private List<Item> orderedItems;

    public Order(Long userId, List<Item> items) {
        this.userId = userId;
        this.orderedItems = items;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setOrderedItems(List<Item> orderedItems) {
        this.orderedItems = orderedItems;
    }

    public List<Item> getOrderedItems() {
        return orderedItems;
    }

    @Override
    public String toString() {
        return "Order{"
                + "id=" + id
                + ", userId=" + userId
                + ", orderedItems=" + orderedItems + '}';
    }
}
