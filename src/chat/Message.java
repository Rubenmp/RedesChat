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
        = new SimpleDateFormat ("dd/MM/yyyy 'at' hh:mm:ss");
    private static final Pattern WHITESPACE_PATTERN 
        = Pattern.compile("\\p{javaWhitespace}+");
    private static int idStaticMessage = 0;
    private int idConversation;
    private int idUser;
    private int idMessage;
    private Date date;
    private String text;


    protected Message(){}
    
    public Message(int p_idConversation, int p_idUser, String p_text) {
    	idConversation  = p_idConversation;
        idUser          = p_idUser;
        idMessage       = idStaticMessage;
        date            = new Date();
        idStaticMessage = idStaticMessage + 1;
        text            = p_text;
    }

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
            System.out.println(text);
        }
    }
    
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
    
    
    protected void exportMessage(PrintWriter writer){
        writer.print(Integer.toString(idConversation) + " ");
        writer.print(Integer.toString(idUser) + " ");
        writer.print(Integer.toString(idMessage) + " ");
        writer.print(getDateString() + " - ");
        writer.println(text);        
    }
    
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
    
    
    public int getIdConversation(){
        return idConversation;
    }

    public int getIdUser(){
      return idUser;
    }

    public int getIdMessage(){
      return idMessage;
    }
    
    public Date getDate(){
        return date;
    }
    
    public String getDateString(){
        return dateFormat.format(date);
    }
    
    public String getFormat(){
        return "<idConversation> <idUser> <idMessage> <date:dateFormat> - <Text>";
    }
}
