package game;

import biuoop.DrawSurface;

/**
 * ass 05.
 * @author Itay Carmiel 208464198 <itaycar875@gmail.com>
 */
public interface Sprite {
    /**
     * @param d where we draw
     */
    void drawOn(DrawSurface d);
    /**
     * notify the sprite that time has passed.
     */
    void timePassed();
    /**
     * Adds the sprite to the game.
     *
     * @param g game
     */
    void addToGame(GameLevel g);
}