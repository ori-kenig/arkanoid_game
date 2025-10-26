// Ori Kenigsbuch
package GameObjects;

import Geometry.Point;

/**
 * The GameObjects.CollisionInfo class provides information about a collision event.
 * It contains the point at which the collision occurs and the collidable
 * object involved in the collision.
 */
public class CollisionInfo {

    private Point collisionPoint;
    private Collidable collidable;

    /**
     * Constructs a GameObjects.CollisionInfo object with the specified collision point and collidable object.
     *
     * @param collisionPoint the {@link Point} where the collision occurs.
     * @param collidable     the {@link Collidable} object involved in the collision.
     */
    public CollisionInfo(Point collisionPoint, Collidable collidable) {
        this.collisionPoint = collisionPoint;
        this.collidable = collidable;
    }


    /**
     * Retrieves the point at which the collision occurs.
     *
     * @return the {@link Point} of the collision.
     */
    public Point collisionPoint() {
        return collisionPoint;
    }

    /**
     * Retrieves the collidable object involved in the collision.
     *
     * @return the {@link Collidable} object involved in the collision.
     */
    public Collidable collisionObject() {
        return collidable;
    }

}
