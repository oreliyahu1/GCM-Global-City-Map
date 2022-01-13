package guiControllers.CatalogEdit;

import java.util.ArrayList;

import animatefx.SlideInUp;
import guiControllers.GeneralValues;
import guiControllers.item.Map_toadd_item;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

// TODO: Auto-generated Javadoc
/**
 * The Class AddMap_Controller.
 */
public class AddMap_Controller {

	/** The instance. */
	public static AddMap_Controller instance;
	
    /** The pn items. */
    @FXML
    private VBox pnItems;
    
    /** The btn exit. */
    @FXML
    private ImageView btn_exit;

    /**
     * Exit all.
     *
     * @param event the event exit from current window
     */
    @FXML
    void exit_all(MouseEvent event) {
    	Stage s = (Stage)getPnItems().getScene().getWindow();
		s.close();
    }
    
    /**
     * Initialize.
     */
    @FXML
    void initialize() {
    	AddMap_Controller.instance = this;
    	client.requestHandler.Catalog.GetExternalmaps(GeneralValues.COUNTRY.getShortName(),GeneralValues.CITY.getName());
    }
    
    /**
     * Close window.exit from current window
     */
    public void close_window() {
    	Stage s = (Stage)getPnItems().getScene().getWindow();
		s.close();
    }
    
    /**
     * Update maps items. fill the vbox list with maps that we need to choose in order to add
     *
     * @param maps_of_city the maps of city
     */
    public void update_maps_items(ArrayList<entities.Map> maps_of_city) {
    	
    	
    	getPnItems().getChildren().clear();

		Runnable runn = new Runnable() {
			@Override
			public void run() {
				for (entities.Map map : maps_of_city) {
					
					Map_toadd_item sp = new Map_toadd_item(map);
					sp.setOnMouseEntered(event -> {
						sp.setStyle("-fx-background-color : #BDBBC3");
					});
					sp.setOnMouseExited(event -> {
						sp.setStyle("-fx-background-color : #EBE8F9");
					});
					sp.setOnMouseClicked(event -> {

					});
					Platform.runLater(() -> {
						getPnItems().getChildren().add(sp);
						new SlideInUp(sp).play();
					});
				}

			}
		};
		Thread thread = new Thread(runn);
		thread.start();
    	
    }
    
    /**
     * Gets the image of map. show a new window of the map image
     *
     * @param map the map
     * @return the image of map
     */
    public void get_image_of_map(entities.Map map) {
    	Image map_image = SwingFXUtils.toFXImage(map.getImage(), null);
    	ImageView image2 = new ImageView();
    	
    	Pane canvas = new Pane();
    	image2.setPreserveRatio(true);
    	image2.setFitHeight(450);
    	image2.setFitWidth(700);
    	canvas.setPrefHeight(image2.getFitHeight());
    	canvas.setPrefWidth(image2.getFitWidth());
    	image2.fitWidthProperty().bind(canvas.widthProperty());
    	image2.fitHeightProperty().bind(canvas.heightProperty());
    	
    	image2.setImage(map_image);
    	
    	canvas.getChildren().add(image2);
    	try {
		
    		Scene scene = new Scene(canvas);
			Stage primaryStage = new Stage();
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.setTitle("GCM - Map View");
			primaryStage.initModality(Modality.APPLICATION_MODAL);
			primaryStage.showAndWait();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	
    	
    }


	/**
	 * Gets the pn items.
	 *
	 * @return the pn items
	 */
	private VBox getPnItems() {
		return pnItems;
	}

    
    
    
}
