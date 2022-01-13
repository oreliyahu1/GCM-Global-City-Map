package guiControllers.CatalogEdit;


import java.util.ArrayList;
import java.util.Optional;
import application.GuiFormatCreator;
import entities.Category;
import guiControllers.GeneralValues;
import guiControllers.Messages.notification;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

// TODO: Auto-generated Javadoc
/**
 * The Class Add_Edit_Place_Controller.
 */
public class Add_Edit_Place_Controller {

	/** The instance. */
	public static Add_Edit_Place_Controller instance;
	
	/** The add edit mode. */
	public static boolean add_edit_mode;   //true(add) , false(edit)
	
	/** The selected poi. */
	public static entities.PlaceOfInterest selected_poi;
    
    /** The label. */
    @FXML
    private Label label;

	
    /** The btn cancel. */
    @FXML
    private Button btn_cancel;

    /** The btn add. */
    @FXML
    private Button btn_add;

    /** The label country. */
    @FXML
    public Label label_country;

    /** The label city. */
    @FXML
    public Label label_city;

    /** The txt name. */
    @FXML
    public TextField txt_name;


    /** The combobox catgeory. */
    @FXML
    private ComboBox<String> combobox_catgeory;

    /** The combobox catgeorymanage. */
    @FXML
    private ComboBox<String> combobox_catgeorymanage;

    /** The txt desc. */
    @FXML
    public TextField txt_desc;


    /** The checkbox access. */
    @FXML
    public CheckBox checkbox_access;

    /**
     * Initialize.
     */
    @FXML
    void initialize() {
    	Add_Edit_Place_Controller.instance = this;
    	client.requestHandler.Catalog.getAllCategories();
    	
    	if(Add_Edit_Place_Controller.add_edit_mode) {
    		getLabel().setText("Add Place of Interest");
    		getBtn_add().setText("Add Place");
    		
    	}else {
    		getLabel().setText("Edit Place of Interest");
    		getBtn_add().setText("Confirm changes");
    		if(selected_poi.getCategory() != null)
    			getCombobox_catgeory().getSelectionModel().select(selected_poi.getCategory().getCategory().toString());
    		txt_desc.setText(selected_poi.getDescription());
    	}
    	txt_desc.setTextFormatter(GuiFormatCreator.txtMaxField(300));
    	txt_name.setTextFormatter(GuiFormatCreator.txtMaxField(50));
    }
    
    
    /**
     * Btn add place click, when clicked palces will be added to the selected city.
     *
     * @param event the event
     */
    @FXML
    void btn_add_place_click(MouseEvent event) {
    	if(txt_name.getText().isEmpty() || txt_desc.getText().isEmpty()) {
    		Stage s = (Stage)getBtn_add().getScene().getWindow();
    		notification.show("Please Fill the empty Fields!", "error", s);
    		return;
    	}
    	if(getCombobox_catgeory().getSelectionModel().getSelectedItem() == null) {
    		Stage s = (Stage)getBtn_add().getScene().getWindow();
    		notification.show("Please Select a Category!", "error", s);
    		return;
    	}
    	if(getBtn_add().getText().equals("Add Place")) {
    		entities.Category category = new Category(null);
        	if(getCombobox_catgeory().getSelectionModel().getSelectedItem() != null)
        		category.setCategory(getCombobox_catgeory().getSelectionModel().getSelectedItem().toString());
        	
        	entities.PlaceOfInterest place = new entities.PlaceOfInterest(GeneralValues.CITY, 
        			txt_name.getText(), txt_desc.getText(), checkbox_access.isSelected(),category);
        	client.requestHandler.Catalog.AddPOICity(place);
    	}else {
    		
    		entities.Category category = new Category(null);
        	if(getCombobox_catgeory().getSelectionModel().getSelectedItem() != null)
        		category.setCategory(getCombobox_catgeory().getSelectionModel().getSelectedItem().toString());
        	
        	entities.PlaceOfInterest place = new entities.PlaceOfInterest(GeneralValues.CITY, 
        			txt_name.getText(), txt_desc.getText(), checkbox_access.isSelected(),category);
        	client.requestHandler.Catalog.UpdatePOICity(selected_poi, place);
        	selected_poi = place;
    		
    	}

    }

