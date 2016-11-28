//package chat;

import java.io.*;
import java.net.*;
import java.util.*;
import java.text.*;
import chat.*;
import GUI.*;


public class main{
    public static void main(String[] args) throws FileNotFoundException{   
        chat.Writer escritor = new chat.Writer(1);
        chat.Printer pantalla = new chat.Printer(1);
        chat.Server servidor = new chat.Server();
        Scanner input = new Scanner(System.in);
        
        
        //Config config = new Config(1);
        System.out.println("Escritor(2), pantalla(3)");
        int number = input.nextInt();
        
        if (number == 2)
            escritor.execute();
        else if (number == 3)
            pantalla.execute();
        
        
        Config config = new Config(1);
        chat.User user1 = new chat.User(1);
        GUI.UserView userView = new GUI.UserView(user1);
        userView.setVisible(true);
               
        
        //PlayerNamesCapture namesCapture = new PlayerNamesCapture(napakalakiView, true);
        //ArrayList<String> names = namesCapture.getNames();

    }
}
