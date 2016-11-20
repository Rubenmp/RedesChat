package chat;

import java.io.*;
import java.net.*;
import java.util.*;


// Servidor del chat
// Cada chat será una hebra, lo que pasa a continuación te sorprenderá
public class Chat{
    private int portWriter  = Writer.port;
    private int portPrinter = Printer.port; // Puerto de escucha
    String text;

    // Sockets
    private ServerSocket serverWriter  = null;
    private ServerSocket serverPrinter = null;
    private Socket socketWriter        = null;
    private Socket socketPrinter       = null;

    // I/O
    private BufferedReader inputStream = null;
    private PrintWriter outputStream   = null;
    
    private Conversation conversation = new Conversation();
    private TreeSet<User> admins      = new TreeSet<>();
    private TreeSet<User> otherUsers  = new TreeSet<>();    
    
    public Chat(){ }
    
    public void addUser(User user){
        otherUsers.add(user);
    }
    public void addAdmin(User user){
        admins.add(user);
    }
    public int removeUser(User user){
        int ret = -1;
        if (admins.contains(user) && admins.size() > 1){
            admins.remove(user);
            ret = 0;
        }
        else if (otherUsers.contains(user)){
            otherUsers.remove(user);
            ret = 0;
        }
        
        return ret;
    }
    
    public int giveAdministration(User user){
        int ret = -1;
        if (otherUsers.contains(user)){
            admins.add(user);
            otherUsers.remove(user);
            ret = 0;
        }
        
        return ret;
    }
    
    
        
    public void execute(){
        try{
            serverWriter  = new ServerSocket(portWriter); // Abrimos el socket en modo pasivo
            serverPrinter = new ServerSocket(portPrinter);
        } catch (IOException e) {
            System.err.println("Error: create server ports.");
        }

        try{
            socketPrinter = serverPrinter.accept();
            outputStream = new PrintWriter(socketPrinter.getOutputStream(), true);
        } catch (IOException e) {
            System.err.println("Error: screen port.");
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
                System.err.println("Error: writer port.");
            }
        }
        //socketPrinter.close();
        //outputStream.close();
    }

}
