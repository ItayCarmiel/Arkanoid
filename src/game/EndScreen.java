package game;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import object.Block;

import java.awt.Color;

/**
 * ass 06.
 * @author Itay Carmiel 208464198 <itaycar875@gmail.com>
 */
public class EndScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    private Counter score;
    private boolean isWin;
    private Color colorWin;
    private Color colorLose;
    private Block background;
    /**
     * @param k keyboard sensor.
     * @param score our score.
     * @param isWinner if we win.
     */
    public EndScreen(KeyboardSensor k, Counter score, boolean isWinner) {
        this.keyboard = k;
        this.stop = false;
        this.score = score;
        this.isWin = isWinner;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
            colorWin = new Color(100, 170, 200);
            colorLose = new Color(200, 0, 0);
            if (isWin) {
                ColorBackGround cb = new ColorBackGround(colorWin);
                cb.drawOn(d);
                d.setColor(Color.BLACK);
                d.drawText(d.getWidth() / 4, d.getHeight() / 2,
                        "You Win! Your score is " + score.getValue(), 32);
            } else {
                ColorBackGround cb = new ColorBackGround(colorLose);
                cb.drawOn(d);
                d.setColor(Color.BLACK);
                d.drawText(d.getWidth() / 4, d.getHeight() / 2,
                        "Game Over. Your score is " + score.getValue(), 32);
            }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
