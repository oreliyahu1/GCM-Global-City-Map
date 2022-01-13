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
 * The Class Home.
 * Extends AbstractError
 * Adding execute error of action
 */
public class Home extends AbstractError {
	
	
	/**
	 * Instantiates a new home.
	 *
	 * @param window the window
	 */
	public Home(String window) {
		super(window);
	}
	
	/* (non-Javadoc)
	 * @see server.errorHandler.AbstractError#getAction(entities.Packet)
	 */
	@Override
	protected Packet<String> getAction(Packet<String> pkt) {
		switch(pkt.getSub_action()) {
		case config.packetTransfer.actions.Home.SUB_ACTION_GET_LOGIN:
			pkt.setData(config.packetTransfer.server.errors.Home.SUB_ACTION_ERROR_GET_LOGIN);
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
		switch(pkt.getSub_action()) {
		case config.packetTransfer.actions.Home.SUB_ACTION_ADD_REGISTER:
			pkt.setData(config.packetTransfer.server.errors.Home.SUB_ACTION_ERROR_ADD_REGISTER);
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
		case config.packetTransfer.actions.Home.SUB_ACTION_UPDATE_LOGOUT:
			pkt.setData(config.packetTransfer.server.errors.Home.SUB_ACTION_ERROR_UPDATE_LOGOUT);
			break;
	}
		return pkt;
	}

}
