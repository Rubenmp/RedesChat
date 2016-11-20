package chat;

import java.util.ArrayList;



public class Server {
    protected static ArrayList<User> users = new ArrayList();
    
    protected static void showPlayer(int index){
        if (Config.validUser(index)){
            System.out.println(users.get(index).getIdUser() + " ");
            System.out.println(users.get(index).getName() + " ");
            for (int i=0; i<users.get(index).getPasswordLength(); ++i)
                System.out.println("*");
            for (Integer it:users.get(index).getConversations())
                System.out.println(" " + Integer.toString(it));
        }
    }
}
