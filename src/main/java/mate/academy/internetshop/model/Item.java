package mate.academy.internetshop.model;

import mate.academy.internetshop.factory.IdGenerator;

public class  Item {
    private final Long id;
    private String name;
    private Double price;
    private String description;

    public Item(String name, Double price) {
        this.id = IdGenerator.getGeneratedId();
        this.name = name;
        this.price = price;
    }

    public Item(Long id) {
        this.id = id;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Item{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", price=" + price + '}';
    }
}
