/*
 * this class defines all the actions the can be done on Catalog 
 * and sends a request to the server with the corresponding packet according to the action and its type 
 * */

package client.requestHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import application.ClientMain;
import entities.Packet;


/**
 * The request handler of Catalog.
 * static class.
 */
public class Catalog {

	/**
	 * The Enum MODE.
	 */
	public static enum MODE {
	/** The edit. */
	EDIT,
	/** The guest. */
	GUEST,
	/** The purchase. */
	PURCHASE,
	/** The download. */
	DOWNLOAD,
	/** The viewrequest. */
	VIEWREQUEST,
	/** The client. */
	CLIENT
	};

	/** The mode. */
	private static MODE mode;
	
	/** The window. */
	private static String window;
	/**
	 * Override public contractor to make it static.
	 */
	private Catalog(){}
	

	/**
	 * Gets the mode.
	 *
	 * @return the mode
	 */
	public static MODE getMode() {
		return mode;
	}


	/**
	 * Sets the mode.
	 *
	 * @param mode the new mode
	 */
	public static void setMode(MODE mode) {
		Catalog.mode = mode;
		switch(mode) {
		case EDIT:
			window = config.packetTransfer.actions.EditCatalog.WINDOW;
			break;
		case GUEST:
			window = config.packetTransfer.actions.Catalog.WINDOW;
			break;
		case CLIENT:
			window = config.packetTransfer.actions.Catalog.WINDOW;
			break;
		case VIEWREQUEST:
			window = config.packetTransfer.actions.Catalog.WINDOW;
			break;
		case DOWNLOAD:
			break;
		case PURCHASE:
			break;
		default:
			break;
			
		}
	}


