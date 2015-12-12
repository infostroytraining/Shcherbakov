package com.infostroy.shcherbakov.model.bean;

import com.infostroy.shcherbakov.model.entity.User;

public class Converter {

    public static User createUserFromRegistrationBean(RegistrationBean registrationBean){
        User user = new User();

        user.setName(registrationBean.getName());
        user.setLastName(registrationBean.getLastName());
        user.setEmail(registrationBean.getEmail());
        user.setPassword(registrationBean.getPassword());
        user.setAvatarName(registrationBean.getAvatarName());

        return user;
    }
}
