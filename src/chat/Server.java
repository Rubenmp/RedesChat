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

    ServerSocket serverSocketW, serverSocketP, serverSocketP2;
    Socket socketW, socketP;
    ServerProcessor processor;
    String text;

    // Pass users to processor
    public void execute(){
        try {
            serverSocketW  = new ServerSocket(Config.getWriterPort()); 			// Abrimos el socket en modo pasivo

            serverSocketP = new ServerSocket(8990); 			// Abrimos el socket en modo pasivo
            serverSocketP2 = new ServerSocket(8991); 			// Abrimos el socket en modo pasivo

            do {

                socketW  = serverSocketW.accept();   // Aceptamos una nueva conexión
                inputStream    = new BufferedReader (new InputStreamReader(socketW.getInputStream()));
                text = inputStream.readLine();
                inputStream.close();

                System.out.print("Server receives: ");
                System.out.println(text); System.out.flush();

                socketP = serverSocketP2.accept();   // Aceptamos una nueva conexión
                outputStream   = new PrintWriter(socketP.getOutputStream(), true);
                outputStream.println(text);
                outputStream.close();

                socketP = serverSocketP.accept();
                outputStream   = new PrintWriter(socketP.getOutputStream(), true);
                outputStream.println(text);
                outputStream.close();
            } while (true);

        } catch (IOException e) {
                System.err.println("Error al escuchar en el puerto "+port);
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
