/*
 * this class defines all the actions the can be done on Profile 
 * and sends a request to the server with the corresponding packet according to the action and its type 
 * */

package client.requestHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import application.ClientMain;
import entities.Packet;


/**
 * The request handler of Profile.
 * static class.
 */
public class Profile {

	/**
	 * Override public contractor to make it static.
	 */
	private Profile(){}
	
	/**
	 * Update profile.
	 *
	 * @param user the user
	 */
	public static void update_profile(entities.User user) {
		Packet<String> pkt;
		try {
			ArrayList<String> list = new ArrayList<String>();
			list.add(user.toString());
			pkt = new Packet<String>(Packet.ACTION_TYPE.UPDATE,
					config.packetTransfer.actions.Profile.WINDOW,"",
					config.packetTransfer.actions.Profile.SUB_ACTION_UPDATE_ACCOUNT_DETAILS,
					list);
			ClientMain.cSrv.handleMessageFromClient(pkt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Update password.
	 *
	 * @param user the user
	 */
	public static void update_password(entities.User user) {
		Packet<String> pkt;
		try {
			ArrayList<String> list = new ArrayList<String>();
			list.add(user.toString());
			pkt = new Packet<String>(Packet.ACTION_TYPE.UPDATE,
					config.packetTransfer.actions.Profile.WINDOW,"",
					config.packetTransfer.actions.Profile.SUB_ACTION_UPDATE_ACCOUNT_PASSWORD,
					list);
			ClientMain.cSrv.handleMessageFromClient(pkt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	/**
	 * Update credit.
	 *
	 * @param credit the credit
	 */
	public static void update_credit(entities.CreditCard credit) {
		Packet<String> pkt;
		try {
			ArrayList<String> list = new ArrayList<String>();
			list.add(credit.toString());
			pkt = new Packet<String>(Packet.ACTION_TYPE.UPDATE,
					config.packetTransfer.actions.Profile.WINDOW,"",
					config.packetTransfer.actions.Profile.SUB_ACTION_UPDATE_PAYMENT_METHOD,
					list);
			ClientMain.cSrv.handleMessageFromClient(pkt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets the all purchase.
	 *
	 * @param username the username
	 * @return the all purchase
	 */
	public static void getAllPurchase(String username) {
		Packet<HashMap<String, String>> pkt;
		try {
			HashMap<String, String> hm = new HashMap<String, String>();
			hm.put(config.database.tables.columns.Purchase.USER, username);
			pkt = new Packet<HashMap<String, String>>(Packet.ACTION_TYPE.GET,
					config.packetTransfer.actions.Profile.WINDOW,"",
					config.packetTransfer.actions.Profile.SUB_ACTION_GET_ALL_PURCHASES,
					hm);
			ClientMain.cSrv.handleMessageFromClient(pkt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Adds the renwal.
	 *
	 * @param purchase the purchase
	 */
	public static void Add_renwal(entities.Purchase purchase) {
		Packet<String> pkt;
		try {
			ArrayList<String> list = new ArrayList<String>();
			list.add(purchase.toString());
			pkt = new Packet<String>(Packet.ACTION_TYPE.ADD,
					config.packetTransfer.actions.Purchase.WINDOW,"",
					config.packetTransfer.actions.Purchase.SUB_ACTION_ADD_RENEWAL,
					list);
			ClientMain.cSrv.handleMessageFromClient(pkt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}
