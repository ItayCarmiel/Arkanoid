package game;

import java.io.Serializable;

/**
 * ass 07.
 * @author Itay Carmiel 208464198 <itaycar875@gmail.com>
 */
    public class ScoreInfo implements Serializable {
        private int score;
        /**
         * constructor.
         * @param score the score of the player
         */
        public ScoreInfo(int score) {
            this.score = score;
        }
        /**
         *
         * @return the score
         */
        public int getScore() {
            return this.score;
        }
        /**
         * Nice string representation for the address.
         *
         * @return string representation
         */
        public String toString() {
            return "ScoreInfo{"
                    + "score='" + this.score + '\''
                    + '}';
        }
}
