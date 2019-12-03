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
             * and broadcast the messages to the chatroom
             */
        }
    }
} 