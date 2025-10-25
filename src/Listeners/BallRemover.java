// Ori Kenigsbuch 206594590
package Listeners;

import GameElement.Ball;
import GameElement.Block;
import GameElement.Counter;
import GameElement.Game;
/**
 * The BallRemover class is responsible for removing a Ball from the game
 * when a Block is hit by a Ball.
 */
public class BallRemover implements HitListener {
    private Game game;
    private Counter remainingBalls;

    /**
     * Constructs a new BallRemover with the specified Game and Counter.
     *
     * @param game           The game from which the Ball should be removed.
     * @param remainingBalls The counter representing the remaining Balls in the game.
     */
    public BallRemover(Game game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    /**
     * Handles the hit event by removing the Ball from the game and decreasing
     * the count of remaining Balls.
     *
     * @param beingHit The Block that was hit.
     * @param hitter   The Ball that hit the Block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        remainingBalls.decrease(1);
    }
}
