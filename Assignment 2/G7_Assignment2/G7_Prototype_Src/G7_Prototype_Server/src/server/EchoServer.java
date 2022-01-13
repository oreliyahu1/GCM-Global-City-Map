package server;
// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

import java.io.*;

import application.ServerMain;
import entities.AbstractJsonToString;
import entities.Packet;
import server.ocsf.*;

/**
 * The Class EchoServer.
 * Extends ObservableOriginatorServer
 * In charge of communication with clients
 */
public class EchoServer extends ObservableOriginatorServer 
{ 
	/**
   * Instantiates a new echo server.
   *
   * @param port the port
   */
	protected EchoServer(int port) 
	{
		super(port);
	}

	/* (non-Javadoc)
   * @see server.ocsf.ObservableOriginatorServer#handleMessageFromClient(java.lang.Object, server.ocsf.ConnectionToClient)
   */
	protected synchronized void handleMessageFromClient(Object msg, ConnectionToClient client)
	{
		if(msg == null) return;
		System.out.println("Data received: from " + client);
		try {
			if(msg instanceof String) {
			// json to packet
			Packet<?> clientPKT = AbstractJsonToString.toObject(entities.Packet.class, (String)msg);
			System.out.println("Server get request: " + clientPKT + "\nfrom " + client);
			String clientRes = ServerMain.srv.handlePacket(clientPKT).toString(); 
			client.sendToClient(clientRes); 
			System.out.println("Server sent response: " + clientRes + "\nData has been sent successfully to " + client);
			} else {
				System.out.println("Data received is unknown from " + client);
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
    

	/* (non-Javadoc)
	 * @see server.ocsf.ObservableOriginatorServer#serverStarted()
	 */
	protected synchronized void serverStarted()
	{
		super.serverStarted();
		System.out.println("Server listening for connections on port " + getPort());
	}
  
	
	/* (non-Javadoc)
	 * @see server.ocsf.ObservableOriginatorServer#serverStopped()
	 */
	protected synchronized void serverStopped()
	{
		super.serverStopped();
		System.out.println("Server has stopped listening for connections.");
	}
	
	/* (non-Javadoc)
	 * @see server.ocsf.ObservableOriginatorServer#clientConnected(server.ocsf.ConnectionToClient)
	 */
	protected synchronized void clientConnected(ConnectionToClient client) {
		super.clientConnected(client);
		System.out.println("New client is connected " + client.toString());
	}

	
	/* (non-Javadoc)
	 * @see server.ocsf.ObservableOriginatorServer#clientDisconnected(server.ocsf.ConnectionToClient)
	 */
	protected synchronized void clientDisconnected(ConnectionToClient client) {
		super.clientDisconnected(client);
		System.out.println("Client is disconnected " + client.toString());
	}
	
}
