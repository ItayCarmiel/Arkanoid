package game;

import biuoop.DrawSurface;
/**
 * ass 07.
 * @author Itay Carmiel 208464198 <itaycar875@gmail.com>
 */
public class ColorBackGround implements Sprite {
    private java.awt.Color color;
    /**
     * Constructor.
     * @param c the color of the background/
     */
    public ColorBackGround(java.awt.Color c) {
        this.color = c;
    }
    /**
     * FIlls the background with the given color.
     * @param d our surface.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle(0, 0, 800, 600);
    }

    @Override
    public void timePassed() {
    }
    /**
     * Adds this sprite to the game.
     * @param g the game level we add the sprite to.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
    /**
     * @return the color of the background
     */
    public java.awt.Color getColorBackGround() {
        return this.color;
    }
}
