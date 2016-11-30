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

    // Limits
    private static final int maxGroupSize = 100;

    private static int idStaticConversation;
    private static int idStaticUser;

    private static final int writerPort  = 8989;
    private static final int printerPort = 8990;
    private static String host;
    
    // Encryption
    private static final String ALGORITHM = "AES";
    private static final String KEY = "1Hbfh667adfDEJ78";    

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

    public static void loadUsers(){
        User user;

        for (int i=0; i<idStaticUser; ++i){
            user = new User(i);
            Server.users.add(user);
        }
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

    public static int newConversation(){
        int ret = idStaticConversation;
        ++idStaticConversation;
        return ret;
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

    public static int newUser(){
        int ret = idStaticUser;
        ++idStaticUser;
        return ret;
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

    public static String getHost(){
        return host;
    }

    public static void setHost(String p_host){
        host = p_host;
    }

    public static boolean validUser(int index){
        return (index >= 0 && index < idStaticUser);
    }

    public static boolean validConversation(int index){
        return (index >= 0 && index < idStaticConversation);
    }
    
    
   
    
    public static String encrypt(String value) throws Exception{
        Key key = generateKey();
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte [] encryptedByteValue = cipher.doFinal(value.getBytes("utf-8"));
        String encryptedValue64 = new BASE64Encoder().encode(encryptedByteValue);
        return encryptedValue64;
               
    }
    
    public static String decrypt(String value) throws Exception{
        Key key = generateKey();
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte [] decryptedValue64 = new BASE64Decoder().decodeBuffer(value);
        byte [] decryptedByteValue = cipher.doFinal(decryptedValue64);
        String decryptedValue = new String(decryptedByteValue,"utf-8");
        return decryptedValue;
                
    }
    
    private static Key generateKey() throws Exception {
        Key key = new SecretKeySpec(KEY.getBytes(), ALGORITHM);
        return key;
    }
}
