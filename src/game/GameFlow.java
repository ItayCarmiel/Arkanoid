package game;

import java.util.List;

import biuoop.GUI;
import biuoop.KeyboardSensor;

/**
 * ass 06.
 * @author Itay Carmiel 208464198 <itaycar875@gmail.com>
 */
public class GameFlow {
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private boolean didWin = true;
    private Counter countBloocks = new Counter(0);
    private Counter score;
    private HighScores highScores;
    private ScoreInfo scoreinfo;
    private biuoop.GUI gui;

    /**
     * @param anim is AnimationRunner of all the game.
     * @param key is our KeyboardSensor of the game.
     * @param g gui
     */
    public GameFlow(AnimationRunner anim, KeyboardSensor key, GUI g) {
        this.animationRunner = anim;
        this.keyboardSensor = key;
        this.gui = g;
        this.score = new Counter(0);
    }
    /**
     * @param levels List with level to play in order
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.score, this.animationRunner,
                    this.gui, this.keyboardSensor);
            level.initialize();
            level.run();
            if (level.getBallCount() == 0) {
                this.didWin = false;
                    break;
                }
            }
        this.animationRunner.run(new KeyPressStoppableAnimation(keyboardSensor,
                KeyboardSensor.SPACE_KEY, new EndScreen(this.animationRunner.getGui().getKeyboardSensor(),
                this.score, this.didWin)));
        this.highScores = new HighScores(1);
        scoreinfo = new ScoreInfo(this.score.getValue());
        highScores.add(scoreinfo);

        }
    }
