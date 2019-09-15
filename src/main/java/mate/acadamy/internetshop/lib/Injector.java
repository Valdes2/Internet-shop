package mate.acadamy.internetshop.lib;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import mate.acadamy.internetshop.Main;
import mate.acadamy.internetshop.dao.BucketDao;
import mate.acadamy.internetshop.dao.ItemDao;
import mate.acadamy.internetshop.dao.OrderDao;
import mate.acadamy.internetshop.dao.UserDao;
import mate.acadamy.internetshop.factory.Factory;
import mate.acadamy.internetshop.model.Item;
import mate.acadamy.internetshop.service.BucketService;
import mate.acadamy.internetshop.service.ItemService;
import mate.acadamy.internetshop.service.OrderService;
import mate.acadamy.internetshop.service.UserService;
import mate.acadamy.internetshop.service.impl.BucketServiceImpl;
import mate.acadamy.internetshop.service.impl.ItemServiceImpl;
import mate.acadamy.internetshop.service.impl.OrderServiceImpl;
import mate.acadamy.internetshop.service.impl.UserServiceImpl;

public class Injector {
    private static Map<Class,Object> classesFields = new HashMap<>();

    static {
        classesFields.put(BucketDao.class, Factory.getBucketDao());
        classesFields.put(ItemDao.class, Factory.getItemDao());
        classesFields.put(OrderDao.class, Factory.getOrderDao());
        classesFields.put(UserDao.class, Factory.getUserDao());
        classesFields.put(BucketService.class, Factory.getBucketService());
        classesFields.put(ItemService.class, Factory.getItemService());
        classesFields.put(OrderService.class, Factory.getOrderService());
        classesFields.put(UserService.class, Factory.getUserService());
    }

    public static void injectDependency() throws IllegalAccessException {
        inject(BucketServiceImpl.class.getDeclaredFields());
        inject(ItemServiceImpl.class.getDeclaredFields());
        inject(OrderServiceImpl.class.getDeclaredFields());
        inject(UserServiceImpl.class.getDeclaredFields());
        inject(Main.class.getDeclaredFields());

    }

    public static void inject(Field[] fields) throws IllegalAccessException {
        for (Field field:fields) {
            if (field.getDeclaredAnnotation(Inject.class) != null) {
                field.setAccessible(true);
                field.set(null, classesFields.get(field.getType()));
            }
        }
    }
}
