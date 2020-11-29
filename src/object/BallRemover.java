package object;

import game.Counter;
import game.GameLevel;
import hit.HitListener;

/**
 * ass 05.
 * @author Itay Carmiel 208464198 <itaycar875@gmail.com>
 */

public class BallRemover implements HitListener {
    private GameLevel g;
    private Counter removedBalls;
    /**
     * @param g current being played.
     * @param removedBalls counter.
     */
    public BallRemover(GameLevel g, Counter removedBalls) {
        this.g = g;
        this.removedBalls = removedBalls;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.g);
        this.removedBalls.decrease(1);
    }
}
