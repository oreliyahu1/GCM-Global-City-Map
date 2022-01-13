package guiControllers.item;


import javafx.event.EventHandler;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * The Class PlaceOnMap.
 */
public class PlaceOnMap extends ImageView {

	/** The description. */
	String description;
	
	/** The Catalog. */
	String Catalog;
	
	/** The name. */
	String name;
	
	/** The y. */
	public double x,y;
	
	/** The place. */
	private entities.PlaceOfInterestMap place;
	
	/**
	 * Instantiates a new place on map.
	 *
	 * @param place the place
	 */
	public PlaceOnMap(entities.PlaceOfInterestMap place) {
		this.name = place.getName();
		if(place.getCategory() == null)
			this.Catalog = "";
		else
			this.Catalog = place.getCategory().getCategory();
		
		this.place = place;
		
		
		description = place.getDescription();
	    x = place.getCordinates().getX()-25;
	    y = place.getCordinates().getY()-40;
	    setTranslateX(x);
	    setTranslateY(y);
	    
		setFitHeight(31.0);
		setFitWidth(25.0);
		setLayoutX(14.0);
		setLayoutY(22.0);
		setPickOnBounds(true);
		setPreserveRatio(true);
		setSmooth(true);
		Image image = new Image(getClass().getResourceAsStream("/image/icons8-marker-96.png"));
		setImage(image);
		
		  Tooltip tooltip = new Tooltip(name + "\n" + Catalog + "\nCoordinate: (" + (x+25) + "," + (y+40) + ")\n" + description);
		    this.setPickOnBounds(true);
		    Tooltip.install(this, tooltip);

	    
		addEventFilter( MouseEvent.MOUSE_PRESSED, getOnMousePressedEventHandler());

	}
	
	/**
	 * Gets the poi.
	 *
	 * @return the poi
	 */
	public entities.PlaceOfInterestMap getpoi(){
		return place;
	}
	  
  	/**
  	 * Gets the on mouse pressed event handler.
  	 *
  	 * @return the on mouse pressed event handler
  	 */
  	public EventHandler<MouseEvent> getOnMousePressedEventHandler() {
	        return onMousePressedEventHandler;
	    }

	    /** The on mouse pressed event handler. */
    	private EventHandler<MouseEvent> onMousePressedEventHandler = new EventHandler<MouseEvent>() {

	        public void handle(MouseEvent event) {
	        	/*Alert alert = new Alert(AlertType.NONE, "Close", ButtonType.OK);
	        	alert.setTitle(name + " - " + Catalog);
	        	alert.setContentText(Address + "\n" + description);
	        	alert.showAndWait();
*/

	        }

	    };
}
