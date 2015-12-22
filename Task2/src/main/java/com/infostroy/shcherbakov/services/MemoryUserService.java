package com.infostroy.shcherbakov.services;

import com.infostroy.shcherbakov.model.DAO.UserDao;
import com.infostroy.shcherbakov.model.DAO.exception.DAOException;
import com.infostroy.shcherbakov.model.entity.User;
import com.infostroy.shcherbakov.services.exception.ServiceException;

import java.util.List;

public class MemoryUserService implements UserService {
    private UserDao userDao;

    public MemoryUserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> getAll() throws DAOException {
        return userDao.getAll();
    }

    @Override
    public User add(User user) throws ServiceException {
        User createdUser = null;
        if (user != null) {
            try {
                createdUser = userDao.create(user);
            } catch (DAOException e) {
                throw new ServiceException(e);
            }
        }
        return createdUser;
    }

    @Override
    public User get(int id) throws ServiceException {
       return userDao.get(id);
    }

    @Override
    public User getUserByEmail(String email) throws ServiceException {
        try {
            return userDao.getUserByEmail(email);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }


}
