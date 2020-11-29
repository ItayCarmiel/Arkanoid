package hit;

import geometry.Point;

/**
 * ass 05.
 * @author Itay Carmiel 208464198 <itaycar875@gmail.com>
 */
public class CollisionInfo {
    private Collidable collisionObject;
    private Point collisionPoint;

    /**
     * @param collisionObject the object that been collided.
     * @param collisionPoint point of collision.
     */
    public CollisionInfo(Collidable collisionObject, Point collisionPoint) {
        this.collisionObject = collisionObject;
        this.collisionPoint = collisionPoint;
    }
    /**
     * @return the point which collision occur.
     */
    public Point getCollisionPoint() {
        return collisionPoint;
    }
    /**
     * @return the collidable involved in the collision.
     */
    public Collidable getCollisionObject() {
        return collisionObject;
    }
    /**
     * returns the collision point.
     *
     * @return this.collisionPoint point
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }
}