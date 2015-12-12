package com.infostroy.shcherbakov.model.bean;

import java.util.ArrayList;
import java.util.List;

public class Validator {

    private final static String NAME_REGEX = "^(?=.{3,20}$)(?![_.-])(?!.*[_.-]{2})[a-zA-Z0-9_-]+([^._-])$";
    private final static String EMAIL_REGEX = "^(([a-zA-Z]|[0-9])|([-]|[_]|[.]))+[@](([a-zA-Z0-9])|([-])){2,63}[.](([a-zA-Z0-9]){2,63})+$";
    private final static String PASSWORD_REGEX = "^((?=\\S*?[A-Z])(?=\\S*?[a-z])(?=\\S*?[0-9]).{6,})\\S$";

    public static List<String> validateRegistrationBean(RegistrationBean registrationBean) {
        List<String> errors = new ArrayList<>();

        if (!registrationBean.getName().matches(NAME_REGEX)) {
            errors.add("Invalid name.");
        }

        if (!registrationBean.getLastName().matches(NAME_REGEX)) {
            errors.add("Invalid last name.");
        }

        if (!registrationBean.getEmail().matches(EMAIL_REGEX)) {
            errors.add("Invalid email.");
        }

        if (!registrationBean.getPassword().matches(PASSWORD_REGEX)) {
            errors.add("Invalid password.");
        }

        if (!registrationBean.getPassword().equals(registrationBean.getRepeatedPassword())) {
            errors.add("Invalid password confirmation.");
        }

        return errors;
    }
}
