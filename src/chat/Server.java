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

    /**
    * It execute server process
    * @return void
    */
    public void execute(){
        try {
            serverSocketW  = new ServerSocket(Config.getWriterPort());
            serverSocketP = new ServerSocket(8990);
            serverSocketP2 = new ServerSocket(8991);

            do {
                socketW  = serverSocketW.accept();
                inputStream    = new BufferedReader (new InputStreamReader(socketW.getInputStream()));
                text = inputStream.readLine();
                inputStream.close();

                socketP = serverSocketP2.accept();   
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



    // Send message to all chat's interfaces of users
    // In ampliation
    public void sendMessage(String message){
      // methods
    }

}
