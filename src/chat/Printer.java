package chat;

import java.io.*;
import java.net.*;


// This class shows different messages of the conversation, and receive
// this information from a server
public class Printer {
    private int idConversation, idUser;
<<<<<<< Updated upstream
    //private String host       = "localhost";	// Nombre del host donde se ejecuta el servidor:
    private String host       = Config.getHost();	// Nombre del host donde se ejecuta el servidor:

=======
    private String host       = "localhost";	// Nombre del host donde se ejecuta el servidor:
>>>>>>> Stashed changes
    protected static int port = Config.getPrinterPort(); // Puerto en el que espera el servidor

/*
        public Printer(int p_idConversation, int p_idUser){
        idConversation = p_idConversation;
        idUser = p_idUser;
    }
    */

    public Printer(int p_idUser){
        idConversation = 111111;
        idUser = p_idUser;
    }

    public void execute(){
        String readingBuffer;
        Socket socketService;
        BufferedReader inputStream;

        try {
            do{
                socketService = new Socket (host, port); // Creamos un socket que se conecte a "host" y "port":
                inputStream   = new BufferedReader(new InputStreamReader(socketService.getInputStream()));
                readingBuffer = inputStream.readLine();
                printMessage(readingBuffer);
                inputStream.close();
                socketService.close();

            } while (readingBuffer != null);

        }catch (UnknownHostException e){
                System.err.println("Error: Host name not found.");
        }catch (IOException e){
                System.err.println("Error: I/O with socket.");
        }
    }

    public void printMessage(String readingBuffer){
        System.out.println("Mensaje \t" + readingBuffer);
        System.out.flush();
    }

}
