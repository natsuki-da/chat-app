package se.sprinto.hakan.chatapp;

import se.sprinto.hakan.chatapp.dao.MessageDatabaseDAO;

public class ChatServerMain {
    public static void main(String[] args) {
        new ChatServer(5555).start();

//        TestDBConnection testDB = new TestDBConnection();
//        testDB.connectToDB();
//        MessageDatabaseDAO mdb = new MessageDatabaseDAO();
//        mdb.testDBConnection();
    }
}
