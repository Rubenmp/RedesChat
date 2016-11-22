package chat;

import static chat.Writer.port;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Server {
    protected static ArrayList<User> users = new ArrayList<User>();
    private String host       = "localhost";	// Nombre del host donde se ejecuta el servidor:
    BufferedReader inputStream;
    PrintWriter    outputStream;
    /*protected static void showUser(int index){
        if (Config.validUser(index)){
            System.out.println(users.get(index).getIdUser() + " ");
            System.out.println(users.get(index).getName() + " ");
            for (int i=0; i<users.get(index).getPasswordLength(); ++i)
                System.out.println("*");
            for (Integer it:users.get(index).getConversations())
                System.out.println(" " + Integer.toString(it));
        }
    }*/
    
    
    public void execute(){
        ServerSocket serverWriter, serverPrinter;
        Socket socketWriter, socketPrinter;
        String text;
        
        
        try{
            serverWriter  = new ServerSocket(Config.getWriterPort()); // Abrimos el socket en modo pasivo
            serverPrinter = new ServerSocket(Config.getPrinterPort());
                        
            while (true){
                try {
                    socketWriter  = serverWriter.accept();   // Aceptamos una nueva conexión
                    socketPrinter = serverPrinter.accept();   // Aceptamos una nueva conexión
                    outputStream = new PrintWriter(socketPrinter.getOutputStream(), true);
                    
                    
                    inputStream  = new BufferedReader (new InputStreamReader(socketWriter.getInputStream()));
                    text = inputStream.readLine();
                    System.out.println(text);System.out.flush();

                    socketWriter.close();
                    inputStream.close();

                    // We only need an inputStream, the output is made with users PrintWriters
                    sendMessage( text );
                    outputStream.println(text); // Enviamos el array
                    outputStream.flush();
                    
                    
                } catch (IOException e) {
                    System.err.println("Error: writer port.");
                }
            }            
            
        } catch (IOException e) {
            System.err.println("Error: create server ports.");
        }
              
    }
    
    
    // Seng message to all chat's interfaces of users
    public void sendMessage(String message){
        //int nConversation = message.getIdConversation();
        //String file = Config.getFileConversation(nConversation);
        /*
        try {
            PrintWriter printer = new PrintWriter(file, Config.getEncoding());
            message.exportMessage(printer);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
        }    
        
        ///////////////////////////////////77//////////
        
        Socket socketService;

        try {
            
            outputStream.println(message); // Enviamos el array
            outputStream.flush();
            
    System.out.println("bbbb");System.out.flush();

            outputStream.close();

        }catch (UnknownHostException e){
            System.err.println("Error: Host not recognized.");
        }catch (IOException e){
            System.err.println("Error: I/O socket.");
        }*/
    }

}
