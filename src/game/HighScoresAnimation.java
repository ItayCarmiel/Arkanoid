package game;
import biuoop.DrawSurface;
import object.Block;

import java.awt.Color;
import java.util.List;

/**
 * ass 06.
 * @author Itay Carmiel 208464198 <itaycar875@gmail.com>
 */
public class HighScoresAnimation implements Animation {
    private HighScores score;
    private Block background;
    private Color colorScore;
    /**
     * constructor.
     * @param scores the scores
     */
    public HighScoresAnimation(HighScores scores) {
        this.score = scores;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        colorScore = new Color(100, 150, 250);
        ColorBackGround cb = new ColorBackGround(colorScore);
        cb.drawOn(d);
        d.setColor(Color.BLACK);
        List<ScoreInfo> l = this.score.getHighScores();
        if (l.size() == 1) {
            d.drawText(200, 300, "The highest score so far is: " + l.get(0).getScore(), 32);
        }
        d.drawText(300, 40, "High Scores", 40);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
