package guiControllers.item;


import client.requestHandler.Catalog;
import guiControllers.HomeController;
import guiControllers.CatalogEdit.EditCity_Controller;
import guiControllers.Subscribe.SubscribeController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
 * The Class CityBase extends the anchorpane container from the scene builder
 */
public class CityBase extends AnchorPane {
	
	/** The y. */
	private double x,y;
	
	/** The pane. */
	private final Pane pane;
	
	/** The btn subscribe. */
	private final Button btn_subscribe;
	
	/** The city name. */
	private final Label city_name;
	
	/** The maps. */
	private final Label maps;
	
	/** The flag. */
	private final ImageView flag;
	
	/** The tours. */
	private final Label tours;
	
	/** The line. */
	private final Line line;
	
	/** The description. */
	private final Label description;
	
	/** The places. */
	private final Label places;
	
	/** The version. */
	private final Label version;
	
	/** The edit city. */
	private final Button edit_city;
	
	/** The subscribed. */
	public boolean subscribed = false;
	
	/** The city. */
	private entities.City city;
	
	/** The city purchase. */
	private entities.Purchase city_purchase;
	
	/**
	 * Instantiates a new city base.
	 *
	 * @param c the c
	 */
	public CityBase(entities.City c) {
		this.city = c;
		pane = new Pane();
		btn_subscribe = new Button();
		city_name = new Label();
		maps = new Label();
		flag = new ImageView();
		tours = new Label();
		line = new Line();
		description = new Label();
		places = new Label();
		version = new Label();
		edit_city = new Button();

		setPrefHeight(81.0);
		setPrefWidth(710.0);
		setStyle("-fx-background-color: #EBE8F9; -fx-background-radius: 5; -fx-background-insets: 0;");

		pane.setLayoutX(0.0);
		pane.setLayoutY(0.0);
		pane.setPrefHeight(107.0);
		pane.setPrefWidth(710.0);
		pane.setStyle("-fx-border-color: #BDBBC3");

		if (HomeController.instance.user != null) {
	
				btn_subscribe.setDisable(false);
				btn_subscribe.setId("login_btn");
				btn_subscribe.setLayoutX(530.0);
				btn_subscribe.setLayoutY(70.0);
				btn_subscribe.setMinHeight(25.951171875);
				btn_subscribe.setMnemonicParsing(false);
				btn_subscribe.setOnMouseClicked(this::btn_ssubscribe_clicked);
				btn_subscribe.setPrefHeight(31.0);
				btn_subscribe.setPrefWidth(175.0);
				btn_subscribe.setStyle("-fx-border-color: #2A73FF; -fx-border-radius: 20;");
				btn_subscribe.setText("Purchase");
				btn_subscribe.getStylesheets().add("//../style.css");
			
			
		}
		
		if(Catalog.getMode().equals(Catalog.MODE.EDIT)) {
			Tooltip tooltip = new Tooltip("Edit City Description");
			Tooltip.install(edit_city, tooltip);
			edit_city.setDisable(false);
			edit_city.setLayoutX(660.0);
			edit_city.setLayoutY(18.0);
			edit_city.setMinHeight(25.951171875);
			edit_city.setMnemonicParsing(false);
			edit_city.setOnMouseClicked(this::btn_editcity_clicked);
			edit_city.setPrefHeight(31.0);
			edit_city.setPrefWidth(35.0);
			edit_city.setStyle("-fx-border-color: #2A73FF; -fx-border-radius: 20;");
			edit_city.setText("");
			edit_city.getStylesheets().add("//../style.css");
			ImageView img1 = new ImageView();
			img1.setFitHeight(28.0);
			img1.setFitWidth(21.0);
			img1.setPickOnBounds(true);
			img1.setPreserveRatio(true);
			Image image = new Image(getClass().getResourceAsStream("/image/icons8-edit-property-50.png"));
			img1.setImage(image);
			edit_city.setGraphic(img1);
			pane.getChildren().add(edit_city);	
		}
		
		
		
		
		ImageView imageView = new ImageView();
		imageView.setFitHeight(28.0);
		imageView.setFitWidth(21.0);
		imageView.setPickOnBounds(true);
		imageView.setPreserveRatio(true);
		Image image = new Image(getClass().getResourceAsStream("/image/icons8-schedule-50.png"));
		imageView.setImage(image);
		btn_subscribe.setGraphic(imageView);

		city_name.setLayoutX(68.0);
		city_name.setLayoutY(25.0);
		city_name.setText(c.getName());

		maps.setLayoutX(195.0);
		maps.setLayoutY(25.0);
		maps.setText(String.valueOf(c.getNumberOfMaps()));

		flag.setFitHeight(31.0);
		flag.setFitWidth(25.0);
		flag.setLayoutX(14.0);
		flag.setLayoutY(22.0);
		flag.setPickOnBounds(true);
		flag.setPreserveRatio(true);
		flag.setSmooth(true);
		image = new Image(getClass().getResourceAsStream("/image/icons8-city-buildings-50.png"));
		flag.setImage(image);

		tours.setLayoutX(338.0);
		tours.setLayoutY(25.0);
		tours.setText(String.valueOf(c.getNumberOfTours()));

		line.setEndX(490.0);
		line.setLayoutX(182.0);
		line.setLayoutY(55.0);
		line.setStartX(-120.0);
		line.setStroke(javafx.scene.paint.Color.valueOf("#bdbbc3"));

		description.setLayoutX(62.0);
		description.setLayoutY(66.0);
		description.setPrefHeight(21.0);
		description.setPrefWidth(420.0);
        Tooltip tooltip = new Tooltip(c.getDescription());
        Tooltip.install(description, tooltip);
        String desc = c.getDescription();
        if(c.getDescription().length() > 117) {
        	desc = desc.substring(0, 117);
        	desc += "...";
        }
		description.setText(desc);
		description.setTextAlignment(javafx.scene.text.TextAlignment.LEFT);
		description.setTextOverrun(javafx.scene.control.OverrunStyle.ELLIPSIS);
		description.setWrapText(false);

		places.setLayoutX(464.0);
		places.setLayoutY(25.0);
		places.setText(String.valueOf(c.getNumberOfPois()));

		version.setLayoutX(608.0);
		version.setLayoutY(25.0);
		version.setText(c.getVersion().toString());

		pane.getChildren().add(city_name);
		pane.getChildren().add(maps);
		pane.getChildren().add(flag);
		pane.getChildren().add(tours);
		pane.getChildren().add(line);
		pane.getChildren().add(description);
		pane.getChildren().add(places);
		pane.getChildren().add(version);

		getChildren().add(pane);

	}

