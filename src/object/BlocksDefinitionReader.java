package object;
import java.awt.Image;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.HashMap;
import java.util.Map;
/**
 * ass 07.
 * @author Itay Carmiel 208464198 <itaycar875@gmail.com>
 */
public class BlocksDefinitionReader {
    private Map<Integer, java.awt.Color> dfillC;
    private Map<Integer, Image> dfillImg;
    private double dheight;
    private double dwidth;
    private java.awt.Color dstroke;
    /**
     * Constructor.
     */
    public BlocksDefinitionReader() {
        Map<Integer, java.awt.Color> cf =
                new HashMap<Integer, java.awt.Color>();
        Map<Integer, Image> fi = new HashMap<Integer, Image>();
        this.dfillC = cf;
        this.dfillImg = fi;
        this.dheight = 0;
        this.dwidth = 0;
        this.dstroke = null;
    }
    /**
     * Returns a BlocksFromSymbolFactory object according to the file
     * it receives.
     * @param reader io object
     * @return blocksFSF
     * @throws Exception throws.
     */
    public BlocksFromSymbolsFactory fromReader(LineNumberReader reader)
            throws Exception {
        try {
            Map<Integer, java.awt.Color> fillC =
                    new HashMap<Integer, java.awt.Color>();
            Map<Integer, Image> fillImg = new HashMap<Integer, Image>();
            BlocksFromSymbolsFactory blocksFSF = new BlocksFromSymbolsFactory();
            String line;
            double height = 0, width = 0;
            String symbol = null;
            java.awt.Color stroke = null;
            while ((line = reader.readLine()) != null) {
                if (line.equals("")) {
                    continue;
                }
                if (line.contains("#")) {
                    continue;
                }
                if (line.contains("default")) {
                    DefinitionsFromText.readFromTextd(line);
                    this.dwidth = DefinitionsFromText.getWidth();
                    this.dheight = DefinitionsFromText.getHeight();
                    this.dstroke = DefinitionsFromText.getStroke();
                    this.dfillC = DefinitionsFromText.getFillC();
                    this.dfillImg = DefinitionsFromText.getFillImg();
                }
                if (line.contains("bdef symbol")) {
                    DefinitionsFromText.readFromTextsd(line);
                    width = DefinitionsFromText.getWidth();
                    height = DefinitionsFromText.getHeight();
                    stroke = DefinitionsFromText.getStroke();
                    fillC = DefinitionsFromText.getFillC();
                    fillImg = DefinitionsFromText.getFillImg();
                    symbol = DefinitionsFromText.getSymbol();
                    if (width == 0) {
                        width = dwidth;
                    }
                    if (height == 0) {
                        height = dheight;
                    }
                    if (stroke == null) {
                        stroke = dstroke;
                    }
                    if (fillC.isEmpty()) {
                        fillC = this.dfillC;
                    }
                    if (fillImg.isEmpty()) {
                        fillImg = this.dfillImg;
                    }
                    if (fillImg == null && fillC == null) {
                        throw new Exception("No fill was included");
                    }
                    if (!fillC.isEmpty()) {
                        java.awt.Color col = fillC.get(1);
                    }
                    System.out.println(fillC.toString());
                    if (!fillImg.isEmpty()) {
                        Image im = fillImg.get(1);
                    }
                    if (width != 0 && height != 0) {
                        BlockReader blockReader;
                        if (stroke != null) {
                            blockReader = new BlockReader(width,
                                    height, fillImg, fillC, stroke);
                        } else {
                            blockReader = new BlockReader(width,
                                    height, fillImg, fillC);
                        }
                        blocksFSF.getBlockCreator().put(symbol, blockReader);
                        height = 0;
                        width = 0;
                        symbol = null;
                        stroke = null;
                        fillC = new HashMap<Integer, java.awt.Color>();
                        fillImg = new HashMap<Integer, Image>();
                    } else {
                        throw new Exception("Block wasn't defined well");
                    }
                }
                if (line.contains("sdef symbol:")) {
                    String[] parts3 = line.split("sdef symbol:");
                    String part4 = parts3[1];
                    String[] parts4 = part4.split(" ");
                    symbol = parts4[0];
                    String[] parts5 = parts4[1].split(":");
                    if (parts5[0].equals("width")) {
                        blocksFSF.getSpacersWidths().put(symbol,
                                Double.parseDouble(parts5[1]));
                    } else {
                        throw new Exception("Wrong spacer");
                    }
                }
            }
            return blocksFSF;
        } catch (IOException e) {
            System.out.println("Something went wrong while reading");
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.out.println("Failed closing the file!");
                }
            }
        }
        return null;
    }
}
