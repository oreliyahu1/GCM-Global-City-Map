package animatefx;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.util.Duration;


// TODO: Auto-generated Javadoc
/**
 * The Class FadeOutRight.
 *
 * @author Loïc Sculier aka typhon0
 */
public class FadeOutRight extends AnimationFX {

    /**
     * Create new FadeOutLeft.
     *
     * @param node The node to affect
     */
    public FadeOutRight(Node node) {
        super(node);
    }

    /**
     * Instantiates a new fade out right.
     */
    public FadeOutRight() {
    }

    /* (non-Javadoc)
     * @see animatefx.AnimationFX#resetNode()
     */
    @Override
    AnimationFX resetNode() {
        getNode().setOpacity(1);
        getNode().setTranslateX(0);
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
                        new KeyValue(getNode().translateXProperty(), 0, AnimateFXInterpolator.EASE)

                ),
                new KeyFrame(Duration.millis(1000 * AnimateFXInterpolator.SPEED),
                        new KeyValue(getNode().opacityProperty(), 0, AnimateFXInterpolator.EASE),
                        new KeyValue(getNode().translateXProperty(), getNode().getBoundsInParent().getWidth(), AnimateFXInterpolator.EASE)

                )
        ));
    }
}

