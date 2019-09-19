package mate.academy.internetshop.model;

import java.util.ArrayList;
import java.util.List;

import mate.academy.internetshop.factory.IdGenerator;

public class Bucket {
    private final Long id;
    private Long userId;
    private List<Item> items;

    public Bucket(Long userId) {
        this.id = IdGenerator.getGeneratedId();
        this.userId = userId;
        this.items = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Bucket{"
                + "id=" + id
                + ", userId=" + userId
                + ", items=" + items + '}';
    }
}
