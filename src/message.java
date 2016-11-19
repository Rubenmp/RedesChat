

import java.io.*;
import java.net.*;


public class message{
  private static int idStaticMessage = 0;
	private int idChat;
  private int idUser;
  private int idMessage;
  private String text;

	public message(int p_idChat, int p_idUser, String p_text) {
		idChat          = p_idChat;
    idUser          = p_idUser;
    text            = p_text;
    idMessage       = idStaticMessage;
    idStaticMessage = idStaticMessage + 1;
	}

	public int getIdChat(){
    return idChat;
  }

  public int getIdUser(){
    return idUser;
  }

  public int getIdMessage(){
    return idMessage;
  }

}
