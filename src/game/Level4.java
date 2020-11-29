package game;
import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import geometry.Point;
import geometry.Velocity;
import object.Block;


/**
 * ass 06.
 * @author Itay Carmiel 208464198 <itaycar875@gmail.com>
 */
public class Level4 implements LevelInformation {
    private String levelName;
    private int paddleSpeed;
    private int numberOfBalls;
    private int paddleWidth;
    private int numberOfBlocksToRemove;
    private Color color;
    private Block background;
    private boolean change;
    /**
     * Constructor.
     */
    public Level4() {
        levelName = "Final Four";
        paddleSpeed = 10;
        numberOfBalls = 3;
        paddleWidth = 85;
        numberOfBlocksToRemove = 105;
        color = new Color(34, 150, 255);
        background = new Block(new Point(0, 0), 800, 600, color);
        change = true;
    }
    /**
     * @return number of balls.
     */
    public int numberOfBalls() {
        return this.numberOfBalls;
    }
    /**
     * @return velocity list of each ball.
     */
    public java.util.List<Velocity> initialBallVelocities() {
        List<Velocity> velocityBalls = new LinkedList<Velocity>();
        int angel = 0;
        velocityBalls.add(Velocity.fromAngleAndSpeed(angel + 30, 7));
        velocityBalls.add(Velocity.fromAngleAndSpeed(angel, 7));
        velocityBalls.add(Velocity.fromAngleAndSpeed(angel - 30, 7));
        return velocityBalls;
    }
    /**
     * @return paddle speed.
     */
    public int paddleSpeed() {
        return this.paddleSpeed;
    }
    /**
     * @return paddle width.
     */
    public int paddleWidth() {
        return this.paddleWidth;
    }

    /**
     * @return level name.
     */
    public String levelName() {
        return this.levelName;
    }

    /**
     * @return level background.
     */
    public Sprite getBackground() {
        return this.background;
    }
    /**
     * @return Blocks list of this level.
     */
    public java.util.List<Block> blocks() {
        List<Block> blocksList = new LinkedList<Block>();
        Block[][] block = new Block[7][];
        if (!change) {
            return blocksList;
        }
        block[0] = createBlock(100, java.awt.Color.GRAY, blocksList);
        block[1] = createBlock(120, java.awt.Color.RED, blocksList);
        block[2] = createBlock(140, java.awt.Color.YELLOW, blocksList);
        block[3] = createBlock(160, java.awt.Color.GREEN, blocksList);
        block[4] = createBlock(180, java.awt.Color.WHITE, blocksList);
        block[5] = createBlock(200, java.awt.Color.PINK, blocksList);
        block[6] = createBlock(220, java.awt.Color.CYAN, blocksList);
        return blocksList;
    }
    /**
     * creates an array of blocks.
     * @param locationY
     *            location Y
     * @param myColor
     *            of the rectangle
     * @param blocks
     *            list of blocks
     * @return block list.
     */
    private Block[] createBlock(int locationY, Color myColor, java.util.List<Block> blocks) {
        double locationX = 25;
        Block[] newBlock = new Block[15];
        for (int i = 0; i < 15; i++) {
            if (!change) {
                return newBlock;
            }
            newBlock[i] = new Block(new Point(locationX, locationY), 50, 20, myColor);
            locationX += 50;
            blocks.add(newBlock[i]);
        }
        return newBlock;
    }
    /**
     * @return blocks number to remove.
     */
    public int numberOfBlocksToRemove() {
        return this.numberOfBlocksToRemove;
    }
}
