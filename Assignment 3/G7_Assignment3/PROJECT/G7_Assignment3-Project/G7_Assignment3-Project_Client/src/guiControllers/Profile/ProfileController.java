package guiControllers.Profile;


import java.io.IOException;
import java.util.ArrayList;

import animatefx.SlideInUp;
import application.GuiFormatCreator;
import entities.Permission;
import guiControllers.FxmlView;
import guiControllers.HomeController;
import guiControllers.item.Purchaseitem;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * The Class ProfileController.
 */
public class ProfileController {

 
	/** The instance. */
	public static ProfileController instance = null;
	
    /** The pn items. */
    @FXML
    private VBox pnItems;

    /** The exit. */
    @FXML
    private ImageView exit;

    /** The btn update. */
    @FXML
    private Button btn_update;

    /** The pane details purchase. */
    @FXML
    private HBox pane_details_purchase;

    /** The scroll purchase. */
    @FXML
    private ScrollPane scroll_purchase;


    /** The txt name. */
    @FXML
    private TextField txt_name;
    
    /** The txt name 2. */
    @FXML
    private TextField txt_name2;
  
 
    /** The txt phone. */
    @FXML
    private TextField txt_phone;

    /** The txt email. */
    @FXML
    private TextField txt_email;



	/**
	 * Gets the txt name.
	 *
	 * @return the txt name
	 */
	public TextField getTxt_name() {
		return txt_name;
	}

	/**
	 * Sets the txt name.
	 *
	 * @param string the new txt name
	 */
	public void setTxt_name(String string) {
		this.txt_name.setText(string);
	}

	/**
	 * Gets the txt phone.
	 *
	 * @return the txt phone
	 */
	public TextField getTxt_phone() {
		return txt_phone;
	}

	/**
	 * Sets the txt phone.
	 *
	 * @param string the new txt phone
	 */
	public void setTxt_phone(String string) {
		this.txt_phone.setText(string);
	}

	/**
	 * Gets the txt email.
	 *
	 * @return the txt email
	 */
	public TextField getTxt_email() {
		return txt_email;
	}

	/**
	 * Sets the txt email.
	 *
	 * @param string the new txt email
	 */
	public void setTxt_email(String string) {
		this.txt_email.setText(string);
	}

	/** The btn updae pay. */
	@FXML
    private Button btn_updae_pay;

    /** The btn update pass. */
    @FXML
    private Button btn_update_pass;
    
    /** The btn update credit. */
    @FXML
    private Button btn_update_credit;

    /** The y. */
    private double x,y;
 
    
    /**
     * Initialize.
     */
    @FXML
    void initialize() {
    	ProfileController.instance = this;
		ProfileController.instance.setTxt_name(HomeController.instance.user.getFirstName());
		ProfileController.instance.txt_name2.setText(HomeController.instance.user.getLastName());
		ProfileController.instance.setTxt_phone(HomeController.instance.user.getPhoneNumber());
		ProfileController.instance.setTxt_email(HomeController.instance.user.getEmail().toString());
		txt_name.setTextFormatter(GuiFormatCreator.txtMaxField(50));
		txt_name2.setTextFormatter(GuiFormatCreator.txtMaxField(50));
		txt_phone.setTextFormatter(GuiFormatCreator.txtNumberField(10));
		txt_email.setTextFormatter(GuiFormatCreator.txtMaxField(100));
		
		
		if(HomeController.instance.user.getPermission().checkRole(Permission.Role.CLIENT)) {
			btn_update_credit.setVisible(true);
		}else {
			btn_update_credit.setVisible(false);
			pane_details_purchase.setVisible(false);
			scroll_purchase.setVisible(false);
		}
		client.requestHandler.Profile.getAllPurchase(HomeController.instance.user.getUsername());
    }
    
