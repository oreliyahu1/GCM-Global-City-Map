package guiControllers.item;

import animatefx.AnimateFXInterpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;

import javafx.scene.shape.Circle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

/**
 * The Class Loading.
 */
public class Loading {

	/** The circle 1. */
	@FXML
	private Circle circle1;

	/** The circle 2. */
	@FXML
	private Circle circle2;

	/**
	 * Initialize.
	 */
	@FXML
	void initialize() {
		circle1.setRotationAxis(Rotate.Z_AXIS);
		circle2.setRotationAxis(Rotate.Z_AXIS);
		
		Timeline timeline  = new Timeline(); 
		timeline.setCycleCount(Timeline.INDEFINITE); 
		timeline.setAutoReverse(true); 
		timeline.getKeyFrames().addAll(  new KeyFrame(Duration.millis(0),
                new KeyValue(circle1.rotateProperty(), -200, AnimateFXInterpolator.EASE),
                new KeyValue(circle1.opacityProperty(), 0, AnimateFXInterpolator.EASE)
        ),
        new KeyFrame(Duration.millis(1000),
                new KeyValue(circle1.rotateProperty(), 0, AnimateFXInterpolator.EASE),
                new KeyValue(circle1.opacityProperty(), 1, AnimateFXInterpolator.EASE)
        ),   new KeyFrame(Duration.millis(0),
                new KeyValue(circle2.rotateProperty(), +200, AnimateFXInterpolator.EASE),
                new KeyValue(circle2.opacityProperty(), 0, AnimateFXInterpolator.EASE)
        ),
        new KeyFrame(Duration.millis(1000),
                new KeyValue(circle2.rotateProperty(), 0, AnimateFXInterpolator.EASE),
                new KeyValue(circle2.opacityProperty(), 1, AnimateFXInterpolator.EASE)
        )); 
		timeline.play();
	}

}
