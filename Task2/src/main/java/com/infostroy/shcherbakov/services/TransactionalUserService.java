package com.infostroy.shcherbakov.services;


import com.infostroy.shcherbakov.db.TransactionManager;
import com.infostroy.shcherbakov.db.exception.TransactionException;
import com.infostroy.shcherbakov.model.DAO.UserDao;
import com.infostroy.shcherbakov.model.entity.User;
import com.infostroy.shcherbakov.services.exception.ServiceException;

import java.sql.Connection;

public class TransactionalUserService implements UserService {
    private TransactionManager transactionManager;
    private UserDao userDao;



    public TransactionalUserService(TransactionManager transactionManager, UserDao userDao) {
        this.transactionManager = transactionManager;
        this.userDao = userDao;
    }
    @Override
    public User add(final User user) throws ServiceException {
        try {
            return transactionManager.doTask(() -> userDao.create(user), Connection.TRANSACTION_READ_COMMITTED);
        } catch (TransactionException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User get(int id) throws ServiceException {
        return null;
    }

    @Override
    public User getUserByEmail(String email) throws ServiceException {
        try {
            return transactionManager.doTask(() -> userDao.getUserByEmail(email), Connection.TRANSACTION_READ_COMMITTED);
        } catch (TransactionException e) {
            throw new ServiceException(e);
        }
    }
}
