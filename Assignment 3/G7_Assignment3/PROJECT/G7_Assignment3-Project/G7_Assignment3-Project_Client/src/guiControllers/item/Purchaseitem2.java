package guiControllers.item;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * The Class Purchaseitem2.
 */
public class Purchaseitem2 extends AnchorPane {

	/** The pane. */
	private Pane pane;
	
	/** The date. */
	private final Label date;
	
	/** The flag. */
	private final ImageView flag;
	
	/** The city. */
	private final Label city;
	
	/** The subject. */
	private final Label subject;
	
	/** The btn renew. */
	private final Button btn_renew;
	
	/** The btn renew 0. */
	private final ImageView btn_renew0;
	
	/** The expire date. */
	private final Label expire_date;
	
	/** The purchase. */
	private entities.Purchase purchase;

	/**
	 * Instantiates a new purchaseitem 2.
	 *
	 * @param purchase the purchase
	 */
	public Purchaseitem2(entities.Purchase purchase) {
		
		this.purchase = purchase;
		pane = new Pane();
		date = new Label();
		flag = new ImageView();
		city = new Label();
		subject = new Label();
		btn_renew = new Button();
		btn_renew0 = new ImageView();
		expire_date = new Label();

		setPrefHeight(54.0);
		setPrefWidth(710.0);
		setStyle("-fx-background-color: #EBE8F9; -fx-background-radius: 5; -fx-background-insets: 0;");

		pane.setLayoutX(0.0);
		pane.setLayoutY(0.0);
		pane.setPrefHeight(57.0);
		pane.setPrefWidth(710.0);
		pane.setStyle("-fx-border-color: #BDBBC3");

		date.setLayoutX(71.0);
		date.setLayoutY(18.0);
		date.setText(purchase.getDate());
		
	
		
		flag.setFitHeight(31.0);
		flag.setFitWidth(25.0);
		flag.setLayoutX(22.0);
		flag.setLayoutY(16.0);
		flag.setPickOnBounds(true);
		flag.setPreserveRatio(true);
		flag.setSmooth(true);

			Image imagee = new Image(getClass().getResourceAsStream("/image/icons8-purchase-order-40.png"));
	

		flag.setImage(imagee);

		city.setLayoutX(215.0);
		city.setLayoutY(18.0);
		city.setText(purchase.getCity().getName());

		subject.setLayoutX(331.0);
		subject.setLayoutY(18.0);
		
		
		
		expire_date.setLayoutX(430.0);
		expire_date.setLayoutY(18.0);
		btn_renew.setDisable(false);
		btn_renew.setLayoutX(610.0);
		btn_renew.setLayoutY(13.0);
		btn_renew.setMinHeight(25.951171875);
		btn_renew.setMnemonicParsing(false);
		btn_renew.setOnMouseClicked(this::btn_renew_clicked);
		btn_renew.setPrefHeight(31.0);
		btn_renew.setPrefWidth(80.0);
		btn_renew.setStyle("-fx-border-color: #2A73FF; -fx-border-radius: 20;");
		btn_renew.getStylesheets().add("//../style.css");
		btn_renew.setText("Renew");
		btn_renew0.setFitHeight(28.0);
		btn_renew0.setFitWidth(21.0);
		btn_renew0.setPickOnBounds(true);
		btn_renew0.setPreserveRatio(true);
		
		
		
		
		
		imagee = new Image(getClass().getResourceAsStream("/image/icons8-renew-40.png"));
		btn_renew0.setImage(imagee);
		btn_renew.setGraphic(btn_renew0);
		
		
		//calculate days
		
		DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDate localDate1= LocalDate.parse(purchase.getDate(),formatter); 
		localDate1 = localDate1.plusDays(purchase.getPurchasePrice().getDays());
		LocalDate localDatenow = LocalDate.now();
		long daysBetween = ChronoUnit.DAYS.between(localDatenow, localDate1);
		
	
		
		switch(purchase.getPurchasePrice().getRtype()) {
		case ONETIME:
			expire_date.setText("");
			subject.setText("ONETIME");
			break;
		case SUBSCRIPTION:
			subject.setText("SUBSCRIPTION ");
			
			if (daysBetween <= 3 && daysBetween > 0 ) {
				expire_date.setText("Expires in " + daysBetween);
			}else if (daysBetween > 0) {
				expire_date.setText("Expires in " + daysBetween);
				
			}else if(daysBetween < 0) {
				expire_date.setText("Expired");
			}

			
			break;
		}
		
		
		pane.getStylesheets().add("/style.css");
		getStylesheets().add("/style.css");

		pane.getChildren().add(date);
		pane.getChildren().add(flag);
		pane.getChildren().add(city);
		pane.getChildren().add(subject);
		
		pane.getChildren().add(expire_date);
		getChildren().add(pane);
		

	}

	/**
	 * Btn renew clicked.
	 *
	 * @param mouseEvent the mouse event
	 */
	protected void btn_renew_clicked(MouseEvent mouseEvent) {
		Alert alert = new Alert(AlertType.CONFIRMATION, "Do you want to Renew the Subscribtion the Subscribtion with 10% discount?",
				ButtonType.YES, ButtonType.NO);
		alert.setHeaderText("Do you want to Renew the Subscribtion the Subscribtion with 10% discount?");
		alert.setContentText(purchase.getPurchasePrice().getPrice() + " with 10% discount, " + 
                "Total price: " + (purchase.getPurchasePrice().getPrice()*(90.0)/100));
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.YES) {
			
		}	
		
	}


	

}
