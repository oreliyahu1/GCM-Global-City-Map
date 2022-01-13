package guiControllers.item;



import java.util.Optional;

import entities.AbstractRequest.STATUS;
import guiControllers.HomeController;
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
 * The Class VersionrequestitemBase.
 */
public class VersionrequestitemBase extends AnchorPane {

    /** The pane. */
    private final Pane pane;
    
    /** The date. */
    private final Label date;
    
    /** The flag. */
    private final ImageView flag;
    
    /** The country. */
    private final Label country;
    
    /** The city. */
    private final Label city;
    
    /** The btn no. */
    private final Button btn_no;
    
    /** The btn delete. */
    private final ImageView btn_delete;
    
    /** The version. */
    private final Label version;
    
    /** The from. */
    private final Label from;
    
    /** The btn yes. */
    private final Button btn_yes;
    
    /** The btn delete 1. */
    private final ImageView btn_delete1;
    
    /** The status. */
    private Label status;
    
    /** The request. */
    private entities.VersionRequest request;
  
    /**
     * Instantiates a new versionrequestitem base.
     *
     * @param request the request
     */
    public VersionrequestitemBase(entities.VersionRequest request) {
    	this.request = request;
        pane = new Pane();
        date = new Label();
        flag = new ImageView();
        from = new Label();
        country = new Label();
        btn_no = new Button();
        btn_delete = new ImageView();
        city = new Label();
        version = new Label();
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

        date.setLayoutX(50.0);
        date.setLayoutY(18.0);
        date.setText(request.getDate());

        flag.setFitHeight(32.0);
        flag.setFitWidth(24.0);
        flag.setLayoutX(7.0);
        flag.setLayoutY(18.0);
        flag.setPickOnBounds(true);
        flag.setPreserveRatio(true);
        flag.setSmooth(true);
        Image image = new Image(getClass().getResourceAsStream("/image/icons8-versions-48.png"));
        flag.setImage(image);
       
        country.setLayoutX(185.0);
        country.setLayoutY(18.0);
        country.setText(request.getCity().getCountry().getName());

        city.setLayoutX(310.0);
        city.setLayoutY(18.0);
        city.setText(request.getCity().getName());
        Tooltip tool = new Tooltip("Reject that Request!");
		Tooltip.install(btn_no, tool);
        btn_no.setDisable(false);
        btn_no.setLayoutX(653.0);
        btn_no.setLayoutY(13.0);
        btn_no.setMinHeight(25.951171875);
        btn_no.setMnemonicParsing(false);
        btn_no.setOnMouseClicked(this::btn_unapprove_clicked);
        btn_no.setPrefHeight(31.0);
        btn_no.setPrefWidth(42.0);
        btn_no.setStyle("-fx-border-color: #2A73FF; -fx-border-radius: 20;");
        btn_no.getStylesheets().add("//../style.css");

        btn_delete.setFitHeight(28.0);
        btn_delete.setFitWidth(21.0);
        btn_delete.setPickOnBounds(true);
        btn_delete.setPreserveRatio(true);
        image = new Image(getClass().getResourceAsStream("/image/icons8-delete-64.png"));
        btn_delete.setImage(image);
        btn_no.setGraphic(btn_delete);

        status.setLayoutX(645);
        status.setLayoutY(13.0);
        status.setText("");
        switch(request.getStatus()) {
        case APPROVE:
        	status.setText("Approved");
        	pane.getChildren().add(status);
        	break;
        case REJECT:
        	status.setText("Rejected");
        	pane.getChildren().add(status);
        	break;
        case WAITING:
        	   pane.getChildren().add(btn_no);
        	   pane.getChildren().add(btn_yes);
        	break;
        }
        
        version.setLayoutX(431.0);
        version.setLayoutY(18.0);
        version.setText(request.getCity().getVersion().toString());
        pane.getChildren().add(version);
        from.setLayoutX(541.0);
        from.setLayoutY(18.0);
        from.setText(request.getUser().getUsername());

        tool = new Tooltip("Approve the Request");
		Tooltip.install(btn_yes, tool);
        btn_yes.setDisable(false);
        btn_yes.setLayoutX(605.0);
        btn_yes.setLayoutY(13.0);
        btn_yes.setMinHeight(25.951171875);
        btn_yes.setMnemonicParsing(false);
        btn_yes.setOnMouseClicked(this::btn_approve_clicked);
        btn_yes.setPrefHeight(31.0);
        btn_yes.setPrefWidth(42.0);
        btn_yes.setStyle("-fx-border-color: #2A73FF; -fx-border-radius: 20;");
        btn_yes.getStylesheets().add("//../style.css");

        btn_delete1.setFitHeight(28.0);
        btn_delete1.setFitWidth(21.0);
        btn_delete1.setPickOnBounds(true);
        btn_delete1.setPreserveRatio(true);
        image = new Image(getClass().getResourceAsStream("/image/icons8-checkmark-60.png"));
        btn_delete1.setImage(image);
        btn_yes.setGraphic(btn_delete1);
        
        pane.getStylesheets().add("/style.css");
        getStylesheets().add("/style.css");

        pane.getChildren().add(date);
        pane.getChildren().add(flag);
        pane.getChildren().add(from);
        pane.getChildren().add(country);
     
        pane.getChildren().add(city);
       
        getChildren().add(pane);
        
        

    }

    /**
     * Btn unapprove clicked.
     *
     * @param mouseEvent the mouse event
     */
    protected void btn_unapprove_clicked(MouseEvent mouseEvent) {
    	Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure to Reject the request?", ButtonType.YES,
				ButtonType.NO);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.YES) {
			entities.VersionRequest newverreq = request;
			newverreq.setStatus(STATUS.REJECT);
			client.requestHandler.ManageRequests.UpdatetVersionReuest(newverreq,HomeController.instance.user);
		} 
    	
    }
    
    /**
     * Btn approve clicked.
     *
     * @param mouseEvent the mouse event
     */
    protected void btn_approve_clicked(MouseEvent mouseEvent) {
    	Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure to Approve the request?", ButtonType.YES,
				ButtonType.NO);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.YES) {
			entities.VersionRequest newverreq = request;
			newverreq.setStatus(STATUS.APPROVE);
			client.requestHandler.ManageRequests.UpdatetVersionReuest(newverreq,HomeController.instance.user);
		} 
    	
    }

}
