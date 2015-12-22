package com.infostroy.shcherbakov.model.DAO;

import com.infostroy.shcherbakov.model.DAO.exception.DAOException;
import com.infostroy.shcherbakov.model.entity.User;

public interface UserDao extends DAO<User>{

    User getUserByEmail(String email) throws DAOException ;

}
