package object;
import java.util.HashMap;
import java.util.Map;
/**
 * ass 07.
 * @author Itay Carmiel 208464198 <itaycar875@gmail.com>
 */
public class BlocksFromSymbolsFactory {
    private Map<String, Double> spacerWidths;
    private Map<String, BlockCreator> blockCreator;
    /**
     * Constructor.
     */
    public BlocksFromSymbolsFactory() {
        Map<String, Double> sw = new HashMap<String, Double>();
        Map<String, BlockCreator> bc = new HashMap<String, BlockCreator>();
        this.spacerWidths = sw;
        this.blockCreator = bc;
    }
    /**
     * Returns the spacers widths map.
     * @return this spacers width map.
     */
    public Map<String, Double> getSpacersWidths() {
        return this.spacerWidths;
    }
    /**
     * Returns the block creator map.
     * @return this block creator map.
     */
    public Map<String, BlockCreator> getBlockCreator() {
        return this.blockCreator;
    }
    /**
     * Returns true if 's' is a valid space symbol.
     * @param s our string.
     * @return true or false.
     */
    public boolean isSpaceSymbol(String s) {
        return spacerWidths.containsKey(s);
    }
    /**
     * Returns true if 's' is a valid block symbol.
     * @param s our string.
     * @return true or false.
     */
    public boolean isBlockSymbol(String s) {
        return blockCreator.containsKey(s);
    }
    /**
     * Returns the int which belongs to the space symbol.
     * @param s  - spacer.
     * @return the spacers integer value.
     */
    public double getSpaceWidth(String s) {
        return this.spacerWidths.get(s);
    }
    /**
     * Creates a block according to the symbol it gets.
     * @param s the symbol.
     * @param x the x coordinate of the point.
     * @param y the y coordinate of the point.
     * @return returns a new block.
     */
    public Block getBlock(String s, int x, int y) {
        return this.blockCreator.get(s).create(x, y);
    }
}
