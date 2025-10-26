// Ori Kenigsbuch
package GameElement;
import Geometry.Point;
import java.util.Random;

/**
 * The GameElement.Velocity class represents a velocity in a 2D coordinate system.
 * It specifies the change in position along the x and y axes.
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * Constructs a GameElement.Velocity object with specified changes in the x and y directions.
     *
     * @param dx The change in position along the x-axis.
     * @param dy The change in position along the y-axis.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Gets the change in position along the x-axis (dx).
     *
     * @return The change in position along the x-axis.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Gets the change in position along the y-axis (dy).
     *
     * @return The change in position along the y-axis.
     */
    public double getDy() {
        return this.dy;
    }

//    /**
//     * Sets the change in position along the x-axis (dx).
//     *
//     * @param dx The new change in position along the x-axis.
//     */
//    public void setDx(double dx) {
//        this.dx = dx;
//    }
//
//    /**
//     * Sets the change in position along the y-axis (dy).
//     * This method updates the current dy value, which represents the
//     * change in position along the y-axis, to the new value provided.
//     *
//     * @param dy The new change in position along the y-axis.
//     */
//    public void setDy(double dy) {
//        this.dy = dy;
//    }

    /**
     * Creates a GameElement.Velocity object from a given angle and speed.
     * The angle is measured in degrees, and the speed determines
     * the magnitude of the velocity vector.
     *
     * @param angle The angle of the velocity vector in degrees.
     * @param speed The magnitude of the velocity vector.
     * @return A GameElement.Velocity object corresponding to the given angle and speed.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = Math.sin(Math.toRadians(angle)) * speed;
        double dy = Math.cos(Math.toRadians(angle)) * -speed;
        return new Velocity(dx, dy);
    }

    /**
     * Generates a random velocity based on a given size.
     * The speed is lower for larger sizes to simulate slower movement.
     *
     * @param size The size of the object that the velocity is associated with.
     * @return A randomly generated GameElement.Velocity object.
     */
    public static Velocity randomVelocity(int size) {
        Random rnd = new Random();
        double angle = 360 * rnd.nextDouble(); // Random angle between 0 and 360 degrees
        double speed  = (size >= 50) ? 0.25 : 10.0; // Adjust speed based on size
        return Velocity.fromAngleAndSpeed(angle, speed);
    }

//    /**
//     * Reverses the velocity along the x-axis.
//     */
//    public void changeAxisX() {
//        this.dx *= -1;
//    }
//
//    /**
//     * Reverses the velocity along the y-axis.
//     */
//    public void changeAxisY() {
//        this.dy *= -1;
//    }
//
//    /**
//     * Reverses the velocity along both axes.
//     */
//    public void flip() {
//        this.changeAxisX();
//        this.changeAxisY();
//    }

    /**
     * Calculates and returns the speed of the object based on its velocity components.
     *
     * @return the speed as a {@code double}, computed as the square root of the sum
     *         of the squares of {@code dx} and {@code dy}.
     */
    public double getSpeed() {
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * Calculates and returns the angle of the velocity in degrees.
     * <p>
     * The angle is determined using the {@link Math#atan2(double, double)} method, which computes
     * the angle in radians, and is then converted to degrees. If both {@code dx} and {@code dy} are
     * zero, an exception is thrown as the angle is undefined.
     * </p>
     *
     * @return the angle of velocity as an integer, rounded to the nearest degree.
     * @throws IllegalArgumentException if both {@code dx} and {@code dy} are zero.
     */
    public int getAngleOfVelocity() {
        if (dx == 0 && dy == 0) {
            throw new IllegalArgumentException("Error");
        }
        double angle = Math.atan2(dx, dy);
        return (int) Math.round(angle);
    }

    /**
     * Applies the velocity to a given point, returning a new point with the updated position.
     *
     * @param p The original point.
     * @return A new Geometry.Point with the updated position, or {@code null} if the input point is null.
     */
    public Point applyToPoint(Point p) {
        if (p == null) {
            return null;
        }
        return new Point(p.getX() + dx, p.getY() + dy);
    }
}

