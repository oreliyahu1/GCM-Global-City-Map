package guiControllers.itemtour;

import java.util.Optional;

import guiControllers.Catalog.SelectedCity_ToursPlacesController;
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

	/** The Place map. */
	public final Label Place_map;
	
	/** The flag. */
	private final ImageView flag;
	
	/** The btn add. */
	private final Button btn_add;

	/** The poi. */
	private entities.PlaceOfInterest poi;

	/**
	 * Instantiates a new place base.
	 *
	 * @param p the p
	 */
	public PlaceBase(entities.PlaceOfInterest p) {

		pane = new Pane();
		Place_map = new Label();
		flag = new ImageView();
		btn_add = new Button();

		poi = p;

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

		
		
		btn_add.setDisable(false);
        btn_add.setId("login_btn");
        btn_add.setLayoutX(180.0);
        btn_add.setLayoutY(21.0);
        btn_add.setMinHeight(25.951171875);
        btn_add.setMnemonicParsing(false);
        btn_add.setOnMouseClicked(this::btn_add_clicked);
        btn_add.setPrefHeight(31.0);
        btn_add.setPrefWidth(42.0);
        btn_add.setStyle("-fx-border-color: #2A73FF; -fx-border-radius: 20;");
        btn_add.getStylesheets().add("//../style.css");
        ImageView img1 = new ImageView();
        img1.setFitHeight(28.0);
        img1.setFitWidth(21.0);
        img1.setPickOnBounds(true);
        img1.setPreserveRatio(true);
        image = new Image(getClass().getResourceAsStream("/image/icons8-plus-50.png"));
		img1.setImage(image);
		btn_add.setGraphic(img1);
        pane.getChildren().add(btn_add);
        
		pane.getStylesheets().add("/style.css");
		getStylesheets().add("/style.css");

		pane.getChildren().add(Place_map);
		pane.getChildren().add(flag);

		getChildren().add(pane);

	}
	
	/**
	 * Gets the place.
	 *
	 * @return the place
	 */
	public entities.PlaceOfInterest getPlace(){
		return poi;
	}
	
	/**
	 * Btn add clicked.
	 *
	 * @param mouseEvent the mouse event
	 */
	protected void btn_add_clicked(MouseEvent mouseEvent) {
		Alert alert = new Alert(AlertType.WARNING, "Add " + poi.getName() + " to this tour?", ButtonType.YES,
				ButtonType.NO);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.YES) {
			entities.PlaceOfInterestTour placet = new entities.PlaceOfInterestTour(poi, SelectedCity_ToursPlacesController.selected_tour, 0, 0);
			SelectedCity_ToursPlacesController.instance.add_one_place_to_tour(placet);
			SelectedCity_ToursPlacesController.instance.getPnItems2().getChildren().remove(this);
		} 
		
		
	}

}
