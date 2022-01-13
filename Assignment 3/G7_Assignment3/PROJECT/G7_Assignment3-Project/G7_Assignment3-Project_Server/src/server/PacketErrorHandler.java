package server;

import config.packetTransfer.server.ResponseErrors;
import java.io.IOException;
import java.util.HashMap;
import entities.Packet;
import server.errorHandler.*;

public class PacketErrorHandler {
	
	private HashMap<String, AbstractError> errorsHandler;
	
	public PacketErrorHandler() {
		this.errorsHandler = new HashMap<String, AbstractError>();
		errorsHandler.put(config.packetTransfer.actions.Home.WINDOW, new Home(config.packetTransfer.actions.Home.WINDOW));
		errorsHandler.put(config.packetTransfer.actions.Catalog.WINDOW, new Catalog(config.packetTransfer.actions.Catalog.WINDOW));
		errorsHandler.put(config.packetTransfer.actions.EditCatalog.WINDOW, new EditCatalog(config.packetTransfer.actions.EditCatalog.WINDOW));
		errorsHandler.put(config.packetTransfer.actions.Notification.WINDOW, new Notification(config.packetTransfer.actions.Notification.WINDOW));
		errorsHandler.put(config.packetTransfer.actions.ManageRequests.WINDOW, new ManageRequests(config.packetTransfer.actions.ManageRequests.WINDOW));
		errorsHandler.put(config.packetTransfer.actions.Rate.WINDOW, new Rate(config.packetTransfer.actions.Rate.WINDOW));
		errorsHandler.put(config.packetTransfer.actions.Purchase.WINDOW, new Purchase(config.packetTransfer.actions.Purchase.WINDOW));
		errorsHandler.put(config.packetTransfer.actions.Profile.WINDOW, new Profile(config.packetTransfer.actions.Profile.WINDOW));
		errorsHandler.put(config.packetTransfer.actions.ManageUsers.WINDOW, new ManageUsers(config.packetTransfer.actions.ManageUsers.WINDOW));
		errorsHandler.put(config.packetTransfer.actions.Activity.WINDOW, new Activity(config.packetTransfer.actions.Activity.WINDOW));
		errorsHandler.put(config.packetTransfer.actions.Download.WINDOW, new Activity(config.packetTransfer.actions.Download.WINDOW));
		errorsHandler.put(config.packetTransfer.actions.ExternalMap.WINDOW, new ExternalMap(config.packetTransfer.actions.ExternalMap.WINDOW)); //FOR EXTERNAL MAP
		errorsHandler.put(config.packetTransfer.actions.DailyReport.WINDOW, new DailyReport(config.packetTransfer.actions.DailyReport.WINDOW));
	}
	
	public Packet<?> handler(Packet.ACTION_TYPE at, Packet<?> pkt) throws IOException {
		Packet<String> res = new Packet<String>(pkt.getAction(), pkt.getWindow(), pkt.getSub_window(), pkt.getSub_action(), "");
		if(at == Packet.ACTION_TYPE.FATAL_ERROR) {
			res.setAction(at);
			res.setData(ResponseErrors.SERVER_RESPONSE_FATAL_ERROR);
			return res;
		}
		
		if(at != Packet.ACTION_TYPE.RESPONSE_ERROR)
			return res;

		AbstractError errHandler = errorsHandler.get(pkt.getWindow());
		if(errHandler == null)
			throw new IOException();
		errHandler.handle(res);
		res.setAction(at);
		return res;
	}

	
}
