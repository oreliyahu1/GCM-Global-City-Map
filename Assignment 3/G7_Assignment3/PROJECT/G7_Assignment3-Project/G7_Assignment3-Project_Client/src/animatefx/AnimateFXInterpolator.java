package animatefx;

import javafx.animation.Interpolator;

// TODO: Auto-generated Javadoc
/**
 * The Class AnimateFXInterpolator.
 */
public final class AnimateFXInterpolator {

    /**
     * Instantiates a new animate FX interpolator.
     */
    private AnimateFXInterpolator() {
        throw new IllegalStateException("AnimateFX Interpolator");
    }

    /** The Constant EASE. */
    public static final Interpolator EASE = Interpolator.SPLINE(0.25, 0.1, 0.25, 1);
    
    /** The Constant SPEED. */
    public static final double SPEED = 0.5;

}
