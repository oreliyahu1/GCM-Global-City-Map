/*
 * this class extends the abstract class AbstractError.
 * executing the handle function from super class , direct the program to diagnose 
 * the action type then call one from these functions : addAction ,getAction ,updateAction,deleteAction 
 * to handle errors and show message correspondingly
*/
package server.errorHandler;

import entities.Packet;

// TODO: Auto-generated Javadoc
/**
 * The Class Catalog.
 * Extends AbstractError
 * Adding execute error of action
 */
public class Notification extends AbstractError{

	/**
	 * Instantiates a new notification.
	 *
	 * @param window the window
	 */
	public Notification(String window) {
		super(window);
	}
	
	/* (non-Javadoc)
	 * @see server.errorHandler.AbstractError#getAction(entities.Packet)
	 */
	@Override
	protected Packet<String> getAction(Packet<String> pkt) {
		switch(pkt.getSub_action()) {
		case config.packetTransfer.actions.Notification.SUB_ACTION_GET_ALL_NOTIFICATIONS:
			pkt.setData(config.packetTransfer.server.errors.Notification.SUB_ACTION_ERROR_GET_NOTIFICATIONS);
			break;
			case config.packetTransfer.actions.Notification.SUB_ACTION_GET_NEW_NOTIFICATIONS:
			pkt.setData(config.packetTransfer.server.errors.Notification.SUB_ACTION_ERROR_GET_NOTIFICATIONS);
			break;
		}
		return pkt;
	}

	/* (non-Javadoc)
	 * @see server.errorHandler.AbstractError#deleteAction(entities.Packet)
	 */
	@Override
	protected Packet<String> deleteAction(Packet<String> pkt) {
		switch(pkt.getSub_action()) {
		case config.packetTransfer.actions.Notification.SUB_ACTION_DELETE_NOTIFICATION:
			pkt.setData(config.packetTransfer.server.errors.Notification.SUB_ACTION_ERROR_DELETE_NOTIFICATIONS);
			break;
		case config.packetTransfer.actions.Notification.SUB_ACTION_DELETE_ALL_NOTIFICATIONS:
			pkt.setData(config.packetTransfer.server.errors.Notification.SUB_ACTION_ERROR_DELETE_NOTIFICATIONS);
			break;
		}
		return pkt;
	}

	/* (non-Javadoc)
	 * @see server.errorHandler.AbstractError#addAction(entities.Packet)
	 */
	@Override
	protected Packet<String> addAction(Packet<String> pkt) {
		switch(pkt.getSub_action()) {
		case config.packetTransfer.actions.Notification.SUB_ACTION_ADD_NOTIFICATION:
			//pkt.setData(data);
			break;
		}
		return pkt;
	}

	/* (non-Javadoc)
	 * @see server.errorHandler.AbstractError#updateAction(entities.Packet)
	 */
	@Override
	protected Packet<String> updateAction(Packet<String> pkt)  {
		switch(pkt.getSub_action()) {
		case config.packetTransfer.actions.Notification.SUB_ACTION_UPDATE_READ_NOTIFICATION:
			pkt.setData(config.packetTransfer.server.errors.Notification.SUB_ACTION_ERROR_DELETE_NOTIFICATIONS);
			break;
		}
		return pkt;
	}
}
