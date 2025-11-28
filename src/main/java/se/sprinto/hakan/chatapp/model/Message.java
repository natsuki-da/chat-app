package se.sprinto.hakan.chatapp.model;

import java.time.LocalDateTime;

public class Message {
    private int id;
    //använder endast userId här, eftersom hela Usern inte behövs
    private int userId;
    private String text;
    private LocalDateTime timestamp;

    //必要ない
    public Message(int userId, String text, LocalDateTime timestamp) {
        this.userId = userId;
        this.text = text;
        this.timestamp = timestamp;
    }

    public Message(String text){
        this.text = text;
    }

    public Message(int userId, String text){
        this.userId = userId;
        this.text = text;
    }

    public Message(int id, int userId, String text, LocalDateTime timestamp) {
        this.id = id;
        this.userId = userId;
        this.text = text;
        this.timestamp = timestamp;
    }

    public int getUserId() {
        return userId;
    }

    public String getText() {
        return text;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setId(int id){
        this.id = id;
    }
}

