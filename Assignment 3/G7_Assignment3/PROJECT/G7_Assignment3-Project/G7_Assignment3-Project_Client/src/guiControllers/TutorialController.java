package guiControllers;

import java.io.IOException;

import animatefx.FadeIn;
import animatefx.FadeInLeft;
import animatefx.FadeInRight;
import animatefx.FadeOutLeft;
import animatefx.FadeOutRight;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * The Class TutorialController.
 */
public class TutorialController {

	/** The current. */
	int current = 1;
	 
 	/** The y. */
 	private double x, y;
	 
	   /** The skip button b. */
   	@FXML
	    private Button skip_button_b;
    
    /** The circle 2. */
    @FXML
    private Circle circle2;


    /** The circle 1. */
    @FXML
    private Circle circle1;

    /** The circle 3. */
    @FXML
    private Circle circle3;
    
    /** The image 1. */
    @FXML
    private ImageView image1;

    /** The image 2. */
    @FXML
    private ImageView image2;

    /** The image 3. */
    @FXML
    private ImageView image3;

    /** The next arrow button. */
    @FXML
    private ImageView next_arrow_button;
    
    /**
     * Back arrow.
     *
     * @param event the event
     */
    @FXML
    void back_arrow(MouseEvent event) {
    	switch(current) {
    	case 1:
    		
    		break;
    		
    	case 2:
    		//move to picture number 1 (image3,1 are not visible) (current = 2)
			current = 1;
			FadeOutRight anim11 = new FadeOutRight(image2);
			anim11.setResetOnFinished(true);
			anim11.play();
			anim11.setOnFinished(event1 -> {
				image2.setVisible(false);

			});
			image1.setVisible(true);
			FadeInLeft anim22 = new FadeInLeft(image1);
			anim22.setResetOnFinished(true);
			anim22.play();
			circle2.setFill(Color.valueOf("bdbbc3"));
			circle1.setFill(Color.valueOf("bdd4f8"));
    		break;
    	case 3:
    		//move to picture number 2 (image2,1 are not visible) (current = 3)
			current = 2;
			FadeOutRight anim1 = new FadeOutRight(image3);
			anim1.setResetOnFinished(true);
			anim1.play();
			anim1.setOnFinished(event1 -> {
				image3.setVisible(false);

			});
			image2.setVisible(true);
			FadeInLeft anim2 = new FadeInLeft(image2);
			anim2.setResetOnFinished(true);
			anim2.play();
			circle3.setFill(Color.valueOf("bdbbc3"));
			circle2.setFill(Color.valueOf("bdd4f8"));
    		break;
    		default:
    	}
    }

    /**
     * Next arrow.
     *
     * @param event the event
     */
    @FXML
    void next_arrow(MouseEvent event) {
    	switch(current) {
    	case 1:
    			//move to picture number 2 (image2,3 are not visible) (current = 1)
    			current = 2;
    			FadeOutLeft anim1 = new FadeOutLeft(image1);
    			anim1.setResetOnFinished(true);
    			anim1.play();
    			anim1.setOnFinished(event1 -> {
    				image1.setVisible(false);

    			});
    			image2.setVisible(true);
    			FadeInRight anim2 = new FadeInRight(image2);
    			anim2.setResetOnFinished(true);
    			anim2.play();
    			circle1.setFill(Color.valueOf("bdbbc3"));
    			circle2.setFill(Color.valueOf("bdd4f8"));
    		break;
    		
    	case 2:
    		//move to picture number 3
    		current = 3;
			FadeOutLeft anim11 = new FadeOutLeft(image2);
			anim11.setResetOnFinished(true);
			anim11.play();
			anim11.setOnFinished(event1 -> {
				image2.setVisible(false);

			});
			image3.setVisible(true);
			FadeInRight anim21 = new FadeInRight(image3);
			anim21.setResetOnFinished(true);
			anim21.play();
			circle2.setFill(Color.valueOf("bdbbc3"));
			circle3.setFill(Color.valueOf("bdd4f8"));
    		break;
    		
    	case 3:
    		//exit and go to application
    		if(skip_button_b.isDisable() == false)
    		Event.fireEvent(skip_button_b, new MouseEvent(MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, MouseButton.PRIMARY, 0, true,
    				true, true, true, true, true, true, true, true, true, null));
    		break;
    		default:
    	}
    }

    /**
     * Next button.
     *
     * @param event the event
     */
    @FXML
    void next_button(MouseEvent event) {
    	
    	Event.fireEvent(next_arrow_button, new MouseEvent(MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, MouseButton.PRIMARY, 0, true,
				true, true, true, true, true, true, true, true, true, null));
    }

    /**
     * Skip button.
     *
     * @param event the event
     */
    @FXML
    void skip_button(MouseEvent event) {
    	//((Node) event.getSource()).getScene().getWindow().close //hiding primary window
    	skip_button_b.setDisable(true);
    	Stage stage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	FadeIn anim = new FadeIn(((Node) event.getSource()).getParent());
    	anim.play();
    	
    	
    	
		Stage primaryStage = new Stage();
	
		Pane root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("/Home.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Scene scene = new Scene(root);			
		primaryStage.setScene(scene);		

        //set stage borderless (without max,min.. etc)
        primaryStage.initStyle(StageStyle.UNDECORATED);

        //drag it here to move window 
        root.setOnMousePressed(event4 -> {
            x = event4.getSceneX();
            y = event4.getSceneY();
        });
        root.setOnMouseDragged(event5 -> {

            primaryStage.setX(event5.getScreenX() - x);
            primaryStage.setY(event5.getScreenY() - y);
            
        });
        anim.setOnFinished(event1 -> {
    		stage1.close();		
    		 primaryStage.show();
    	});
       
    }

}
