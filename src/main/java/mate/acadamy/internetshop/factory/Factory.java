package mate.acadamy.internetshop.factory;

import mate.acadamy.internetshop.dao.BucketDao;
import mate.acadamy.internetshop.dao.ItemDao;
import mate.acadamy.internetshop.dao.OrderDao;
import mate.acadamy.internetshop.dao.UserDao;
import mate.acadamy.internetshop.dao.impl.BucketDaoImpl;
import mate.acadamy.internetshop.dao.impl.ItemDaoImpl;
import mate.acadamy.internetshop.dao.impl.OrderDaoImpl;
import mate.acadamy.internetshop.dao.impl.UserDaoImpl;
import mate.acadamy.internetshop.service.BucketService;
import mate.acadamy.internetshop.service.ItemService;
import mate.acadamy.internetshop.service.OrderService;
import mate.acadamy.internetshop.service.UserService;
import mate.acadamy.internetshop.service.impl.BucketServiceImpl;
import mate.acadamy.internetshop.service.impl.ItemServiceImpl;
import mate.acadamy.internetshop.service.impl.OrderServiceImpl;
import mate.acadamy.internetshop.service.impl.UserServiceImpl;

public class Factory {

    private static BucketDao bucketDao;
    private static ItemDao itemDao;
    private static OrderDao orderDao;
    private static UserDao userDao;

    private static BucketService bucketService;
    private static ItemService itemService;
    private static OrderService orderService;
    private static UserService userService;

    public static BucketDao getBucketDao() {
        if (bucketDao == null) {
            bucketDao = new BucketDaoImpl();
        }
        return bucketDao;
    }

    public static ItemDao getItemDao() {
        if (itemDao == null) {
            itemDao = new ItemDaoImpl();
        }
        return itemDao;
    }

    public static OrderDao getOrderDao() {
        if (orderDao == null) {
            orderDao = new OrderDaoImpl();
        }
        return orderDao;
    }

    public static UserDao getUserDao() {
        if (userDao == null) {
            userDao = new UserDaoImpl();
        }
        return userDao;
    }

    public static BucketService getBucketService() {
        if (bucketService == null) {
            bucketService = new BucketServiceImpl();
        }
        return bucketService;
    }

    public static ItemService getItemService() {
        if (itemService == null) {
            itemService = new ItemServiceImpl();
        }
        return itemService;
    }

    public static OrderService getOrderService() {
        if (orderService == null) {
            orderService = new OrderServiceImpl();
        }
        return orderService;
    }

    public static UserService getUserService() {
        if (userService == null) {
            userService = new UserServiceImpl();
        }
        return userService;
    }
}
