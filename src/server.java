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

public class server{
    public static void main(String[] args) throws FileNotFoundException{
        chat.Server servidor = new chat.Server();

        //Config config = new Config(1);

        System.out.println("Server...");
        servidor.execute();
    }
}
