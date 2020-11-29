package game;
/**
 * ass 07.
 * @author Itay Carmiel 208464198 <itaycar875@gmail.com>
 */

/**
 * @param <T> the type.
 */
public interface Task<T> {
    /**
     * runs the task.
     * @return the task type
     */
    T run();
}
