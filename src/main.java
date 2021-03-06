/**
* This is a user-to-user chat app we developed for a class project.
*
*
* @author  Rubén Morales Pérez & Francisco Javier Morales Piqueras
* @version 1.0
* @since   30/11/2016
*/

import java.io.*;
import java.net.*;
import java.util.*;
import java.text.*;
import chat.*;


public class main{
    public static void main(String[] args) throws FileNotFoundException{
        Scanner input = new Scanner(System.in);

        //Config config = new Config(1);
        System.out.print("Introduce server's IP: ");
        String hostIP = input.next();
        if (hostIP.isEmpty())
          Config.setHost("localhost");
        else
          Config.setHost(hostIP);

        System.out.println("Open a writer window: 1. Open a printer window: 2.");
        int number = input.nextInt();

        chat.Writer writer = new chat.Writer(0);
        chat.Printer printer = new chat.Printer(0);

        if (number == 1)
            writer.execute();
        else if (number == 2)
            printer.execute();

    }
}
