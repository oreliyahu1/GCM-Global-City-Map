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
 * The Class Profile.
 * Extends AbstractRequest
 */
public class ManageUsers extends AbstractRequest {

	/**
	 * Instantiates a new manage users.
	 *
	 * @param window the window
	 * @param cdb the cdb
	 */
	public ManageUsers(String window, ClientDB cdb) {
		super(window, cdb);
	}

	/* (non-Javadoc)
	 * @see server.database.requestHandler.AbstractRequest#getAction(entities.Packet)
	 */
	@Override
	protected ArrayList<String> getAction(Packet<?> pkt) throws IOException, SQLException, ResponseException {
		HashMap<String, String> res = pkt.objectTranslationElem();
		ArrayList<String> whereVal = null;

		switch (pkt.getSub_action()) {
		case config.packetTransfer.actions.ManageUsers.SUB_ACTION_GET_ACCOUNT_DETAILS:
			whereVal = AbstractRequest.createArrayList(res.get(config.database.tables.columns.User.USERNAME));
			return AbstractJsonToString.hashMapToArrayListJsonEntities(entities.User::fromHashMap,
					cdb.executeSelectQuery(config.database.queries.User.MySQL_QUERY_GET_USER_DETAILS, whereVal));
		case config.packetTransfer.actions.ManageUsers.SUB_ACTION_GET_PAYMENT_METHOD:
			whereVal = AbstractRequest.createArrayList(res.get(config.database.tables.columns.CreditCard.OWNER));
			return AbstractJsonToString.hashMapToArrayListJsonEntities(entities.CreditCard::fromHashMap,
					cdb.executeSelectQuery(config.database.queries.CreditCard.MySQL_QUERY_GET_PAYMENT_DETAILS, whereVal));
		case config.packetTransfer.actions.ManageUsers.SUB_ACTION_GET_ALL_USERS:
			return AbstractJsonToString.hashMapToArrayListJsonEntities(entities.User::fromHashMap,
					cdb.executeSelectQuery(config.database.queries.User.MySQL_QUERY_GET_ALL_USERS));
			case config.packetTransfer.actions.ManageUsers.SUB_ACTION_GET_ALL_USER_PURCHASES:
				whereVal = AbstractRequest.createArrayList(res.get(config.database.tables.columns.Purchase.USER));
				return AbstractJsonToString.hashMapToArrayListJsonEntities(entities.Purchase::fromHashMap, cdb.executeSelectQuery(config.database.queries.Purchase.MYSQL_QUERY_GET_ALL_USER_PURCHASE, whereVal));
		default:
			throw new ResponseException(Packet.ACTION_TYPE.FATAL_ERROR);
		}

	}

	/* (non-Javadoc)
	 * @see server.database.requestHandler.AbstractRequest#deleteAction(entities.Packet)
	 */
	@Override
	protected boolean deleteAction(Packet<?> pkt) throws IOException, SQLException, ResponseException {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see server.database.requestHandler.AbstractRequest#addAction(entities.Packet)
	 */
	@Override
	protected boolean addAction(Packet<?> pkt) throws IOException, SQLException, ResponseException {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see server.database.requestHandler.AbstractRequest#updateAction(entities.Packet)
	 */
	@Override
	protected boolean updateAction(Packet<?> pkt) throws IOException, SQLException, ResponseException {
		ArrayList<String> whereVal = null;
		ArrayList<String> json = pkt.objectTranslation();
		switch (pkt.getSub_action()) {
		case config.packetTransfer.actions.ManageUsers.SUB_ACTION_UPDATE_ACCOUNT_DETAILS:
			entities.User user = AbstractJsonToString.toObject(entities.User.class, json.get(0));
			whereVal = AbstractRequest.createArrayList(user.getFirstName(), user.getLastName(), user.getPhoneNumber(),
					user.getEmail(), user.getUsername());
			return cdb.executeQuery(config.database.queries.User.MySQL_QUERY_UPDATE_ACCOUNT_DETAILS, whereVal);
		case config.packetTransfer.actions.ManageUsers.SUB_ACTION_UPDATE_ACCOUNT_PASSWORD:
			entities.User user1 = AbstractJsonToString.toObject(entities.User.class, json.get(0));
			whereVal = AbstractRequest.createArrayList(user1.getPassword(), user1.getUsername());
			return cdb.executeQuery(config.database.queries.User.MySQL_QUERY_UPDATE_ACCOUNT_PASSWORD, whereVal);
		case config.packetTransfer.actions.ManageUsers.SUB_ACTION_UPDATE_PAYMENT_METHOD:
			entities.CreditCard creditcard = AbstractJsonToString.toObject(entities.CreditCard.class, json.get(0));
			whereVal = AbstractRequest.createArrayList(String.valueOf(creditcard.getNumber()),
					String.valueOf(creditcard.getCvv()), creditcard.getExpireyDate(),
					creditcard.getOwner().getUsername());
			return cdb.executeQuery(config.database.queries.CreditCard.MySQL_QUERY_UPDATE_PAYMENT_METHOD, whereVal);
		case config.packetTransfer.actions.ManageUsers.SUB_ACTION_UPDATE_PERMISSION:
			entities.User user2 = AbstractJsonToString.toObject(entities.User.class, json.get(0));
			whereVal = AbstractRequest.createArrayList(String.valueOf(user2.getPermission().getPremission()),user2.getUsername());
			return cdb.executeQuery(config.database.queries.User.MySQL_QUERY_SET_PERMISSION, whereVal);
		default:
			throw new ResponseException(Packet.ACTION_TYPE.FATAL_ERROR);
		}
	}
}
