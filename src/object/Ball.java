package object;

import biuoop.DrawSurface;
import game.GameEnvironment;
import game.Sprite;
import geometry.Point;
import geometry.Velocity;
import game.GameLevel;
import hit.CollisionInfo;
import geometry.Line;

/**
 * ass 05.
 * @author Itay Carmiel 208464198 <itaycar875@gmail.com>
 */
public class Ball implements Sprite {
    private Point center;
    private int r;
    private java.awt.Color color;
    private Velocity vel;
    private int hight;
    private int width;
    private static GameEnvironment gameEnvironment = null;

    /**
     * constructor.
     * @param center - point center
     * @param r - radius
     * @param color - color of the ball
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
    }
    /**
     * constructor.
     * @param x - x of center
     * @param y - y of center
     * @param r - radius
     * @param color - color of the ball
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
    }
    /**
     * @return - returns the x of the center
     */
    public double getX() {
        return (double) this.center.getX();
    }
    /**
     * @return - returns the y of the center
     */
    public double getY() {
        return (double) this.center.getX();
    }
    /**
     * @return - returns the radius ball
     */
    public int getSize() {
        return this.r;
    }
    /**
     * @return - returns the color of the ball
     */
    public java.awt.Color getColor() {
        return this.color;
    }
    /**
     * @param surface - the surface that we draw on
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(java.awt.Color.black);
        surface.drawCircle((int) this.center.getX(),
                (int) this.center.getY(), this.r);
        surface.setColor(this.color);
        surface.fillCircle((int) this.center.getX(),
                (int) this.center.getY(), this.r);
    }
    /**
     * add ball to game.
     * @param g is our game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
    /**
     * remove from game this ball.
     * @param g our game.
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }

    @Override
    public void timePassed() {
        moveOneStep();
    }

    /**
     * @param surface - draw
     * @param hight1 - int
     * @param width1 - int
     */
    public void drawOnNew(DrawSurface surface, int hight1, int width1) {
        surface.drawCircle((int) this.center.getX(), (int) this.center.getY(), this.r);
        this.hight = hight1;
        this.width = width1;
        surface.setColor(this.color);
        surface.fillCircle((int) this.center.getX(),
                (int) this.center.getY(), this.r);
    }
    /**
     * set velocity.
     * @param v - velocity
     */
    public void setVelocity(Velocity v) {
        this.vel = v;
    }
    /**
     * set velocity.
     * @param dx - dx
     * @param dy - dy
     */
    public void setVelocity(double dx, double dy) {
        this.vel = new Velocity(dx, dy);
    }
    /**
     * @return - returns velocity
     */
    public Velocity getVelocity() {
        return vel;
    }
    /**
     * @param maxX - draw
     * @param maxY - int
     * @param minX - int
     * @param minY - int
     */
    public void moveOneStep(int maxX, int maxY, int minX, int minY) {
        if (this.vel != null) {
            Point point = this.vel.applyToPoint(this.center);
            //exception in the right side of the window (maxX).
            if (point.getX() + this.getSize() >= maxX) {
                if (this.vel.getDx() >= 0) {
                    this.vel.setDx(-this.vel.getDx());
                }
            }
            //exception in the left side of the window (minX).
            if (point.getX() - this.getSize() <= minX) {
                if (this.vel.getDx() <= 0) {
                    this.vel.setDx(-(this.vel.getDx()));
                }
            }
            //exception in the bottom side of the window (maxY).
            if (point.getY() + this.getSize() >= maxY) {
                if (this.vel.getDy() >= 0) {
                    this.vel.setDy(-this.vel.getDy());
                }
            }
            //exception in the top side of the window (minY).
            if (point.getY() - this.getSize() <= minY) {
                if (this.vel.getDy() <= 0) {
                    this.vel.setDy(-(this.vel.getDy()));
                }
            }
            //change the point according to all the changes.
            this.center = this.getVelocity().applyToPoint(this.center);
        }
    }
    /**
     * step of the ball.
     */
    public void moveOneStep() {
        int nextX, nextY;
        nextX = (int) this.getVelocity().applyToPoint(this.center).getX();
        nextY = (int) this.getVelocity().applyToPoint(this.center).getY();
        for (int i = 0; i < 1; i++) {
            nextX = (int) this.getVelocity().applyToPoint(new Point(nextX, nextY)).getX();
            nextY = (int) this.getVelocity().applyToPoint(new Point(nextX, nextY)).getY();
        }
        Line trajectory = new Line(this.center, new Point(nextX, nextY));
        CollisionInfo collided = Ball.gameEnvironment.getClosestCollision(trajectory);
        if (collided != null) { //collision!
            this.setVelocity(collided.getCollisionObject().hit(this, collided.getCollisionPoint(), this.vel));
            moveOneStepWithoutAlmostCollision();
        } else {
            //move the point one step.
            this.center = this.getVelocity().applyToPoint(this.center);
        }
    }
    /**
     * similar to the above method, but here we don't calculate the future appliance because we may have,
     * two collisions consequently.
     */
    public void moveOneStepWithoutAlmostCollision() {
        this.center = this.getVelocity().applyToPoint(this.center);
        int nextX, nextY;
        nextX = (int) this.getVelocity().applyToPoint(this.center).getX();
        nextY = (int) this.getVelocity().applyToPoint(this.center).getY();
        Line trajectory = new Line(this.center, new Point(nextX, nextY));
        CollisionInfo collided = Ball.gameEnvironment.getClosestCollision(trajectory);
        if (collided != null) { //collision!
            this.setVelocity(collided.getCollisionObject().hit(this, collided.getCollisionPoint(), this.vel));
            moveOneStepWithoutAlmostCollision();
        } else {
            //move the point one step.
            this.center = this.getVelocity().applyToPoint(this.center);
        }
    }
    /**
     * if environment wasn't created then create and return it, otherwise just return it.
     * @return ball environment
     */
    public static GameEnvironment getGameEnvironment() {
        if (gameEnvironment == null) {
            gameEnvironment = new GameEnvironment();
        }
        return Ball.gameEnvironment;
    }
}