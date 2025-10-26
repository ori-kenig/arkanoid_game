// Ori Kenigsbuch
package Geometry;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code Geometry.Rectangle} class represents a rectangle defined by a position (upper-left corner)
 * and dimensions (width and height).
 * <p>
 * It provides methods for accessing the rectangle's properties, computing its sides, and finding
 * intersection points with a given line.
 * </p>
 */
 public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;


    /**
     * Constructs a new {@code Geometry.Rectangle} with the specified upper-left point, width, and height.
     *
     * @param upperLeft the {@link Point} representing the upper-left corner of the rectangle
     * @param width     the width of the rectangle
     * @param height    the height of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Returns the width of the rectangle.
     *
     * @return the width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Returns the height of the rectangle.
     *
     * @return the height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Returns the upper-left point of the rectangle.
     *
     * @return the {@link Point} representing the upper-left corner of the rectangle
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Returns the upper-right point of the rectangle.
     *
     * @return the {@link Point} representing the upper-right corner of the rectangle
     */
    public Point getUpperRight() {
        return new Point(this.upperLeft.getX() + width, this.upperLeft.getY());
    }

    /**
     * Returns the bottom-left point of the rectangle.
     *
     * @return the {@link Point} representing the bottom-left corner of the rectangle
     */
    public Point getBottomLeft() {
        return new Point(this.upperLeft.getX(), this.upperLeft.getY() - height);
    }

    /**
     * Returns the bottom-right point of the rectangle.
     *
     * @return the {@link Point} representing the bottom-right corner of the rectangle
     */
    public Point getBottomRight() {
        return new Point(this.upperLeft.getX() + width, this.upperLeft.getY() + height);
    }

    /**
     * Sets the upper-left point of the rectangle to a new point.
     *
     * @param upperLeft the new {@link Point} representing the upper-left corner of the rectangle
     */
    public void setUpperLeft(Point upperLeft) {
        this.upperLeft = upperLeft;
    }

    /**
     * Returns the top side of the rectangle as a {@link Line}.
     *
     * @return the {@link Line} representing the top edge of the rectangle
     */
    public Line topLine() {
        return new Line(this.upperLeft, getUpperRight());
    }

    /**
    * Returns the bottom side of the rectangle as a {@link Line}.
    *
    * @return the {@link Line} representing the bottom edge of the rectangle
    */
    public Line bottomLine() {
        return new Line(getBottomLeft(), getBottomRight());
    }

    /**
     * Returns the left side of the rectangle as a {@link Line}.
     *
     * @return the {@link Line} representing the left edge of the rectangle
     */
    public Line leftLine() {
        return new Line(this.upperLeft, getBottomLeft());
    }


    /**
     * Returns the right side of the rectangle as a {@link Line}.
     *
     * @return the {@link Line} representing the right edge of the rectangle
     */
    public Line rightLine() {
        return new Line(getUpperRight(), getBottomRight());
    }

    /**
     * Returns a list of intersection points between the rectangle's sides and the specified line.
     * <p>
     * If no intersection points are found, an empty list is returned.
     * </p>
     *
     * @param line the {@link Line} to check for intersections with the rectangle
     * @return a {@link List} of {@link Point} objects representing the intersection points
     */
    public List<Point> intersectionPoints(Line line) {
        double x = this.upperLeft.getX();
        double y = this.upperLeft.getY();

        Line up = new Line(x, y, x + width, y);
        Line down = new Line(x, y + height, x + width, y + height);
        Line left = new Line(x, y, x, y + height);
        Line right = new Line(x + width, y, x + width, y + height);
        List<Point> intersectionPointsL = new ArrayList<>();


        if (up.isIntersecting(line)) {
            intersectionPointsL.add(up.intersectionWith(line));
        }

        if (down.isIntersecting(line)) {
            intersectionPointsL.add(down.intersectionWith(line));
        }

        if (left.isIntersecting(line)) {
            intersectionPointsL.add(left.intersectionWith(line));
        }

        if (right.isIntersecting(line)) {
            intersectionPointsL.add(right.intersectionWith(line));
        }

        return intersectionPointsL;
    }

    // checks if point on rect
    public boolean onRectangle(Point p) {
        double x = this.upperLeft.getX();
        double y = this.upperLeft.getY();

        Line top = new Line(x, y, x + width, y);
        Line down = new Line(x, y + height, x + width, y + height);
        Line left = new Line(x, y, x, y + height);
        Line right = new Line(x + width, y, x + width, y + height);
        boolean flag = false;

        if (top.isOnLine(p)) {
            flag = true;
        }
        if (down.isOnLine(p)) {
            flag = true;
        }
        if (left.isOnLine(p)) {
            flag = true;
        }
        if (right.isOnLine(p)) {
            flag = true;
        }
        return flag;
    }
}
