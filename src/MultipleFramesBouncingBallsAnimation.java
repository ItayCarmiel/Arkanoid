import geometry.Point;
import biuoop.DrawSurface;
import biuoop.GUI;
import geometry.Velocity;
import object.Ball;

import java.awt.Color;
/**
 * ass 02.
 * @author Itay Carmiel 208464198 <itaycar875@gmail.com>
 */
public class MultipleFramesBouncingBallsAnimation {
    /**
     * @param args - center
     */
    public static void main(String[] args) {
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        GUI gui = new GUI("Interface", 800, 600);
        //number of balls in each array.
        int numOfBalls = args.length / 2;
        Ball[] balls1 = new Ball[numOfBalls];
        Ball[] balls2 = new Ball[numOfBalls];
        double[] first = new double[args.length / 2];
        double[] second = new double[args.length / 2];
        int i;
        for (i = 0; i < numOfBalls; i++) {
            first[i] = Double.parseDouble(args[i]);
        }
        int j = 0;
        for (; i < args.length; i++) {
            second[j] = Double.parseDouble(args[i]);
            j++;
        }
        for (i = 0; i < numOfBalls; i++) {
            double x = 50 + Math.random() * (500 - 50);
            double y = 50 + Math.random() * (500 - 50);
            double z = 450 + Math.random() * (600 - 450);
            double w = 450 + Math.random() * (600 - 450);
            balls1[i] = new Ball(new Point(x, y), (int) first[i], Color.yellow);
            balls2[i] = new Ball(new Point(z, w), (int) second[i], Color.gray);
            Velocity v1 = Velocity.fromAngleAndSpeed(45, 100 * (1 / first[i]));
            Velocity v2 = Velocity.fromAngleAndSpeed(45, 100 * (1 / second[i]));
            balls1[i].setVelocity(v1);
            balls2[i].setVelocity(v2);
        }
        i = 0;
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            d.setColor(java.awt.Color.GRAY);
            d.fillRectangle(50, 50, 450, 450);
            d.setColor(java.awt.Color.YELLOW);
            d.fillRectangle(450, 450, 150, 150);
            d.setColor(Color.black);
            for (j = 0; j < numOfBalls; j++) {
                balls1[j].moveOneStep(500, 500, 50, 50);
                balls2[j].moveOneStep(600, 600, 450, 450);
                balls1[j].drawOnNew(d, 50, 500);
                balls2[j].drawOnNew(d, 450, 600);
            }
            gui.show(d);
            sleeper.sleepFor(50);
        }
    }
}