	/**
	 * Start.
	 *
	 * @param pane the pane
	 * @throws Exception the exception
	 */
	public void start(Pane pane) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource(FxmlView.PROFILE.getFXML()));
		HomeController.instance.Set_Title(FxmlView.PROFILE.getTitle());
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
     * Btn editupdate clicked.
     *
     * @param event the event
     */
    @FXML
    void btn_editupdate_clicked(MouseEvent event) {
    	if(btn_update.getText().equals("Edit")) {
    		btn_update.setText("Update");
    		getTxt_email().setDisable(false);
    		getTxt_phone().setDisable(false);
    		getTxt_name().setDisable(false);
    		txt_name2.setDisable(false);
    		
    		
    		
    	}else {
    		btn_update.setText("Edit");
    		entities.User newuser = new entities.User(HomeController.instance.user.getUsername());
    		newuser.setCreated(HomeController.instance.user.getCreated());
    		newuser.setEmail(getTxt_email().getText());
    		newuser.setPassword(HomeController.instance.user.getPassword());
    		newuser.setPermission(HomeController.instance.user.getPermission());
    		newuser.setFirstName(getTxt_name().getText());
    		newuser.setPhoneNumber(getTxt_phone().getText());
    		newuser.setLastName(txt_name2.getText());
 
    		client.requestHandler.Profile.update_profile(newuser);
    		
    		getTxt_email().setDisable(true);
    		getTxt_phone().setDisable(true);
    		getTxt_name().setDisable(true);
    		txt_name2.setDisable(true);
    	}
    }
    
    /**
     * Refresh purchases.
     */
    public void refresh_purchases() {
    	client.requestHandler.Profile.getAllPurchase(HomeController.instance.user.getUsername());
    }
    
    /**
     * Update purchase list.
     *
     * @param purchases the purchases
     */
    public void update_purchase_list(ArrayList<entities.Purchase> purchases) {
    	pnItems.getChildren().clear();

		Runnable runn = new Runnable() {
			@Override
			public void run() {
				for (entities.Purchase pur : purchases) {
					Purchaseitem sp = new Purchaseitem(pur);
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
   
    
    /**
     * Btn update credit clicked.
     *
     * @param event the event
     */
    @FXML
    void btn_update_credit_clicked(MouseEvent event) {
    	try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/profile/UpdateCredit.fxml"));
			Scene scene = new Scene(fxmlLoader.load());
			Stage primaryStage = new Stage();
			// scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.initStyle(StageStyle.UNDECORATED);
			scene.setOnMousePressed(event1 -> {
				x = event1.getSceneX();
				y = event1.getSceneY();
			});
			scene.setOnMouseDragged(event2 -> {

				primaryStage.setX(event2.getScreenX() - x);
				primaryStage.setY(event2.getScreenY() - y);

			});
			primaryStage.initModality(Modality.APPLICATION_MODAL);
			primaryStage.showAndWait();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    /**
     * Btn update password clicked.
     *
     * @param event the event
     */
    @FXML
    void btn_update_password_clicked(MouseEvent event) {
    	try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/profile/UpdatePassword.fxml"));
			Scene scene = new Scene(fxmlLoader.load());
			Stage primaryStage = new Stage();
			// scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.initStyle(StageStyle.UNDECORATED);
			scene.setOnMousePressed(event1 -> {
				x = event1.getSceneX();
				y = event1.getSceneY();
			});
			scene.setOnMouseDragged(event2 -> {

				primaryStage.setX(event2.getScreenX() - x);
				primaryStage.setY(event2.getScreenY() - y);

			});
			primaryStage.initModality(Modality.APPLICATION_MODAL);
			primaryStage.showAndWait();

		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }
    
    /**
     * Btn update permissions.
     *
     * @param event the event
     */
    @FXML
    void btn_update_permissions(MouseEvent event) {
    	try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/profile/Permissions.fxml"));
			Scene scene = new Scene(fxmlLoader.load());
			Stage primaryStage = new Stage();
			// scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.initStyle(StageStyle.UNDECORATED);
			scene.setOnMousePressed(event1 -> {
				x = event1.getSceneX();
				y = event1.getSceneY();
			});
			scene.setOnMouseDragged(event2 -> {

				primaryStage.setX(event2.getScreenX() - x);
				primaryStage.setY(event2.getScreenY() - y);

			});
			primaryStage.initModality(Modality.APPLICATION_MODAL);
			primaryStage.showAndWait();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

}
