// Ori Kenigsbuch
package GameElement;

import Geometry.Line;
import Geometry.Point;
import GameObjects.Sprite;
import biuoop.DrawSurface;
import java.awt.Color;


/**
 * The GameElement.Ball class represents a simple geometric ball with a defined center,
 * radius, color, and velocity. It provides methods for accessing and modifying
 * the ball's properties, as well as methods for moving and drawing the ball.
 */
public class Ball implements Sprite {
    private Point center;
    private int radius;
    private Color color;
    private Velocity v;
    private GameEnvironment environment;
    private Game game;

    /**
     * Constructs a new GameElement.Ball with the specified center, radius, and color.
     *
     * @param center The center point of the ball.
     * @param r    The radius of the ball.
     * @param color  The color of the ball.
     * @param v The velocity of the ball.
     * @param environment The environment of the game.
     */
    public Ball(Point center, int r, Color color, Velocity v, GameEnvironment environment) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.v = v;
        this.environment = environment;
//        this.game = game;
    }

    /**
     * Constructs a new GameElement.Ball with the specified x and y coordinates, radius, and color.
     *
     * @param x     The x-coordinate of the center point of the ball.
     * @param y     The y-coordinate of the center point of the ball.
     * @param r     The radius of the ball.
     * @param color The color of the ball.
     * @param environment game environment.
     * @param game the game object.
     */
    public Ball(double x, double y, int r, Color color, GameEnvironment environment, Game game) {
        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
        this.environment = environment;
        this.game = game;
    }

    // accessors

    /**
     * Gets the x-coordinate of the center point of the ball, rounded to the nearest integer.
     *
     * @return The x-coordinate of the center point.
     */

    public int getX() {
        return (int) Math.round(center.getX());
    }

    /**
     * Gets the y-coordinate of the center point of the ball, rounded to the nearest integer.
     *
     * @return The y-coordinate of the center point.
     */
    public int getY() {
        return (int) Math.round(center.getY());
    }

//    /**
//     * Returns the center point of this object.
//     *
//     * @return the center {@link Geometry.Point} of this object.
//     */
//    public Geometry.Point getCenter() {
//        return this.center;
//    }

    /**
     * Gets the size (radius) of the ball.
     *
     * @return The size (radius) of the ball.
     */

    public int getSize() {
        return radius;
    }

    /**
     * Sets the center point of the ball to the given point.
     *
     * @param p The new center point.
     */
    public void setCenter(Point p) {
        this.center = new Point(p.getX(), p.getY());
    }

    /**
     * Gets the color of the ball.
     *
     * @return The color of the ball.
     */
    public java.awt.Color getColor() {
        return color;
    }

    /**
     * Draws the ball on the given DrawSurface.
     *
     * @param surface The DrawSurface on which to draw the ball.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle((int) center.getX(), (int) center.getY(), radius);
    }

    /**
     * Sets the velocity of the ball to the specified GameElement.Velocity.
     *
     * @param v The new velocity.
     */
    public void setVelocity(Velocity v) {
        this.v = v;
    }

    /**
     * Sets the velocity of the ball to the specified components (dx, dy).
     *
     * @param dx The change in x-coordinate.
     * @param dy The change in y-coordinate.
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }

    /**
     * Gets the velocity of the ball.
     *
     * @return The velocity of the ball.
     */
    public Velocity getVelocity() {
        return this.v;
    }

    /**
     * Retrieves the game environment associated with this object.
     *
     * @return the {@link GameEnvironment} instance.
     */
    public GameEnvironment getGameEnv() {
        return environment;
    }


//    /**
//     * Sets the size of the ball to the specified size.
//     *
//     * @param size The new size of the ball.
//     */
//    public void setSize(double size) {
//        this.radius = (int) size;
//    }

    /**
     * Sets the game environment for this object.
     *
     * @param gameEnvironment the {@link GameEnvironment} to associate with this object.
     */
    public void setGameEnvironment(GameEnvironment gameEnvironment) {
    this.environment = gameEnvironment;
    }

    /**
     * Notifies the object that a unit of time has passed and updates its position
     * by invoking the {@code moveOneStep()} method.
     * This method is typically called repeatedly as part of a game loop to ensure
     * that the object updates its state or position over time.
     */
    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * Moves the ball one step based on its current velocity.
     * Updates the center of the ball according to its velocity.
     */
    public void moveOneStep() {
        double almostX;
        double almostY;
        Line trajectory = new Line(this.getX(), this.getY(),
                this.getX() + this.getVelocity().getDx(), this.getY() + this.getVelocity().getDy());
        if (this.environment.getClosestCollision(trajectory) == null) {
            this.center = this.getVelocity().applyToPoint(this.center);
        } else {
            //x value of collision point
            double colpX =  this.environment.getClosestCollision(trajectory).collisionPoint().getX();
            //y value of collision point
            double colpY =  this.environment.getClosestCollision(trajectory).collisionPoint().getY();
            //x value of collidable upperLeft point
            double xcolid = this.environment.getClosestCollision(trajectory).collisionObject().
                    getCollisionRectangle().getUpperLeft().getX();
            //y value of collidable upperLeft point
            double ycolid = this.environment.getClosestCollision(trajectory).collisionObject().
                    getCollisionRectangle().getUpperLeft().getY();
            //collidable width
            double colWidth = this.environment.getClosestCollision(trajectory).collisionObject().getCollisionRectangle()
                    .getWidth();
            //collidable height
            double colHeight = this.environment.getClosestCollision(trajectory).collisionObject().
                    getCollisionRectangle().getHeight();

            double xDiffrence = Math.abs(trajectory.start().getX() - colpX);
            double yDiffrence = Math.abs(trajectory.start().getY() - colpY);

            // bring the ball almost to the collision point
            if (trajectory.start().getX() > colpX) {
                almostX = this.center.getX() - xDiffrence * 0.99;
            } else {
                almostX = this.center.getX() + xDiffrence * 0.99;
            }
            if (trajectory.start().getY() > colpY) {
                almostY = this.center.getY() - yDiffrence * 0.99;
            } else {
                almostY = this.center.getY() + yDiffrence * 0.99;
            }

            this.center = new Point(almostX, almostY);
            Point collisionPoint = this.environment.getClosestCollision(trajectory).collisionPoint();
            this.setVelocity(this.environment.getClosestCollision(trajectory).collisionObject().hit(this, collisionPoint,
                    this.getVelocity()));
            this.center = this.getVelocity().applyToPoint(this.center);

            double x = this.getX();
            double y = this.getY();
            double dx = this.getVelocity().getDx();
            double dy = this.getVelocity().getDy();
        }
    }

    /**
     * adding the ball to the game.
     */
    public void addToGame(Game g) {
        g.addSprite(this);
    }

    /**
     * removing the ball from the game.
     * @param game the game to remove the ball from.
     */
    public void removeFromGame(Game game) {
        game.removeSprite(this);
    }

    /**
     * change the color of the ball.
     * @param color the color we want the ball to be with.
     */
    public void setColor(Color color) {
        this.color = color;
    }

}
