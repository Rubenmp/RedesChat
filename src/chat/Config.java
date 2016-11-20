package chat;

import java.io.File;


public class Config {
    private static String folder = new String();
    private static String conversations = new String();
    private static String users = new String();
    private static String settings = new String();
    private static final String encoding = "UTF-8";
    
    private static int idStaticConversation = 0;
    private static int idStaticUser = 0;

    
    private static final int writerPort  = 8989; 
    private static final int printerPort = 8990;
    
    public Config(){
        folder = ".chat/";
        conversations += folder + "conversations/";
        users += folder + "users/";
        settings += folder +"settings/";
     
        File dir = new File(folder);
        dir.mkdir();
        dir = new File(conversations);
        dir.mkdir();
        dir = new File(users);
        dir.mkdir();
        dir = new File(settings);
        dir.mkdir();   
    }
    
    public void loadUsers(){
    
    }
    
    public static String getFolder(){
        return folder;
    }
    
    public static String getConversations(){
        return conversations;
    }
    
    public static String getUsers(){
        return users;
    }
    
    public static String getSettings(){
        return settings;
    }
    
    public static String getEncoding(){
        return encoding;
    }
    
    
    
    public static int getIdStaticConversation(){
        return idStaticConversation;
    }
    
    public static void newConversation(){
        ++idStaticConversation;
    }
    
    public static String getFileConversation(int index){
        String file = new String();
        if (index >= 0 && index < idStaticConversation)
            file += conversations + index + Conversation.extension;
        
        return file; 
    }
    
    public static int getIdStaticUser(){
        return idStaticUser;
    }
    
    public static void newUser(){
        ++idStaticUser;
    }
    
    public static String getFileUser(int index){
        String file = new String();
        if (index >= 0 && index < idStaticUser)
            file += users + index + User.extension;
        
        return file; 
    }
    
    
    
    public static int getWriterPort(){
        return writerPort;
    }
    
    public static int getPrinterPort(){
        return printerPort;
    }
       
}
