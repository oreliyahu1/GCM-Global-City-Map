/*
 * this class extends AbstractResponse 
 * it defines the next action according to the response status
 */

package client.responseHandler;

import java.io.IOException;
import entities.Packet;

/**
 * The response handler of home. Static class
 */
public class Rate extends AbstractResponse {

	public Rate(String window) {
		super(window);
	}

	@Override
	protected boolean responseHandler(Packet<?> pkt) throws IOException {
		System.out.println(pkt.getSub_action().toString());
		switch (pkt.getSub_action()) {
	
		case config.packetTransfer.actions.Rate.SUB_ACTION_DELETE_RATE:

			break;

		default:
			return false;
		}
		return true;
	}


	@Override
	protected boolean errorHandler(Packet<?> pkt) throws IOException {
		switch (pkt.getSub_action()) {
		case config.packetTransfer.actions.Rate.SUB_ACTION_DELETE_RATE:
			
			break;
		default:
			alertError(pkt);
			return false;
		}
		return true;
	}

}
