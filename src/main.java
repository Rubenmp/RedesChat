//package c<hat;

import java.io.*;
import java.net.*;
import java.util.*;
import java.text.*;
import chat.*;

public class main{
    public static void main(String[] args) throws FileNotFoundException{         
        chat.Writer escritor = new chat.Writer();
        chat.Printer pantalla = new chat.Printer();
        chat.Chat servidor = new chat.Chat();
        Scanner input = new Scanner(System.in);
        

        Config config = new Config();
        
        System.out.println("Servidor(1), escritor(2), pantalla(3)");
        int number = input.nextInt();
        System.out.println("...");

        if (number == 1)
            servidor.execute();
        else if (number == 2)
            escritor.execute();
        else if (number == 3)
            pantalla.execute();

    }
}
