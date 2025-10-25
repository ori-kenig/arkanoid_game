// Ori Kenigsbuch 206594590

package GameObjects;

import biuoop.DrawSurface;

/**
 * The GameObjects.Sprite interface represents a game object that can be drawn on a screen and updated over time.
 * It provides methods for rendering the object and handling time-based updates.
 */
public interface Sprite {
    /**
     * Draws the sprite on the specified {@link DrawSurface}.
     *
     * @param d the {@link DrawSurface} on which the sprite should be drawn.
     */
    void drawOn(DrawSurface d);


    /**
     * Notifies the sprite that a unit of time has passed, allowing it to update its state accordingly.
     */
    void timePassed();

}
