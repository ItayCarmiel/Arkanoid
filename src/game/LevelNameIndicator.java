package game;

import biuoop.DrawSurface;
import geometry.Rectangle;

/**
 * ass 06.
 * @author Itay Carmiel 208464198 <itaycar875@gmail.com>
 */
public class LevelNameIndicator implements Sprite {
    private String s;
    private Rectangle rectangle;
    /**
     * @param rect of idicator
     * @param name of indicator
     */
    public LevelNameIndicator(Rectangle rect, String name) {
        this.s = name;
        this.rectangle = rect;
    }
    /**
     * Draws LivesIndicator block on surface.
     * @param d
     *            surface to draw.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(java.awt.Color.gray);
        d.drawText((int) this.rectangle.getUpperLeft().getX() + (int) this.rectangle.getWidth() * 3 / 4,
                (int) this.rectangle.getUpperLeft().getY() + 15, "Level Name: " + s, 15);
    }
    /**
     * @param game our game
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }
    /**
     * Every level we need to print only in the beggining (once).
     * So no need timePassed for this object.
     */
    @Override
    public void timePassed() {
    }
}
