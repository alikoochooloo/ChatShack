/**
 * This connection class contains all of the INCOMING message logic for the ChatShackServer Application
 * Incoming messages are stored in the Vector 'messageList' which is passed in as a parameter to individual connections
 * Valid incoming messages are stored in the Vector 'messageList'
 * 
 * Dalton Rutledge
 */

import java.net.*;
import java.io.*;
import java.util.Vector;
import java.util.HashMap;

public class InComingConnection implements Runnable
{
	private Socket	client;
	public static final int BUFFER_SIZE = 4096;

	private Vector messageList;
	private HashMap userConnections;
	private String usrname;
	
	public InComingConnection(Socket client, Vector messageList, Hashmap userConnections) {
		this.client = client;
		this.messageList = messageList;
		this.userConnections = userConnections;
		this.usrName = "";
	}

    /*
     * This method runs in a separate thread.
     */	
	public void run() { 
		byte[] buffer = new byte[BUFFER_SIZE];
		
		// declaring data some streams
		BufferedInputStream  fromClient = null;
		BufferedOutputStream toClient = null;

		try {
			//Handle the request
			fromClient = new BufferedInputStream(client.getInputStream());
			toClient = new BufferedOutputStream(client.getOutputStream());


			//close streams
			if (fromClient != null)
			fromClient.close();
			if (toClient != null)
			toClient.close();
		}
		catch (java.io.IOException ioe) {
			System.err.println(ioe);
		}
}
}