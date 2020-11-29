package hit;

import object.Ball;
import object.Block;

/**
 * ass 05.
 * @author Itay Carmiel 208464198 <itaycar875@gmail.com>
 */

public interface HitListener {
    /**
     *  This method is called whenever the beingHit object is hit.
     *  The hitter parameter is the Ball that's doing the hitting.
     * @param beingHit - block
     * @param hitter - ball
     */
    void hitEvent(Block beingHit, Ball hitter);
}
