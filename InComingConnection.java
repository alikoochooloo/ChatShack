/**
 * This InComingConnection class contains all of the INCOMING message logic for the ChatShackServer Application
 * Incoming messages are stored in the Vector 'messageList' which is passed in as a parameter to individual connections
 * Valid incoming messages are stored in the Vector 'messageList'
 * When a successful join message is received, the thread will add that user and socket outgoing data stream to the userConnections Hashmap
 * All other parts of the thread will check to make sure a valid usrname has been made (only possible through the join command in the protocol)
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
			//Open data streams
			fromClient = new BufferedInputStream(client.getInputStream());
			toClient = new BufferedOutputStream(client.getOutputStream());

			/*
			 * While-loop that reads messages from the client 
			 * Parse incoming messages according to the protocol
			 * Handle joins first to set username and update hashmap
			 * Then move on to all other commands
			 * Make sure to update the vector with all relevant messages
			 */

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