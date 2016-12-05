package chat;

import static chat.Conversation.getConversationFile;
import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;


public class Message{
    protected static final SimpleDateFormat dateFormat
        = new SimpleDateFormat ("dd/MM '@' hh:mm");
    private static final Pattern WHITESPACE_PATTERN
        = Pattern.compile("\\p{javaWhitespace}+");
    private static int idStaticMessage = 0;
    private int idConversation;
    private int idUser;
    private int idMessage;
    private Date date;
    private String text;


    protected Message(){}
      /**
      * Constructor of message
      * @param int p_idConversation
      * @param int p_idUser
      * @param String p_text
      */
    public Message(int p_idConversation, int p_idUser, String p_text) {
    	  idConversation  = p_idConversation;
        idUser          = p_idUser;
        idMessage       = idStaticMessage;
        date            = new Date();
        idStaticMessage = idStaticMessage + 1;
        text            = p_text;
    }

    /**
    * It creates a new message using Scanner
    * @param Scanner scan
    * @return void
    */
    protected void importMessage(Scanner scan){
        if (scan.hasNextLine()){
            idConversation = scan.nextInt(); // "not necesary"
            idUser         = scan.nextInt();
            idMessage      = scan.nextInt();

            scan.useDelimiter(Pattern.compile("-"));
            String logicalLine = scan.next();
            try {
               date = dateFormat.parse(logicalLine);
            }catch (ParseException e) {
               System.err.println("Unparseable " + logicalLine + "using " + dateFormat);
            }

            scan.useDelimiter(WHITESPACE_PATTERN);
            String separator = scan.next();
            text = scan.nextLine().substring(1); // Removing first " "
            //System.out.println(text);
        }
    }

    /**
    * It converts string to a message
    * @param String string
    * @return Mesasge
    * @see static function
    */
    protected static Message toMessage(String string){
        File fichAux = new File(".aux");
        Message message = null;
        PrintWriter writer;
        Scanner scan;

        try {
            writer = new PrintWriter(fichAux, Config.getEncoding());
            writer.println(string);
            writer.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Message.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Message.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            scan    = new Scanner(fichAux);
            message = new Message();
            message.importMessage(scan);
            fichAux.delete();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Message.class.getName()).log(Level.SEVERE, null, ex);
        }

        return message;
    }

    /**
    * It export message to a PrintWriter
    * @param PrintWriter writer
    * @return void
    */
    protected void exportMessage(PrintWriter writer){
        writer.print(Integer.toString(idConversation) + " ");
        writer.print(Integer.toString(idUser) + " ");
        writer.print(Integer.toString(idMessage) + " ");
        writer.print(getDateString() + " - ");
        writer.println(text);
    }

    /**
    * It converts message to string
    * @return String
    */
    @Override
    public String toString(){
        String message = new String();
        message += Integer.toString(idConversation) + " ";
        message += Integer.toString(idUser) + " ";
        message += Integer.toString(idMessage) + " ";
        message += getDateString() + " - ";
        message += text + "\n";

        return message;
    }

    /**
    * It provides identifier of conversation
    * @return int idConversation
    */
    public int getIdConversation(){
        return idConversation;
    }

    /**
    * It provides identifier of user
    * @return int idUser
    */
    public int getIdUser(){
      return idUser;
    }

    /**
    * It provides local identifier of message.
    * Total identifier is composed of idConversation,idUser and idMessage
    * @return int idMessage
    */
    public int getIdMessage(){
      return idMessage;
    }

    /**
    * It provides creation date of message
    * @return Date date
    */
    public Date getDate(){
        return date;
    }

    /**
    * It provides creation date of message in the message format
    * @return String
    */
    public String getDateString(){
        return dateFormat.format(date);
    }

    /**
    * It provides text of message
    * @return String text
    */
    public String getText(){
      return text;
    }

    /**
    * It provides format of message
    * @return String
    */
    public String getFormat(){
        return "<idConversation> <idUser> <idMessage> <date:dateFormat> - <Text>";
    }
}
