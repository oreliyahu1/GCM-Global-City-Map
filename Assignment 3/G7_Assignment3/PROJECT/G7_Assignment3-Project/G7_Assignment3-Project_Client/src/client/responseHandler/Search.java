/*
 * this class extends AbstractResponse 
 * it defines the next action according to the response status
 */

package client.responseHandler;

import java.io.IOException;
import java.util.ArrayList;
import entities.AbstractJsonToString;
import entities.Packet;


/**
 * The response handler of Search.
 * Static class 
 */
public class Search extends AbstractResponse{
	
	/**
	 * Instantiates a new search.
	 *
	 * @param window the window
	 */
	public Search(String window) {
		super(window);
	}

	/* (non-Javadoc)
	 * @see client.responseHandler.AbstractResponse#responseHandler(entities.Packet)
	 */
	@Override
	protected boolean responseHandler(Packet<?> pkt) throws IOException {
		ArrayList<String> res;
		switch(pkt.getSub_action()) {
			case config.packetTransfer.actions.Search.SUB_ACTION_GET_SEARCH_BY_CITY_NAME:
				res = pkt.objectTranslation();
				ArrayList<entities.City> cities = new ArrayList<entities.City>();
				for(String json : res)
					cities.add(AbstractJsonToString.toObject(entities.City.class, json));
				guiControllers.Catalog.MapSearcherController.instance.get_cities_by_name(cities);
				
				
			break;
			case config.packetTransfer.actions.Search.SUB_ACTION_GET_SEARCH_BY_TEXT_MAP:
				res = pkt.objectTranslation();
				ArrayList<entities.City> cities2 = new ArrayList<entities.City>();
				for(String json : res)
					cities2.add(AbstractJsonToString.toObject(entities.City.class, json));
				guiControllers.Catalog.MapSearcherController.instance.get_cities_by_maptext(cities2);
				
				
			break;
	
			case config.packetTransfer.actions.Search.SUB_ACTION_GET_SEARCH_BY_POI:
				res = pkt.objectTranslation();
				ArrayList<entities.City> cities3 = new ArrayList<entities.City>();
				for(String json : res)
					cities3.add(AbstractJsonToString.toObject(entities.City.class, json));
				guiControllers.Catalog.MapSearcherController.instance.get_cities_by_placetext(cities3);
				
				
			break;
			case config.packetTransfer.actions.Search.SUB_ACTION_GET_SEARCH_BY_TEXT_CITY:
				res = pkt.objectTranslation();
				ArrayList<entities.City> cities4 = new ArrayList<entities.City>();
				for(String json : res)
					cities4.add(AbstractJsonToString.toObject(entities.City.class, json));
				guiControllers.Catalog.MapSearcherController.instance.get_cities_by_desc(cities4);
				
				
			break;
			default:
				return false;
		}	
		return true;
	}

	/* (non-Javadoc)
	 * @see client.responseHandler.AbstractResponse#errorHandler(entities.Packet)
	 */
	@Override
	protected boolean errorHandler(Packet<?> pkt) throws IOException {
		switch(pkt.getSub_action()) {
		case config.packetTransfer.actions.Search.SUB_ACTION_GET_SEARCH_BY_CITY_NAME:
			//error adding purchase
		break;

			default:
				alertError(pkt);
				return false;
		}
		return true;
	}


}
