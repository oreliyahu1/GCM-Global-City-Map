package guiControllers.item;

import java.util.Optional;

import client.requestHandler.Catalog;
import entities.Permission;
import guiControllers.HomeController;
import guiControllers.Catalog.SelectedMap_CityController;
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
 * The Class PlaceBase.
 */
public class PlaceBase extends AnchorPane {

	/** The pane. */
	private final Pane pane;
	
	/** The btn delete. */
	private final Button btn_delete;
	
	/** The Place map. */
	public final Label Place_map;
	
	/** The flag. */
	private final ImageView flag;
	
	/** The img 1. */
	private final ImageView img1;

	/** The y. */
	public double x, y;
	
	/** The poimap. */
	private entities.PlaceOfInterestMap poimap;

	/**
	 * Instantiates a new place base.
	 *
	 * @param p the p
	 */
	public PlaceBase(entities.PlaceOfInterestMap p) {

		pane = new Pane();
	
		btn_delete = new Button();
	
		img1 = new ImageView();
		Place_map = new Label();
		flag = new ImageView();
		
		poimap = p;
		x = p.getCordinates().getX();
		y = p.getCordinates().getY();


	        setPrefHeight(78.0);
	        setPrefWidth(238.0);
	        setStyle("-fx-background-color: #EBE8F9; -fx-background-radius: 5; -fx-background-insets: 0;");

	        pane.setLayoutX(2.0);
	        pane.setLayoutY(5.0);
	        pane.setPrefHeight(70.0);
	        pane.setPrefWidth(238.0);
	        pane.setStyle("-fx-border-color: #BDBBC3");



		   Place_map.setLayoutX(48.0);
	        Place_map.setLayoutY(25.0);
	        
	    	String name = p.getName();
			Tooltip tooltip = new Tooltip(name);
			Tooltip.install(Place_map, tooltip);
		
				if (name.length() > 20) {
					name = name.substring(0, 20);
					name += "...";
				}
			Place_map.setText(name);
			

	        flag.setFitHeight(31.0);
	        flag.setFitWidth(25.0);
	        flag.setLayoutX(14.0);
	        flag.setLayoutY(21.0);
	        flag.setPickOnBounds(true);
	        flag.setPreserveRatio(true);
	        flag.setSmooth(true);
		Image image = new Image(getClass().getResourceAsStream("/image/icons8-marker-96.png"));
		flag.setImage(image);
		pane.getStylesheets().add("/style.css");
		getStylesheets().add("/style.css");

		if(Catalog.getMode().equals(Catalog.MODE.EDIT)) {
			if(HomeController.instance.user.getPermission().checkRole(Permission.Role.COMPANYMANAGER) ||
					(HomeController.instance.user.getPermission().checkRole(Permission.Role.PRODUCTMANAGER))) {
				btn_delete.setDisable(false);
		        btn_delete.setId("login_btn");
		        btn_delete.setLayoutX(180.0);
		        btn_delete.setLayoutY(21.0);
		        btn_delete.setMinHeight(25.951171875);
		        btn_delete.setMnemonicParsing(false);
		        btn_delete.setOnMouseClicked(this::btn_delete_clicked);
		        btn_delete.setPrefHeight(31.0);
		        btn_delete.setPrefWidth(42.0);
		        btn_delete.setStyle("-fx-border-color: #2A73FF; -fx-border-radius: 20;");
		        btn_delete.getStylesheets().add("//../style.css");
		        img1.setFitHeight(28.0);
		        img1.setFitWidth(21.0);
		        img1.setPickOnBounds(true);
		        img1.setPreserveRatio(true);
		        image = new Image(getClass().getResourceAsStream("/image/icons8-trash-48.png"));
				img1.setImage(image);
		        btn_delete.setGraphic(img1);
		        pane.getChildren().add(btn_delete);
			}
		}
		 
	

	        
	        pane.getStylesheets().add("/style.css");
	        getStylesheets().add("/style.css");
	        
		
		pane.getChildren().add(Place_map);
		pane.getChildren().add(flag);
		
		getChildren().add(pane);

	}

	/**
	 * Btn view clicked.
	 *
	 * @param mouseEvent the mouse event
	 */
	protected void btn_view_clicked(MouseEvent mouseEvent) {
		for (PlaceOnMap p : SelectedMap_CityController.instance.places2) {
			
			if(poimap.getName().equals(p.getpoi().getName())) {
				if(p.getStyle().isEmpty())
					p.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0.1, 0.1, 0.1);");
				else
					p.setStyle("");
			}else
				p.setStyle("");
		}
	}
	 
 	/**
 	 * Btn delete clicked.
 	 *
 	 * @param mouseEvent the mouse event
 	 */
 	protected void btn_delete_clicked(MouseEvent mouseEvent) {
			Alert alert = new Alert(AlertType.WARNING, "Are you sure to Delete " + poimap.getName() + "?", ButtonType.YES,
					ButtonType.NO);

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.YES) {
				
					
							client.requestHandler.Catalog.delete_POIfromMAP(poimap);
						
					
			} 
	
		 
		 
	 }
}
