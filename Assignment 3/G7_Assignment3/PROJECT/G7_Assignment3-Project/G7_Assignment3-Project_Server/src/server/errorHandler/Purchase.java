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
 * The Class Purchase.
 * Extends AbstractError
 * Adding execute error of action
 */
public class Purchase extends AbstractError{
	
	/**
	 * Instantiates a new purchase.
	 *
	 * @param window the window
	 */
	public Purchase(String window) {
		super(window);
	}
	
		/* (non-Javadoc)
		 * @see server.errorHandler.AbstractError#getAction(entities.Packet)
		 */
		@Override
	protected Packet<String> getAction(Packet<String> pkt) {
		switch(pkt.getSub_action()) {
		case config.packetTransfer.actions.Purchase.SUB_ACTION_GET_RATES:
			pkt.setData(config.packetTransfer.server.errors.Purchase.SUB_ACTION_ERROR_GET_RATES);
			break;
			
		case config.packetTransfer.actions.Purchase.SUB_ACTION_GET_PURCHASES_EXPIRES_IN_3DAYS:
			pkt.setData(config.packetTransfer.server.errors.Purchase.SUB_ACTION_ERROR_GET_PURCHASES_EXPIRES_IN_3DAYS);
			break;
		case config.packetTransfer.actions.Purchase.SUB_ACTION_GET_USER_UNEXPIRED_PURCHASES:
			pkt.setData(config.packetTransfer.server.errors.Purchase.SUB_ACTION_ERROR_GET_USER_UNEXPIRED_PURCHASES);
			break;
		case config.packetTransfer.actions.Purchase.SUB_ACTION_GET_PAYMENT_DETAILS:
			pkt.setData(config.packetTransfer.server.errors.Purchase.SUB_ACTION_ERROR_GET_PAYMENT_DETAILS);
			break;
		case config.packetTransfer.actions.Purchase.SUB_ACTION_GET_ALL_USER_PURCHASES:
			pkt.setData(config.packetTransfer.server.errors.Purchase.SUB_ACTION_ERROR_GET_ALL_USER_PURCHASES);
			
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
		case config.packetTransfer.actions.Purchase.SUB_ACTION_ADD_PURCHASE:
			pkt.setData(config.packetTransfer.server.errors.Purchase.SUB_ACTION_ERROR__ADD_PURCHASE);
			break;
		case config.packetTransfer.actions.Purchase.SUB_ACTION_ADD_RENEWAL:
			pkt.setData(config.packetTransfer.server.errors.Purchase.SUB_ACTION_ERROR_ADD_RENEWAL);
			break;
		case config.packetTransfer.actions.Purchase.SUB_ACTION_GET_LAST_USER_PURCHASE:
			pkt.setData(config.packetTransfer.server.errors.Purchase.SUB_ACTION_ERROR_GET_LAST_USER_PURCHASE);
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
