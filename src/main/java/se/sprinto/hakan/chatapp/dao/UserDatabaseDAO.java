package se.sprinto.hakan.chatapp.dao;

import se.sprinto.hakan.chatapp.model.User;
import se.sprinto.hakan.chatapp.util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDatabaseDAO implements UserDAO{
    @Override
    public User login(String username, String password) {
        String sql = "SELECT * FROM user WHERE user_name = ? AND password=?";
        User user = null;
        try(Connection conn = DatabaseUtil.getInstance().getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            try (ResultSet rs = pstmt.executeQuery()){
                if(rs.next()){
                    int id = rs.getInt("user_id");
                    String userName = rs.getString("user_name");
                    String userPassword = rs.getString("password");
                    user = new User(id, userName, userPassword);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return user;
    }


    @Override
//    public User register(User user) {
//        String sql = "INSERT INTO user (user_name, password) VALUES (?, ?)";
//        try (Connection conn = DatabaseUtil.getInstance().getConnection();
//             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
//            pstmt.setString(1, user.getUsername());
//            pstmt.setString(2, user.getPassword());
//            pstmt.executeUpdate();
//            ResultSet rs = pstmt.getGeneratedKeys();
//            if(rs.next()){
//                int generatedId= rs.getInt(1);
//                user.setId(generatedId);
//            }
//        } catch(SQLException e){
//            e.printStackTrace();
//            return null;
//        }
//        return user;
//    }
    public User register(User user) {
        String sql = "INSERT INTO user (user_name, password) VALUES (?, ?)";
        try (Connection conn = DatabaseUtil.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if(rs.next()){
                int generatedId= rs.getInt(1);
                user.setId(generatedId);
            }
        } catch(SQLException e){
            e.printStackTrace();
            return null;
        }
        return user;
    }

    @Override
    public User findByUsername(String username) {
        String sql = "SELECT * FROM user WHERE user_name = ?";
        User user = null;
        try(Connection conn = DatabaseUtil.getInstance().getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                String usernme = rs.getString("user_name");
                String password = rs.getString("password");
                user = new User(usernme, password);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public List<User> getAllUsers(){
        String sql = "SELECT * FROM user";
        List<User> userList = new ArrayList<>();
        try(Connection conn = DatabaseUtil.getInstance().getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()){
            while(rs.next()){
                User user = rowToUser(rs);
                userList.add(user);
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return userList;
    }

//    @Override
//    public User findByUsername(String username){
//        String sql = "SELECT * FROM user WHERE user_name = ?";
//        User user = null;
//        try(Connection conn = DatabaseUtil.getInstance().getConnection();
//        PreparedStatement pstmt = conn.prepareStatement(sql)){
//            pstmt.setString(1, username);
//            ResultSet rs = pstmt.executeQuery();
//            if(rs.next()){
//                String usernme = rs.getString("user_name");
//                String password = rs.getString("password");
//                user = new User(usernme, password);
//            }
//        } catch(SQLException e) {
//            e.printStackTrace();
//        }
//        return user;
//    }
//
//    public List<User> getAllUsers(){
//        String sql = "SELECT * FROM user";
//        List<User> userList = new ArrayList<>();
//        try(Connection conn = DatabaseUtil.getInstance().getConnection();
//        PreparedStatement pstmt = conn.prepareStatement(sql);
//        ResultSet rs = pstmt.executeQuery()){
//            while(rs.next()){
//                User user = rowToUser(rs);
//                userList.add(user);
//            }
//        } catch(SQLException e){
//            e.printStackTrace();
//        }
//        return userList;
//    }

    public User rowToUser(ResultSet rs) throws SQLException{
        int id = rs.getInt("id");
        String username = rs.getString("user_name");
        String password = rs.getString("password");
        User user = new User(id, username, password);
        return user;
    }


}
