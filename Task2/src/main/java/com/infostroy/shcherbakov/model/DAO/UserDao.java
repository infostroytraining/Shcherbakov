package com.infostroy.shcherbakov.model.DAO;

import com.infostroy.shcherbakov.model.DAO.interfaces.IUserDao;
import com.infostroy.shcherbakov.model.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserDao implements IUserDao{

    private static final List<User> USER_STORAGE = new ArrayList<>();

    static {
        User user = new User();
        user.setName("name");
        user.setLastName("lastName");
        user.setEmail("email@gmail.com");
        user.setPassword("123123QWEqwe");
        USER_STORAGE.add(user);

        user.setName("name2");
        user.setLastName("lastName2");
        user.setEmail("email2@gmail.com");
        user.setPassword("123123QWEqwe");
        USER_STORAGE.add(user);


        user.setName("name3");
        user.setLastName("lastName3");
        user.setEmail("email3@gmail.com");
        user.setPassword("123123QWEqwe");
        USER_STORAGE.add(user);
    }



    @Override
    public User getUserByEmail(String email) {
        for (User user : USER_STORAGE) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }

        return null;
    }

    @Override
    public synchronized List<User> getAllUsers() {
        return USER_STORAGE;
    }

	@Override
	public void addUser(User user) {
		 USER_STORAGE.add(user);
	}
}
