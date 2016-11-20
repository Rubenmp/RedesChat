package chat;

import java.io.*;
import java.net.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;


public class Message{
    protected static final SimpleDateFormat dateFormat
        = new SimpleDateFormat ("dd/MM/yyyy 'at' hh:mm:ss");
    private static Pattern WHITESPACE_PATTERN 
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
            idConversation = scan.nextInt();
            idUser         = scan.nextInt();
            idMessage      = scan.nextInt();
                                System.out.print(idConversation+ " "+ idUser+" "+idMessage+"\n");

            scan.useDelimiter(Pattern.compile("-"));
            String logicalLine = scan.next();
            try {
               date = dateFormat.parse(logicalLine); 
                          System.out.println(date.toString());

            }catch (ParseException e) { 
               System.err.println("Unparseable " + logicalLine + "using " + dateFormat); 
            }         
            
            scan.useDelimiter(WHITESPACE_PATTERN);
            String separator = scan.next();
            text = scan.nextLine().substring(1);
            System.out.println(text);
        }
    }
    
    
    protected void exportMessage(PrintWriter writer){
        writer.print(Integer.toString(idConversation) + " ");
        writer.print(Integer.toString(idUser) + " ");
        writer.print(Integer.toString(idMessage) + " ");
        writer.print(getDateString() + " - ");
        writer.println(text);        
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
