//ServerArea.java
import java.io.*;
import java.net.*;

public class ServerArea {

	public static void main(String[] args) throws IOException{
			int port=9999;
			ServerSocket s = new ServerSocket(port);
			Socket sCli = s.accept();

			DataOutputStream outputToClient = new DataOutputStream(sCli.getOutputStream());

			outputToClient.writeDouble(12.5);

			outputToClient.close();
			sCli.close();	
	}
}
