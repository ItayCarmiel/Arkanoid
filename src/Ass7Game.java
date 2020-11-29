import biuoop.KeyboardSensor;
import game.LevelInformation;
import game.GameFlow;
import game.AnimationRunner;
import game.LevelSetter;
import game.HighScores;
import game.HighScoresAnimation;
import game.KeyPressStoppableAnimation;
import game.Task;
import game.MenuAnimation;
import game.Menu;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import biuoop.GUI;

/**
 * ass 06.
 * @author Itay Carmiel 208464198 <itaycar875@gmail.com>
 */
public class Ass7Game {
    /**
     * @param args - choose between:
     *             Level 1-4: "level_sets.txt" or none.
     *             Level 1 only: "level1.txt"
     *             Level 2 only: "level2.txt"
     *             Level 3 only: "level3.txt"
     *             Level 4 only: "level4.txt"
     */
    public static void main(String[] args) {
        int i;
        String levelsDef;
        LineNumberReader lineNR = null;
        if (args.length > 0) {
            if ((args[0].equals("level_sets.txt")) || (args[0].equals("level1.txt")) || (args[0].equals("level2.txt"))
                    || (args[0].equals("level3.txt")) || (args[0].equals("level4.txt"))) {
                levelsDef = args[0];
            } else {
                levelsDef = "level_sets.txt";
            }
        } else {
            levelsDef = "level_sets.txt";
        }
        try {
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(levelsDef);
            InputStreamReader reader = new InputStreamReader(is);
            lineNR = new LineNumberReader(reader);
        } catch (Exception ex) {
            System.out.println("Error");
        }
        Map<String, List<LevelInformation>> levelsEOH =
                new TreeMap<String, List<LevelInformation>>();
        try {
            LevelSetter ls = new LevelSetter(lineNR);
            final GUI g = new GUI("Arkanoid", 800, 600);
            final AnimationRunner runner = new AnimationRunner(60, g);
            final KeyboardSensor ks = g.getKeyboardSensor();
            // run the menu
            while (true) {
                levelsEOH = ls.buildLevelsFromFile();
                i = 0;
                Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>(ks, "Arkanoid");
                Task<Void> play = null;
                for (final Map.Entry<String, List<LevelInformation>> entry
                        : levelsEOH.entrySet()) {
                     play = new Task<Void>() {
                        private GameFlow gf = new GameFlow(runner, ks, g);

                        public Void run() {
                            gf.runLevels(entry.getValue());
                            return null;
                        }
                    };
                    i++;
                }
                Task<Void> hsaTask = new Task<Void>() {
                    private HighScoresAnimation highScoreAnimation
                            = new HighScoresAnimation(new HighScores(1));

                    public Void run() {
                        runner.run(new KeyPressStoppableAnimation(ks,
                                KeyboardSensor.SPACE_KEY, highScoreAnimation));
                        return null;
                    }
                };
                Task<Void> qTask = new Task<Void>() {
                    public Void run() {
                        System.exit(0);
                        return null;
                    }
                };
                menu.addSelection("s", "Start", play);
                menu.addSelection("h", "High Scores", hsaTask);
                menu.addSelection("q", "Quit", qTask);
                // wait for users selection
                runner.run(menu);
                Task<Void> task = menu.getTaskStatus();
                task.run();
            }
        }  catch (Exception ex) {
            System.out.println("Error");
        }
    }
}
