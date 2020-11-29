package game;
import geometry.Velocity;
import object.Block;

import java.util.List;

/**
 * ass 06.
 * @author Itay Carmiel 208464198 <itaycar875@gmail.com>
 */
public interface LevelInformation {
    /**
     * number of left balls.
     * @return the number of balls that was left.
     */
    int numberOfBalls();
    /**
     * Initial velocity of each ball.
     * @return a velocity list of the balls.
     */
    java.util.List<Velocity> initialBallVelocities();
    /**
     * @return the paddle's speed.
     */
    int paddleSpeed();
    /**
     * @return the paddle's width.
     */
    int paddleWidth();
    /**
     * @return level name.
     */
    String levelName();
    /**
     * @return a sprite with the background of the level.
     */
    Sprite getBackground();
    /**
     * @return a list of blocks that make up this level.
     */
    List<Block> blocks();
    /**
     * @return number of blocks that should be removed.
     */
    int numberOfBlocksToRemove();
}
