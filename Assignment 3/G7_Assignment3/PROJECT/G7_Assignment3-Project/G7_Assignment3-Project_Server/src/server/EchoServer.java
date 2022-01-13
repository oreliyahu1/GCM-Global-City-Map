package server;
// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

import java.io.*;
import java.util.HashMap;
import application.ServerMain;
import entities.AbstractJsonToString;
import entities.Packet;
import server.ocsf.*;

// TODO: Auto-generated Javadoc
/**
 * The Class EchoServer.
 * Extends ObservableOriginatorServer
 * In charge of communication with clients
 */
public class EchoServer extends ObservableOriginatorServer 
{ 
	
	/** The Constant USERNAME_LOGIN. */
	public static final String USERNAME_LOGIN = "#OS:Username login.";
	
	/** The Constant USERNAME_LOGOUT. */
	public static final String USERNAME_LOGOUT = "#OS:Username logout.";
	
	/** The Constant USERNAME_TOKEN_EXPIRED. */
	public static final String USERNAME_TOKEN_EXPIRED = "#OS:Username expired.";
	
	/**
   * Instantiates a new echo server.
   *
   * @param port the port
   */
	protected EchoServer(int port) 
	{
		super(port);
	}

	/**
	 * Sets the client config.
	 *
	 * @param client the client
	 * @param configClient the config client
	 */
	@SuppressWarnings("unchecked")
	public synchronized void setClientConfig(ConnectionToClient client, HashMap<String, String> configClient) {
		synchronized(client) {
			if(client.getInfo("config") == null)
				client.setInfo("config", configClient);
			else {
				for(String key : configClient.keySet())
					((HashMap<String, String>)client.getInfo("config")).replace(key, configClient.get(key));
			}
		}
	}
	
	/**
	 * Gets the clients config.
	 *
	 * @return the clients config
	 */
	public synchronized HashMap<String, HashMap<String, String>> getClientsConfig(){
		HashMap<String, HashMap<String, String>> clients = new HashMap<String, HashMap<String, String>>();
		synchronized (super.getClientConnections()) {
			for(Thread clientThread : super.getClientConnections()) {
				synchronized (clientThread) {
					if(clientThread instanceof ConnectionToClient) {
						ConnectionToClient client = (ConnectionToClient)clientThread;
						@SuppressWarnings("unchecked")
						HashMap<String, String> configClient = (HashMap<String, String>)client.getInfo("config");
						clients.put(client.getId() + "/" + ((client.getInetAddress() == null) ? "" : client.getInetAddress().getHostName()), configClient);
					}
				}		

			}	
		}
		return clients;
	}
	
	/* (non-Javadoc)
   *@see server.ocsf.ObservableOriginatorServer#handleMessageFromClient(java.lang.Object, server.ocsf.ConnectionToClient)
   *
   * operating client requestss
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
			String clientRes = ServerMain.srv.handlePacket(client, clientPKT).toString(); 
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
	
	  /**
	   * Hook method called each time a user is login
	   * The method may be overridden by subclasses.
	   *
	   * @param client the connection with the client.
	   */
	  @SuppressWarnings("deprecation")
	protected synchronized void usernameLogin(ConnectionToClient client) 
	  {
	    setChanged();
	    notifyObservers(new OriginatorMessage(client, USERNAME_LOGIN));
	  }

	  /**
	   * Hook method called each time a user is logout
	   * The method may be overridden by subclasses.
	   *
	   * @param client the connection with the client.
	   */
	  @SuppressWarnings("deprecation")
	protected synchronized void usernameLogout(ConnectionToClient client) 
	  {
	    setChanged();
	    notifyObservers(new OriginatorMessage(client, USERNAME_LOGOUT));
	  }
	  
	  /**
	   * Hook method called each time a token is expired
	   * The method may be overridden by subclasses.
	   *
	   * @param client the connection with the client.
	   */
	  @SuppressWarnings("deprecation")
	protected synchronized void tokenExpired(ConnectionToClient client) 
	  {
	    setChanged();
	    notifyObservers(new OriginatorMessage(client, USERNAME_TOKEN_EXPIRED));
	  }
}
