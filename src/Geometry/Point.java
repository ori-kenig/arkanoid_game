// Ori Kenigsbuch 206594590
package Geometry;

/**
 * The Geometry.Point class represents a point in a 2D coordinate system.
 * It provides methods to calculate distances between points, compare points for equality,
 * and retrieve the x and y coordinates of the point.
 */
public class Point {

    private double x; // The x-coordinates of the point
    private double y; // The y-coordinates of the point

    /**
     * Constructs a Geometry.Point with the specified x and y coordinates.
     *
     * @param x The x-coordinate of the point.
     * @param y The y-coordinate of the point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }


    /**
     * Calculates the distance between this point and another point.
     *
     * @param other The other point to calculate the distance to.
     * @return The distance between this point and the other point.
     */
    public double distance(Point other) {
        return Math.sqrt(
                ((this.x - other.x) * (this.x - other.x))
                + ((this.y - other.y) * (this.y - other.y)));
    }

    /**
     * Compares two double values to determine if they are approximately equal,
     * within a small threshold.
     *
     * @param first  The first double value.
     * @param second The second double value.
     * @return {@code true} if the two values are approximately equal, {@code false} otherwise.
     */
    public static boolean equal(double first, double second) {
        double threshold = 0.000001;
        return Math.abs(first - second) <= threshold;
}
    /**
     * Compares this point with another point to determine if they are equal.
     * Two points are considered equal if their x and y coordinates are approximately equal.
     *
     * @param other The other point to compare with.
     * @return {@code true} if the points are equal, {@code false} otherwise.
     */
    public boolean equals(Point other) {
        return (equal(this.x, other.x) && equal(this.y, other.y));
    }

    /**
     * Retrieves the x-coordinate of this point.
     *
     * @return The x-coordinate of this point.
     */
    public double getX() {
        return this.x;
    }


    /**
     * Retrieves the y-coordinate of this point.
     *
     * @return The y-coordinate of this point.
     */
    public double getY() {
        return this.y;
    }
}