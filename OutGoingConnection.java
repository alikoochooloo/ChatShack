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
    private HashMap userConnections;
    private Vector messageList; 

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
        }
    }
} 