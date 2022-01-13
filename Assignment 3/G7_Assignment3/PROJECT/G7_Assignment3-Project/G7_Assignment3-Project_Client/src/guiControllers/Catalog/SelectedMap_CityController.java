package guiControllers.Catalog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import animatefx.FadeOutUp;
import animatefx.SlideInUp;
import client.requestHandler.Catalog;
import entities.Coordinates;
import entities.Map;
import guiControllers.FxmlView;
import guiControllers.HomeController;
import guiControllers.item.PlaceBase;
import guiControllers.item.PlaceOnMap;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;


/**
 * The Class SelectedMap_CityController.
 */
public class SelectedMap_CityController {

	/** The instance. */
	public static SelectedMap_CityController instance;
	
	/** The Places. */
	private ArrayList<entities.PlaceOfInterestMap> Places = new ArrayList<entities.PlaceOfInterestMap>(); // places
	
	/** The places 2. */
	public ArrayList<PlaceOnMap> places2 = new ArrayList<PlaceOnMap>(); // location icons

	/** The selected map. */
	public entities.Map selected_map;

	/** The selected coordinates. */
	public Coordinates selected_Coordinates;

	/** The btn zoom in. */
	@FXML
	private ImageView btn_zoom_in;

	/** The btn zoom out. */
	@FXML
	private ImageView btn_zoom_out;

	/** The pn items. */
	@FXML
	public VBox pnItems;

	/** The img map. */
	// new
	@FXML
	private ImageView img_map;

	/** The zoom slider. */
	@FXML
	private Slider zoom_slider;

	/** The map scrollpane. */
	@FXML
	private ScrollPane map_scrollpane;

	/** The canvas. */
	@FXML
	public Pane canvas;
	


	/** The zoom group. */
	Group zoomGroup;

	/** The working collection. */
	ObservableList<Node> workingCollection;
	
	/** The n. */
	Node[] n = new Node[1];

	/** The btn add. */
	@FXML
	private Button btn_add;

	/** The y. */
	private double x,y;
	
	/**
	 * The Class NodeComparatorPlaceBase. by place name
	 */
	private class NodeComparatorPlaceBase implements Comparator<Node> {
		
		/* (non-Javadoc)
		 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
		 */
		@Override
		public int compare(Node o1, Node o2) {
			if(o1 instanceof PlaceBase && o2 instanceof PlaceBase) {
			PlaceBase sp1 = (PlaceBase) o1;
			PlaceBase sp2 = (PlaceBase) o2;
			String s1 = (String) sp1.Place_map.getText();
			String s2 = (String) sp2.Place_map.getText();

			return s1.compareTo(s2);
			}
			return 0;
		}
	}

	/**
	 * Btn add clicked. adding a place to selected map
	 *
	 * @param event the event
	 */
	@FXML
	void btn_add_clicked(MouseEvent event) {

		if (btn_add.getText().equals("Add Place to Map")) {
			btn_add.setText("Select Cordinate on Map");
			btn_add.setStyle("-fx-border-color: #2A73FF;-fx-border-radius:20;-fx-background-color: white;");

		} else {
			btn_add.setText("Add Place to Map");
			btn_add.setStyle("-fx-border-color: #2A73FF;-fx-border-radius:20;");
		}
	}

