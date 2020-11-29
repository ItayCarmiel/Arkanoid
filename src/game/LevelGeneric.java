package game;
import geometry.Velocity;
import object.Block;
import java.util.ArrayList;
import java.util.List;
/**
 * ass 07.
 * @author Itay Carmiel 208464198 <itaycar875@gmail.com>
 */
public class LevelGeneric implements LevelInformation {
    private List<Velocity> velocityList;
    private int paddleSpeed;
    private int paddleWidth;
    private Sprite backGround;
    private List<Block> listOfBlocks;
    private String levelName;
    /**
     * Constructor.
     */
    public LevelGeneric() {
        List<Velocity> lv = new ArrayList<Velocity>();
        List<Block> lb = new ArrayList<Block>();
        String name = new String();
        this.velocityList = lv;
        this.paddleSpeed = 0;
        this.paddleWidth = 0;
        this.backGround = null;
        this.listOfBlocks = lb;
        this.levelName = name;
    }
    /**
     * Adds a velocity to the velocity list.
     * @param v the velocity we want to add to the list.
     */
    public void addVelocity(Velocity v) {
        this.velocityList.add(v);
    }
    /**
     * Returns the number of balls in the level.
     * @return number of balls.
     */
    public int numberOfBalls() {
        return this.velocityList.size();
    }
    /**
     * Returns the velocities of the balls.
     * @return The list of ball velocities.
     */
    /**
     * Sets the list of velocities.
     * @param l - the list of velocities.
     */
    public void setListOfVelocities(List<Velocity> l) {
        this.velocityList = l;
    }
    /**
     * Returns the list of velocities.
     * @return this list of velocities.
     */
    public List<Velocity> initialBallVelocities() {
        return this.velocityList;
    }
    /**
     * Sets the paddle's speed.
     * @param speed - paddle speed.
     */
    public void setPaddleSpeed(int speed) {
        this.paddleSpeed = speed;
    }
    /**
     * Returns the paddles speed.
     * @return paddle speed.
     */
    public int paddleSpeed() {
        return this.paddleSpeed;
    }
    /**
     * Sets the paddle width.
     * @param width - the paddle width.
     */
    public void setPaddleWidth(int width) {
        this.paddleWidth = width;
    }
    /**
     * Returns the paddle width.
     * @return paddle width.
     */
    public int paddleWidth() {
        return this.paddleWidth;
    }
    /**
     * Sets the level name.
     * @param name - the level's name.
     */
    public void setLevelName(String name) {
        this.levelName = name;
    }
    /**
     * Returns the levels name.
     * @return a string of the levels name.
     */
    public String levelName() {
        return this.levelName;
    }
    /**
     * Sets the background.
     * @param s - the new background.
     */
    public void setBackGround(Sprite s) {
        this.backGround = s;
    }
    /**
     * Returns this background.
     * @return the background.
     */
    public Sprite getBackground() {
        return this.backGround;
    }

    /**
     * Sets the list of blocks.
     * @param l - the list of blocks.
     */
    public void setListOfBlocks(List<Block> l) {
        this.listOfBlocks = l;
    }
    /**
     * Returns this list of blocks.
     * @return list of blocks.
     */
    public List<Block> blocks() {
        return this.listOfBlocks;
    }
    /**
     * Returns the number of blocks needed to be removed in order to
     * clear the level.
     * @return number of blocks.
     */
    public int numberOfBlocksToRemove() {
        return this.listOfBlocks.size();
    }
}
