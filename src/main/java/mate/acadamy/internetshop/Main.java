package mate.acadamy.internetshop;

import mate.acadamy.internetshop.lib.Inject;
import mate.acadamy.internetshop.lib.Injector;
import mate.acadamy.internetshop.model.Bucket;
import mate.acadamy.internetshop.model.Item;
import mate.acadamy.internetshop.model.Order;
import mate.acadamy.internetshop.model.User;
import mate.acadamy.internetshop.service.BucketService;
import mate.acadamy.internetshop.service.ItemService;
import mate.acadamy.internetshop.service.OrderService;
import mate.acadamy.internetshop.service.UserService;

public class Main {

    static {
        try {
            Injector.injectDependency();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Inject
    public static BucketService bucketService;

    @Inject
    public static ItemService itemService;

    @Inject
    public static OrderService orderService;

    @Inject
    public static UserService userService;

    public static void main(String[] args) {

        User firstUser = userService.create(new User("Mike", "12345"));

        Item cup = itemService.create(new Item("qFun", 99.99));
        Item book = itemService.create(new Item("Mobi Dick", 500.00));

        Bucket bucket = bucketService.create(new Bucket(firstUser.getId()));
        bucketService.addItem(bucket.getId(), cup.getId());
        bucketService.addItem(bucket.getId(), book.getId());
        bucketService.update(bucket);

        Order newOrder = orderService.completeOrder(bucket.getItems(), firstUser.getId());
        bucketService.clear(bucket.getId());
        bucketService.update(bucket);

        userService.getOrders(firstUser.getId()).stream().forEach(System.out::println);

    }
}
