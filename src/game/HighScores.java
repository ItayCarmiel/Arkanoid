package game;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.LinkedList;
import java.util.List;

/**
 * ass 07.
 * @author Itay Carmiel 208464198 <itaycar875@gmail.com>
 */
public class HighScores {
    private int size;
    private LinkedList<ScoreInfo> scoresList;
    private File file;
    private String path = "highscores.txt";
    /**
     * Create an high-scores.
     * @param size the size of the table
     */
    public HighScores(int size) {
            this.size = size;
            this.scoresList = new LinkedList<ScoreInfo>();
            this.file = new File(path);
            if (!file.exists()) {
                this.clear();
                this.scoresList.add(new ScoreInfo(0));
            }
            try {
                this.load(this.file);
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
    /**
     * Add a high-score.
     * @param score the score
     */
    public void add(ScoreInfo score) {
        int temp = this.getRank(score.getScore());
        if (temp > this.size) {
            return;
        } else {
            this.scoresList.add(temp - 1, score);
            while (this.scoresList.size() > this.size) {
                this.scoresList.remove(this.scoresList.size() - 1);
            }
            try {
                String s = String.valueOf(scoresList.get(0).getScore());
                FileWriter fw = new FileWriter(this.file.getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write("The highest score so far is: " + s);
                bw.close();
                this.save(this.file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            this.save(this.file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Return the current high scores.
     * The list is sorted such that the highest
     * scores come first.
     * @return the table
     */
    public List<ScoreInfo> getHighScores() {
        this.scoresList.clear();
        try {
            this.load(this.file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this.scoresList;
    }

    /**
     * return the rank of the current score: where will it
     * be on the list if added?
     * Rank 1 means the score will be highest on the list.
     * Rank `size` means the score will be lowest.
     * Rank > `size` means the score is too low and will not
     * be added to the list.
     * @param score the score.
     * @return the rank.
     */
    public int getRank(int score) {
        int i;
        for (i = 0; i < this.scoresList.size(); i++) {
            if (score >= this.scoresList.get(i).getScore()) {
                return i + 1;
            }
        }
        return i + 1;
    }

    /**
     * Clears the table.
     */
    public void clear() {
        this.scoresList.clear();
        try {
            this.save(this.file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Load table data from file.
     * Current table data is cleared.
     * @param filename the file
     * @throws IOException if it failed to save the file
     */
    public void load(File filename) throws IOException {
        ScoreInfo tempScore = null;
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(
                    new FileInputStream(filename));
            tempScore = (ScoreInfo) objectInputStream.readObject();
            while (tempScore != null) {
                this.scoresList.add(tempScore);
                tempScore = (ScoreInfo) objectInputStream.readObject();
            }
        } catch (FileNotFoundException e) {
            System.err.println("Unable to find file: " + filename);
            return;
        } catch (ClassNotFoundException e) {
            System.err.println("Unable to find class for object in file: "
                    + filename.getName());
            return;
        } catch (IOException e) {
            System.err.println("Failed reading object");
            e.printStackTrace(System.err);
            return;
        } finally {
            try {
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file: " + filename);
            }
        }
    }
    /**
     *  Save table data to the specified file.
     * @param filename the file name
     * @throws IOException if it failed to save the file
     */
    public void save(File filename) throws IOException {
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(
                    new FileOutputStream(filename));
            for (ScoreInfo tempScore : this.scoresList) {
                objectOutputStream.writeObject(tempScore);
            }
            objectOutputStream.writeObject(null);
        } catch (IOException e) {
            System.err.println("Failed saving object");
            e.printStackTrace(System.err);
        } finally {
            try {
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file: "
                        + filename.getName());
            }
        }
    }
}
