package game;
import geometry.Point;
import geometry.Velocity;
import object.Block;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

/**
 * ass 06.
 * @author Itay Carmiel 208464198 <itaycar875@gmail.com>
 */
public class Level2 implements LevelInformation {
    private int numberOfBalls;
    private int paddleSpeed;
    private int paddleWidth;
    private int numberOfBlocksToRemove;
    private String levelName;
    private Block background;
    /**
     * Cunstructor.
     */
    public Level2() {
        background = new Block(new Point(0, 0), 800, 600, Color.blue);
        levelName = "Wide Easy";
        numberOfBalls = 10;
        paddleSpeed = 6;
        paddleWidth = 500;
        numberOfBlocksToRemove = 15;
    }
    @Override
    public int numberOfBalls() {
        return this.numberOfBalls;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocityBalls = new LinkedList<Velocity>();
        int angel = 72;
        for (int i = 0; i < this.numberOfBalls; i++) {
            velocityBalls.add(Velocity.fromAngleAndSpeed(angel, 5));
            angel -= 16;
        }
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
        Color[] colorArray = new Color[7];
        colorArray[0] = Color.white;
        colorArray[1] = Color.pink;
        colorArray[2] = Color.blue;
        colorArray[3] = Color.green;
        colorArray[4] = Color.yellow;
        colorArray[5] = Color.orange;
        colorArray[6] = Color.red;
        double axisX = 725;
        double axisY = 245;
        List<Block> blocksList = new LinkedList<Block>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                blocksList.add(new Block(new Point(axisX, axisY), 50, 20, colorArray[i]));
                axisX = (axisX - 50);
            }
        }
        // 3 blocks of green
        for (int j = 0; j < 3; j++) {
            blocksList.add(new Block(new Point(axisX, axisY), 50, 20, colorArray[3]));
            axisX = (axisX - 50);
        }
        for (int i = 4; i < 7; i++) {
            for (int j = 0; j < 2; j++) {
                blocksList.add(new Block(new Point(axisX, axisY), 50, 20, colorArray[i]));
                axisX = (axisX - 50);
            }
        }
        return blocksList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.numberOfBlocksToRemove;
    }
}
