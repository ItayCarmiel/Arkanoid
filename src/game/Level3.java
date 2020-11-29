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
public class Level3 implements LevelInformation {
    private int numberOfBalls;
    private int paddleSpeed;
    private int paddleWidth;
    private int numberOfBlocksToRemove;
    private String levelName;
    private int count;
    private Color color;
    private Block background;
    private boolean change;

    /**
     * Cunstructor.
     */
    public Level3() {
        numberOfBalls = 2;
        paddleSpeed = 10;
        paddleWidth = 85;
        numberOfBlocksToRemove = 40;
        levelName = "Green 3";
        count = 0;
        color = new Color(0, 150, 100);
        background = new Block(new Point(0, 0), 800, 600, color);
        change = true;
    }
    @Override
    public int numberOfBalls() {
        return this.numberOfBalls;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new LinkedList<Velocity>();
        int initialV = 38;
        velocities.add(Velocity.fromAngleAndSpeed(initialV, 7));
        initialV -= 81;
        velocities.add(Velocity.fromAngleAndSpeed(initialV, 7));
        initialV -= 81;
        velocities.add(Velocity.fromAngleAndSpeed(initialV, 7));
        return velocities;
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
        Block[][] block = new Block[5][];
        block[0] = createBlock(10, 725, 125, java.awt.Color.GRAY, blocksList);
        block[1] = createBlock(9, 725, 145, java.awt.Color.RED, blocksList);
        block[2] = createBlock(8, 725, 165, java.awt.Color.YELLOW, blocksList);
        block[3] = createBlock(7, 725, 185, java.awt.Color.BLUE, blocksList);
        block[4] = createBlock(6, 725, 205, java.awt.Color.WHITE, blocksList);
        return blocksList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.numberOfBlocksToRemove;
    }
    /**
     * create blocks array.
     * @param size number of blocks
     * @param axisX x axis
     * @param axisY y axis
     * @param mycolor block color
     * @param blocks block list
     * @return blocks array created.
     */
    private Block[] createBlock(int size, int axisX, int axisY, Color mycolor, java.util.List<Block> blocks) {
        Block[] gameBlocks = new Block[size];
        if (!change) {
            return gameBlocks;
        }
        for (int i = 0; i < size; i++) {
            gameBlocks[i] = new Block(new Point(axisX, axisY), 50, 20, mycolor);
            axisX -= 50;
            blocks.add(gameBlocks[i]);
        }
        return gameBlocks;
    }
}
