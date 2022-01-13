package guiControllers.itemtour;

import java.util.Optional;

import animatefx.SlideInUp;
import client.requestHandler.Catalog;
import guiControllers.Catalog.SelectedCity_ToursPlacesController;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

/**
 * The Class PlaceofInterestItem.
 */
public class PlaceofInterestItem extends AnchorPane {

	/** The pane. */
	private final Pane pane;
	
	/** The place name. */
	private final Label place_name;
	
	/** The flag. */
	private final ImageView flag;
	
	/** The label 0. */
	private final Label label0;
	
	/** The line. */
	private final Line line;
	
	/** The label 1. */
	private final Label label1;
	
	/** The label 2. */
	private final Label label2;
	
	/** The type. */
	private final Label type;
	
	/** The btn delete. */
	public final Button btn_delete;
	
	/** The btn edit. */
	public final Button btn_edit;
	
	/** The mins. */
	private final ComboBox<Integer> mins;
	
	/** The hours. */
	private final ComboBox<Integer> hours;
	
	/** The days. */
	private final ComboBox<Integer> days;
	
	/** The this place. */
	entities.PlaceOfInterestTour THIS_PLACE;

	/**
	 * Instantiates a new placeof interest item.
	 *
	 * @param place the place
	 */
	public PlaceofInterestItem(entities.PlaceOfInterestTour place) {

		THIS_PLACE = place;
		pane = new Pane();
		place_name = new Label();
		flag = new ImageView();
		label0 = new Label();
		line = new Line();
		label1 = new Label();
		label2 = new Label();
		type = new Label();
		mins = new ComboBox<Integer>();
		hours = new ComboBox<Integer>();
		days = new ComboBox<Integer>();
		for (int i = 0; i < 60; i++)
			mins.getItems().add(i);
		for (int i = 0; i < 24; i++)
			hours.getItems().add(i);
		for (int i = 0; i < 10; i++)
			days.getItems().add(i);



		btn_delete = new Button();
		btn_edit = new Button();

		setPrefHeight(81.0);
		setPrefWidth(710.0);
		setStyle("-fx-background-color: #EBE8F9; -fx-background-radius: 5; -fx-background-insets: 0;");

		pane.setLayoutX(0.0);
		pane.setLayoutY(0.0);
		pane.setPrefHeight(101.0);
		pane.setPrefWidth(710.0);
		pane.setStyle("-fx-border-color: #BDBBC3");

		place_name.setLayoutX(62.0);
		place_name.setLayoutY(25.0);
		place_name.setText(place.getName());
		
		

		flag.setFitHeight(31.0);
		flag.setFitWidth(25.0);
		flag.setLayoutX(14.0);
		flag.setLayoutY(22.0);
		flag.setPickOnBounds(true);
		flag.setPreserveRatio(true);
		flag.setSmooth(true);
		Image image = new Image(getClass().getResourceAsStream("/image/icons8-map-pinpoint-48.png"));
		flag.setImage(image);

		type.setLayoutX(450.0);
		type.setLayoutY(25.0);
		String s = new String("");
		if ((!Catalog.getMode().equals(Catalog.MODE.EDIT)) || SelectedCity_ToursPlacesController.edit_tour == false) {
			if (place.getCategory() == null)
				s = "";
			else
				s = place.getCategory().getCategory();
		}
		Tooltip tooltip = new Tooltip(place.getName() + " - " + s);
		Tooltip.install(place_name, tooltip);

		type.setText(s);

		label0.setLayoutX(320.0);
		label0.setLayoutY(25.0);
		label0.setText(String.valueOf(place.isAccessible()));

		line.setEndX(490.0);
		line.setLayoutX(182.0);
		line.setLayoutY(55.0);
		line.setStartX(-120.0);
		line.setStroke(javafx.scene.paint.Color.valueOf("#bdbbc3"));

		int num_seconds = place.getSecTime();
		int dayss = num_seconds / (3600* 24);
		num_seconds = num_seconds % (24*3600);
		int hourss = num_seconds / 3600;
		num_seconds = num_seconds % (24*3600);
		num_seconds %=3600;
		int minss = num_seconds /60;

		mins.setLayoutX(390);
		mins.setLayoutY(66);
		mins.setPrefWidth(55);
		mins.setPromptText("M");
		mins.setValue((int)minss);

		hours.setLayoutX(330);
		hours.setLayoutY(66);
		hours.setPrefWidth(55);
		hours.setPromptText("H");
		hours.setValue((int)hourss);

		days.setLayoutX(270);
		days.setLayoutY(66);
		days.setPrefWidth(55);
		days.setPromptText("D");
		days.setValue((int)dayss);
		/*
		 * time.setLayoutX(430); time.setLayoutY(66.0);
		 * time.setText(String.valueOf(place.getSecTime()));
		 */

		label1.setLayoutX(62.0);
		label1.setLayoutY(66.0);
		
		String desc = place.getDescription();
		tooltip = new Tooltip(place.getDescription());
		Tooltip.install(label1, tooltip);
		if(SelectedCity_ToursPlacesController.edit_tour == false) {
			if (place.getDescription().length() > 117) {
				desc = desc.substring(0, 117);
				desc += "...";
			}
		}else {
			if (place.getDescription().length() > 10) {
				desc = desc.substring(0, 10);
				desc += "...";
			}
		}

		
		
		label1.setText(desc);

		label1.setTextAlignment(javafx.scene.text.TextAlignment.LEFT);
		label1.setTextOverrun(javafx.scene.control.OverrunStyle.ELLIPSIS);
		label1.setWrapText(false);

		// new
		// need to cov from sec to time
		label2.setLayoutX(410.0);
		label2.setLayoutY(25.0);
		// String time =
		// String.format("%02d:%02d:%02d",(tour.getNumberOfTourDuration()/3600),
		// ((tour.getNumberOfTourDuration() % 3600)/60), (tour.getNumberOfTourDuration()
		// % 60));
		label2.setText("");
		if (SelectedCity_ToursPlacesController.edit_tour == true) {
			// delete button
			btn_delete.setText("");
			btn_delete.setDisable(false);
			btn_delete.setLayoutX(610.0);
			btn_delete.setLayoutY(21.0);
			btn_delete.setMinHeight(25.951171875);
			btn_delete.setMnemonicParsing(false);
			btn_delete.setOnMouseClicked(this::btn_delete_clicked);
			btn_delete.setPrefHeight(31.0);
			btn_delete.setPrefWidth(30);
			btn_delete.setStyle("-fx-border-color: #2A73FF; -fx-border-radius: 20;");
			btn_delete.getStylesheets().add("//../style.css");
			ImageView img1 = new ImageView();
			img1.setFitHeight(28.0);
			img1.setFitWidth(21.0);
			img1.setPickOnBounds(true);
			img1.setPreserveRatio(true);
			image = new Image(getClass().getResourceAsStream("/image/icons8-trash-48.png"));
			img1.setImage(image);
			btn_delete.setGraphic(img1);
			pane.getChildren().add(btn_delete);

		}
		if ((!Catalog.getMode().equals(Catalog.MODE.EDIT)) || SelectedCity_ToursPlacesController.edit_tour == false) {
			Label time = new Label();
			time.setLayoutX(600);
			time.setLayoutY(21);
			time.setText(dayss + "D :" + hourss + "H :" + minss + "M");
			pane.getChildren().add(time); 
		}
		
		pane.getStylesheets().add("/style.css");
		getStylesheets().add("/style.css");

		pane.getChildren().add(place_name);
		pane.getChildren().add(flag);
		pane.getChildren().add(label0);
		pane.getChildren().add(line);
		pane.getChildren().add(label1);
		pane.getChildren().add(type);
		// new
		pane.getChildren().add(label2);
		// pane.getChildren().add(time);
		if (SelectedCity_ToursPlacesController.edit_tour == true) {
			pane.getChildren().add(mins);
			pane.getChildren().add(hours);
			pane.getChildren().add(days);
		}
		getChildren().add(pane);

	}

