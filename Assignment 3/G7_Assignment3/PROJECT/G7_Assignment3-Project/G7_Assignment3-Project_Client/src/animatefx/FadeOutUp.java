package animatefx;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.util.Duration;


// TODO: Auto-generated Javadoc
/**
 * The Class FadeOutUp.
 *
 * @author Loïc Sculier aka typhon0
 */
public class FadeOutUp extends AnimationFX {

    /**
     * Create new FadeOutUp.
     *
     * @param node The node to affect
     */
    public FadeOutUp(Node node) {
        super(node);
    }

    /**
     * Instantiates a new fade out up.
     */
    public FadeOutUp() {
    }

    /* (non-Javadoc)
     * @see animatefx.AnimationFX#resetNode()
     */
    @Override
    AnimationFX resetNode() {
        getNode().setOpacity(1);
        getNode().setTranslateY(0);
        return this;
    }

    /* (non-Javadoc)
     * @see animatefx.AnimationFX#initTimeline()
     */
    @Override
    void initTimeline() {
        setTimeline(new Timeline(
                new KeyFrame(Duration.millis(0),
                        new KeyValue(getNode().opacityProperty(), 1, AnimateFXInterpolator.EASE),
                        new KeyValue(getNode().translateYProperty(), 0, AnimateFXInterpolator.EASE)

                ),
                new KeyFrame(Duration.millis(1000 * AnimateFXInterpolator.SPEED),
                        new KeyValue(getNode().opacityProperty(), 0, AnimateFXInterpolator.EASE),
                        new KeyValue(getNode().translateYProperty(), -getNode().getBoundsInParent().getHeight(), AnimateFXInterpolator.EASE)

                )
        ));
    }
}

