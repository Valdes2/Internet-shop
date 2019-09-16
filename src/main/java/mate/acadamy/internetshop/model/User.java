package mate.acadamy.internetshop.model;

import java.util.ArrayList;
import java.util.List;

import mate.acadamy.internetshop.factory.IdGenerator;

public class User {
    private final Long id;
    private String name;
    private String password;
    private List<Order> orders;

    public User(String name, String password) {
        this.id = IdGenerator.getGeneratedId();
        this.name = name;
        this.password = password;
        this.orders = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
