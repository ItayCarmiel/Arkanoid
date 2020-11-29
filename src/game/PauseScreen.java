package game;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import object.Block;

import java.awt.Color;

/**
 * ass 06.
 * @author Itay Carmiel 208464198 <itaycar875@gmail.com>
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    private Color color;
    private Block background;
    /**
     * @param k sensor of the keyboard.
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        color = Color.magenta;
        ColorBackGround cb = new ColorBackGround(color);
        cb.drawOn(d);
        d.setColor(Color.BLACK);
        d.drawText(150, d.getHeight() / 2, "paused -- press space to continue", 32);
    }
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}