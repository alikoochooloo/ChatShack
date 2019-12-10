/**
 * this is the client for chatShack
 * coppied from EchoClient
 */

import java.net.*;
import java.io.*;
import javax.swing.*;

public class ShackClient implements Runnable
{
	Socket server;
	BufferedReader fromServer;
	bigG screen;

	public void ReaderThread(Socket server, bigG screen) {
		this.server = server;
		this.screen = screen;
	}
	public void run() {
		try {
			fromServer = new BufferedReader(new InputStreamReader(server.getInputStream()));

			while (true) {
				String message = fromServer.readLine();
				String[] first = message.split("\r\n");
				String[] second = first[0].split("|");
				String username = "";
				String content = "";
				if (second[0].equals("STAT")){
					if (second[1].equals("420")){
						content = "last messege did not went through";
						username = second[0];
						// dat = "";
						screen.updatecontent(username, content);
					}
					
				}
				else{
					if (second[0].equals("PVMG")){
						username = second[1]+ " to you";
						content = first[1];
					}
					if (second[0].equals("LEAV")){
						username = "server";
						content = second[1] + " left the chat";
					}
					if (second[0].equals("JOIN")){
						username = "server";
						content = second[1] + " joined the chat";
					}
					if (second[0].equals("BDMG")){
						username = second[1];
						content = first[1];
					}
					screen.updateusers(second[0], username);
					screen.updatecontent(username, content);
				}
			}
		}
		catch (IOException ioe) { System.out.println(ioe); }

	}
}
