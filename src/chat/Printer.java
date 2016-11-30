package chat;

import java.io.*;
import java.net.*;
import java.lang.Object;
import java.util.regex.*;

// This class shows different messages of the conversation, and receive
// this information from a server
public class Printer {
    private int idConversation, idUser;
    //private String host       = "localhost";	// Nombre del host donde se ejecuta el servidor:
    private String host       = Config.getHost();	// Nombre del host donde se ejecuta el servidor:
    protected static int port = Config.getPrinterPort(); // Puerto en el que espera el servidor

/*
        public Printer(int p_idConversation, int p_idUser){
        idConversation = p_idConversation;
        idUser = p_idUser;
    }
    */

    public Printer(int p_idUser){
        idConversation = 111111;
        idUser = p_idUser;
    }

    public void execute(){
        String readingBuffer;
        Socket socketService;
        BufferedReader inputStream;

        try {
            do{
                socketService = new Socket (host, 8991); // Creamos un socket que se conecte a "host" y "port":
                inputStream   = new BufferedReader(new InputStreamReader(socketService.getInputStream()));
                readingBuffer = inputStream.readLine();
                printMessage(readingBuffer);
                inputStream.close();
                socketService.close();
            } while (readingBuffer != null);

        }catch (UnknownHostException e){
                System.err.println("Error: Host name not found.");
        }catch (IOException e){
                System.err.println("Error: I/O with socket.");
        }
    }

    public void printMessage(String readingBuffer){
        Message m = Message.toMessage(readingBuffer);
        String[] text_parts;
        String bold_text = "";
        String underlined_text = "";
        String blink_text = "";
        String final_text = "";
        // BOLD TEXT

        text_parts = m.getText().split(Pattern.quote("*"));
        boolean bold = false;
        for(String part: text_parts){
            if (bold){
                bold_text += "\033[1m";
                bold_text += part;
            }
            if (!bold){
                bold_text += "\033[0m";
                bold_text += part;
            }
            bold = !bold;
        }

        // UNDERLINED TEXT

        text_parts = bold_text.split(Pattern.quote("_"));
        boolean underlined = false;
        for(String part: text_parts){
            if (underlined){
                underlined_text += "\033[4m";
                underlined_text += part;
            }
            if (!underlined){
                underlined_text += "\033[0m";
                underlined_text += part;
            }
            underlined = !underlined;
        }

        // BLINK TEXT

        text_parts = underlined_text.split(Pattern.quote("\\"));
        boolean blink = false;
        for(String part: text_parts){
            if (blink){
                blink_text += "\033[5m";
                blink_text += part;
            }
            if (!blink){
                blink_text += "\033[0m";
                blink_text += part;
            }
            blink = !blink;
        }

        final_text = blink_text + "\033[0m";

        System.out.println("\033[2m" + m.getDateString() + " from " + final_text);
        System.out.flush();
    }

}
