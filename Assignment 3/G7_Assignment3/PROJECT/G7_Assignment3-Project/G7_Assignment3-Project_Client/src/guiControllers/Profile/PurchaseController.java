package guiControllers.Profile;


import java.util.ArrayList;

import animatefx.SlideInUp;
import guiControllers.item.Purchaseitem2;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * The Class PurchaseController.
 */
public class PurchaseController {

	/** The instance. */
	public static PurchaseController instance;
	
	/** The clientuser. */
	public static entities.User clientuser;
    
    /** The pn items. */
    @FXML
    private VBox pnItems;
    
    
    /** The btn exit. */
    @FXML
    private ImageView btn_exit;

    /**
     * Exit all.
     *
     * @param event the event
     */
    @FXML
    void exit_all(MouseEvent event) {
    	Stage s = (Stage)btn_exit.getScene().getWindow();
    	s.close();
    }
	
	/**
	 * Initialize.
	 */
	@FXML
    void initialize() {
		PurchaseController.instance = this;
		client.requestHandler.ManageClients.getAllPurchase(clientuser);
    	
    }
	
	/**
	 * Refresh purchase list. to view client's purchases
	 *
	 * @param purchases the purchases
	 */
	public void refresh_purchase_list(ArrayList<entities.Purchase> purchases) {
	 	pnItems.getChildren().clear();

		Runnable runn = new Runnable() {
			@Override
			public void run() {
				for (entities.Purchase purch : purchases) {
					
					Purchaseitem2 sp = new Purchaseitem2(purch);
					sp.setOnMouseEntered(event -> {
						sp.setStyle("-fx-background-color : #BDBBC3");
					});
					sp.setOnMouseExited(event -> {
						sp.setStyle("-fx-background-color : #EBE8F9");
					});
					sp.setOnMouseClicked(event -> {

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
