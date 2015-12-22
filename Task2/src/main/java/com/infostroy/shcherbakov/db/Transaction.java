package com.infostroy.shcherbakov.db;


import com.infostroy.shcherbakov.model.DAO.exception.DAOException;

@FunctionalInterface
public interface Transaction<T> {

    T execute() throws DAOException;

}