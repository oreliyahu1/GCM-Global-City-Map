/*
 * this class defines all the actions the can be done on Purchase 
 * and sends a request to the server with the corresponding packet according to the action and its type 
 * */

package client.requestHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import application.ClientMain;
import entities.Packet;


/**
 * The request handler of Purchase.
 * static class.
 */
public class Purchase {

	/**
	 * Override public contractor to make it static.
	 */
	private Purchase(){}
	
	/**
	 * Gets the payment.
	 *
	 * @param user the user
	 */
	public static void Get_payment(entities.User user) {
		Packet<HashMap<String, String>> pkt;
		try {
			HashMap<String, String> hm = new HashMap<String, String>();
			hm.put(config.database.tables.columns.Purchase.USER, user.getUsername());

			pkt = new Packet<HashMap<String, String>>(Packet.ACTION_TYPE.GET,
					config.packetTransfer.actions.Purchase.WINDOW,"",
					config.packetTransfer.actions.Purchase.SUB_ACTION_GET_PAYMENT_DETAILS,
					hm);
			ClientMain.cSrv.handleMessageFromClient(pkt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Adds the purchase.
	 *
	 * @param purchase the purchase
	 */
	public static void Add_purchase(entities.Purchase purchase) {
		Packet<String> pkt;
		try {
			ArrayList<String> list = new ArrayList<String>();
			list.add(purchase.toString());
			pkt = new Packet<String>(Packet.ACTION_TYPE.ADD,
					config.packetTransfer.actions.Purchase.WINDOW,"",
					config.packetTransfer.actions.Purchase.SUB_ACTION_ADD_PURCHASE,
					list);
			ClientMain.cSrv.handleMessageFromClient(pkt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Gets the last purchase.
	 *
	 * @param user the user
	 */
	public static void Get_last_purchase(entities.User user) {
		Packet<HashMap<String, String>> pkt;
		try {
			HashMap<String, String> hm = new HashMap<String, String>();
			hm.put(config.database.tables.columns.Purchase.USER, user.getUsername());

			pkt = new Packet<HashMap<String, String>>(Packet.ACTION_TYPE.GET,
					config.packetTransfer.actions.Purchase.WINDOW,"",
					config.packetTransfer.actions.Purchase.SUB_ACTION_GET_LAST_USER_PURCHASE,
					hm);
			ClientMain.cSrv.handleMessageFromClient(pkt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets the unexpired purchases.
	 *
	 * @param user the user
	 * @param city the city
	 */
	public static void Get_unexpired_purchases(entities.User user, entities.City city) {
		Packet<HashMap<String, String>> pkt;
		try {
			HashMap<String, String> hm = new HashMap<String, String>();
			hm.put(config.database.tables.columns.Purchase.CITY, city.getName());
			hm.put(config.database.tables.columns.Purchase.SHORTCOUNTRY, city.getCountry().getShortName());
			hm.put(config.database.tables.columns.Purchase.USER, user.getUsername());
			pkt = new Packet<HashMap<String, String>>(Packet.ACTION_TYPE.GET,
					config.packetTransfer.actions.Purchase.WINDOW,"",
					config.packetTransfer.actions.Purchase.SUB_ACTION_GET_USER_UNEXPIRED_PURCHASES,
					hm);
			ClientMain.cSrv.handleMessageFromClient(pkt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets the all rates.
	 *
	 * @return the all rates
	 */
	public static void getAllRates() {
		Packet<HashMap<String, String>> pkt;
		try {
			pkt = new Packet<HashMap<String, String>>(Packet.ACTION_TYPE.GET,
					config.packetTransfer.actions.Purchase.WINDOW,"",
					config.packetTransfer.actions.Purchase.SUB_ACTION_GET_RATES,
					new HashMap<String, String>());
			ClientMain.cSrv.handleMessageFromClient(pkt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	

	
}