	/**
	 * Initialize.
	 */
	@FXML
	void initialize() {
		SelectedMap_CityController.instance = this;
		selected_Coordinates = new Coordinates(0, 0);

		if(!Catalog.getMode().equals(Catalog.MODE.EDIT)) {
			btn_add.setVisible(false);
		}
		// new
		zoom_slider.setMin(0.5);
		zoom_slider.setMax(1.5);
		zoom_slider.setValue(1.0);
		zoom_slider.valueProperty().addListener((o, oldVal, newVal) -> zoom((Double) newVal));
		// Wrap scroll content in a Group so ScrollPane re-computes scroll bars
		Group contentGroup = new Group();
		zoomGroup = new Group();
		contentGroup.getChildren().add(zoomGroup);
		zoomGroup.getChildren().add(map_scrollpane.getContent());
		map_scrollpane.setContent(contentGroup);

		//

		FXMLLoader loader3 = new FXMLLoader(getClass().getResource(FxmlView.LOADING.getFXML()));
		try {
			n[0] = loader3.load();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		pnItems.getChildren().add(n[0]);



	}

	/**
	 * Start.
	 *
	 * @param pane the pane
	 * @throws Exception the exception
	 */
	public void start(Pane pane) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource(FxmlView.CITY_SELECTED_MAP.getFXML()));

		Node n = null;
		try {
			n = fxmlLoader.load();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		pane.getChildren().clear();
		pane.getChildren().add(n);
		Platform.runLater(() -> {
			HomeController.instance.Set_Title(FxmlView.CITY_SELECTED_MAP.getTitle());
		});
	}

	/**
	 * Click zoom in.
	 *
	 * @param event the event
	 */
	@FXML
	void click_zoom_in(MouseEvent event) {
		ImageView which = (ImageView) event.getSource();
		double sliderVal = zoom_slider.getValue();
		if (which.getId().equals("btn_zoom_out")) {
			zoom_slider.setValue(sliderVal -= 0.1);
		} else {

			zoom_slider.setValue(sliderVal += 0.1);
		}
	}

	/**
	 * Zoom.
	 *
	 * @param scaleValue the scale value
	 */
	private void zoom(double scaleValue) {
//      System.out.println("airportapp.Controller.zoom, scaleValue: " + scaleValue);
		double scrollH = map_scrollpane.getHvalue();
		double scrollV = map_scrollpane.getVvalue();
		zoomGroup.setScaleX(scaleValue);
		zoomGroup.setScaleY(scaleValue);
		map_scrollpane.setHvalue(scrollH);
		map_scrollpane.setVvalue(scrollV);
	}

