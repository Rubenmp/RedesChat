//package chat;

import java.io.*;
import java.net.*;
import java.util.*;
import java.text.*;
import chat.*;
import GUI.*;


public class server{
    public static void main(String[] args) throws FileNotFoundException{   
        chat.Server servidor = new chat.Server();
        Scanner input = new Scanner(System.in);
        
        Config config = new Config(1);

        System.out.println("Server...");
        servidor.execute();
    }
}

