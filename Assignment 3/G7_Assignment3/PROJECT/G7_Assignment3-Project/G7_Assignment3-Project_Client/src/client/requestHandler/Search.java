/*
 * this class defines all the actions the can be done on Search 
 * and sends a request to the server with the corresponding packet according to the action and its type 
 * */

package client.requestHandler;

import java.io.IOException;
import java.util.HashMap;

import application.ClientMain;
import entities.Packet;


/**
 * The request handler of advanced search.
 * static class.
 */
public class Search {

	/**
	 * Override public contractor to make it static.
	 */
	private Search(){}
	
	/**
	 * Gets the allcitites by name.
	 *
	 * @param cityname the cityname
	 * @return the allcitites by name
	 */
	public static void getallcitites_by_name(String cityname) {
		Packet<HashMap<String, String>> pkt;
		try {
			HashMap<String, String> hm = new HashMap<String, String>();
			hm.put(config.database.tables.columns.City.NAME, cityname);
			pkt = new Packet<HashMap<String, String>>(Packet.ACTION_TYPE.GET,
					config.packetTransfer.actions.Search.WINDOW,"",
					config.packetTransfer.actions.Search.SUB_ACTION_GET_SEARCH_BY_CITY_NAME,
					hm);
			ClientMain.cSrv.handleMessageFromClient(pkt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets the allcitites by map description.
	 *
	 * @param mapname the mapname
	 * @return the allcitites by map description
	 */
	public static void getallcitites_by_map_description(String mapname) {
		Packet<HashMap<String, String>> pkt;
		try {
			HashMap<String, String> hm = new HashMap<String, String>();
			hm.put(config.database.tables.columns.Map.DESCRIPTION, mapname);
			pkt = new Packet<HashMap<String, String>>(Packet.ACTION_TYPE.GET,
					config.packetTransfer.actions.Search.WINDOW,"",
					config.packetTransfer.actions.Search.SUB_ACTION_GET_SEARCH_BY_TEXT_MAP,
					hm);
			ClientMain.cSrv.handleMessageFromClient(pkt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	/**
	 * Gets the allcitites by poi description.
	 *
	 * @param poiname the poiname
	 * @return the allcitites by poi description
	 */
	public static void getallcitites_by_poi_description(String poiname) {
		Packet<HashMap<String, String>> pkt;
		try {
			HashMap<String, String> hm = new HashMap<String, String>();
			hm.put(config.database.tables.columns.PlaceOfInterestMap.NAME, poiname);
			pkt = new Packet<HashMap<String, String>>(Packet.ACTION_TYPE.GET,
					config.packetTransfer.actions.Search.WINDOW,"",
					config.packetTransfer.actions.Search.SUB_ACTION_GET_SEARCH_BY_POI,
					hm);
			ClientMain.cSrv.handleMessageFromClient(pkt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets the allcitites by description.
	 *
	 * @param desc the desc
	 * @return the allcitites by description
	 */
	public static void getallcitites_by_description(String desc) {
		Packet<HashMap<String, String>> pkt;
		try {
			HashMap<String, String> hm = new HashMap<String, String>();
			hm.put(config.database.tables.columns.City.DESCRIPTION, desc);
			pkt = new Packet<HashMap<String, String>>(Packet.ACTION_TYPE.GET,
					config.packetTransfer.actions.Search.WINDOW,"",
					config.packetTransfer.actions.Search.SUB_ACTION_GET_SEARCH_BY_TEXT_CITY,
					hm);
			ClientMain.cSrv.handleMessageFromClient(pkt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
