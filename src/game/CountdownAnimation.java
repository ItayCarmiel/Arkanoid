package game;

import biuoop.DrawSurface;
import java.awt.Color;

/**
 * ass 06.
 * @author Itay Carmiel 208464198 <itaycar875@gmail.com>
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private boolean stop;
    private long beginTime;
    private long relativeTime;
    private long beginRelativeTime;
    /**
     * @param numOfSeconds seconds.
     * @param countFrom count from this to zero.
     * @param gameScreen where to display.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom,
                              SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        stop = false;
        beginTime = System.currentTimeMillis();
        beginRelativeTime = (long) (1000 * this.numOfSeconds / this.countFrom);
        relativeTime = beginRelativeTime;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        this.gameScreen.drawAllOn(d);
        d.setColor(Color.cyan);
        d.drawText(375, d.getHeight() / 5, countFrom + "", 100);
        long passedTime = System.currentTimeMillis() - beginTime;
        if (relativeTime < passedTime) {
            countFrom--;
            relativeTime = (relativeTime + beginRelativeTime);
        }
        if (countFrom == 0) {
            stop = true;
        }
    }
    @Override
    public boolean shouldStop() {
        return stop;
    }
}
