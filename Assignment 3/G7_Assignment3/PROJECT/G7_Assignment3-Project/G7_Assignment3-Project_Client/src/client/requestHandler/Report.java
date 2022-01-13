/*
 * this class defines all the actions the can be done on Report 
 * and sends a request to the server with the corresponding packet according to the action and its type 
 * */

package client.requestHandler;

import java.io.IOException;
import java.util.ArrayList;

import application.ClientMain;
import entities.Packet;

/**
 * The request handler of Report.
 * static class.
 */
public class Report {

	/**
	 * Override public contractor to make it static.
	 */
	private Report(){}
	
	public static void get_report(String date1, String date2) {
		Packet<String> pkt;
		try {
			ArrayList<String> list = new ArrayList<String>();
			list.add(date1.toString());
			list.add(date2.toString());
			pkt = new Packet<String>(Packet.ACTION_TYPE.GET,
					config.packetTransfer.actions.DailyReport.WINDOW,"",
					config.packetTransfer.actions.DailyReport.SUB_ACTION_GET_REPORTS,
					list);
			ClientMain.cSrv.handleMessageFromClient(pkt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	
}
