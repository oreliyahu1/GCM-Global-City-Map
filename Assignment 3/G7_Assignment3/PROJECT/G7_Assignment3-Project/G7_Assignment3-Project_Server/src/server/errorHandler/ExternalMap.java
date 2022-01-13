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
 * !FOR EXTERNAL SYSTEM ONLY!
 * The ExternalMap Home.
 * Adding execute error of action
 */
public class ExternalMap extends AbstractError {
	
	
	/**
	 * Instantiates a new ExternalMap.
	 *
	 * @param window the window
	 */
	public ExternalMap(String window) {
		super(window);
	}
	
	/* (non-Javadoc)
	 * @see server.errorHandler.AbstractError#getAction(entities.Packet)
	 */
	@Override
	protected Packet<String> getAction(Packet<String> pkt) {
		switch(pkt.getSub_action()) {
		case config.packetTransfer.actions.ExternalMap.SUB_ACTION_GET_ALL_CITIES:
			pkt.setData(config.packetTransfer.server.errors.ExternalMap.SUB_ACTION_ERROR_GET_ALL_CITIES);
			break;
		case config.packetTransfer.actions.ExternalMap.SUB_ACTION_GET_ALL_COUNTRIES:
			pkt.setData(config.packetTransfer.server.errors.ExternalMap.SUB_ACTION_ERROR_GET_ALL_COUNTRIES);
			break;
		case config.packetTransfer.actions.ExternalMap.SUB_ACTION_GET_ALL_CITY_EXTERNAL_MAPS:
			pkt.setData(config.packetTransfer.server.errors.ExternalMap.SUB_ACTION_ERROR_GET_ALL_CITY_EXTERNAL_MAPS);
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
		case config.packetTransfer.actions.ExternalMap.SUB_ACTION_ADD_CITY_TO_COUNTRY:
			pkt.setData(config.packetTransfer.server.errors.ExternalMap.SUB_ACTION_ERROR_ADD_CITY_TO_COUNTRY);
			break;
		case config.packetTransfer.actions.ExternalMap.SUB_ACTION_ADD_EXTERNAL_MAP:
			pkt.setData(config.packetTransfer.server.errors.ExternalMap.SUB_ACTION_ERROR_ADD_EXTERNAL_MAP);
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
		case config.packetTransfer.actions.ExternalMap.SUB_ACTION_UPDATE_EXTERNAL_MAP:
			pkt.setData(config.packetTransfer.server.errors.ExternalMap.SUB_ACTION_ERROR_UPDATE_EXTERNAL_MAP);
			break;
	}
		return pkt;
	}

}
