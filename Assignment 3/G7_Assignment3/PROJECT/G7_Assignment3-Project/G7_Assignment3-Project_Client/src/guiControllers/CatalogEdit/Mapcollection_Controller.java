package guiControllers.CatalogEdit;

import entities.Version;
import guiControllers.GeneralValues;
import guiControllers.HomeController;
import guiControllers.Messages.notification;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


/**
 * The Class Mapcollection_Controller.
 */
public class Mapcollection_Controller {

	
/** The instance. */
public static Mapcollection_Controller instance;
    
    /** The btn cancel. */
    @FXML
    private Button btn_cancel;

    /** The btn push. */
    @FXML
    private Button btn_push;

    /** The label country. */
    @FXML
    private Label label_country;

    /** The label city. */
    @FXML
    private Label label_city;

    /** The label version. */
    @FXML
    private Label label_version;
    
    /** The txt version. */
    @FXML
    private TextField txt_version;

    /** The current ver. */
    private entities.Version currentVer;

    /**
     * Initialize.
     */
    @FXML
    void initialize() {
    	Mapcollection_Controller.instance = this;
    	getLabel_country().setText(GeneralValues.COUNTRY.getName());
    	getLabel_city().setText(GeneralValues.CITY.getName());
    	client.requestHandler.Catalog.GetLastCollectionVersion(GeneralValues.CITY.getCountry().getShortName(),
    			GeneralValues.CITY.getName());
    	currentVer = new Version(0, 0);

    
    }
    
    /**
     * Close window.
     */
    public void close_window() {
    	Stage s = (Stage)getBtn_push().getScene().getWindow();
		s.close();
    }

    /**
     * Btn cancel.
     *
     * @param event the event
     */
    @FXML
    void btn_cancel(MouseEvent event) {
    	Stage s = (Stage)getBtn_push().getScene().getWindow();
		s.close();
    }

    /**
     * Btn push click.
     *
     * @param event the event
     */
    @FXML
    void btn_push_click(MouseEvent event) {
 	
    	if(getTxt_version().getText().isEmpty()) {
    		Stage s = (Stage)getBtn_push().getScene().getWindow();
    		notification.show("Please Fill required Field!", "error", s);
    		return;
    	}
    	entities.Version user = entities.Version.fromString(getTxt_version().getText());
    	if(user.compareTo(getCurrentVer()) > 0) {
    		entities.City reqCity = new entities.City(GeneralValues.CITY.getCountry(), GeneralValues.CITY.getName(), user);
    		entities.VersionRequest request = new entities.VersionRequest(HomeController.instance.user,reqCity );
    		client.requestHandler.Catalog.PushMapsCollection(request);
    		//close_window();
    	}else {
    		Stage s = (Stage)getBtn_push().getScene().getWindow();
    		notification.show("Please type a higher version!", "error", s);
    	}
    	
    }
	
	/**
	 * Sets the version.
	 *
	 * @param c the new version
	 */
	public void setVersion(entities.City c) {
		this.getLabel_version().setText(c.getVersion().toString());
		this.setCurrentVer(c.getVersion()) ;
	}

	
	/**
	 * Gets the btn push.
	 *
	 * @return the btn push
	 */
	private Button getBtn_push() {
		return btn_push;
	}

	
	/**
	 * Gets the label country.
	 *
	 * @return the label country
	 */
	private Label getLabel_country() {
		return label_country;
	}


	/**
	 * Gets the label city.
	 *
	 * @return the label city
	 */
	private Label getLabel_city() {
		return label_city;
	}

	
	/**
	 * Gets the label version.
	 *
	 * @return the label version
	 */
	private Label getLabel_version() {
		return label_version;
	}

	

	/**
	 * Gets the txt version.
	 *
	 * @return the txt version
	 */
	private TextField getTxt_version() {
		return txt_version;
	}


	/**
	 * Gets the current ver.
	 *
	 * @return the current ver
	 */
	private entities.Version getCurrentVer() {
		return currentVer;
	}

	/**
	 * Sets the current ver.
	 *
	 * @param currentVer the new current ver
	 */
	private void setCurrentVer(entities.Version currentVer) {
		this.currentVer = currentVer;
	}
    
   


}
