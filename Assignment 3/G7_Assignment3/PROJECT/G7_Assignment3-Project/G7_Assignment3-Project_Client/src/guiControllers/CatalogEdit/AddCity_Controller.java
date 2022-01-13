package guiControllers.CatalogEdit;

import application.GuiFormatCreator;
import guiControllers.GeneralValues;
import guiControllers.Messages.notification;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

// TODO: Auto-generated Javadoc
/**
 * The Class AddCity_Controller.
 */
public class AddCity_Controller {


	/** The instance. */
	public static AddCity_Controller instance;
    
    /** The btn cancel. */
    @FXML
    private Button btn_cancel;

    /** The btn add. */
    @FXML
    private Button btn_add;

    /** The txt cityname. */
    @FXML
    private TextField txt_cityname;
    
    /** The txt desc. */
    @FXML
    private TextField txt_desc;

    /**
     * Btn add clicked, call the request handler to add a new city.
     *
     * @param event the event
     */
    @FXML
    void btn_add_clicked(MouseEvent event) {
    	if(getTxt_cityname().getText().isEmpty() || getTxt_desc().getText().isEmpty()) {
    		Stage s = (Stage)getBtn_add().getScene().getWindow();
    		notification.show("Please Fill Fields!", "error", s);
    		return;
    	}
    	entities.Version ver = new entities.Version(0, 0);
    	entities.City city = new entities.City(GeneralValues.COUNTRY, getTxt_cityname().getText(),getTxt_desc().getText(), ver);
    	client.requestHandler.Catalog.AddCity(city);
    }


    /**
     * Initialize.
     */
    @FXML
    void initialize() {
    	AddCity_Controller.instance = this;
    	getTxt_cityname().setTextFormatter(GuiFormatCreator.txtMaxField(50));
    	getTxt_desc().setTextFormatter(GuiFormatCreator.txtMaxField(300));
    	
    }
    
    /**
     * Btn cancel clicked. exit from current window
     *
     * @param event the event
     */
    @FXML
    void btn_cancel_clicked(MouseEvent event) {
    	close_window();
    }
    
    /**
     * Close window.exit from current window
     */
    public void close_window() {
    	Stage s = (Stage)getBtn_add().getScene().getWindow();
		s.close();
    }


	/**
	 * Gets the btn add.
	 *
	 * @return the btn add
	 */
	private Button getBtn_add() {
		return btn_add;
	}


	/**
	 * Gets the txt cityname.
	 *
	 * @return the txt cityname
	 */
	private TextField getTxt_cityname() {
		return txt_cityname;
	}

	
	/**
	 * Gets the txt desc.
	 *
	 * @return the txt desc
	 */
	private TextField getTxt_desc() {
		return txt_desc;
	}

    

}
