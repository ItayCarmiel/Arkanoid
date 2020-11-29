import biuoop.GUI;
import biuoop.DrawSurface;
import geometry.Line;

import java.util.Random;
import java.awt.Color;

/**
 * ass 02.
 * @author Itay Carmiel 208464198 <itaycar875@gmail.com>
 */
public class AbstractArtDrawing {
    /**
     *
     * @return - returns a random line
     */
    Line generateRandomLine() {
        Random rand = new Random(); // create a random-number generator
        // get a double in range 1-400
        double x1 = (double) rand.nextInt(400) + 1;
        // get a double in range 1-400
        double x2 = (double) rand.nextInt(400) + 1;
        // get a double in range 1-300
        double y1 = (double) rand.nextInt(300) + 1;
        // get a double in range 1-300
        double y2 = (double) rand.nextInt(300) + 1;
        Line l = new Line(x1, y1, x2, y2); // creates a new line
        return l;
    }
    /**
     * draws random lines and draws blue circles where.
     * the middle of the lines are and draws red circles
     * where the lines intersect
     */
    void drawRandomly() {
        Random rand = new Random();
        // create a random-number generator
        // gets a integer which be the number of lines to be drawn
        // creates an array
        int numberOfLines = rand.nextInt(20) + 1;
        Line[] lines = new Line[numberOfLines];
        // Create a window with the title "Random Circles Example"
        // which is 400 pixels wide and 300 pixels high.
        GUI gui = new GUI("Random Circles Example", 400, 300);
        DrawSurface d = gui.getDrawSurface();
        for (int i = 0; i < numberOfLines; ++i) {
            // creates and puts in each cell of the array a line
            // and then draws the line
            lines[i] = generateRandomLine();
            d.drawLine((int) lines[i].start().getX(), (int) lines[i].start().getY(),
                    (int) lines[i].end().getX(), (int) lines[i].end().getY());
            // sets the color to be blue and then draws the middle
            // of the line which its radius is 3
        }
        for (int i = 0; i < lines.length; ++i) {
            // sets the color to be blue and then draws the middle
            // of the line which its radius is 3
            if (lines[i].length() != 0) {
                d.setColor(Color.BLUE);
                d.fillCircle((int) lines[i].middle().getX(),
                        (int) lines[i].middle().getY(), 3);
            }
        }
        for (int i = 0; i < lines.length; i++) {
            for (int j = i + 1; j < lines.length; j++) {
                if (lines[i].isIntersecting(lines[j])) {
                    d.setColor(Color.RED);
                    if (lines[i].intersectionWith(lines[j]) != null) {
                        d.fillCircle(
                                (int) lines[i].intersectionWith(lines[j]).getX(),
                                (int) lines[i].intersectionWith(lines[j]).getY(), 3);
                    }
                }
            } //end of for loop
        } //end of for loop
        gui.show(d);
    }
    /**
     * @param args nothing
     */
    public static void main(String[] args) {
        AbstractArtDrawing example = new AbstractArtDrawing();
        example.drawRandomly();
    }
}