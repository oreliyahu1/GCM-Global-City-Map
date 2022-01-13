package guiControllers.Window;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import java.util.ArrayList;

import application.GuiFormatCreator;
import entities.City;
import guiControllers.LoadingController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

/**
 * The Class CityController.
 * Controller city 
 */
public class CityController {

	/** The instance. - used for external updating from response handler */
	public static CityController instance;
    
    /** The cities names. - are presented in the listview_cities */
    private ObservableList<String> citiesNames;
   
    /** The listview cities. */
    @FXML
    private ListView<String> listview_cities;

    /** The btn connect. */
    @FXML
    private Button btn_connect;
    
    /** The txtf cityname. */
    @FXML
    private TextField txtf_cityname;

    /** The txtf numberofmaps. */
    @FXML
    private TextField txtf_numberofmaps;

    /** The txtf numberofpoi. */
    @FXML
    private TextField txtf_numberofpoi;

    /** The txtf numberoftours. */
    @FXML
    private TextField txtf_numberoftours;

    /** The txtf versionnumber. */
    @FXML
    private TextField txtf_versionnumber;

    /** The btn edit. */
    @FXML
    private Button btn_edit;

    /** The btn add. */
    @FXML
    private Button btn_add;

    /** The btn delete. */
    @FXML
    private Button btn_delete;
    
    /** The btn submit. */
    @FXML
    private Button btn_submit;  

	/**
	 * Initialize.
	 */
	@FXML
    private void initialize() {
    	CityController.instance=this;
    	citiesNames = FXCollections.observableArrayList();
    	listview_cities.setItems(citiesNames);
    	listview_cities.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> ov, String old_val, String new_val) {
            	if(new_val != null) client.requestHandler.City.getCity(new_val); 	
            } }); // Event that is triggered by clicking on another value 
    	txtf_numberofmaps.setTextFormatter(GuiFormatCreator.onlyNumber());
    	txtf_numberofpoi.setTextFormatter(GuiFormatCreator.onlyNumber());
    	txtf_numberoftours.setTextFormatter(GuiFormatCreator.onlyNumber());
    	txtf_versionnumber.setTextFormatter(GuiFormatCreator.onlyNumbersAndOneDot());
    	LoadingController.instance.show();
    	client.requestHandler.City.getAllCityNames();
    	btn_click_add(null);
    }
    
    /**
     * Btn click submit.
     *
     * @param event the event
     */
    @FXML
    private void btn_click_submit(ActionEvent event) {
    	if(GuiFormatCreator.checkEmpty(txtf_cityname.getText(), txtf_numberofmaps.getText(), txtf_numberofpoi.getText(), txtf_numberoftours.getText(), txtf_versionnumber.getText())) {
    		Alert alert = new Alert(AlertType.ERROR, "One of the fields is empty", ButtonType.OK);
    		alert.showAndWait();
    		return;
    	}
    	if(!GuiFormatCreator.checkVersion(txtf_versionnumber.getText())) {
    		Alert alert = new Alert(AlertType.ERROR, "Version should be valid (like x.x)", ButtonType.OK);
    		alert.showAndWait();
    		return;
    	}
    	LoadingController.instance.show(btn_add.getScene().getWindow());
    	switch(btn_submit.getText()) {
    	case "Add":
        	client.requestHandler.City.addCity(new entities.City(
        	    	txtf_cityname.getText(),
        	    	Integer.parseInt(txtf_numberofmaps.getText()),
        	    	Integer.parseInt(txtf_numberofpoi.getText()),
        	    	Integer.parseInt(txtf_numberoftours.getText()),
        	    	entities.Version.fromString(txtf_versionnumber.getText())));
    		break;
    	case "Delete":
        	if(!txtf_cityname.getText().isEmpty()) {
        		client.requestHandler.City.deleteCity(txtf_cityname.getText());
        	} else {
        		LoadingController.instance.hide();
        		Alert alert = new Alert(AlertType.ERROR, "You need select city", ButtonType.OK);
        		alert.showAndWait();
        	}
    		break;
    	case "Edit":
    		if(listview_cities.getSelectionModel().getSelectedItem() != null) {
    	    	client.requestHandler.City.editCity(listview_cities.getSelectionModel().getSelectedItem(),new entities.City(
    	    	    	txtf_cityname.getText(),
    	    	    	Integer.parseInt(txtf_numberofmaps.getText()),
    	    	    	Integer.parseInt(txtf_numberofpoi.getText()),
    	    	    	Integer.parseInt(txtf_numberoftours.getText()),
    	    	    	entities.Version.fromString(txtf_versionnumber.getText())));
    		} else {
    			LoadingController.instance.hide();
    	    	Alert alert = new Alert(AlertType.ERROR, "You need select city", ButtonType.OK);
    	    	alert.showAndWait();
    		}
    		break;
    	default:
    		LoadingController.instance.hide();
    		Alert alert = new Alert(AlertType.ERROR, "You need select action", ButtonType.OK);
    		alert.showAndWait();
    		break;
    	}
    }
    
    /**
     * Btn click add.
     *
     * @param event the event
     */
    @FXML
    private void btn_click_add(ActionEvent event) {
    	btn_submit.setText(btn_add.getText());
    	btn_add.setStyle("-fx-background-color: red;");
    	btn_delete.setStyle(btn_submit.getStyle());
    	btn_edit.setStyle(btn_submit.getStyle());
    	setAllEditable(true);
    }
    
    /**
     * Btn click delete.
     *
     * @param event the event
     */
    @FXML
    private void btn_click_delete(ActionEvent event) {
    	btn_submit.setText(btn_delete.getText());
    	btn_delete.setStyle("-fx-background-color: red;");
    	btn_add.setStyle(btn_submit.getStyle());
    	btn_edit.setStyle(btn_submit.getStyle());
    	setAllEditable(false);
    }
    
    /**
     * Btn click edit.
     *
     * @param event the event
     */
    @FXML
    private void btn_click_edit(ActionEvent event) {
    	btn_submit.setText(btn_edit.getText());
    	btn_edit.setStyle("-fx-background-color: red;");
    	btn_add.setStyle(btn_submit.getStyle());
    	btn_delete.setStyle(btn_submit.getStyle());
    	setAllEditable(true);
    }
    
    /**
     * Sets the all editable.
     * This function is called when a button is changed and get details of city if it has selected value
     * @param editable the new all editable
     */
    private void setAllEditable(boolean editable) {
    	txtf_cityname.setEditable(editable);
    	txtf_numberofmaps.setEditable(editable);
    	txtf_numberofpoi.setEditable(editable);
    	txtf_numberoftours.setEditable(editable);
    	txtf_versionnumber.setEditable(editable);
    	if(listview_cities.getSelectionModel().getSelectedItem() != null) { 
    		client.requestHandler.City.getCity(listview_cities.getSelectionModel().getSelectedItem()); 
    	} else {
	    	txtf_cityname.setText("");
	    	txtf_numberofmaps.setText("");
	    	txtf_numberofpoi.setText("");
	    	txtf_numberoftours.setText("");
	    	txtf_versionnumber.setText("");
    	}
    }
	