	/**
	 * Adds the edit layout.
	 */
	public void add_edit_layout() {
		setPrefHeight(81.0);
		setPrefWidth(450.0);
		pane.setLayoutX(0.0);
		pane.setLayoutY(0.0);
		pane.setPrefHeight(101.0);
		pane.setPrefWidth(450.0);

		btn_delete.setLayoutX(400.0);
		line.setEndX(250.0);
		line.setLayoutX(182.0);
		line.setLayoutY(55.0);
		line.setStartX(-120.0);
	}

	/**
	 * Client layout.
	 */
	public void client_layout() {
		setPrefHeight(81.0);
		setPrefWidth(710.0);
		pane.setLayoutX(0.0);
		pane.setLayoutY(0.0);
		pane.setPrefHeight(101.0);
		pane.setPrefWidth(710.0);

		btn_delete.setLayoutX(610.0);
		line.setEndX(490.0);
		line.setLayoutX(182.0);
		line.setLayoutY(55.0);
		line.setStartX(-120.0);
	}

	/**
	 * Returnmins.
	 *
	 * @return the int
	 */
	private int returnmins() {
		return mins.getSelectionModel().getSelectedItem().intValue();
	}

	/**
	 * Returnhours.
	 *
	 * @return the int
	 */
	private int returnhours() {
		return hours.getSelectionModel().getSelectedItem().intValue();
	}

	/**
	 * Returndays.
	 *
	 * @return the int
	 */
	private int returndays() {
		return days.getSelectionModel().getSelectedItem().intValue();
	}

	/**
	 * Gets the sectime.
	 *
	 * @return the sectime
	 */
	public int getsectime() {
		return (returnmins() * 60 + returnhours() * 3600 + returndays() * 86400);
	}

	/**
	 * Gets the place name.
	 *
	 * @return the place name
	 */
	public Labeled getPlace_name() {
		// TODO Auto-generated method stub
		return place_name;
	}

	/**
	 * Gets the placetour.
	 *
	 * @return the placetour
	 */
	public entities.PlaceOfInterestTour getplacetour() {
		return THIS_PLACE;
	}

	/**
	 * Btn delete clicked.
	 *
	 * @param mouseEvent the mouse event
	 */
	protected void btn_delete_clicked(MouseEvent mouseEvent) {
		Alert alert = new Alert(AlertType.WARNING,
				"Are you sure to Delete " + THIS_PLACE.getName() + " from this tour?", ButtonType.YES, ButtonType.NO);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.YES) {
			SelectedCity_ToursPlacesController.instance.getPnItems().getChildren().remove(this);
			
			PlaceBase sp = new PlaceBase(THIS_PLACE);

			sp.setOnMouseEntered(event -> {
				sp.setStyle("-fx-background-color : #BDBBC3");

			});
			sp.setOnMouseExited(event -> {
				sp.setStyle("-fx-background-color : #EBE8F9");

			});


			Platform.runLater(() -> {
				SelectedCity_ToursPlacesController.instance.getPnItems2().getChildren().add(sp);
				new SlideInUp(sp).play();
			});
			
		}
	}
}
