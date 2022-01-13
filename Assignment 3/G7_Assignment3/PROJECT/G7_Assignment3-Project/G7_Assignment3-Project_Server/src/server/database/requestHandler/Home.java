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
 * The Class Home.
 * Extends AbstractRequest
 */
public class Home extends AbstractRequest {
	
	
	/**
	 * Instantiates a new home.
	 *
	 * @param window the window
	 * @param cdb the cdb
	 */
	public Home(String window, ClientDB cdb) {
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
		case config.packetTransfer.actions.Home.SUB_ACTION_GET_LOGIN:
			whereVal = AbstractRequest.createArrayList(res.get(config.database.tables.columns.User.USERNAME).toLowerCase(),
					res.get(config.database.tables.columns.User.PASSWORD));
			return AbstractJsonToString.hashMapToArrayListJsonEntities(entities.User::fromHashMap, cdb.executeSelectQuery(config.database.queries.User.MySQL_QUERY_GET_LOGIN, whereVal));
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
		ArrayList<String> whereVal = null;
		switch(pkt.getSub_action()) {
		case config.packetTransfer.actions.Home.SUB_ACTION_ADD_REGISTER:
			ArrayList<String> data = pkt.objectTranslation();
			entities.User newUser=  AbstractJsonToString.toObject(entities.User.class, data.get(0));
			entities.CreditCard newPayment =  AbstractJsonToString.toObject(entities.CreditCard.class, data.get(1));
			whereVal = AbstractRequest.createArrayList(
					newUser.getUsername(),newUser.getFirstName(), newUser.getLastName(), newUser.getEmail(),newUser.getPassword(),newUser.getPhoneNumber(),
					String.valueOf(entities.Permission.Role.CLIENT.getValue()),
					newUser.getUsername(), String.valueOf(newPayment.getNumber()),String.valueOf(newPayment.getCvv()),newPayment.getExpireyDate());//the client is not define the premission!!!!!
			
			return cdb.executeQuery(String.format("%s; %s;", config.database.queries.User.MySQL_QUERY_ADD_USER,config.database.queries.CreditCard.MySQL_QUERY_ADD_CREDITCARD), whereVal);
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
