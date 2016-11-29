package chat;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Writer {
    private int idConversation, idUser;
    private String host       = "localhost";	// Nombre del host donde se ejecuta el servidor:
    protected static int port = Config.getWriterPort(); 							// Puerto en el que espera el servidor:
    Socket socketService;
    PrintWriter outputStream;


        /*
    public Writer(int p_idConversation, int p_idUser){ 
        idConversation = p_idConversation;
        idUser = p_idUser;
    }
    */
    
    
    public Writer(int p_idUser){
        idConversation = 2222;
        idUser = p_idUser;
    }
    
    
    public void execute(){
        String writingBuffer;
        Message message;
        Scanner input = new Scanner(System.in);

        while (input.hasNext()){
            writingBuffer = input.nextLine();
            if (writingBuffer.toLowerCase() != "exit"){
                message = new Message(idConversation, idUser, writingBuffer);
                write(message);
            }
        }

        input.close();
    }

    public void write(Message message){
        String writingBuffer = message.toString();
         try {
            socketService = new Socket (host, Config.getWriterPort());
            outputStream = new PrintWriter(socketService.getOutputStream(), true);
                
            outputStream.println(writingBuffer); // Enviamos el array
            outputStream.flush();
            
            socketService.close();
            outputStream.close();
            
        } catch (IOException ex) {
            Logger.getLogger(Writer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