    /**
     * Btn add cat click. when clicked category will be added
     *
     * @param event the event
     */
    @FXML
    void btn_add_cat_click(MouseEvent event) {
    	TextInputDialog dialog = new TextInputDialog("");
    	 
    	dialog.setTitle("Add Category");
    	dialog.setHeaderText("Category name:");
    	dialog.setContentText("");
    	 
    	Optional<String> result = dialog.showAndWait();
    	 
    	result.ifPresent(name -> {
    	    client.requestHandler.Catalog.add_category(new entities.Category(name));
    	});
    }


    /**
     * Btn deletecat click.
     *
     * @param event the event
     */
    @FXML
    void btn_deletecat_click(MouseEvent event) {
    	if(getCombobox_catgeorymanage().getSelectionModel().getSelectedItem() == null) {
    		Stage s = (Stage)getBtn_add().getScene().getWindow();
    		notification.show("Please Select a category to Delete!", "error", s);
    		return;
    	}
    	Alert alert = new Alert(AlertType.WARNING, "Are you sure to Delete " + getCombobox_catgeorymanage().getSelectionModel().getSelectedItem().toString() + " Category?",
				ButtonType.YES, ButtonType.NO);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.YES) {
			client.requestHandler.Catalog.delete_category(new entities.Category(combobox_catgeorymanage.getSelectionModel().getSelectedItem().toString()));
		    
		}	
    	
    }

    /**
     * Btn updatecat clicked.
     *
     * @param event the event
     */
    @FXML
    void btn_updatecat_clicked(MouseEvent event) {
    	if(getCombobox_catgeorymanage().getSelectionModel().getSelectedItem() == null) {
    		Stage s = (Stage)getBtn_add().getScene().getWindow();
    		notification.show("Please Select a category to Update!", "error", s);
    		return;
    	}
    	TextInputDialog dialog = new TextInputDialog(getCombobox_catgeorymanage().getSelectionModel().getSelectedItem().toString());
   	 
    	dialog.setTitle("Update Category");
    	dialog.setHeaderText("Old Categoy name:" );
    	dialog.setContentText("");
    	 
    	Optional<String> result = dialog.showAndWait();
    	 
    	result.ifPresent(name -> {
    	    client.requestHandler.Catalog.update_category(new entities.Category(getCombobox_catgeorymanage().getSelectionModel().getSelectedItem().toString()),
    	    		new entities.Category(name));
    	});
    }
    
    /**
     * Btn cancel click.
     *
     * @param event the event
     */
    @FXML
    void btn_cancel_click(MouseEvent event) {
    	Stage s = (Stage)getBtn_add().getScene().getWindow();
		s.close();
    }
    
    /**
     * Close window.
     */
    public void close_window() {
    	Stage s = (Stage)getBtn_add().getScene().getWindow();
		s.close();
    }
	
	/**
	 * Update categories.
	 *
	 * @param cat the cat
	 */
	public void update_categories(ArrayList<entities.Category> cat) {
		for(entities.Category category : cat) {
			getCombobox_catgeory().getItems().add(category.getCategory());
			getCombobox_catgeorymanage().getItems().add(category.getCategory());
			
		}
	}


	/**
	 * Gets the label.
	 *
	 * @return the label
	 */
	private Label getLabel() {
		return label;
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
	 * Gets the combobox catgeory.
	 *
	 * @return the combobox catgeory
	 */
	private ComboBox<String> getCombobox_catgeory() {
		return combobox_catgeory;
	}


	/**
	 * Gets the combobox catgeorymanage.
	 *
	 * @return the combobox catgeorymanage
	 */
	private ComboBox<String> getCombobox_catgeorymanage() {
		return combobox_catgeorymanage;
	}



	
	
	
}
