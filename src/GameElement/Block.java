// Ori Kenigsbuch
package GameElement;

import GameObjects.Collidable;
import GameObjects.Sprite;
import Geometry.Line;
import Geometry.Point;
import Geometry.Rectangle;
import Listeners.HitListener;
import Listeners.HitNotifier;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


/**
 * The {@code GameElement.Block} class represents a rectangular block in the game.
 * It implements the {@link Collidable} and {@link Sprite} interfaces,
 * enabling it to interact with other game elements and be rendered on the screen.
 * A block has a position, size (represented by a {@link Rectangle}), and a color.
 * It can detect collisions, modify velocities upon collision, and be drawn onto a {@link DrawSurface}.
 */
public class Block implements Collidable, Sprite {

    private Rectangle rectangle;
    private Color color;
    private Game game;
    private List<HitListener> hitListeners;


    /**
     * Constructs a {@code GameElement.Block} with the specified rectangle and color.
     *
     * @param rectangle the {@link Rectangle} defining the block's shape and position
     * @param color     the {@link Color} of the block
     */
    public Block(Rectangle rectangle, Color color, Game game) {
        this.rectangle = rectangle;
        this.color = color;
        this.game = game;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * Draws the block on the specified {@link DrawSurface}.
     *
     * @param d the {@link DrawSurface} on which the block is drawn
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) rectangle.getUpperLeft().getX(), (int) rectangle.getUpperLeft().getY(),
                (int) rectangle.getWidth(), (int) rectangle.getHeight());
        d.setColor(Color.black);
        d.drawRectangle((int) rectangle.getUpperLeft().getX(), (int) rectangle.getUpperLeft().getY(),
                (int) rectangle.getWidth(), (int) rectangle.getHeight());
    }

    /**
     * Returns the collision rectangle of the block.
     *
     * @return the {@link Rectangle} representing the block's collision shape
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * Returns this block as the object involved in a collision.
     *
     * @return this block
     */
    public Block getCollisionBlock() {
        return this;
    }

    /**
     * Returns the color of the block.
     *
     * @return the {@link Color} of the block
     */
    public Color getColor() {
        return color;
    }

    /**
     * Handles a collision with the block at a given collision point and velocity.
     * <p>
     * This method calculates the new velocity after the collision, based on the collision point
     * and the block's edges.
     * </p>
     *
     * @param collisionPoint   the {@link Point} where the collision occurred
     * @param currentVelocity  the current {@link Velocity} of the object before the collision
     * @return the new {@link Velocity} after the collision
     */
//    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double x = this.rectangle.getUpperLeft().getX();
        double y = this.rectangle.getUpperLeft().getY();
        double width = this.rectangle.getWidth();
        double height = this.rectangle.getHeight();
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();

        Line up = new Line(x, y, x + width, y);
        Line down = new Line(x, y + height, x + width, y + height);
        Line left = new Line(x, y, x, y + height);
        Line right = new Line(x + width, y, x + width, y + height);
        List<Point> intersectionPoints = new ArrayList<>();
            if (!ballColorMatch(hitter)) {
                this.notifyHit(hitter);
            }

        if (up.isOnLine(collisionPoint)) {
            dy *= -1;
        }

        if (down.isOnLine(collisionPoint)) {
            dy *= -1;
        }

        if (right.isOnLine(collisionPoint)) {
            dx *= -1;
        }

        if (left.isOnLine(collisionPoint)) {
            dx *= -1;
        }
        return  new Velocity(dx, dy);

    }

    /**
     * Notifies the block that time has passed.
     * This method is currently empty but can be overridden or expanded if
     * the block needs to update its state over time.
     */
    @Override
    public void timePassed() {
    }

    /**
     * Add the block to the game as both a sprite and a collidable object.
     */
    public void addToGame() {
        game.addCollidable(this);
        game.addSprite(this);
    }

    /**
     * Return true if block that being hit by the ball has the same color as the ball.
     * @param ball the ball that hit the block.
     * @return The collision rectangle.
     */
    public boolean ballColorMatch(Ball ball) {
        return ball.getColor().equals(color);
    }

    /**
     * remove the block from the game as both a sprite and a collidable object.
     * @param game the game to remove the block from.
     */
    public void removeFromGame(Game game) {
        // when ball hit block and the colorMatch is false
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    /**
     * Notifies all registered {@link HitListener}s that a hit event has occurred.
     *
     * <p>This method creates a copy of the current list of hit listeners
     * to avoid issues that could arise if listeners are added or removed
     * while iterating over the list. Each listener is then notified of the
     * hit event, providing the object that was hit and the ball that caused
     * the hit.</p>
     *
     * @param hitter the {@link Ball} that caused the hit event.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * add hl as a listener to the block.
     * @param hl the listener to be registered to the block .
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * // Remove hl from the list of listeners to hit events.
     * @param hl the listener to removed from the block .
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
}

