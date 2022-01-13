/*
 * this class defines all the actions the can be done on Notification 
 * and sends a request to the server with the corresponding packet according to the action and its type 
 * */

package client.requestHandler;

import java.io.IOException;
import java.util.HashMap;

import application.ClientMain;
import entities.Packet;



/**
 * The Class Notifications.
 */
public class Notifications {

	/**
	 * Override public contractor to make it static.
	 */
	private Notifications(){}
	
	/**
	 * Gets the all notifications.
	 *
	 * @param user the user
	 * @return the all notifications
	 */
	public static void getAllNotifications(entities.User user) {
		Packet<HashMap<String, String>> pkt;
		try {
			HashMap<String, String> hm = new HashMap<String, String>();
			hm.put(config.database.tables.columns.Message.TO, user.getUsername());
			pkt = new Packet<HashMap<String, String>>(Packet.ACTION_TYPE.GET,
					config.packetTransfer.actions.Notification.WINDOW,"",
					config.packetTransfer.actions.Notification.SUB_ACTION_GET_ALL_NOTIFICATIONS,
					hm);
			ClientMain.cSrv.handleMessageFromClient(pkt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Delete notification.
	 *
	 * @param msg the msg
	 */
	public static void Delete_Notification(entities.Message msg) {
		Packet<HashMap<String, String>> pkt;
		try {
			HashMap<String, String> hm = new HashMap<String, String>();
			hm.put(config.database.tables.columns.Message.ID, String.valueOf(msg.getId()));
			pkt = new Packet<HashMap<String, String>>(Packet.ACTION_TYPE.DELETE,
					config.packetTransfer.actions.Notification.WINDOW,"",
					config.packetTransfer.actions.Notification.SUB_ACTION_DELETE_NOTIFICATION,
					hm);
			ClientMain.cSrv.handleMessageFromClient(pkt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Delete ALL notifications.
	 *
	 * @param id the id
	 */
	public static void Delete_ALLNotifications(String id) {
		Packet<HashMap<String, String>> pkt;
		try {
			HashMap<String, String> hm = new HashMap<String, String>();
			hm.put(config.database.tables.columns.Message.ID, id);
			pkt = new Packet<HashMap<String, String>>(Packet.ACTION_TYPE.DELETE,
					config.packetTransfer.actions.Notification.WINDOW,"",
					config.packetTransfer.actions.Notification.SUB_ACTION_DELETE_ALL_NOTIFICATIONS,
					hm);
			ClientMain.cSrv.handleMessageFromClient(pkt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Update notifications.
	 *
	 * @param id the id
	 */
	public static void Update_Notifications(String id) {
		Packet<HashMap<String, String>> pkt;
		try {
			HashMap<String, String> hm = new HashMap<String, String>();
			hm.put(config.database.tables.columns.Message.ID, id);
			pkt = new Packet<HashMap<String, String>>(Packet.ACTION_TYPE.UPDATE,
					config.packetTransfer.actions.Notification.WINDOW,"",
					config.packetTransfer.actions.Notification.SUB_ACTION_UPDATE_READ_NOTIFICATION,
					hm);
			ClientMain.cSrv.handleMessageFromClient(pkt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Adds the notifications.
	 *
	 * @param user the user
	 */
	public static void addNotifications(entities.User user) {
		Packet<HashMap<String, String>> pkt;
		try {
			HashMap<String, String> hm = new HashMap<String, String>();
			pkt = new Packet<HashMap<String, String>>(Packet.ACTION_TYPE.GET,
					config.packetTransfer.actions.Notification.WINDOW,"",
					config.packetTransfer.actions.Notification.SUB_ACTION_ADD_NOTIFICATION,
					hm);
			ClientMain.cSrv.handleMessageFromClient(pkt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets the new notifications.
	 *
	 * @param user the user
	 * @return the new notifications
	 */
	public static void getNewNotifications(entities.User user) {
		Packet<HashMap<String, String>> pkt;
		try {
			HashMap<String, String> hm = new HashMap<String, String>();
			hm.put(config.database.tables.columns.Message.TO, user.getUsername());
			pkt = new Packet<HashMap<String, String>>(Packet.ACTION_TYPE.GET,
					config.packetTransfer.actions.Notification.WINDOW,"",
					config.packetTransfer.actions.Notification.SUB_ACTION_GET_NEW_NOTIFICATIONS,
					hm);
			ClientMain.cSrv.handleMessageFromClient(pkt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}
