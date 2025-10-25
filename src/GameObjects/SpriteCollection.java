// Ori Kenigsbuch 206594590
package GameObjects;

import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code GameObjects.SpriteCollection} class represents a collection of {@link Sprite} objects.
 * It provides methods to manage the sprites, update their states, and render them
 * on a {@link DrawSurface}.
 * <p>
 * This class is useful for organizing and iterating over a group of sprites in a game,
 * ensuring that all sprites are properly updated and drawn.
 * </p>
 */
 public class SpriteCollection {
    private List<Sprite> spriteList; // List of sprites in the collection

    /**
     * Constructs an empty {@code GameObjects.SpriteCollection}.
     */
    public SpriteCollection() {
        this.spriteList = new ArrayList<>();
    }

    /**
     * Adds a {@link Sprite} to the collection.
     *
     * @param s the sprite to be added
     */
    public void addSprite(Sprite s) {
        if (s != null) {
            this.spriteList.add(s);
        }
    }

    /**
     * Notifies all sprites in the collection that time has passed by calling
     * their {@code timePassed} method.
     */
    public void notifyAllTimePassed() {
        List<Sprite> spritesCopy = new ArrayList<>(spriteList);
        for (Sprite sprite : spritesCopy) {
            sprite.timePassed();
        }
    }

    /**
     * Draws all sprites in the collection on the given {@link DrawSurface}.
     *
     * @param d the {@code DrawSurface} on which the sprites will be drawn
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : spriteList) {
            sprite.drawOn(d);
        }
    }

    /**
     * remove a sprite from the collection.
     *
     * @param s The sprite to be removed.
     */
    public void removeSprite(Sprite s) {
        this.spriteList.remove(s);
    }

    /**
     * Returns the list of sprites in the collection.
     *
     * @return a {@link List} of {@link Sprite} objects
     */
    public List<Sprite> getSprites() {
        return this.spriteList;
    }
}
