package guiControllers.item;


import java.util.Optional;

import client.requestHandler.Catalog;
import entities.Permission;
import guiControllers.HomeController;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;


/**
 * The Class Map.
 */
public class Map extends AnchorPane {

    /** The pane. */
    private final Pane pane;
    
    /** The Map name. */
    private final Label Map_name;
    
    /** The label. */
    private final Label label;
    
    /** The flag. */
    private final ImageView flag;
    
    /** The line. */
    private final Line line;
    
    /** The label 1. */
    private final Label label1;
    
    /** The label 3. */
    private final Label label3;
    
    /** The delete map. */
    private final Button delete_map;

    /** The map. */
    private entities.Map map;
    
    /**
     * Instantiates a new map.
     *
     * @param map the map
     */
    public Map(entities.Map map) {
    	this.map = map;
        pane = new Pane();
        Map_name = new Label();
        label = new Label();
        flag = new ImageView();
        line = new Line();
        label1 = new Label();
        label3 = new Label();
        delete_map = new Button();
        

        setPrefHeight(81.0);
        setPrefWidth(710.0);
        setStyle("-fx-background-color: #EBE8F9; -fx-background-radius: 5; -fx-background-insets: 0;");

        pane.setLayoutX(0.0);
        pane.setLayoutY(0.0);
        pane.setPrefHeight(101.0);
        pane.setPrefWidth(710.0);
        pane.setStyle("-fx-border-color: #BDBBC3");

        Map_name.setLayoutX(68.0);
        Map_name.setLayoutY(25.0);
        Map_name.setText(map.getName());

        label.setLayoutX(345.0);
        label.setLayoutY(25.0);
        label.setText(String.valueOf(map.getNumberOfPois()));

        flag.setFitHeight(31.0);
        flag.setFitWidth(25.0);
        flag.setLayoutX(14.0);
        flag.setLayoutY(22.0);
        flag.setPickOnBounds(true);
        flag.setPreserveRatio(true);
        flag.setSmooth(true);
        Image image = new Image(getClass().getResourceAsStream("/image/icons8-map-100.png"));
        flag.setImage(image);

        line.setEndX(490.0);
        line.setLayoutX(182.0);
        line.setLayoutY(55.0);
        line.setStartX(-120.0);
        line.setStroke(javafx.scene.paint.Color.valueOf("#bdbbc3"));

        label1.setLayoutX(62.0);
        label1.setLayoutY(66.0);
        
        Tooltip tooltip = new Tooltip(map.getDescription());
        Tooltip.install(label1, tooltip);
        String desc = map.getDescription();
        if(map.getDescription().length() > 117) {
        	desc = desc.substring(0, 117);
        	desc += "...";
        }
        label1.setText(desc);
        label1.setTextAlignment(javafx.scene.text.TextAlignment.LEFT);
        label1.setTextOverrun(javafx.scene.control.OverrunStyle.ELLIPSIS);
        label1.setWrapText(false);

        
        
        
    	if(Catalog.getMode().equals(Catalog.MODE.EDIT)) {
			if(HomeController.instance.user.getPermission().checkRole(Permission.Role.COMPANYMANAGER) ||
					(HomeController.instance.user.getPermission().checkRole(Permission.Role.PRODUCTMANAGER))) {
				delete_map.setText("");
				delete_map.setDisable(false);
				delete_map.setLayoutX(630.0);
				delete_map.setLayoutY(21.0);
				delete_map.setMinHeight(25.951171875);
				delete_map.setMnemonicParsing(false);
				delete_map.setOnMouseClicked(this::btn_delete_clicked);
				delete_map.setPrefHeight(31.0);
				delete_map.setPrefWidth(30);
				delete_map.setStyle("-fx-border-color: #2A73FF; -fx-border-radius: 20;");
				delete_map.getStylesheets().add("//../style.css");
				ImageView img1 = new ImageView();
				img1.setFitHeight(28.0);
				img1.setFitWidth(21.0);
				img1.setPickOnBounds(true);
				img1.setPreserveRatio(true);
				image = new Image(getClass().getResourceAsStream("/image/icons8-trash-48.png"));
				img1.setImage(image);
				delete_map.setGraphic(img1);
				pane.getChildren().add(delete_map);
				
			}
		}
        
        
        
        
    
        label3.setLayoutX(488.0);
        label3.setLayoutY(25.0);
        label3.setText(map.getMapVer().toString());
        pane.getStylesheets().add("/style.css");
        getStylesheets().add("/style.css");

        pane.getChildren().add(Map_name);
        pane.getChildren().add(label);
        pane.getChildren().add(flag);
        pane.getChildren().add(line);
        pane.getChildren().add(label1);
        pane.getChildren().add(label3);
        getChildren().add(pane);

    }

	/**
	 * Gets the map name.
	 *
	 * @return the map name
	 */
	public Labeled getMap_name() {
		// TODO Auto-generated method stub
		return Map_name;
	}
	
	/**
	 * Btn delete clicked.
	 *
	 * @param mouseEvent the mouse event
	 */
	public void btn_delete_clicked(MouseEvent mouseEvent) {
		Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure to Delete the map from " + map.getCity().getName() + "?", ButtonType.YES,
				ButtonType.NO);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.YES) {
			entities.Map delmap = map;
			client.requestHandler.Catalog.delete_map_from_city(delmap);
		} 
	}
}
