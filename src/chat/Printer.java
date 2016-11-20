package chat;

import java.io.*;
import java.net.*;

// This class shows different messages of the conversation, and receive
// this information from a server
public class Printer{
    private String host = "localhost";				// Nombre del host donde se ejecuta el servidor:
    private int port    = 8990; 							// Puerto en el que espera el servidor

    public Printer(){}

    public void execute(){
        String readingBuffer;
        Socket socketService;
        BufferedReader inputStream;

        try {
            socketService = new Socket (host, port); // Creamos un socket que se conecte a "host" y "port":
            inputStream   = new BufferedReader(new InputStreamReader(socketService.getInputStream()));

            readingBuffer = inputStream.readLine();
            while (readingBuffer != null){
                System.out.println("Mensaje \t" + readingBuffer);
                System.out.flush();

                //socketService.close();
                //socketService = new Socket (host, port); // Creamos un socket que se conecte a "host" y "port":
                //inputStreamm  = new BufferedReader(new InputStreamReader(socketService.getInputStream()));

                readingBuffer = inputStream.readLine();
            }

            socketService.close();
            inputStream.close();

        }catch (UnknownHostException e){
                System.err.println("Error: Nombre de host no encontrado.");
        }catch (IOException e){
                System.err.println("Error de entrada/salida al abrir el socket.");
        }
    }

}
