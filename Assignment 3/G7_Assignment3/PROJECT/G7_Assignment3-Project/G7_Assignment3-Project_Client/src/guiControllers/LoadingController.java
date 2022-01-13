package guiControllers;

import application.ClientMain;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.Window;
/**
 * The Class LoadingController.
 */
public class LoadingController {

	/** The instance - to be used by show and hide function outside the controller */
	public static LoadingController instance;
    
    /** The my stage. */
    private Stage myStage;
    
    /** The label message. */
    @FXML
    private Label label_message;

    /** The label exit. */
    @FXML
    private Label label_exit;

    /**
     * Label click exit.
     *
     * @param event the event
     */
    @FXML
    void label_click_exit(MouseEvent event) {
    	ClientMain.safeExit();
    }
    
    /**
     * Sets the text.
     *
     * @param message the new text
     */
    private void setText(String message) {
    	label_message.setText(message);
    	label_message.setVisible(true);
    	label_exit.setVisible(true);
    }
    
    /**
     * Delete text.
     */
    private void deleteText() {
    	label_message.setText("");
    	label_message.setVisible(false);
    	label_exit.setVisible(false);
    }
    
    /**
     * Sets the stage.
     *
     * @param stage the new stage
     */
    public void setStage(Stage stage) {
    	this.myStage=stage;
    }
    
	/**
	 * Initialize.
	 */
	@FXML
    private void initialize() {
		LoadingController.instance=this;
	}
    
	/**
	 * Sets the XY.
	 * Calculation is done on screen resolution
	 */
	private void SetXY() {
		myStage.setX((Screen.getPrimary().getBounds().getWidth() / 2) - (myStage.getScene().getWindow().getWidth() / 2));
		myStage.setY((Screen.getPrimary().getBounds().getHeight() / 2) - (myStage.getScene().getWindow().getHeight() / 2));
	}
	
	/**
	 * Sets the XY.
	 * Calculation is done by the window that has called the loading
	 * @param win the window of called by window
	 */
	private void SetXY(Window win) {
		myStage.setX(win.getX() + (win.getWidth() / 2) - (myStage.getScene().getWindow().getWidth() /2));
		myStage.setY(win.getY() + (win.getHeight() / 2) - (myStage.getScene().getWindow().getHeight() /2));
	}
	
	/**
	 * Show loading window 
	 * 
	 * @param win the window
	 * @param message the message to show
	 */
	public void show(Window win, String message) {
		SetXY(win);
		setText(message);
		myStage.show();
	}
	
	/**
	 * Show.
	 *
	 * @param win the win
	 */
	public void show(Window win) {
		SetXY(win);
		deleteText();
		myStage.show();
	}
	
	/**
	 * Show.
	 */
	public void show() {	
		SetXY();
		deleteText();
		myStage.show();
	}
	
	/**
	 * Show.
	 *
	 * @param message the message
	 */
	public void show(String message) {	
		SetXY();
		setText(message);
		myStage.show();
	}
	
	/**
	 * Hide.
	 */
	public void hide() {
		myStage.hide();
	}
	
}
