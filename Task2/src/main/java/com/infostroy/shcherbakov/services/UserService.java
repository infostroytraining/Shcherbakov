package com.infostroy.shcherbakov.services;

import com.infostroy.shcherbakov.captcha.Captcha;
import com.infostroy.shcherbakov.constants.ErrorConstants;
import com.infostroy.shcherbakov.model.DAO.interfaces.IUserDao;
import com.infostroy.shcherbakov.model.bean.Converter;
import com.infostroy.shcherbakov.model.bean.RegistrationBean;
import com.infostroy.shcherbakov.model.bean.Validator;
import com.infostroy.shcherbakov.model.entity.User;

import java.util.List;

public class UserService {

    private IUserDao userDao;

    public UserService(IUserDao userDao) {
        this.userDao = userDao;
    }

    public List<String> addNewUser(RegistrationBean registrationBean, Captcha captcha, String captchaInput) {
        List<String> validationErrors = Validator.validateRegistrationBean(registrationBean);

        if (captcha == null || (captchaInput != null && !captchaInput.equals(captcha.getValue()))) {
            validationErrors.add(ErrorConstants.WRONG_CAPTCHA_ERROR);
        }

        if (validationErrors.isEmpty()) {
            User user = Converter.createUserFromRegistrationBean(registrationBean);

            User tempUser = userDao.getUserByEmail(user.getEmail());

            if (tempUser == null) {
                userDao.addUser(user);
            } else {
                validationErrors.add("User already exists");
            }
        }

        return validationErrors;
    }

    public User getUser(String email) {
        return userDao.getUserByEmail(email);
    }

}