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
 * The Class ExternalMap.
 * Extends AbstractRequest
 */
public class ExternalMap extends AbstractRequest {
	
	/**
	 * Instantiates a new edits the ExternalMap.
	 *
	 * @param window the window
	 * @param cdb the cdb
	 */
	public ExternalMap(String window, ClientDB cdb) {
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
		case config.packetTransfer.actions.ExternalMap.SUB_ACTION_GET_ALL_CITY_EXTERNAL_MAPS:
			whereVal = AbstractRequest.createArrayList(res.get(config.database.tables.columns.City.SHORTCOUNTRY),
					res.get(config.database.tables.columns.City.NAME),
					res.get(config.database.tables.columns.ExternalMap.NAME));
			return AbstractJsonToString.hashMapToArrayListJsonEntities(entities.City::fromHashMap,
					cdb.executeSelectQuery(config.database.queries.ExternalMap.MySQL_QUERY_GET_ALL_CITY_EXTERNAL_MAPS, whereVal));
		case config.packetTransfer.actions.ExternalMap.SUB_ACTION_GET_ALL_COUNTRIES:
			return AbstractJsonToString.hashMapToArrayListJsonEntities(entities.Country::fromHashMap,
					cdb.executeSelectQuery(config.database.queries.Country.MySQL_QUERY_GET_ALL_COUNTRIES));
		case config.packetTransfer.actions.ExternalMap.SUB_ACTION_GET_ALL_CITIES:
			return AbstractJsonToString.hashMapToArrayListJsonEntities(entities.Country::fromHashMap,
					cdb.executeSelectQuery(config.database.queries.City.MySQL_QUERY_GET_ALL_CITIES));
		default:
			throw new ResponseException(Packet.ACTION_TYPE.FATAL_ERROR);
		}
	}

	/* (non-Javadoc)
	 * @see server.database.requestHandler.AbstractRequest#deleteAction(entities.Packet)
	 */
	@Override
	protected boolean deleteAction(Packet<?> pkt)  throws IOException, SQLException, ResponseException {
		return false;
	}

	/* (non-Javadoc)
	 * @see server.database.requestHandler.AbstractRequest#addAction(entities.Packet)
	 */
	@Override
	protected boolean addAction(Packet<?> pkt) throws IOException, SQLException, ResponseException {
		ArrayList<String> whereVal = null;
		ArrayList<String> data = pkt.objectTranslation();
		switch (pkt.getSub_action()) {
		case config.packetTransfer.actions.ExternalMap.SUB_ACTION_ADD_CITY_TO_COUNTRY:
			entities.City city = AbstractJsonToString.toObject(entities.City.class, data.get(0));
			whereVal = AbstractRequest.createArrayList(city.getCountry().getShortName(), city.getName(),
					city.getVersion().toString(), city.getDescription());
			return cdb.executeQuery(config.database.queries.City.MySQL_QUERY_ADD_CITY_TO_COUNTRY, whereVal);
		case config.packetTransfer.actions.ExternalMap.SUB_ACTION_ADD_EXTERNAL_MAP:
			entities.Map map = AbstractJsonToString.toObject(entities.Map.class, data.get(0));
			whereVal = AbstractRequest.createArrayList(map.getCity().getCountry().getShortName(),
					map.getCity().getName(),
					map.getName(),
					map.getMapVer().toString(),
					map.getDescription(),
					map.getBase64_image());
			return cdb.executeQuery(config.database.queries.ExternalMap.MySQL_QUERY_ADD_EXTERNAL_MAP, whereVal);
			
		default:
			throw new ResponseException(Packet.ACTION_TYPE.FATAL_ERROR);
		}
	}

	/* (non-Javadoc)
	 * @see server.database.requestHandler.AbstractRequest#updateAction(entities.Packet)
	 */
	@Override
	protected boolean updateAction(Packet<?> pkt) throws IOException, SQLException, ResponseException {
		ArrayList<String> whereVal = null;
		ArrayList<String> json = null;
		switch (pkt.getSub_action()) {
		case config.packetTransfer.actions.ExternalMap.SUB_ACTION_UPDATE_EXTERNAL_MAP:
			json = pkt.objectTranslation();
			entities.Map map=  AbstractJsonToString.toObject(entities.Map.class, json.get(0));
			whereVal = AbstractRequest.createArrayList(
					map.getCity().getCountry().getShortName(),
					map.getCity().getName(),
					map.getName());
			return cdb.executeQuery(config.database.queries.ExternalMap.MySQL_QUERY_UPDATE_EXTERNAL_MAP, whereVal);
		default:
			throw new ResponseException(Packet.ACTION_TYPE.FATAL_ERROR);
		}
	}
}