	/**
	 * Gets the all countries.
	 *
	 * @return the all countries
	 */
	public static void getAllCountries() {
		Packet<HashMap<String, String>> pkt;
		try {
			pkt = new Packet<HashMap<String, String>>(Packet.ACTION_TYPE.GET,
					window,"",
					config.packetTransfer.actions.Catalog.SUB_ACTION_GET_COUNTRIES,
					new HashMap<String, String>());
			ClientMain.cSrv.handleMessageFromClient(pkt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets the all maps of city.
	 *
	 * @param c the c
	 * @return the all maps of city
	 */
	public static void getAllMapsOfCity(entities.City c) {
		Packet<HashMap<String, String>> pkt;
		try {
			HashMap<String, String> hm = new HashMap<String, String>();
			hm.put(config.database.tables.columns.City.SHORTCOUNTRY, c.getCountry().getShortName());
			hm.put(config.database.tables.columns.City.NAME, c.getName());
			hm.put(config.database.tables.columns.City.MAPSCOLLECTIONVERSION, c.getVersion().toString());
			pkt = new Packet<HashMap<String, String>>(Packet.ACTION_TYPE.GET,
					window,"",
					config.packetTransfer.actions.Catalog.SUB_ACTION_GET_CITY_MAPS,
					hm);
			ClientMain.cSrv.handleMessageFromClient(pkt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	/**
	 * Gets the all maps of city toadd.
	 *
	 * @param c the c
	 * @return the all maps of city toadd
	 */
	///
	public static void getAllMapsOfCity_toadd(entities.City c) {
		Packet<HashMap<String, String>> pkt;
		try {
			HashMap<String, String> hm = new HashMap<String, String>();
			hm.put(config.database.tables.columns.City.SHORTCOUNTRY, c.getCountry().getShortName());
			hm.put(config.database.tables.columns.City.NAME, c.getName());
			hm.put(config.database.tables.columns.City.MAPSCOLLECTIONVERSION, c.getVersion().toString());
			pkt = new Packet<HashMap<String, String>>(Packet.ACTION_TYPE.GET,
					window,config.packetTransfer.actions.EditCatalog.SUB_WINDOW_EDIT_MAP,
					config.packetTransfer.actions.Catalog.SUB_ACTION_GET_CITY_MAPS,
					hm);
			ClientMain.cSrv.handleMessageFromClient(pkt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	/**
	 * Gets the full map of city.
	 *
	 * @param map the map
	 * @param purchase the purchase
	 * @return the full map of city
	 */
	public static void getFullMapOfCity(entities.Map map,entities.Purchase purchase) {
		Packet<HashMap<String, String>> pkt;
		try {
			
			HashMap<String, String> hm = new HashMap<String, String>();
			hm.put(config.database.tables.columns.Map.SHORTCOUNTRY, map.getCity().getCountry().getShortName());
			hm.put(config.database.tables.columns.Map.CITY,map.getCity().getName());
			hm.put(config.database.tables.columns.Map.COLLECTION_VERSION, map.getCity().getVersion().toString());
			hm.put(config.database.tables.columns.Map.NAME, map.getName());
			hm.put(config.database.tables.columns.Purchase.USER, purchase.getUser().getUsername());
			hm.put(config.database.tables.columns.Purchase.ID, String.valueOf(purchase.getId()));
			
			pkt = new Packet<HashMap<String, String>>(Packet.ACTION_TYPE.GET,
					window,"",
					config.packetTransfer.actions.Catalog.SUB_ACTION_GET_FULL_MAP_OF_CITY,
					hm);
			ClientMain.cSrv.handleMessageFromClient(pkt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets the full map of city.
	 *
	 * @param map the map
	 * @return the full map of city
	 */
	public static void getFullMapOfCity(entities.Map map) {
		Packet<HashMap<String, String>> pkt;
		try {
			
			HashMap<String, String> hm = new HashMap<String, String>();
			hm.put(config.database.tables.columns.Map.SHORTCOUNTRY, map.getCity().getCountry().getShortName());
			hm.put(config.database.tables.columns.Map.CITY,map.getCity().getName());
			hm.put(config.database.tables.columns.Map.COLLECTION_VERSION, map.getCity().getVersion().toString());
			hm.put(config.database.tables.columns.Map.NAME, map.getName());
			
			pkt = new Packet<HashMap<String, String>>(Packet.ACTION_TYPE.GET,
					window,"",
					config.packetTransfer.actions.Catalog.SUB_ACTION_GET_FULL_MAP_OF_CITY,
					hm);
			ClientMain.cSrv.handleMessageFromClient(pkt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets the full map of city to view.
	 *
	 * @param map the map
	 * @return the full map of city to view
	 */
	public static void getFullMapOfCity_toView(entities.Map map) {
		Packet<HashMap<String, String>> pkt;
		try {
			
			HashMap<String, String> hm = new HashMap<String, String>();
			hm.put(config.database.tables.columns.Map.SHORTCOUNTRY, map.getCity().getCountry().getShortName());
			hm.put(config.database.tables.columns.Map.CITY,map.getCity().getName());
			hm.put(config.database.tables.columns.Map.NAME, map.getName());
			
			pkt = new Packet<HashMap<String, String>>(Packet.ACTION_TYPE.GET,
					window,config.packetTransfer.actions.EditCatalog.SUB_WINDOW_EDIT_MAP,
					config.packetTransfer.actions.EditCatalog.SUB_ACTION_GET_EXTERNAL_FULL_MAP,
					hm);
			ClientMain.cSrv.handleMessageFromClient(pkt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	/**
	 * Gets the cities of country.
	 *
	 * @param short_country the short country
	 * @return the cities of country
	 */
	public static void getCitiesOfCountry(String short_country) {
		Packet<HashMap<String, String>> pkt;
		try {
			HashMap<String, String> hm = new HashMap<String, String>();
			hm.put(config.database.tables.columns.Country.SHORT, short_country);
			if(mode == MODE.EDIT)
				hm.put(config.database.tables.columns.City.MAPSCOLLECTIONVERSION, "0.0");
			pkt = new Packet<HashMap<String, String>>(Packet.ACTION_TYPE.GET,
					window,"",
					config.packetTransfer.actions.Catalog.SUB_ACTION_GET_COUNTRY_CITIES_NAMES,
					hm);
			ClientMain.cSrv.handleMessageFromClient(pkt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets the all recommended tours.
	 *
	 * @param c the c
	 * @return the all recommended tours
	 */
	public static void getAllRecommendedTours(entities.City c) {
		Packet<HashMap<String, String>> pkt;
		try {
			HashMap<String, String> hm = new HashMap<String, String>();
			hm.put(config.database.tables.columns.City.SHORTCOUNTRY, c.getCountry().getShortName());
			hm.put(config.database.tables.columns.City.NAME, c.getName());
			hm.put(config.database.tables.columns.City.MAPSCOLLECTIONVERSION, c.getVersion().toString());
			pkt = new Packet<HashMap<String, String>>(Packet.ACTION_TYPE.GET,
					window,"",
					config.packetTransfer.actions.Catalog.SUB_ACTION_GET_RECOMMENDED_TOURS_OF_CITY,
					hm);
			ClientMain.cSrv.handleMessageFromClient(pkt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets the all POIOF map.
	 *
	 * @param map the map
	 * @return the all POIOF map
	 */
	public static void getAllPOIOFMap(entities.Map map) {
		Packet<HashMap<String, String>> pkt;
		try {
			
			HashMap<String, String> hm = new HashMap<String, String>();
			hm.put(config.database.tables.columns.Map.SHORTCOUNTRY, map.getCity().getCountry().getShortName());
			hm.put(config.database.tables.columns.Map.CITY,map.getCity().getName());
			hm.put(config.database.tables.columns.Map.COLLECTION_VERSION, map.getCity().getVersion().toString());
			hm.put(config.database.tables.columns.Map.NAME, map.getName());
			
			pkt = new Packet<HashMap<String, String>>(Packet.ACTION_TYPE.GET,
					window,"",
					config.packetTransfer.actions.Catalog.SUB_ACTION_GET_POIS_OF_MAP,
					hm);
			ClientMain.cSrv.handleMessageFromClient(pkt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets the all tours.
	 *
	 * @param c the c
	 * @return the all tours
	 */
	public static void getAllTours(entities.City c) {
		Packet<HashMap<String, String>> pkt;
		try {
			HashMap<String, String> hm = new HashMap<String, String>();
			hm.put(config.database.tables.columns.City.SHORTCOUNTRY, c.getCountry().getShortName());
			hm.put(config.database.tables.columns.City.NAME, c.getName());
			hm.put(config.database.tables.columns.City.MAPSCOLLECTIONVERSION, c.getVersion().toString());
			pkt = new Packet<HashMap<String, String>>(Packet.ACTION_TYPE.GET,
					window,"",
					config.packetTransfer.actions.Catalog.SUB_ACTION_GET_TOURS_OF_CITY,
					hm);
			ClientMain.cSrv.handleMessageFromClient(pkt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Adds the poi to map.
	 *
	 * @param poimap the poimap
	 */
	public static void add_poiToMap(entities.PlaceOfInterestMap poimap) {
		Packet<String> pkt;
		try {
			ArrayList<String> list = new ArrayList<String>();
			list.add(poimap.toString());
			pkt = new Packet<String>(Packet.ACTION_TYPE.ADD,
					window,config.packetTransfer.actions.EditCatalog.SUB_WINDOW_EDIT_MAP,
					config.packetTransfer.actions.EditCatalog.SUB_ACTION_ADD_POI_TO_MAP,
					list);
			ClientMain.cSrv.handleMessageFromClient(pkt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Delete PO ifrom MAP.
	 *
	 * @param poimap the poimap
	 */
	public static void delete_POIfromMAP(entities.PlaceOfInterestMap poimap) {
		Packet<String> pkt;
		try {
			ArrayList<String> list = new ArrayList<String>();
			list.add(poimap.toString());
			pkt = new Packet<String>(Packet.ACTION_TYPE.DELETE,
					window,"",
					config.packetTransfer.actions.EditCatalog.SUB_ACTION_DELETE_POI_FROM_MAP,
					list);
			ClientMain.cSrv.handleMessageFromClient(pkt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets the all places.
	 *
	 * @param c the c
	 * @param sub_window the sub window
	 * @return the all places
	 */
	private static void getAllPlaces(entities.City c, String sub_window) {
		Packet<HashMap<String, String>> pkt;
		try {
			HashMap<String, String> hm = new HashMap<String, String>();
			hm.put(config.database.tables.columns.City.SHORTCOUNTRY, c.getCountry().getShortName());
			hm.put(config.database.tables.columns.City.NAME, c.getName());
			hm.put(config.database.tables.columns.City.MAPSCOLLECTIONVERSION, c.getVersion().toString());
			pkt = new Packet<HashMap<String, String>>(Packet.ACTION_TYPE.GET,
					window,sub_window,
					config.packetTransfer.actions.Catalog.SUB_ACTION_GET_POIS_OF_CITY,
					hm);
			ClientMain.cSrv.handleMessageFromClient(pkt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets the all places.
	 *
	 * @param c the c
	 * @return the all places
	 */
	public static void getAllPlaces(entities.City c) {
		getAllPlaces(c,"");
	}
	
	/**
	 * Gets the all places EDITMAP.
	 *
	 * @param c the c
	 * @return the all places EDITMAP
	 */
	public static void getAllPlaces_EDITMAP(entities.City c) {
		getAllPlaces(c,config.packetTransfer.actions.EditCatalog.SUB_WINDOW_EDIT_MAP);
	}


	/**
	 * Gets the all places tours.
	 *
	 * @param c the c
	 * @return the all places tours
	 */
	public static void getAllPlaces_tours(entities.City c) {
		Packet<HashMap<String, String>> pkt;
		try {
			HashMap<String, String> hm = new HashMap<String, String>();
			hm.put(config.database.tables.columns.City.SHORTCOUNTRY, c.getCountry().getShortName());
			hm.put(config.database.tables.columns.City.NAME, c.getName());
			hm.put(config.database.tables.columns.City.MAPSCOLLECTIONVERSION, c.getVersion().toString());
			pkt = new Packet<HashMap<String, String>>(Packet.ACTION_TYPE.GET,
					window,config.packetTransfer.actions.EditCatalog.SUB_WINDOW_EDIT_TOUR,
					config.packetTransfer.actions.Catalog.SUB_ACTION_GET_POIS_OF_CITY,
					hm);
			ClientMain.cSrv.handleMessageFromClient(pkt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Gets the all categories.
	 *
	 * @return the all categories
	 */
	public static void getAllCategories() {
		Packet<HashMap<String, String>> pkt;
		try {
			pkt = new Packet<HashMap<String, String>>(Packet.ACTION_TYPE.GET,
					window,config.packetTransfer.actions.EditCatalog.SUB_WINDOW_EDIT_POI,
					config.packetTransfer.actions.EditCatalog.SUB_ACTION_GET_ALL_CATEGORIES,
					new HashMap<String, String>());
			ClientMain.cSrv.handleMessageFromClient(pkt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Adds the category.
	 *
	 * @param categ the categ
	 */
	/*public static void getAllCategories2() {
		Packet<HashMap<String, String>> pkt;
		try {
			pkt = new Packet<HashMap<String, String>>(Packet.ACTION_TYPE.GET,
					window,"",
					config.packetTransfer.actions.EditCatalog.SUB_ACTION_GET_ALL_CATEGORIES,
					new HashMap<String, String>());
			ClientMain.cSrv.handleMessageFromClient(pkt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	public static void add_category(entities.Category categ) {
		Packet<String> pkt;
		try {
			ArrayList<String> list = new ArrayList<String>();
			list.add(categ.toString());
			pkt = new Packet<String>(Packet.ACTION_TYPE.ADD,
					window,config.packetTransfer.actions.EditCatalog.SUB_WINDOW_EDIT_POI,
					config.packetTransfer.actions.EditCatalog.SUB_ACTION_ADD_CATEGORY,
					list);
			ClientMain.cSrv.handleMessageFromClient(pkt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Update category.
	 *
	 * @param oldcateg the oldcateg
	 * @param newcateg the newcateg
	 */
	public static void update_category(entities.Category oldcateg,entities.Category newcateg) {
		Packet<String> pkt;
		try {
			ArrayList<String> list = new ArrayList<String>();
			list.add(oldcateg.toString());
			list.add(newcateg.toString());
			pkt = new Packet<String>(Packet.ACTION_TYPE.UPDATE,
					window,config.packetTransfer.actions.EditCatalog.SUB_WINDOW_EDIT_POI,
					config.packetTransfer.actions.EditCatalog.SUB_ACTION_UPDATE_CATEGORY,
					list);
			ClientMain.cSrv.handleMessageFromClient(pkt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Delete category.
	 *
	 * @param categ the categ
	 */
	public static void delete_category(entities.Category categ) {
		Packet<String> pkt;
		try {
			ArrayList<String> list = new ArrayList<String>();
			list.add(categ.toString());
			pkt = new Packet<String>(Packet.ACTION_TYPE.DELETE,
					window,config.packetTransfer.actions.EditCatalog.SUB_WINDOW_EDIT_POI,
					config.packetTransfer.actions.EditCatalog.SUB_ACTION_DELETE_CATEGORY,
					list);
			ClientMain.cSrv.handleMessageFromClient(pkt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Adds the POI city.
	 *
	 * @param place the place
	 */
	public static void AddPOICity(entities.PlaceOfInterest place) {
		Packet<String> pkt;
		try {
			ArrayList<String> list = new ArrayList<String>();
			list.add(place.toString());
			pkt = new Packet<String>(Packet.ACTION_TYPE.ADD,
					window,config.packetTransfer.actions.EditCatalog.SUB_WINDOW_EDIT_POI,
					config.packetTransfer.actions.EditCatalog.SUB_ACTION_ADD_POI_TO_CITY,
					list);
			ClientMain.cSrv.handleMessageFromClient(pkt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Delete POI city.
	 *
	 * @param place the place
	 */
	public static void DeletePOICity(entities.PlaceOfInterest place) {
		Packet<String> pkt;
		try {
			ArrayList<String> list = new ArrayList<String>();
			list.add(place.toString());
			pkt = new Packet<String>(Packet.ACTION_TYPE.DELETE,
					window,config.packetTransfer.actions.EditCatalog.SUB_WINDOW_EDIT_POI,
					config.packetTransfer.actions.EditCatalog.SUB_ACTION_DELETE_POI_FROM_CITY,
					list);
			ClientMain.cSrv.handleMessageFromClient(pkt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Update POI city.
	 *
	 * @param oldplace the oldplace
	 * @param newplace the newplace
	 */
	public static void UpdatePOICity(entities.PlaceOfInterest oldplace,entities.PlaceOfInterest newplace) {
		Packet<String> pkt;
		try {
			ArrayList<String> list = new ArrayList<String>();
			list.add(oldplace.toString());
			list.add(newplace.toString());
			pkt = new Packet<String>(Packet.ACTION_TYPE.UPDATE,
					window,config.packetTransfer.actions.EditCatalog.SUB_WINDOW_EDIT_POI,
					config.packetTransfer.actions.EditCatalog.SUB_ACTION_UPDATE_POI_OF_CITY,
					list);
			ClientMain.cSrv.handleMessageFromClient(pkt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Adds the city.
	 *
	 * @param city the city
	 */
	public static void AddCity(entities.City city) {
		Packet<String> pkt;
		try {
			ArrayList<String> list = new ArrayList<String>();
			list.add(city.toString());
			pkt = new Packet<String>(Packet.ACTION_TYPE.ADD,
					window,"",
					config.packetTransfer.actions.EditCatalog.SUB_ACTION_ADD_CITY_TO_COUNTRY,
					list);
			ClientMain.cSrv.handleMessageFromClient(pkt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Edits the city.
	 *
	 * @param oldcity the oldcity
	 * @param newcity the newcity
	 */
	public static void EditCity(entities.City oldcity,entities.City newcity) {
		Packet<String> pkt;
		try {
			ArrayList<String> list = new ArrayList<String>();
			list.add(oldcity.toString());
			list.add(newcity.toString());
			pkt = new Packet<String>(Packet.ACTION_TYPE.UPDATE,
					window,"",
					config.packetTransfer.actions.EditCatalog.SUB_ACTION_UPDATE_CITY_OF_COUNTRY,
					list);
			ClientMain.cSrv.handleMessageFromClient(pkt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets the all places in tours.
	 *
	 * @param tour the tour
	 * @return the all places in tours
	 */
	public static void getAllPlaces_in_tours(entities.Tour tour) {
		Packet<HashMap<String, String>> pkt;
		try {
			HashMap<String, String> hm = new HashMap<String, String>();
			hm.put(config.database.tables.columns.Tour.SHORTCOUNTRY, tour.getCity().getCountry().getShortName());
			hm.put(config.database.tables.columns.Tour.CITY, tour.getCity().getName());
			hm.put(config.database.tables.columns.Tour.COLLECTION_VERSION, tour.getCity().getVersion().toString());
			hm.put(config.database.tables.columns.Tour.NAME, tour.getName());
			
			pkt = new Packet<HashMap<String, String>>(Packet.ACTION_TYPE.GET,
					window,"",
					config.packetTransfer.actions.Catalog.SUB_ACTION_GET_POIS_OF_TOUR,
					hm);
			ClientMain.cSrv.handleMessageFromClient(pkt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets the all purchases in country for user.
	 *
	 * @param shortname the shortname
	 * @param username the username
	 * @return the all purchases in country for user
	 */
	public static void get_all_purchases_in_country_for_user(String shortname,String username) {
		Packet<HashMap<String, String>> pkt;
		try {
			HashMap<String, String> hm = new HashMap<String, String>();
			hm.put(config.database.tables.columns.Purchase.SHORTCOUNTRY, shortname);
			hm.put(config.database.tables.columns.Purchase.USER, username);
			
			pkt = new Packet<HashMap<String, String>>(Packet.ACTION_TYPE.GET,
					window,"",
					config.packetTransfer.actions.Catalog.SUB_ACTION_GET_PURCHASES_BY_COUNTRY_USER,
					hm);
			ClientMain.cSrv.handleMessageFromClient(pkt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Update tours soft.
	 *
	 * @param oldtour the oldtour
	 * @param newtour the newtour
	 */
	public static void UpdateTours_soft(entities.Tour oldtour, entities.Tour newtour) {
		Packet<String> pkt;
		try {
			ArrayList<String> list = new ArrayList<String>();
			list.add(oldtour.toString());
			list.add(newtour.toString());
			pkt = new Packet<String>(Packet.ACTION_TYPE.UPDATE,
					window,config.packetTransfer.actions.EditCatalog.SUB_WINDOW_EDIT_TOUR,
					config.packetTransfer.actions.EditCatalog.SUB_ACTION_UPDATE_TOUR_WITHOUT_POIS,
					list);
			ClientMain.cSrv.handleMessageFromClient(pkt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Update tours hard.
	 *
	 * @param placestours the placestours
	 */
	public static void UpdateTours_hard(ArrayList<entities.PlaceOfInterestTour> placestours) {
		Packet<String> pkt;
		try {
			ArrayList<String> list = new ArrayList<String>();
			for(entities.PlaceOfInterestTour placeintour : placestours) {
				list.add(placeintour.toString());
			}
			pkt = new Packet<String>(Packet.ACTION_TYPE.UPDATE,
					window,config.packetTransfer.actions.EditCatalog.SUB_WINDOW_EDIT_TOUR,
					config.packetTransfer.actions.EditCatalog.SUB_ACTION_UPDATE_TOUR,
					list);
			ClientMain.cSrv.handleMessageFromClient(pkt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Adds the tour.
	 *
	 * @param placestours the placestours
	 */
	public static void AddTour(ArrayList<entities.PlaceOfInterestTour> placestours) {
		Packet<String> pkt;
		try {
			ArrayList<String> list = new ArrayList<String>();
			for(entities.PlaceOfInterestTour placeintour : placestours) {
				list.add(placeintour.toString());
			}
			pkt = new Packet<String>(Packet.ACTION_TYPE.ADD,
					window,config.packetTransfer.actions.EditCatalog.SUB_WINDOW_EDIT_TOUR,
					config.packetTransfer.actions.EditCatalog.SUB_ACTION_ADD_TOUR_TO_CITY,
					list);
			ClientMain.cSrv.handleMessageFromClient(pkt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Delete tour.
	 *
	 * @param tour the tour
	 */
	public static void DeleteTour(entities.Tour tour) {
		Packet<String> pkt;
		try {
			ArrayList<String> list = new ArrayList<String>();
			list.add(tour.toString());
			pkt = new Packet<String>(Packet.ACTION_TYPE.DELETE,
					window,config.packetTransfer.actions.EditCatalog.SUB_WINDOW_EDIT_TOUR,
					config.packetTransfer.actions.EditCatalog.SUB_ACTION_DELETE_TOUR_FROM_CITY,
					list);
			ClientMain.cSrv.handleMessageFromClient(pkt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Push maps collection.
	 *
	 * @param ver the ver
	 */
	public static void PushMapsCollection(entities.VersionRequest ver) {
		Packet<String> pkt;
		try {
			ArrayList<String> list = new ArrayList<String>();
			list.add(ver.toString());
			pkt = new Packet<String>(Packet.ACTION_TYPE.ADD,
					window,"",
					config.packetTransfer.actions.EditCatalog.SUB_ACTION_ADD_COLLECTION_VERSION_CITY_REQUEST,
					list);
			ClientMain.cSrv.handleMessageFromClient(pkt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets the last collection version.
	 *
	 * @param shortCountry the short country
	 * @param city the city
	 */
	public static void GetLastCollectionVersion(String shortCountry, String city) {
		Packet<HashMap<String, String>> pkt;
		try {
			HashMap<String, String> hm = new HashMap<String, String>();
			hm.put(config.database.tables.columns.City.SHORTCOUNTRY, shortCountry);
			hm.put(config.database.tables.columns.City.NAME, city);
			pkt = new Packet<HashMap<String, String>>(Packet.ACTION_TYPE.GET,
					window,"",
					config.packetTransfer.actions.EditCatalog.SUB_ACTION_GET_LAST_COLLECTION_VERSION,
					hm);
			ClientMain.cSrv.handleMessageFromClient(pkt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets the externalmaps.
	 *
	 * @param shortCountry the short country
	 * @param city the city
	 */
	public static void GetExternalmaps(String shortCountry, String city) {
		Packet<HashMap<String, String>> pkt;
		try {
			HashMap<String, String> hm = new HashMap<String, String>();
			hm.put(config.database.tables.columns.City.SHORTCOUNTRY, shortCountry);
			hm.put(config.database.tables.columns.City.NAME, city);
			pkt = new Packet<HashMap<String, String>>(Packet.ACTION_TYPE.GET,
					window,config.packetTransfer.actions.EditCatalog.SUB_WINDOW_EDIT_MAP,
					config.packetTransfer.actions.EditCatalog.SUB_ACTION_GET_ALL_EXTERNAL_MAPS_BY_CITY,
					hm);
			ClientMain.cSrv.handleMessageFromClient(pkt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Adds the externalmap.
	 *
	 * @param map the map
	 */
	public static void addExternalmap(entities.Map map) {
		Packet<String> pkt;
		try {
			ArrayList<String> list = new ArrayList<String>();
			list.add(map.toString());
			pkt = new Packet<String>(Packet.ACTION_TYPE.ADD,
					window,config.packetTransfer.actions.EditCatalog.SUB_WINDOW_EDIT_MAP,
					config.packetTransfer.actions.EditCatalog.SUB_ACTION_ADD_EXRERNAL_MAP_TO_CITY,
					list);
			ClientMain.cSrv.handleMessageFromClient(pkt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Delete map from city.
	 *
	 * @param delmap the delmap
	 */
	public static void delete_map_from_city(entities.Map delmap) {
		Packet<String> pkt;
		try {
			ArrayList<String> list = new ArrayList<String>();
			list.add(delmap.toString());
			pkt = new Packet<String>(Packet.ACTION_TYPE.DELETE,
					window,config.packetTransfer.actions.EditCatalog.SUB_WINDOW_EDIT_MAP,
					config.packetTransfer.actions.EditCatalog.SUB_ACTION_DELETE_MAP_FROM_CITY,
					list);
			ClientMain.cSrv.handleMessageFromClient(pkt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
