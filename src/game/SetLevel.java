package game;
/**
 * ass 07.
 * @author Itay Carmiel 208464198 <itaycar875@gmail.com>
 */
public class SetLevel {
    private String key;
    private String path;
    private String title;
    /**
     * Constructor.
     * @param description the description line.
     * @param path the path line.
     */
    public SetLevel(String description, String path) {
        String[] parts = description.split(":");
        this.key = parts[0];
        this.path = path;
        this.title = parts[1];
    }
    /**
     * Returns the key that matches the level.
     * @return a string of the key.
     */
    public String getKey() {
        return this.key;
    }
    /**
     * Returns the path.
     * @return a string of the path of the level.
     */
    public String getPath() {
        return this.path;
    }
    /**
     * Returns the title of the level.
     * @return a string of the level name.
     */
    public String getTitle() {
        return this.title;
    }
}
