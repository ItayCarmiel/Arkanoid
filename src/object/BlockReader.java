package object;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.awt.Image;
import geometry.Rectangle;
import geometry.Point;

/**
 * ass 07.
 * @author Itay Carmiel 208464198 <itaycar875@gmail.com>
 */
public class BlockReader implements BlockCreator {
    private Map<Integer, Color> fillC;
    private Map<Integer, Image> fillImg;
    private double width;
    private double height;
    private java.awt.Color stroke;
    /**
     * Constructor.
     * @param width - the width of the block.
     * @param height - the height of the block.
     * @param fillC - map
     * @param fillImg - map
     * @param stroke - the borders color of the block.
     */
    public BlockReader(double width, double height, Map<Integer, Image> fillImg, Map<Integer, java.awt.Color> fillC,
                       java.awt.Color stroke) {
        this.width = width;
        this.height = height;
        this.stroke = stroke;
        this.fillC = new TreeMap<Integer, Color>();
        this.fillImg = new TreeMap<Integer, Image>();
        this.fillC.putAll(fillC);
        this.fillImg.putAll(fillImg);
    }
    /**
     * Constructor.
     * @param width - the width of the block.
     * @param height - the height of the block.
     * @param fillC - map
     * @param fillImg - map
     */
    public BlockReader(double width, double height,
                       Map<Integer, Image> fillImg, Map<Integer, java.awt.Color> fillC) {
        this.width = width;
        this.height = height;
        this.fillC = new TreeMap<Integer, Color>();
        this.fillImg = new TreeMap<Integer, Image>();
        this.fillC.putAll(fillC);
        this.fillImg.putAll(fillImg);
        this.stroke = null;
    }
    @Override
    public Block create(int xpos, int ypos) {
        Block b = new Block(new Rectangle(new Point(xpos, ypos),
                this.width, this.height));
        java.util.List<Color> color = new ArrayList<Color>();
        List<Image> image = new ArrayList<Image>();
        if (!fillC.isEmpty()) {
            color.add(this.fillC.get(1));
        }
        if (!fillImg.isEmpty()) {
            image.add(this.fillImg.get(1));
        }
        b.setColor(color);
        b.setImg(image);
        System.out.println(color.toString());
        b.setStroke(this.stroke);
        return b;
    }
}
