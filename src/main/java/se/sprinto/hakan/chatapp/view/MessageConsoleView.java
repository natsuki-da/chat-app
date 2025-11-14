package se.sprinto.hakan.chatapp.view;

import se.sprinto.hakan.chatapp.dao.MessageDAO;
import se.sprinto.hakan.chatapp.dao.MessageDatabaseDAO;
import se.sprinto.hakan.chatapp.model.Message;

import java.util.Scanner;

public class MessageConsoleView {
    public void createMessage(){
        Scanner scanner = new Scanner(System.in);
        MessageDAO messageDAO = new MessageDatabaseDAO();

        System.out.println("Enter your message here: ");
        String text = scanner.nextLine();

        System.out.println("SEND");
        Message message = new Message(text);
        messageDAO.saveMessage(message);
    }
}
