/*
 * this class defines all the actions the can be done on ManageClients 
 * and sends a request to the server with the corresponding packet according to the action and its type 
 * */

package client.requestHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import application.ClientMain;
import entities.Packet;


/**
 * The request handler of city.
 * static class.
 */
public class ManageClients {

	/**
	 * Override public contractor to make it static.
	 */
	private ManageClients(){}
	
	/**
	 * Gets the allusers.
	 *
	 * @return the allusers
	 */
	public static void getAllusers() {
		Packet<HashMap<String, String>> pkt;
		try {
			pkt = new Packet<HashMap<String, String>>(Packet.ACTION_TYPE.GET,
					config.packetTransfer.actions.ManageUsers.WINDOW,"",
					config.packetTransfer.actions.ManageUsers.SUB_ACTION_GET_ALL_USERS,
					new HashMap<String, String>());
			ClientMain.cSrv.handleMessageFromClient(pkt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
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
					config.packetTransfer.actions.ManageUsers.WINDOW,"",
					config.packetTransfer.actions.ManageUsers.SUB_ACTION_UPDATE_ACCOUNT_DETAILS,
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
					config.packetTransfer.actions.ManageUsers.WINDOW,"",
					config.packetTransfer.actions.ManageUsers.SUB_ACTION_UPDATE_ACCOUNT_PASSWORD,
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
					config.packetTransfer.actions.ManageUsers.WINDOW,"",
					config.packetTransfer.actions.ManageUsers.SUB_ACTION_UPDATE_PAYMENT_METHOD,
					list);
			ClientMain.cSrv.handleMessageFromClient(pkt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Update permission.
	 *
	 * @param newuser the newuser
	 */
	public static void update_permission(entities.User newuser) {
		Packet<String> pkt;
		try {
			ArrayList<String> list = new ArrayList<String>();
			list.add(newuser.toString());
			pkt = new Packet<String>(Packet.ACTION_TYPE.UPDATE,
					config.packetTransfer.actions.ManageUsers.WINDOW,"",
					config.packetTransfer.actions.ManageUsers.SUB_ACTION_UPDATE_PERMISSION,
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
	 * @param user the user
	 * @return the all purchase
	 */
	public static void getAllPurchase(entities.User user) {
		Packet<HashMap<String, String>> pkt;
		try {
			HashMap<String, String> hm = new HashMap<String, String>();
			hm.put(config.database.tables.columns.Purchase.USER, user.getUsername());
			pkt = new Packet<HashMap<String, String>>(Packet.ACTION_TYPE.GET,
					config.packetTransfer.actions.ManageUsers.WINDOW,"",
					config.packetTransfer.actions.ManageUsers.SUB_ACTION_GET_ALL_USER_PURCHASES,
					hm);
			ClientMain.cSrv.handleMessageFromClient(pkt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

	
}
