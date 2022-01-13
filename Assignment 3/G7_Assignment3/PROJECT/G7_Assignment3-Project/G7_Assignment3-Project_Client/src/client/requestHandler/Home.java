/*
 * this class defines all the actions the can be done on Home 
 * and sends a request to the server with the corresponding packet according to the action and its type 
 * */

package client.requestHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import application.ClientMain;

import entities.Packet;

/**
 * The request handler of Home.
 * static class.
 */
public class Home {

	/**
	 * Override public contractor to make it static.
	 */
	private Home(){}
	
	/**
	 * Login.
	 *
	 * @param username the username
	 * @param password the password
	 * @return login(asynchronously).
	 */
	public static void login(String username, String password) {
		Packet<HashMap<String, String>> pkt;
		try {
			HashMap<String, String> hm = new HashMap<String, String>();
			hm.put(config.database.tables.columns.User.USERNAME, username);
			hm.put(config.database.tables.columns.User.PASSWORD, password);
			pkt = new Packet<HashMap<String, String>>(Packet.ACTION_TYPE.GET,
					config.packetTransfer.actions.Home.WINDOW,"",
					config.packetTransfer.actions.Home.SUB_ACTION_GET_LOGIN,
					hm);
			ClientMain.cSrv.handleMessageFromClient(pkt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Register.
	 *
	 * @param user the user
	 * @param payment the payment
	 * @return register(asynchronously).
	 */
	public static void register(entities.User user, entities.AbstractPayment payment) {
		Packet<String> pkt;
		try {
			ArrayList<String> list = new ArrayList<String>();
			list.add(user.toString());
			list.add(payment.toString());
			pkt = new Packet<String>(Packet.ACTION_TYPE.ADD,
					config.packetTransfer.actions.Home.WINDOW,"",
					config.packetTransfer.actions.Home.SUB_ACTION_ADD_REGISTER,
					list);
			ClientMain.cSrv.handleMessageFromClient(pkt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Logout.
	 *
	 * @return logout(asynchronously).
	 */
	public static void logout() {
		Packet<String> pkt;
		try {
			pkt = new Packet<String>(Packet.ACTION_TYPE.UPDATE,
					config.packetTransfer.actions.Home.WINDOW,"",
					config.packetTransfer.actions.Home.SUB_ACTION_UPDATE_LOGOUT,
					new ArrayList<String>());
			ClientMain.cSrv.handleMessageFromClient(pkt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
