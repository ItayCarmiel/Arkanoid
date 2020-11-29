import biuoop.GUI;
import biuoop.DrawSurface;
import object.Ball;

/**
 * ass 03.
 * @author Itay Carmiel 208464198 <itaycar875@gmail.com>
 */
public class BouncingBallAnimation {
    /**
     * @param args - nothing
     */
    public static void main(String[] args) {
        GUI gui = new GUI("title", 800, 600);
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        Ball ball = new Ball(0, 0, 30, java.awt.Color.BLACK);
        ball.setVelocity(2, 2);
        while (true) {
            ball.moveOneStep();
            DrawSurface d = gui.getDrawSurface();
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }
}