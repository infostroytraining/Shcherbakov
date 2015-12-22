package com.infostroy.shcherbakov.model.DAO.memory;


import com.infostroy.shcherbakov.model.DAO.UserDao;
import com.infostroy.shcherbakov.model.DAO.exception.DAOException;
import com.infostroy.shcherbakov.model.entity.User;
import com.infostroy.shcherbakov.model.storage.UserStorage;

import java.util.List;

public class MemoryUserDAO implements UserDao {

    private UserStorage storage;

    public MemoryUserDAO(UserStorage storage) {
        this.storage = storage;
    }

    @Override
    public User create(User user) throws DAOException {
        return storage.add(user);
    }



    @Override
    public User get(int id) {
        return storage.getUserById(id);
    }

    @Override
    public User update(User entity) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<User> getAll() throws DAOException {
        return null;
    }

    @Override
    public User getUserByEmail(String email) throws DAOException {
        return storage.getUserByEmail(email);
    }
}
