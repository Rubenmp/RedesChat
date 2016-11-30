package chat;

import java.io.*;
import java.net.*;


// This class shows different messages of the conversation, and receive
// this information from a server
public class Printer {
    private int idConversation, idUser;
    //private String host       = "localhost";	// Nombre del host donde se ejecuta el servidor:
    private String host       = Config.getHost();	// Nombre del host donde se ejecuta el servidor:
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
            ServerSocket serverSocketP = new ServerSocket(Config.getPrinterPort()); 			// Abrimos el socket en modo pasivo

            do{
                Socket socketP = serverSocketP.accept();   // Aceptamos una nueva conexión               
                
                inputStream   = new BufferedReader(new InputStreamReader(socketP.getInputStream()));
                readingBuffer = inputStream.readLine();
                inputStream.close();
                socketP.close();
                printMessage(readingBuffer);

                try {
                  Thread.sleep(2500);                 //1000 milliseconds is one second.
                } catch(InterruptedException ex) {
                  Thread.currentThread().interrupt();
                }
            } while (readingBuffer != null);

        }catch (UnknownHostException e){
                System.err.println("Error: Host name not found.");
        }catch (IOException e){
                System.err.println("Error: I/O with socket.");
        }
    }

    public void printMessage(String readingBuffer){
        Message m = Message.toMessage(readingBuffer);
        System.out.println(m.getDateString() + " from " + m.getText());
        System.out.flush();
    }

}
