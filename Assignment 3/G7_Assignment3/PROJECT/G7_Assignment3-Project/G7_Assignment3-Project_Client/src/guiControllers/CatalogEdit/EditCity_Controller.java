package guiControllers.CatalogEdit;

import application.GuiFormatCreator;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


// TODO: Auto-generated Javadoc
/**
 * The Class EditCity_Controller.
 */
public class EditCity_Controller {


	/** The city. */
	public static entities.City city;
	
	/** The instance. */
	public static EditCity_Controller instance;
    
    /** The btn cancel. */
    @FXML
    private Button btn_cancel;

    /** The btn edit. */
    @FXML
    private Button btn_Edit;


    /** The txt cityname. */
    @FXML
    private TextField txt_cityname;


    /** The txt desc. */
    @FXML
    private TextField txt_desc;



    /**
     * Btn cancel clicked.
     *
     * @param event the event
     */
    @FXML
    void btn_cancel_clicked(MouseEvent event) {
    	Stage s = (Stage)getBtn_Edit().getScene().getWindow();
		s.close();
    }
    
    /**
     * Close window.
     */
    public void close_window() {
    	Stage s = (Stage)getBtn_Edit().getScene().getWindow();
		s.close();
    }
    
    /**
     * Btn edit clicked.
     *
     * @param event the event
     */
    @FXML
    void btn_edit_clicked(MouseEvent event) {
    	entities.City newcity = new entities.City(city.getCountry(),city.getName(),txt_desc.getText(),city.getVersion());
    	client.requestHandler.Catalog.EditCity(city,newcity);
    }

    /**
     * Initialize.
     */
    @FXML
    void initialize() {
    	EditCity_Controller.instance = this;
    	getTxt_desc().setText(city.getDescription());
    	getTxt_cityname().setText(city.getName());
    	getTxt_cityname().setTextFormatter(GuiFormatCreator.txtMaxField(50));
    	getTxt_desc().setTextFormatter(GuiFormatCreator.txtMaxField(300));
 
    	
    	
    }


	/**
	 * Gets the btn edit.
	 *
	 * @return the btn edit
	 */
	private Button getBtn_Edit() {
		return btn_Edit;
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
