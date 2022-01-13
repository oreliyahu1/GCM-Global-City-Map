package guiControllers.item;


import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

/**
 * The Class Map_toadd_item.
 */
public class Map_toadd_item extends AnchorPane {

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
    
    /** The btnview. */
    private final Button btnview;
    
    /** The btnadd. */
    private final Button btnadd;
    
    /** The map. */
    private entities.Map map;
    
    /**
     * Instantiates a new map toadd item.
     *
     * @param map the map
     */
    public Map_toadd_item(entities.Map map) {
    	this.map = map;
        pane = new Pane();
        Map_name = new Label();
        label = new Label();
        flag = new ImageView();
        line = new Line();
        label1 = new Label();
        btnadd = new Button();
        
        btnview = new Button();

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

        label.setLayoutX(195.0);
        label.setLayoutY(25.0);
        label.setText(map.getMapVer().toString());

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

    
    
        pane.getStylesheets().add("/style.css");
        getStylesheets().add("/style.css");

        
        btnview.setDisable(false);
		btnview.setLayoutX(425.0);
		btnview.setLayoutY(15.0);
		btnview.setMinHeight(25.951171875);
		btnview.setMnemonicParsing(false);
		btnview.setOnMouseClicked(this::view_map_image_clicked);
		btnview.setPrefHeight(30);
		btnview.setPrefWidth(150);
		btnview.setText("View Map Image");
		btnview.setStyle("-fx-border-color: #2A73FF; -fx-border-radius: 20;");
		btnview.getStylesheets().add("//../style.css");
		ImageView btnview0 = new ImageView();
		btnview0.setFitHeight(28.0);
		btnview0.setFitWidth(21.0);
		btnview0.setPickOnBounds(true);
		btnview0.setPreserveRatio(true);
		Image imagee = new Image(getClass().getResourceAsStream("/image/icons8-purchase-order-100.png"));
		btnview0.setImage(imagee);
		btnview.setGraphic(btnview0);
		
		 btnadd.setDisable(false);
			btnadd.setLayoutX(580.0);
			btnadd.setLayoutY(15.0);
			btnadd.setMinHeight(25.951171875);
			btnadd.setMnemonicParsing(false);
			btnadd.setOnMouseClicked(this::view_btn_add_clicked);
			btnadd.setPrefHeight(30);
			btnadd.setPrefWidth(120);
			btnadd.setText("Add to City");
			btnadd.setStyle("-fx-border-color: #2A73FF; -fx-border-radius: 20;");
			btnadd.getStylesheets().add("//../style.css");
			ImageView btnadd0 = new ImageView();
			btnadd0.setFitHeight(28.0);
			btnadd0.setFitWidth(21.0);
			btnadd0.setPickOnBounds(true);
			btnadd0.setPreserveRatio(true);
			imagee = new Image(getClass().getResourceAsStream("/image/icons8-purchase-order-100.png"));
			btnadd0.setImage(imagee);
			btnadd.setGraphic(btnadd0);
        
			pane.getChildren().add(btnadd);
		 pane.getChildren().add(btnview);
        pane.getChildren().add(Map_name);
        pane.getChildren().add(label);
        pane.getChildren().add(flag);
        pane.getChildren().add(line);
        pane.getChildren().add(label1);
    
        getChildren().add(pane);

    }
    
    /**
     * View map image clicked.
     *
     * @param mouseEvent the mouse event
     */
    protected void view_map_image_clicked(MouseEvent mouseEvent) {
    	client.requestHandler.Catalog.getFullMapOfCity_toView(map);
    	
    }
    
    /**
     * View btn add clicked.
     *
     * @param mouseEvent the mouse event
     */
    protected void view_btn_add_clicked(MouseEvent mouseEvent) {
    	client.requestHandler.Catalog.addExternalmap(map);
    	
    }
	
	/**
	 * Gets the map name.
	 *
	 * @return the map name
	 */
	public entities.Map getMap_name() {
		// TODO Auto-generated method stub
		return this.map;
	}
}
