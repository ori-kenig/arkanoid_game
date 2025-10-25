package Listeners;// Ori Kenigsbuch 206594590

/**
 * The HitNotifier interface represents an object that can notify listeners about hit events.
 */
public interface HitNotifier {

    /**
     * Adds a HitListener to the list of listeners for hit events.
     *
     * @param hl The HitListener to be added.
     */
    void addHitListener(HitListener hl);

    /**
     * Removes a HitListener from the list of listeners for hit events.
     *
     * @param hl The HitListener to be removed.
     */
    void removeHitListener(HitListener hl);

    // This method is called whenever the beingHit object is hit.
    // The hitter parameter is the GameElement.Ball that's doing the hitting.
//    void hitEvent(Block beingHit, Ball hitter);
}
