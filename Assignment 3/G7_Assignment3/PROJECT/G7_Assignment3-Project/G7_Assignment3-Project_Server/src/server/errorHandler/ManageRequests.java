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
 * The Class RateRequest.
 * Extends AbstractError
 * Adding execute error of action
 */
public class ManageRequests extends AbstractError {

	/**
	 * Instantiates a new manage requests.
	 *
	 * @param window the window
	 */
	public ManageRequests(String window) {
		super(window);
	}

	/* (non-Javadoc)
	 * @see server.errorHandler.AbstractError#getAction(entities.Packet)
	 */
	@Override
	protected Packet<String> getAction(Packet<String> pkt) {
		switch(pkt.getSub_action()) {
		case config.packetTransfer.actions.ManageRequests.SUB_ACTION_GET_ALL_RATE_REQUESTS:
			pkt.setData(config.packetTransfer.server.errors.Requests.SUB_ACTION_ERROR_GET_ALL_REQUESTS);
			break;
		case config.packetTransfer.actions.ManageRequests.SUB_ACTION_GET_ALL_VERSION_REQUESTS:
			pkt.setData(config.packetTransfer.server.errors.Requests.SUB_ACTION_ERROR_GET_ALL_REQUESTS);
			break;
		case config.packetTransfer.actions.ManageRequests.SUB_ACTION_GET_USER_RATE_REQUEST_RESPONSE:
			pkt.setData(config.packetTransfer.server.errors.Requests.SUB_ACTION_ERROR_GET_USER_REQUEST_RESPONSE);
			break;
		case config.packetTransfer.actions.ManageRequests.SUB_ACTION_GET_USER_VERSION_REQUEST_RESPONSE:
			pkt.setData(config.packetTransfer.server.errors.Requests.SUB_ACTION_ERROR_GET_USER_REQUEST_RESPONSE);
			break;
		case config.packetTransfer.actions.ManageRequests.SUB_ACTION_GET_ALL_RATES:
			pkt.setData(config.packetTransfer.server.errors.Rate.SUB_ACTION_ERROR_GET_RATES);
			
		}
		return pkt;
	}

	/* (non-Javadoc)
	 * @see server.errorHandler.AbstractError#deleteAction(entities.Packet)
	 */
	@Override
	protected Packet<String> deleteAction(Packet<String> pkt) {
		switch(pkt.getSub_action()) {
		case config.packetTransfer.actions.ManageRequests.SUB_ACTION_DELETE_RATE_REQUEST:
			pkt.setData(config.packetTransfer.server.errors.Requests.SUB_ACTION_ERROR_DELETE_REQUEST);
			break;
		case config.packetTransfer.actions.ManageRequests.SUB_ACTION_DELETE_VERSION_REQUEST:
			pkt.setData(config.packetTransfer.server.errors.Requests.SUB_ACTION_ERROR_DELETE_REQUEST);
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
		case config.packetTransfer.actions.ManageRequests.SUB_ACTION_ADD_RATE:
			pkt.setData(config.packetTransfer.server.errors.Requests.SUB_ACTION_ERROR_ADD_RATE);
			break;
		case config.packetTransfer.actions.ManageRequests.SUB_ACTION_ADD_SUBSCRIPTION_RATE_REQUEST:
			pkt.setData(config.packetTransfer.server.errors.Requests.SUB_ACTION_ERROR_ADD_RATE_REQUEST);
			break;
		case config.packetTransfer.actions.ManageRequests.SUB_ACTION_ADD_ONETIME_RATE_REQUEST:
			pkt.setData(config.packetTransfer.server.errors.Requests.SUB_ACTION_ERROR_ADD_RATE_REQUEST);
			break;
		case config.packetTransfer.actions.ManageRequests.SUB_ACTION_ADD_VERSION_REQUEST:
			pkt.setData(config.packetTransfer.server.errors.Requests.SUB_ACTION_ERROR_ADD_VERSION_REQUEST);
			break;
			
		case config.packetTransfer.actions.ManageRequests.SUB_ACTION_ADD_VERSION:
			pkt.setData(config.packetTransfer.server.errors.Requests.SUB_ACTION_ERROR_ADD_VERSION);
			break;
		}
		return pkt;
	}
	
	/* (non-Javadoc)
	 * @see server.errorHandler.AbstractError#updateAction(entities.Packet)
	 */
	@Override
	protected Packet<String> updateAction(Packet<String> pkt) {
		switch(pkt.getSub_action()) {
		case config.packetTransfer.actions.ManageRequests.SUB_ACTION_UPDATE_APPROVAL_RATE_REQUEST:
			pkt.setData(config.packetTransfer.server.errors.Requests.SUB_ACTION_ERROR_UPDATE_REQUEST);
			break;
		case config.packetTransfer.actions.ManageRequests.SUB_ACTION_UPDATE_APPROVAL_VERSION_REQUEST:
			pkt.setData(config.packetTransfer.server.errors.Requests.SUB_ACTION_ERROR_UPDATE_REQUEST);
			break;
		}
		return pkt;
	}
	
}
