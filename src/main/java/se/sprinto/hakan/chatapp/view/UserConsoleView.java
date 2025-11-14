package se.sprinto.hakan.chatapp.view;

import se.sprinto.hakan.chatapp.dao.UserDAO;
import se.sprinto.hakan.chatapp.dao.UserDatabaseDAO;
import se.sprinto.hakan.chatapp.model.User;

import java.util.Scanner;

public class UserConsoleView {
    Scanner scanner = new Scanner(System.in);

    public void createUser(){
        UserDAO user = new UserDatabaseDAO();

        System.out.println("Enter your name:");
        String username = scanner.nextLine();
        System.out.println("Enter your password:");
        String password = scanner.nextLine();

        User user1 = new User(username, password);
        user.register(user1);
    }

    public User login(){
        System.out.println("Enter your username:");
        String userName = scanner.nextLine();

        System.out.println("Enter your password:");
        String userPassword = scanner.nextLine();

        User user = new User(userName, userPassword);

        return user;
    }
}
