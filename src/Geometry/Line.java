// Ori Kenigsbuch
package Geometry;

import java.util.ArrayList;
import java.util.List;

/**
 * The Geometry.Line class represents a line segment in a 2D coordinate system.
 * It provides methods to calculate the length, midpoint, and intersection points of the line,
 * and to determine if two lines intersect or are equal.
 */
public class Line {

    private Point start; //The starting point of the line
    private Point end; //The end point of the line

    /**
     * Constructs a Geometry.Line with specified start and end points.
     *
     * @param start The starting point of the line.
     * @param end   The ending point of the line.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Constructs a Geometry.Line with specified coordinates for start and end points.
     *
     * @param x1 The x-coordinate of the starting point.
     * @param y1 The y-coordinate of the starting point.
     * @param x2 The x-coordinate of the ending point.
     * @param y2 The y-coordinate of the ending point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * Calculates the length of the line.
     *
     * @return The length of the line.
     */
    public double length() {
        return this.start.distance(end);
    }

    /**
     * Calculates the midpoint of the line.
     *
     * @return The midpoint of the line as a Geometry.Point.
     */
    public Point middle() {
        double x = (this.start.getX() + this.end.getX()) / 2;
        double y = (this.start.getY() + this.end.getY()) / 2;
        return new Point(x, y);
    }

    /**
     * Returns the starting point of the line.
     *
     * @return The starting point of the line.
     */
    public Point start() {
        return this.start;
    }

    /**
     * Returns the ending point of the line.
     *
     * @return The ending point of the line.
     */
    public Point end() {
        return this.end;
    }

    /**
     * Checks if this line intersects with another line.
     *
     * @param other The other line to check for intersection.
     * @return {@code true} if the lines intersect, {@code false} otherwise.
     */
    public boolean isIntersecting(Line other) {
        return intersectionWith(other) != null;
    }

    /**
     * Checks if this line intersects with two other lines.
     *
     * @param other1 The first other line to check for intersection.
     * @param other2 The second other line to check for intersection.
     * @return {@code true} if this line intersects with both lines, {@code false} otherwise.
     */
    public boolean isIntersecting(Line other1, Line other2) {
        if (equals(other1) || equals(other2) || other1.equals(other2)) {
            return false;
        }
        return this.isIntersecting(other1) && this.isIntersecting(other2);
    }

