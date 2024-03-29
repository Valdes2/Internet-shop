package mate.academy.internetshop.controller;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import mate.academy.internetshop.lib.Injector;
import org.apache.log4j.Logger;

public class InjectorInitializer implements ServletContextListener {
    private static final Logger logger = Logger.getLogger(InjectorInitializer.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            logger.debug("Injection started...");
            Injector.injectDependency();
        } catch (IllegalAccessException e) {
            logger.fatal("Injection was failed!", e);
        }
    }
}
