/*
 * 
 * this class extends the abstract class AbstractRequest.
 * executing the execute function from super class , direct the program to diagnose 
 * the action type then call one from these functions : addAction ,getAction ,updateAction,deleteAction 
 * so we can execute the corresponding query (according to sub actions)
 *
 */
package server.database.requestHandler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import entities.AbstractJsonToString;
import entities.Packet;
import entities.Purchase;
import entities.User;
import entities.AbstractRequest.STATUS;
import server.ClientDB;
import server.database.ResponseException;

// TODO: Auto-generated Javadoc
/**
 * The Class RateRequest.
 * Extends AbstractRequest
 */
public class ManageRequests extends AbstractRequest {

	/**
	 * Instantiates a new manage requests.
	 *
	 * @param window the window
	 * @param cdb the cdb
	 */
	public ManageRequests(String window, ClientDB cdb) {
		super(window, cdb);
	}

	/* (non-Javadoc)
	 * @see server.database.requestHandler.AbstractRequest#getAction(entities.Packet)
	 */
	@Override
	protected ArrayList<String> getAction(Packet<?> pkt) throws IOException, SQLException, ResponseException {
		HashMap<String, String> res = pkt.objectTranslationElem();
		ArrayList<String> whereVal = null;
		switch(pkt.getSub_action()) {
		case config.packetTransfer.actions.ManageRequests.SUB_ACTION_GET_ALL_RATE_REQUESTS:
			return AbstractJsonToString.hashMapToArrayListJsonEntities(entities.RateRequest::fromHashMap, cdb.executeSelectQuery(config.database.queries.RateRequest.MySQL_QUERY_GET_ALL_RATE_REQUESTS));
		case config.packetTransfer.actions.ManageRequests.SUB_ACTION_GET_ALL_VERSION_REQUESTS:
			return AbstractJsonToString.hashMapToArrayListJsonEntities(entities.VersionRequest::fromHashMap, cdb.executeSelectQuery(config.database.queries.VersionRequest.MySQL_QUERY_GET_ALL_VERSION_REQUESTS));
		case config.packetTransfer.actions.ManageRequests.SUB_ACTION_GET_USER_RATE_REQUEST_RESPONSE :
			whereVal = AbstractRequest.createArrayList(res.get(config.database.tables.columns.RateRequest.USERNAME_REQUEST));
			return AbstractJsonToString.hashMapToArrayListJsonEntities(entities.RateRequest::fromHashMap, cdb.executeSelectQuery(config.database.queries.RateRequest.MySQL_QUERY_GET_USER_RATE_REQUEST_RESPONSE, whereVal));
		case config.packetTransfer.actions.ManageRequests.SUB_ACTION_GET_USER_VERSION_REQUEST_RESPONSE:
			whereVal = AbstractRequest.createArrayList(res.get(config.database.tables.columns.VersionRequest.USERNAME_REQUEST));
			return AbstractJsonToString.hashMapToArrayListJsonEntities(entities.VersionRequest::fromHashMap, cdb.executeSelectQuery(config.database.queries.VersionRequest.MySQL_QUERY_GET_USER_VERSION_REQUEST_RESPONSE, whereVal));
		case config.packetTransfer.actions.ManageRequests.SUB_ACTION_GET_ALL_RATES:
			return AbstractJsonToString.hashMapToArrayListJsonEntities(entities.Rate::fromHashMap, cdb.executeSelectQuery(config.database.queries.Rate.MySQL_QUERY_GET_ALL_RATES));
			
		default:
			throw new ResponseException(Packet.ACTION_TYPE.FATAL_ERROR);
		}
	}

