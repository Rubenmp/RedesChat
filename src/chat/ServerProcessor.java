package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;





public class ServerProcessor extends Thread{
    private Socket socketService, socketService2;
    private BufferedReader inputStream;
    private PrintWriter  outputStream;

    public ServerProcessor(Socket socket, Socket socket2){
        socketService = socket;
        socketService2 = socket2;
        inputStream  = null;
        outputStream = null;
    }

    public void run(){
        try {
            // Obtiene los flujos de escritura/lectura
            inputStream    = new BufferedReader (new InputStreamReader(socketService.getInputStream()));
            outputStream   = new PrintWriter(socketService2.getOutputStream(), true);

            String text;
            text = inputStream.readLine(); // Lee la frase a Yodaficar:
            // String message = process(text);
            outputStream.println(text);

            // Obtiene los flujos de escritura/lectura
            inputStream.close();
            outputStream.close();
            
        } catch (IOException ex) {
            Logger.getLogger(ServerProcessor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Yoda interpreta una frase y la devuelve en su "dialecto":
    private String process(String text) {
        return text;
    }

}
