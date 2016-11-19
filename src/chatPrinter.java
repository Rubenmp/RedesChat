

import java.io.*;
import java.net.*;

// This class shows differente messages of the conversation, and receive
// this information from a server
public class chatPrinter{

	public static void main(String[] args) {
		String host = "localhost";				// Nombre del host donde se ejecuta el servidor:
		int port    = 8990; 							// Puerto en el que espera el servidor:

		String readingBuffer;
		Socket socketService;
		BufferedReader inputStream;

		try {
			socketService = new Socket (host, port); // Creamos un socket que se conecte a "host" y "port":
			inputStream = new BufferedReader(new InputStreamReader(socketService.getInputStream()));

			readingBuffer = inputStream.readLine();
			while (readingBuffer.length() != 0){
				System.out.println("Mensaje \t" + readingBuffer);

				socketService.close();
				socketService = new Socket (host, port); // Creamos un socket que se conecte a "host" y "port":

				readingBuffer = inputStream.readLine();
			}

			socketService.close();

		}catch (UnknownHostException e){
			System.err.println("Error: Nombre de host no encontrado.");
		}catch (IOException e){
			System.err.println("Error de entrada/salida al abrir el socket.");
		}
	}
}
