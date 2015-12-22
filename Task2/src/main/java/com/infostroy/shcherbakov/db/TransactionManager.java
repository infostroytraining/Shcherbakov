package com.infostroy.shcherbakov.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.infostroy.shcherbakov.db.exception.TransactionException;
import com.infostroy.shcherbakov.model.DAO.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class TransactionManager {

    private static final Logger log = LogManager.getLogger(TransactionManager.class);

    public <T> T doTask(Transaction<T> transaction, int transactionIsolation) throws TransactionException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "54321");
            connection.setAutoCommit(false);
            ConnectionHolder.setConnection(connection);
            connection.setTransactionIsolation(transactionIsolation);
            T value = transaction.execute();
            connection.commit();
            return value;
        } catch (SQLException | DAOException exeption) {
            tryToRollback(connection);
            throw new TransactionException(exeption);
        } finally {
            tryToCloseConnection(connection);
        }
    }

    private void tryToRollback(Connection connection) throws TransactionException {
        if (connection != null) {
            try {
                log.error("Try to rolback transaction");
                connection.rollback();
            } catch (SQLException e) {
                throw new TransactionException(e);
            }
        }
    }

    private void tryToCloseConnection(Connection connection) throws TransactionException {
        ConnectionHolder.setConnection(null);
        try {
            connection.close();
        } catch (SQLException e) {
            log.error("Error while closing the connection", e);
            throw new TransactionException(e);
        }
    }
}