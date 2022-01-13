
package server;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicReference;

import entities.AbstractJsonToString;
import entities.Packet;
import entities.Settings;
import entities.User;
import server.ocsf.ConnectionToClient;
import server.ocsf.ObservableServer;
import server.ocsf.OriginatorMessage;

// TODO: Auto-generated Javadoc
/**
 * The Class ServerService.
s * Singleton class
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
	
	/** The clients token. */
	private HashMap<String, String> clientsToken;
	
	/** The online runtime. */
	private Timer online_runtime;
	
	/** The perr handler. */
	private static PacketErrorHandler perrHandler;
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
				case ObservableServer.CLIENT_CONNECTED:// if client connected execute setClientConfig 
					ConnectionToClient client = (ConnectionToClient)((OriginatorMessage)arg).getOriginator();
					HashMap<String, String> configClient = new HashMap<String, String>();
					//username and user token are necessary to continue the program according to this parametrs 
					configClient.put(config.database.tables.columns.User.USERNAME, null); 
					configClient.put(config.GCM.CLIENT_DEFAULT_TOKEN, null);
					echoServer.setClientConfig(client, configClient);
					break;
			}
		}});
		online_runtime = null;
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
		if(ServerService.instance == null) {
			perrHandler = new PacketErrorHandler();
			ServerService.instance = new ServerService(runSettings);
		}
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
        	clientsToken  = new HashMap<String, String>();
        	echoServer.setPort(runSettings.getPort());
        	dbConnection.setDbConfig(runSettings.getDb());
        	if(dbConnection.Connect()) {
            	echoServer.listen();
            	online_runtime = new Timer(true); //run as daemon thread
            	//We need to reminder to subscribers!!
            	online_runtime.scheduleAtFixedRate(new TimerTask() {
            			public void run() {
        		        	dailyRunning();     
        		        }}, 0, 24* 60 * 60 * 1000);
            	///1000 is one second
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
			if(online_runtime!=null) {
				online_runtime.cancel();
				online_runtime.purge(); //removes all cancelled tasks from the timer
			}
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
	 *
	 * @param client the client
	 * @param pkt the packet from client
	 * @return the response packet
	 */
	protected Packet<?> handlePacket(ConnectionToClient client, Packet<?> pkt) {
		Packet<?> rePacket = null;
		Packet.ACTION_TYPE at = null;
		AtomicReference<Packet<?>> refPacket  = new AtomicReference<Packet<?>>(pkt);
		try {
			if(checkClientToken(client))
			{
				if(logoutHandler(client, pkt))
					return new Packet<String>(Packet.ACTION_TYPE.RESPONSE_SUCCESS,pkt.getWindow(),pkt.getSub_window(),pkt.getSub_action(),"");
				
				at = dbConnection.executeSQLQuery(refPacket);
				rePacket = refPacket.get();
				if(loginHandler(client, rePacket) == Packet.ACTION_TYPE.RESPONSE_ERROR) at=Packet.ACTION_TYPE.RESPONSE_ERROR;
			} else {
				echoServer.tokenExpired(client);
				rePacket = new Packet<String>(Packet.ACTION_TYPE.RESPONSE_ERROR,config.packetTransfer.actions.Home.WINDOW,pkt.getSub_window(),config.packetTransfer.actions.Home.SUB_ACTION_UPDATE_LOGOUT, config.packetTransfer.server.ResponseErrors.SERVER_RESPONSE_TOKEN_FAILED);
				logoutHandler(client, rePacket);
				return rePacket;
			}
			if(at != Packet.ACTION_TYPE.RESPONSE_SUCCESS)
				rePacket = perrHandler.handler(at, pkt);
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
	 * Daily running.
	 */
	private void dailyRunning() {
		try {
			if(server.database.requestHandler.DailyRequests.createDailyReport(dbConnection))
				System.out.println("Daily report created successfully");
			else
				System.out.println("Error creating daily report");
		} catch (SQLException ex) {
     		System.out.println("ClientDB: SQLException " + ex.getMessage());
     		System.out.println("ClientDB: SQLState " + ex.getSQLState());
     		System.out.println("ClientDB: VendorError " + ex.getErrorCode());
		}
		try {
			if(server.database.requestHandler.DailyRequests.sendMessageSubscriptionReminder(dbConnection))
				System.out.println("Daily subscription reminder sent successfully");
			else
				System.out.println("Error daily subscription reminder sent");
		} catch (SQLException ex) {
     		System.out.println("ClientDB: SQLException " + ex.getMessage());
     		System.out.println("ClientDB: SQLState " + ex.getSQLState());
     		System.out.println("ClientDB: VendorError " + ex.getErrorCode());
		}
	}
	
	/**
	 * Gets the clients connected.
	 *
	 * @return the clients connected
	 */
	public ArrayList<String> getClientsConnected(){
		HashMap<String, HashMap<String, String>> clients = echoServer.getClientsConfig();
		ArrayList<String> clientsConnected = new ArrayList<String>();
		for(Entry<String, HashMap<String, String>> clientConfig : clients.entrySet()) {
			String clientID = clientConfig.getKey();
			String username = clientConfig.getValue().get(config.database.tables.columns.User.USERNAME);
			String token = clientConfig.getValue().get(config.GCM.CLIENT_DEFAULT_TOKEN);
			
			if ((username == null) || (token == null)) {
				clientsConnected.add(String.format("%s / %s", clientID, config.GCM.CLIENT_DEFAULT_TOKEN));
			}else {
				if ((clientsToken.get(username) != null) && (clientsToken.get(username).equals(token)))
					token = "Connected";
				else
					token = "Expired token";
				clientsConnected.add(String.format("%s / %s / %s", clientID, username, token));
			}
		}
		return clientsConnected;
	}
	
	/**
	 * Check client login.
	 * Function without purpose - preparation for login (assignment 3)
	 *
	 * @param client the client
	 * @return true, if login successfully
	 */
	private synchronized boolean checkClientToken(ConnectionToClient client) {
		synchronized(client) {
			@SuppressWarnings("unchecked")
			HashMap<String, String> configClient = (HashMap<String, String>)client.getInfo("config");
			String username = configClient.get(config.database.tables.columns.User.USERNAME);
			String token = configClient.get(config.GCM.CLIENT_DEFAULT_TOKEN);
			if (token == null) //is guest
				return true;
			if((username == null) || (clientsToken.get(username) == null))
				return false;
			if(clientsToken.get(username).equals(token))
				return true;
			return false;
		}
	}
	
	/**
	 * Logout handler.
	 *
	 * @param client the client
	 * @param pkt the pkt
	 * @return true, if successful
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private synchronized boolean logoutHandler(ConnectionToClient client, Packet<?> pkt) throws IOException {
		synchronized(client) {
			if(pkt.getSub_action().equals(config.packetTransfer.actions.Home.SUB_ACTION_UPDATE_LOGOUT)) {
				HashMap<String, String> configClient = new HashMap<String, String>();
				configClient.put(config.database.tables.columns.User.USERNAME, null);
				configClient.put(config.GCM.CLIENT_DEFAULT_TOKEN, null);
				echoServer.setClientConfig(client, configClient);
				echoServer.usernameLogout(client);
				return true;
			}
			return false;
		}
	}
	
	/**
	 * Login handler.
	 *
	 * @param client the client
	 * @param pkt the pkt
	 * @return the packet. ACTIO N TYPE
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws NoSuchAlgorithmException the no such algorithm exception
	 */
	private Packet.ACTION_TYPE loginHandler(ConnectionToClient client, Packet<?> pkt) throws IOException, NoSuchAlgorithmException {
		if(pkt.getSub_action().equals(config.packetTransfer.actions.Home.SUB_ACTION_GET_LOGIN)) {
			ArrayList<String> res = pkt.objectTranslation();
			if(res.isEmpty())
				return Packet.ACTION_TYPE.RESPONSE_ERROR;

			User user = AbstractJsonToString.toObject(User.class, res.get(0));
			String token = user.getUsername() + Calendar.getInstance().getTime().getTime();
			byte[] bytesOfMessage = token.getBytes("UTF-8");
			MessageDigest md = MessageDigest.getInstance("MD5");
			BigInteger tokenInteger = new BigInteger(1, md.digest(bytesOfMessage));
			token = String.format("%1$032X", tokenInteger);
			if(clientsToken.containsKey(user.getUsername()))
				clientsToken.replace(user.getUsername(), token);
			else
				clientsToken.put(user.getUsername(), token);
			@SuppressWarnings("unchecked")
			HashMap<String, String> configClient = (HashMap<String, String>)client.getInfo("config");
			configClient.put(config.database.tables.columns.User.USERNAME, user.getUsername());
			configClient.put(config.GCM.CLIENT_DEFAULT_TOKEN, token);
			echoServer.usernameLogin(client);
		}
		return Packet.ACTION_TYPE.RESPONSE_SUCCESS;
	}
	
	/**
	 * Initialization DB tables.
	 *
	 * @param initFolder the init folder
	 */
	public void initializationDBTables(File initFolder) {
		dbConnection.initializationDBTables(initFolder);
	}
}
