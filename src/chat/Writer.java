package chat;

import java.io.*;
import java.net.*;
import java.util.*;

public class Writer{
    private String host       = "localhost";	// Nombre del host donde se ejecuta el servidor:
    protected static int port = Config.getWriterPort(); 							// Puerto en el que espera el servidor:

    public Writer(){}

    public void execute(){
        String writingBuffer;
        Scanner input = new Scanner(System.in);

        while (input.hasNext()){
            writingBuffer = input.nextLine();
            if (writingBuffer.toLowerCase() != "exit")
                write(writingBuffer);
        }

        input.close();
    }

    public void write(String writingBuffer){
        Socket socketService;
        PrintWriter outputStream;

        try {
            socketService = new Socket (host, port);
            outputStream = new PrintWriter(socketService.getOutputStream(), true);

            outputStream.println(writingBuffer); // Enviamos el array
            outputStream.flush();

            socketService.close();
            outputStream.close();

        }catch (UnknownHostException e){
            System.err.println("Error: Nombre de host no encontrado.");
        }catch (IOException e){
            System.err.println("Error de entrada/salida al abrir el socket.");
        }
    }

}
