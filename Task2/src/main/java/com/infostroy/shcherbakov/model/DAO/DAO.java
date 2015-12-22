package com.infostroy.shcherbakov.model.DAO;

import com.infostroy.shcherbakov.model.DAO.exception.DAOException;

import java.util.List;

public interface DAO<T> {

    T create(T entity) throws DAOException;

    T get(int id);

    T update(T entity) throws DAOException;

    void remove(int id);

    List<T> getAll() throws DAOException;
}