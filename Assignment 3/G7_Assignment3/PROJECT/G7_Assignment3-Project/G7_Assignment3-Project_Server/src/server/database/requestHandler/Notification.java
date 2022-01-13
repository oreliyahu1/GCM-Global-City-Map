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
 * The Class Catalog.
 * Extends AbstractRequest
 */
public class Notification extends AbstractRequest{

	/**
	 * Instantiates a new notification.
	 *
	 * @param window the window
	 * @param cdb the cdb
	 */
	public Notification(String window, ClientDB cdb) {
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
		case config.packetTransfer.actions.Notification.SUB_ACTION_GET_ALL_NOTIFICATIONS:
			whereVal = AbstractRequest.createArrayList(res.get(config.database.tables.columns.Message.TO));
			return AbstractJsonToString.hashMapToArrayListJsonEntities(entities.Message::fromHashMap, cdb.executeSelectQuery(config.database.queries.Message.MySQL_QUERY_GET_ALL_NOTIFICATIONS, whereVal));
		case config.packetTransfer.actions.Notification.SUB_ACTION_GET_NEW_NOTIFICATIONS:
			whereVal = AbstractRequest.createArrayList(res.get(config.database.tables.columns.Message.TO));
			return AbstractJsonToString.hashMapToArrayListJsonEntities(entities.Message::fromHashMap, cdb.executeSelectQuery(config.database.queries.Message.MySQL_QUERY_GET_NEW_NOTIFICATIONS, whereVal));
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
		HashMap<String, String> res = pkt.objectTranslationElem();
		switch(pkt.getSub_action()) {
		case config.packetTransfer.actions.Notification.SUB_ACTION_DELETE_NOTIFICATION:
			whereVal = AbstractRequest.createArrayList(res.get(config.database.tables.columns.Message.ID));
			return cdb.executeQuery(config.database.queries.Message.MySQL_QUERY_DELETE_NOTIFICATION, whereVal);
		case config.packetTransfer.actions.Notification.SUB_ACTION_DELETE_ALL_NOTIFICATIONS:
			whereVal = AbstractRequest.createArrayList(res.get(config.database.tables.columns.Message.ID));
			return cdb.executeQuery(config.database.queries.Message.MySQL_QUERY_DELETE_ALL_NOTIFICATIONS, whereVal);
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
		switch(pkt.getSub_action()) {
		case config.packetTransfer.actions.Notification.SUB_ACTION_ADD_NOTIFICATION:
			ArrayList<String> data = pkt.objectTranslation();
			entities.Message message =  AbstractJsonToString.toObject(entities.Message.class, data.get(0));
			whereVal = AbstractRequest.createArrayList(message.getFrom().getUsername(),
					message.getTo().getUsername(),
					message.getSubject(), message.getMessage());
			return cdb.executeQuery(config.database.queries.Message.MySQL_QUERY_ADD_NOTIFICATION, whereVal);
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
		HashMap<String, String> res = pkt.objectTranslationElem();
		switch(pkt.getSub_action()) {
		case config.packetTransfer.actions.Notification.SUB_ACTION_UPDATE_READ_NOTIFICATION:
			whereVal = AbstractRequest.createArrayList(res.get(config.database.tables.columns.Message.ID));
			return cdb.executeQuery(config.database.queries.Message.MySQL_QUERY_UPDATE_READ_NOTIFICATION, whereVal);
		default:
			throw new ResponseException(Packet.ACTION_TYPE.FATAL_ERROR);
		}
	}
}
