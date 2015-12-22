package com.infostroy.shcherbakov.model.storage;

import com.infostroy.shcherbakov.model.entity.User;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class UserStorage {
    private Map<Integer, User> storage;
    private AtomicInteger id = new AtomicInteger();

    public UserStorage() {
        storage = new HashMap<>();
    }

    public User add(User user) {
        int id = generateId();
        user.setId(id);
        storage.put(id, user);
        return user;
    }

    public User getUserById(int id){
        return storage.get(id);
    }

    public User getUserByEmail(String email){
      List<User> userList=new ArrayList<>(storage.values());
        for(int i=0;i<userList.size();i++){
            if(userList.get(i).getEmail().equals(email)){
                return userList.get(i);
            }
        }
        return null;
    }


    public List<User> all() {
        return new ArrayList<User>(storage.values());
    }

    private int generateId() {
        return id.incrementAndGet();
    }
}
