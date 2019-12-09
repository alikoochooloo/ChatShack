/**
 * This OutGoingConnection class contains all of the OUTGOING message logic for the ChatShackServer Application
 * Outgoing messages are popped from the Vector 'messageList' which is passed in as a parameter to this class when the server starts
 * Outgoing messages are sent to the correct location using each message's individual destination field, and the HashMap of userconnections 
 * (which is also passed in as instance data from the server) which stores dataoutputsteams from sockets 
 * Dalton Rutledge
 */



import java.util.HashMap;
import java.java.util.Vector;
import java.net.*;
import java.io.*;

public class outGoingConnection implements Runnable
{
    public static final int BUFFER_SIZE = 4096;
    private HashMap userConnections;
    private java.util.Vector<E> messageList; 

    public outGoingConnection(Vector messages, HashMap usersConnections){
        this.messageList = messages;
        this.userConnections = usersConnections;    
    }

    public void run() {
        while (true) {
            // sleep for 1/10th of a second
            try { Thread.sleep(100); } catch (InterruptedException ignore) { }


            /**
             * check if there are any messages in the Vector. If so, remove them
             * and broadcast the messages to the correct locations 
             * (based on the message 'dest' field, and the corresponding HashMap values)
             */

            while(!(messageList.isEmpty())){
                curMsg = messageList.remove(0);


                //Sort out message pieces:
				int firstBar = msgIn.indexOf("|");
				int secondBar = msgIn.indexOf("|", firstBar+ 1);
				int thirdBar = msgIn.indexOf("|", secondBar + 1);
				int line1End = msgIn.indexOf("\r\n");

				String command = msgIn.substring(0, firstBar);
				String requestedUserName = msgIn.substring(firstBar + 1, secondBar); 

				//just realized I only need the below line of code for the broadcast thread. 
				String destination = msgIn.substring(secongBar + 1, thirdBar);



            }


        }
    }
} 