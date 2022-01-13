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
public class Download extends AbstractError {


	/**
	 * Instantiates a new download.
	 *
	 * @param window the window
	 */
	public Download(String window) {
		super(window);
	}

	/* (non-Javadoc)
	 * @see server.errorHandler.AbstractError#getAction(entities.Packet)
	 */
	@Override
	protected Packet<String> getAction(Packet<String> pkt) {
		switch(pkt.getSub_action()) {
		case config.packetTransfer.actions.Download.SUB_ACTION_GET_CITY_MAPS:
			pkt.setData(config.packetTransfer.server.errors.Download.SUB_ACTION_ERROR_GET_CITY_MAPS);
			break;
			case config.packetTransfer.actions.Download.SUB_ACTION_GET_FULL_MAP_BY_CITY:
				pkt.setData(config.packetTransfer.server.errors.Download.SUB_ACTION_ERROR_GET_FULL_MAP_BY_CITY);
			break;
			case config.packetTransfer.actions.Download.SUB_ACTION_GET_POISMAP_BY_MAP:
				pkt.setData(config.packetTransfer.server.errors.Download.SUB_ACTION_ERROR_GET_POISMAP_BY_MAP);
				
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
		return pkt;
	}
	
}
