package game;

import biuoop.DrawSurface;
import java.awt.Image;

/**
 * ass 07.
 * @author Itay Carmiel 208464198 <itaycar875@gmail.com>
 */
public class Images implements Sprite {
    private Image img;
    /**
     * Constructor.
     * @param img the image we receive from the file.
     */
    public Images(Image img) {
        this.img = img;
    }
    @Override
    public void drawOn(DrawSurface d)  {
        d.drawImage(0, 25, this.img);
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
