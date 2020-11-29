package geometry;

import java.util.List;

/**
 * ass 05.
 * @author Itay Carmiel 208464198 <itaycar875@gmail.com>
 */
public class Line {
    // Fields
    private Point start;
    private Point end;
    /**
     * @param start - the start point
     * @param end - the end point
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }
    /**
     * @param x1 - the x of the start point
     * @param y1 - the y of the start point
     * @param x2 - the x of the end point
     * @param y2 - the y of the end point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }
    /**
     * @return - returns the length of the line
     */
    public double length() {
        double dx = start.getX() - end.getX();
        double dy = start.getY() - end.getY();
        return Math.sqrt((dx * dx) + (dy * dy));
    }
    /**
     * @return the middle point of the line
     */
    public Point middle() {
        double midX = (start.getX() + end.getX()) / 2;
        double midY = (start.getY() + end.getY()) / 2;
        Point middle = new Point(midX, midY);
        return middle;
    }

    /**
     * @return - returns the start point
     */
    public Point start() {
        return this.start;
    }
    /**
     * @return - returns the end point
     */
    public Point end() {
        return this.end;
    }
    /**
     * @param x1 - first point x
     * @param y1 - first point y
     * @param x2 - second point x
     * @param y2 - second point y
     * @param compX - comparable point x
     * @param compY - comparable point y
     * @return relative poisition of the comparable point. for example: 1 is under line, -1 is above line, 0 is on line.
     */
    protected int getRelativeLocation(double x1, double y1, double x2, double y2, double compX, double compY) {
        double relativeLoc;
        //this var will contain a number that represent a relative location of the point compare to the line
        x2 -= x1;
        y2 -= y1;
        compX -= x1;
        compY -= y1;
        relativeLoc = compX * y2 - compY * x2; //get relative location
        if (relativeLoc == 0.0) { //calc the location on the line, which side the point falls.
            relativeLoc = compX * x2 + compY * y2; //reverse value
            if (relativeLoc > 0.0) { //reverse projection of the point and check again
                compX -= x2;
                compY -= y2;
                relativeLoc = compX * x2 + compY * y2;
                if (relativeLoc < 0.0) { //in the middle
                    relativeLoc = 0.0;
                }
            }
        }
        if (relativeLoc < 0) { //point is above line
            return -1;
        } else if (relativeLoc > 0) { //point is under line
            return 1;
        } else {
            return 0; //point is on line
        }
    }
    /**
     * @param other - another line
     * @return true of false
     */
    public boolean isIntersecting(Line other) {
        //relative location of current line and start point of second line
        int mult1 = getRelativeLocation(this.start.getX(), this.start.getY(),
                this.end.getX(), this.end.getY(), other.start.getX(), other.start.getY());
        //relative location of current line and end point of second line
        int mult2 = getRelativeLocation(this.start.getX(), this.start.getY(),
                this.end.getX(), this.end.getY(), other.end.getX(), other.end.getY());
        //relative location of current start point and second line
        int mult3 = getRelativeLocation(other.start.getX(), other.start.getY(),
                other.end.getX(), other.end.getY(), this.start.getX(), this.start.getY());
        //relative location of current end point and second line
        int mult4 = getRelativeLocation(other.start.getX(), other.start.getY(),
                other.end.getX(), other.end.getY(), this.end.getX(), this.end.getY());
        //if true, calculated by relative positions of the lines' points, then the lines are intersected.
        if ((mult1 * mult2 <= 0) && (mult3 * mult4 <= 0)) {
            return true;
        }
        return false;
    }
    /**
     * public Geometry.Point intersectionWith.
     * <p>
     * This method check if two lines are Intersecting.
     * <p>
     * @param other - another line to check if intersected with current line.
     * @return the intersection point if the lines intersect,and null otherwise.
     */

    public Point intersectionWith(Line other) {
        double a = this.end.getY() - this.start.getY();
        double b = this.start.getX() - this.end.getX();
        double a2 = other.end.getY() - other.start.getY();
        double b2 = other.start.getX() - other.end.getX();
        double c = a * this.start.getX() + b * this.start.getY();
        double c2 = a2 * other.start.getX() + b2 * other.start.getY();
        double det = a * b2 - a2 * b;
        double x = (b2 * c - b * c2) / det;
        double y = (a * c2 - a2 * c) / det;
        Point intersection = new Point(x, y);
        return intersection;
    }
    /**
     * @param other - other line
     * @return return true if the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        if ((this.start.equals(other.start)) && (this.end.equals(other.end))) {
            return true;
        }
        return false;
    }
    /**
     * @param rec collision rectangle.
     * @return closest collision point.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rec) {
        List<Point> points = rec.intersectionPoints(this);
        if (points.isEmpty()) {
            return null;
        }
        double minDist = this.start.distance(points.get(0));
        int closestIntersection = 0;
        for (int i = 1; i < points.size(); i++) {
            double tempDist = this.start.distance(points.get(i));
            if (tempDist < minDist) {
                minDist = tempDist;
                closestIntersection = i;
            }
        }
        return points.get(closestIntersection);
    }
    /**
     * @param p point.
     * @return true or false.
     */
    public boolean isPointOnTheLine(Point p) {
        if (p.distance(start) + p.distance(end) == start.distance(end)) {
            return true;
        }
        return false;
    }
}