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
 * The Class Profile.
 * Extends AbstractError
 * Adding execute error of action
 */
public class ManageUsers extends AbstractError {

	/**
	 * Instantiates a new manage users.
	 *
	 * @param window the window
	 */
	public ManageUsers(String window) {
		super(window);
	}

	/* (non-Javadoc)
	 * @see server.errorHandler.AbstractError#getAction(entities.Packet)
	 */
	@Override
	protected Packet<String> getAction(Packet<String> pkt) {
		switch (pkt.getSub_action()) {
		case config.packetTransfer.actions.ManageUsers.SUB_ACTION_GET_ACCOUNT_DETAILS:
			pkt.setData(config.packetTransfer.server.errors.ManageUsers.SUB_ACTION_ERROR_GET_ACCOUNT_DETAILS);
			break;
		case config.packetTransfer.actions.ManageUsers.SUB_ACTION_GET_PAYMENT_METHOD:
			pkt.setData(config.packetTransfer.server.errors.ManageUsers.SUB_ACTION_ERROR_GET_PAYMENT_METHOD);
			break;
		case config.packetTransfer.actions.ManageUsers.SUB_ACTION_GET_ALL_USERS:
			pkt.setData(config.packetTransfer.server.errors.ManageUsers.SUB_ACTION_ERROR_GET_ALL_USERS);
			break;
		case config.packetTransfer.actions.ManageUsers.SUB_ACTION_GET_ALL_USER_PURCHASES:
		pkt.setData(config.packetTransfer.server.errors.ManageUsers.SUB_ACTION_ERROR_GET_ALL_USER_PURCHASES);
		break;
		}
		return pkt;
	}

	/* (non-Javadoc)
	 * @see server.errorHandler.AbstractError#deleteAction(entities.Packet)
	 */
	@Override
	protected Packet<String> deleteAction(Packet<String> pkt) {
		return pkt;
	}

	/* (non-Javadoc)
	 * @see server.errorHandler.AbstractError#addAction(entities.Packet)
	 */
	@Override
	protected Packet<String> addAction(Packet<String> pkt) {
		return pkt;
	}

	/* (non-Javadoc)
	 * @see server.errorHandler.AbstractError#updateAction(entities.Packet)
	 */
	@Override
	protected Packet<String> updateAction(Packet<String> pkt) {
		switch (pkt.getSub_action()) {
		case config.packetTransfer.actions.ManageUsers.SUB_ACTION_UPDATE_ACCOUNT_DETAILS:
			pkt.setData(config.packetTransfer.server.errors.ManageUsers.SUB_ACTION_ERROR_UPDATE_ACCOUNT_DETAILS);
			break;
		case config.packetTransfer.actions.ManageUsers.SUB_ACTION_UPDATE_ACCOUNT_PASSWORD:
			pkt.setData(config.packetTransfer.server.errors.ManageUsers.SUB_ACTION_ERROR_UPDATE_ACCOUNT_PASSWORD);
			break;
		case config.packetTransfer.actions.ManageUsers.SUB_ACTION_UPDATE_PAYMENT_METHOD:
			pkt.setData(config.packetTransfer.server.errors.ManageUsers.SUB_ACTION_ERROR_UPDATE_PAYMENT_METHOD);
			break;
		case config.packetTransfer.actions.ManageUsers.SUB_ACTION_UPDATE_PERMISSION:
			pkt.setData(config.packetTransfer.server.errors.ManageUsers.SUB_ACTION_ERROR_UPDATE_PERMISSION);
			break;
		}
		return pkt;
	}
}
