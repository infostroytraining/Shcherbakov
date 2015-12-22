package com.infostroy.shcherbakov.model.DAO.postgesql;



import com.infostroy.shcherbakov.db.ConnectionHolder;
import com.infostroy.shcherbakov.model.DAO.UserDao;
import com.infostroy.shcherbakov.model.DAO.exception.DAOException;
import com.infostroy.shcherbakov.model.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class PostgreUserDAO implements UserDao {
    private static final String INSERT_USER = "INSERT INTO userdb.user(first_name,last_name,email,password, avatar_name) values(?,?,?,?,?);";
    private static final String SELECT_USER_BY_EMAIL = "SELECT * FROM userdb.user WHERE email = ?";
    private static final String SELECT_USER_BY_ID = "SELECT * FROM userdb.user WHERE id = ?";
    private static final String SELECT_ALL_USERS = "SELECT * FROM userdb.user";
    private static final String UPDATE_USER = "UPDATE userdb.user SET password=?, first_name=?, last_name=? WHERE email=?";
    private static final String REMOVE_USER_BY_ID = "DELETE FROM userdb.user WHERE id = ?";

    private static final Logger log = LogManager.getLogger();

    @Override
    public User create(User user) throws DAOException {
        log.entry(user);
        Connection connection = ConnectionHolder.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getAvatarName());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException ex) {
            log.error("SQLException during user insert query", ex);
            throw new DAOException(ex);
        }
        log.exit(user);
        return user;
    }



    @Override
    public User get(int id) {
        log.entry(id);
        User user = null;
        Connection connection = ConnectionHolder.getConnection();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);
            preparedStatement.setString(1, String.valueOf(id));
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                user=extractUser(resultSet);
            }
        }catch (SQLException ex) {
            log.error("SQLException during get user by id", ex);
           // throw new DAOException(ex);
        }
        log.exit(user);
        return user;
    }

    @Override
    public User update(User user) throws DAOException {
        log.entry(user);
        Connection connection = ConnectionHolder.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(UPDATE_USER);
            statement.setString(1,user.getPassword());
            statement.setString(2,user.getName());
            statement.setString(3,user.getLastName());
            statement.setString(4,user.getEmail());
            statement.executeUpdate();
        } catch (SQLException ex) {
            log.error("SQLException during user update", ex);
            throw new DAOException(ex);
        }
        log.exit(user);
        return user;
    }

    @Override
    public void remove(int id) {
        log.entry(id);
        Connection connection = ConnectionHolder.getConnection();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_USER_BY_ID);
            preparedStatement.setString(1, String.valueOf(id));
            preparedStatement.executeUpdate();
        }catch (SQLException ex) {
            log.error("SQLException during remove user by id", ex);
        }
    }

    @Override
    public List<User> getAll() throws DAOException {
        List<User> userList=new LinkedList<>();
        Connection connection = ConnectionHolder.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
           while(resultSet.next()){
              User user= extractUser(resultSet);
               userList.add(user);
           }

        } catch (SQLException ex) {
            log.error("SQLException during get all users", ex);
            throw new DAOException(ex);
        }
        log.exit(userList);
        return userList;
    }

    @Override
    public User getUserByEmail(String email) throws DAOException {
        log.entry(email);
        User user = null;
        Connection connection = ConnectionHolder.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_EMAIL)){
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                user=extractUser(resultSet);
            }
        }catch (SQLException ex) {
            log.error("SQLException during get user by email", ex);
            throw new DAOException(ex);
        }
        return user;
    }
    private User extractUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setPassword(rs.getString("password"));
        user.setEmail(rs.getString("email"));
        user.setAvatarName(rs.getString("avatar_name"));
        return user;
    }


}