	/* (non-Javadoc)
	 * @see server.database.requestHandler.AbstractRequest#deleteAction(entities.Packet)
	 */
	@Override
	protected boolean deleteAction(Packet<?> pkt) throws IOException, SQLException, ResponseException {
		ArrayList<String> whereVal = null;
		ArrayList<String> json = pkt.objectTranslation();
		switch(pkt.getSub_action()) {
		case config.packetTransfer.actions.ManageRequests.SUB_ACTION_DELETE_RATE_REQUEST:
			entities.RateRequest rateRequest =  AbstractJsonToString.toObject(entities.RateRequest.class, json.get(0));
			whereVal = AbstractRequest.createArrayList(String.valueOf(rateRequest.getId()));
			return cdb.executeQuery(config.database.queries.RateRequest.MySQL_QUERY_DELETE_RATE_REQUEST, whereVal);
		case config.packetTransfer.actions.ManageRequests.SUB_ACTION_DELETE_VERSION_REQUEST:
			entities.VersionRequest versionRequest =  AbstractJsonToString.toObject(entities.VersionRequest.class, json.get(0));
			whereVal = AbstractRequest.createArrayList(String.valueOf(versionRequest.getId()));
			return cdb.executeQuery(config.database.queries.VersionRequest.MySQL_QUERY_DELETE_VERSION_REQUEST, whereVal);
		default:
			throw new ResponseException(Packet.ACTION_TYPE.FATAL_ERROR);
		}
	}

