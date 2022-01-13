/*
 * this class extends the abstract class AbstractError.
 * executing the handle function from super class , direct the program to diagnose 
 * the action type then call one from these functions : addAction ,getAction ,updateAction,deleteAction 
 * to handle errors and show message correspondingly
*/
package server.errorHandler;

import entities.Packet;

// TODO: Auto-generated Javadoc
/**
 * The Class Catalog.
 * Extends AbstractError
 * Adding execute error of action
 */
public class EditCatalog extends AbstractError {
	
	
	/**
	 * Instantiates a new edits the catalog.
	 *
	 * @param window the window
	 */
	public EditCatalog(String window) {
		super(window);
	}

	/* (non-Javadoc)
	 * @see server.errorHandler.AbstractError#getAction(entities.Packet)
	 */
	@Override
	protected Packet<String> getAction(Packet<String> pkt) {
		switch (pkt.getSub_action()) {
		case config.packetTransfer.actions.EditCatalog.SUB_ACTION_GET_COUNTRIES:
			pkt.setData(config.packetTransfer.server.errors.Catalog.SUB_ACTION_ERROR_GET);
			break;
		case config.packetTransfer.actions.EditCatalog.SUB_ACTION_GET_COUNTRY_CITIES_NAMES:
			pkt.setData(config.packetTransfer.server.errors.Catalog.SUB_ACTION_ERROR_GET);
			break;
		case config.packetTransfer.actions.EditCatalog.SUB_ACTION_GET_CITY_MAPS:
			pkt.setData(config.packetTransfer.server.errors.Catalog.SUB_ACTION_ERROR_GET_CITY_MAPS);
			break;
		case config.packetTransfer.actions.EditCatalog.SUB_ACTION_GET_RECOMMENDED_TOURS_OF_CITY:
			pkt.setData(config.packetTransfer.server.errors.Catalog.SUB_ACTION_ERROR_GET_TOURS_OF_CITY);
			break;
		case config.packetTransfer.actions.EditCatalog.SUB_ACTION_GET_TOURS_OF_CITY:
			pkt.setData(config.packetTransfer.server.errors.Catalog.SUB_ACTION_ERROR_GET_RECOMMENDED_TOURS_OF_CITY);
			break;
		case config.packetTransfer.actions.EditCatalog.SUB_ACTION_GET_POIS_OF_CITY:
			pkt.setData(config.packetTransfer.server.errors.Catalog.SUB_ACTION_ERROR_GET_POIS_OF_CITY);
			break;
		case config.packetTransfer.actions.EditCatalog.SUB_ACTION_GET_POIS_OF_MAP:
			pkt.setData(config.packetTransfer.server.errors.Catalog.SUB_ACTION_ERROR_GET_POIS_OF_MAP);
			break;
		case config.packetTransfer.actions.EditCatalog.SUB_ACTION_GET_FULL_MAP_OF_CITY:
			pkt.setData(config.packetTransfer.server.errors.Catalog.SUB_ACTION_ERROR_GET_FULL_MAP_OF_CITY);
			break;
		case config.packetTransfer.actions.EditCatalog.SUB_ACTION_GET_LAST_COLLECTION_VERSION:
			pkt.setData(config.packetTransfer.server.errors.EditCatalog.SUB_ACTION_ERROR_GET_LAST_COLLECTION_VERSION);
			break;
		case config.packetTransfer.actions.EditCatalog.SUB_ACTION_GET_ALL_CATEGORIES:
			pkt.setData(config.packetTransfer.server.errors.EditCatalog.SUB_ACTION_ERROR_GET_ALL_CATEGORIES);
			break;
		case config.packetTransfer.actions.EditCatalog.SUB_ACTION_GET_POIS_OF_TOUR :
			pkt.setData(config.packetTransfer.server.errors.EditCatalog.SUB_ACTION_ERROR_GET_POIS_OF_TOUR);
			break;
		case config.packetTransfer.actions.EditCatalog.SUB_ACTION_GET_EXTERNAL_FULL_MAP:
			pkt.setData(config.packetTransfer.server.errors.EditCatalog.SUB_ACTION_ERROR_GET_EXTERNAL_FULL_MAP);
			break;
		}
		return pkt;
	}

	/* (non-Javadoc)
	 * @see server.errorHandler.AbstractError#deleteAction(entities.Packet)
	 */
	@Override
	protected Packet<String> deleteAction(Packet<String> pkt) {
		switch (pkt.getSub_action()) {
		case config.packetTransfer.actions.EditCatalog.SUB_ACTION_DELETE_POI_FROM_MAP:
			pkt.setData(config.packetTransfer.server.errors.EditCatalog.SUB_ACTION_ERROR_DELETE_FROM_MAP);
			break;
		case config.packetTransfer.actions.EditCatalog.SUB_ACTION_DELETE_POI_FROM_CITY:
			pkt.setData(config.packetTransfer.server.errors.EditCatalog.SUB_ACTION_ERROR_DELETE_FROM_CITY);
			break;
		case config.packetTransfer.actions.EditCatalog.SUB_ACTION_DELETE_TOUR_FROM_CITY:
			pkt.setData(config.packetTransfer.server.errors.EditCatalog.SUB_ACTION_ERROR_DELETE_FROM_CITY);
			break;
		case config.packetTransfer.actions.EditCatalog.SUB_ACTION_DELETE_CATEGORY:
			pkt.setData(config.packetTransfer.server.errors.EditCatalog.SUB_ACTION_ERROR_DELETE_CATEGORY);
			break;
		case config.packetTransfer.actions.EditCatalog.SUB_ACTION_DELETE_MAP_FROM_CITY:
			pkt.setData(config.packetTransfer.server.errors.EditCatalog.SUB_ACTION_ERROR_DELETE_FROM_CITY);
			break;
		case config.packetTransfer.actions.EditCatalog.SUB_ACTION_ADD_EXRERNAL_MAP_TO_CITY:
			pkt.setData(config.packetTransfer.server.errors.EditCatalog.SUB_ACTION_ERROR_GET_ALL_EXTERNAL_MAPS_BY_CITY);
			break;		
		}
		return pkt;
	}

