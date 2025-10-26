// Ori Kenigsbuch
package Listeners;

import GameElement.Ball;
import GameElement.Block;
import GameElement.Counter;

/**
 * The ScoreTrackingListener class is responsible for tracking and updating the player's score
 * when a Block is hit by a Ball.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Constructs a new ScoreTrackingListener with the specified Counter for tracking the score.
     *
     * @param scoreCounter The Counter representing the player's score.
     */
    public ScoreTrackingListener (Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * Handles the hit event by increasing the player's score when a Block is hit by a Ball.
     *
     * @param beingHit The Block that was hit.
     * @param hitter   The Ball that hit the Block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(5);
    }
}

