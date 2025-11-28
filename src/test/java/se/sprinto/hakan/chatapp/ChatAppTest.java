package se.sprinto.hakan.chatapp;

import org.junit.jupiter.api.*;
import se.sprinto.hakan.chatapp.dao.MessageDatabaseDAO;
import se.sprinto.hakan.chatapp.dao.UserDatabaseDAO;
import se.sprinto.hakan.chatapp.model.Message;
import se.sprinto.hakan.chatapp.model.User;
import se.sprinto.hakan.chatapp.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ChatAppTest {
    UserDatabaseDAO userDatabaseDAO = new UserDatabaseDAO();
    MessageDatabaseDAO messageDatabaseDAO = new MessageDatabaseDAO();

    @BeforeAll
    static void createDBTable()throws SQLException {
        Connection conn = DatabaseUtil.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS user(
                    user_id INT PRIMARY KEY AUTO_INCREMENT,
                    user_name VARCHAR(20) NOT NULL UNIQUE,
                    password VARCHAR(20) NOT NULL
                );
                """
        );
        stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS message(
                                message_id INT PRIMARY KEY AUTO_INCREMENT,
                                text TEXT NOT NULL,
                                date_time DATETIME DEFAULT CURRENT_TIMESTAMP,
                                user_id INT NOT NULL,
                                FOREIGN KEY (user_id) REFERENCES user(user_id));
                """
        );
    }

    @DisplayName("Register a new user successfully")
    @Test
    void testRegisterUser(){
        //1. Arrange
        User sampleUser = new User("Natsuki", "n123");

        //2. Act
        User registeredUser = userDatabaseDAO.register(sampleUser);

        //3. Assert
        assertNotNull(registeredUser);
        //System.out.println(registeredUser.getId());
        assertTrue(registeredUser.getId() > 0);
    }

   @Test
   void createTwoMsgsUnderUser(){
       //1. Arrange
       User sampleUser = new User("Filip", "f321");
       User registeredUser = userDatabaseDAO.register(sampleUser);
       int userId = registeredUser.getId();
       Message msg1 = new Message(userId, "Hello");
       Message msg2 = new Message(userId, "How are you?");
       messageDatabaseDAO.saveMessage(msg1);
       messageDatabaseDAO.saveMessage(msg2);

       //2. Act
       List<Message> messageList = messageDatabaseDAO.getMessagesByUserId(userId);

       //3. Assert
       assertEquals(2, messageList.size());
   }

    @Test
    void getTwoMsgsFromUser(){
        //1. Arrange
        User sampleUser = new User("Tyler", "t321");
        User registeredUser = userDatabaseDAO.register(sampleUser);
        int userId = registeredUser.getId();
        Message msg1 = new Message(userId, "Tjena");
        Message msg2 = new Message(userId, "Hur är läget");
        Message msg3 = new Message(userId, "Jag mår bra!");
        messageDatabaseDAO.saveMessage(msg1);
        messageDatabaseDAO.saveMessage(msg2);
        messageDatabaseDAO.saveMessage(msg3);

        //2. Act
        User loggedInUser = userDatabaseDAO.login(registeredUser.getUsername(), registeredUser.getPassword());
        List<Message> messageList = loggedInUser.getMessages();

        //3. Assert
        assertEquals(3, messageList.size());
    }

}

