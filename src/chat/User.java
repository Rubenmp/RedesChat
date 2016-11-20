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
    
    private ArrayList<Writer>  writers       = new ArrayList<Writer>();
    private ArrayList<Printer> printers      = new ArrayList<Printer>();
    private ArrayList<Integer> conversations = new ArrayList<Integer>();
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
                tab      = -1;
                name     = scan.nextLine();
                password = scan.nextLine();
               
                while (scan.hasNext()){
                    group = scan.nextInt();
                    writer  = new Writer(group, idUser);
                    printer = new Printer(group, idUser);
                    conversations.add(new Integer(group));
                    writers.add(writer);
                    printers.add(printer);
                }
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
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
        idUser = Config.getIdStaticUser();
        Config.newUser();
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
        return (conversations.contains(index));
    }
    
    public ArrayList<Writer> getWriters(){
        return writers;
    }
    public ArrayList<Printer> getPrinters(){
        return printers;
    }

    public ArrayList<Integer> getConversations(){
        return conversations;
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
            for (Integer i:conversations)
                writer.print(Integer.toString(i) + " ");
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            writer.close();
        } 
    }
    
    
        
    public void writeMessage(String text){
        Message message = new Message(conversations.get(tab), idUser, text);
        writers.get(tab).write(message);       
    }
    
}
