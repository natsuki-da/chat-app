package se.sprinto.hakan.chatapp.dao;

import se.sprinto.hakan.chatapp.model.Message;
import se.sprinto.hakan.chatapp.util.DatabaseUtil;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class MessageDatabaseDAO implements MessageDAO{

    @Override
    public void saveMessage(Message message) {
        String sql = "INSERT INTO message (text, user_id) VALUES (?, ?)";
        try(Connection conn = DatabaseUtil.getInstance().getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            pstmt.setString(1, message.getText());
            pstmt.setInt(2, message.getUserId());
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if(rs.next()){
                int generatedId = rs.getInt(1);
                message.setId(generatedId);
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Message> getMessagesByUserId(int userId) {
        String sql = "SELECT * FROM message WHERE user_id = ?";
        List<Message> messages = new ArrayList<>();

        try (Connection conn = DatabaseUtil.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
               Message message = rowToMessage(rs);
               messages.add(message);
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return messages;
    }

    public Message rowToMessage(ResultSet rs) throws SQLException {
        int messageId = rs.getInt("message_id");
        int userId = rs.getInt("user_id");
        String text = rs.getString("text");
        Timestamp timestamp = rs.getTimestamp("date_time");
        LocalDateTime dateTime = timestamp != null ? timestamp.toLocalDateTime() : null;
        return new Message(messageId, userId, text, dateTime);
    }
}
