package se.sprinto.hakan.chatapp.service;

import se.sprinto.hakan.chatapp.dao.UserDAO;
import se.sprinto.hakan.chatapp.dao.UserDatabaseDAO;
import se.sprinto.hakan.chatapp.model.User;

public class UserService {
    private final UserDAO userDAO;

    public UserService (UserDAO userDAO){
        this.userDAO = userDAO;
    }

    public String registerUser(String username){
        if(userDAO.findByUsername(username) != null){
//        if(UserDatabaseDAO.findByUsername(username != null){
            throw new IllegalArgumentException("User name exists already");
        }
        return username;
    }
}
