//package chat;

import java.io.*;
import java.net.*;
import java.util.*;
import java.text.*;
import chat.*;
import GUI.*;


public class main{
    public static void main(String[] args) throws FileNotFoundException{
        chat.Writer escritor = new chat.Writer(0);
        chat.Printer pantalla = new chat.Printer(0);
        Scanner input = new Scanner(System.in);

        //Config config = new Config(1);
        System.out.println("Escritor(2), pantalla(3)");
        int number = input.nextInt();

        if (number == 2)
            escritor.execute();
        else if (number == 3)
            pantalla.execute();

        //PlayerNamesCapture namesCapture = new PlayerNamesCapture(napakalakiView, true);
        //ArrayList<String> names = namesCapture.getNames();

    }
}
