package client.requestHandler;

import java.io.IOException;
import java.util.HashMap;

import application.ClientMain;
import config.packetTransfer.Actions.ACTION_TYPE;
import entities.Packet;

/**
 * The request handler of city.
 * static class.
 */
public class City {

	/**
	 * Override public contractor to make it static.
	 */
	private City(){}
	
	/**
	 * Gets the all city names.
	 * Create packet suitable for requesting cities name's and sent it to clientService for continue execution.
	 * @return the all city names(asynchronously).
	 */
	public static void getAllCityNames() {
		Packet<HashMap<String, String>> pkt;
		try {
			pkt = new Packet<HashMap<String, String>>(ACTION_TYPE.GET,
					config.packetTransfer.actions.City.WINDOW_ECITCITY,
					config.packetTransfer.actions.City.SUB_ACTION_GET_CITIES_NAMES,
					config.packetTransfer.Prototype.CLIENT_TOKEN, new HashMap<String, String>());
			ClientMain.cSrv.handleMessageFromClient(pkt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Delete city.
	 * Create packet suitable for requesting delete city and sent it to clientService for continue execution.
	 * @param name the name
	 */
	public static void deleteCity(String name) {
		Packet<HashMap<String, String>> pkt;
		try {
			HashMap<String, String> hm = new HashMap<String, String>();
			hm.put(config.database.tables.columns.City.NAME, name);
			pkt = new Packet<HashMap<String, String>>(ACTION_TYPE.DELETE,
					config.packetTransfer.actions.City.WINDOW_ECITCITY,
					config.packetTransfer.actions.City.SUB_ACTION_DELETE_CITY,
					config.packetTransfer.Prototype.CLIENT_TOKEN, hm);
			ClientMain.cSrv.handleMessageFromClient(pkt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Edits the city.
	 * Create packet suitable for requesting edit city and sent it to clientService for continue execution.
	 * @param name the name
	 * @param city the city
	 */
	public static void editCity(String name, entities.City city) {
		Packet<HashMap<String, String>> pkt;
		try {
			HashMap<String, String> hm = new HashMap<String, String>();
			hm.put(config.database.tables.columns.City.NAME, name);
			hm.put(config.packetTransfer.actions.City.SUB_ACTION_EDIT_CITY, city.toString());
			pkt = new Packet<HashMap<String, String>>(ACTION_TYPE.UPDATE,
					config.packetTransfer.actions.City.WINDOW_ECITCITY,
					config.packetTransfer.actions.City.SUB_ACTION_EDIT_CITY,
					config.packetTransfer.Prototype.CLIENT_TOKEN, hm);
			ClientMain.cSrv.handleMessageFromClient(pkt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets the city.
	 * Create packet suitable for requesting get city and sent it to clientService for continue execution.
	 * @param name the name
	 * @return the city
	 */
	public static void getCity(String name) {
		Packet<HashMap<String, String>> pkt;
		try {
			HashMap<String, String> hm = new HashMap<String, String>();
			hm.put(config.database.tables.columns.City.NAME, name);
			pkt = new Packet<HashMap<String, String>>(ACTION_TYPE.GET,
					config.packetTransfer.actions.City.WINDOW_ECITCITY,
					config.packetTransfer.actions.City.SUB_ACTION_GET_CITY,
					config.packetTransfer.Prototype.CLIENT_TOKEN, hm);
			ClientMain.cSrv.handleMessageFromClient(pkt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Adds the city.
	 * Create packet suitable for requesting add city and sent it to clientService for continue execution.
	 * @param city the city
	 */
	public static void addCity(entities.City city) {
		Packet<String> pkt;
		try {
			pkt = new Packet<String>(ACTION_TYPE.ADD,
					config.packetTransfer.actions.City.WINDOW_ECITCITY,
					config.packetTransfer.actions.City.SUB_ACTION_ADD_CITY,
					config.packetTransfer.Prototype.CLIENT_TOKEN, city.toString());
			ClientMain.cSrv.handleMessageFromClient(pkt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
