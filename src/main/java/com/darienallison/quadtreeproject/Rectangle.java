/**
 * The Rectangle class represents a rectangular area in a 2D space, defined by
 * an origin point (x, y), length, and width. It provides methods to retrieve
 * its properties, check if a point is contained within the rectangle, and display
 * its information as a formatted string.
 */
public class Rectangle {
    private double x, y, length, width;

    /**
     * Constructs a Rectangle with specified origin coordinates, length, and width.
     *
     * @param x the x-coordinate of the bottom-left corner of the rectangle.
     * @param y the y-coordinate of the bottom-left corner of the rectangle.
     * @param length the length of the rectangle along the x-axis.
     * @param width the width of the rectangle along the y-axis.
     */
    public Rectangle(double x, double y, double length, double width) {
        this.x = x;
        this.y = y;
        this.length = length;
        this.width = width;
    }

    /**
     * Gets the x-coordinate of the rectangle's bottom-left corner.
     *
     * @return the x-coordinate.
     */
    public double getX() {
        return x;
    }

    /**
     * Gets the y-coordinate of the rectangle's bottom-left corner.
     *
     * @return the y-coordinate.
     */
    public double getY() {
        return y;
    }

    /**
     * Gets the length of the rectangle.
     *
     * @return the length of the rectangle.
     */
    public double getLength() {
        return length;
    }

    /**
     * Gets the width of the rectangle.
     *
     * @return the width of the rectangle.
     */
    public double getWidth() {
        return width;
    }

    /**
     * Checks if a point specified by (px, py) is contained within this rectangle.
     *
     * @param px the x-coordinate of the point to check.
     * @param py the y-coordinate of the point to check.
     * @return true if the point (px, py) is within the rectangle, false otherwise.
     */
    public boolean contains(double px, double py) {
        return (px >= x && px <= x + length && py >= y && py <= y + width);
    }

    /**
     * Returns a string representation of the rectangle, displaying its location
     * and dimensions in a readable format.
     *
     * @return a formatted string with the rectangle's coordinates, length, and width.
     */
    @Override
    public String toString() {
        return String.format("Rectangle at (%.2f, %.2f): %.2fx%.2f", x, y, length, width);
    }
}
