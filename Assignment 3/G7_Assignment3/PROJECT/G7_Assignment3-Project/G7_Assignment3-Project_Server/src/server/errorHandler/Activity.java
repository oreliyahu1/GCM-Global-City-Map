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
 * The Class Activity.
 * Extends AbstractError
 * Adding execute error of action
 */
public class Activity extends AbstractError {

	/**
	 * Instantiates a new activity.
	 *
	 * @param window the window
	 */
	public Activity(String window) {
		super(window);
	}

	/* (non-Javadoc)
	 * @see server.errorHandler.AbstractError#getAction(entities.Packet)
	 */
	@Override
	protected Packet<String> getAction(Packet<String> pkt) {
		switch(pkt.getSub_action()) {
		case config.packetTransfer.actions.Activity.SUB_ACTION_GET_ALL_ACTIVITIES:
			pkt.setData(config.packetTransfer.server.errors.Activity.SUB_ACTION_ERROR_GET_ALL_ACTIVITIES);	
		break;
		}
		return pkt;

	}

	/* (non-Javadoc)
	 * @see server.errorHandler.AbstractError#deleteAction(entities.Packet)
	 */
	@Override
	protected Packet<String> deleteAction(Packet<String> pkt) {
		// TODO Auto-generated method stub
		return pkt;
	}

	/* (non-Javadoc)
	 * @see server.errorHandler.AbstractError#addAction(entities.Packet)
	 */
	@Override
	protected Packet<String> addAction(Packet<String> pkt) {
		switch (pkt.getSub_action()) {
		case config.packetTransfer.actions.Activity.SUB_ACTION_ADD_VIEW:
			pkt.setData(config.packetTransfer.server.errors.Activity.SUB_ACTION_ERROR_ADD_ACTIVITY);
			break;
		case config.packetTransfer.actions.Activity.SUB_ACTION_ADD_DOWNLOAD:
			pkt.setData(config.packetTransfer.server.errors.Activity.SUB_ACTION_ERROR_ADD_ACTIVITY);
			break;
		}
		return pkt;
	}
	
	/* (non-Javadoc)
	 * @see server.errorHandler.AbstractError#updateAction(entities.Packet)
	 */
	@Override
	protected Packet<String> updateAction(Packet<String> pkt) {
		return pkt;
	}
}
