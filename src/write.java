//package c<hat;

import java.io.*;
import java.net.*;
import java.util.*;

import chat.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class write{
    public static void main(String[] args) throws FileNotFoundException{
 


        /*
        public class BufferedReaderExample {  public static void main(String[] args) {   try (BufferedReader br = new BufferedReader(new FileReader("C:\\testing.txt")))   {    String sCurrentLine;    while ((sCurrentLine = br.readLine()) != null) {     System.out.println(sCurrentLine);    }   } catch (IOException e) {    e.printStackTrace();   }  } }
        */
      
      
        
          
        chat.Writer escritor = new chat.Writer();
        chat.Printer pantalla = new chat.Printer();
        chat.Server servidor = new chat.Server();
        Scanner input = new Scanner(System.in);


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
