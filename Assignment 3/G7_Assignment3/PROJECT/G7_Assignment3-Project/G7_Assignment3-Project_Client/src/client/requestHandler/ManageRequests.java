/*
 * this class defines all the actions the can be done on ManageRequests 
 * and sends a request to the server with the corresponding packet according to the action and its type 
 * */

package client.requestHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import application.ClientMain;
import entities.Packet;

/**
 * The request handler of requests.
 * static class.
 */
public class ManageRequests {

	/**
	 * Override public contractor to make it static.
	 */
	private ManageRequests(){}
	
	/**
	 * Gets the all version requests.
	 *
	 * @return the all version requests
	 */
	public static void getAll_version_requests() {
		Packet<HashMap<String, String>> pkt;
		try {
			HashMap<String, String> hm = new HashMap<String, String>();
			pkt = new Packet<HashMap<String, String>>(Packet.ACTION_TYPE.GET,
					config.packetTransfer.actions.ManageRequests.WINDOW,"",
					config.packetTransfer.actions.ManageRequests.SUB_ACTION_GET_ALL_VERSION_REQUESTS,
					hm);
			ClientMain.cSrv.handleMessageFromClient(pkt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets the all rates requests.
	 *
	 * @return the all rates requests
	 */
	public static void getAll_rates_requests() {
		Packet<HashMap<String, String>> pkt;
		try {
			HashMap<String, String> hm = new HashMap<String, String>();
			pkt = new Packet<HashMap<String, String>>(Packet.ACTION_TYPE.GET,
					config.packetTransfer.actions.ManageRequests.WINDOW,"",
					config.packetTransfer.actions.ManageRequests.SUB_ACTION_GET_ALL_RATE_REQUESTS,
					hm);
			ClientMain.cSrv.handleMessageFromClient(pkt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Updatet version reuest.
	 *
	 * @param req the req
	 * @param user the user
	 */
	public static void UpdatetVersionReuest(entities.VersionRequest req, entities.User user) {
		Packet<String> pkt;
		try {
			ArrayList<String> list = new ArrayList<String>();
			list.add(req.toString());
			list.add(user.toString());
			pkt = new Packet<String>(Packet.ACTION_TYPE.UPDATE,
					config.packetTransfer.actions.ManageRequests.WINDOW,"",
					config.packetTransfer.actions.ManageRequests.SUB_ACTION_UPDATE_APPROVAL_VERSION_REQUEST,
					list);
			ClientMain.cSrv.handleMessageFromClient(pkt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Updatet rates reuest.
	 *
	 * @param req the req
	 * @param user the user
	 */
	public static void UpdatetRatesReuest(entities.RateRequest req, entities.User user) {
		Packet<String> pkt;
		try {
			ArrayList<String> list = new ArrayList<String>();
			list.add(req.toString());
			list.add(user.toString());
			pkt = new Packet<String>(Packet.ACTION_TYPE.UPDATE,
					config.packetTransfer.actions.ManageRequests.WINDOW,"",
					config.packetTransfer.actions.ManageRequests.SUB_ACTION_UPDATE_APPROVAL_RATE_REQUEST,
					list);
			ClientMain.cSrv.handleMessageFromClient(pkt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Sets the rate.
	 *
	 * @param rateRequest the new rate
	 */
	public static void set_rate(entities.RateRequest rateRequest) {
		Packet<String> pkt;
		try {
			ArrayList<String> list = new ArrayList<String>();
			list.add(rateRequest.toString());
			pkt = new Packet<String>(Packet.ACTION_TYPE.ADD,
					config.packetTransfer.actions.ManageRequests.WINDOW,"",
					config.packetTransfer.actions.ManageRequests.SUB_ACTION_ADD_RATE,
					list);
			ClientMain.cSrv.handleMessageFromClient(pkt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Sets the version.
	 *
	 * @param verRequest the new version
	 */
	public static void set_version(entities.VersionRequest verRequest) {
		Packet<String> pkt;
		try {
			ArrayList<String> list = new ArrayList<String>();
			list.add(verRequest.toString());
			pkt = new Packet<String>(Packet.ACTION_TYPE.ADD,
					config.packetTransfer.actions.ManageRequests.WINDOW,"",
					config.packetTransfer.actions.ManageRequests.SUB_ACTION_ADD_VERSION,
					list);
			ClientMain.cSrv.handleMessageFromClient(pkt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets the all myrates requests.
	 *
	 * @param username the username
	 * @return the all myrates requests
	 */
	public static void getAll_myrates_requests(String username) {
		Packet<HashMap<String, String>> pkt;
		try {
			HashMap<String, String> hm = new HashMap<String, String>();
			hm.put(config.database.tables.columns.Request.USERNAME_REQUEST, username);
			pkt = new Packet<HashMap<String, String>>(Packet.ACTION_TYPE.GET,
					config.packetTransfer.actions.ManageRequests.WINDOW,"",
					config.packetTransfer.actions.ManageRequests.SUB_ACTION_GET_USER_RATE_REQUEST_RESPONSE,
					hm);
			ClientMain.cSrv.handleMessageFromClient(pkt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets the all myversion requests.
	 *
	 * @param username the username
	 * @return the all myversion requests
	 */
	public static void getAll_myversion_requests(String username) {
		Packet<HashMap<String, String>> pkt;
		try {
			HashMap<String, String> hm = new HashMap<String, String>();
			hm.put(config.database.tables.columns.Request.USERNAME_REQUEST, username);
			pkt = new Packet<HashMap<String, String>>(Packet.ACTION_TYPE.GET,
					config.packetTransfer.actions.ManageRequests.WINDOW,"",
					config.packetTransfer.actions.ManageRequests.SUB_ACTION_GET_USER_VERSION_REQUEST_RESPONSE,
					hm);
			ClientMain.cSrv.handleMessageFromClient(pkt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Delete one rate reuest.
	 *
	 * @param req the req
	 */
	public static void DeleteOneRateReuest(entities.RateRequest req) {
		Packet<String> pkt;
		try {
			ArrayList<String> list = new ArrayList<String>();
			list.add(req.toString());
			pkt = new Packet<String>(Packet.ACTION_TYPE.DELETE,
					config.packetTransfer.actions.ManageRequests.WINDOW,"",
					config.packetTransfer.actions.ManageRequests.SUB_ACTION_DELETE_RATE_REQUEST,
					list);
			ClientMain.cSrv.handleMessageFromClient(pkt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Delete one version reuest.
	 *
	 * @param req the req
	 */
	public static void DeleteOneVersionReuest(entities.VersionRequest req) {
		Packet<String> pkt;
		try {
			ArrayList<String> list = new ArrayList<String>();
			list.add(req.toString());
			pkt = new Packet<String>(Packet.ACTION_TYPE.DELETE,
					config.packetTransfer.actions.ManageRequests.WINDOW,"",
					config.packetTransfer.actions.ManageRequests.SUB_ACTION_DELETE_VERSION_REQUEST,
					list);
			ClientMain.cSrv.handleMessageFromClient(pkt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Adds the one rate reuestonetime.
	 *
	 * @param request the request
	 */
	public static void AddOneRateReuestonetime(entities.RateRequest request) {
		Packet<String> pkt;
		try {
			ArrayList<String> list = new ArrayList<String>();
			list.add(request.toString());
			pkt = new Packet<String>(Packet.ACTION_TYPE.ADD,
					config.packetTransfer.actions.ManageRequests.WINDOW,"",
					config.packetTransfer.actions.ManageRequests.SUB_ACTION_ADD_ONETIME_RATE_REQUEST,
					list);
			ClientMain.cSrv.handleMessageFromClient(pkt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Adds the one rate reuestsubscribtion.
	 *
	 * @param request the request
	 */
	public static void AddOneRateReuestsubscribtion(entities.RateRequest request) {
		Packet<String> pkt;
		try {
			ArrayList<String> list = new ArrayList<String>();
			list.add(request.toString());
			pkt = new Packet<String>(Packet.ACTION_TYPE.ADD,
					config.packetTransfer.actions.ManageRequests.WINDOW,"",
					config.packetTransfer.actions.ManageRequests.SUB_ACTION_ADD_SUBSCRIPTION_RATE_REQUEST,
					list);
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
					config.packetTransfer.actions.ManageRequests.WINDOW,"",
					config.packetTransfer.actions.ManageRequests.SUB_ACTION_GET_ALL_RATES,
					new HashMap<String, String>());
			ClientMain.cSrv.handleMessageFromClient(pkt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	
}
