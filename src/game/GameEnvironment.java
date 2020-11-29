package game;

import hit.Collidable;
import hit.CollisionInfo;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;

import java.util.ArrayList;
import java.util.List;

/**
 * ass 05.
 * @author Itay Carmiel 208464198 <itaycar875@gmail.com>
 */
public class GameEnvironment {
    private ArrayList<Collidable> collidables;

    /**
     * array for collids.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<Collidable>();
    }
    /**
     * @param c add the collidable to the environment arr.
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }
    /**
     * @param c remove the collidable to the environment arr.
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }
    /**
     * @param trajectory - line which indicates the ball movement.
     * @return collision.CollisionInfo collision information - point and object.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        int indexOfCollidable = -1;
        Point close = null;
        Point coli;
        Rectangle colRect;
        for (int i = 0; i < collidables.size(); i++) {
            colRect = collidables.get(i).getCollisionRectangle();
            coli = trajectory.closestIntersectionToStartOfLine(colRect);
            if (coli != null) {
                if (close == null) {
                    indexOfCollidable = i;
                    close = coli;
                } else {
                    if (trajectory.start().distance(coli) < trajectory.start().distance(close)) {
                        indexOfCollidable = i;
                        close = coli;
                    }
                }
            }
        }
        if (close != null) {
            return new CollisionInfo(collidables.get(indexOfCollidable), close);
        }
        return null;
    }
    /**
     * returns the list of collidables.
     * @return the list of collidables.
     */
    public List<Collidable> getList() {
        return this.collidables;
    }
    /**
     * remove all collidables from environment.
     */
    public void deplete() {
        while (!collidables.isEmpty()) {
            collidables.remove(0);
        }
    }
}
