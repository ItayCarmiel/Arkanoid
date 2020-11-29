package game;
/**
 * ass 07.
 * @author Itay Carmiel 208464198 <itaycar875@gmail.com>
 */
/**
 * @param <T> the type.
 */
public interface Menu<T> extends Animation {
    /**
     * adds selections.
     * @param key the key
     * @param message the message
     * @param returnVal the return value
     */
    void addSelection(String key, String message, T returnVal);
    /**
     *
     * @return returns the status
     */
    T getTaskStatus();
    /**
     *
     * @return returns the menu status
     */
    Menu<T> getMenuStatus();
    /**
     * tells us what to run a sub menu or a task.
     * @return "m" for sub menu "t" for task
     */
    String whatToRun();
}
