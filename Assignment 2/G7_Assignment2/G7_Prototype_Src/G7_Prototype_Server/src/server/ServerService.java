package server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import config.packetTransfer.Actions.ACTION_TYPE;
import config.packetTransfer.server.ServerActions;
import entities.Packet;
import entities.Settings;
import server.ocsf.ObservableServer;
import server.ocsf.OriginatorMessage;

/**
 * The Class ServerService.
 * Singleton class
 */
@SuppressWarnings("deprecation") // Because of using observable
public class ServerService {

	/** The instance. */
	private static ServerService instance;
	
	/** The echo server. */
	private EchoServer echoServer;
	
	/** The db connection. */
	private ClientDB dbConnection;
	
	/** The run settings. */
	private Settings runSettings;
	
	/** The Connected. */
	private boolean Connected;
	
	/**
	 * Instantiates a new server service.
	 *
	 * @param runSettings the running settings
	 */
	private ServerService(Settings runSettings) {
		setRunSettings(runSettings);
		echoServer = new EchoServer(runSettings.getPort());
		dbConnection = ClientDB.getClientDB(runSettings.getDb());
		Connected = false;
		echoServer.addObserver(new Observer() {		
			@Override
			public void update(Observable o, Object arg) {
				switch((String)((OriginatorMessage)arg).getMessage()) {	
				case ObservableServer.SERVER_STOPPED:
					Connected=false;
					break;		
				case ObservableServer.LISTENING_EXCEPTION:
					stopServer();
					Connected=false;
					break;	
			}
		}});
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
	 * Gets the server service.
	 * Singleton creator
	 * @param runSettings the running settings
	 * @return the server service
	 */
	public static ServerService getServerService(Settings runSettings) {
		if(ServerService.instance == null)
			ServerService.instance = new ServerService(runSettings);	
		return ServerService.instance;
	}
	
	/**
	 * Checks if is connected.
	 *
	 * @return true, if is connected
	 */
	public boolean isConnected() {
		return Connected;
	}
	
	/**
	 * Gets the number of clients.
	 *
	 * @return the number of clients
	 */
	public int getNumberOfClients() {
		return echoServer.getNumberOfClients();
	}
	
	/**
	 * Run server.
	 *
	 * @return true, if run server successfully
	 */
	public boolean runServer() {
		System.out.println("ServerService: Try to start server");
        try {
        	echoServer.setPort(runSettings.getPort());
        	dbConnection.setDbConfig(runSettings.getDb());
        	if(dbConnection.Connect()) {
            	echoServer.listen();
        		System.out.println("ServerService: is connected");
        		Connected=true;
        		runSettings.save();
            	return true;
        	}
        } catch (IOException ex) {
        	System.out.println("ServerService: Exception " + ex.getMessage());
        }
 		System.out.println("ServerService: runServer error");
        stopServer();
		return false;
	}
	
	/**
	 * Stop server.
	 *
	 * @return true, if server stopped successfully
	 */
	public boolean stopServer() {
		System.out.println("ServerService: Try to stop");
		try {
	    	echoServer.stopListening();
			echoServer.close();
	    	dbConnection.CloseConnection();
	    	Connected = false;
		    System.out.println("ServerService: Shutdown successful");
		} catch (IOException e) {
        	System.out.println("ServerService: stopServer exception " + e.getMessage());
		}
	    return false;
	}
	
	/**
	 * Delete observers.
	 */
	public void deleteObservers() {
		echoServer.deleteObservers();
	}
	
	/**
	 * Handle packet.
	 * Get packet from echo server class and execute it by the client db
	 * @param pkt the packet from client
	 * @return the response packet
	 */
	protected Packet<?> handlePacket(Packet<?> pkt) {
		Packet<?> rePacket = null;
		try {
			if(checkClientLogin(pkt.getToken()))
			{
				rePacket = dbConnection.executeSQLQuery(pkt);
				if(rePacket == null)
					rePacket = ServerService.createErrorPacket(ServerActions.SERVER_RESPONSE_ERROR_UNEXPECTED, pkt);
			} else {
				//need to login
			}
		} catch (Exception ex) {
			System.out.println("ServerService: handlePacket error " + ex);
		}
		return rePacket;
	}
	
	/**
	 * Adds the observer echo server.
	 *
	 * @param o the o
	 */
	public void addObserverEchoServer(Observer o){
		echoServer.addObserver(o);
	}
	
	/**
	 * Check client login.
	 * Function without purpose - preparation for login (assignment 3)
	 * @param token the token
	 * @return true, if login successfully
	 */
	private boolean checkClientLogin(String token) {
		return (token.equals(config.packetTransfer.Prototype.CLIENT_TOKEN));
	}
	
	////////////////////// Static function for creating default response packet
	/**
	 * Creates the boolean packet.
	 *
	 * @param reAction the re action
	 * @param pkt the packet
	 * @return the packet
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static Packet<Boolean> createBooleanPacket(Boolean reAction, Packet<?> pkt) throws IOException{
		if(reAction) return ServerService.createSuccsesPacket(reAction, pkt);
		return ServerService.createErrorPacket(reAction, pkt);
	}
	
	/**
	 * Creates the error packet.
	 *
	 * @param <E> the element type
	 * @param data the data response
	 * @param pkt the packet
	 * @return the packet
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static <E> Packet<E> createErrorPacket(E data, Packet<?> pkt) throws IOException{
		return new Packet<E>(ACTION_TYPE.RESPONSE_ERROR, pkt.getWindow(), pkt.getSub_action(), null, data);
	}
	
	/**
	 * Creates the error packet.
	 *
	 * @param <E> the element type
	 * @param data the data response
	 * @param pkt the packet
	 * @return the packet
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static <E> Packet<E> createErrorPacket(ArrayList<E> data, Packet<?> pkt) throws IOException{
		return new Packet<E>(ACTION_TYPE.RESPONSE_ERROR, pkt.getWindow(), pkt.getSub_action(), null, data);
	}
	
	/**
	 * Creates the succses packet.
	 *
	 * @param <E> the element type
	 * @param data the data response
	 * @param pkt the packet
	 * @return the packet
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static <E> Packet<E> createSuccsesPacket(E data, Packet<?> pkt) throws IOException{
		return new Packet<E>(ACTION_TYPE.RESPONSE_SUCCESS, pkt.getWindow(), pkt.getSub_action(), null, data);
	}
	
	/**
	 * Creates the succses packet.
	 *
	 * @param <E> the element type
	 * @param data the data response
	 * @param pkt the packet
	 * @return the packet
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static <E> Packet<E> createSuccsesPacket(ArrayList<E> data, Packet<?> pkt) throws IOException{
		return new Packet<E>(ACTION_TYPE.RESPONSE_SUCCESS, pkt.getWindow(), pkt.getSub_action(), null, data);
	}
}
