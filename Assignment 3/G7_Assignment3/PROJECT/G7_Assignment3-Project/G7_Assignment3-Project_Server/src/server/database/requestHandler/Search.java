/*
 * 
 * this class extends the abstract class AbstractRequest.
 * executing the execute function from super class , direct the program to diagnose 
 * the action type then call one from these functions : addAction ,getAction ,updateAction,deleteAction 
 * so we can execute the corresponding query (according to sub actions)
 *
 */


package server.database.requestHandler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import entities.AbstractJsonToString;
import entities.Packet;
import server.ClientDB;
import server.database.ResponseException;

// TODO: Auto-generated Javadoc
/**
 * The Class Catalog.
 * Extends AbstractRequest
 */
public class Search extends AbstractRequest {

	/**
	 * Instantiates a new Search.
	 *
	 * @param window the window
	 * @param cdb the cdb
	 */
	public Search(String window, ClientDB cdb) {
		super(window, cdb);
	}

	/* (non-Javadoc)
	 * @see server.database.requestHandler.AbstractRequest#getAction(entities.Packet)
	 */
	@Override
	protected ArrayList<String> getAction(Packet<?> pkt) throws IOException, SQLException, ResponseException {
		HashMap<String, String> res = pkt.objectTranslationElem();
		ArrayList<String> whereVal = null;
		switch (pkt.getSub_action()) {
		case config.packetTransfer.actions.Search.SUB_ACTION_GET_SEARCH_BY_CITY_NAME:
			whereVal = AbstractRequest.createArrayList("%"+res.get(config.database.tables.columns.City.NAME)+"%");
			return AbstractJsonToString.hashMapToArrayListJsonEntities(entities.City::fromHashMap,
					cdb.executeSelectQuery(config.database.queries.City.MySQL_QUERY_GET_SEARCH_BY_CITY_NAME,
							whereVal));
		case config.packetTransfer.actions.Search.SUB_ACTION_GET_SEARCH_BY_TEXT_CITY:
			whereVal = AbstractRequest.createArrayList("%"+res.get(config.database.tables.columns.City.DESCRIPTION)+"%");
			return AbstractJsonToString.hashMapToArrayListJsonEntities(entities.City::fromHashMap,
					cdb.executeSelectQuery(config.database.queries.City.MYSQL_QUERY_GET_SEARCH_BY_TEXT_CITY,
							whereVal));
		case config.packetTransfer.actions.Search.SUB_ACTION_GET_SEARCH_BY_TEXT_MAP:
			whereVal = AbstractRequest.createArrayList("%"+res.get(config.database.tables.columns.Map.DESCRIPTION)+"%",
					"%"+res.get(config.database.tables.columns.Map.DESCRIPTION)+"%",
					"%"+res.get(config.database.tables.columns.Map.DESCRIPTION)+"%");
			return AbstractJsonToString.hashMapToArrayListJsonEntities(entities.City::fromHashMap,
					cdb.executeSelectQuery(config.database.queries.Map.MYSQL_QUERY_GET_SEARCH_BY_TEXT_MAP,
							whereVal));
	
		case config.packetTransfer.actions.Search.SUB_ACTION_GET_SEARCH_BY_POI:
			whereVal = AbstractRequest.createArrayList("%"+res.get(config.database.tables.columns.PlaceOfInterestMap.NAME)+"%",
					"%"+res.get(config.database.tables.columns.PlaceOfInterestMap.NAME)+"%");
			return AbstractJsonToString.hashMapToArrayListJsonEntities(entities.City::fromHashMap,
					cdb.executeSelectQuery(config.database.queries.PlaceOfInterestMap.MYSQL_QUERY_GET_SEARCH_BY_POI,
							whereVal));
		default:
			throw new ResponseException(Packet.ACTION_TYPE.FATAL_ERROR);
		}
	}

	/* (non-Javadoc)
	 * @see server.database.requestHandler.AbstractRequest#deleteAction(entities.Packet)
	 */
	@Override
	protected boolean deleteAction(Packet<?> pkt) throws IOException, SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see server.database.requestHandler.AbstractRequest#addAction(entities.Packet)
	 */
	@Override
	protected boolean addAction(Packet<?> pkt) throws IOException, SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see server.database.requestHandler.AbstractRequest#updateAction(entities.Packet)
	 */
	@Override
	protected boolean updateAction(Packet<?> pkt) throws SQLException, IOException {
		// TODO Auto-generated method stub
		return false;
	}
}
