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
    PrintWriter    outputStream2, outputStream3;

    ServerSocket serverSocket, serverSocket2;
    Socket socket, socket2, socket3;
    ServerProcessor processor;
    String text;

    // Pass users to processor
    public void execute(){
        try {
            serverSocket  = new ServerSocket(Config.getWriterPort()); 			// Abrimos el socket en modo pasivo
            serverSocket2 = new ServerSocket(Config.getPrinterPort()); 			// Abrimos el socket en modo pasivo

            do {
              socket  = serverSocket.accept();   // Aceptamos una nueva conexión
              inputStream    = new BufferedReader (new InputStreamReader(socket.getInputStream()));
              text = inputStream.readLine();
              inputStream.close();

              System.out.println("leido");System.out.flush();

              socket2 = serverSocket2.accept();   // Aceptamos una nueva conexión
              outputStream2   = new PrintWriter(socket2.getOutputStream(), true);
              outputStream2.println(text);
              outputStream2.close();
              System.out.println("enviado1");System.out.flush();

              socket3 = serverSocket2.accept();   // Aceptamos una nueva conexión
              outputStream3   = new PrintWriter(socket3.getOutputStream(), true);
              outputStream3.println(text);
              outputStream3.close();
              System.out.println("enviado2");System.out.flush();
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




/*
socket  = serverSocket.accept();   // Aceptamos una nueva conexión

socket2 = serverSocket2.accept();   // Aceptamos una nueva conexión
socket3 = serverSocket2.accept();   // Aceptamos una nueva conexión

inputStream    = new BufferedReader (new InputStreamReader(socket.getInputStream()));
outputStream2   = new PrintWriter(socket2.getOutputStream(), true);
outputStream3   = new PrintWriter(socket3.getOutputStream(), true);

text = inputStream.readLine();
outputStream2.println(text);
outputStream3.println(text);

// Obtiene los flujos de escritura/lectura
inputStream.close();
outputStream2.close();
outputStream3.close();

*/