	/* (non-Javadoc)
	 * @see server.database.requestHandler.AbstractRequest#addAction(entities.Packet)
	 */
	@Override
	protected boolean addAction(Packet<?> pkt) throws IOException, SQLException, ResponseException {
		ArrayList<String> whereVal = null;
		ArrayList<String> json = pkt.objectTranslation();
		switch(pkt.getSub_action()) {
		case config.packetTransfer.actions.ManageRequests.SUB_ACTION_ADD_SUBSCRIPTION_RATE_REQUEST:
			entities.RateRequest rateRequest =  AbstractJsonToString.toObject(entities.RateRequest.class, json.get(0));
			whereVal = AbstractRequest.createArrayList(String.valueOf(rateRequest.getRate().getPrice()),
					String.valueOf(rateRequest.getRate().getDays()),
					entities.Rate.type.SUBSCRIPTION.toString(),
					rateRequest.getUser().getUsername(),
					entities.AbstractRequest.STATUS.WAITING.toString());
			
			boolean res1 = cdb.executeQuery(config.database.queries.RateRequest.MySQL_QUERY_ADD_RATE_REQUEST , whereVal);
			ArrayList<User> user = AbstractJsonToString.hashMapToArrayListEntities(entities.User::fromHashMap, cdb.executeSelectQuery(config.database.queries.User.MySQL_QUERY_GET_USER_BY_PERMISSION,
					AbstractRequest.createArrayList(String.valueOf(entities.Permission.Role.COMPANYMANAGER.getValue()))));

			for(User userto : user) {
				if(!userto.equals(rateRequest.getUser())) {
					ArrayList<String> whereValMessage = new ArrayList<String>();
					whereValMessage.add(rateRequest.getUser().getUsername());
					whereValMessage.add(userto.getUsername());
					whereValMessage.add("I added a request to add a rate");
					whereValMessage.add(String.format("Hello %s,\r\n" 
							+ "I added a request to add a price,\r\n" + 
							"I'm waiting for your response",
							userto.getUsername()));	
					cdb.executeQuery(config.database.queries.Message.MySQL_QUERY_ADD_NOTIFICATION, whereValMessage);
				}
			}
			
			return res1;
		case config.packetTransfer.actions.ManageRequests.SUB_ACTION_ADD_ONETIME_RATE_REQUEST:
			entities.RateRequest rateRequest2 =  AbstractJsonToString.toObject(entities.RateRequest.class, json.get(0));
			whereVal = AbstractRequest.createArrayList(String.valueOf(rateRequest2.getRate().getPrice()),
					"0",
					entities.Rate.type.ONETIME.toString(),
					rateRequest2.getUser().getUsername(),
					entities.AbstractRequest.STATUS.WAITING.toString());
			boolean res2 = cdb.executeQuery(config.database.queries.RateRequest.MySQL_QUERY_ADD_RATE_REQUEST , whereVal);
			ArrayList<User> user2 = AbstractJsonToString.hashMapToArrayListEntities(entities.User::fromHashMap, cdb.executeSelectQuery(config.database.queries.User.MySQL_QUERY_GET_USER_BY_PERMISSION,
					AbstractRequest.createArrayList(String.valueOf(entities.Permission.Role.COMPANYMANAGER.getValue()))));

			for(User userto : user2) {
				if(!userto.equals(rateRequest2.getUser())) {
					ArrayList<String> whereValMessage = new ArrayList<String>();
					whereValMessage.add(rateRequest2.getUser().getUsername());
					whereValMessage.add(userto.getUsername());
					whereValMessage.add("I added a request to add a rate");
					System.out.println("here3");
					whereValMessage.add(String.format("Hello %s,\r\n" 
							+ "I added a request to add a price,\r\n" + 
							"I'm waiting for your response",
							userto.getUsername()));	
					cdb.executeQuery(config.database.queries.Message.MySQL_QUERY_ADD_NOTIFICATION, whereValMessage);
				}
			}	
		return res2;
		case config.packetTransfer.actions.ManageRequests.SUB_ACTION_ADD_VERSION_REQUEST:
			entities.VersionRequest versionRequest =  AbstractJsonToString.toObject(entities.VersionRequest.class, json.get(0));
			whereVal = AbstractRequest.createArrayList(versionRequest.getCity().getName(),
					String.valueOf(versionRequest.getCity().getCountry().getShortName()),
					String.valueOf(versionRequest.getCity().getVersion()),
					versionRequest.getUser().getUsername(),
					entities.AbstractRequest.STATUS.WAITING.toString());
			return cdb.executeQuery(config.database.queries.VersionRequest.MySQL_QUERY_ADD_VERSION_REQUEST , whereVal);
		case config.packetTransfer.actions.ManageRequests.SUB_ACTION_ADD_RATE:
			entities.RateRequest rateRequest3 =  AbstractJsonToString.toObject(entities.RateRequest.class, json.get(0));
			whereVal = AbstractRequest.createArrayList(String.valueOf(rateRequest3.getRate().getPrice()),
					String.valueOf(rateRequest3.getRate().getDays()),
					String.valueOf(rateRequest3.getRate().getRtype()));
			boolean res = cdb.executeQuery(config.database.queries.Rate.MySQL_QUERY_ADD_RATE, whereVal);
			pkt.setSub_action(config.packetTransfer.actions.ManageRequests.SUB_ACTION_DELETE_RATE_REQUEST);
			deleteAction(pkt);
			pkt.setSub_action(config.packetTransfer.actions.ManageRequests.SUB_ACTION_ADD_RATE);
			return res;
		case config.packetTransfer.actions.ManageRequests.SUB_ACTION_ADD_VERSION :
			entities.VersionRequest versionRequest2 =  AbstractJsonToString.toObject(entities.VersionRequest.class, json.get(0));
			whereVal = AbstractRequest.createArrayList(versionRequest2.getCity().getVersion().toString(), versionRequest2.getCity().getName(), versionRequest2.getCity().getCountry().getShortName());
			boolean res3 = cdb.executeQuery(config.database.queries.City.MySQL_QUERY_UPDATE_VERSION_CITY, whereVal);
			pkt.setSub_action(config.packetTransfer.actions.ManageRequests.SUB_ACTION_DELETE_VERSION_REQUEST);
			deleteAction(pkt);
			pkt.setSub_action(config.packetTransfer.actions.ManageRequests.SUB_ACTION_ADD_VERSION);
			
			whereVal = AbstractRequest.createArrayList(versionRequest2.getCity().getName(), versionRequest2.getCity().getCountry().getShortName());
			ArrayList<Purchase> purchases = AbstractJsonToString.hashMapToArrayListEntities(entities.Purchase::fromHashMap, cdb.executeSelectQuery(config.database.queries.Purchase.MYSQL_QUERY_GET_UNEXPIRED_PURCHASES, whereVal));
			
			for(Purchase purchase : purchases) {
				ArrayList<String> whereValMessage = new ArrayList<String>();
				whereValMessage.add(purchase.getUser().getUsername());
				whereValMessage.add(purchase.getUser().getUsername());
				whereValMessage.add(String.format("We've released %s version of the city you purchased", versionRequest2.getCity().getVersion().toString()));
				whereValMessage.add(String.format("Hello %s,\r\n" 
						+ "We want to update you that we've released new version to %s,\r\n" + 
						"We hope you enojoy our services,\r\n",
						purchase.getUser().getUsername(),
						purchase.getCity().getName()));
				cdb.executeQuery(config.database.queries.Message.MySQL_QUERY_ADD_NOTIFICATION, whereValMessage);
			}	 
			return res3;
		default:
			throw new ResponseException(Packet.ACTION_TYPE.FATAL_ERROR);
		}
	}