	/**
	 * Gets the city name.
	 *
	 * @return the city name
	 */
	public Labeled getCity_name() {
		return city_name;
	}

	/**
	 * Btn ssubscribe clicked.
	 *
	 * @param mouseEvent the mouse event
	 */
	protected void btn_ssubscribe_clicked(MouseEvent mouseEvent) {
		 try {
				
			 SubscribeController.city = city;
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/subscribe/Subscribe.fxml"));
				Scene scene = new Scene(fxmlLoader.load());
			
				Stage primaryStage = new Stage();
				
				// scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
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
		

		// Hide this current window (if this is what you want)

	}
	
	/**
	 * Btn editcity clicked.
	 *
	 * @param mouseEvent the mouse event
	 */
	protected void btn_editcity_clicked(MouseEvent mouseEvent) {
		 try {
			
			 	EditCity_Controller.city = city;
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/CatalogEdit/EditCity.fxml"));
				Scene scene = new Scene(fxmlLoader.load());
			
				Stage primaryStage = new Stage();
				
				// scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
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

	/**
	 * Show subscribe.
	 */
	public void show_subscribe() {
		pane.getChildren().add(btn_subscribe);
	}

	/**
	 * Checks if is subscribed.
	 *
	 * @return true, if successful
	 */
	public boolean IsSubscribed() {
		return subscribed;
	}
	
	/**
	 * Sets the subscribed.
	 *
	 * @param sub the new subscribed
	 */
	public void set_subscribed(boolean sub) {
		subscribed = sub;
	}
	
	/**
	 * Gets the city purchase.
	 *
	 * @return the city purchase
	 */
	public entities.Purchase get_city_purchase(){
		return city_purchase;
	}
	
	/**
	 * Sets the purchase city.
	 *
	 * @param purch the new purchase city
	 */
	public void setpurchase_city(entities.Purchase purch) {
		city_purchase = purch;
	}

}
