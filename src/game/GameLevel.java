package game;

import java.awt.Color;
import java.util.Iterator;

import biuoop.GUI;
import hit.Collidable;
import geometry.Point;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import geometry.Rectangle;
import geometry.Velocity;
import object.Ball;
import object.Paddle;
import object.Block;
import object.BlockRemover;
import object.BallRemover;


/**
 * ass 05.
 * @author Itay Carmiel 208464198 <itaycar875@gmail.com>
 */
public class GameLevel implements Animation {
    private LevelInformation info;
    private Counter score;
    private Counter blockCount;
    private Counter ballCount = new Counter(0);
    private ScoreTrackingListener tracker;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Paddle paddle;
    private KeyboardSensor keyboard;
    private Ball[] balls;
    private java.util.List<Velocity> velocities;
    private java.util.List<Block> gameBlocks;
    private BlockRemover blockRe;
    private BallRemover ballRe;
    private AnimationRunner runner;
    private boolean running;
    private GUI gui;
    /**
     * @param info our level info
     * @param r animation runner to run animation.
     * @param k keyboard sensor.
     * @param blockcount num of blocks.
     * @param scores the score.
     */
    public GameLevel(LevelInformation info, AnimationRunner r, KeyboardSensor k,
                      Counter blockcount, Counter scores) {
        this.score = scores;
        this.blockCount = blockcount;
        this.keyboard = k;
        this.runner = r;
        this.info = info;
    }
    /**
     * constructor.
     * @param levelInfo level information
     * @param score score counter
     * @param ar animation runner
     * @param gui the gui
     * @param keyboard keyboard sensor
     */
    public GameLevel(LevelInformation levelInfo, Counter score, AnimationRunner ar,
                     biuoop.GUI gui, biuoop.KeyboardSensor keyboard) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.blockCount = new Counter();
        this.ballCount = new Counter();
        this.score = score;
        this.runner = ar;
        this.info = levelInfo;
        this.gui = gui;
        this.keyboard = keyboard;
    }
    /**
     * @param c add the collidable to the environment arr.
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }
    /**
     * @param c remove the collidable to the environment arr.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }
    /**
     * @param s - sprite
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }
    /**
     * @param s - sprite
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }
    /**
     * Initialize a new game: create the Blocks and geometry.object.Ball (and object.Paddle).
     *  and add them to the game.
     */
    public void initialize() {
        sprites = new SpriteCollection();
        this.runner.setGui(this.gui);
        // add backGround
        this.sprites.addSprite(this.info.getBackground());
        this.blockCount = new Counter(info.numberOfBlocksToRemove());
        this.ballCount = new Counter(info.numberOfBalls());
        blockRe = new BlockRemover(this, this.blockCount);
        ballRe = new BallRemover(this, this.ballCount);
        this.tracker = new ScoreTrackingListener(this.score);
        this.environment = Ball.getGameEnvironment();
        LevelNameIndicator levelName = new LevelNameIndicator(new Rectangle(new Point(0, 0),
                800, 25), this.info.levelName());
        ScoreIndicator scoreIndi = new ScoreIndicator(new Rectangle(new Point(0, 0), 800, 25),
                this.score);
        Block upBorderBlock = new Block(new Rectangle(new Point(0, 0), 800, 20)),
                rightBorderBlock = new Block(new Rectangle(new Point(780, 0), 20, 600)),
                leftBorderBlock = new Block(new Rectangle(new Point(0, 0), 20, 600)),
                deadRegionBlock = new Block(new Rectangle(new Point(0, 600), 800, 20));
        upBorderBlock.setColor(Color.lightGray);
        leftBorderBlock.setColor(java.awt.Color.lightGray);
        rightBorderBlock.setColor(java.awt.Color.lightGray);
        deadRegionBlock.setColor(java.awt.Color.lightGray);
        upBorderBlock.addToGame(this);
        leftBorderBlock.addToGame(this);
        rightBorderBlock.addToGame(this);
        deadRegionBlock.addHitListener(ballRe);
        deadRegionBlock.addToGame(this);
        scoreIndi.addToGame(this);
        levelName.addToGame(this);
        gameBlocks = this.info.blocks();
        Iterator<Block> iter = gameBlocks.iterator();
        while (iter.hasNext()) {
            iter.next().addToGame(this);
        }
        Iterator<Block> iter2 = gameBlocks.iterator();
        while (iter2.hasNext()) {
            iter2.next().addHitListener(blockRe);
        }
        Iterator<Block> iter3 = gameBlocks.iterator();
        while (iter3.hasNext()) {
            iter3.next().addHitListener(tracker);
        }
    }
    /**
     * Run the game - start the animation loop.
     */
    public void run() {
        playOneTurn();
        if (blockCount.getValue() == 0) {
            score.increase(100);
            runner.runOneFrame(this);
            removeElements();
        }
        this.paddle.removeFromGame(this);
        runner.runOneFrame(this);
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        if ((ballCount.getValue() == 0) || (blockCount.getValue() == 0)) {
            running = false;
            removeElements();
        }
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(keyboard,
                    KeyboardSensor.SPACE_KEY, new PauseScreen(this.keyboard)));
        }
    }

    @Override
    public boolean shouldStop() {
        return !running;
    }
    /**
     * create balls on top of the paddle and the paddle.
     */
    private void createBallsOnTopOfPaddle() {
        balls = new Ball[this.info.numberOfBalls()];
        paddle = new Paddle(new Point((800 - info.paddleWidth()) / 2,
                600 - 45), info.paddleWidth(), 20, Color.ORANGE, this.keyboard);
        paddle.setSpeed(this.info.paddleSpeed());
        environment.addCollidable(paddle);
        paddle.addToGame(this);
        this.velocities = info.initialBallVelocities();
        Iterator<Velocity> velo = velocities.iterator();
        for (int i = 0; i < this.info.numberOfBalls(); i++) {
            balls[i] = new Ball(800 / 2, 600 - 65, 7, Color.white);
            balls[i].setVelocity(velo.next());
            balls[i].addToGame(this);
        }
    }
    /**
     * Run the game.
     */
    public void playOneTurn() {
        this.createBallsOnTopOfPaddle();
        // Count Down 3 2 1 Go (during 2 seconds).
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        running = true;
        // the loop that runs the game
        this.runner.run(this);
    }
    /**
     * get balls number.
     * @return number of blocks.
     */
    public int getBallCount() {
        return ballCount.getValue();
    }
    /**
     * remove all elements from level.
     */
    public void removeElements() {
        this.environment.deplete();
        this.sprites.deplete();
    }
}