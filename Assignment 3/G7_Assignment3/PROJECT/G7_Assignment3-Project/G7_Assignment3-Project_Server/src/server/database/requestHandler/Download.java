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
import entities.Rate;
import server.ClientDB;
import server.database.ResponseException;

// TODO: Auto-generated Javadoc
/**
 * The Class Activity.
 * Extends AbstractRequest
 */
public class Download extends AbstractRequest {

	/**
	 * Instantiates a new download.
	 *
	 * @param window the window
	 * @param cdb the cdb
	 */
	public Download(String window, ClientDB cdb) {
		super(window, cdb);
	}

	/* (non-Javadoc)
	 * @see server.database.requestHandler.AbstractRequest#getAction(entities.Packet)
	 */
	@Override
	protected ArrayList<String> getAction(Packet<?> pkt) throws IOException, SQLException, ResponseException {
		ArrayList<String> whereVal = null;
		switch(pkt.getSub_action()) {
		case config.packetTransfer.actions.Download.SUB_ACTION_GET_CITY_MAPS:
			ArrayList<String> data1 = pkt.objectTranslation();
			entities.Purchase purchase = AbstractJsonToString.toObject(entities.Purchase.class, data1.get(0));
			whereVal = AbstractRequest.createArrayList(String.valueOf(purchase.getId()));
			if((purchase.getPurchasePrice().getRtype() == Rate.type.SUBSCRIPTION) || (cdb.executeQuery(config.database.queries.Purchase.MySQL_QUERY_UPDATE_PURCHASE_DOWNLOAD, whereVal))) {
				whereVal = AbstractRequest.createArrayList(purchase.getCity().getCountry().getShortName(),
						purchase.getCity().getName(),
						purchase.getCity().getVersion().toString());
				return AbstractJsonToString.hashMapToArrayListJsonEntities(entities.Map::fromHashMap,
						cdb.executeSelectQuery(config.database.queries.Map.MySQL_QUERY_GET_CITY_MAPS_BY_CITY, whereVal));
			}
		case config.packetTransfer.actions.Download.SUB_ACTION_GET_FULL_MAP_BY_CITY:
			ArrayList<String> data = pkt.objectTranslation();
			entities.Activity activity2 = AbstractJsonToString.toObject(entities.Activity.class, data.get(0));
			whereVal = AbstractRequest.createArrayList(
					activity2.getPurchase().getCity().getName(),
					String.valueOf(activity2.getPurchase().getId()),
					activity2.getPurchase().getCity().getCountry().getShortName(),
					activity2.getPurchase().getCity().getVersion().toString(),
					entities.Activity.type.DOWNLOAD.toString(),
					activity2.getMap().getName(),
					activity2.getUser().getUsername());
			cdb.executeQuery(config.database.queries.Activity.MySQL_QUERY_ADD_ACTIVITY, whereVal);
			whereVal = AbstractRequest.createArrayList(activity2.getPurchase().getCity().getCountry().getShortName(),
					activity2.getPurchase().getCity().getName(),
					activity2.getPurchase().getCity().getVersion().toString(),
					activity2.getMap().getName());
			return AbstractJsonToString.hashMapToArrayListJsonEntities(entities.Map::fromHashMap,
					cdb.executeSelectQuery(config.database.queries.Map.MySQL_QUERY_GET_ALL_CITY_MAPS, whereVal));
		case config.packetTransfer.actions.Download.SUB_ACTION_GET_POISMAP_BY_MAP:
			HashMap<String, String> res2 = pkt.objectTranslationElem();
			whereVal = AbstractRequest.createArrayList(res2.get(config.database.tables.columns.Map.SHORTCOUNTRY),
					res2.get(config.database.tables.columns.Map.CITY),
					res2.get(config.database.tables.columns.Map.COLLECTION_VERSION),
					res2.get(config.database.tables.columns.Map.NAME));
			return AbstractJsonToString.hashMapToArrayListJsonEntities(entities.PlaceOfInterestMap::fromHashMap,
					cdb.executeSelectQuery(config.database.queries.PlaceOfInterestMap.MySQL_QUERY_GET_POIS_BY_MAP,whereVal));	
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
	protected boolean addAction(Packet<?> pkt) throws IOException, SQLException, ResponseException {
		return false;
	}
	
	/* (non-Javadoc)
	 * @see server.database.requestHandler.AbstractRequest#updateAction(entities.Packet)
	 */
	@Override
	protected boolean updateAction(Packet<?> pkt) throws IOException, SQLException, ResponseException {
		return false;
	}
}
