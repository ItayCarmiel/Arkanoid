package geometry;

import java.util.List;
import hit.CollisionInfo;

/**
 * ass 05.
 * @author Itay Carmiel 208464198 <itaycar875@gmail.com>
 */
public class Point {
    private double x;
    private double y;
    /**
     * @param x - the x of a point
     * @param y - the y of a point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    /**
     * calculate the distance between two points.
     *
     * @param other point
     * @return 'double' distance between the two points
     */
    public double distance(Point other) {
        double dx = this.x - other.getX();
        double dy = this.y - other.getY();
        return Math.sqrt((dx * dx) + (dy * dy));
    }
    /**
     * compare two points.
     *
     * @param other point
     * @return 'true' is the points are equal, 'false' otherwise
     */
    public boolean equals(Point other) {
        if ((this.x == other.getX()) && (this.y == other.getY())) {
            return true;
        }
        return false;
    }
    /** return value of point.
     *
     * @return value of x
     */
    public double getX() {
        return this.x;
    }
    /**
     * return value of point.
     *
     * @return value of y
     */
    public double getY() {
        return this.y;
    }
    /**
     * @param newX - double x
     */
    public void setX(double newX) {
        this.x = newX;
    }
    /**
     * @param newY - double y
     */
    public void setY(double newY) {
        this.y = newY;
    }
    /**
     * checks whats the closest collision point to a point.
     * @param colList List<CollissionInfo>
     * @return Geometry.Point closest
     */
    public CollisionInfo closestCollision(List<CollisionInfo> colList) {
        CollisionInfo closest = colList.get(0);
        int i = 1;
        while (i < colList.size()) {
            if (this.distance(colList.get(i).collisionPoint()) < this.distance(closest.collisionPoint())) {
                closest = colList.get(i);
            }
            i++;
        }
        return closest;
    }
}