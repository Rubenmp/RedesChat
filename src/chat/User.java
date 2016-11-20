package chat;

import java.io.*;
import java.net.*;
import java.util.*;

// This class shows differente messages of the conversation, and receive
// this information from a server
public class User{
    protected static final String extension = ".usr";
    private Writer writer = new Writer();
    private Printer printer = new Printer();
    private int idUser;
    private String name;
    private String password;
    private TreeSet<Integer> conversations = new TreeSet<Integer>();
    // Each int in conversations is an idConversation

    public User(String p_name){
        name = p_name;
        password = new String();
        idUser = Config.getIdStaticUser();
        Config.newUser();
    }
    
    public User(String p_name, String p_password){
        name = p_name;
        password = p_password;
        idUser = Config.getIdStaticUser();
        Config.newUser();
    }
    
    public void saveUser(){
    
    }
        
        

    public void writeMessage(Message message){
        writer.write(message.toString());       
    }
    
}
