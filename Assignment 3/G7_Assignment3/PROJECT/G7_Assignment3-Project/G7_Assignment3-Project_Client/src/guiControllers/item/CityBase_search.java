package guiControllers.item;




import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import javafx.scene.shape.Line;


// TODO: Auto-generated Javadoc
/**
 * The Class CityBase_search extends the anchorpane container from the scene builder.
 */
public class CityBase_search extends AnchorPane {

	/** The pane. */
	private final Pane pane;
	
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

	/** The version. */
	private final Label version;
	
	/**  The total places of city. */
	private final Label Places;
	
	/**  The total tour of city. */
	private final Label Tours;
	
	
	
	/**
	 * Instantiates a new city base search.
	 *
	 * @param c the c
	 */
	public CityBase_search(entities.City c) {
		
		pane = new Pane();
		city_name = new Label();
		maps = new Label();
		flag = new ImageView();
		tours = new Label();
		line = new Line();
		description = new Label();
		version = new Label();
		Places = new Label();
		Tours = new Label();

		setPrefHeight(81.0);
		setPrefWidth(710.0);
		setStyle("-fx-background-color: #EBE8F9; -fx-background-radius: 5; -fx-background-insets: 0;");

		pane.setLayoutX(0.0);
		pane.setLayoutY(0.0);
		pane.setPrefHeight(107.0);
		pane.setPrefWidth(710.0);
		pane.setStyle("-fx-border-color: #BDBBC3");



		city_name.setLayoutX(68.0);
		city_name.setLayoutY(25.0);
		city_name.setText(c.getName());

		maps.setLayoutX(338.0);
		maps.setLayoutY(25.0);
		maps.setText(String.valueOf(c.getNumberOfMaps()));

		flag.setFitHeight(31.0);
		flag.setFitWidth(25.0);
		flag.setLayoutX(14.0);
		flag.setLayoutY(22.0);
		flag.setPickOnBounds(true);
		flag.setPreserveRatio(true);
		flag.setSmooth(true);
		Image image = new Image(getClass().getResourceAsStream("/image/icons8-city-buildings-50.png"));
		flag.setImage(image);

		tours.setLayoutX(195.0);
		tours.setLayoutY(25.0);
		tours.setText(c.getCountry().getName());

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

		

		version.setLayoutX(468.0);
		version.setLayoutY(25.0);
		version.setText("V" + c.getVersion().toString());
		
		Places.setLayoutX(568.0);
		Places.setLayoutY(25.0);
		Places.setText(String.valueOf(c.getNumberOfPois()));
		
		Tours.setLayoutX(659.0);
		Tours.setLayoutY(25.0);
		Tours.setText(String.valueOf(c.getNumberOfTours()));

		pane.getChildren().add(city_name);
		pane.getChildren().add(maps);
		pane.getChildren().add(flag);
		pane.getChildren().add(tours);
		pane.getChildren().add(line);
		pane.getChildren().add(description);
		pane.getChildren().add(version);
		pane.getChildren().add(Places);
		pane.getChildren().add(Tours);
		
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
	 * Nset layout of city name desc. (not to show the labels when ot the city or its description is selected )
	 */
	public void nset_layout_of_city_name_desc() {
		get_tours_total().setText("");
		get_places_total().setText("");
	}
	
	/**
	 * Gets the tours total.
	 *
	 * @return the tours total
	 */
	public Labeled get_tours_total() {
		return Tours;
	}
	
	/**
	 * Gets the places total.
	 *
	 * @return the places total
	 */
	public Labeled get_places_total() {
		return Places;
	}


}
