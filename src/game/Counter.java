package game;

/**
 * ass 05.
 * @author Itay Carmiel 208464198 <itaycar875@gmail.com>
 */

public class Counter {
    private int counter;
    /**
     * Constructor.
     */
    public Counter() {
        this.counter = 0;
    }
    /**
     * constructor.
     * @param count is the count of something
     */
    public Counter(int count) {
        this.counter = count;
    }
    /**
     * @param number to add.
     */
    public void increase(int number) {
        this.counter += number;
    }
    /**
     * @param number to subtract.
     */
    public void decrease(int number) {
        this.counter -= number;
    }
    /**
     * @return current value.
     */
    public int getValue() {
        return this.counter;
    }
}
