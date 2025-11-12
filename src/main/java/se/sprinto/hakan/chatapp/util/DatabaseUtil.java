package se.sprinto.hakan.chatapp.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseUtil {
    private static DatabaseUtil instance;
    Properties properties;

    private DatabaseUtil(){
        properties = new Properties();
        try(InputStream input = ClassLoader.getSystemResourceAsStream("application.properties")){
            properties.load(input);
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    public static DatabaseUtil getInstance(){
        if(instance == null){
            instance = new DatabaseUtil();
        }
        return instance;
    }

    public String getProperties(String key) throws SQLException {
        return properties.getProperty(key);
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(getProperties("db.url"), getProperties("db.user"), getProperties("db.password"));
    }



}
