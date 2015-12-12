package com.infostroy.shcherbakov.model.DAO.interfaces;

import com.infostroy.shcherbakov.model.entity.User;

import java.util.List;

public interface IUserDao {

    void addUser(User user);

    User getUserByEmail(String email);

    List<User> getAllUsers();
}
