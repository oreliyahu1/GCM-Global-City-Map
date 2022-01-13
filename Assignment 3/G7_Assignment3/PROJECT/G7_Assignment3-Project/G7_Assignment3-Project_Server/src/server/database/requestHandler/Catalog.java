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
public class Catalog extends AbstractRequest {

	/**
	 * Instantiates a new catalog.
	 *
	 * @param window the window
	 * @param cdb the cdb
	 */
	public Catalog(String window, ClientDB cdb) {
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
		case config.packetTransfer.actions.Catalog.SUB_ACTION_GET_COUNTRIES://get total cities,maps,tours,places of interes
			return AbstractJsonToString.hashMapToArrayListJsonEntities(entities.Country::fromHashMap,
					cdb.executeSelectQuery(config.database.queries.Country.MySQL_QUERY_GET_COUNTRIES));
		case config.packetTransfer.actions.Catalog.SUB_ACTION_GET_COUNTRY_CITIES_NAMES://get cities for a specific country
			whereVal = AbstractRequest.createArrayList(res.get(config.database.tables.columns.Country.SHORT));
			return AbstractJsonToString.hashMapToArrayListJsonEntities(entities.City::fromHashMap,
					cdb.executeSelectQuery(config.database.queries.City.MySQL_QUERY_GET_COUNTRY_CITIES_BY_COUNTRY,
							whereVal));
		case config.packetTransfer.actions.Catalog.SUB_ACTION_GET_CITY_MAPS://get maps for a specific city
			whereVal = AbstractRequest.createArrayList(res.get(config.database.tables.columns.City.SHORTCOUNTRY),
					res.get(config.database.tables.columns.City.NAME),
					res.get(config.database.tables.columns.City.MAPSCOLLECTIONVERSION));
			return AbstractJsonToString.hashMapToArrayListJsonEntities(entities.Map::fromHashMap,
					cdb.executeSelectQuery(config.database.queries.Map.MySQL_QUERY_GET_CITY_MAPS_BY_CITY, whereVal));
		case config.packetTransfer.actions.Catalog.SUB_ACTION_GET_RECOMMENDED_TOURS_OF_CITY://get recommended tours for a specific city
			whereVal = AbstractRequest.createArrayList(res.get(config.database.tables.columns.City.SHORTCOUNTRY),
					res.get(config.database.tables.columns.City.NAME),
					res.get(config.database.tables.columns.City.MAPSCOLLECTIONVERSION));
			return AbstractJsonToString.hashMapToArrayListJsonEntities(entities.Tour::fromHashMap,
					cdb.executeSelectQuery(config.database.queries.Tour.MySQL_QUERY_GET_RECOMMENDED_TOURS_BY_CITY,
							whereVal));
		case config.packetTransfer.actions.Catalog.SUB_ACTION_GET_TOURS_OF_CITY://get all tours for a specific city
			whereVal = AbstractRequest.createArrayList(res.get(config.database.tables.columns.City.SHORTCOUNTRY),
					res.get(config.database.tables.columns.City.NAME),
					res.get(config.database.tables.columns.City.MAPSCOLLECTIONVERSION));
			return AbstractJsonToString.hashMapToArrayListJsonEntities(entities.Tour::fromHashMap,
					cdb.executeSelectQuery(config.database.queries.Tour.MySQL_QUERY_GET_TOURS_BY_CITY, whereVal));
		case config.packetTransfer.actions.Catalog.SUB_ACTION_GET_POIS_OF_CITY://get places of interest for a specific city
			whereVal = AbstractRequest.createArrayList(res.get(config.database.tables.columns.City.SHORTCOUNTRY),
					res.get(config.database.tables.columns.City.NAME),
					res.get(config.database.tables.columns.City.MAPSCOLLECTIONVERSION));
			return AbstractJsonToString.hashMapToArrayListJsonEntities(entities.PlaceOfInterest::fromHashMap,
					cdb.executeSelectQuery(config.database.queries.PlaceOfInterest.MySQL_QUERY_GET_POIS_BY_CITY,
							whereVal));
		case config.packetTransfer.actions.Catalog.SUB_ACTION_GET_POIS_OF_MAP:
			whereVal = AbstractRequest.createArrayList(res.get(config.database.tables.columns.Map.SHORTCOUNTRY),
					res.get(config.database.tables.columns.Map.CITY),
					res.get(config.database.tables.columns.Map.COLLECTION_VERSION),
					res.get(config.database.tables.columns.Map.NAME));
			return AbstractJsonToString.hashMapToArrayListJsonEntities(entities.PlaceOfInterestMap::fromHashMap,
					cdb.executeSelectQuery(config.database.queries.PlaceOfInterestMap.MySQL_QUERY_GET_POIS_BY_MAP,
							whereVal));
		case config.packetTransfer.actions.Catalog.SUB_ACTION_GET_FULL_MAP_OF_CITY://get all city maps	
			if((res.get(config.database.tables.columns.Purchase.ID) != null) && (res.get(config.database.tables.columns.Purchase.USER)!=null)) {
					whereVal = AbstractRequest.createArrayList(
							res.get(config.database.tables.columns.Map.CITY),
							res.get(config.database.tables.columns.Purchase.ID),
							res.get(config.database.tables.columns.Map.SHORTCOUNTRY),
							res.get(config.database.tables.columns.Map.COLLECTION_VERSION),
							entities.Activity.type.VIEW.toString(),
							res.get(config.database.tables.columns.Map.NAME),
							res.get(config.database.tables.columns.Purchase.USER));
					cdb.executeQuery(config.database.queries.Activity.MySQL_QUERY_ADD_ACTIVITY, whereVal);
			}		
			whereVal = AbstractRequest.createArrayList(res.get(config.database.tables.columns.Map.SHORTCOUNTRY),
					res.get(config.database.tables.columns.Map.CITY),
					res.get(config.database.tables.columns.Map.COLLECTION_VERSION),
					res.get(config.database.tables.columns.Map.NAME));
			return AbstractJsonToString.hashMapToArrayListJsonEntities(entities.Map::fromHashMap,
							cdb.executeSelectQuery(config.database.queries.Map.MySQL_QUERY_GET_ALL_CITY_MAPS, whereVal));	
		case config.packetTransfer.actions.Catalog.SUB_ACTION_GET_POIS_OF_TOUR ://get places of interest for a specific tour
			whereVal = AbstractRequest.createArrayList(res.get(config.database.tables.columns.Tour.SHORTCOUNTRY),
					res.get(config.database.tables.columns.Tour.CITY),
					res.get(config.database.tables.columns.Tour.COLLECTION_VERSION),
					res.get(config.database.tables.columns.Tour.NAME));
			return AbstractJsonToString.hashMapToArrayListJsonEntities(entities.PlaceOfInterestTour::fromHashMap,cdb.executeSelectQuery(config.database.queries.Tour.MySQL_QUERY_GET_POISTOUR_BY_TOUR, whereVal));
		case config.packetTransfer.actions.Catalog.SUB_ACTION_GET_PURCHASES_BY_COUNTRY_USER ://get places of interest for a specific tour
			whereVal = AbstractRequest.createArrayList(res.get(config.database.tables.columns.Purchase.SHORTCOUNTRY),
					res.get(config.database.tables.columns.Purchase.USER));
			return AbstractJsonToString.hashMapToArrayListJsonEntities(entities.Purchase::fromHashMap,cdb.executeSelectQuery(config.database.queries.Purchase.MySQL_QUERY_GET_PURCHASES_BY_COUNTRY, whereVal));
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
