package server.database.requestHandler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import entities.AbstractJsonToString;
import entities.Packet;
import server.ClientDB;
import server.ServerService;
import server.database.QueryCreator;

/**
 * The Class City.
 * Static class 
 */
public class City {

	/**
	 * Execute.
	 * Get packet and db connection, execute the request by the client db
	 * @param cdb the cdb
	 * @param pkt the pkt
	 * @return the packet
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static Packet<?> execute(ClientDB cdb, Packet<?> pkt) throws SQLException, IOException{
			switch(pkt.getAction()) {
			case GET:
				return ServerService.createSuccsesPacket(getAction(cdb, pkt), pkt);
			case ADD:
				return ServerService.createBooleanPacket(addAction(cdb, pkt), pkt);
			case UPDATE:
				return ServerService.createBooleanPacket(updateAction(cdb, pkt), pkt);
			case DELETE:
				return ServerService.createBooleanPacket(deleteAction(cdb, pkt), pkt);
			default:
				throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Handle gets action
	 *
	 * @param cdb the cdb
	 * @param pkt the packet
	 * @return the return values from DB
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws SQLException the SQL exception
	 */
	private static ArrayList<String> getAction(ClientDB cdb, Packet<?> pkt) throws IOException, SQLException {
		String query = null;
		HashMap<String, String> res = pkt.objectTranslationElem();
		ArrayList<String> selectCol = new ArrayList<String>();
		ArrayList<String> whereCol = new ArrayList<String>();
		ArrayList<String> whereVal = new ArrayList<String>();
		ArrayList<String> oprVal = new ArrayList<String>();
		switch(pkt.getSub_action()) {
		case config.packetTransfer.actions.City.SUB_ACTION_GET_CITIES_NAMES:
			selectCol.add(config.database.tables.columns.City.NAME);
			query = QueryCreator.createSelectQueryFormat(config.database.tables.City.TABLE_NAME, selectCol, whereCol, oprVal);
			return cdb.executeSelectQuery(query, whereVal).get(config.database.tables.columns.City.NAME);
		case config.packetTransfer.actions.City.SUB_ACTION_GET_CITY:
			whereCol.add(config.database.tables.columns.City.NAME);
			whereVal.add(res.get(config.database.tables.columns.City.NAME));
			query = QueryCreator.createSelectQueryFormat(config.database.tables.City.TABLE_NAME, selectCol, whereCol, oprVal);
			return AbstractJsonToString.hashMapToArrayListJsonEntities(entities.City::fromHashMap, cdb.executeSelectQuery(query, whereVal));
		default:
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Handle delete action.
	 *
	 * @param cdb the cdb
	 * @param pkt the packet
	 * @return true, if successfully deleted
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws SQLException the SQL exception
	 */
	private static boolean deleteAction(ClientDB cdb, Packet<?> pkt) throws IOException, SQLException {
		String query = null;
		HashMap<String, String> res = pkt.objectTranslationElem();
		ArrayList<String> whereCol = new ArrayList<String>();
		ArrayList<String> whereVal = new ArrayList<String>();
		ArrayList<String> oprVal = new ArrayList<String>();
		switch(pkt.getSub_action()) {
		case config.packetTransfer.actions.City.SUB_ACTION_DELETE_CITY:
			whereCol.add(config.database.tables.columns.City.NAME);
			whereVal.add(res.get(config.database.tables.columns.City.NAME));
			break;
		default:
			throw new IllegalArgumentException();
		}
		query = QueryCreator.createDeleteQueryFormat(config.database.tables.City.TABLE_NAME, whereCol, oprVal);
		if(!cdb.executeQuery(query, whereVal)) throw new SQLException("","",1062);
		return true;
	}
	
	/**
	 * Handle adds the action.
	 *
	 * @param cdb the cdb
	 * @param pkt the packet
	 * @return true, if successfully added
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws SQLException the SQL exception
	 */
	private static boolean addAction(ClientDB cdb, Packet<?> pkt) throws IOException, SQLException {
		String query = null;
		ArrayList<String> selectCol = new ArrayList<String>();
		ArrayList<String> whereVal = new ArrayList<String>();
		switch(pkt.getSub_action()) {
		case config.packetTransfer.actions.City.SUB_ACTION_ADD_CITY:
			entities.City city=  AbstractJsonToString.toObject(entities.City.class, pkt.objectTranslationElem());
			selectCol.add(config.database.tables.columns.City.NAME);
			whereVal.add(city.getName());
			selectCol.add(config.database.tables.columns.City.NO_MAPS);
			whereVal.add(Integer.toString(city.getNumberOfMaps()));
			selectCol.add(config.database.tables.columns.City.NO_POI);
			whereVal.add(Integer.toString(city.getNumberOfPOI()));
			selectCol.add(config.database.tables.columns.City.NO_TOURS);
			whereVal.add(Integer.toString(city.getNumberOfTours()));
			selectCol.add(config.database.tables.columns.City.VERSION);
			whereVal.add(city.getVersion().toString());
			break;
		default:
			throw new IllegalArgumentException();
		}
		query = QueryCreator.createInsertQueryFormat(config.database.tables.City.TABLE_NAME, selectCol);
		//If not added executeQuery throws 1062 error code
		return cdb.executeQuery(query, whereVal);
	}
	
	/**
	 * Handle  update action.
	 *
	 * @param cdb the cdb
	 * @param pkt the pkt
	 * @return true, if successful
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private static boolean updateAction(ClientDB cdb, Packet<?> pkt) throws SQLException, IOException {
		String query = null;
		HashMap<String, String> res = pkt.objectTranslationElem();
		ArrayList<String> selectCol = new ArrayList<String>();
		ArrayList<String> whereCol = new ArrayList<String>();
		ArrayList<String> whereVal = new ArrayList<String>();
		ArrayList<String> oprVal = new ArrayList<String>();
		switch(pkt.getSub_action()) {
		case config.packetTransfer.actions.City.SUB_ACTION_EDIT_CITY:
			entities.City city = AbstractJsonToString.toObject(entities.City.class, res.get(config.packetTransfer.actions.City.SUB_ACTION_EDIT_CITY));
			selectCol.add(config.database.tables.columns.City.NAME);
			whereVal.add(city.getName());
			selectCol.add(config.database.tables.columns.City.NO_MAPS);
			whereVal.add(Integer.toString(city.getNumberOfMaps()));
			selectCol.add(config.database.tables.columns.City.NO_POI);
			whereVal.add(Integer.toString(city.getNumberOfPOI()));
			selectCol.add(config.database.tables.columns.City.NO_TOURS);
			whereVal.add(Integer.toString(city.getNumberOfTours()));
			selectCol.add(config.database.tables.columns.City.VERSION);
			whereVal.add(city.getVersion().toString());
			whereCol.add(config.database.tables.columns.City.NAME);
			whereVal.add(res.get(config.database.tables.columns.City.NAME));
			break;
		default:
			throw new IllegalArgumentException();
		}
		query = QueryCreator.createUpdateQueryFormat(config.database.tables.City.TABLE_NAME, selectCol, whereCol, oprVal);
		if(!cdb.executeQuery(query, whereVal)) throw new SQLException("","",1062);
		return true;
	}
	
	/**
	 * Override public contractor to make it static.
	 */
	private City() {}
}
