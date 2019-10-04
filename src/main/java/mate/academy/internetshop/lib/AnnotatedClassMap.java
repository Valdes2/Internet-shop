package mate.academy.internetshop.lib;

import java.util.HashMap;
import java.util.Map;

import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.dao.RoleDao;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.factory.Factory;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.ItemService;
import mate.academy.internetshop.service.OrderService;
import mate.academy.internetshop.service.UserService;

public class AnnotatedClassMap {
    private static Map<Class,Object> classesFields = new HashMap<>();

    static {
        classesFields.put(BucketDao.class, Factory.getBucketDao());
        classesFields.put(ItemDao.class, Factory.getItemDao());
        classesFields.put(OrderDao.class, Factory.getOrderDao());
        classesFields.put(UserDao.class, Factory.getUserDao());
        classesFields.put(RoleDao.class,Factory.getRoleDao());
        classesFields.put(BucketService.class, Factory.getBucketService());
        classesFields.put(ItemService.class, Factory.getItemService());
        classesFields.put(OrderService.class, Factory.getOrderService());
        classesFields.put(UserService.class, Factory.getUserService());
    }

    public static Object getImplimentation(Class interfaceClass) {
        return classesFields.get(interfaceClass);
    }
}
