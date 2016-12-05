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

      /**
      * User constructor
      * @param int index
      */
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

                // Possible ampliation for groups
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
    }

    /**
    * User constructor by name
    * @param String p_name
    */
    public User(String p_name){
        name = p_name;
        password = new String();
        idUser = Config.getIdStaticUser();
        Config.newUser();
    }

    /**
    * User constructor providing name and password
    * @param String p_name
    * @param String p_password
    */
    public User(String p_name, String p_password){
        name = p_name;
        password = p_password;
        idUser = Config.newUser();
    }


    /**
    * It provides user id
    * @return int idUser
    */
    public int getIdUser(){
        return idUser;
    }

    /**
    * It provides specific identifier of conversation which is the actual conversation
    * @return int tab
    */
    public int getTab(){ // Ampliation for different chats
        return tab;
    }

    /**
    * It provides user's name
    * @return String name
    */
    public String getName(){
        return name;
    }

    /**
    * It provides password length
    * @return int
    */
    protected int getPasswordLength(){
        return password.length();
    }

    /**
    * It says if an user is in actual conversation
    * @param int index
    * @return boolean
    */
    public boolean inConversation(int index){ // Ampliation for groups
        return true;
        //return (conversations.contains(index));
    }

    /**
    * It returns writer of user
    * @return Writer
    */
    public Writer getWriters(){
        return writer;
    }

    /**
    * It return printer of user
    * @return Printer
    */
    public Printer getPrinter(){
        return printer;
    }

    /**
    * It provides conversation id
    * @return Integer
    */
    public Integer getConversation(){
        return conversation;
    }

    /**
    * Static function
    * @param int index
    * @return User
    * @see validUser()
    */
    public static User getUser(int index){
        User user = new User();
        if (Config.validUser(index))
            return User.getUser(index);
        else
            user.idUser = -1;

        return user;
    }

    /**
    * It saves user in configuration folders
    * @return void
    */
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

    /**
    * It write a message to actual conversation
    * @param String text
    * @return void
    */
    public void writeMessage(String text){
        Message message = new Message(conversation, idUser, text);
        writer.write(message);
    }

}
