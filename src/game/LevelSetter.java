package game;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
/**
 * ass 07.
 * @author Itay Carmiel 208464198 <itaycar875@gmail.com>
 */
public class LevelSetter {
    private List<SetLevel> setLevels;
    private Map<String, String> nameFromKey;
    /**
     * Constructor.
     * @param reader the file we read from.
     * @throws IOException if file is written wrongly.
     */
    public LevelSetter(LineNumberReader reader) throws IOException {
        int i = 0;
        this.setLevels = new ArrayList<SetLevel>();
        this.nameFromKey = new TreeMap<String, String>();
        String oddLine = new String();
        String evenLine = new String();
        while ((oddLine = reader.readLine()) != null) {
            evenLine = reader.readLine();
            this.setLevels.add(new SetLevel(oddLine, evenLine));
            this.nameFromKey.put(this.setLevels.get(i).getKey(),
                    this.setLevels.get(i).getTitle());
            i++;
        }
    }
    /**
     * Returns the name level which matches the key.
     * @param key our key.
     * @return the level name.
     */
    public String getLevelName(String key) {
        return this.nameFromKey.get(key);
    }
    /**
     * Returns a map which contains the a list of level information.
     * @return map.
     * @throws Exception wrong text.
     */
    public  Map<String, List<LevelInformation>> buildLevelsFromFile()
            throws Exception {
        Map<String, List<LevelInformation>> map = new
                TreeMap<String, List<LevelInformation>>();
        List<LevelInformation> l;
        for (int i = 0; i < this.setLevels.size(); i++) {
            l = new ArrayList<LevelInformation>();
            String text = this.setLevels.get(i).getPath();
            InputStream is = ClassLoader.getSystemClassLoader().
                    getResourceAsStream(text);
            InputStreamReader reader = new InputStreamReader(is);
            LineNumberReader lineNR = new LineNumberReader(reader);
            l.addAll(LevelSpecificationReader.fromReader(lineNR));
            map.put(this.setLevels.get(i).getKey(), l);
        }
        return map;
    }
}
