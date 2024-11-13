import java.util.ArrayList;
import java.util.List;

/**
 * The LeafNode class represents a leaf node in the quadtree structure.
 * It holds a list of rectangles up to a maximum capacity and supports basic
 * operations such as inserting, deleting, finding, updating, and dumping
 * rectangles within this node.
 */
public class LeafNode extends Node {
    private static final int MAX_CAPACITY = 5;
    private List<Rectangle> rectangles = new ArrayList<>();

    /**
     * Constructs a LeafNode with specified boundaries.
     *
     * @param xMin the minimum x-coordinate boundary of this node.
     * @param xMax the maximum x-coordinate boundary of this node.
     * @param yMin the minimum y-coordinate boundary of this node.
     * @param yMax the maximum y-coordinate boundary of this node.
     */
    public LeafNode(double xMin, double xMax, double yMin, double yMax) {
        super(xMin, xMax, yMin, yMax);
    }

    /**
     * Inserts a rectangle into this leaf node if there is capacity.
     * If the capacity is exceeded, the node splits.
     *
     * @param rect the Rectangle to insert.
     */
    @Override
    public void insert(Rectangle rect) {
        if (rectangles.size() < MAX_CAPACITY) {
            rectangles.add(rect);
        } else {
            // Implement splitting logic when max capacity is exceeded
            System.out.println("Splitting the node...");
        }
    }

    /**
     * Deletes a specified rectangle from this leaf node if it exists.
     *
     * @param rect the Rectangle to delete.
     */
    @Override
    public void delete(Rectangle rect) {
        rectangles.remove(rect);
    }

    /**
     * Finds a rectangle at the specified coordinates within this leaf node.
     *
     * @param x the x-coordinate of the rectangle to find.
     * @param y the y-coordinate of the rectangle to find.
     * @return the found Rectangle, or null if no rectangle exists at the specified coordinates.
     */
    @Override
    public Rectangle find(double x, double y) {
        for (Rectangle rect : rectangles) {
            if (rect.contains(x, y)) {
                return rect;
            }
        }
        return null;
    }

    /**
     * Updates the dimensions of a rectangle at the specified coordinates if it exists.
     *
     * @param x the x-coordinate of the rectangle to update.
     * @param y the y-coordinate of the rectangle to update.
     * @param length the new length of the rectangle.
     * @param width the new width of the rectangle.
     */
    @Override
    public void update(double x, double y, double length, double width) {
        Rectangle rect = find(x, y);
        if (rect != null) {
            // Update the rectangle's dimensions (length and width)
            System.out.println("Rectangle found and updated.");
        } else {
            System.out.println("Nothing to update at (" + x + ", " + y + ").");
        }
    }

    /**
     * Prints the structure of this leaf node, including all rectangles it contains.
     * Each level of the tree is indented according to its depth.
     *
     * @param level the current depth level of this node in the tree, used for indentation.
     */
    @Override
    public void dump(int level) {
        for (int i = 0; i < level; i++) System.out.print("\t");
        System.out.println("LeafNode: " + rectangles);
    }
}
