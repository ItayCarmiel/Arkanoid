package game;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * ass 06.
 * @author Itay Carmiel 208464198 <itaycar875@gmail.com>
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    /**
     * @param framesPerSecond - number of frames every second.
     * @param gui - gui of animation.
     */
    public AnimationRunner(int framesPerSecond, GUI gui) {
        this.framesPerSecond = framesPerSecond;
        this.gui = gui;
    }
    /**
     * @param animation to run.
     */
    public void run(Animation animation) {
        Sleeper sleeper = new biuoop.Sleeper();
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d);
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(millisecondsPerFrame);
            }
        }
    }
    /**
     * @param animation to run only one frame.
     */
    public void runOneFrame(Animation animation) {
        DrawSurface d = gui.getDrawSurface();
        animation.doOneFrame(d);
        gui.show(d);
    }
    /**
     * @return the gui.
     */
    public GUI getGui() {
        return this.gui;
    }
    /**
     * set gui.
     * @param newGui the gui
     */
    public void setGui(GUI newGui) {
        this.gui = newGui;
    }
}
