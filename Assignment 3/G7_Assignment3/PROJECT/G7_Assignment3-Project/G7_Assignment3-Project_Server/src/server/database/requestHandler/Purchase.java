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
import server.ClientDB;
import server.database.ResponseException;

// TODO: Auto-generated Javadoc
/**
 * The Class Purchase.
 * Extends AbstractRequest
 */
public class Purchase extends AbstractRequest{
	
	/**
	 * Instantiates a new purchase.
	 *
	 * @param window the window
	 * @param cdb the cdb
	 */
	public Purchase(String window, ClientDB cdb) {
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
		case config.packetTransfer.actions.Purchase.SUB_ACTION_GET_RATES:
			return AbstractJsonToString.hashMapToArrayListJsonEntities(entities.Rate::fromHashMap, cdb.executeSelectQuery(config.database.queries.Rate.MySQL_QUERY_GET_ALL_RATES));
		case config.packetTransfer.actions.Purchase.SUB_ACTION_GET_PURCHASES_EXPIRES_IN_3DAYS:
			return AbstractJsonToString.hashMapToArrayListJsonEntities(entities.Purchase::fromHashMap, cdb.executeSelectQuery(config.database.queries.Purchase.MySQL_QUERY_GET_PURCHASES_SUBSCRIPTION_EXPIRES_IN_X_DAYS));
		case config.packetTransfer.actions.Purchase.SUB_ACTION_GET_USER_UNEXPIRED_PURCHASES:
			whereVal = AbstractRequest.createArrayList(res.get(config.database.tables.columns.Purchase.CITY),
					res.get(config.database.tables.columns.Purchase.SHORTCOUNTRY),
					res.get(config.database.tables.columns.Purchase.USER));
			return AbstractJsonToString.hashMapToArrayListJsonEntities(entities.Purchase::fromHashMap, cdb.executeSelectQuery(config.database.queries.Purchase.MYSQL_QUERY_GET_USER_UNEXPIRED_PURCHASES, whereVal));
		case config.packetTransfer.actions.Purchase.SUB_ACTION_GET_PAYMENT_DETAILS:
			whereVal = AbstractRequest.createArrayList(res.get(config.database.tables.columns.Purchase.USER));
			return AbstractJsonToString.hashMapToArrayListJsonEntities(entities.CreditCard::fromHashMap, cdb.executeSelectQuery(config.database.queries.CreditCard.MySQL_QUERY_GET_PAYMENT_DETAILS, whereVal));
		case config.packetTransfer.actions.Purchase.SUB_ACTION_GET_LAST_USER_PURCHASE :
			whereVal = AbstractRequest.createArrayList(res.get(config.database.tables.columns.Purchase.USER));
			return AbstractJsonToString.hashMapToArrayListJsonEntities(entities.Purchase::fromHashMap, cdb.executeSelectQuery(config.database.queries.Purchase.MYSQL_QUERY_GET_USER_LAST_PURCHASE, whereVal));
		case config.packetTransfer.actions.Purchase.SUB_ACTION_GET_ALL_USER_PURCHASES:
			whereVal = AbstractRequest.createArrayList(res.get(config.database.tables.columns.Purchase.USER));
			return AbstractJsonToString.hashMapToArrayListJsonEntities(entities.Purchase::fromHashMap, cdb.executeSelectQuery(config.database.queries.Purchase.MYSQL_QUERY_GET_ALL_USER_PURCHASE, whereVal));
		case config.packetTransfer.actions.Purchase.SUB_ACTION_GET_ALL_USER_CITY_PURCHASES:
			whereVal = AbstractRequest.createArrayList(res.get(config.database.tables.columns.Purchase.USER),
					res.get(config.database.tables.columns.Purchase.CITY),
					res.get(config.database.tables.columns.Purchase.SHORTCOUNTRY));
			return AbstractJsonToString.hashMapToArrayListJsonEntities(entities.Purchase::fromHashMap, cdb.executeSelectQuery(config.database.queries.Purchase.MYSQL_QUERY_GET_ALL_USER_CITY_PURCHASES, whereVal));
		default:
	
			throw new ResponseException(Packet.ACTION_TYPE.FATAL_ERROR);
			
		
		}
	}

	/* (non-Javadoc)
	 * @see server.database.requestHandler.AbstractRequest#deleteAction(entities.Packet)
	 */
	@Override
	protected boolean deleteAction(Packet<?> pkt) throws IOException, SQLException, ResponseException {
		return false;
	}

	
	/* (non-Javadoc)
	 * @see server.database.requestHandler.AbstractRequest#addAction(entities.Packet)
	 */
	@Override
	protected boolean addAction(Packet<?> pkt) throws IOException, SQLException, ResponseException {
		ArrayList<String> whereVal = null;
		ArrayList<String> json = pkt.objectTranslation();
		switch(pkt.getSub_action()) {
		case config.packetTransfer.actions.Purchase.SUB_ACTION_ADD_PURCHASE:
			entities.Purchase Purchase =  AbstractJsonToString.toObject(entities.Purchase.class, json.get(0));
			whereVal = AbstractRequest.createArrayList("0",
					Purchase.getCity().getName(),
					String.valueOf(Purchase.getPurchasePrice().getPrice()),
					String.valueOf(Purchase.getPurchasePrice().getDays()),
					Purchase.getCity().getCountry().getShortName(),
					String.valueOf(Purchase.getCity().getVersion()),
					String.valueOf(Purchase.getPurchasePrice().getRtype()),
					Purchase.getUser().getUsername());
			return cdb.executeQuery(config.database.queries.Purchase.MySQL_QUERY_ADD_PURCHASE , whereVal);
		case config.packetTransfer.actions.Purchase.SUB_ACTION_ADD_RENEWAL:
			entities.Purchase Purchase2 =  AbstractJsonToString.toObject(entities.Purchase.class, json.get(0));
			whereVal = AbstractRequest.createArrayList(String.valueOf(Purchase2.getRenewal()),
			Purchase2.getCity().getName(),
			String.valueOf(Purchase2.getPurchasePrice().getPrice()),
			String.valueOf(Purchase2.getPurchasePrice().getDays()),
			Purchase2.getCity().getCountry().getShortName(),
			String.valueOf(Purchase2.getCity().getVersion()),
			String.valueOf(Purchase2.getPurchasePrice().getRtype()),
			Purchase2.getUser().getUsername());
			return cdb.executeQuery(config.database.queries.Purchase.MySQL_QUERY_ADD_PURCHASE , whereVal);
		default:
			throw new ResponseException(Packet.ACTION_TYPE.FATAL_ERROR);
		}
	}

	/* (non-Javadoc)
	 * @see server.database.requestHandler.AbstractRequest#updateAction(entities.Packet)
	 */
	@Override
	protected boolean updateAction(Packet<?> pkt) throws IOException, SQLException, ResponseException {
		// TODO Auto-generated method stub
		return false;
	}
}
