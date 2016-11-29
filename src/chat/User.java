package chat;

import static chat.Conversation.getConversationFile;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

// This class shows differente messages of the conversation, and receive
// this information from a server
public class User{
    protected static final String extension = ".usr";
    private int idUser;
    private int tab = -1;   
    private String name;
    private String password;
    
    private Writer  writer;
    private Printer printer;
    private Integer conversation;
    // Each int in conversations is an idConversation

    
    private User(){}
    public User(int index){
        if (Config.validUser(index)){
            try {
                Scanner scan = new Scanner(new File(Config.getFileUser(index)));
                Writer writer;
                Printer printer;
                int group;
                
                idUser   = Integer.parseInt(scan.nextLine());
                tab      = 0;
                name     = scan.nextLine();
                password = scan.nextLine();
                conversation = scan.nextInt();

                /*while (scan.hasNext()){
                    group = scan.nextInt();
                    
                    writer = new Writer(group, idUser);
                    // printer = new Printer(group, idUser);
                    conversations.add(new Integer(group));
                    //writers.add(writer);
                    // printer.add(printer);
                }*/
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            printer = new Printer(idUser);
        }
        // Need verification of integrity
    }
    
    public User(String p_name){
        name = p_name;
        password = new String();
        idUser = Config.getIdStaticUser();
        Config.newUser();
    }
    
    public User(String p_name, String p_password){
        name = p_name;
        password = p_password;
        idUser = Config.newUser();
    }
    
    
    public void execute(){
    
     
    
    }
    
    
    
    public int getIdUser(){
        return idUser;
    }
    
    public int getTab(){
        return tab;
    }
    
    public String getName(){
        return name;
    }
    
    protected int getPasswordLength(){
        return password.length();
    }
    
    public boolean inConversation(int index){
        return true;
        //return (conversations.contains(index));
    }
    
    public Writer getWriters(){
        return writer;
    }
    public Printer getPrinter(){
        return printer;
    }

    public Integer getConversation(){
        return conversation;
    }
    
    
    
    public static User getUser(int index){
        User user = new User();
        if (Config.validUser(index))
            return User.getUser(index);      
        else
            user.idUser = -1;
        
        return user;
    }
    
    public void saveUser(){
        PrintWriter writer = null;
        try {
            String file = Config.getFileUser(idUser);
            writer = new PrintWriter(file, Config.getEncoding());
            
            writer.println(idUser);
            writer.println(name);
            writer.println(password);
            //for (Integer i:conversations)
            //    writer.print(Integer.toString(i) + " ");
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            writer.close();
        } 
    }
    
    
        
    public void writeMessage(String text){
        Message message = new Message(conversation, idUser, text);
        writer.write(message);       
    }
    
}
