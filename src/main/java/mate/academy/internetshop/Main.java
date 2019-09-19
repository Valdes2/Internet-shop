package mate.academy.internetshop;

import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.ItemService;
import mate.academy.internetshop.service.OrderService;
import mate.academy.internetshop.service.UserService;

public class Main {

    @Inject
    public static BucketService bucketService;

    @Inject
    public static ItemService itemService;

    @Inject
    public static OrderService orderService;

    @Inject
    public static UserService userService;

    static {
        try {
            Injector.injectDependency();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        User mike = userService.create(new User("Mike", "12345"));

        Item cup = itemService.create(new Item("qFun", 99.99));
        Item book = itemService.create(new Item("Mobi Dick", 500.00));

        Bucket bucket = bucketService.create(new Bucket(mike.getId()));
        bucketService.addItem(bucket.getId(), cup.getId());
        bucketService.addItem(bucket.getId(), book.getId());
        bucketService.update(bucket);

        Order newOrder = new Order(mike.getId(), bucket.getItems());
        orderService.completeOrder(bucket.getItems(), mike.getId());
        bucketService.clear(bucket.getId());
        bucketService.update(bucket);

        userService.getOrders(mike.getId()).stream().forEach(System.out::println);

    }
}
