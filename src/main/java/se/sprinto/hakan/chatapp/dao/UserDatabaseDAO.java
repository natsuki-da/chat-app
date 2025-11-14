package se.sprinto.hakan.chatapp.dao;

import se.sprinto.hakan.chatapp.model.User;
import se.sprinto.hakan.chatapp.util.DatabaseUtil;

import java.sql.*;

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
                    int userId = rs.getInt("id");
                    String userName = rs.getString("user_name");
                    String userPassword = rs.getString("password");
                    user = new User(userId, userName, userPassword);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return user;
    }


    @Override
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

}