/////////////////////// functions for the response handler
	/**
	 * Response - Set cities names
	 * Set the list view value's that were received 
	 * @param res_citiesNames the res cities names
	 */
    public void response_setCitiesNames(ArrayList<String> res_citiesNames) {
    	String selectName = listview_cities.getSelectionModel().getSelectedItem();
	 	citiesNames.clear();
	 	for(String name : res_citiesNames)
	 		citiesNames.add(name);
	 	if(citiesNames.isEmpty()) {
	    	txtf_cityname.setText("");
	    	txtf_numberofmaps.setText("");
	    	txtf_numberofpoi.setText("");
	    	txtf_numberoftours.setText("");
	    	txtf_versionnumber.setText("");
	 	}else {
	 		if(citiesNames.contains(txtf_cityname.getText()))
	 			listview_cities.getSelectionModel().select(txtf_cityname.getText());
	 		else if(citiesNames.contains(selectName))
	 			listview_cities.getSelectionModel().select(selectName);
	 		else
	 			listview_cities.getSelectionModel().select(0);
	 	}
    }
    
    /**
     * Response - Set city
     * Set city fields that were received
     * @param show the show
     */
    public void response_setCity(City show) {
    	txtf_cityname.setText(show.getName());
    	txtf_numberofmaps.setText(String.valueOf(show.getNumberOfMaps()));
    	txtf_numberofpoi.setText(String.valueOf(show.getNumberOfPOI()));
    	txtf_numberoftours.setText(String.valueOf(show.getNumberOfTours()));
    	txtf_versionnumber.setText(show.getVersion().toString());
    }
    
}
