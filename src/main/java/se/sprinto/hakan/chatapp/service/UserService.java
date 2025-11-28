package se.sprinto.hakan.chatapp.service;

import se.sprinto.hakan.chatapp.dao.UserDAO;

import java.util.regex.Pattern;

public class UserService {
    private final UserDAO userDAO;

    public UserService (UserDAO userDAO){
        this.userDAO = userDAO;
    }

    public String checkUsernameAvailable(String username){
        if(userDAO.findByUsername(username) != null){
            throw new IllegalArgumentException("This username exists already");
        }
        return username;
    }

    public String validatePassword(String password){
        if (password == null || password.isEmpty()){
            //System.out.println("Lösenordet får inte lämnas tomt.");
            return null;
        }
        String regex = "^(?=(?:.*[a-zA-Z]){1,})(?=(?:.*\\d){1,}).{4,}$";
        if (!Pattern.matches(regex, password)){
            //System.out.println("Lösenordet måste innehålla minst en bokstav, minst en siffra och vara minst 4 tecken långt.");
            return null;
        }
        return password;
    }
}
