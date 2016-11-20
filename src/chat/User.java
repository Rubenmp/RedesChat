package chat;

import java.io.*;
import java.net.*;
import java.util.*;

// This class shows differente messages of the conversation, and receive
// this information from a server
public class User{
    private static int idStaticUser = 0;
    private Writer writer = new Writer();
    private Printer printer = new Printer();
    private int idUser;
    private String name;
    private ArrayList<Integer> conversations = new ArrayList<Integer>();
    // Each int in conversations is an idConversation

    public User(String p_name){
        name = p_name;
        idUser = idStaticUser;
        idStaticUser = idStaticUser + 1;
    }

}
