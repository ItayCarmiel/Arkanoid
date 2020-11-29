package object;

import java.awt.Color;
import java.util.ArrayList;
import java.awt.Image;
import java.util.List;

import hit.Collidable;
import game.Sprite;
import geometry.Point;
import biuoop.DrawSurface;
import geometry.Rectangle;
import geometry.Velocity;
import game.GameLevel;
import hit.HitListener;
import hit.HitNotifier;

/**
 * ass 05.
 * @author Itay Carmiel 208464198 <itaycar875@gmail.com>
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rect;
    private ArrayList<HitListener> hitListeners;
    private List<Image> image;
    private java.awt.Color stroke;
    private List<java.awt.Color> color;

    /**
     * @param rect
     *            rectangle
    */
    public Block(Rectangle rect) {
        this.rect = rect;
        this.hitListeners = new ArrayList<HitListener>();
        this.color = new ArrayList<Color>();
        this.image = new ArrayList<Image>();
    }

    /**
     * @param point
     *            location
     * @param width
     *            of block
     * @param height
     *            of block
     * @param color
     *            of block
     */
    public Block(Point point, int width, int height, Color color) {
        rect = new Rectangle(point, width, height, color);
        this.hitListeners = new ArrayList<HitListener>();
    }

    @Override
    /**
     * @return this rect
     */
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * @param collisionPoint
     *            collision point where there was a hit of block or paddle or
     *            wall (block too).
     * @param currentVelocity
     *            velocity before the collision.
     * @param hitter the hitting ball.
     * @return new velocity after hit.
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Rectangle.LinePosition side = this.rect.getIntersectionLinePoisition(collisionPoint);
        if (side == Rectangle.LinePosition.Down || side == Rectangle.LinePosition.Up) {
            currentVelocity.changeDyDirection();
        }
        if (side == Rectangle.LinePosition.Left || side == Rectangle.LinePosition.Right) {
            currentVelocity.changeDxDirection();
        }
        this.notifyHit(hitter);
        return currentVelocity;
    }

    /**
     * @param d - surface
     */
    public void drawOn(DrawSurface d) {
        if (!this.color.isEmpty()) {
            d.setColor(this.color.get(0));
            d.fillRectangle((int) this.rect.getUpperLeft().getX(),
                    (int) this.rect.getUpperLeft().getY(),
                    (int) (this.rect.getWidth()), (int) (this.rect.getHeight()));
        } else if (!this.image.isEmpty()) {
            Image image1 = this.image.get(0);
            d.drawImage((int) this.rect.getUpperLeft().getX(),
                    (int) this.rect.getUpperLeft().getY(),
                    image1);
        }
        if (this.stroke != null) {
            d.setColor(stroke);
            d.drawRectangle((int) this.rect.getUpperLeft().getX(),
                    (int) this.rect.getUpperLeft().getY(),
                    (int) (this.rect.getWidth()), (int) (this.rect.getHeight()));
        }
    }
    /**
     * @param g - game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
    /**
     * @param g - game
     */
    public void removeFromGame(GameLevel g) {
        g.removeCollidable(this);
        g.removeSprite(this);
    }
    @Override
     public void timePassed() {
    }
    /**
     * @param hitter hitting ball.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        ArrayList<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
    /**
     * Sets the blocks color to the color given.
     * @param color1 a color.
     */
    public void setColor(java.awt.Color color1) {
        this.color.add(color1);
    }
    /**
     * Sets this stroke.
     * @param s our new stroke.
     */
    public void setStroke(java.awt.Color s) {
        this.stroke = s;
    }
    /**
     * set color.
     *
     * @param newColor
     *            a new color
     */
    public void setColor(List<java.awt.Color> newColor) {
        this.color.addAll(newColor);
    }
    /**
     * Sets our image list.
     * @param newImg the new image list.
     */
    public void setImg(List<Image> newImg) {
        this.image.addAll(newImg);
    }
    /**
     * Returns the blocks width.
     * @return width.
     */
    public int getWidth() {
        return (int) this.rect.getWidth();
    }
}