    /**
     * Checks if a point lies on this line segment.
     *
     * @param point The point to check.
     * @return {@code true} if the point is on the line, {@code false} otherwise.
     */
    public boolean isOnLine(Point point) {
        double x = point.getX();
        double y = point.getY();

        double minX = Math.min(this.start.getX(), this.end.getX());
        double maxX = Math.max(this.start.getX(), this.end.getX());
        double minY = Math.min(this.start.getY(), this.end.getY());
        double maxY = Math.max(this.start.getY(), this.end.getY());

        return (x >= minX && x <= maxX && y >= minY && y <= maxY);
    }
    /**
     * Calculates the intersection point between this line and another line.
     *
     * @param other The other line to check for intersection.
     * @return The intersection point as a Geometry.Point, or {@code null} if there is no intersection.
     */
    public Point intersectionWith(Line other) {
        double perpendicularX, perpendicularY, x1, x2, y1, y2;
        Point intersecPoint;

        // Case both lines are verticals
        if (Point.equal(this.start.getX(), this.end.getX())
                && Point.equal(other.start.getX(), other.end.getX())) {
            return null; //Parallel vertical lines
            // Case one line is vertical and the other is not
        } else if (Point.equal(this.start.getX(), this.end.getX())) {
            // This line is vertical
            perpendicularX = this.start.getX();
            // Check if other line is horizontal
            if (Point.equal(other.start.getY(), other.end.getY())) {
                perpendicularY = other.start.getY();
                intersecPoint = new Point(perpendicularX, perpendicularY);
            } else {
                //Calculate intersection using other line's equation
                x1 = other.start.getX();
                x2 = other.end.getX();
                y1 = other.start.getY();
                y2 = other.end.getY();
                double slope = (y1 - y2) / (x1 - x2);
                double intersectY = y1 + slope * (perpendicularX - x1);
                intersecPoint = new Point(perpendicularX, intersectY);
            }
            // Check if intersection point is on both lines
            if (!(this.isOnLine(intersecPoint)) || !(other.isOnLine(intersecPoint))) {
                return null;
            }

        } else if (Point.equal(other.start.getX(), other.end.getX())) {
            // Similar logic for the other line being perpendicular to x-axis
            perpendicularX = other.start.getX();
            if (Point.equal(this.start.getY(), this.end.getY())) {
                perpendicularY = this.start.getY();
                intersecPoint = new Point(perpendicularX, perpendicularY);
            } else {
                x1 = this.start.getX();
                x2 = this.end.getX();
                y1 = this.start.getY();
                y2 = this.end.getY();
                double slope = (y1 - y2) / (x1 - x2);
                double yintersec = y1 + slope * (perpendicularX - x1);
                intersecPoint = new Point(perpendicularX, yintersec);
            }

            // Check if intersection point is on both lines
            if (!(this.isOnLine(intersecPoint)) || !(other.isOnLine(intersecPoint))) {
                return null;
            }

        } else {
            // Neither line is vertical
            double slope1 = (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
            double b1 = this.start.getY() - slope1 * this.start.getX();

            double slope2 = (other.start.getY() - other.end.getY()) / (other.start.getX() - other.end.getX());
            double b2 = other.start.getY() - slope2 * other.start.getX();

            if (Point.equal(slope1, slope2)) {
                return null; // Parallel lines
            }

            double intersectX = (b2 - b1) / (slope1 - slope2);
            double intersectY = slope1 * intersectX + b1;

            intersecPoint = new Point(intersectX, intersectY);

            // Check if the intersection point on both lines
            if (!(this.isOnLine(intersecPoint)) || !(other.isOnLine(intersecPoint))) {
                return null;
            }
        }

        return intersecPoint;
    }

    /**
     * Checks if this line is equal to another line.
     * Two lines are equal if they have the same start and end points, regardless of order.
     *
     * @param other The other line to compare.
     * @return {@code true} if the lines are equal, {@code false} otherwise.
     */
    public boolean equals(Line other) {
        return (this.start.equals(other.start) && this.end.equals(other.end))
                || (this.start.equals(other.end) && this.end.equals(other.start));

    }

    /**
     * Finds the closest intersection point between this line and a given rectangle, relative to the start of the
     * line.
     * If there are no intersection points, returns {@code null}.
     *
     * @param rect the {@link Rectangle} to check for intersection with this line.
     * @return the closest intersection {@link Point} to the start of the line, or {@code null} if no intersection
     * exists.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> intersections = rect.intersectionPoints(this);
        if (intersections.isEmpty()) {
            return null;
        }
        double intersectionDistance = intersections.get(0).distance(this.start);
        Point closest = intersections.get(0);
        for (Point point : intersections) {
            if (point.distance(this.start) < intersectionDistance) {
                intersectionDistance = point.distance(this.start);
                closest = point;
            }
        }
        return closest;
    }

    /**
     * Divides this line into 5 equal zones along its length, starting from the line's start point.
     * Each zone is represented as a sub-line, and the method returns a list of these sub-lines.
     *
     * @return a {@link List} of {@link Line} objects, where each line represents a zone of equal width
     *         along the original line. The zones are divided based on the x-coordinate distance.
     */
    public List<Line> zones() {
        List<Line> zones1 = new ArrayList<>();
        Point p1 = this.start;
        double size = Math.abs(this.end.getX() - this.start.getX()) / 5;
        for (int i = 0; i < 5; i++) {
            Point p2 = new Point(p1.getX() + size, this.start.getY());
            zones1.add(new Line(p1, p2));
            p1 = p2;
        }
        return zones1;
    }

}
