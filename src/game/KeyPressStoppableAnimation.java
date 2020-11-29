package game;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
/**
 * ass 06.
 * @author Itay Carmiel 208464198 <itaycar875@gmail.com>
 */

public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor keyboard;
    private String key;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;
    /**
     * constructor.
     * @param sensor a keyboard sensor
     * @param key the scores
     * @param animation the animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor,
                                      String key, Animation animation) {
        this.animation = animation;
        this.key = key;
        this.keyboard = sensor;
        this.stop = false;
        this.isAlreadyPressed = true;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);
        if (this.keyboard.isPressed(this.key)) {
            if (this.isAlreadyPressed) {
                return;
            } else {
                this.stop = true;
            }
        }
        this.isAlreadyPressed = false;
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
