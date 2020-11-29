package geometry;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
/**
 * ass 05.
 * @author Itay Carmiel 208464198 <itaycar875@gmail.com>
 */
/**
 * cpnstructer.
 */
public class Rectangle {
    /**
     * Enum.
     */
    public enum LinePosition {
        Undefined, Up, Down, Right, Left
    }
    private Point upperLeft;
    private Color color;
    private double width, height;

    /**
     * Constructor.
     * @param upperLeft - point
     * @param width - double
     * @param height - double
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.width = width;
        this.height = height;
        this.upperLeft = upperLeft;
        this.color = Color.BLACK;
    }
    // Create a new rectangle with location and width/height.
    /**
     * Constructor.
     * @param upperLeft - point
     * @param width - double
     * @param height - double
     * @param c - color
     */
    public Rectangle(Point upperLeft, double width, double height, Color c) {
        this.width = width;
        this.height = height;
        this.upperLeft = upperLeft;
        this.color = c;
    }
    /**
     * @param line - trajectory.
     * @return arg of collision points.
     */
    public List<Point> intersectionPoints(Line line) {
        List<Point> points = new ArrayList<Point>();
        Line line1 = new Line(upperLeft.getX(), upperLeft.getY(), upperLeft.getX() + this.width, upperLeft.getY());
        Line line2 = new Line(upperLeft.getX(), upperLeft.getY(), upperLeft.getX(), upperLeft.getY() + this.height);
        Line line3 = new Line(upperLeft.getX(), upperLeft.getY() + this.height, upperLeft.getX() + this.width,
                upperLeft.getY() + this.height); // down, horizontal
        Line line4 = new Line(upperLeft.getX() + this.width, upperLeft.getY(), upperLeft.getX() + this.width,
                upperLeft.getY() + this.height); // right, vertical

        if (line.isIntersecting(line1)) {
            points.add(line.intersectionWith(line1));
        }
        if (line.isIntersecting(line2)) {
            points.add(line.intersectionWith(line2));
        }
        if (line.isIntersecting(line3)) {
            points.add(line.intersectionWith(line3));
        }
        if (line.isIntersecting(line4)) {
            points.add(line.intersectionWith(line4));
        }
        return points;
    }
    /**
     * @return upper-left point.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }
    // Return the width and height of the rectangle
    /**
     * @return width.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * @return height.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * @return color.
     */
    public Color getColor() {
        return this.color;
    }
    /**
     * @param p - point
     * @return which side was hit.
     */
    public LinePosition getIntersectionLinePoisition(Point p) {
        Line upLine = new Line(upperLeft.getX(), upperLeft.getY(), upperLeft.getX() + this.width, upperLeft.getY());
        Line leftLine = new Line(upperLeft.getX(), upperLeft.getY(), upperLeft.getX(), upperLeft.getY() + this.height);
        Line downLine = new Line(upperLeft.getX(), upperLeft.getY() + this.height, upperLeft.getX() + this.width,
                upperLeft.getY() + this.height);
        Line rightLine = new Line(upperLeft.getX() + this.width, upperLeft.getY(), upperLeft.getX() + this.width,
                upperLeft.getY() + this.height);

        if (upLine.isPointOnTheLine(p)) {
            return LinePosition.Up;
        }

        if (downLine.isPointOnTheLine(p)) {
            return LinePosition.Down;
        }

        if (rightLine.isPointOnTheLine(p)) {
            return LinePosition.Right;
        }

        if (leftLine.isPointOnTheLine(p)) {
            return LinePosition.Left;
        }

        return LinePosition.Undefined;
    }

}