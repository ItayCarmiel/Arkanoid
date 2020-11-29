package hit;

/**
 * ass 05.
 * @author Itay Carmiel 208464198 <itaycar875@gmail.com>
 */

public interface HitNotifier {
    /**
     * Add hl as a listener to hit events.
     * @param hl - hitList
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hl from the list of listeners to hit events.
     * @param hl - hitList
     */
    void removeHitListener(HitListener hl);
}
