/*
 * this class extends AbstractResponse 
 * it defines the next action according to the response status
 */

package client.responseHandler;

import java.io.IOException;
import java.util.ArrayList;
import entities.AbstractJsonToString;
import entities.Packet;
/**
 * The response handler of home.
 * Static class 
 */
public class Report extends AbstractResponse{
	
	public Report(String window) {
		super(window);
	}

	@Override
	protected boolean responseHandler(Packet<?> pkt) throws IOException {
		ArrayList<String> res;
		switch(pkt.getSub_action()) {
			case config.packetTransfer.actions.DailyReport.SUB_ACTION_GET_REPORTS:
				res = pkt.objectTranslation();
				ArrayList<entities.DailyReport> reports = new ArrayList<entities.DailyReport>();
				for(String json : res)
					reports.add(AbstractJsonToString.toObject(entities.DailyReport.class, json));
				guiControllers.Reports_Controller.instance.get_reports(reports);
				
				
			break;
	
			default:
				return false;
		}	
		return true;
	}

	@Override
	protected boolean errorHandler(Packet<?> pkt) throws IOException {
		switch(pkt.getSub_action()) {
		case config.packetTransfer.actions.DailyReport.SUB_ACTION_GET_REPORTS:
			//error adding purchase
		break;

			default:
				alertError(pkt);
				return false;
		}
		return true;
	}


}
