package chat;

import java.io.*;
import java.net.*;
import java.util.*;


// Servidor del chat
// Cada chat será una hebra, lo que pasa a continuación te sorprenderá
public class Server {
    private int portWriter  = 8989;
    private int portPrinter = 8990;		 // Puerto de escucha
    String text;

    // Sockets
    private ServerSocket serverWriter  = null;
    private ServerSocket serverPrinter = null;
    private Socket socketWriter        = null;
    private Socket socketPrinter       = null;

    // I/O
    private BufferedReader inputStream = null;
    private PrintWriter outputStream   = null;
    
    

    public Server(){
        File dir = new File(Conversation.folder);
        dir.mkdir();
    }

    public void execute(){
        try{
            serverWriter  = new ServerSocket(portWriter); 	// Abrimos el socket en modo pasivo
            serverPrinter = new ServerSocket(portPrinter);
        } catch (IOException e) {
            System.err.println("Error al crear puertos del servidor");
        }

        try{
            socketPrinter = serverPrinter.accept();
            outputStream = new PrintWriter(socketPrinter.getOutputStream(), true);
        } catch (IOException e) {
            System.err.println("Error al escuchar en el puerto de la pantalla");
        }

        while (true){
            try {
                socketWriter = serverWriter.accept();   // Aceptamos una nueva conexión
                inputStream  = new BufferedReader (new InputStreamReader(socketWriter.getInputStream()));
                text = inputStream.readLine();
                //System.out.println(text);				System.out.flush();

                socketWriter.close();
                inputStream.close();

                outputStream.println(text);
                outputStream.flush();

            } catch (IOException e) {
                System.err.println("Error al escuchar en el puerto del escritor");
            }
        }
        //socketPrinter.close();
        //outputStream.close();
    }

}
