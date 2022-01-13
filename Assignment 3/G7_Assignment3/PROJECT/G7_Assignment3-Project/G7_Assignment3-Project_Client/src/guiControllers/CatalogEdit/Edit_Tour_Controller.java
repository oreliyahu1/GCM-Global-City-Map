package guiControllers.CatalogEdit;

import application.GuiFormatCreator;
import guiControllers.Messages.notification;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


// TODO: Auto-generated Javadoc
/**
 * The Class Edit_Tour_Controller.
 */
public class Edit_Tour_Controller {


	/** The instance. */
	public static Edit_Tour_Controller instance;
	
	/** The tour. */
	public static entities.Tour tour;

    /** The txt name. */
    @FXML
    private TextField txt_name;


    /** The txt desc. */
    @FXML
	private
     TextField txt_desc;



    /** The checkbox recommended. */
    @FXML
    private CheckBox checkbox_recommended;

    /** The btn cancel. */
    @FXML
    private Button btn_cancel;

    /** The btn edit. */
    @FXML
    private Button btn_edit;

    /**
     * Btn edit tour click.
     *
     * @param event the event
     */
    @FXML
    void btn_edit_tour_click(MouseEvent event) {
    	  boolean recommended = false;
    	  if(getCheckbox_recommended().isSelected() == true)
    		 recommended = true;
    	  if(getTxt_desc().getText().isEmpty()) {
    		  Stage s = (Stage)getBtn_edit().getScene().getWindow();
    		  notification.show("Please Fill a description!", "error", s);
    		  return;
    	  }
    	  if(getTxt_name().getText().isEmpty()) {
    		  Stage s = (Stage)getBtn_edit().getScene().getWindow();
    		  notification.show("Please Fill a name!", "error", s);
    		  return;
    	  }
    	  entities.Tour newtour = new entities.Tour(tour.getCity(),getTxt_name().getText(), recommended, getTxt_desc().getText());
  		client.requestHandler.Catalog.UpdateTours_soft(tour, newtour);
    		 
    }

    /**
     * Btn cancel click.
     *
     * @param event the event
     */
    @FXML
    void btn_cancel_click(MouseEvent event) {
    	Stage s = (Stage)getBtn_edit().getScene().getWindow();
		s.close();
    }

    /**
     * Initialize.
     */
    @FXML
    void initialize() {
    	Edit_Tour_Controller.instance = this;
    	getTxt_desc().setTextFormatter(GuiFormatCreator.txtMaxField(300));
    	getTxt_name().setTextFormatter(GuiFormatCreator.txtMaxField(50));
    	
    	getTxt_name().setText(tour.getName());
    	getTxt_desc().setText(tour.getDescription());
    	if(tour.isRecommended())
    		getCheckbox_recommended().setSelected(true);
    	else
    		getCheckbox_recommended().setSelected(false);
    }
    
    /**
     * Close window.
     */
    public void close_window() {
    	Stage s = (Stage)getBtn_edit().getScene().getWindow();
		s.close();
    }

	
	/**
	 * Gets the txt name.
	 *
	 * @return the txt name
	 */
	private TextField getTxt_name() {
		return txt_name;
	}

	
	/**
	 * Gets the txt desc.
	 *
	 * @return the txt desc
	 */
	private TextField getTxt_desc() {
		return txt_desc;
	}



	/**
	 * Gets the checkbox recommended.
	 *
	 * @return the checkbox recommended
	 */
	private CheckBox getCheckbox_recommended() {
		return checkbox_recommended;
	}

	

	/**
	 * Gets the btn cancel.
	 *
	 * @return the btn cancel
	 */
	

	/**
	 * Gets the btn edit.
	 *
	 * @return the btn edit
	 */
	private Button getBtn_edit() {
		return btn_edit;
	}


}
