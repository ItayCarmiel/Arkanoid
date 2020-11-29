package geometry;

/**
 * ass 05.
 * @author Itay Carmiel 208464198 <itaycar875@gmail.com>
 */
public class Velocity {
    private double dx;
    private double dy;
    /**
     * constructor.
     * @param dx - the dx
     * @param dy - the dy
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }
    /**
     * @param x - double x
     */
    public void setDx(double x) {
        this.dx = x;
    }
    /**
     * @param y - double y
     */
    public void setDy(double y) {
        this.dy = y;
    }
    /**
     * @return - returns dx
     */
    public double getDx() {
        return dx;
    }
    /**
     * @return - returns dy
     */
    public double getDy() {
        return dy;
    }
    /**
     * Changes velocity of y by 180 degrees.
     */
    public void changeDyDirection() {
        this.dy = dy * (-1);
    }
    /**
     * Changes velocity of x by 180 degrees.
     */
    public void changeDxDirection() {
        this.dx = dx * (-1);
    }
    /**
     * Take a point with position (x,y) and return a new point
     * with position (x + dx, y + dy).
     * @param p - the point
     * @return new point with position (x + dx, y + dy)
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }
    /**
     * @param angle - the angle
     * @param speed - the speed
     * @return returns the velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.sin(Math.PI * (angle / 180));
        double dy = speed * Math.cos(Math.PI * (angle / 180));
        return new Velocity(dx, dy);
    }
}