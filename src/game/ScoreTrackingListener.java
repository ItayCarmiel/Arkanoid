package game;

import hit.HitListener;
import object.Ball;
import object.Block;

/**
 * ass 05.
 * @author Itay Carmiel 208464198 <itaycar875@gmail.com>
 */

public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;
    /**
     * @param scoreCounter current score counter.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }
    /**
     *  Update current score according to the hit occurrences.
     *  @param beingHit block that is being hit
     *  @param hitter ball that hit the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
            this.currentScore.increase(5);
    }
}
