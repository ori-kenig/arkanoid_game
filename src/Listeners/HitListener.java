// Ori Kenigsbuch 206594590
package Listeners;

import GameElement.Ball;
import GameElement.Block;
/**
 * The HitListener interface represents an object that listens for hit events in a game.
 */

public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     *
     * @param beingHit The Block that was hit.
     * @param hitter   The Ball that hit the Block.
     */
    void hitEvent(Block hit, Ball hitter);
}