	/* (non-Javadoc)
	 * @see server.errorHandler.AbstractError#addAction(entities.Packet)
	 */
	@Override
	protected Packet<String> addAction(Packet<String> pkt) {
		switch (pkt.getSub_action()) {
		case config.packetTransfer.actions.EditCatalog.SUB_ACTION_ADD_POI_TO_MAP:
			pkt.setData(config.packetTransfer.server.errors.EditCatalog.SUB_ACTION_ERROR_ADD_POI_TO_MAP);
			break;
		case config.packetTransfer.actions.EditCatalog.SUB_ACTION_ADD_CATEGORY:
			pkt.setData(config.packetTransfer.server.errors.EditCatalog.SUB_ACTION_ERROR_ADD_CATEGORY);
			break;
		case config.packetTransfer.actions.EditCatalog.SUB_ACTION_ADD_POI_TO_CITY:
			pkt.setData(config.packetTransfer.server.errors.EditCatalog.SUB_ACTION_ERROR_ADD_POI_TO_CITY);
			break;
		case config.packetTransfer.actions.EditCatalog.SUB_ACTION_ADD_CITY_TO_COUNTRY:
			pkt.setData(config.packetTransfer.server.errors.EditCatalog.SUB_ACTION_ERROR_ADD_CITY_TO_COUNTRY);
			break;
		case config.packetTransfer.actions.EditCatalog.SUB_ACTION_ADD_EXRERNAL_MAP_TO_CITY:
			pkt.setData(config.packetTransfer.server.errors.EditCatalog.SUB_ACTION_ERROR_ADD_EXRERNAL_MAP_TO_CITY);
			break;
		case config.packetTransfer.actions.EditCatalog.SUB_ACTION_ADD_TOUR_TO_CITY:
			pkt.setData(config.packetTransfer.server.errors.EditCatalog.SUB_ACTION_ERROR_ADD_TOUR_TO_CITY);
			break;
		case config.packetTransfer.actions.EditCatalog.SUB_ACTION_ADD_COLLECTION_VERSION_CITY_REQUEST:
			pkt.setData(config.packetTransfer.server.errors.EditCatalog.SUB_ACTION_ERROR_ADD_COLLECTION_VERSION_CITY_REQUEST);
			break;
		}
		return pkt;
	}

	/* (non-Javadoc)
	 * @see server.errorHandler.AbstractError#updateAction(entities.Packet)
	 */
	@Override
	protected Packet<String> updateAction(Packet<String> pkt) {
		switch (pkt.getSub_action()) {
		case config.packetTransfer.actions.EditCatalog.SUB_ACTION_UPDATE_POI_OF_CITY:
			pkt.setData(config.packetTransfer.server.errors.EditCatalog.SUB_ACTION_ERROR_UPDATE_POI_OF_CITY);
			break;
		case config.packetTransfer.actions.EditCatalog.SUB_ACTION_UPDATE_TOUR_WITHOUT_POIS:
			pkt.setData(config.packetTransfer.server.errors.EditCatalog.SUB_ACTION_ERROR_UPDATE_TOUR_WITHOUT_POIS);
			break;
		case config.packetTransfer.actions.EditCatalog.SUB_ACTION_UPDATE_MAP_OF_CITY:
			pkt.setData(config.packetTransfer.server.errors.EditCatalog.SUB_ACTION_ERROR_UPDATE_MAP_OF_CITY);
			break;
		case config.packetTransfer.actions.EditCatalog.SUB_ACTION_UPDATE_TOUR:	
			pkt.setData(config.packetTransfer.server.errors.EditCatalog.SUB_ACTION_ERROR_UPDATE_TOUR);
			break;
		case config.packetTransfer.actions.EditCatalog.SUB_ACTION_UPDATE_CATEGORY:
			pkt.setData(config.packetTransfer.server.errors.EditCatalog.SUB_ACTION_ERROR_UPDATE_CATEGORY);
			break;
		case config.packetTransfer.actions.EditCatalog.SUB_ACTION_UPDATE_CITY_OF_COUNTRY:
			pkt.setData(config.packetTransfer.server.errors.EditCatalog.SUB_ACTION_ERROR_UPDATE_CITY_OF_COUNTRY);
			break;
		}
		return pkt;
	}
}
