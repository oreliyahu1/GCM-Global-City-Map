/*
 * this class defines all the actions the can be done on Rate 
 * and sends a request to the server with the corresponding packet according to the action and its type 
 * */

package client.requestHandler;

import java.io.IOException;
import java.util.HashMap;

import application.ClientMain;
import entities.Packet;

/**
 * The request handler of Rates.
 * static class.
 */
public class Rate {

	/**
	 * Override public contractor to make it static.
	 */
	private Rate(){}

	/**
	 * Delete rate.
	 *
	 * @param rate the rate
	 */
	public static void delete_rate(entities.Rate rate) {
		Packet<HashMap<String, String>> pkt;
		try {
			HashMap<String, String> hm = new HashMap<String, String>();
			hm.put(config.database.tables.columns.Rate.NUMBEROFDAYS, String.valueOf(rate.getDays()));
			hm.put(config.database.tables.columns.Rate.PRICE, String.valueOf(rate.getPrice()));
			pkt = new Packet<HashMap<String, String>>(Packet.ACTION_TYPE.DELETE,
					config.packetTransfer.actions.Rate.WINDOW,"",
					config.packetTransfer.actions.Rate.SUB_ACTION_DELETE_RATE,
					hm);
			ClientMain.cSrv.handleMessageFromClient(pkt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
