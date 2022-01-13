// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

package client;

import client.ocsf.*;
import entities.AbstractJsonToString;
import entities.Packet;
import entities.Settings;

import java.io.*;

/**
 * The Class ClientService.
 * Main Class that extends ObservableClient
 * Singleton class
 * In charge of communication with server
 */
public class ClientService extends ObservableClient
{
	
	/** The instance. */
	private static ClientService instance;
	
	/** The run settings. */
	private Settings runSettings;
	
	private ResponseHandler responseHandler;
	
	/**
   * Gets the client service.
   * Singleton creator
   * @param host the host
   * @param port the port
   * @return the client service
   */
  public static ClientService getClientService(Settings runSettings) {
		if(ClientService.instance == null) {
			runSettings.open();
			ClientService.instance = new ClientService(runSettings);
		}
		return ClientService.instance;
	}

	/**
	 * Instantiates a new client service.
	 *
	 * @param runSettings the running settings
	 */
	private ClientService(Settings runSettings)
	{
		super(runSettings.getIp(), runSettings.getPort()); //Call the superclass constructor
		this.runSettings = runSettings;
		this.responseHandler = new ResponseHandler();
	}
	
	/**
	 * Gets the run settings.
	 *
	 * @return the run settings
	 */
	public Settings getRunSettings() {
		return runSettings;
	}

	/**
	 * Sets the run settings.
	 *
	 * @param runSettings the new run settings
	 */
	public void setRunSettings(Settings runSettings) {
		this.runSettings = runSettings;
		runSettings.open();
	}
	
	/**
	 * Run client.
	 *
	 * @return true, if successful
	 */
	public boolean runClient() {
		System.out.println("ClientService: Trying to connect server");
		try {
			setHost(runSettings.getIp());
			setPort(runSettings.getPort());
			openConnection();
			runSettings.save();
			System.out.println("ClientService: Client is connected");
			return true;
		} catch (IOException ex) {
			System.out.println("ClientService: Exception " + ex.getMessage());
		}
		return false;
	}
	
	/**
	 * Stop client.
	 *
	 * @return true, if successful
	 */
	public boolean stopClient() {
		System.out.println("ClientService: Trying to stop server");
	    try {
	    	closeConnection();
	    	System.out.println("ClientService: Client is disconnected");
	    	return true;
	    } catch (IOException ex) 
	    {
	    	System.out.println("ClientService: Exception " + ex.getMessage());
	    }
	    return false;
	}
    
	/* (non-Javadoc)
   * @see client.ocsf.ObservableClient#handleMessageFromServer(java.lang.Object)
   * using platform.runlater method
   */
	public void handleMessageFromServer(Object msg) 
	{
		System.out.println("ClientService: Getting message from server");
		if(msg == null) return;
		System.out.println("ClientService: Data received from server");
		if(msg instanceof String) {
			System.out.println("ClientService: packet received from server: " + (String)msg); //note for or
			try {
				responseHandler.execute(AbstractJsonToString.toObject(entities.Packet.class, (String)msg));
			} catch (Exception ex) {
				System.out.println("ClientService: exception " + ex);
			}
		} else {
			System.out.println("ClientService: data accepted is unknown");
		}
	}


	/**
	 * Handle message from client.
	 * Send packet to server
	 * @param packet the packet
	 */
	public void handleMessageFromClient(Packet<?> pkt)  
	{
		//LoadingController.instance.show(); //think about where to put it
		//need to add mother fucker!!!!
		if(pkt == null) return;
		System.out.println("ClientService: Preparing to send packet to server");
		try {
			String pktS = pkt.toString();
			System.out.println("ClientService: Packet send to server: " + pktS);
			sendToServer(pktS);
			System.out.println("ClientService: Packet has been sent successfully");
		} catch(IOException e) {
			System.out.println("ClientService: Error sending the packet " + e.getMessage());
			stopClient();
		}
	}

}
