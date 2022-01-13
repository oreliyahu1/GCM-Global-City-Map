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
 * The Class Activity.
 * Extends AbstractRequest
 */
public class Activity extends AbstractRequest {

	/**
	 * Instantiates a new activity.
	 *
	 * @param window the window
	 * @param cdb the cdb
	 */
	public Activity(String window, ClientDB cdb) {
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
		case config.packetTransfer.actions.Activity.SUB_ACTION_GET_ALL_ACTIVITIES://get all activities
			whereVal = AbstractRequest.createArrayList(res.get(config.database.tables.columns.Activity.USERNAME));
			return AbstractJsonToString.hashMapToArrayListJsonEntities(entities.Activity::fromHashMap, cdb.executeSelectQuery(config.database.queries.Activity.MySQL_QUERY_GET_ALL_ACTIVITIES_BY_USERNAME, whereVal));
		default:
			throw new ResponseException(Packet.ACTION_TYPE.FATAL_ERROR);
		}
	}

	/* (non-Javadoc)
	 * @see server.database.requestHandler.AbstractRequest#deleteAction(entities.Packet)
	 */
	@Override
	protected boolean deleteAction(Packet<?> pkt) throws IOException, SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see server.database.requestHandler.AbstractRequest#addAction(entities.Packet)
	 */
	@Override
	protected boolean addAction(Packet<?> pkt) throws IOException, SQLException, ResponseException {
		ArrayList<String> whereVal = null;
		ArrayList<String> data = pkt.objectTranslation();
		switch (pkt.getSub_action()) {
		case config.packetTransfer.actions.Activity.SUB_ACTION_ADD_VIEW://add view activity
			entities.Activity activity = AbstractJsonToString.toObject(entities.Activity.class, data.get(0));
			whereVal = AbstractRequest.createArrayList(
					activity.getPurchase().getCity().getName(),
					String.valueOf(activity.getPurchase().getId()),
					activity.getPurchase().getCity().getCountry().getShortName(),
					String.valueOf(activity.getPurchase().getCity().getVersion()),
					entities.Activity.type.VIEW.toString(),
					activity.getMap().getName(),
					activity.getUser().getUsername());
			return cdb.executeQuery(config.database.queries.Activity.MySQL_QUERY_ADD_ACTIVITY, whereVal);
		case config.packetTransfer.actions.Activity.SUB_ACTION_ADD_DOWNLOAD://add download activity
			entities.Activity activity2 = AbstractJsonToString.toObject(entities.Activity.class, data.get(0));
			whereVal = AbstractRequest.createArrayList(
					activity2.getPurchase().getCity().getName(),
					String.valueOf(activity2.getPurchase().getId()),
					activity2.getPurchase().getCity().getCountry().getShortName(),
					activity2.getPurchase().getCity().getVersion().toString(),
					entities.Activity.type.DOWNLOAD.toString(),
					activity2.getMap().getName(),
					activity2.getUser().getUsername());
			return cdb.executeQuery(config.database.queries.Activity.MySQL_QUERY_ADD_ACTIVITY, whereVal);
		default:
			throw new ResponseException(Packet.ACTION_TYPE.FATAL_ERROR);
		}
	}

	/* (non-Javadoc)
	 * @see server.database.requestHandler.AbstractRequest#updateAction(entities.Packet)
	 */
	@Override
	protected boolean updateAction(Packet<?> pkt) throws IOException, SQLException, ResponseException {
		return false;
	}
}
