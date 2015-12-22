package com.infostroy.shcherbakov.services;

import com.infostroy.shcherbakov.captcha.Captcha;
import com.infostroy.shcherbakov.constants.ErrorConstants;
import com.infostroy.shcherbakov.model.DAO.exception.DAOException;
import com.infostroy.shcherbakov.model.bean.Converter;
import com.infostroy.shcherbakov.model.bean.RegistrationBean;
import com.infostroy.shcherbakov.model.bean.Validator;
import com.infostroy.shcherbakov.model.entity.User;
import com.infostroy.shcherbakov.services.exception.ServiceException;

import java.util.List;

public interface UserService {
     User add(User  user) throws ServiceException;
     User get(int id) throws ServiceException;
     User getUserByEmail(String email) throws ServiceException;
}