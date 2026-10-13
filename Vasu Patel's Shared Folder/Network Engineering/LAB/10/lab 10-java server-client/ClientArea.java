//ClientArea.java

import java.io.*;
import java.net.*;

public class ClientArea {
	public static void main(String[] args) throws IOException{

		String ip="127.0.0.1";
		int port=9999;
		Socket s = new Socket(ip, port); //or "localhost" or your IP
		System.out.println("Socket is open now at "+ ip+":"+port+"\n");
		
		DataInputStream inputFromClient = new DataInputStream(s.getInputStream());
		System.out.println("Waiting for data from the server:");
		System.out.println(inputFromClient.readDouble());

		inputFromClient.close();
		s.close();
		System.out.println("Socket is closed at "+ ip+":"+port+"\n");

	}
}
