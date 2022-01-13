package guiControllers.item;

import java.util.Optional;

import client.requestHandler.Catalog;
import entities.Permission;
import guiControllers.GeneralValues;
import guiControllers.HomeController;
import guiControllers.CatalogEdit.Add_Edit_Place_Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
	
	/** The y. */
	private double x,y;

	/** The this place. */
	entities.PlaceOfInterest THIS_PLACE;

	/**
	 * Instantiates a new placeof interest item.
	 *
	 * @param place the place
	 */
	public PlaceofInterestItem(entities.PlaceOfInterest place) {

		THIS_PLACE = place;
		pane = new Pane();
		place_name = new Label();
		flag = new ImageView();
		label0 = new Label();
		line = new Line();
		label1 = new Label();
		label2 = new Label();
		type = new Label();

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

		place_name.setLayoutX(68.0);
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
		if (place.getCategory() == null)
			s = "";
		else
			s = place.getCategory().getCategory();

		type.setText(s);

		label0.setLayoutX(320.0);
		label0.setLayoutY(25.0);
		label0.setText(String.valueOf(place.isAccessible()));

		line.setEndX(490.0);
		line.setLayoutX(182.0);
		line.setLayoutY(55.0);
		line.setStartX(-120.0);
		line.setStroke(javafx.scene.paint.Color.valueOf("#bdbbc3"));

		label1.setLayoutX(62.0);
		label1.setLayoutY(66.0);
		Tooltip tooltip = new Tooltip(place.getDescription());
		Tooltip.install(label1, tooltip);
		String desc = place.getDescription();
		if (place.getDescription().length() > 117) {
			desc = desc.substring(0, 117);
			desc += "...";
		}
		label1.setText(desc);

		label1.setTextAlignment(javafx.scene.text.TextAlignment.LEFT);
		label1.setTextOverrun(javafx.scene.control.OverrunStyle.ELLIPSIS);
		label1.setWrapText(false);

		// new
		// need to cov from sec to time
		label2.setLayoutX(410.0);
		label2.setLayoutY(25.0);

		Tooltip tool = new Tooltip("Delete Place of Interest");
		Tooltip.install(btn_delete, tool);
		label2.setText("");

		// delete button
		if(Catalog.getMode().equals(Catalog.MODE.EDIT)) {
			if(HomeController.instance.user.getPermission().checkRole(Permission.Role.COMPANYMANAGER) ||
					(HomeController.instance.user.getPermission().checkRole(Permission.Role.PRODUCTMANAGER))) {
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
		}
		
		if(Catalog.getMode().equals(Catalog.MODE.EDIT)) {
			tool = new Tooltip("Edit Place of Interest");
			Tooltip.install(btn_edit, tool);
			btn_edit.setText("");
			btn_edit.setDisable(false);
			btn_edit.setLayoutX(655.0);
			btn_edit.setLayoutY(21.0);
			btn_edit.setMinHeight(25.951171875);
			btn_edit.setMnemonicParsing(false);
			btn_edit.setOnMouseClicked(this::btn_edit_clicked);
			btn_edit.setPrefHeight(31.0);
			btn_edit.setPrefWidth(30);
			btn_edit.setStyle("-fx-border-color: #2A73FF; -fx-border-radius: 20;");
			btn_edit.getStylesheets().add("//../style.css");
			ImageView img2 = new ImageView();
			img2.setFitHeight(28.0);
			img2.setFitWidth(21.0);
			img2.setPickOnBounds(true);
			img2.setPreserveRatio(true);
			image = new Image(getClass().getResourceAsStream("/image/icons8-edit-property-50.png"));
			img2.setImage(image);
			btn_edit.setGraphic(img2);
			pane.getChildren().add(btn_edit);
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
		//
		getChildren().add(pane);

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
	 * Btn delete clicked.
	 *
	 * @param mouseEvent the mouse event
	 */
	protected void btn_delete_clicked(MouseEvent mouseEvent) {
		Alert alert = new Alert(AlertType.WARNING, "Are you sure to Delete " + THIS_PLACE.getName() + "?", ButtonType.YES,
				ButtonType.NO);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.YES) {
			client.requestHandler.Catalog.DeletePOICity(THIS_PLACE);
		} 


	}

	/**
	 * Btn edit clicked.
	 *
	 * @param mouseEvent the mouse event
	 */
	protected void btn_edit_clicked(MouseEvent mouseEvent) {
		// open dialog to edit place
	
		 try {

			 Add_Edit_Place_Controller.selected_poi = THIS_PLACE;
				Add_Edit_Place_Controller.add_edit_mode = false;
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/CatalogEdit/Add_place.fxml"));
				Scene scene = new Scene(fxmlLoader.load());
			
				Stage primaryStage = new Stage();
				
				// scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.setResizable(false);
				primaryStage.initStyle(StageStyle.UNDECORATED);
				
				Add_Edit_Place_Controller.instance.label_country.setText(GeneralValues.COUNTRY.getName());
				Add_Edit_Place_Controller.instance.label_city.setText(THIS_PLACE.getCity().getName());
				Add_Edit_Place_Controller.instance.txt_name.setText(THIS_PLACE.getName());
				Add_Edit_Place_Controller.instance.txt_desc.setText(THIS_PLACE.getDescription());
				if (THIS_PLACE.isAccessible() == true)
					Add_Edit_Place_Controller.instance.checkbox_access.setSelected(true);
				else
					Add_Edit_Place_Controller.instance.checkbox_access.setSelected(false);

				
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
