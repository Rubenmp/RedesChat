package chat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.TreeSet;


// In ampliation for some conversations at the same time
public class Conversation {
    protected static final String extension = ".chat";
    private int idConversation;
    private ArrayList<Message> messages = new ArrayList<>();

    private Conversation conversation = new Conversation();
    private TreeSet<User> admins      = new TreeSet<User>();
    private TreeSet<User> otherUsers  = new TreeSet<User>();

    protected int newMessages = 0;


    public Conversation(){
        idConversation = Config.newConversation();
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
