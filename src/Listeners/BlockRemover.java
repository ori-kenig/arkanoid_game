// Ori Kenigsbuch
package Listeners;

import GameElement.Ball;
import GameElement.Block;
import GameElement.Counter;
import GameElement.Game;
/**
 * The BlockRemover class is responsible for removing a Block from the game
 * when it is hit by a Ball.
 */
public class BlockRemover implements HitListener {
    private Game game;
    private Counter remainingBlocks;

    /**
     * Constructs a new BlockRemover with the specified Game and Counter.
     *
     * @param game            The game from which the Block should be removed.
     * @param remainingBlocks The counter representing the remaining Blocks in the game.
     */
    public BlockRemover(Game game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }

    /**
     * Handles the hit event by removing the Block from the game, decreasing
     * the count of remaining Blocks, and changing the color of the hitting Ball.
     *
     * @param beingHit The Block that was hit.
     * @param hitter   The Ball that hit the Block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeHitListener(this);
        beingHit.removeFromGame(this.game);
        remainingBlocks.decrease(1);
        hitter.setColor(beingHit.getColor());
    }
}

