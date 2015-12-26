package com.infostroy.shcherbakov.model.bean;

import com.infostroy.shcherbakov.constants.EntityConstants;

import java.util.HashMap;
import java.util.Map;

public class Validator {

    private final static String NAME_REGEX = "^(?=.{3,20}$)(?![_.-])(?!.*[_.-]{2})[a-zA-Z0-9_-]+([^._-])$";
    private final static String EMAIL_REGEX = "^(([a-zA-Z]|[0-9])|([-]|[_]|[.]))+[@](([a-zA-Z0-9])|([-])){2,63}[.](([a-zA-Z0-9]){2,63})+$";
    private final static String PASSWORD_REGEX = "^((?=\\S*?[A-Z])(?=\\S*?[a-z])(?=\\S*?[0-9]).{6,})\\S$";

    public static Map<String,String> validateRegistrationBean(RegistrationBean registrationBean) {
        Map<String,String> errors=new HashMap<>();

        if (!registrationBean.getName().matches(NAME_REGEX)) {
            errors.put(EntityConstants.USER_NAME,"Invalid name.");
        }

        if (!registrationBean.getLastName().matches(NAME_REGEX)) {
            errors.put(EntityConstants.USER_LAST_NAME,"Invalid last name.");
        }

        if (!registrationBean.getEmail().matches(EMAIL_REGEX)) {
            errors.put(EntityConstants.USER_EMAIL,"Invalid email.");
        }

        if (!registrationBean.getPassword().matches(PASSWORD_REGEX)) {
            errors.put(EntityConstants.USER_PASSWORD,"Invalid password.");
        }

        if (!registrationBean.getPassword().equals(registrationBean.getRepeatedPassword())) {
            errors.put(EntityConstants.USER_REPEATED_PASSWORD,"Invalid password confirmation.");
        }

        return errors;
    }
}
