package object;
/**
 * ass 07.
 * @author Itay Carmiel 208464198 <itaycar875@gmail.com>
 */
public interface BlockCreator {
    /**
     * Create a block at the specified location.
     * @param xpos the x position.
     * @param ypos the y position.
     * @return a new block according to the positions.
     */
    Block create(int xpos, int ypos);
}
