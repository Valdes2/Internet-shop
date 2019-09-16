package mate.acadamy.internetshop.db;

import java.util.ArrayList;
import java.util.List;

import mate.acadamy.internetshop.model.Bucket;
import mate.acadamy.internetshop.model.Item;
import mate.acadamy.internetshop.model.Order;
import mate.acadamy.internetshop.model.User;

public class Storage {
    public static final List<Bucket> buckets = new ArrayList<Bucket>();
    public static final List<Item> items = new ArrayList<>();
    public static final List<Order> orders = new ArrayList<>();
    public static final List<User> users = new ArrayList<>();
}
