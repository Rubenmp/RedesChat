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
        // Sockets
        ServerSocket serverWriter, serverPrinter;
        Socket socketWriter, socketPrinter;
        String text;
        
        try{
            serverWriter  = new ServerSocket(Writer.port); // Abrimos el socket en modo pasivo
            serverPrinter = new ServerSocket(Printer.port);
            
            while (true){
                try {
                    socketWriter = serverWriter.accept();   // Aceptamos una nueva conexión
                    inputStream  = new BufferedReader (new InputStreamReader(socketWriter.getInputStream()));
                    text = inputStream.readLine();

                    socketWriter.close();
                    inputStream.close();

                    // We only need an inputStream, the output is made with users PrintWriters
                    sendMessage( text );
                     
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
        }*/       
        
        ///////////////////////////////////77//////////
        
        Socket socketService;
        PrintWriter outputStream;

        try {
            socketService = new Socket (host, Printer.port);
            outputStream = new PrintWriter(socketService.getOutputStream(), true);

            outputStream.println(message); // Enviamos el array
            outputStream.flush();
            
            socketService.close();
            outputStream.close();

        }catch (UnknownHostException e){
            System.err.println("Error: Host not recognized.");
        }catch (IOException e){
            System.err.println("Error: I/O socket.");
        }
    }

}
