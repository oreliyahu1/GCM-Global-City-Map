package animatefx;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.util.Duration;


// TODO: Auto-generated Javadoc
/**
 * The Class SlideInUp.
 *
 * @author Loïc Sculier aka typhon0
 */
public class SlideInUp extends AnimationFX {

    /**
     * Create new SlideInUp.
     *
     * @param node The node to affect
     */
    public SlideInUp(Node node) {
        super(node);
    }

    /**
     * Instantiates a new slide in up.
     */
    public SlideInUp() {
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
                        new KeyValue(getNode().translateYProperty(), getNode().getBoundsInParent().getHeight(), AnimateFXInterpolator.EASE)
                ),
                new KeyFrame(Duration.millis(1000 * AnimateFXInterpolator.SPEED),
                        new KeyValue(getNode().translateYProperty(), 0, AnimateFXInterpolator.EASE)
                )
        ));
    }
}
