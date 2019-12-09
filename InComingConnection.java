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
		this.usrname = "";
	}

    /*
     * This method runs in a separate thread.
     */	
	public void run() { 
		byte[] buffer = new byte[BUFFER_SIZE];
		
		// declaring data some streams
		BufferedInputStream  fromClient = null;
		BufferedOutputStream toClient = null;

		String good200 = "";
		String error400 = "";
		String error420 = "";

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

			
			boolean connected = true;
			while(connected){	
				//recieve and trim the client string
				String msgIn = "";
				numBytes = fromClient.read(buffer);
				msgIn += new String(buffer, 0, numBytes);
				msgIn = msgIn.trim();

				/* 
				 * check if the message is a join
				 * if the join is invalid we send back the appropriate error code
				 * Only add clients to the hashmap (and messages to the vector) if the join is valid
				 */

				//Sort out message pieces:
				int firstBar = msgIn.indexOf("|");
				int secondBar = msgIn.indexOf("|", firstBar+ 1);
				int thirdBar = msgIn.indexOf("|", secondBar + 1);
				int line1End = msgIn.indexOf("\r\n");

				String command = msgIn.substring(0, firstBar);
				String requestedUserName = msgIn.substring(firstBar + 1, secondBar); 

				//just realized I only need the below line of code for the broadcast thread. 
				//String destination = msgIn.substring(secongBar + 1, thirdBar);


				// Handle the JOIN command
				if(command.equals("JOIN")){
					boolean goodName = true; 
					if(userConnections.containsKey(requestedUserName)){
						goodName = false;
					}

					//figure out how to parse usernames for restricted characters later
					//if(requestedUserName.contains("awdadw"))

					if(requestedUserName.length()>15){
						goodName = false;
					}

					if(goodName){
						userConnections.put(requestedUserName, toClient);
						messageList.add(msgIn);
						this.username = requestedUserName;
						byte[] errorBytes = new byte[BUFFER_SIZE];
						errorBytes = good200.getBytes();
						int errLen = good200.length();
						toClient.write(errorBytes, 0, errLen);
					}
					else {
						byte[] errorBytes = new byte[BUFFER_SIZE];
						errorBytes = error420.getBytes();
						int errLen = error420.length();
						toClient.write(errorBytes, 0, errLen);
					}
				}

				else if(command.equals("LEAV")){
					messageList.add(msgIn);
					byte[] errorBytes = new byte[BUFFER_SIZE];
					errorBytes = good200.getBytes();
					int errLen = good200.length();
					toClient.write(errorBytes, 0, errLen);
					connected = false;
					userConnections.remove(this.usrname);
				}

				else if(!(this.usrName.equals(""))){
					messageList.add(msgIn);
					byte[] errorBytes = new byte[BUFFER_SIZE];
					errorBytes = good200.getBytes();
					int errLen = good200.length();
					toClient.write(errorBytes, 0, errLen);
				}

				else{
					byte[] errorBytes = new byte[BUFFER_SIZE];
					errorBytes = error400.getBytes();
					int errLen = error400.length();
					toClient.write(errorBytes, 0, errLen);
				}
			}

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