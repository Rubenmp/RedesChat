package chat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;



public class Conversation {
    protected static final String extension = ".chat";
    private int idConversation;
    
    // Members functions
    private ArrayList<Message> messages = new ArrayList<>();
    

    public Conversation(){
        idConversation = Config.getIdStaticConversation();
        Config.newConversation();  
    }
    
    public Message get(int index){
        if (index > 0 && index < messages.size())
            return messages.get(index);
        return null;
    }
    
    public String getConversationFile(){
        String file = new String();
        file += Config.getConversations() + "/";
        file += Integer.toString(idConversation);
        file += extension;
        
        return file;
    }
    
    public static String getConversationFile(int index){
        String file = new String();
        if (index < Config.getIdStaticConversation()){
            file += Config.getConversations() + "/";
            file += Integer.toString(index);
            file += extension;
        }
        
        return file;
    }
    
    public void add(Message p_message){
        if (p_message.getIdConversation() == idConversation)
            messages.add(p_message);
        else{
            String error = "Error: adding message to a conversation with different idConversation";    
            System.err.println(error);
        }
    }
    
    public int importConversation(int idConversation) throws FileNotFoundException{
        messages = new ArrayList<>();
        
        if (idConversation >= 0 && idConversation < Config.getIdStaticConversation()){
            Scanner scan = new Scanner(new File(getConversationFile(idConversation)));
            Message message;
            
            while (scan.hasNext()){ 
                message = new Message();
                message.importMessage(scan);
                messages.add(message);
            }
            return 0;
        }
        
        return 1;
    }
    
    public void exportConversation(){
        String file = getConversationFile();
        
        try{
            PrintWriter writer = new PrintWriter(file, Config.getEncoding());
            // Format: <idConversation> <idUser> <idMessage> <date> <Text>            
            for (Message it:messages)
                it.exportMessage(writer);
                
            writer.close();
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            System.err.println("Error: export " + idConversation + " conversation failed.");
        } 
    }
    
}
