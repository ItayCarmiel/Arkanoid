package game;

import biuoop.DrawSurface;

/**
 * ass 06.
 * @author Itay Carmiel 208464198 <itaycar875@gmail.com>
 */
public interface Animation {
    /**
     * @param d drawing surface.
     */
    void doOneFrame(DrawSurface d);
    /**
     * @return boolean if we should stop.
     */
    boolean shouldStop();
}
