// Ori Kenigsbuch 206594590
package GameElement;

import GameObjects.Collidable;
import GameObjects.Sprite;
import Geometry.Line;
import Geometry.Point;
import Geometry.Rectangle;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

/**
 * The {@code GameElement.Paddle} class represents a paddle in the game, which can be controlled by the user
 * using the left and right arrow keys on the keyboard. The paddle is a {@link Sprite} and
 * a {@link Collidable} object. It can move within the bounds of the game window and interacts
 * with other game objects, such as the ball, when they collide with it.
 * The paddle is represented as a {@link Block} object, and its behavior is defined by the
 * movement, drawing, and collision methods.
 */
public class Paddle implements Sprite, Collidable {
    private KeyboardSensor keyboard;
    private Block b;
    private final Rectangle shape;
    private GUI gui;
    private GameEnvironment environment;

    /**
     * Constructs a new {@code GameElement.Paddle} object with the specified block, GUI, and game environment.
     *
     * @param b          the {@link Block} representing the paddle's shape
     * @param gui        the {@link GUI} used to obtain the keyboard sensor
     * @param environment the {@link GameEnvironment} containing the game world
     */
    public Paddle(Block b, GUI gui, GameEnvironment environment) {
        this.b = b;
        this.shape = b.getCollisionRectangle();
        this.gui = gui;
        this.keyboard = gui.getKeyboardSensor();
        this.environment = environment;
    }

    /**
     * Moves the paddle to the left by 5 units, ensuring it does not move out of bounds.
     */
    public void moveLeft() {
        Point topLeft;
        double guiW = this.gui.getDrawSurface().getWidth();

        if (getCollisionRectangle().getUpperLeft().getX() > 0) {
            topLeft = new Point(getCollisionRectangle().getUpperLeft().getX() - 5,
                    getCollisionRectangle().getUpperLeft().getY());
        } else {
            topLeft = new Point(guiW - this.environment.getBoundsWidth() - this.shape.getWidth(),
                    getCollisionRectangle().getUpperLeft().getY());
        }
        this.shape.setUpperLeft(topLeft);
    }


    /**
     * Moves the paddle to the right by 5 units, ensuring it does not move out of bounds.
     */
    public void moveRight() {
        Point topLeft;
        double guiW = this.gui.getDrawSurface().getWidth();

        if (this.getCollisionRectangle().getUpperLeft().getX() + this.shape.getWidth() < guiW) {
            topLeft = new Point(this.getCollisionRectangle().getUpperLeft().getX() + 5,
                    this.getCollisionRectangle().getUpperLeft().getY());
        } else {
            topLeft = new Point(0, this.getCollisionRectangle().getUpperLeft().getY());
        }
        this.shape.setUpperLeft(topLeft);
    }

    /**
     * Updates the paddle's state based on keyboard input.
     * Moves the paddle left or right if the corresponding keys are pressed.
     */
    public void timePassed() {
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
        if (this.keyboard.isPressed(KeyboardSensor.ENTER_KEY)) {
            this.gui.close();
        }
    }

    /**
     * Draws the paddle on the given {@link DrawSurface}.
     * The paddle is drawn as a filled rectangle with a black border.
     *
     * @param d the {@link DrawSurface} used to draw the paddle
     */
    public void drawOn(DrawSurface d) {
        d.setColor(b.getColor());
        Rectangle rec = this.shape;
        d.fillRectangle((int) rec.getUpperLeft().getX(), (int) rec.getUpperLeft().getY(),
                (int) rec.getWidth(), (int) rec.getHeight());
        d.drawRectangle((int) rec.getUpperLeft().getX(), (int) rec.getUpperLeft().getY(),
                (int) rec.getWidth(), (int) rec.getHeight());
    }

    /**
     * Returns the rectangle representing the collision shape of the paddle.
     *
     * @return the {@link Rectangle} representing the paddle's collision shape
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.shape;
    }

//    /**
//     * Calculates and returns the new velocity after the paddle is hit at a specific point.
//     * The angle of reflection is determined based on the region of the paddle the ball hits.
//     *
//     * @param collisionPoint the point of collision between the paddle and the ball
//     * @param currentVelocity the current velocity of the ball before the collision
//     * @return the new velocity of the ball after the collision with the paddle
//     */
//    public GameElement.Velocity hit(Geometry.Point collisionPoint, GameElement.Velocity currentVelocity) {
//        double threshold = 0.000001;
//
//        double regionW = this.shape.getWidth() / 5;
//        double regionCollisionPointX = collisionPoint.getX() - this.shape.getUpperLeft().getX();
//
//        int region = (int) (regionCollisionPointX / regionW) + 1;
//
//        if ((collisionPoint.getY() >= this.shape.getUpperLeft().getY()) && (collisionPoint.getY()
//                <= this.shape.getUpperLeft().getY() + this.shape.getHeight())) {
//            if (Math.abs(this.shape.getUpperLeft().getX() - collisionPoint.getX()) < threshold) { //left
//                return new GameElement.Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
//            } else if (Math.abs(collisionPoint.getX() - (this.shape.getUpperLeft().getX()
//            + this.shape.getWidth())) < threshold) { //right
//                return new GameElement.Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
//            }
//        }
//
//        double angle;
//        switch (region) {
//            case 1:
//                angle = 300;
//                break;
//            case 2:
//                angle = 330;
//                break;
//            case 3:
//                angle = currentVelocity.getAngleOfVelocity();
//                if (currentVelocity.getDy() > 0) {
//                    currentVelocity = new GameElement.Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
//                }
//                break;
//            case 4:
//                angle = 30;
//                break;
//            case 5:
//                angle = 60;
//                break;
//            default:
//                angle = currentVelocity.getAngleOfVelocity();
//        }
//        return GameElement.Velocity.fromAngleAndSpeed(angle, currentVelocity.getSpeed());
//    }


    /**
     * Handles a collision with the paddle, updating the velocity accordingly.
     *
     * @param collisionPoint   The point of collision.
     * @param currentVelocity  The current velocity of the colliding object.
     * @param hitter the ball that hit the paddle.
     * @return The new velocity after the collision.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double x = this.shape.getUpperLeft().getX();
        double y = this.shape.getUpperLeft().getY();
        double width = this.shape.getWidth();
        double height = this.shape.getHeight();
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();

        Line up = new Line(x, y, x + width, y);
        Line down = new Line(x, y + height, x + width, y + height);
        Line left = new Line(x, y, x, y - height);
        Line right = new Line(x + width, y, x + width, y + height);

        double fifthWidth = width / 5;
        int region = (int) ((collisionPoint.getX() - x) / fifthWidth) + 1;
        double speed = Math.sqrt(dx * dx + (dy * dy));

        if (up.isOnLine(collisionPoint)) {
            switch (region) {
                case 1:
                    return Velocity.fromAngleAndSpeed(300, speed);

                case 2:
                    return Velocity.fromAngleAndSpeed(330, speed);

                case 3:
                    return new Velocity(dx, -dy);

                case 4:
                    return Velocity.fromAngleAndSpeed(30, speed);

                case 5:
                    return Velocity.fromAngleAndSpeed(60, speed);
                default:
                    return currentVelocity;
            }
        }
        return currentVelocity;
    }
        /**
         * Adds the paddle to the given game. It is added as both a collidable and a sprite.
         *
         * @param g the {@link Game} to add the paddle to
         */
    public void addToGame(Game g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
}
