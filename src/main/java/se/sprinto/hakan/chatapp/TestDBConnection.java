package se.sprinto.hakan.chatapp;

import java.sql.*;

public class TestDBConnection {
    String url = "jdbc:mysql://localhost:3306/chatapp_db";
    String user = "chatapp-user";
    String password = "Pass123!";

    public void connectToDB(){
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            DatabaseMetaData meta = conn.getMetaData();
            System.out.println("Successfully connected to the chatapp_db");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
