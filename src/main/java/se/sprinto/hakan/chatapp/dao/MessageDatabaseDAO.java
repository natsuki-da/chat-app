package se.sprinto.hakan.chatapp.dao;

import se.sprinto.hakan.chatapp.model.Message;

import java.util.List;

public class MessageDatabaseDAO implements MessageDAO{
    @Override
    public void saveMessage(Message message) {

    }

    @Override
    public List<Message> getMessagesByUserId(int userId) {
        return List.of();
    }
}
