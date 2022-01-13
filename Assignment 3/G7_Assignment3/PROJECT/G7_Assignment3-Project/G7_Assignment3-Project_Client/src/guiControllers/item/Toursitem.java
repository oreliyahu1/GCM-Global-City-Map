package guiControllers.item;

import java.util.Optional;

import animatefx.FadeOutRight;
import client.requestHandler.Catalog;
import entities.Permission;
import guiControllers.HomeController;
import guiControllers.SceneController;
import guiControllers.Catalog.SelectedCity_ToursPlacesController;
import guiControllers.CatalogEdit.Edit_Tour_Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * The Class Toursitem.
 */
public class Toursitem extends AnchorPane {

	/** The pane. */
	private final Pane pane;
	
	/** The tour name. */
	private final Label tour_name;
	
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
	
	/** The recommended. */
	private final Label recommended;
	
	/** The btn delete. */
	private final Button btn_delete;
	
	/** The btn edit. */
	private final Button btn_edit;
	
	/** The selected tour. */
	private entities.Tour selected_tour;
	
	/** The btn edit 2. */
	private final Button btn_edit2;

	/** The y. */
	private double x,y;
	
	/**
	 * Instantiates a new toursitem.
	 *
	 * @param tour the tour
	 */
	public Toursitem(entities.Tour tour) {
		selected_tour = tour;
		pane = new Pane();
		tour_name = new Label();
		flag = new ImageView();
		label0 = new Label();
		line = new Line();
		label1 = new Label();
		label2 = new Label();
		recommended = new Label();
		btn_delete = new Button();
		btn_edit = new Button();
		btn_edit2 = new Button();

		setPrefHeight(81.0);
		setPrefWidth(710.0);
		setStyle("-fx-background-color: #EBE8F9; -fx-background-radius: 5; -fx-background-insets: 0;");

		pane.setLayoutX(0.0);
		pane.setLayoutY(0.0);
		pane.setPrefHeight(101.0);
		pane.setPrefWidth(710.0);
		pane.setStyle("-fx-border-color: #BDBBC3");

		tour_name.setLayoutX(68.0);
		tour_name.setLayoutY(25.0);
		tour_name.setText(tour.getName());

		flag.setFitHeight(31.0);
		flag.setFitWidth(25.0);
		flag.setLayoutX(14.0);
		flag.setLayoutY(22.0);
		flag.setPickOnBounds(true);
		flag.setPreserveRatio(true);
		flag.setSmooth(true);
		Image image = new Image(getClass().getResourceAsStream("/image/icons8-map-pinpoint-48.png"));
		flag.setImage(image);

		recommended.setLayoutX(180.0);
		recommended.setLayoutY(25.0);
		recommended.setText(String.valueOf(tour.isRecommended()));

		label0.setLayoutX(360.0);
		label0.setLayoutY(25.0);
		label0.setText(String.valueOf(tour.getNumberOfPois()));

		line.setEndX(490.0);
		line.setLayoutX(182.0);
		line.setLayoutY(55.0);
		line.setStartX(-120.0);
		line.setStroke(javafx.scene.paint.Color.valueOf("#bdbbc3"));

		label1.setLayoutX(62.0);
		label1.setLayoutY(66.0);
		
		Tooltip tooltip = new Tooltip(tour.getDescription());
		Tooltip.install(label1, tooltip);
		String desc = tour.getDescription();
		if (tour.getDescription() != null) {
			if (tour.getDescription().length() > 117) {
				desc = desc.substring(0, 117);
				desc += "...";
			}
		}
		label1.setText(desc);

		label1.setTextAlignment(javafx.scene.text.TextAlignment.LEFT);
		label1.setTextOverrun(javafx.scene.control.OverrunStyle.ELLIPSIS);
		label1.setWrapText(false);

		// new
		// need to cov from sec to time
		label2.setLayoutX(450.0);
		label2.setLayoutY(25.0);
		
		
		int num_seconds = (int)tour.getNumberOfTourDuration();
		int dayss = num_seconds / (3600* 24);
		num_seconds = num_seconds % (24*3600);
		int hourss = num_seconds / 3600;
		num_seconds = num_seconds % (24*3600);
		num_seconds %=3600;
		int minss = num_seconds /60;
		
		String time = String.format("%02dD :%02dH :%02dM", ((int)dayss),
				((int)hourss), ((int) minss));
		
		label2.setText(time);

		if(Catalog.getMode().equals(Catalog.MODE.EDIT)) {
			if(HomeController.instance.user.getPermission().checkRole(Permission.Role.COMPANYMANAGER) ||
					(HomeController.instance.user.getPermission().checkRole(Permission.Role.PRODUCTMANAGER))) {
		
				Tooltip tool = new Tooltip("Delete Tour");
				Tooltip.install(btn_delete, tool);
				// delete button
				btn_delete.setText("");
				btn_delete.setDisable(false);
				btn_delete.setLayoutX(565.0);
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
	

		// edit pois button
		Tooltip tool = new Tooltip("Edit Tour places by Adding or Deleting!");
		Tooltip.install(btn_edit, tool);
		btn_edit.setText("");
		btn_edit.setDisable(false);
		btn_edit.setLayoutX(610.0);
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
		

		// edit without pois
		tool = new Tooltip("Edit Tour name,recommended and description!");
		Tooltip.install(btn_edit2, tool);
		btn_edit2.setText("");
		btn_edit2.setDisable(false);
		btn_edit2.setLayoutX(655.0);
		btn_edit2.setLayoutY(21.0);
		btn_edit2.setMinHeight(25.951171875);
		btn_edit2.setMnemonicParsing(false);
		btn_edit2.setOnMouseClicked(this::btn_edit_clicked2);
		btn_edit2.setPrefHeight(31.0);
		btn_edit2.setPrefWidth(30);
		btn_edit2.setStyle("-fx-border-color: #2A73FF; -fx-border-radius: 20;");
		btn_edit2.getStylesheets().add("//../style.css");
		img2 = new ImageView();
		img2.setFitHeight(28.0);
		img2.setFitWidth(21.0);
		img2.setPickOnBounds(true);
		img2.setPreserveRatio(true);
		image = new Image(getClass().getResourceAsStream("/image/icons8-edit-50.png"));
		img2.setImage(image);
		btn_edit2.setGraphic(img2);
		

		pane.getStylesheets().add("/style.css");
		getStylesheets().add("/style.css");

		if(Catalog.getMode().equals(Catalog.MODE.EDIT)) {
			pane.getChildren().add(btn_edit2);
			pane.getChildren().add(btn_edit);
			
		}
		
		pane.getChildren().add(tour_name);
		pane.getChildren().add(flag);
		pane.getChildren().add(label0);
		pane.getChildren().add(line);
		pane.getChildren().add(label1);
		pane.getChildren().add(recommended);
		// new
		pane.getChildren().add(label2);
		//
		getChildren().add(pane);

	}

	/**
	 * Gets the tour name.
	 *
	 * @return the tour name
	 */
	public Labeled getTour_name() {
		// TODO Auto-generated method stub
		return tour_name;
	}

	/**
	 * Btn delete clicked.
	 *
	 * @param mouseEvent the mouse event
	 */
	protected void btn_delete_clicked(MouseEvent mouseEvent) {

		Alert alert = new Alert(AlertType.WARNING, "Are you sure to Delete " + selected_tour.getName() + "?",
				ButtonType.YES, ButtonType.NO);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.YES) {
			client.requestHandler.Catalog.DeleteTour(selected_tour);
		}	
		// SelectedCity_ToursController.instance.pnItems.getChildren().remove(this);
	}

	/**
	 * Btn edit clicked.
	 *
	 * @param mouseEvent the mouse event
	 */
	protected void btn_edit_clicked(MouseEvent mouseEvent) {
		FadeOutRight anim1 = new FadeOutRight(HomeController.instance.PANE);
		anim1.play();
		anim1.setResetOnFinished(true);
		anim1.setOnFinished(event1 -> {
			SceneController.push(HomeController.instance.PANE);
			SceneController.push_title(HomeController.instance.Get_Title());
			SelectedCity_ToursPlacesController LookupForm = new SelectedCity_ToursPlacesController();
			SelectedCity_ToursPlacesController.edit_tour = true;
			SelectedCity_ToursPlacesController.selected_tour = selected_tour;
			
			try {

				LookupForm.start(HomeController.instance.PANE, selected_tour);

				// SelectedCity_ToursPlacesController.instance.selected_tour = tour;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});

	}

	/**
	 * Btn edit clicked 2.
	 *
	 * @param mouseEvent the mouse event
	 */
	protected void btn_edit_clicked2(MouseEvent mouseEvent) {
	
		 try {
			 Edit_Tour_Controller.tour = selected_tour;
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/CatalogEdit/Edit_tour.fxml"));
				Scene scene = new Scene(fxmlLoader.load());
			
				Stage primaryStage = new Stage();
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
}
