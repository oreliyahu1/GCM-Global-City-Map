package guiControllers;
import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import animatefx.SlideInUp;
import guiControllers.FxmlView;
import guiControllers.HomeController;
import guiControllers.item.MessageBase;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;


/**
	 * The Class Notification_Controller.
	 */
	public class Notification_Controller {

	    /** The resources. */
    	@FXML
	    private ResourceBundle resources;

	    /** The location. */
    	@FXML
	    private URL location;

	    /** The pnl overview. */
    	@FXML
	    private Pane pnlOverview;

	    /** The pn items. */
    	@FXML
	    private VBox pnItems;

	    /** The n. */
    	Node[] n = new Node[1];
	  
	
		/** The instance. */
		public static Notification_Controller instance;
		
		/**
		 * Initialize.
		 */
		@FXML
		void initialize() {
			Notification_Controller.instance = this;
			client.requestHandler.Notifications.getAllNotifications(HomeController.instance.user);
			
		}
		
		/**
		 * Start.
		 *
		 * @param pane the pane
		 * @throws Exception the exception
		 */
		public void start(Pane pane) throws Exception {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getResource(FxmlView.NOTIFICATIONS.getFXML()));
			HomeController.instance.Set_Title(FxmlView.NOTIFICATIONS.getTitle());
			Node n = null;
			try {
				n = fxmlLoader.load();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			pane.getChildren().clear();
			pane.getChildren().add(n);
		}
		
		/**
		 * Btn delete all clicked.
		 *
		 * @param event the event
		 */
		@FXML
	    void btn_Delete_all_clicked(MouseEvent event) {
			Alert alert = new Alert(AlertType.WARNING, "Are you sure to Delete All MESSAGES?",
					ButtonType.YES, ButtonType.NO);

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.YES) {
				client.requestHandler.Notifications.Delete_ALLNotifications(HomeController.instance.user.getUsername());
			    
			}	
			
		}
		
		/**
		 * Refresh notifications.
		 */
		public void refresh_notifications() {
			pnItems.getChildren().clear();
			client.requestHandler.Notifications.getAllNotifications(HomeController.instance.user);
		}
		
		/**
		 * Gets the allnotifications.
		 *
		 * @param messages the messages
		 * @return the allnotifications
		 */
		public void getAllnotifications(ArrayList<entities.Message> messages) {
			pnItems.getChildren().clear();

			Runnable runn = new Runnable() {
				@Override
				public void run() {
					for (entities.Message msg : messages) {
						MessageBase sp = new MessageBase(msg);
						sp.setOnMouseEntered(event -> {
							sp.setStyle("-fx-background-color : #BDBBC3");
						});
						sp.setOnMouseExited(event -> {
							sp.setStyle("-fx-background-color : #EBE8F9");
						});
						sp.setOnMouseClicked(event -> {
							sp.show_msg();
							sp.updateimage();
	
					  	
						});
						Platform.runLater(() -> {
							pnItems.getChildren().add(sp);
							new SlideInUp(sp).play();
						});
					}

					


				}
			};
			Thread thread = new Thread(runn);
			thread.start();
		}

	}
	


