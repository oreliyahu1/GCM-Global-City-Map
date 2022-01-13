package server.database.requestHandler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import entities.Packet;
import server.ClientDB;
import server.database.ResponseException;

// TODO: Auto-generated Javadoc
/**
 * The Class AbstractRequest.
 * abstract class ,
 *  according to SUB ACTIONS , queries will be executed using classes which extends this class 
 */
public abstract class AbstractRequest {
	
	/** The window. */
	protected String window;
	
	/** The cdb. */
	protected ClientDB cdb;
	
	/**
	 * Instantiates a new abstract request.
	 *
	 * @param window the window
	 * @param cdb the cdb
	 */
	public AbstractRequest(String window, ClientDB cdb) {
		this.window = window;
		this.cdb = cdb;
	}

	/**
	 * Execute.
	 * Get packet and db connection, execute the request by the client db
	 * 
	 * according to action type , call the action function to execute the queries
	 *
	 * @param pkt the pkt
	 * @return the packet
	 * @throws SQLException the SQL exception
	 * @throws ResponseException the response exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public Packet<?> execute(Packet<?> pkt) throws SQLException, ResponseException, IOException{
		boolean audres;
			switch(pkt.getAction()) {
			case GET:
				return new Packet<ArrayList<String>>(Packet.ACTION_TYPE.RESPONSE_SUCCESS, pkt.getWindow(), pkt.getSub_window(), pkt.getSub_action(), getAction(pkt));
			case ADD:
				audres = addAction(pkt);
				break;
			case UPDATE:
				audres = updateAction(pkt);
				break;
			case DELETE:
				audres = deleteAction(pkt);
				break;
			default:
				throw new ResponseException(Packet.ACTION_TYPE.FATAL_ERROR);
		}
		if(!audres)
			throw new ResponseException(Packet.ACTION_TYPE.RESPONSE_ERROR);
	
		return new Packet<String>(Packet.ACTION_TYPE.RESPONSE_SUCCESS, pkt.getWindow(), pkt.getSub_window(), pkt.getSub_action(), "");
	}
	

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof AbstractRequest)
			return ((AbstractRequest)obj).getWindow().equals(this.getWindow());
		return false;
	}


	/**
	 * Gets the window.
	 *
	 * @return the window
	 */
	public String getWindow() {
		return window;
	}


	/**
	 * Sets the window.
	 *
	 * @param window the new window
	 */
	public void setWindow(String window) {
		this.window = window;
	}

	
	/**
	 * Gets the cdb.
	 *
	 * @return the cdb
	 */
	public ClientDB getCdb() {
		return cdb;
	}

	/**
	 * Sets the cdb.
	 *
	 * @param cdb the new cdb
	 */
	public void setCdb(ClientDB cdb) {
		this.cdb = cdb;
	}

	/**
	 * Handle gets action.
	 *
	 * @param pkt the packet
	 * @return the return values from DB
	 * @throws ResponseException the response exception
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected abstract ArrayList<String> getAction(Packet<?> pkt) throws ResponseException, SQLException, IOException;
	
	/**
	 * Handle delete action.
	 *
	 * @param pkt the packet
	 * @return true, if successfully deleted
	 * @throws ResponseException the response exception
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected abstract boolean deleteAction(Packet<?> pkt) throws ResponseException, SQLException, IOException;
	
	/**
	 * Handle adds the action.
	 *
	 * @param pkt the packet
	 * @return true, if successfully added
	 * @throws ResponseException the response exception
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected abstract boolean addAction(Packet<?> pkt) throws ResponseException, SQLException, IOException;
	
	/**
	 * Handle  update action.
	 *
	 * @param pkt the pkt
	 * @return true, if successful
	 * @throws SQLException the SQL exception
	 * @throws ResponseException the response exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected abstract boolean updateAction(Packet<?> pkt) throws SQLException, ResponseException, IOException;
	
	/**
	 * Creates the array list.
	 *
	 * @param arg0 the arg 0
	 * @return the array list
	 */
	public static ArrayList<String> createArrayList(String...arg0) {
		ArrayList<String> re = new ArrayList<String>();
		for(String arg : arg0)
			re.add(arg);
		return re;
	}
	
}
