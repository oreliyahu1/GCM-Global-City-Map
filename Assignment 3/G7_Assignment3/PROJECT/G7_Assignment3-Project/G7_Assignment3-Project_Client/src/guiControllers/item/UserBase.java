package guiControllers.item;


import java.util.Optional;

import application.GuiFormatCreator;
import entities.Permission;

import guiControllers.GeneralValues;
import guiControllers.HomeController;
import guiControllers.Messages.notification;
import guiControllers.Profile.PurchaseController;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;

import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class UserBase extends AnchorPane {

	private Pane pane;
	private  Label created;
	private  ImageView flag;
	private  Label user;
	private  Label firstname;
	private  Label lastname;
	private  Button ViewDetails;
	private  Button ChangeDetails;
	private  Button Changepass;
	private  Button btnview;
	private  Button changecredit;
    private  ScrollPane scrollPane;
    private  VBox pnItems;
    private Label role;
	
	private boolean isshowed = false;
	private entities.User userclient;

	private double x,y;
	public UserBase(entities.User userclient) {
		this.userclient = userclient;

		pane = new Pane();
		created = new Label();
		flag = new ImageView();
		user = new Label();
		firstname = new Label();
		lastname = new Label();
		ViewDetails = new Button();
		ChangeDetails = new Button();
		Changepass = new Button();
		btnview = new Button();
		changecredit = new Button();
		scrollPane = new ScrollPane();
        pnItems = new VBox();
        role = new Label();
        
		setPrefHeight(54.0);
		setPrefWidth(710.0);
		setStyle("-fx-background-color: #EBE8F9; -fx-background-radius: 5; -fx-background-insets: 0;");

		pane.setLayoutX(0.0);
		pane.setLayoutY(0.0);
		pane.setPrefHeight(57.0);
		pane.setPrefWidth(710.0);
		pane.setStyle("-fx-border-color: #BDBBC3");

		created.setLayoutX(71.0);
		created.setLayoutY(18.0);
		created.setText(userclient.getCreated());


		flag.setFitHeight(31.0);
		flag.setFitWidth(25.0);
		flag.setLayoutX(22.0);
		flag.setLayoutY(16.0);
		flag.setPickOnBounds(true);
		flag.setPreserveRatio(true);
		flag.setSmooth(true);

		Image imagee = new Image(getClass().getResourceAsStream("/image/icons8_Person_32px.png"));

		flag.setImage(imagee);

		user.setLayoutX(215.0);
		user.setLayoutY(18.0);
		user.setText(userclient.getUsername());
		
		firstname.setLayoutX(320);
		firstname.setLayoutY(18.0);
		firstname.setText(userclient.getFirstName());

		lastname.setLayoutX(450);
		lastname.setLayoutY(18.0);
		lastname.setText(userclient.getLastName());
		
		role.setLayoutX(530);
		role.setLayoutY(18.0);
		String Role = "";
		if(userclient.getPermission().checkRole(entities.Permission.Role.CLIENT)) {
			Role = "Client";
		}else if(userclient.getPermission().checkRole(entities.Permission.Role.WORKER)) {
			Role = "Worker";
		}else if(userclient.getPermission().checkRole(entities.Permission.Role.PRODUCTWORKER)) {
			Role = "Product Department Worker";
		}else if(userclient.getPermission().checkRole(entities.Permission.Role.PRODUCTMANAGER)) {
			Role = "Product Department Manager";
			GeneralValues.Found_Department_manager = true;
		}else if(userclient.getPermission().checkRole(entities.Permission.Role.COMPANYMANAGER)) {
			Role = "Company Manager";
		}
		role.setText(Role);
		pane.getChildren().add(role);
		
		pane.getStylesheets().add("/style.css");
		getStylesheets().add("/style.css");

		
		
		ViewDetails.setDisable(false);
		ViewDetails.setLayoutX(20.0);
		ViewDetails.setLayoutY(90.0);
		ViewDetails.setMinHeight(25.951171875);
		ViewDetails.setMnemonicParsing(false);
		ViewDetails.setOnMouseClicked(this::ViewDetails_clicked);
		ViewDetails.setPrefHeight(40);
		ViewDetails.setPrefWidth(130);
		ViewDetails.setText("View Details");
		ViewDetails.setStyle("-fx-border-color: #2A73FF; -fx-border-radius: 20;");
		ViewDetails.getStylesheets().add("//../style.css");
		ImageView ViewDetails0 = new ImageView();
		ViewDetails0.setFitHeight(28.0);
		ViewDetails0.setFitWidth(21.0);
		ViewDetails0.setPickOnBounds(true);
		ViewDetails0.setPreserveRatio(true);
		imagee = new Image(getClass().getResourceAsStream("/image/icons8-more-details-100.png"));
		ViewDetails0.setImage(imagee);
		ViewDetails.setGraphic(ViewDetails0);
		
		
		ChangeDetails.setDisable(false);
		ChangeDetails.setLayoutX(155.0);
		ChangeDetails.setLayoutY(90.0);
		ChangeDetails.setMinHeight(25.951171875);
		ChangeDetails.setMnemonicParsing(false);
		ChangeDetails.setOnMouseClicked(this::ChangeDetails_clicked);
		ChangeDetails.setPrefHeight(40);
		ChangeDetails.setPrefWidth(130);
		ChangeDetails.setText("Details");
		ChangeDetails.setStyle("-fx-border-color: #2A73FF; -fx-border-radius: 20;");
		ChangeDetails.getStylesheets().add("//../style.css");
		ImageView ChangeDetails0 = new ImageView();
		ChangeDetails0.setFitHeight(28.0);
		ChangeDetails0.setFitWidth(21.0);
		ChangeDetails0.setPickOnBounds(true);
		ChangeDetails0.setPreserveRatio(true);
		imagee = new Image(getClass().getResourceAsStream("/image/icons8-edit-account-filled-100.png"));
		ChangeDetails0.setImage(imagee);
		ChangeDetails.setGraphic(ChangeDetails0);
		
		
		Changepass.setDisable(false);
		Changepass.setLayoutX(290.0);
		Changepass.setLayoutY(90.0);
		Changepass.setMinHeight(25.951171875);
		Changepass.setMnemonicParsing(false);
		Changepass.setOnMouseClicked(this::changepass_clicked);
		Changepass.setPrefHeight(40);
		Changepass.setPrefWidth(130);
		Changepass.setText("Password");
		Changepass.setStyle("-fx-border-color: #2A73FF; -fx-border-radius: 20;");
		Changepass.getStylesheets().add("//../style.css");
		ImageView Changepass0 = new ImageView();
		Changepass0.setFitHeight(28.0);
		Changepass0.setFitWidth(21.0);
		Changepass0.setPickOnBounds(true);
		Changepass0.setPreserveRatio(true);
		imagee = new Image(getClass().getResourceAsStream("/image/icons8-password-100.png"));
		Changepass0.setImage(imagee);
		Changepass.setGraphic(Changepass0);
		
		
		btnview.setDisable(false);
		btnview.setLayoutX(425.0);
		btnview.setLayoutY(90.0);
		btnview.setMinHeight(25.951171875);
		btnview.setMnemonicParsing(false);
		btnview.setOnMouseClicked(this::putchaselist_clicked);
		btnview.setPrefHeight(40);
		btnview.setPrefWidth(130);
		btnview.setText("Purchase List");
		btnview.setStyle("-fx-border-color: #2A73FF; -fx-border-radius: 20;");
		btnview.getStylesheets().add("//../style.css");
		ImageView btnview0 = new ImageView();
		btnview0.setFitHeight(28.0);
		btnview0.setFitWidth(21.0);
		btnview0.setPickOnBounds(true);
		btnview0.setPreserveRatio(true);
		imagee = new Image(getClass().getResourceAsStream("/image/icons8-purchase-order-100.png"));
		btnview0.setImage(imagee);
		btnview.setGraphic(btnview0);
		
		
		
		changecredit.setDisable(false);
		changecredit.setLayoutX(560.0);
		changecredit.setLayoutY(90.0);
		changecredit.setMinHeight(25.951171875);
		changecredit.setMnemonicParsing(false);
		changecredit.setOnMouseClicked(this::changeCredit_clicked);
		changecredit.setPrefHeight(40);
		changecredit.setPrefWidth(130);
		changecredit.setText("Permission");
		changecredit.setStyle("-fx-border-color: #2A73FF; -fx-border-radius: 20;");
		changecredit.getStylesheets().add("//../style.css");
		ImageView changecredit0 = new ImageView();
		changecredit0.setFitHeight(28.0);
		changecredit0.setFitWidth(21.0);
		changecredit0.setPickOnBounds(true);
		changecredit0.setPreserveRatio(true);
		imagee = new Image(getClass().getResourceAsStream("/image/icons8-mastercard-credit-card-100.png"));
		changecredit0.setImage(imagee);
		changecredit.setGraphic(changecredit0);
		
		
		 scrollPane.setHbarPolicy(javafx.scene.control.ScrollPane.ScrollBarPolicy.NEVER);
	        scrollPane.setLayoutX(30.0);
	        scrollPane.setLayoutY(150.0);
	        scrollPane.setPrefHeight(256.0);
	        scrollPane.setPrefWidth(660.0);
	        scrollPane.setStyle("-fx-background-color: #EBE8F9;");
	        scrollPane.setVbarPolicy(javafx.scene.control.ScrollPane.ScrollBarPolicy.ALWAYS);

	        pnItems.setPrefHeight(259.0);
	        pnItems.setPrefWidth(744.0);
	        pnItems.setSpacing(5.0);
	        pnItems.setStyle("-fx-background-color: #EBE8F9;");
	        pnItems.setPadding(new Insets(5.0));
	        scrollPane.setContent(pnItems);
	        scrollPane.getStylesheets().add("/style.css");
		
		pane.getChildren().add(created);
		pane.getChildren().add(flag);
		pane.getChildren().add(user);
		pane.getChildren().add(firstname);
		pane.getChildren().add(lastname);

		getChildren().add(pane);

	}

	public void show_profile() {
	
		if(isshowed == false) {
			isshowed = true;
			changeSize1(pane,150);
		}else {
			isshowed = false;
			changeSize2(pane,57);
			
			}
		
	}
	protected void changeCredit_clicked(MouseEvent mouseEvent) {

		 Dialog<ButtonType> dialog = new Dialog<ButtonType>();
	        dialog.setTitle(userclient.getUsername());
	        dialog.setHeaderText("Update Permission");
	        DialogPane dialogPane = dialog.getDialogPane();
	        dialogPane.getButtonTypes().addAll(ButtonType.APPLY, ButtonType.CANCEL);
	       
	        ComboBox<String> roles = new ComboBox<String>();
	        roles.setPromptText("Choose Role");
	        roles.getItems().add("Client");
	        roles.getItems().add("Worker");
	        roles.getItems().add("Product Department Worker");
	        if(GeneralValues.Found_Department_manager == false) {
	        	roles.getItems().add("Product Department Manager");
	        }
	
	        dialogPane.setContent(new VBox(8, roles));
	     
	        Optional<ButtonType> result = dialog.showAndWait();
			if (result.get() == ButtonType.APPLY) {
				if(roles.getSelectionModel().getSelectedItem() == null) {
					Stage s = (Stage)HomeController.instance.PANE.getScene().getWindow();
					notification.show("Please Select a Role!", "error", s);
					return;
				}
				entities.User newuser = userclient;
				entities.Permission permission = newuser.getPermission();
				if(roles.getSelectionModel().getSelectedItem().toString().equals("Client")) {
					permission.setPremission(1);
					newuser.setPermission(permission);
				}else if(roles.getSelectionModel().getSelectedItem().toString().equals("Worker")) {
					permission.setPremission(2);
					newuser.setPermission(permission);
				}else if(roles.getSelectionModel().getSelectedItem().toString().equals("Product Department Worker")) {
					permission.setPremission(4);
					newuser.setPermission(permission);
				}else if(roles.getSelectionModel().getSelectedItem().toString().equals("Product Department Manager")) {
					permission.setPremission(8);
					newuser.setPermission(permission);
				}
				client.requestHandler.ManageClients.update_permission(newuser);

				
			}	
	}
	
	protected void ViewDetails_clicked(MouseEvent mouseEvent) {

		Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(userclient.getUsername());
        alert.setHeaderText("Client Details");
        alert.setContentText("REGISTRED ON: " + userclient.getCreated() + "\nUSERNAME: " + userclient.getUsername() + "\nEMAIL: " +
        		userclient.getEmail() + "\nFULL NAME:" + userclient.getFirstName() + " " + userclient.getLastName() + "\nPHONE: " + 
        		userclient.getPhoneNumber() + "\nPERMISSION:" + userclient.getPermission());
        alert.showAndWait();
	}
	protected void changepass_clicked(MouseEvent mouseEvent) {
		 Dialog<ButtonType> dialog = new Dialog<ButtonType>();
	        dialog.setTitle(userclient.getUsername());
	        dialog.setHeaderText("Change Client Password");
	        DialogPane dialogPane = dialog.getDialogPane();
	        dialogPane.getButtonTypes().addAll(ButtonType.APPLY, ButtonType.CANCEL);
	        TextField textField = new TextField();
	        textField.setPromptText("New Password");
	  
	        textField.setTextFormatter(GuiFormatCreator.txtMaxField(32));

	        dialogPane.setContent(new VBox(8, textField));
	     
	        Optional<ButtonType> result = dialog.showAndWait();
			if (result.get() == ButtonType.APPLY) {
				if(textField.getText().isEmpty())
				{
					Stage s = (Stage)HomeController.instance.PANE.getScene().getWindow();
					notification.show("Password Cannot be empty!", "error", s);
					return;
				}
				entities.User newuser = userclient;
	    		newuser.setPassword(textField.getText());
	    		client.requestHandler.ManageClients.update_password(newuser);
			}
		
	}
	protected void putchaselist_clicked(MouseEvent mouseEvent) {

		
		 try {
			 PurchaseController.clientuser = userclient;
			 FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/PurchaseList.fxml"));
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
	protected void ChangeDetails_clicked(MouseEvent mouseEvent) {

		 Dialog<ButtonType> dialog = new Dialog<ButtonType>();
	        dialog.setTitle(userclient.getUsername());
	        dialog.setHeaderText("Change Client Details");
	        DialogPane dialogPane = dialog.getDialogPane();
	        dialogPane.getButtonTypes().addAll(ButtonType.APPLY, ButtonType.CANCEL);
	        TextField textField = new TextField();
	        textField.setPromptText("First Name");
	        textField.setText(userclient.getFirstName());
	        TextField textField2 = new TextField();
	        textField2.setPromptText("Last Name");
	        textField2.setText(userclient.getLastName());
	        TextField textField3 = new TextField();
	        textField3.setPromptText("Email");
	        textField3.setText(userclient.getEmail());
	        TextField textField4 = new TextField();
	        textField4.setPromptText("Phone Number");
	        textField4.setText(userclient.getPhoneNumber());
	        
	        textField.setTextFormatter(GuiFormatCreator.txtMaxField(50));
	        textField2.setTextFormatter(GuiFormatCreator.txtMaxField(50));
	        textField3.setTextFormatter(GuiFormatCreator.txtMaxField(100));
	        textField4.setTextFormatter(GuiFormatCreator.txtNumberField(7));

	        dialogPane.setContent(new VBox(8, textField, textField2,textField3,textField4));
	     
	        Optional<ButtonType> result = dialog.showAndWait();
			if (result.get() == ButtonType.APPLY) {
				if(textField.getText().isEmpty() || textField2.getText().isEmpty() || textField3.getText().isEmpty() || textField4.getText().isEmpty())
				{
					Stage s = (Stage)HomeController.instance.PANE.getScene().getWindow();
					notification.show("Please Fill all Fields!", "error", s);
					return;
				}
				if(GuiFormatCreator.checkEmail(textField3.getText()) == false) {
	    			Stage s = (Stage)HomeController.instance.PANE.getScene().getWindow();
	    			notification.show("Incorrect Email Format!", "error", s);
	    			return;
	    		}
				entities.User newuser = userclient;
				newuser.setEmail(textField3.getText());
				newuser.setFirstName(textField.getText());
				newuser.setLastName(textField2.getText());
				newuser.setPhoneNumber(textField4.getText());
				client.requestHandler.ManageClients.update_profile(newuser);
				this.firstname.setText(textField.getText());
				this.lastname.setText(textField2.getText());
			}	
	}
	public void changeSize1(Pane pane, double height) {
		this.setDisable(true);
	    Duration cycleDuration = Duration.millis(500);
	    Timeline timeline = new Timeline(
	    	    new KeyFrame(cycleDuration,
	                    new KeyValue(pane.prefHeightProperty(),height,Interpolator.EASE_BOTH)),
	            new KeyFrame(cycleDuration,
	                    new KeyValue(this.prefHeightProperty(),height,Interpolator.EASE_BOTH))
	            );

	    timeline.play();
	    timeline.setOnFinished(event -> {
	    	//pane.getChildren().add(content);
	    	pane.getChildren().add(ViewDetails);
			pane.getChildren().add(ChangeDetails);
			pane.getChildren().add(Changepass);
			pane.getChildren().add(btnview);
			if(!(userclient.getPermission().checkRole(Permission.Role.COMPANYMANAGER)))
				pane.getChildren().add(changecredit);
			this.setDisable(false);
	    });
	
	}
	public void changeSize2(Pane pane, double height) {
		//pane.getChildren().remove(content);
		this.setDisable(true);
		pane.getChildren().remove(ViewDetails);
		pane.getChildren().remove(ChangeDetails);
		pane.getChildren().remove(Changepass);
		pane.getChildren().remove(btnview);
		if(!(userclient.getPermission().checkRole(Permission.Role.COMPANYMANAGER)))
			pane.getChildren().remove(changecredit);
	    Duration cycleDuration = Duration.millis(500);
	    Timeline timeline = new Timeline(
	    	    new KeyFrame(cycleDuration,
	                    new KeyValue(pane.prefHeightProperty(),height,Interpolator.EASE_BOTH)),
	            new KeyFrame(cycleDuration,
	                    new KeyValue(this.prefHeightProperty(),height,Interpolator.EASE_BOTH))
	            );

	    timeline.play();
	    timeline.setOnFinished(event -> {
	    	this.setDisable(false);
	    });
	
	}
	public entities.User get_user() {
		return userclient;
	}


}
