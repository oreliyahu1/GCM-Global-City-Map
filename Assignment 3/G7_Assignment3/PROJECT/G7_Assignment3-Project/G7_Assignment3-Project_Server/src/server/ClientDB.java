
package server;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

import entities.Database;
import entities.Packet;
import javafx.util.Pair;
import server.database.MySQLConnection;
import server.database.ResponseException;
import server.database.requestHandler.*;

// TODO: Auto-generated Javadoc
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
	
	/** The request handler. */
	private HashMap<String, AbstractRequest> requestHandler;
	
	/**
	 * Instantiates a new client DB.
	 *
	 * @param dbConfig the db config
	 */
	private ClientDB(Database dbConfig) {
		super(dbConfig);
		this.requestHandler = new HashMap<String, AbstractRequest>();
		requestHandler.put(config.packetTransfer.actions.Home.WINDOW, new Home(config.packetTransfer.actions.Home.WINDOW, this));
		requestHandler.put(config.packetTransfer.actions.Catalog.WINDOW, new Catalog(config.packetTransfer.actions.Catalog.WINDOW, this));
		requestHandler.put(config.packetTransfer.actions.EditCatalog.WINDOW, new EditCatalog(config.packetTransfer.actions.EditCatalog.WINDOW, this));
		requestHandler.put(config.packetTransfer.actions.Notification.WINDOW, new Notification(config.packetTransfer.actions.Notification.WINDOW, this));
		requestHandler.put(config.packetTransfer.actions.ManageRequests.WINDOW, new ManageRequests(config.packetTransfer.actions.ManageRequests.WINDOW, this));
		requestHandler.put(config.packetTransfer.actions.Rate.WINDOW, new Rate(config.packetTransfer.actions.Rate.WINDOW, this));
		requestHandler.put(config.packetTransfer.actions.Profile.WINDOW, new Profile(config.packetTransfer.actions.Profile.WINDOW, this));
		requestHandler.put(config.packetTransfer.actions.ManageUsers.WINDOW, new ManageUsers(config.packetTransfer.actions.ManageUsers.WINDOW, this));
		requestHandler.put(config.packetTransfer.actions.Activity.WINDOW, new Activity(config.packetTransfer.actions.Activity.WINDOW, this));
		requestHandler.put(config.packetTransfer.actions.Purchase.WINDOW, new Purchase(config.packetTransfer.actions.Purchase.WINDOW,this));
		requestHandler.put(config.packetTransfer.actions.Download.WINDOW, new Download(config.packetTransfer.actions.Download.WINDOW,this));
		requestHandler.put(config.packetTransfer.actions.ExternalMap.WINDOW, new Download(config.packetTransfer.actions.ExternalMap.WINDOW,this)); //FOR EXTERNAL MAP
		requestHandler.put(config.packetTransfer.actions.DailyReport.WINDOW, new DailyReport(config.packetTransfer.actions.DailyReport.WINDOW,this)); //FOR EXTERNAL MAP
		requestHandler.put(config.packetTransfer.actions.Search.WINDOW, new Search(config.packetTransfer.actions.Search.WINDOW,this)); //FOR EXTERNAL MAP
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
			initializationSchemaTables();
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
	 * Insert from CSV.
	 * insert the data into sql tables
	 *
	 * @param csvFile the csv file
	 */
	private void insertFromCSV(String csvFile) {
		ArrayList<String> columns = new ArrayList<String>();
		ArrayList<String> list = new ArrayList<String>();
		try (Scanner scanner = new Scanner(new File(csvFile), "UTF-8");) {
		    while (scanner.hasNextLine()) {
		    	list.add(scanner.nextLine());
		    }
		    if(list.isEmpty())
		    	return;
	
		    String[] vals = list.get(0).split(",");
		    for(int i = 0; i<vals.length; i++)
		    	columns.add(vals[i]);
		    
		    String query = String.format("INSERT INTO `%s`(%s) values (%s)", csvFile.substring(csvFile.lastIndexOf('\\') + 1,csvFile.lastIndexOf('.')),
		    		String.format("`%s`", String.join("`, `", columns)),
		    		String.join(", ", Collections.nCopies(columns.size(), "?")));
		    
		    list.remove(0);
		    
		    ArrayList<String> Wvals = new ArrayList<String>();
		    for(String row : list) {
		    	String[] tvals = row.split(",");
				for(int i = 0; i<tvals.length; i++)
				    Wvals.add(tvals[i]);
				for(int i=0; i<columns.size()-tvals.length; i++)
					 Wvals.add("");
				try {
					super.executeQuery(query, Wvals);
				} catch (SQLException ex) {/* handle any errors*/
			   		System.out.println("ClientDB: SQLException " + ex.getMessage());
			    	System.out.println("ClientDB: SQLState " + ex.getSQLState());
			   		System.out.println("ClientDB: VendorError " + ex.getErrorCode());
				}
				Wvals.clear();
	 		}
		}catch(Exception ex) {
			System.out.println("ClientDB: Error read " + csvFile);
		}
	}
	
	/**
	 * Initialization schema tables.
	 */
	private void initializationSchemaTables() {
 		System.out.println("ClientDB: create db tables");
 		for(String tableQuery : config.database.Schema.createTableQuery()) {
	        try {
	    		super.executeQuery(tableQuery);
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
	 * Initialization DB tables.
	 *
	 * @param initFolder the init folder
	 */
	public void initializationDBTables(File initFolder) {
		System.out.println("ClientDB: load db tables from " + initFolder);
        for(Pair<String, Map<String, String>> table : config.database.Schema.TABLES) {
        	File tablecsv = new File(initFolder.getAbsolutePath() + String.format("/%s.csv", table.getKey()));
        	if(tablecsv.exists()) {
        		System.out.println("ClientDB: insert csv file: " + tablecsv.getPath());
        	    insertFromCSV(tablecsv.getPath());
        	}else
        		System.out.println("ClientDB: error csv file not found: " + tablecsv.getPath());
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
			super.executeQuery(String.format("CREATE SCHEMA %s", config.database.Schema.DB_SCHEMA_NAME));	
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
	 * @param refPackeet the ref packeet
	 * @return the response packet
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public Packet.ACTION_TYPE executeSQLQuery(AtomicReference<Packet<?>> refPackeet) throws IOException{
		try {
			AbstractRequest reqHandler = requestHandler.get(refPackeet.get().getWindow());
			if(reqHandler == null)
				throw new IOException();
			refPackeet.set(reqHandler.execute(refPackeet.get()));
			return Packet.ACTION_TYPE.RESPONSE_SUCCESS;
		} catch(IOException ioe){
			return Packet.ACTION_TYPE.FATAL_ERROR;
		}
		catch(SQLException ex) {
     		System.out.println("ClientDB SQLException: " + ex.getMessage());
        	System.out.println("ClientDB SQLState: " + ex.getSQLState());
        	return Packet.ACTION_TYPE.RESPONSE_ERROR;
		} catch (ResponseException e) {
			return Packet.ACTION_TYPE.RESPONSE_ERROR;
		}
	}
}
