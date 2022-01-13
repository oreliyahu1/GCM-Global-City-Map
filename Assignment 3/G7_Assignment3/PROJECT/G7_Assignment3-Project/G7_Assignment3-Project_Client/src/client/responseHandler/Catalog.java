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
 * The response handler of catalog.
 * Static class 
 */
public class Catalog extends AbstractResponse{
	
	/**
	 * Instantiates a new catalog.
	 *
	 * @param window the window
	 */
	public Catalog(String window) {
		super(window);
	}

	/* (non-Javadoc)
	 * @see client.responseHandler.AbstractResponse#responseHandler(entities.Packet)
	 */
	@Override
	protected boolean responseHandler(Packet<?> pkt) throws IOException {
		ArrayList<String> res;
		switch(pkt.getSub_action()) {
	
			case config.packetTransfer.actions.Catalog.SUB_ACTION_GET_COUNTRIES:
				res = pkt.objectTranslation();
				ArrayList<entities.Country> resCountry = new ArrayList<entities.Country>();
				for(String json : res)
					resCountry.add(AbstractJsonToString.toObject(entities.Country.class, json));
				guiControllers.Catalog.CatalogController.instance.update_counties(resCountry);
			break;	
			case config.packetTransfer.actions.Catalog.SUB_ACTION_GET_COUNTRY_CITIES_NAMES:
				res = pkt.objectTranslation();
				ArrayList<entities.City> resCities = new ArrayList<entities.City>();
				for(String json : res)
					resCities.add(AbstractJsonToString.toObject(entities.City.class, json));
				guiControllers.Catalog.CitiesController.instance.update_cities(resCities);
			break;
			case config.packetTransfer.actions.Catalog.SUB_ACTION_GET_CITY_MAPS:
				res = pkt.objectTranslation();
				ArrayList<entities.Map> resMaps = new ArrayList<entities.Map>();
				for(String json : res)
					resMaps.add(AbstractJsonToString.toObject(entities.Map.class, json));
				guiControllers.Catalog.Selected_CityController.instance.update_maps(resMaps);
			break;
			case config.packetTransfer.actions.Catalog.SUB_ACTION_GET_RECOMMENDED_TOURS_OF_CITY:
				res = pkt.objectTranslation();
				ArrayList<entities.Tour> resRecommendedTours = new ArrayList<entities.Tour>();
				for(String json : res)
					resRecommendedTours.add(AbstractJsonToString.toObject(entities.Tour.class, json));
				guiControllers.Catalog.Selected_CityController.instance.update_recommended_tours(resRecommendedTours);
			break;
			case config.packetTransfer.actions.Catalog.SUB_ACTION_GET_POIS_OF_MAP:
				res = pkt.objectTranslation();
				ArrayList<entities.PlaceOfInterestMap> POIS_OF_MAPS = new ArrayList<entities.PlaceOfInterestMap>();
				for(String json : res)
					POIS_OF_MAPS.add(AbstractJsonToString.toObject(entities.PlaceOfInterestMap.class, json));
				guiControllers.Catalog.SelectedMap_CityController.instance.update_map(POIS_OF_MAPS);
			break;
			case config.packetTransfer.actions.Catalog.SUB_ACTION_GET_TOURS_OF_CITY:
				res = pkt.objectTranslation();
				ArrayList<entities.Tour> TOURS_OF_CITY = new ArrayList<entities.Tour>();
				for(String json : res)
					TOURS_OF_CITY.add(AbstractJsonToString.toObject(entities.Tour.class, json));
				guiControllers.Catalog.SelectedCity_ToursController.instance.update_tours(TOURS_OF_CITY);
			break;
			case config.packetTransfer.actions.Catalog.SUB_ACTION_GET_POIS_OF_CITY:
				res = pkt.objectTranslation();
				ArrayList<entities.PlaceOfInterest> POIS_OF_CITY = new ArrayList<entities.PlaceOfInterest>();
				for(String json : res)
					POIS_OF_CITY.add(AbstractJsonToString.toObject(entities.PlaceOfInterest.class, json));

				guiControllers.Catalog.SelectedCity_PlacesController.instance.update_places(POIS_OF_CITY);
			break;
			case config.packetTransfer.actions.Catalog.SUB_ACTION_GET_FULL_MAP_OF_CITY:
				res = pkt.objectTranslation();
				ArrayList<entities.Map> map = new ArrayList<entities.Map>();
				for(String json : res)
					map.add(AbstractJsonToString.toObject(entities.Map.class, json));
				guiControllers.Catalog.SelectedMap_CityController.instance.getFullMap(map.get(0));
			break;
			case config.packetTransfer.actions.Catalog.SUB_ACTION_GET_POIS_OF_TOUR:
				res = pkt.objectTranslation();
				ArrayList<entities.PlaceOfInterestTour> POIS_OF_Tour = new ArrayList<entities.PlaceOfInterestTour>();
				for(String json : res)
					POIS_OF_Tour.add(AbstractJsonToString.toObject(entities.PlaceOfInterestTour.class, json));
				guiControllers.Catalog.SelectedCity_ToursPlacesController.instance.update_places_of_tour(POIS_OF_Tour);
			break;
			case config.packetTransfer.actions.Catalog.SUB_ACTION_GET_PURCHASES_BY_COUNTRY_USER:
				res = pkt.objectTranslation();
				ArrayList<entities.Purchase> purchases = new ArrayList<entities.Purchase>();
				for(String json : res)
					purchases.add(AbstractJsonToString.toObject(entities.Purchase.class, json));
				guiControllers.Catalog.CitiesController.instance.get_all_purchases_by_username_country(purchases);
			break;
			
			
			/*case config.packetTransfer.actions.EditCatalog.SUB_ACTION_GET_ALL_CATEGORIES:
				//close window
				res = pkt.objectTranslation();
				ArrayList<entities.Category> categories = new ArrayList<entities.Category>();
				for(String json : res)
					categories.add(AbstractJsonToString.toObject(entities.Category.class, json));
				guiControllers.Catalog.SelectedMap_CityController.instance.update_categories(categories);
				break;*/
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
		alertError(pkt);
		return true;
		
	}


}
