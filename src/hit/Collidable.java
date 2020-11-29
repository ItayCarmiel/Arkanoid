package hit;

import object.Ball;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import game.GameLevel;

/**
 * ass 05.
 * @author Itay Carmiel 208464198 <itaycar875@gmail.com>
 */
public interface Collidable {
    /**
     * @return collision rectangle
     */
    Rectangle getCollisionRectangle();
    /**
     * @param collisionPoint where object was hit.
     * @param currentVelocity velocity of the ball when hitting.
     * @param hitter the hitting ball.
     * @return velocity after hit.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
    /**
     * Adds the sprite to the game.
     *
     * @param g game
     */
    void addToGame(GameLevel g);
}