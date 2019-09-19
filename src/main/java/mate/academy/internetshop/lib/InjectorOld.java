package mate.academy.internetshop.lib;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.factory.Factory;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.ItemService;
import mate.academy.internetshop.service.OrderService;
import mate.academy.internetshop.service.UserService;
import mate.academy.internetshop.service.impl.BucketServiceImpl;
import mate.academy.internetshop.service.impl.ItemServiceImpl;
import mate.academy.internetshop.service.impl.OrderServiceImpl;
import mate.academy.internetshop.service.impl.UserServiceImpl;

public class InjectorOld {
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
