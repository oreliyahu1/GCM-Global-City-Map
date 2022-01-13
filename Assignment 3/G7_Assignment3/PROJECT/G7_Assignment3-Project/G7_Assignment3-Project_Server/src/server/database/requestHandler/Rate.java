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
 * The Class RateRequest.
 * Extends AbstractRequest
 */
public class Rate extends AbstractRequest {


	/**
	 * Instantiates a new rate.
	 *
	 * @param window the window
	 * @param cdb the cdb
	 */
	public Rate(String window, ClientDB cdb) {
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
		case config.packetTransfer.actions.Rate.SUB_ACTION_GET_ALL_RATES:
			return AbstractJsonToString.hashMapToArrayListJsonEntities(entities.Rate::fromHashMap, cdb.executeSelectQuery(config.database.queries.Rate.MySQL_QUERY_GET_ALL_RATES));
		case config.packetTransfer.actions.Rate.SUB_ACTION_GET_ONETIME_RATES:
			whereVal = AbstractRequest.createArrayList(res.get(config.database.tables.columns.Rate.TYPE));
			return AbstractJsonToString.hashMapToArrayListJsonEntities(entities.Rate::fromHashMap, cdb.executeSelectQuery(config.database.queries.Rate.MySQL_QUERY_GET_RATES_BY_TYPE, whereVal));
		case config.packetTransfer.actions.Rate.SUB_ACTION_GET_SUBSCRIPTION_RATES:
			whereVal = AbstractRequest.createArrayList(res.get(config.database.tables.columns.Rate.TYPE));
			return AbstractJsonToString.hashMapToArrayListJsonEntities(entities.Rate::fromHashMap, cdb.executeSelectQuery(config.database.queries.Rate.MySQL_QUERY_GET_RATES_BY_TYPE, whereVal));
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
		case config.packetTransfer.actions.Rate.SUB_ACTION_DELETE_RATE:
			entities.Rate rate =  AbstractJsonToString.toObject(entities.Rate.class, json.get(0));
			whereVal = AbstractRequest.createArrayList(String.valueOf(rate.getPrice()),String.valueOf(rate.getDays()));
			return cdb.executeQuery(config.database.queries.Rate.MySQL_QUERY_DELETE_RATE, whereVal);
		default:
			throw new ResponseException(Packet.ACTION_TYPE.FATAL_ERROR);
		}
	}

	/* (non-Javadoc)
	 * @see server.database.requestHandler.AbstractRequest#addAction(entities.Packet)
	 */
	@Override
	protected boolean addAction(Packet<?> pkt) throws IOException, SQLException, ResponseException {
		return false;
	}

	/* (non-Javadoc)
	 * @see server.database.requestHandler.AbstractRequest#updateAction(entities.Packet)
	 */
	@Override
	protected boolean updateAction(Packet<?> pkt) throws IOException, SQLException, ResponseException {
		ArrayList<String> whereVal = null;
		ArrayList<String> json = pkt.objectTranslation();
		switch(pkt.getSub_action()) {
		case config.packetTransfer.actions.Rate.SUB_ACTION_UPDATE_RATE:
			entities.Rate old_rate =  AbstractJsonToString.toObject(entities.Rate.class, json.get(0));
			entities.Rate new_rate =  AbstractJsonToString.toObject(entities.Rate.class, json.get(0));
			whereVal = AbstractRequest.createArrayList(String.valueOf(new_rate.getPrice()),String.valueOf(new_rate.getDays()),new_rate.getRtype().toString(),
					old_rate.getRtype().toString(),String.valueOf(old_rate.getDays()));
			return cdb.executeQuery(config.database.queries.Rate.MySQL_QUERY_UPDATE_RATE, whereVal);
		default:
			throw new ResponseException(Packet.ACTION_TYPE.FATAL_ERROR);
		}
	}
	
}
