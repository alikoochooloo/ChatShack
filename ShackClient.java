/**
 * this is the client for chatShack
 * coppied from EchoClient
 */

import java.net.*;
import java.io.*;
import javax.swing.*;

public class ShackClient
{
	// public static final int DEFAULT_PORT = 1337;
	
	// public static void main(String[] args) throws IOException {
	// 	if (args.length != 1) {
	// 		System.err.println("Usage: java EchoClient <echo server>");
	// 		System.exit(0);
	// 	}
		
	// 	BufferedReader networkBin = null;	// the reader from the network
	// 	PrintWriter networkPout = null;		// the writer to the network
	// 	BufferedReader localBin = null;		// the reader from the local keyboard
	// 	Socket sock = null;			// the socket
		
	// 	try {
	// 		sock = new Socket(args[0], DEFAULT_PORT);
			
	// 		// set up the necessary communication channels
	// 		networkBin = new BufferedReader(new InputStreamReader(sock.getInputStream()));
	// 		localBin = new BufferedReader(new InputStreamReader(System.in));
			
	// 		/**
	// 		 * a PrintWriter allows us to use println() with ordinary
	// 		 * socket I/O. "true" indicates automatic flushing of the stream.
	// 		 * The stream is flushed with an invocation of println()
	// 		 */
	// 		networkPout = new PrintWriter(sock.getOutputStream(),true);
			
	// 		/**
	// 		 * Read from the keyboard and send it to the echo server.
	// 		 * Quit reading when the client enters a period "."
	// 		 */
	// 		boolean done = false;
	// 		while (!done) {
	// 			String line = localBin.readLine();
	// 			if (line.equals("."))
	// 				done = true;
	// 			else {
	// 				networkPout.println(line);
	// 				System.out.println("Server: " + networkBin.readLine());
	// 			}
	// 		}
	// 	}
	// 	catch (IOException ioe) {
	// 		System.err.println(ioe);
	// 	}
	// 	finally {
	// 		if (networkBin != null)
	// 			networkBin.close();
	// 		if (localBin != null)
	// 			localBin.close();
	// 		if (networkPout != null)
	// 			networkPout.close();
	// 		if (sock != null)
	// 			sock.close();
	// 	}
	// }
	Socket server;
	BufferedReader fromServer;
	ChatScreen screen;

	public void ReaderThread(Socket server, ChatScreen screen) {
		this.server = server;
		this.screen = screen;
	}
	public void run() {
		try {
			fromServer = new BufferedReader(new InputStreamReader(server.getInputStream()));

			while (true) {
				String message = fromServer.readLine();
				String[] s = message.split("|");

				// now display it on the display area
				screen.displayMessage(message);
			}
		}
		catch (IOException ioe) { System.out.println(ioe); }

	}
}
