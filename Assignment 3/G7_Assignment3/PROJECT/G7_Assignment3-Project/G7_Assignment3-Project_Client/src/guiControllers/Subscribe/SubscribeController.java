package guiControllers.Subscribe;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import application.GuiFormatCreator;
import guiControllers.HomeController;
import guiControllers.Catalog.CitiesController;
import guiControllers.Messages.notification;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * The Class SubscribeController.
 */
public class SubscribeController {

	/** The instace. */
	public static SubscribeController instace;
	
	/** The city. */
	public static entities.City city;

	
	/** The combobox rates. */
	@FXML
	private ComboBox<Integer> combobox_rates;

	/** The subscribe rates. */
	private ArrayList<entities.Rate> subscribe_rates = new ArrayList<entities.Rate>();
	
	/** The btn cancel. */
	@FXML
	private Button btn_cancel;
	
	/** The totalsubscribe. */
	@FXML
	private Label totalsubscribe;

	/** The totalonetime. */
	@FXML
	private Label totalonetime;

	/** The onetime. */
	private double onetime = 0;
	
	
	/** The subscribe. */
	private double subscribe = 0;

    /** The txt pass para. */
    @FXML
    private PasswordField txt_pass_para;

    /** The txt pass download. */
    @FXML
    private PasswordField txt_pass_download;
    
    
	/** The selected rate sub. */
	private entities.Rate selected_rate_sub = null;
	
	/** The onetime rate. */
	private entities.Rate onetime_rate = null;


    /** The label card 2. */
    @FXML
    private Label label_card2;

    /** The label card. */
    @FXML
    private Label label_card;
    
    /** The expdate. */
    private String expdate;

	/**
	 * Initialize.
	 */
	@FXML
	void initialize() {
		SubscribeController.instace = this;
		client.requestHandler.Purchase.getAllRates();
		client.requestHandler.Purchase.Get_payment(HomeController.instance.user);
		txt_pass_para.setTextFormatter(GuiFormatCreator.txtMaxField(32));
		txt_pass_download.setTextFormatter(GuiFormatCreator.txtMaxField(32));

	}
	
	/**
	 * Gets the payment.
	 *
	 * @param mycard the mycard
	 * @return the payment
	 */
	public void get_payment(entities.CreditCard mycard) {
		if(mycard == null)
			return;
		String card = Long.toString(mycard.getNumber());
		String lastFourDigits;
		if (card.length() > 4)
		{
		    lastFourDigits = card.substring(card.length() - 4);
		    label_card2.setText("**** **** **** " + lastFourDigits);
		    label_card.setText("**** **** **** " + lastFourDigits);
		}
		else
		{
			 label_card2.setText(card);
			    label_card.setText(card);
		}
		
		expdate = mycard.getExpireyDate();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate1 = LocalDate.parse(expdate, formatter);
		LocalDate localDatenow = LocalDate.now();
		if(localDate1.isBefore(localDatenow)) {
			
			Stage s = (Stage)HomeController.instance.PANE.getScene().getWindow();
			notification.show("Credit card date is expired!", "error", s);
			close_window();
		}
	}

	/**
	 * Btn purchase onetime clicked.
	 *
	 * @param event the event
	 */
	@FXML
	void btn_purchase_onetime_clicked(MouseEvent event) {
		
		if(onetime_rate == null) {
			Stage sb = (Stage) btn_cancel.getScene().getWindow();
			notification.show("There is no ONETIME purchase avaiable right now!", "error", sb);
			return;
		}	
		if(!(txt_pass_download.getText().equals(HomeController.instance.user.getPassword()))) {
			Stage sb = (Stage) btn_cancel.getScene().getWindow();
			notification.show("Password is Incorrect!", "error", sb);
			return;
		}
		entities.Purchase purchaseonetime = new entities.Purchase(HomeController.instance.user,onetime_rate, city);
		client.requestHandler.Purchase.Add_purchase(purchaseonetime);
		close_window();
		
	}
	
	


	
	/**
	 * Btn putchase subscribe clicked. subscribe click 
	 *
	 * @param event the event
	 */
	@FXML
	void btn_putchase_subscribe_clicked(MouseEvent event) {
		if(selected_rate_sub == null) {
			Stage sb = (Stage) btn_cancel.getScene().getWindow();
			notification.show("Please Select a Rate Package to Purchase!", "Red", sb);
			return;
		}	
		if(!(txt_pass_para.getText().equals(HomeController.instance.user.getPassword()))) {
			Stage sb = (Stage) btn_cancel.getScene().getWindow();
			notification.show("Password is Incorrect!", "error", sb);
			return;
		}
		
		entities.Purchase purchasesubscribe = new entities.Purchase(HomeController.instance.user,selected_rate_sub, city);
		client.requestHandler.Purchase.Add_purchase(purchasesubscribe);
		CitiesController.instance.refreshCities();
		close_window();
	}

	/**
	 * Refresh rates. get rates by response handler
	 *
	 * @param rates the rates
	 */
	public void refresh_rates(ArrayList<entities.Rate> rates) {
		for (entities.Rate ratee : rates) {

			switch (ratee.getRtype()) {
			case ONETIME:
				onetime_rate = ratee;
				onetime = ratee.getPrice();

				break;
			case SUBSCRIPTION:
				subscribe_rates.add(ratee);
				combobox_rates.getItems().add(ratee.getDays());
				break;
			}
		}
		totalonetime.setText("Total price: " + onetime + "$");
		combobox_rates.valueProperty().addListener((obs, oldItem, newItem) -> { // By clicking on a value within the
																				// combobox, the state will be drawn//
			if (newItem != null) {
				subscribe = return_cost_by_day_for_subscribtion(
						combobox_rates.getSelectionModel().getSelectedItem().intValue());
				totalsubscribe.setText("Total price: " + subscribe + "$");
			}
		});
	}

	/**
	 * Return cost by day for subscribtion.
	 *
	 * @param day the day
	 * @return the double
	 */
	private double return_cost_by_day_for_subscribtion(int day) {
		for (entities.Rate rate : subscribe_rates) {
			if (rate.getDays() == day) {
				selected_rate_sub = rate;
				return rate.getPrice();
			}
		}
		return 0;
	}

	/**
	 * Close window.
	 */
	public void close_window() {
		Stage sb = (Stage) btn_cancel.getScene().getWindow();
		sb.close();
	}

	/**
	 * Cancel click.
	 *
	 * @param event the event
	 */
	@FXML
	void cancel_click(MouseEvent event) {
		Stage sb = (Stage) btn_cancel.getScene().getWindow();
		sb.close();
	}

}
