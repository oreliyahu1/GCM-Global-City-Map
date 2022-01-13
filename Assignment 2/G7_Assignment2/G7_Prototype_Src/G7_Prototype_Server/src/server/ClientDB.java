package server;
import java.io.IOException;
import java.sql.SQLException;

import config.packetTransfer.server.ServerActions;
import entities.Database;
import entities.Packet;
import server.database.MySQLConnection;
import server.database.QueryCreator;

/**
 * The Class ClientDB.
 * Extends MySQLConnection
 * Singleton class
 */
public class ClientDB extends MySQLConnection {

	//Mysql jdbc error codes
	//https://dev.mysql.com/doc/connector-j/5.1/en/connector-j-reference-error-sqlstates.html
	
	/** The instance. */
	private static ClientDB instance;
	
	/**
	 * Instantiates a new client DB.
	 *
	 * @param dbConfig the db config
	 */
	private ClientDB(Database dbConfig) {
		super(dbConfig);
	}
	
	/**
	 * Gets the client DB.
	 * Singleton creator
	 * @param dbConfig the db config
	 * @return the client DB
	 */
	public static ClientDB getClientDB(Database dbConfig) {
		if(ClientDB.instance == null)
			ClientDB.instance = new ClientDB(dbConfig);	
		return ClientDB.instance;
	}
	
	/* (non-Javadoc)
	 * @see server.database.MySQLConnection#Connect()
	 */
	@Override
	public boolean Connect() {
        System.out.println("ClientDB: Trying to connect mysql");
		try {
			super.ConnectSchema();
			initializationDBTables();
	        System.out.println("ClientDB: Connect successful");
			return true;
		} catch (SQLException ex) {/* handle any errors*/
     		System.out.println("ClientDB: SQLException " + ex.getMessage());
     		System.out.println("ClientDB: SQLState " + ex.getSQLState());
     		System.out.println("ClientDB: VendorError " + ex.getErrorCode());
     		//ER_BAD_DB_ERROR 1049
     		if(ex.getErrorCode() == 1049) {
     			if(createSchema())
     				if(Connect()) return true; //prevents recursion (return Connect())
     		}
        } catch (Exception ex) {
     		System.out.println("ClientDB: Error " + ex);
        }
		return false;
	}

	/* (non-Javadoc)
	 * @see server.database.MySQLConnection#CloseConnection()
	 */
	@Override
	public boolean CloseConnection() {
        System.out.println("ClientDB: Trying close mysql connection");	
		try {
			ClientDB.instance = null;
			if(super.CloseConnection()) {
				System.out.println("ClientDB: Close successful");
				return true;
			}
	        return true;
		} catch (SQLException ex) {/* handle any errors*/
     		System.out.println("ClientDB: SQLException " + ex.getMessage());
     		System.out.println("ClientDB: SQLState " + ex.getSQLState());
     		System.out.println("ClientDB: VendorError " + ex.getErrorCode());
        } catch (Exception ex) {
     		System.out.println("ClientDB: Error " + ex);
        }
		return false;
	}

	/**
	 * Initialization DB tables.
	 *
	 */
	public void initializationDBTables() {
 		System.out.println("ClientDB: initialization db tables");
 		for(String table : config.database.Schema.TABLES.keySet()) {
	        try {
	    		super.executeQuery(QueryCreator.createTableQueryFormat(table, config.database.Schema.TABLES.get(table)));
	     	} catch (SQLException ex)  {/* handle any errors*/
	     		System.out.println("ClientDB: cannot connect create tables");
	     		System.out.println("SQLException: " + ex.getMessage());
	     		System.out.println("SQLState: " + ex.getSQLState());
	     		System.out.println("VendorError: " + ex.getErrorCode());
	        } catch (Exception ex) {
	        	System.out.println("ClientDB: Error" + ex.getMessage());
	        }
 		}
	}
	
	/**
	 * Creates the schema.
	 *
	 * @return true, if create scheme successfully
	 */
	public boolean createSchema() {
        System.out.println("ClientDB: Trying to create schema");	
		try {
			super.Connect();
			super.executeQuery(QueryCreator.createSchemaQueryFormat(config.database.Schema.DB_SCHEMA_NAME));	
	        System.out.println("ClientDB: Schema created successfully");
			super.CloseConnection();
	        return true;
		} catch (SQLException ex) {/* handle any errors*/
     		System.out.println("ClientDB: SQLException " + ex.getMessage());
     		System.out.println("ClientDB: SQLState " + ex.getSQLState());
     		System.out.println("ClientDB: VendorError " + ex.getErrorCode());
        } catch (Exception ex) {
     		System.out.println("ClientDB: Error " + ex);
        }
		return false;
	}
	
	/**
	 * Execute SQL query.
	 *
	 * Get packet and send it to sub-request handler to continue execute according to packet window value
	 * 
	 * @param pkt the packet
	 * @return the response packet
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public Packet<?> executeSQLQuery(Packet<?> pkt) throws IOException{
		try {
			switch(pkt.getWindow()) {
			case config.packetTransfer.actions.City.WINDOW_ECITCITY:
				return server.database.requestHandler.City.execute(this, pkt);
			default:
				return ServerService.createErrorPacket(ServerActions.SERVER_RESPONSE_ERROR_NOACTION, pkt);
			}
		} catch(IllegalArgumentException iae){
			return ServerService.createErrorPacket(ServerActions.SERVER_RESPONSE_ERROR_NOACTION, pkt);
		}
		catch(SQLException ex) {
			if(ex.getErrorCode() == 1062) {
				switch(pkt.getAction()) {
				case GET:
					return ServerService.createErrorPacket(ServerActions.SERVER_RESPONSE_ERROR_UNEXISTS, pkt);
				case ADD:
					return ServerService.createErrorPacket(ServerActions.SERVER_RESPONSE_ERROR_EXISTS, pkt);
				case UPDATE:
					return ServerService.createErrorPacket(ServerActions.SERVER_RESPONSE_ERROR_UPDATE, pkt);
				case DELETE:
					return ServerService.createErrorPacket(ServerActions.SERVER_RESPONSE_ERROR_UNEXISTS, pkt);
				default:
					return ServerService.createErrorPacket(ServerActions.SERVER_RESPONSE_ERROR_NOACTION, pkt);
				}
			}
		}
		return null;
	}
}
