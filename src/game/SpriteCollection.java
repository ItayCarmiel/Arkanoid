package game;

import java.util.ArrayList;
import biuoop.DrawSurface;

/**
 * ass 05.
 * @author Itay Carmiel 208464198 <itaycar875@gmail.com>
 */
public class SpriteCollection {
    private ArrayList<Sprite> sprites;
    /**
     * Constructor.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<Sprite>();
    }
    /**
     * Method to add sprites to collection.
     * @param s a sprite to add.
     */
    public void addSprite(Sprite s) {
            this.sprites.add(s);
    }
    /**
     * Method to remove sprites to collection.
     * @param s a sprite to remove.
     */
    public void removeSprite(Sprite s) {
        this.sprites.remove(s);
    }
    /**
     * Call timePassed.
     */
    public void notifyAllTimePassed() {
        for (int i = 0; i < sprites.size(); i++) {
            this.sprites.get(i).timePassed();
        }
    }
    /**
     * @param d where we draw.
     */
    public void drawAllOn(DrawSurface d) {
        for (int i = 0; i < sprites.size(); i++) {
            this.sprites.get(i).drawOn(d);
        }
    }
    /**
     * Remove all elements (sprites) from collection.
     */
    public void deplete() {
        while (!sprites.isEmpty()) {
            sprites.remove(0);
        }
    }
}