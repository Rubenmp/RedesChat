package chat;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Writer {
    private int idConversation, idUser;
    private String host       = Config.getHost();	// Nombre del host donde se ejecuta el servidor:
    protected static int port = Config.getWriterPort(); 							// Puerto en el que espera el servidor:
    Socket socketService;
    PrintWriter outputStream;


        
    public Writer(int p_idConversation, int p_idUser){
        idConversation = p_idConversation;
        idUser = p_idUser;
    }
    


    public Writer(int p_idUser){
        idConversation = 2222;
        idUser = p_idUser;
    }


    public void execute(){
        String writingBuffer;
        Message message;
        Scanner input = new Scanner(System.in);

        System.out.print("Introduce your username: ");
        String username = input.next();

        while (input.hasNext()){
            writingBuffer = input.nextLine();
            if (writingBuffer.length() != 0){
                writingBuffer = username + ":\t" + writingBuffer;

                if (writingBuffer.toLowerCase() != "exit"){
                    message = new Message(idConversation, idUser, writingBuffer);
                    write(message);
                }
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
