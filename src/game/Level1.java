package game;

import geometry.Point;
import geometry.Velocity;
import object.Block;
import java.util.LinkedList;
import java.util.List;

/**
 * ass 06.
 * @author Itay Carmiel 208464198 <itaycar875@gmail.com>
 */
public class Level1 implements LevelInformation {
    private int numberOfBalls;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private int numberOfBlocksToRemove;
    private Sprite background;
    /**
     * constructor.
     */
    public Level1() {
        background = new Block(new Point(0, 0), 800, 600, java.awt.Color.black);
        numberOfBlocksToRemove = 1;
        levelName = "Direct Hit";
        numberOfBalls = 1;
        paddleSpeed = 12;
        paddleWidth = 85;
    }
    @Override
    public int numberOfBalls() {
        return this.numberOfBalls;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocityBalls = new LinkedList<Velocity>();
        velocityBalls.add(Velocity.fromAngleAndSpeed(180, 10));
        return velocityBalls;
    }

    @Override
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    @Override
    public int paddleWidth() {
        return this.paddleWidth;
    }

    @Override
    public String levelName() {
        return this.levelName;
    }

    @Override
    public Sprite getBackground() {
        return this.background;
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocksList = new LinkedList<Block>();
        Block blocks = new Block(new Point(380, 150), 40, 40, java.awt.Color.RED);
        blocksList.add(blocks);
        return blocksList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.numberOfBlocksToRemove;
    }
}
