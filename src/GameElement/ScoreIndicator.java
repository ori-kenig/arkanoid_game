// Ori Kenigsbuch 206594590
package GameElement;

import GameObjects.Sprite;
import Geometry.Point;
import Geometry.Rectangle;

import biuoop.DrawSurface;
import java.awt.Color;

/**
 * The ScoreIndicator class represents a sprite that displays the current score in the game.
 * It is designed to be displayed as a rectangular area at the top of the game screen.
 */
public class ScoreIndicator implements Sprite {
    private Rectangle rect;
    private Counter score;

    /**
     * Constructs a new ScoreIndicator with the specified Counter for the score.
     *
     * @param score The Counter representing the score to be displayed.
     */
    public ScoreIndicator(Counter score) {
        this.rect = new Rectangle(new Point(0, 0), 800, 35);
        this.score = score;
    }

    /**
     * Draws the sprite on the given DrawSurface.
     *
     * @param surface The DrawSurface on which the sprite is drawn.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.gray);
        surface.fillRectangle((int) rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY(),
                (int) rect.getWidth(), (int) rect.getHeight());
        surface.setColor(Color.black);
        surface.drawRectangle((int) rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY(),
                (int) rect.getWidth(), (int) rect.getHeight());
        String scoreString = "score: " + score.getValue();
        surface.drawText(420, 20, scoreString, 18);
    }

    /**
     * Notifies the sprite that a unit of time has passed.
     * This method is called once per frame in the animation loop.
     */
    @Override
    public void timePassed() {    }

    /**
     * Adds the ScoreIndicator to the specified game.
     *
     * @param game The game to which the ScoreIndicator will be added.
     */
    public void addToGame(Game game) {
        game.addSprite(this);
    }
}
