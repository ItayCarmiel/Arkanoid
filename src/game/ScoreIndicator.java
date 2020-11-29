package game;

import biuoop.DrawSurface;
import geometry.Rectangle;

/**
 * ass 05.
 * @author Itay Carmiel 208464198 <itaycar875@gmail.com>
 */
public class ScoreIndicator implements Sprite {
    private Counter count;
    private Rectangle rectangle;
    /**
     * constructor.
     * @param rect rectangle of score.
     * @param score the score.
     */
    public ScoreIndicator(Rectangle rect, Counter score) {
        this.count = score;
        this.rectangle = rect;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(java.awt.Color.lightGray);
        d.fillRectangle(0, 0, 800, 25);
        d.setColor(java.awt.Color.gray);
        String livesDisplay = String.valueOf(this.count.getValue());
        d.drawText((int) this.rectangle.getUpperLeft().getX() + (int) this.rectangle.getWidth() / 2 - 25,
                (int) this.rectangle.getUpperLeft().getY() + 15, "Score: " + livesDisplay, 15);
    }
    @Override
    public void timePassed() {
        // just to fill.
    }
    /**
     * Add this indicator to our game.
     * @param g our game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
