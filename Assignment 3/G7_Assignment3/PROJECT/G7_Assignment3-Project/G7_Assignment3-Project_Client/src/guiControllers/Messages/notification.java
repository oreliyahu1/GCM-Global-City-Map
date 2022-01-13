package guiControllers.Messages;

import animatefx.FadeOutUp;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;


/**
 * The Class notification. to show a error or success notification in the middle of the window
 */
    public class notification {

        /** The toast timeout. */
        private static int TOAST_TIMEOUT = 1200;
        
        /** The label. */
        static Label label;
        
        /** The locked. */
        private static boolean locked = false;
        
        /**
         * Creates the popup.
         *
         * @param message the message
         * @param type the type
         * @return the popup
         */
        //type = error,success
        private static Popup createPopup(final String message, String type) {
            final Popup popup = new Popup();
            popup.setAutoFix(true);
            label = new Label(message);
            if(type.equals("error")) {
            	label.setStyle("-fx-background-color: cornsilk;" + 
                		"-fx-padding: 10;" + 
                		"-fx-border-color: red;" + 
                		"-fx-border-width: 0.8;" + 
                		"-fx-font-size: 16;-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
            }else {
            	label.setStyle("-fx-background-color: cornsilk;" + 
                		"-fx-padding: 10;" + 
                		"-fx-border-color: black;" + 
                		"-fx-border-width: 0.3;" + 
                		"-fx-font-size: 16;-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
            }
            
            popup.getContent().add(label);
            return popup;
        }

        /**
         * Show.
         *
         * @param message the message
         * @param type the type
         * @param stage the stage
         */
        public static void show(final String message, String type, Stage stage) {
        	 if(locked == true)
             	return;
            //Stage stage = (Stage) HomeController.instance.PANE.getScene().getWindow();
            Popup popup = createPopup(message,type);
            locked = true;
            popup.setOnShown(e -> {
                popup.setX(stage.getX() + stage.getWidth() / 1.68 - popup.getWidth() / 2);
                popup.setY(stage.getY() + stage.getHeight() / 1.2 - popup.getHeight() / 2);
            });
            popup.show(stage);
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(TOAST_TIMEOUT), event -> {}));
            timeline.play();
			timeline.setOnFinished(event1 -> {
				FadeOutUp anim = new FadeOutUp(label);
				anim.play();
				anim.setOnFinished(event2 -> {
					popup.hide();
					locked = false;
				});
			});
            
   
        }
        
    }