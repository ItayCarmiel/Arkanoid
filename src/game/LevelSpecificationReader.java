package game;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;
import object.Block;
import object.BlocksFromSymbolsFactory;
import object.BlocksDefinitionReader;
import object.ColorsParser;
import geometry.Velocity;
import javax.imageio.ImageIO;
/**
 * ass 07.
 * @author Itay Carmiel 208464198 <itaycar875@gmail.com>
 */
public class LevelSpecificationReader {
    /**
     * Receives a text from which it builds levels.
     * @param reader a text file.
     * @return a list of levels.
     * @throws Exception if read file isn't correct
     */
    public static List<LevelInformation> fromReader(LineNumberReader reader)
            throws Exception {
        List<LevelInformation> liList = new ArrayList<LevelInformation>();
        try {
            BlocksFromSymbolsFactory blocksFSF = null;
            String line = new String();
            LevelGeneric l = new LevelGeneric();
            int blocksStartX = 0, blocksStartY = 0, rowHeight = 0;
            while ((line = reader.readLine()) != null) {
                blocksFSF = null;
                l = new LevelGeneric();
                blocksStartX = 0;
                blocksStartY = 0;
                rowHeight = 0;
                System.out.println(line);
                if (line.equals("")) {
                    continue;
                }
                if (line.equals("START_LEVEL")) {
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line);
                        if (line.equals("")) {
                            continue;
                        }
                        if (line.contains(":")) {
                            String[] parts = line.split(":");
                            String part1 = parts[0];
                            String part2 = parts[1];
                            if (part1.equals("level_name")) {
                                l.setLevelName(part2);
                            }
                            if (part1.equals("ball_velocities")) {
                                for (String vertal : part2.split(" ")) {
                                    String[] parts3 = vertal.split(",");
                                    Velocity v = new Velocity(5, 5);
                                    l.addVelocity(
                                            v.fromAngleAndSpeed(Double.parseDouble(parts3[0]),
                                                    Double.parseDouble(parts3[1])));
                                }
                            }
                            if (part1.equals("background")) {
                                if (!part2.contains("color")) {
                                    String[] str = part2.split("\\(");
                                    String[] str2 = str[1].split("\\)");
                                    InputStream is = ClassLoader.
                                            getSystemClassLoader().
                                            getResourceAsStream(str2[0]);
                                    Image img = ImageIO.read(is);
                                    Images img2 = new Images(img);
                                    l.setBackGround(img2);
                                } else {
                                    // background will be a color
                                    ColorBackGround cbg = new ColorBackGround(
                                            ColorsParser.colorFromString(part2));
                                    l.setBackGround(cbg);
                                }
                            }
                            if (part1.equals("paddle_speed")) {
                                l.setPaddleSpeed(Integer.parseInt(part2));
                            }
                            if (part1.equals("paddle_width")) {
                                l.setPaddleWidth(Integer.parseInt(part2));
                            }
                            if (part1.equals("block_definitions")) {
                                BlocksDefinitionReader blocksDR = new
                                        BlocksDefinitionReader();
                                InputStream is = ClassLoader.
                                        getSystemClassLoader().
                                        getResourceAsStream(part2);
                                InputStreamReader newReader =
                                        new InputStreamReader(is);
                                LineNumberReader lineNR =
                                        new LineNumberReader(newReader);
                                blocksFSF = blocksDR.fromReader(lineNR);
                            }
                            if (part1.equals("blocks_start_x")) {
                                blocksStartX = Integer.parseInt(part2);
                            }
                            if (part1.equals("blocks_start_y")) {
                                blocksStartY = Integer.parseInt(part2);
                            }
                            if (part1.equals("row_height")) {
                                rowHeight = Integer.parseInt(part2);
                            }
                        } else {
                            if (line.equals("START_BLOCKS")) {
                                int x = blocksStartX, y = blocksStartY;
                                while ((line = reader.readLine()) != null) {
                                    if (line.equals("END_BLOCKS")) {
                                        break;
                                    }
                                    if (line.equals("")) {
                                        continue;
                                    }
                                    for (int i = 0; i < line.length(); i++) {
                                        String c = line.substring(i, i + 1);
                                        if (blocksFSF.getSpacersWidths().
                                                containsKey(c)) {
                                            x += (int) blocksFSF.
                                                    getSpaceWidth(c);
                                        } else {
                                            Block b = blocksFSF.
                                                    getBlock(c, x, y);
                                            x += b.getWidth();
                                            l.blocks().add(b);
                                        }
                                    }
                                    blocksStartY += rowHeight;
                                    y = blocksStartY;
                                    x = blocksStartX;
                                }
                            }
                            line = reader.readLine();
                            if ((line.equals("END_LEVEL"))) {
                                liList.add(l);
                            }
                        }
                        if (line.equals("END_LEVEL")) {
                            break;
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Some");
        }
        return liList;
    }
}
