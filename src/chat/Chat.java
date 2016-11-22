package chat;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


// Servidor del chat
// Cada chat será una hebra, lo que pasa a continuación te sorprenderá
public class Chat{
    // I/O
    //private ArrayList<PrintWriter> outputStream   = new ArrayList<PrintWriter>();
    
    private Conversation conversation = new Conversation();
    private TreeSet<User> admins      = new TreeSet<User>();
    private TreeSet<User> otherUsers  = new TreeSet<User>();    
    
    protected int newMessages;
    
    public Chat(){
        newMessages = 0; 
    }
    
    public void addUser(User user){
        otherUsers.add(user);
    }
    public void addAdmin(User user){
        admins.add(user);
    }
    public int removeUser(User user){
        int ret = -1;
        if (admins.contains(user) && admins.size() > 1){
            admins.remove(user);
            ret = 0;
        }
        else if (otherUsers.contains(user)){
            otherUsers.remove(user);
            ret = 0;
        }
        
        return ret;
    }
    
    public int giveAdministration(User user){
        int ret = -1;
        if (otherUsers.contains(user)){
            admins.add(user);
            otherUsers.remove(user);
            ret = 0;
        }
        
        return ret;
    }
}
