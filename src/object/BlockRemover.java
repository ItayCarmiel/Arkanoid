package object;

import game.GameLevel;
import hit.HitListener;
import game.Counter;

/**
 * ass 05.
 * @author Itay Carmiel 208464198 <itaycar875@gmail.com>
 */
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;
    /**
     * Ctor.
     * @param gameLevel the game.
     * @param removedBlocks counter.
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = removedBlocks;
    }
    /**
     * remove a block.
     * @param beingHit being hit block.
     * @param hitter hitting ball.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
            beingHit.removeHitListener(this);
            beingHit.removeFromGame(this.gameLevel);
            hitter.getGameEnvironment().removeCollidable(beingHit);
            this.remainingBlocks.decrease(1);
    }
}
