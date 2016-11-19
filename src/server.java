
import java.io.*;
import java.net.*;
import java.util.*;


// Servidor del chat
// Cada chat será una hebra, lo que pasa a continuación te sorprenderá
public class server {
	public static void main(String[] args) {
		int portWriter  = 8989;
		int portPrinter = 8990;		 // Puerto de escucha
		String text;

		// Sockets
		ServerSocket serverWriter  = null;
		ServerSocket serverPrinter = null;
		Socket socketWriter        = null;
		Socket socketPrinter       = null;

		// I/O
		BufferedReader inputStream;
		PrintWriter outputStream;

		try {
			serverWriter  = new ServerSocket(portWriter); 	// Abrimos el socket en modo pasivo
			serverPrinter = new ServerSocket(portPrinter);

			do {
				socketWriter  = serverWriter.accept();   // Aceptamos una nueva conexión
				socketPrinter = serverPrinter.accept();


				inputStream  = new BufferedReader (new InputStreamReader(socketWriter.getInputStream()));
				outputStream = new PrintWriter(socketPrinter.getOutputStream(), true);




				text = inputStream.readLine();
				System.out.println(text);
				System.out.flush();




				outputStream.println(text);

			} while (true);

		} catch (IOException e) {
			System.err.println("Error al escuchar en el puerto");
		}
	}

}


/* // Version con varios chats
public static void main(String[] args){
	int idServidor; // Identifica al servidor
	int idChat;		  // Identificador del próximo chat que se cree
	ArrayList<Chat> chats = new ArrayList<Chat>(); // Lista de chats abiertos

	idServidor = id;
	idChat     = idServidor*1000;

	chats.add(new Chat(idChat));

	// Ejecutamos el chat
	chats.get(chats.size()-1).start();	// Empieza el último chat creado
}*/