	/**
	 * Map click.get x,y to add a place on map click
	 *
	 * @param event the event
	 */
	@FXML
	void map_click(MouseEvent event) {

		if (btn_add.getText().equals("Select Cordinate on Map")) {
			//x_add = event.getX();
			//y_add = event.getY();
			selected_Coordinates.setX(event.getX());
			selected_Coordinates.setY(event.getY());
			 try {

				 FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/CatalogEdit/add_place_to_map.fxml"));
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
 
	
	///////////////////////////////////////////////////////////////
	
	
	/**
	 * Update map. response call this method
	 *
	 * @param places_on_map the places on map
	 */
	public void update_map(ArrayList<entities.PlaceOfInterestMap> places_on_map) {
		Places.clear();
		pnItems.getChildren().clear();
		canvas.getChildren().clear();
		canvas.getChildren().add(img_map);
		
		for (entities.PlaceOfInterestMap place : places_on_map) {
			Places.add(place);
		}
		add_on_map(Places);
	}

	/**
	 * Adds the on map. add marker on selected map
	 *
	 * @param places the places
	 */
	private void add_on_map(ArrayList<entities.PlaceOfInterestMap> places) {

		Runnable runn = new Runnable() {
			@Override
			public void run() {
				for (entities.PlaceOfInterestMap p : Places) {

					PlaceOnMap pp = new PlaceOnMap(p);
					places2.add(pp);

					PlaceBase sp = new PlaceBase(p);

					final Tooltip customTooltip = new Tooltip();
					sp.setOnMouseEntered(event -> {
						sp.setStyle("-fx-background-color : #BDBBC3");

					});
					sp.setOnMouseExited(event -> {
						sp.setStyle("-fx-background-color : #EBE8F9");
						customTooltip.hide();
						pp.setStyle("");
					});
					sp.setOnMouseClicked(event -> {
						move_to_place(pp, p, customTooltip, sp);
					});

					Platform.runLater(() -> {
						pnItems.getChildren().add(sp);

						canvas.getChildren().add(pp);

						new SlideInUp(sp).play();
					});
				}

				FadeOutUp animate = new FadeOutUp(n[0]);
				animate.play();
				animate.setOnFinished(event1 -> {
					Platform.runLater(() -> {
						pnItems.getChildren().remove(n[0]);
						workingCollection = FXCollections.observableArrayList(pnItems.getChildren());
						Collections.sort(workingCollection, new NodeComparatorPlaceBase());
						pnItems.getChildren().setAll(workingCollection);
						// search_text.setDisable(false);
					});

				});

			}
		};
		Thread thread = new Thread(runn);
		thread.start();

	}

	/**
	 * Move to place.
	 *
	 * @param pp the pp
	 * @param p the p
	 * @param customTooltip the custom tooltip
	 * @param sp the sp
	 */
	public void move_to_place(PlaceOnMap pp, entities.PlaceOfInterestMap p, Tooltip customTooltip, PlaceBase sp) {
		double mapWidth = zoomGroup.getBoundsInLocal().getWidth();
		double mapHeight = zoomGroup.getBoundsInLocal().getHeight();
		double scrollH = (Double) pp.x / mapWidth;
		double scrollV = (Double) pp.y / mapHeight;

		final Timeline timeline = new Timeline();
		final KeyValue kv1 = new KeyValue(map_scrollpane.hvalueProperty(), scrollH);
		final KeyValue kv2 = new KeyValue(map_scrollpane.vvalueProperty(), scrollV);
		final KeyValue kv3 = new KeyValue(zoomGroup.scaleXProperty(), 1.5);
		final KeyValue kv4 = new KeyValue(zoomGroup.scaleYProperty(), 1.5);
	        
		
		
		final KeyFrame kf = new KeyFrame(Duration.millis(500), kv1, kv2,kv3,kv4);
		timeline.getKeyFrames().add(kf);
		timeline.play();
		timeline.setOnFinished(event1 -> {

			zoom_slider.setValue(1.5);
			
			Point2D point = pp.localToScene(0.0, 0.0);
			String catalog;
			if(p.getCategory() == null)
				catalog = "";
			else
				catalog = p.getCategory().getCategory();
			customTooltip.setText(p.getName() + "\n" + catalog + "\nCoordinate: (" + (p.getCordinates().getX()+25) + "," + (p.getCordinates().getY()+40) + ")\n" + p.getDescription());
			customTooltip.setAutoHide(true);
			customTooltip.show(sp.getScene().getWindow(),
					point.getX() + 40 + +pp.getScene().getX() + pp.getScene().getWindow().getX(),
					point.getY() + pp.getScene().getY() + pp.getScene().getWindow().getY());
			pp.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0.1, 0.1, 0.1);");

		});
	}
	
	/**
	 * Gets the full map.
	 *
	 * @param map the map
	 * @return the full map
	 */
	public void getFullMap(Map map) {

		// selected_map = map;
		Image map_image;
		if (!map.getBase64_image().equals("")) {
			map_image = SwingFXUtils.toFXImage(map.getImage(), null);

			double height = map_image.getHeight();
			double widght = map_image.getWidth();

			img_map.setFitHeight(height);
			img_map.setFitWidth(widght);
			canvas.setPrefHeight(height);
			canvas.setPrefWidth(widght);
			img_map.fitWidthProperty().bind(canvas.widthProperty());
			img_map.fitHeightProperty().bind(canvas.heightProperty());
			img_map.setPreserveRatio(true);
			img_map.setImage(map_image);

		}

	}
	
	
	/**
	 * Refresh poi map by calling request handler.
	 */
	/////////////////////////////////////////
	public void refreshPoiMap() {

		client.requestHandler.Catalog.getAllPOIOFMap(selected_map);
	}
	
}
