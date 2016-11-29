//package chat;

import java.io.*;
import java.net.*;
import java.util.*;
import java.text.*;
import chat.*;
import GUI.*;


public class main{
    public static void main(String[] args) throws FileNotFoundException{

        chat.Writer writer = new chat.Writer(0);
        chat.Printer printer = new chat.Printer(0);
        Scanner input = new Scanner(System.in);

        //Config config = new Config(1);
        System.out.print("Introduce server's IP: ");
        String hostIP = input.next();
        Config.setHost(hostIP);

        System.out.println("Escritor(2), pantalla(3)");
        int number = input.nextInt();


        if (number == 2)
            writer.execute();
        else if (number == 3)
            printer.execute();

        //PlayerNamesCapture namesCapture = new PlayerNamesCapture(napakalakiView, true);
        //ArrayList<String> names = namesCapture.getNames();

    }
}
