package guiControllers.Registeration;

import java.io.IOException;
import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import application.GuiFormatCreator;
import entities.CreditCard;
import guiControllers.FxmlView;
import guiControllers.HomeController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * The Class PaymentController.
 */
public class PaymentController {
	
	/** The instance. */
	public static PaymentController instance;
	
	/** The new user. */
	public entities.User newUser;

	/** The txt cardnum. */
	@FXML
	private TextField txt_cardnum;

	/** The txt cvv. */
	@FXML
	private TextField txt_cvv;

	/** The txt date. */
	@FXML
	private TextField txt_date;

	/** The client title. */
	@FXML
	public Label client_title;


    
	 /** The combo month. */
 	@FXML
	    private ComboBox<String> combo_month;

	    /** The combo year. */
    	@FXML
	    private ComboBox<String> combo_year;

	/**
	 * Initialize.
	 */
	@FXML
	private void initialize() {
		PaymentController.instance = this;
		 
	
		int yearnow = Calendar.getInstance().get(Calendar.YEAR);
		 txt_cardnum.setTextFormatter(GuiFormatCreator.txtNumberField(16));
		 txt_cvv.setTextFormatter(GuiFormatCreator.txtNumberField(3));
		 ObservableList<String> year = FXCollections.observableArrayList();
		 ObservableList<String> month = FXCollections.observableArrayList();
		 for(int i=0 ; i<12 ; i++) {
			 year.add( "" + (yearnow + i));
			 month.add("" + (i+1));
		 }
		 combo_year.setItems(year);
		 combo_month.setItems(month);
		 
		
	}


	
	/**
	 * Start.
	 *
	 * @param pane the pane
	 * @throws Exception the exception
	 */
	public void start(Pane pane) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource(FxmlView.PAYMENT.getFXML()));
		HomeController.instance.Set_Title(FxmlView.PAYMENT.getTitle());
		Node n = null;
		try {
			n = fxmlLoader.load();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		pane.getChildren().clear();
		pane.getChildren().add(n);
	}
	
	/**
	 * Btn signup. check required fileds and register an client account
	 *
	 * @param event the event
	 */
	@FXML
	void btn_signup(MouseEvent event) {

		boolean continue_and_register = true;
		if(txt_cardnum.getText().isEmpty()) {
			txt_cardnum.getParent().setStyle("-fx-border-color:red;");
    		continue_and_register = false;
    	}else
    	{
    		txt_cardnum.getParent().setStyle("-fx-border-color:green;");
    	}
		if(txt_cvv.getText().isEmpty()) {
			txt_cvv.getParent().setStyle("-fx-border-color:red;");
    		continue_and_register = false;
    	}else
    	{
    		txt_cvv.getParent().setStyle("-fx-border-color:green;");
    	}

		if (combo_year.getSelectionModel().getSelectedItem() == null) {
			combo_year.setStyle("-fx-border-color:red;");
			continue_and_register = false;
		} else {
			combo_year.setStyle("-fx-border-color:green;");
		}
		if (combo_month.getSelectionModel().getSelectedItem() == null) {
			combo_month.setStyle("-fx-border-color:red;");
			continue_and_register = false;
		} else {
			combo_month.setStyle("-fx-border-color:green;");
		}
    	if(!continue_and_register)
    		return;
    	
    	String date;
    	if(combo_month.getSelectionModel().getSelectedItem().equals("10") || combo_month.getSelectionModel().getSelectedItem().equals("11")
    		|| combo_month.getSelectionModel().getSelectedItem().equals("12")){
    			date = combo_year.getSelectionModel().getSelectedItem() + "-" + combo_month.getSelectionModel().getSelectedItem() + "-01";
    		}else {
    			date = combo_year.getSelectionModel().getSelectedItem() + "-0" + combo_month.getSelectionModel().getSelectedItem() + "-01";
    		}
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    	LocalDate localDate = LocalDate.parse(date, formatter);
    	
    	long num = Long.parseLong(txt_cardnum.getText());
    	
		entities.CreditCard newCredit = new CreditCard(num, Integer.parseInt(txt_cvv.getText()), localDate);
		client.requestHandler.Home.register(RegisterController.instance.get_user(), newCredit);
	}
}