	/* (non-Javadoc)
	 * @see server.database.requestHandler.AbstractRequest#updateAction(entities.Packet)
	 */
	@Override
	protected boolean updateAction(Packet<?> pkt) throws IOException, SQLException, ResponseException {
		ArrayList<String> whereVal = null;
		ArrayList<String> json = pkt.objectTranslation();
		switch(pkt.getSub_action()) {
		case config.packetTransfer.actions.ManageRequests.SUB_ACTION_UPDATE_APPROVAL_RATE_REQUEST:
			entities.RateRequest rateRequest =  AbstractJsonToString.toObject(entities.RateRequest.class, json.get(0));
			whereVal = AbstractRequest.createArrayList(rateRequest.getStatus().toString(), String.valueOf(rateRequest.getId()));
			boolean res = cdb.executeQuery(config.database.queries.RateRequest.MySQL_QUERY_UPDATE_APPROVAL_RATE_REQUEST, whereVal);
			
			entities.User user =  AbstractJsonToString.toObject(entities.User.class, json.get(1));
			if(user.equals(rateRequest.getUser()))
				return res;
			ArrayList<String> whereValMessage = new ArrayList<String>();
			whereValMessage.add(user.getUsername());
			whereValMessage.add(rateRequest.getUser().getUsername());
			whereValMessage.add("I answered your rate request");
			whereValMessage.add(String.format("Hello %s,\r\n" 
					+ "I decided to %s your rate request"
					+ "%s",
					rateRequest.getUser().getUsername(),
					rateRequest.getStatus().toString(),
					(rateRequest.getStatus()==STATUS.APPROVE) ? ",\r\nPlease set the rate version on 'Manage Requests' page." : ".\r\n"));
			cdb.executeQuery(config.database.queries.Message.MySQL_QUERY_ADD_NOTIFICATION, whereValMessage);
			
			return res;
		case config.packetTransfer.actions.ManageRequests.SUB_ACTION_UPDATE_APPROVAL_VERSION_REQUEST:
			entities.VersionRequest versionRequest =  AbstractJsonToString.toObject(entities.VersionRequest.class, json.get(0));
			whereVal = AbstractRequest.createArrayList(versionRequest.getStatus().toString(), String.valueOf(versionRequest.getId()));
			boolean res2 = cdb.executeQuery(config.database.queries.VersionRequest.MySQL_QUERY_UPDATE_APPROVAL_VERSION_REQUEST, whereVal);
			
			if(versionRequest.getStatus()==STATUS.REJECT) {
				whereVal = AbstractRequest.createArrayList(versionRequest.getCity().getName(),
						versionRequest.getCity().getCountry().getShortName(),
						versionRequest.getCity().getVersion().toString());
				cdb.executeQuery(config.database.queries.Map.MySQL_QUERY_DELETE_COLLECTION_VERSION, whereVal);
				cdb.executeQuery(config.database.queries.PlaceOfInterest.MySQL_QUERY_DELETE_POIS_FROM_CITY, whereVal);
			}
			
			entities.User user2 =  AbstractJsonToString.toObject(entities.User.class, json.get(1));
			if(user2.equals(versionRequest.getUser()))
				return res2;
			
			ArrayList<String> whereValMessage2 = new ArrayList<String>();
			whereValMessage2.add(user2.getUsername());
			whereValMessage2.add(versionRequest.getUser().getUsername());
			whereValMessage2.add(String.format("I answered your version request of %s city", versionRequest.getCity().getName()));
			whereValMessage2.add(String.format("Hello %s,\r\n" 
					+ "I decided to %s your version request of %s city"
					+ "%s",
					versionRequest.getUser().getUsername(),
					versionRequest.getStatus().toString(), versionRequest.getCity().getName(),
					(versionRequest.getStatus()==STATUS.APPROVE) ? ",\r\nPlease set the city version on 'Manage Requests' page." : ".\r\n"));
			cdb.executeQuery(config.database.queries.Message.MySQL_QUERY_ADD_NOTIFICATION, whereValMessage2);
			
			return res2;
		default:
			throw new ResponseException(Packet.ACTION_TYPE.FATAL_ERROR);
		}
	}
	
}
