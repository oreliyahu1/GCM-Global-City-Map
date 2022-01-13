package animatefx;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.util.Duration;


// TODO: Auto-generated Javadoc
/**
 * The Class Flash.
 *
 * @author Loïc Sculier aka typhon0
 */
public class Flash extends AnimationFX {

    /**
     * Create new Flash.
     *
     * @param node The node to affect
     */
    public Flash(Node node) {
        super(node);
    }

    /**
     * Instantiates a new flash.
     */
    public Flash() {
    }

    /* (non-Javadoc)
     * @see animatefx.AnimationFX#resetNode()
     */
    @Override
    AnimationFX resetNode() {

        return this;
    }

    /* (non-Javadoc)
     * @see animatefx.AnimationFX#initTimeline()
     */
    @Override
    void initTimeline() {
        setTimeline(new Timeline(

                new KeyFrame(Duration.millis(0),
                        new KeyValue(getNode().opacityProperty(), 1, AnimateFXInterpolator.EASE)
                ),
                new KeyFrame(Duration.millis(250 * AnimateFXInterpolator.SPEED),
                        new KeyValue(getNode().opacityProperty(), 0, AnimateFXInterpolator.EASE)
                ),
                new KeyFrame(Duration.millis(500 * AnimateFXInterpolator.SPEED),
                        new KeyValue(getNode().opacityProperty(), 1, AnimateFXInterpolator.EASE)
                ),
                new KeyFrame(Duration.millis(750 * AnimateFXInterpolator.SPEED),
                        new KeyValue(getNode().opacityProperty(), 0, AnimateFXInterpolator.EASE)
                ),
                new KeyFrame(Duration.millis(1000 * AnimateFXInterpolator.SPEED),
                        new KeyValue(getNode().opacityProperty(), 1, AnimateFXInterpolator.EASE)
                )

        ));
    }

}

