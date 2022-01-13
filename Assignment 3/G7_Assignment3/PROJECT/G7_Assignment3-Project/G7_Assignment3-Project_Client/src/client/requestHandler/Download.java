/*
 * this class defines all the actions the can be done on Download 
 * and sends a request to the server with the corresponding packet according to the action and its type 
 * */

package client.requestHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import application.ClientMain;
import entities.Packet;


/**
 * The request handler of Download.
 * static class.
 */
public class Download {

	/**
	 * Override public contractor to make it static.
	 */
	private Download(){}
	
	
	/**
	 * Gets the all maps of cityfor download.
	 *
	 * @param purchase the purchase
	 * @return the all maps of cityfor download
	 */
	public static void getAllMapsOfCityforDownload(entities.Purchase purchase) {
		Packet<String> pkt;
		try {
			ArrayList<String> list = new ArrayList<String>();
			list.add(purchase.toString());
			pkt = new Packet<String>(Packet.ACTION_TYPE.GET,
					config.packetTransfer.actions.Download.WINDOW,"",
					config.packetTransfer.actions.Download.SUB_ACTION_GET_CITY_MAPS,
					list);
			ClientMain.cSrv.handleMessageFromClient(pkt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets the full map of city.
	 *
	 * @param activity the activity
	 * @return the full map of city
	 */
	public static void getFullMapOfCity(entities.Activity activity) {
		Packet<String> pkt;
		try {
			ArrayList<String> list = new ArrayList<String>();
			list.add(activity.toString());
			pkt = new Packet<String>(Packet.ACTION_TYPE.GET,
					config.packetTransfer.actions.Download.WINDOW,"",
					config.packetTransfer.actions.Download.SUB_ACTION_GET_FULL_MAP_BY_CITY,
					list);
			ClientMain.cSrv.handleMessageFromClient(pkt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
		/**
		 * Gets the poismap by map.
		 *
		 * @param map the map
		 * @return the poismap by map
		 */
		public static void getPOISMAP_BY_MAP(entities.Map map) {
			Packet<HashMap<String, String>> pkt;
			try {
				HashMap<String, String> hm = new HashMap<String, String>();
				hm.put(config.database.tables.columns.Map.SHORTCOUNTRY, map.getCity().getCountry().getShortName());
				hm.put(config.database.tables.columns.Map.NAME, map.getName());
				hm.put(config.database.tables.columns.Map.CITY, map.getCity().getName());
				hm.put(config.database.tables.columns.Map.COLLECTION_VERSION, map.getCity().getVersion().toString());
				
				pkt = new Packet<HashMap<String, String>>(Packet.ACTION_TYPE.GET,
						config.packetTransfer.actions.Download.WINDOW,"",
						config.packetTransfer.actions.Download.SUB_ACTION_GET_POISMAP_BY_MAP,
						hm);
				ClientMain.cSrv.handleMessageFromClient(pkt);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
	
	
	
}
