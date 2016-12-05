package chat;

import static chat.Conversation.getConversationFile;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


public class Config {
    private static String folder = "/home/rubenmp/Chat/.chatConfig/";
    private static String conversations = new String();
    private static String users = new String();
    private static String settings = new String();
    private static final String encoding = "UTF-8";

    // Files
    private static String initialConfig = new String();

    private static int idStaticConversation;
    private static int idStaticUser;

    private static final int writerPort  = 8989;
    private static final int printerPort = 8990;
    private static String host;

    /**
    * This function initialized configuration of chat
    * @param int value 1 if folders previously created, 0 other case
    *
    */
    public Config(int value){
        conversations += folder + "conversations/";
        users    += folder + "users/";
        settings += folder +"settings/";
        createFolders();

        // Configuration files
        initialConfig += settings + "init.conf";

        if (value != 0)
            initConfig();
        else{
            idStaticConversation = 0;
            idStaticUser         = 0;
            saveConfig();
        }

        loadUsers();
    }

    /**
    * This function creates folders for the administration of chat
    * @return void
    */
    private static void createFolders(){
        File dir = new File(folder);
        dir.mkdir();
        dir = new File(conversations);
        dir.mkdir();
        dir = new File(users);
        dir.mkdir();
        dir = new File(settings);
        dir.mkdir();
    }

    /**
    * This function provides static variables to program
    * @return void
    */
    private static void initConfig(){
        Scanner scan;
        try {
            scan = new Scanner(new File(initialConfig));
            idStaticConversation = scan.nextInt();
            idStaticUser         = scan.nextInt();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
    * This function save the configuration of chat (static variables, etc)
    * @return void
    */
    private static void saveConfig(){
        try {
            PrintWriter writer = new PrintWriter(initialConfig, Config.getEncoding());
            writer.println(idStaticConversation + " " + idStaticUser);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
    * This function save users in server
    * @return void
    */
    public static void loadUsers(){
        User user;

        for (int i=0; i<idStaticUser; ++i){
            user = new User(i);
            Server.users.add(user);
        }
    }

    /**
    * It return main folder
    * @return String main folder
    */
    public static String getFolder(){
        return folder;
    }

    /**
    * This function returns conversations folder
    * @return String convesations
    */
    public static String getConversations(){
        return conversations;
    }

    /**
    * This function returns users folder
    * @return void
    */
    public static String getUsers(){
        return users;
    }

    /**
    * This funcion return settings folder
    * @return String settings folder
    */
    public static String getSettings(){
        return settings;
    }

    /**
    * This function return encoding used in chat
    * @return String encoding
    */
    public static String getEncoding(){
        return encoding;
    }


    /**
    * It provides the amount of registered conversations
    * @return int idStaticConversation
    */
    public static int getIdStaticConversation(){
        return idStaticConversation;
    }

    /**
    * It creates a new conversation in chat
    * @return int identifier of conversation
    */
    public static int newConversation(){
        int ret = idStaticConversation;
        ++idStaticConversation;
        return ret;
    }

    /**
    * It provides file of conversation in the parameter
    * @param String index
    * @return String file
    */
    public static String getFileConversation(int index){
        String file = new String();
        if (index >= 0 && index < idStaticConversation)
            file += conversations + index + Conversation.extension;

        return file;
    }

    /**
    * It provides the amount of registered users in chat
    * @return int
    */
    public static int getIdStaticUser(){
        return idStaticUser;
    }

    /**
    * It creates a new user
    * @return int identifier of user
    */
    public static int newUser(){
        int ret = idStaticUser;
        ++idStaticUser;
        return ret;
    }

    /**
    * It provides file of user passing identifier
    * @param int index in 0...idStaticUser
    * @return String userFile
    */
    public static String getFileUser(int index){
        String file = new String();
        if (index >= 0 && index < idStaticUser)
            file += users + index + User.extension;

        return file;
    }


    /**
    * It provides writer port
    * @return int writerPort
    */
    public static int getWriterPort(){
        return writerPort;
    }

    /**
    * It provides printer port
    * @return int printerPort
    */
    public static int getPrinterPort(){
        return printerPort;
    }

    /**
    * It provides host of the server
    * @return String host
    */
    public static String getHost(){
        return host;
    }

    /**
    * It modifies host of server
    * @param String p_host
    * @return void
    */
    public static void setHost(String p_host){
        host = p_host;
    }

    /**
    * It says if an index of user is valid
    * @param in index
    * @return boolean
    */
    public static boolean validUser(int index){
        return (index >= 0 && index < idStaticUser);
    }

    /**
    * It says if an index of conversation is valid
    * @param int index
    * @return boolean 
    */
    public static boolean validConversation(int index){
        return (index >= 0 && index < idStaticConversation);
    }

}
