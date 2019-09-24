package mate.academy.internetshop.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import mate.academy.internetshop.factory.IdGenerator;

public class User {
    private final Long id;
    private String name;
    private String login;
    private String password;
    private String token;
    private List<Order> orders;
    private Long bucketId;
    private Set<Role> roles = new HashSet<>();

    public User(String name, String login, String password) {
        this.id = IdGenerator.getGeneratedId();
        this.name = name;
        this.login = login;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void setBucketId(Long bucketId) {
        this.bucketId = bucketId;
    }

    public Long getBucketId() {
        return bucketId;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void addRole(Role role) {
        roles.add(role);
    }

    @Override
    public String toString() {
        return "User{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", password='" + password + '\''
                + ", orders=" + orders + '}';
    }
}
