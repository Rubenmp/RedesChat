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
    private BufferedReader inputStream = null;
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
    
    
    
        
    public void run(){
        // Sockets
        ServerSocket serverWriter, serverPrinter;
        Socket socketWriter, socketPrinter;
        String text;
        
        try{
            serverWriter  = new ServerSocket(Writer.port); // Abrimos el socket en modo pasivo
            serverPrinter = new ServerSocket(Printer.port);
            
            while (true){
                try {
                    socketWriter = serverWriter.accept();   // Aceptamos una nueva conexión
                    inputStream  = new BufferedReader (new InputStreamReader(socketWriter.getInputStream()));
                    text = inputStream.readLine();

                    socketWriter.close();
                    inputStream.close();

                    // We only need an inputStream, the output is made with users PrintWriters
                    sendMessage( Message.toMessage(text) );
                     
                } catch (IOException e) {
                    System.err.println("Error: writer port.");
                }
            }            
            
        } catch (IOException e) {
            System.err.println("Error: create server ports.");
        }
              
    }
    
    
    // Seng message to all chat's interfaces of users
    public void sendMessage(Message message){
        int nConversation = message.getIdConversation();
        String file = Config.getFileConversation(nConversation);
        
        try {
            PrintWriter printer = new PrintWriter(file, Config.getEncoding());
            message.exportMessage(printer);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        for (User it:admins)
            it.getPrinters().get( it.getTab() ).printMessage( message.toString() );
        for (User it:otherUsers)
            it.getPrinters().get( it.getTab() ).printMessage( message.toString() );        
       
        ++newMessages;
    }

}
