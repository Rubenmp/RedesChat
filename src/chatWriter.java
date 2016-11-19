

import java.io.*;
import java.net.*;
import java.util.*;

public class chatWriter{
  public static void main(String[] args) {
    String host = "localhost";				// Nombre del host donde se ejecuta el servidor:
    int port    = 8989; 							// Puerto en el que espera el servidor:

    String writingBuffer;

    try {
      Socket socketService = new Socket (host, port); // Creamos un socket para enviar los datos al servidor
      PrintWriter outputStream = new PrintWriter(socketService.getOutputStream(), true);

      Scanner input = new Scanner(System.in);

      //writingBuffer = "Al monte del volcán debes ir sin demora";
      writingBuffer = input.nextLine();



      //System.out.println(writingBuffer);
      while (input.hasNext()){
        writingBuffer = input.nextLine();
        outputStream.println(writingBuffer); // Enviamos el array
        outputStream.flush();
      }





    }catch (UnknownHostException e){
      System.err.println("Error: Nombre de host no encontrado.");
    }catch (IOException e){
      System.err.println("Error de entrada/salida al abrir el socket.");
    }
  }

}
