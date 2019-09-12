package mate.acadamy.internetshop.model;

public class Item {
    private static Long id;
    private String name;
    private Double price;

    public static Long getId() {
        return id;
    }

    public static void setId(Long id) {
        Item.id = id;
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
}
