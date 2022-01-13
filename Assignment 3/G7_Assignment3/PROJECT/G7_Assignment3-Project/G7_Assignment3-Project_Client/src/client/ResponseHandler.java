package client;

import java.io.IOException;
import java.util.HashMap;

import client.responseHandler.*;
import entities.Packet;
import guiControllers.LoadingController;
import javafx.application.Platform;

/**
 * The Class ResponseHandler.
 * Static class
 * Main response handler
 */
public class ResponseHandler extends AbstractResponse{

	/** The response handler. */
	private HashMap<String, AbstractResponse> responseHandler;
	
	/**
	 * Instantiates a new response handler.
	 */
	public ResponseHandler() {
		super(null);
		responseHandler = new HashMap<String, AbstractResponse>();
		//responseHandler.put(config.packetTransfer.actions.City.WINDOW, new City(config.packetTransfer.actions.City.WINDOW));
		responseHandler.put(config.packetTransfer.actions.Home.WINDOW, new Home(config.packetTransfer.actions.Home.WINDOW));
		responseHandler.put(config.packetTransfer.actions.Catalog.WINDOW, new Catalog(config.packetTransfer.actions.Catalog.WINDOW));
		responseHandler.put(config.packetTransfer.actions.EditCatalog.WINDOW, new EditCatalog(config.packetTransfer.actions.EditCatalog.WINDOW));
		responseHandler.put(config.packetTransfer.actions.Notification.WINDOW, new Notifications(config.packetTransfer.actions.Notification.WINDOW));
		responseHandler.put(config.packetTransfer.actions.ManageRequests.WINDOW, new ManageRequests(config.packetTransfer.actions.ManageRequests.WINDOW));
		responseHandler.put(config.packetTransfer.actions.Rate.WINDOW, new Rate(config.packetTransfer.actions.Rate.WINDOW));
		responseHandler.put(config.packetTransfer.actions.Profile.WINDOW, new Profile(config.packetTransfer.actions.Profile.WINDOW));
		responseHandler.put(config.packetTransfer.actions.Purchase.WINDOW, new Purchase(config.packetTransfer.actions.Purchase.WINDOW));
		responseHandler.put(config.packetTransfer.actions.ManageUsers.WINDOW, new ManageClients(config.packetTransfer.actions.ManageUsers.WINDOW));
		responseHandler.put(config.packetTransfer.actions.Download.WINDOW, new Download(config.packetTransfer.actions.Download.WINDOW));
		responseHandler.put(config.packetTransfer.actions.DailyReport.WINDOW, new Report(config.packetTransfer.actions.DailyReport.WINDOW));
		responseHandler.put(config.packetTransfer.actions.Search.WINDOW, new Search(config.packetTransfer.actions.Search.WINDOW));
		
	}

	/* (non-Javadoc)
	 * @see client.responseHandler.AbstractResponse#execute(entities.Packet)
	 */
	public boolean execute(Packet<?> pkt){
		System.out.println("ResponseHandler: package is entered processing");
		 //If need to update a GUI component from a non-GUI thread, you can use that to put your update in a queue and it will be handle by the GUI thread as soon as possible.
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				try {
					LoadingController.instance.hide();
					if(pkt.getAction() == Packet.ACTION_TYPE.FATAL_ERROR) {
						errorHandler(pkt);
					}
					responseHandler(pkt);
					System.out.println("ResponseHandler: package processing successful");
				}catch(IOException iex) {
					System.out.println("ResponseHandler: error " + iex.toString());
				}
		}});	
		return true;
	}
	
	/* (non-Javadoc)
	 * @see client.responseHandler.AbstractResponse#responseHandler(entities.Packet)
	 */
	@Override
	protected boolean responseHandler(Packet<?> pkt) throws IOException {
		AbstractResponse resHandler = responseHandler.get(pkt.getWindow());
		if(resHandler == null)
			throw new IOException();
		return resHandler.execute(pkt);
	}


	/* (non-Javadoc)
	 * @see client.responseHandler.AbstractResponse#errorHandler(entities.Packet)
	 */
	@Override
	protected boolean errorHandler(Packet<?> pkt) throws IOException {
		alertError(pkt);
		return true;
	}
}
