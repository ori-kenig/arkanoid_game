// Ori Kenigsbuch 206594590
package GameObjects;

import GameElement.Ball;
import GameElement.Velocity;
import Geometry.Point;
import Geometry.Rectangle;


/**
 * The GameObjects.Collidable interface represents objects that can participate in collision detection and response.
 * It provides methods to get the collision shape and handle the effects of a collision.
 */
public interface Collidable {

    /**
     * Returns the "collision shape" of the object as a {@link Rectangle}.
     * This defines the area where collisions can occur.
     *
     * @return the collision rectangle of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * Returns the {@link Block} representation of the object for collision purposes.
     *
     * @return the collision block of the object.
     */
//    GameElement.Block getCollisionBlock();

    /**
     * Notifies the object of a collision at a specific point with a given velocity.
     * Calculates and returns the new velocity of the object after the collision.
     *
     * @param collisionPoint   the point at which the collision occurred.
     * @param currentVelocity  the velocity of the object before the collision.
     * @return the new velocity of the object after the collision.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}