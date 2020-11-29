package object;

import hit.Collidable;
import game.Sprite;
import geometry.Point;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import geometry.Rectangle;
import geometry.Velocity;
import game.GameLevel;

/**
 * ass 05.
 * @author Itay Carmiel 208464198 <itaycar875@gmail.com>
 */
    public class Paddle implements Sprite, Collidable {
        private Rectangle rectangle;
        private int speed = 10;
        private KeyboardSensor keyboard;
        private java.awt.Color color;
        /**
         * @param rect - geometry.Rectangle.
         */
         public Paddle(Rectangle rect) {
           this.rectangle = rect;
        }
    /**
     * @param upperLeft - point.
     * @param width - double
     * @param height - double
     * @param color - color
     * @param keyboard - keyboard
     */
    public Paddle(Point upperLeft, double width, double height, java.awt.Color color, KeyboardSensor keyboard) {
        this.rectangle = new Rectangle(upperLeft, width, height, color);
        this.color = color;
        this.keyboard = keyboard;
    }
    /**
     * @return color of the paddle.
     */
    public java.awt.Color getColor() {
        return this.color;
    }
    /**
     * moves the paddle left.
     */
    public void moveLeft() {
        if (this.rectangle.getUpperLeft().getX() > 30) {
            this.rectangle = new Rectangle(
                    new Point(this.rectangle.getUpperLeft().getX() - speed, this.rectangle.getUpperLeft().getY()),
                    this.rectangle.getWidth(), this.rectangle.getHeight(), color);
        }
    }
    /**
     * moves the paddle right.
     */
    public void moveRight() {
        if (this.rectangle.getUpperLeft().getX() + this.rectangle.getWidth() < 770) {
            this.rectangle = new Rectangle(
                    new Point(this.rectangle.getUpperLeft().getX() + speed, this.rectangle.getUpperLeft().getY()),
                    this.rectangle.getWidth(), this.rectangle.getHeight(), color);
        }
    }
    /**
     * @param d where we draw.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.getColor());
        d.fillRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
    }

    /**
     * timePassed.
     */
    public void timePassed() {
        if (!(keyboard.isPressed(KeyboardSensor.LEFT_KEY)) && (keyboard.isPressed(KeyboardSensor.RIGHT_KEY))) {
            moveRight();
        }
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY) && !(keyboard.isPressed(KeyboardSensor.RIGHT_KEY))) {
            moveLeft();
        }
    }
    /**
     * @param hitter the hitting ball.
     * @param collisionPoint where object was hit.
     * @param currentVelocity velocity of the ball when hitting.
     * @return velocity.
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double part = (this.rectangle.getWidth() / 5);
        double x = this.rectangle.getUpperLeft().getX();
        double po = collisionPoint.getX();
        double rou = Math.round(po);
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        double mySpeed = Math.sqrt(dx * dx + dy * dy);
        if (rou <= (x + part) && (rou >= x)) {
            currentVelocity = Velocity.fromAngleAndSpeed(240, mySpeed);
        } else if ((rou <= (x + (2 * part))) && (rou > (x + part))) {
            currentVelocity = Velocity.fromAngleAndSpeed(210, mySpeed);
        } else if ((rou <= (x + (3 * part))) && (rou > (x + (2 * part)))) {
            currentVelocity = new Velocity(Math.round(dx), Math.round(currentVelocity.getDy()) * -1);
        } else if ((rou <= (x + (4 * part))) && (rou > (x + (3 * part)))) {
            currentVelocity = Velocity.fromAngleAndSpeed(150, mySpeed);
        } else if ((rou <= (x + (5 * part))) && (rou > (x + (4 * part)))) {
            currentVelocity = Velocity.fromAngleAndSpeed(120, mySpeed);
        }
        return currentVelocity;
    }
    /**
     * @return rectangle.
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }
    /**
     * @param v - speed.
     */
    public void setSpeed(int v) {
        this.speed = v;
    }
    /**
     * @param g - game.
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
    /**
     * this func will remove the paddle to the game.
     * @param game to remove the paddle from
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }
}
