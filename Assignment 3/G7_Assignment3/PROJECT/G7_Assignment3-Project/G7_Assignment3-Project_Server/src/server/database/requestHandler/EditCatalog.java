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
import java.util.Collections;
import java.util.HashMap;

import entities.AbstractJsonToString;
import entities.Packet;
import entities.User;
import server.ClientDB;
import server.database.ResponseException;

// TODO: Auto-generated Javadoc
/**
 * The Class Catalog.
 * Extends AbstractRequest
 */
public class EditCatalog extends AbstractRequest {
	
	
	/**
	 * Instantiates a new edits the catalog.
	 *
	 * @param window the window
	 * @param cdb the cdb
	 */
	public EditCatalog(String window, ClientDB cdb) {
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
		case config.packetTransfer.actions.EditCatalog.SUB_ACTION_GET_COUNTRIES:
			return AbstractJsonToString.hashMapToArrayListJsonEntities(entities.Country::fromHashMap,
					cdb.executeSelectQuery(config.database.queries.Country.MySQL_QUERY_GET_COUNTRIES_EDIT));
		case config.packetTransfer.actions.EditCatalog.SUB_ACTION_GET_COUNTRY_CITIES_NAMES:
			whereVal = AbstractRequest.createArrayList(res.get(config.database.tables.columns.City.SHORTCOUNTRY));
			return AbstractJsonToString.hashMapToArrayListJsonEntities(entities.City::fromHashMap,
					cdb.executeSelectQuery(config.database.queries.City.MySQL_QUERY_GET_COUNTRY_CITIES_BY_COUNTRY_EDIT, whereVal));
		case config.packetTransfer.actions.EditCatalog.SUB_ACTION_GET_CITY_MAPS:
			whereVal = AbstractRequest.createArrayList(res.get(config.database.tables.columns.City.SHORTCOUNTRY),
					res.get(config.database.tables.columns.City.NAME),
					res.get(config.database.tables.columns.City.MAPSCOLLECTIONVERSION));
			return AbstractJsonToString.hashMapToArrayListJsonEntities(entities.Map::fromHashMap,
					cdb.executeSelectQuery(config.database.queries.Map.MySQL_QUERY_GET_CITY_MAPS_BY_CITY, whereVal));
		case config.packetTransfer.actions.EditCatalog.SUB_ACTION_GET_RECOMMENDED_TOURS_OF_CITY:
			whereVal = AbstractRequest.createArrayList(res.get(config.database.tables.columns.City.SHORTCOUNTRY),
					res.get(config.database.tables.columns.City.NAME),
					res.get(config.database.tables.columns.City.MAPSCOLLECTIONVERSION));
			return AbstractJsonToString.hashMapToArrayListJsonEntities(entities.Tour::fromHashMap,
					cdb.executeSelectQuery(config.database.queries.Tour.MySQL_QUERY_GET_RECOMMENDED_TOURS_BY_CITY, whereVal));
		case config.packetTransfer.actions.EditCatalog.SUB_ACTION_GET_TOURS_OF_CITY:
			whereVal = AbstractRequest.createArrayList(res.get(config.database.tables.columns.City.SHORTCOUNTRY),
					res.get(config.database.tables.columns.City.NAME),
					res.get(config.database.tables.columns.City.MAPSCOLLECTIONVERSION));
			return AbstractJsonToString.hashMapToArrayListJsonEntities(entities.Tour::fromHashMap,
					cdb.executeSelectQuery(config.database.queries.Tour.MySQL_QUERY_GET_TOURS_BY_CITY, whereVal));
		case config.packetTransfer.actions.EditCatalog.SUB_ACTION_GET_POIS_OF_CITY:
			whereVal = AbstractRequest.createArrayList(res.get(config.database.tables.columns.City.SHORTCOUNTRY),
					res.get(config.database.tables.columns.City.NAME),
					res.get(config.database.tables.columns.City.MAPSCOLLECTIONVERSION));
			return AbstractJsonToString.hashMapToArrayListJsonEntities(entities.PlaceOfInterest::fromHashMap,
					cdb.executeSelectQuery(config.database.queries.PlaceOfInterest.MySQL_QUERY_GET_POIS_BY_CITY, whereVal));
		case config.packetTransfer.actions.EditCatalog.SUB_ACTION_GET_POIS_OF_MAP:
			whereVal = AbstractRequest.createArrayList(res.get(config.database.tables.columns.Map.SHORTCOUNTRY),
					res.get(config.database.tables.columns.Map.CITY),
					config.GCM.CLIENT_DEFAULT_EDIT_CATALOG_VERSION,
					res.get(config.database.tables.columns.Map.NAME));
			return AbstractJsonToString.hashMapToArrayListJsonEntities(entities.PlaceOfInterestMap::fromHashMap,
					cdb.executeSelectQuery(config.database.queries.PlaceOfInterestMap.MySQL_QUERY_GET_POIS_BY_MAP,whereVal));
		case config.packetTransfer.actions.EditCatalog.SUB_ACTION_GET_FULL_MAP_OF_CITY:
			whereVal = AbstractRequest.createArrayList(res.get(config.database.tables.columns.Map.SHORTCOUNTRY),
					res.get(config.database.tables.columns.Map.CITY),
					res.get(config.database.tables.columns.Map.COLLECTION_VERSION),
					res.get(config.database.tables.columns.Map.NAME));
			return AbstractJsonToString.hashMapToArrayListJsonEntities(entities.Map::fromHashMap,
					cdb.executeSelectQuery(config.database.queries.Map.MySQL_QUERY_GET_ALL_CITY_MAPS, whereVal));
		case config.packetTransfer.actions.EditCatalog.SUB_ACTION_GET_LAST_COLLECTION_VERSION:
			whereVal = AbstractRequest.createArrayList(res.get(config.database.tables.columns.City.SHORTCOUNTRY),
					res.get(config.database.tables.columns.City.NAME));
			return AbstractJsonToString.hashMapToArrayListJsonEntities(entities.City::fromHashMap,
					cdb.executeSelectQuery(config.database.queries.City.MySQL_QUERY_GET_LAST_COLLECTION_VERSION, whereVal));
		case config.packetTransfer.actions.EditCatalog.SUB_ACTION_GET_ALL_CATEGORIES:
			return AbstractJsonToString.hashMapToArrayListJsonEntities(entities.Category::fromHashMap,
					cdb.executeSelectQuery(config.database.queries.Category.MySQL_QUERY_GET_ALL_CATEGORIES));
		case config.packetTransfer.actions.Catalog.SUB_ACTION_GET_POIS_OF_TOUR :
			whereVal = AbstractRequest.createArrayList(res.get(config.database.tables.columns.Tour.SHORTCOUNTRY),
					res.get(config.database.tables.columns.Tour.CITY),
					res.get(config.database.tables.columns.Tour.COLLECTION_VERSION),
					res.get(config.database.tables.columns.Tour.NAME));
			return AbstractJsonToString.hashMapToArrayListJsonEntities(entities.PlaceOfInterestTour::fromHashMap,cdb.executeSelectQuery(config.database.queries.Tour.MySQL_QUERY_GET_POISTOUR_BY_TOUR, whereVal));
		case config.packetTransfer.actions.EditCatalog.SUB_ACTION_GET_ALL_EXTERNAL_MAPS_BY_CITY:
			whereVal = AbstractRequest.createArrayList(res.get(config.database.tables.columns.Map.SHORTCOUNTRY),
					res.get(config.database.tables.columns.Map.CITY));
			return AbstractJsonToString.hashMapToArrayListJsonEntities(entities.Map::fromHashMap,cdb.executeSelectQuery(config.database.queries.ExternalMap.MySQL_QUERY_GET_ALL_CITY_EXTERNAL_MAPS, whereVal));
		case config.packetTransfer.actions.EditCatalog.SUB_ACTION_GET_EXTERNAL_FULL_MAP:
			whereVal = AbstractRequest.createArrayList(res.get(config.database.tables.columns.Map.SHORTCOUNTRY),
					res.get(config.database.tables.columns.Map.CITY),
					res.get(config.database.tables.columns.Map.NAME));
			return AbstractJsonToString.hashMapToArrayListJsonEntities(entities.Map::fromHashMap,cdb.executeSelectQuery(config.database.queries.ExternalMap.MySQL_QUERY_GET_ALL_CITY_EXTERNAL_FULL_MAP, whereVal));
		default:
			throw new ResponseException(Packet.ACTION_TYPE.FATAL_ERROR);
		}
	}

	/* (non-Javadoc)
	 * @see server.database.requestHandler.AbstractRequest#deleteAction(entities.Packet)
	 */
	@Override
	protected boolean deleteAction(Packet<?> pkt)  throws IOException, SQLException, ResponseException {
		ArrayList<String> whereVal = null;
		ArrayList<String> data = pkt.objectTranslation();
		switch (pkt.getSub_action()) {
		case config.packetTransfer.actions.EditCatalog.SUB_ACTION_DELETE_POI_FROM_MAP:
			entities.PlaceOfInterestMap poimap = AbstractJsonToString.toObject(entities.PlaceOfInterestMap.class, data.get(0));
			whereVal = AbstractRequest.createArrayList(poimap.getCity().getCountry().getShortName(),
					poimap.getCity().getName(), poimap.getCity().getVersion().toString(), poimap.getName(),
					poimap.getMap().getName());
			return cdb.executeQuery(config.database.queries.PlaceOfInterestMap.MySQL_QUERY_DELETE_POI_FROM_MAP, whereVal);
		case config.packetTransfer.actions.EditCatalog.SUB_ACTION_DELETE_POI_FROM_CITY:
			entities.PlaceOfInterest poi = AbstractJsonToString.toObject(entities.PlaceOfInterest.class, data.get(0));
			whereVal = AbstractRequest.createArrayList(poi.getCity().getCountry().getShortName(),
					poi.getCity().getName(), poi.getCity().getVersion().toString(), poi.getName());
			return cdb.executeQuery(config.database.queries.PlaceOfInterest.MySQL_QUERY_DELETE_POI_FROM_CITY, whereVal);
		case config.packetTransfer.actions.EditCatalog.SUB_ACTION_DELETE_TOUR_FROM_CITY:
			entities.Tour tour = AbstractJsonToString.toObject(entities.Tour.class, data.get(0));
			whereVal = AbstractRequest.createArrayList(tour.getCity().getName(),
					tour.getCity().getCountry().getShortName(),
					tour.getCity().getVersion().toString(),
					tour.getName());
			return cdb.executeQuery(config.database.queries.Tour.MySQL_QUERY_DELETE_TOUR_FROM_CITY, whereVal);
		case config.packetTransfer.actions.EditCatalog.SUB_ACTION_DELETE_CATEGORY:
			entities.Category category = AbstractJsonToString.toObject(entities.Category.class, data.get(0));
			whereVal = AbstractRequest.createArrayList(category.getCategory());
			return cdb.executeQuery(config.database.queries.Category.MySQL_QUERY_DELETE_CATEGORY, whereVal);
		case config.packetTransfer.actions.EditCatalog.SUB_ACTION_DELETE_MAP_FROM_CITY:
			entities.Map map = AbstractJsonToString.toObject(entities.Map.class, data.get(0));
			whereVal = AbstractRequest.createArrayList(map.getCity().getName(),
					map.getCity().getCountry().getShortName(),
					map.getCity().getVersion().toString(),
					map.getName());
			return cdb.executeQuery(config.database.queries.Map.MySQL_QUERY_DELETE_MAP , whereVal);
		default:
			throw new ResponseException(Packet.ACTION_TYPE.FATAL_ERROR);
		}
	}

	/* (non-Javadoc)
	 * @see server.database.requestHandler.AbstractRequest#addAction(entities.Packet)
	 */
	@Override
	protected boolean addAction(Packet<?> pkt) throws IOException, SQLException, ResponseException {
		ArrayList<String> whereVal = null;
		ArrayList<String> data = pkt.objectTranslation();
		switch (pkt.getSub_action()) {
		case config.packetTransfer.actions.EditCatalog.SUB_ACTION_ADD_POI_TO_MAP:
			entities.PlaceOfInterestMap poimap = AbstractJsonToString.toObject(entities.PlaceOfInterestMap.class, data.get(0));
			whereVal = AbstractRequest.createArrayList(poimap.getMap().getCity().getCountry().getShortName(),
					poimap.getMap().getCity().getName(), poimap.getMap().getCity().getVersion().toString(),
					poimap.getName(), poimap.getMap().getName(), String.valueOf(poimap.getCordinates().getX()),
					String.valueOf(poimap.getCordinates().getY()));
			return cdb.executeQuery(config.database.queries.PlaceOfInterestMap.MySQL_QUERY_ADD_POI_TO_MAP, whereVal);
		case config.packetTransfer.actions.EditCatalog.SUB_ACTION_ADD_CATEGORY:
			entities.Category category = AbstractJsonToString.toObject(entities.Category.class, data.get(0));
			whereVal = AbstractRequest.createArrayList(category.getCategory());
			return cdb.executeQuery(config.database.queries.Category.MySQL_QUERY_ADD_CATEGORY, whereVal);
		case config.packetTransfer.actions.EditCatalog.SUB_ACTION_ADD_POI_TO_CITY:
			entities.PlaceOfInterest poi = AbstractJsonToString.toObject(entities.PlaceOfInterest.class, data.get(0));
			whereVal = AbstractRequest.createArrayList(String.valueOf(poi.isAccessible() ? 1 : 0),
					poi.getCity().getName(), poi.getCity().getCountry().getShortName(),
					poi.getCity().getVersion().toString(), poi.getDescription(), poi.getName(),
					String.valueOf(poi.getCategory().getCategory()));
			return cdb.executeQuery(config.database.queries.PlaceOfInterest.MySQL_QUERY_ADD_POI_TO_CITY, whereVal);
		case config.packetTransfer.actions.EditCatalog.SUB_ACTION_ADD_CITY_TO_COUNTRY:
			entities.City city = AbstractJsonToString.toObject(entities.City.class, data.get(0));
			whereVal = AbstractRequest.createArrayList(city.getCountry().getShortName(), city.getName(),
					city.getVersion().toString(), city.getDescription());
			return cdb.executeQuery(config.database.queries.City.MySQL_QUERY_ADD_CITY_TO_COUNTRY, whereVal);
		case config.packetTransfer.actions.EditCatalog.SUB_ACTION_ADD_EXRERNAL_MAP_TO_CITY:
			entities.Map map = AbstractJsonToString.toObject(entities.Map.class, data.get(0));
			whereVal = AbstractRequest.createArrayList(map.getCity().getCountry().getShortName(),
					map.getCity().getName(),
					map.getName());
			return cdb.executeQuery(config.database.queries.Map.MySQL_QUERY_ADD_EXTERNAL_MAP_TO_CITY, whereVal);
		case config.packetTransfer.actions.EditCatalog.SUB_ACTION_ADD_TOUR_TO_CITY:
			ArrayList<entities.PlaceOfInterestTour> poistour = new ArrayList<entities.PlaceOfInterestTour>();
			for (String poistourJson : data)
				poistour.add(AbstractJsonToString.toObject(entities.PlaceOfInterestTour.class, poistourJson));
			whereVal = new ArrayList<String>();
			String query = config.database.queries.Tour.MySQL_QUERY_ADD_TOUR_PARTIAL
					+ String.join(", ", Collections.nCopies(poistour.size(), "(?,?,?,?,?,?,?,?,?)"));
			for (entities.PlaceOfInterestTour poitour : poistour) {
				whereVal.add(poitour.getCity().getName());
				whereVal.add(poitour.getCity().getCountry().getShortName());
				whereVal.add(poitour.getCity().getVersion().toString());
				whereVal.add(poitour.getTour().getDescription());
				whereVal.add(String.valueOf(poitour.getIndex()));
				whereVal.add(poitour.getName());
				whereVal.add(poitour.getTour().getName());
				whereVal.add(String.valueOf(poitour.getSecTime()));
				whereVal.add(String.valueOf(poitour.getTour().isRecommended() ? 1 : 0));
			}
			return cdb.executeQuery(query, whereVal);
		case config.packetTransfer.actions.EditCatalog.SUB_ACTION_ADD_COLLECTION_VERSION_CITY_REQUEST:
			entities.VersionRequest vr = AbstractJsonToString.toObject(entities.VersionRequest.class, data.get(0));
			whereVal = AbstractRequest.createArrayList(vr.getCity().getCountry().getShortName(),
					vr.getCity().getName(),
					vr.getCity().getVersion().toString(),
					vr.getUser().getUsername(),
					vr.getCity().getCountry().getShortName(),
					vr.getCity().getName());
			
			if(!cdb.executeQuery(config.database.queries.VersionRequest.MySQL_QUERY_ADD_VERSION_REQUEST , whereVal))
				return false;
			
			whereVal = AbstractRequest.createArrayList(
					vr.getCity().getVersion().toString(),vr.getCity().getCountry().getShortName(),vr.getCity().getName(),config.GCM.CLIENT_DEFAULT_EDIT_CATALOG_VERSION,
					vr.getCity().getVersion().toString(),vr.getCity().getCountry().getShortName(),vr.getCity().getName(),config.GCM.CLIENT_DEFAULT_EDIT_CATALOG_VERSION,
					vr.getCity().getVersion().toString(),vr.getCity().getCountry().getShortName(),vr.getCity().getName(),config.GCM.CLIENT_DEFAULT_EDIT_CATALOG_VERSION,
					vr.getCity().getVersion().toString(),vr.getCity().getCountry().getShortName(),vr.getCity().getName(),config.GCM.CLIENT_DEFAULT_EDIT_CATALOG_VERSION);
					
			cdb.executeQuery(String.format("%s; %s; %s; %s;", config.database.queries.PlaceOfInterest.MySQL_QUERY_ADD_POIS_NEW_COLLECTION_VERSION,
					config.database.queries.Tour.MySQL_QUERY_ADD_TOURS_NEW_COLLECTION_VERSION,
					config.database.queries.Map.MySQL_QUERY_ADD_MAPS_NEW_COLLECTION_VERSION,
					config.database.queries.PlaceOfInterestMap.MySQL_QUERY_ADD_POISMAP_NEW_COLLECTION_VERSION) , whereVal);
			
			ArrayList<User> user = AbstractJsonToString.hashMapToArrayListEntities(entities.User::fromHashMap, cdb.executeSelectQuery(config.database.queries.User.MySQL_QUERY_GET_USER_BY_PERMISSION,
					AbstractRequest.createArrayList(String.valueOf(entities.Permission.Role.PRODUCTMANAGER.getValue()))));

			for(User userto : user) {
				if(!vr.getUser().equals(userto)) {
					ArrayList<String> whereValMessage = new ArrayList<String>();
					whereValMessage.add(vr.getUser().getUsername());
					whereValMessage.add(userto.getUsername());
					whereValMessage.add("I added a request to update version collection");
					whereValMessage.add(String.format("Hello %s,\r\n" 
							+ "I added a request to update a version collection of %s city to %s,\r\n"
							+ "I'm waiting for your response",
							userto.getUsername(),
							vr.getCity().getName(), vr.getCity().getVersion()));
					cdb.executeQuery(config.database.queries.Message.MySQL_QUERY_ADD_NOTIFICATION, whereValMessage);
				}
			}
				
			return true;
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
		ArrayList<String> data = pkt.objectTranslation();
		switch (pkt.getSub_action()) {
		case config.packetTransfer.actions.EditCatalog.SUB_ACTION_UPDATE_POI_OF_CITY:
			entities.PlaceOfInterest old_poi = AbstractJsonToString.toObject(entities.PlaceOfInterest.class, data.get(0));
			entities.PlaceOfInterest new_poi = AbstractJsonToString.toObject(entities.PlaceOfInterest.class, data.get(1));
			whereVal = AbstractRequest.createArrayList(new_poi.getName(),
					String.valueOf(new_poi.isAccessible() ? 1 : 0), new_poi.getDescription(),
					String.valueOf(new_poi.getCategory().getCategory()), old_poi.getCity().getCountry().getShortName(),
					old_poi.getCity().getName(), old_poi.getCity().getVersion().toString(), old_poi.getName());
			return cdb.executeQuery(config.database.queries.PlaceOfInterest.MySQL_QUERY_UPDATE_POI_OF_CITY, whereVal);
		case config.packetTransfer.actions.EditCatalog.SUB_ACTION_UPDATE_TOUR_WITHOUT_POIS:
			entities.Tour old_tour = AbstractJsonToString.toObject(entities.Tour.class, data.get(0));
			entities.Tour new_tour = AbstractJsonToString.toObject(entities.Tour.class, data.get(1));
			whereVal = AbstractRequest.createArrayList(new_tour.getName(),
					new_tour.getDescription(),
					String.valueOf(new_tour.isRecommended() ? 1 : 0),
					old_tour.getCity().getCountry().getShortName(),
					old_tour.getCity().getName(),
					old_tour.getCity().getVersion().toString(),
					old_tour.getName());
			return cdb.executeQuery(config.database.queries.Tour.MySQL_QUERY_UPDATE_TOUR_WITHOUT_POIS, whereVal);
		case config.packetTransfer.actions.EditCatalog.SUB_ACTION_UPDATE_MAP_OF_CITY:
			entities.Map old_map = AbstractJsonToString.toObject(entities.Map.class, data.get(0));
			entities.Map new_map = AbstractJsonToString.toObject(entities.Map.class, data.get(1));
			whereVal = AbstractRequest.createArrayList(new_map.getName(),
					new_map.getMapVer().toString(),
					new_map.getDescription(), new_map.getBase64_image(),
					old_map.getCity().getCountry().getShortName(),old_map.getCity().getName(),
					old_map.getCity().getVersion().toString(), old_map.getName());
			return cdb.executeQuery(config.database.queries.Map.MySQL_QUERY_UPDATE_MAP, whereVal);
		case config.packetTransfer.actions.EditCatalog.SUB_ACTION_UPDATE_TOUR:	
			ArrayList<entities.PlaceOfInterestTour> poistour = new ArrayList<entities.PlaceOfInterestTour>();
			for (String poistourJson : data)
				poistour.add(AbstractJsonToString.toObject(entities.PlaceOfInterestTour.class, poistourJson));
			whereVal = AbstractRequest.createArrayList(poistour.get(0).getTour().getCity().getName(),
					poistour.get(0).getTour().getCity().getCountry().getShortName(),
					poistour.get(0).getTour().getCity().getVersion().toString(),
					poistour.get(0).getTour().getName());

			String query2 = config.database.queries.Tour.MySQL_QUERY_ADD_TOUR_PARTIAL + String.join(", ", Collections.nCopies(poistour.size(), "(?,?,?,?,?,?,?,?,?)"));
			
			for (entities.PlaceOfInterestTour poitour : poistour) {
				whereVal.add(poitour.getTour().getCity().getName());
				whereVal.add(poitour.getTour().getCity().getCountry().getShortName());
				whereVal.add(poitour.getTour().getCity().getVersion().toString());
				whereVal.add(poitour.getTour().getDescription());
				whereVal.add(String.valueOf(poitour.getIndex()));
				whereVal.add(poitour.getName());
				whereVal.add(poitour.getTour().getName());
				whereVal.add(String.valueOf(poitour.getSecTime()));
				whereVal.add(String.valueOf(poitour.getTour().isRecommended() ? 1 : 0));
			}
			return cdb.executeQuery(String.format("%s; %s;", config.database.queries.Tour.MySQL_QUERY_DELETE_TOUR_FROM_CITY, query2), whereVal);
		case config.packetTransfer.actions.EditCatalog.SUB_ACTION_UPDATE_CATEGORY:
			entities.Category old_category = AbstractJsonToString.toObject(entities.Category.class, data.get(0));
			entities.Category new_category = AbstractJsonToString.toObject(entities.Category.class, data.get(1));
			whereVal = AbstractRequest.createArrayList(new_category.getCategory(), old_category.getCategory());
			return cdb.executeQuery(config.database.queries.Category.MySQL_QUERY_UPDATE_CATEGORY, whereVal);
		case config.packetTransfer.actions.EditCatalog.SUB_ACTION_UPDATE_CITY_OF_COUNTRY:
			entities.City old_city = AbstractJsonToString.toObject(entities.City.class, data.get(0));
			entities.City new_city = AbstractJsonToString.toObject(entities.City.class, data.get(1));
			whereVal = AbstractRequest.createArrayList(new_city.getName(),
					new_city.getDescription(),
					old_city.getName(),
					old_city.getCountry().getShortName());
			return cdb.executeQuery(config.database.queries.City.MySQL_QUERY_UPDATE_CITY, whereVal);
		default:
			throw new ResponseException(Packet.ACTION_TYPE.FATAL_ERROR);
		}
	}
}
