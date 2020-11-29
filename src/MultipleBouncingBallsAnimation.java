import java.util.Random;
import biuoop.DrawSurface;
import biuoop.GUI;
import object.Ball;

/**
 * ass 02.
 * @author Itay Carmiel 208464198 <itaycar875@gmail.com>
 */
public class MultipleBouncingBallsAnimation {
    /**
     * @param numbers - a string array that
     * should be converted to an double array
     * @return double[] doubleArray - an double array
     * that was made of a string array
     */
    public static Ball[] stringToBalls(String[] numbers) {
        int i, radius;
        Random r = new Random();
        Ball[] ballsArray = new Ball[numbers.length];
        //converts every cell to ball
        for (i = 0; i < numbers.length; i++) {
            radius = Integer.parseInt(numbers[i]);
            ballsArray[i] = new Ball(Math.max(r.nextInt(600 - radius), radius),
                    Math.max(r.nextInt(800 - radius), radius),
                    radius, java.awt.Color.BLACK);
            if (radius < 50) {
                ballsArray[i].setVelocity(51 - radius, 51 - radius);
            } else {
                ballsArray[i].setVelocity(1, 1);
            }
        } //end for loop
        return ballsArray;
    }
    /**
     * @param args - centers
     */
    public static void main(String[] args) {
        Ball[] balls;
        int i;
        balls = stringToBalls(args);
        GUI gui = new GUI("title", 800, 600);
        DrawSurface d = gui.getDrawSurface();
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        while (true) {
            for (i = 0; i < balls.length; i++) {
                balls[i].moveOneStep();
            } //end of for loop
            d = gui.getDrawSurface();
            for (i = 0; i < balls.length; i++) {
                balls[i].drawOn(d);
            } //end of for loop
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }
}
