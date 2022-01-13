package guiControllers.item;


import java.util.Optional;


import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * The Class RaterequestitemBasemy.
 */
public class RaterequestitemBasemy extends AnchorPane {

    /** The pane. */
    private final Pane pane;
    
    /** The date. */
    private final Label date;
    
    /** The flag. */
    private final ImageView flag;
    
    /** The type. */
    private final Label type;
    
    /** The btn no. */
    private final Button btn_no;
    
    /** The btn delete. */
    private final ImageView btn_delete;
    
    /** The price. */
    private final Label price;
    
    /** The days. */
    private final Label days;
    
    /** The btn yes. */
    private final Button btn_yes;
    
    /** The btn delete 1. */
    private final ImageView btn_delete1;
    
    /** The status. */
    private Label status;

    /** The raterequest. */
    private entities.RateRequest raterequest;
    
    /**
     * Instantiates a new raterequestitem basemy.
     *
     * @param rate the rate
     */
    public RaterequestitemBasemy(entities.RateRequest rate) {
    	this.raterequest = rate;
   
        pane = new Pane();
        date = new Label();
        flag = new ImageView();
        type = new Label();
        btn_no = new Button();
        btn_delete = new ImageView();
        price = new Label();
        days = new Label();
        btn_yes = new Button();
        btn_delete1 = new ImageView();
        status = new Label();

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
        date.setText(rate.getDate());

        flag.setFitHeight(64.0);
        flag.setFitWidth(56.0);
        flag.setLayoutX(4.0);
        flag.setPickOnBounds(true);
        flag.setPreserveRatio(true);
        flag.setSmooth(true);
        Image image = new Image(getClass().getResourceAsStream("/image/icons8-coins-100.png"));
        flag.setImage(image);
       
  

        type.setLayoutX(215.0);
        type.setLayoutY(18.0);
        type.setText(raterequest.getRate().getRtype().name());
        Tooltip tool = new Tooltip("Delete");
		Tooltip.install(btn_no, tool);
        btn_no.setDisable(false);
        btn_no.setLayoutX(653.0);
        btn_no.setLayoutY(13.0);
        btn_no.setMinHeight(25.951171875);
        btn_no.setMnemonicParsing(false);
        btn_no.setOnMouseClicked(this::btn_delete_clicked);
        btn_no.setPrefHeight(31.0);
        btn_no.setPrefWidth(42.0);
        btn_no.setStyle("-fx-border-color: #2A73FF; -fx-border-radius: 20;");
        btn_no.getStylesheets().add("//../style.css");

        btn_delete.setFitHeight(28.0);
        btn_delete.setFitWidth(21.0);
        btn_delete.setPickOnBounds(true);
        btn_delete.setPreserveRatio(true);
        image = new Image(getClass().getResourceAsStream("/image/icons8-trash-48.png"));
        btn_delete.setImage(image);
        btn_no.setGraphic(btn_delete);
        pane.getChildren().add(btn_no);
        
        price.setLayoutX(305.0);
        price.setLayoutY(18.0);
        price.setText(String.valueOf(raterequest.getRate().getPrice()));

        days.setLayoutX(431.0);
        days.setLayoutY(18.0);
        days.setText(String.valueOf(raterequest.getRate().getDays()));

        tool = new Tooltip("Set this Rate");
		Tooltip.install(btn_yes, tool);
        btn_yes.setDisable(false);
        btn_yes.setLayoutX(575.0);
        btn_yes.setLayoutY(13.0);
        btn_yes.setText("Set");
        btn_yes.setMinHeight(25.951171875);
        btn_yes.setMnemonicParsing(false);
        btn_yes.setOnMouseClicked(this::btn_set_clicked);
        btn_yes.setPrefHeight(31.0);
        btn_yes.setPrefWidth(75.0);
        btn_yes.setStyle("-fx-border-color: #2A73FF; -fx-border-radius: 20;");
        btn_yes.getStylesheets().add("//../style.css");

        btn_delete1.setFitHeight(28.0);
        btn_delete1.setFitWidth(21.0);
        btn_delete1.setPickOnBounds(true);
        btn_delete1.setPreserveRatio(true);
        image = new Image(getClass().getResourceAsStream("/image/icons8-checkmark-60.png"));
        btn_delete1.setImage(image);
        btn_yes.setGraphic(btn_delete1);
        
        status.setLayoutX(541);
        status.setLayoutY(13.0);
        status.setText("");
        switch(rate.getStatus()) {
        case APPROVE:
        	 pane.getChildren().add(btn_yes);
        	break;
        case REJECT:
        	status.setText("Rejected");
        	pane.getChildren().add(status);
        	break;
        case WAITING:
        	status.setText("Waiting");
        	pane.getChildren().add(status);
        	break;
        }
        
        pane.getStylesheets().add("/style.css");
        getStylesheets().add("/style.css");

        pane.getChildren().add(date);
        pane.getChildren().add(flag);
        pane.getChildren().add(type);

        pane.getChildren().add(price);
        pane.getChildren().add(days);

        getChildren().add(pane);

    }

    /**
     * Btn delete clicked.
     *
     * @param mouseEvent the mouse event
     */
    protected void btn_delete_clicked(MouseEvent mouseEvent) {
    	Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure to DELETE the request?", ButtonType.YES,
				ButtonType.NO);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.YES) {
			client.requestHandler.ManageRequests.DeleteOneRateReuest(raterequest);
		} 
    	
    }

    /**
     * Btn set clicked.
     *
     * @param mouseEvent the mouse event
     */
    protected void btn_set_clicked(MouseEvent mouseEvent) {
    	Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure to SET the rate request?", ButtonType.YES,
				ButtonType.NO);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.YES) {
			client.requestHandler.ManageRequests.set_rate(raterequest);

		} 
    }

}
