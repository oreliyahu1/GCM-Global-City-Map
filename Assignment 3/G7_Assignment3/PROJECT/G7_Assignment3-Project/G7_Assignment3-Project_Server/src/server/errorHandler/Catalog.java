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
public class Catalog extends AbstractError {

	/**
	 * Instantiates a new catalog.
	 *
	 * @param window the window
	 */
	public Catalog(String window) {
		super(window);
	}

	/* (non-Javadoc)
	 * @see server.errorHandler.AbstractError#getAction(entities.Packet)
	 */
	@Override
	protected Packet<String> getAction(Packet<String> pkt) {
		switch (pkt.getSub_action()) {
		case config.packetTransfer.actions.Catalog.SUB_ACTION_GET_COUNTRIES:
			pkt.setData(config.packetTransfer.server.errors.Catalog.SUB_ACTION_ERROR_GET);
			break;
		case config.packetTransfer.actions.Catalog.SUB_ACTION_GET_COUNTRY_CITIES_NAMES:
			pkt.setData(config.packetTransfer.server.errors.Catalog.SUB_ACTION_ERROR_GET);
			break;
		case config.packetTransfer.actions.Catalog.SUB_ACTION_GET_CITY_MAPS:
			pkt.setData(config.packetTransfer.server.errors.Catalog.SUB_ACTION_ERROR_GET_CITY_MAPS);
			break;
		case config.packetTransfer.actions.Catalog.SUB_ACTION_GET_RECOMMENDED_TOURS_OF_CITY:
			pkt.setData(config.packetTransfer.server.errors.Catalog.SUB_ACTION_ERROR_GET_TOURS_OF_CITY);
			break;
		case config.packetTransfer.actions.Catalog.SUB_ACTION_GET_TOURS_OF_CITY:
			pkt.setData(config.packetTransfer.server.errors.Catalog.SUB_ACTION_ERROR_GET_RECOMMENDED_TOURS_OF_CITY);
			break;
		case config.packetTransfer.actions.Catalog.SUB_ACTION_GET_POIS_OF_CITY:
			pkt.setData(config.packetTransfer.server.errors.Catalog.SUB_ACTION_ERROR_GET_POIS_OF_CITY);
			break;
		case config.packetTransfer.actions.Catalog.SUB_ACTION_GET_POIS_OF_MAP:
			pkt.setData(config.packetTransfer.server.errors.Catalog.SUB_ACTION_ERROR_GET_POIS_OF_MAP);
			break;
		case config.packetTransfer.actions.Catalog.SUB_ACTION_GET_FULL_MAP_OF_CITY:
			pkt.setData(config.packetTransfer.server.errors.Catalog.SUB_ACTION_ERROR_GET_FULL_MAP_OF_CITY);
			break;
		case config.packetTransfer.actions.Catalog.SUB_ACTION_GET_POIS_OF_TOUR :
			pkt.setData(config.packetTransfer.server.errors.Catalog.SUB_ACTION_ERROR_GET_POIS_OF_TOUR);
			break;
		case config.packetTransfer.actions.Catalog.SUB_ACTION_GET_PURCHASES_BY_COUNTRY_USER:
			pkt.setData(config.packetTransfer.server.errors.Catalog.SUB_ACTION_ERROR_GET_PURCHASES_BY_COUNTRY_USER);
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
	protected Packet<String> addAction(Packet<String> pkt)  {
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
