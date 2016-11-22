//package chat;

import java.io.*;
import java.net.*;
import java.util.*;
import java.text.*;
import chat.*;

public class main{
    public static void main(String[] args) throws FileNotFoundException{   
        chat.Writer escritor = new chat.Writer(1);
        chat.Printer pantalla = new chat.Printer(1);
        chat.Server servidor = new chat.Server();
        Scanner input = new Scanner(System.in);
        
        
        Config config = new Config(1);
        System.out.println("Servidor(1), escritor(2), pantalla(3)");
        int number = input.nextInt();
        
        
        if (number == 1)
            servidor.execute();
        else if (number == 2)
            escritor.execute();
        else if (number == 3)
            pantalla.execute();

    }
}
