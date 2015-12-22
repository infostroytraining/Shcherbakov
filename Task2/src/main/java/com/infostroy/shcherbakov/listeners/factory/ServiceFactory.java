package com.infostroy.shcherbakov.listeners.factory;
import com.infostroy.shcherbakov.db.TransactionManager;
import com.infostroy.shcherbakov.model.DAO.UserDao;
import com.infostroy.shcherbakov.model.DAO.memory.MemoryUserDAO;
import com.infostroy.shcherbakov.model.DAO.postgesql.PostgreUserDAO;
import com.infostroy.shcherbakov.model.storage.UserStorage;
import com.infostroy.shcherbakov.services.MemoryUserService;
import com.infostroy.shcherbakov.services.TransactionalUserService;
import com.infostroy.shcherbakov.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ServiceConfigurationError;

public class ServiceFactory {

    public static final String MEMORY = "memory";
    public static final String DB = "db";
    private static final String POSTGRE_DRIVER = "org.postgresql.Driver";

    private static Logger logger = LogManager.getLogger();

    public static UserService getUserService(String type) {
        if (type == null || type.isEmpty()) {
            logger.fatal("Could initialize application. Source type is null or empty");
            throw new IllegalArgumentException();
        }
        if (type.equals(MEMORY)) {
            return initMemoryService();
        } else if (type.equals(DB)) {
            loadPostgreDriver();
            return initTransactionalService();
        } else {
            logger.fatal("Could initialize application with source type {}", type);
            throw new ServiceConfigurationError("Could initialize application with source type [" + type + "]");
        }
    }

    private static void loadPostgreDriver() {
        try {
            Class.forName(POSTGRE_DRIVER);
        } catch (ClassNotFoundException e) {
            logger.fatal("Could load {} driver", POSTGRE_DRIVER);
            throw new ServiceConfigurationError("Could load" + POSTGRE_DRIVER + "driver");
        }
    }

    private static UserService initMemoryService() {
       UserStorage storage = new UserStorage();
       UserDao userDao = new MemoryUserDAO(storage);
        return new MemoryUserService(userDao);
    }

    private static UserService initTransactionalService() {
        TransactionManager transactionManager = new TransactionManager();
        UserDao userDao = new PostgreUserDAO();
        return new TransactionalUserService(transactionManager, userDao);
    }
}