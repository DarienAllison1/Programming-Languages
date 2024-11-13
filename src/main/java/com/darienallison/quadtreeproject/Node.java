/**
 * The Node class represents an abstract node in a quadtree structure.
 * It provides a common interface and shared properties for both internal and leaf nodes.
 * Each node has defined spatial boundaries, and subclasses are expected to implement
 * the quadtree operations: insert, delete, find, update, and dump.
 */
public abstract class Node {
    protected double xMin, xMax, yMin, yMax;

    /**
     * Constructs a Node with specified spatial boundaries.
     *
     * @param xMin the minimum x-coordinate boundary of this node.
     * @param xMax the maximum x-coordinate boundary of this node.
     * @param yMin the minimum y-coordinate boundary of this node.
     * @param yMax the maximum y-coordinate boundary of this node.
     */
    public Node(double xMin, double xMax, double yMin, double yMax) {
        this.xMin = xMin;
        this.xMax = xMax;
        this.yMin = yMin;
        this.yMax = yMax;
    }

    /**
     * Inserts a rectangle into this node or its child nodes.
     * Subclasses should define specific insertion logic based on node type.
     *
     * @param rect the Rectangle to insert.
     */
    public abstract void insert(Rectangle rect);

    /**
     * Deletes a specified rectangle from this node or its child nodes.
     * Subclasses should define specific deletion logic based on node type.
     *
     * @param rect the Rectangle to delete.
     */
    public abstract void delete(Rectangle rect);

    /**
     * Finds a rectangle at the specified coordinates within this node or its child nodes.
     * Subclasses should define specific search logic based on node type.
     *
     * @param x the x-coordinate of the rectangle to find.
     * @param y the y-coordinate of the rectangle to find.
     * @return the found Rectangle, or null if no rectangle exists at the specified coordinates.
     */
    public abstract Rectangle find(double x, double y);

    /**
     * Updates the dimensions of a rectangle at the specified coordinates within this node or its child nodes.
     * Subclasses should define specific update logic based on node type.
     *
     * @param x the x-coordinate of the rectangle to update.
     * @param y the y-coordinate of the rectangle to update.
     * @param length the new length of the rectangle.
     * @param width the new width of the rectangle.
     */
    public abstract void update(double x, double y, double length, double width);

    /**
     * Prints the structure of this node and its child nodes (if any), with indentation
     * according to the node's depth level in the quadtree.
     *
     * @param level the current depth level of this node in the tree, used for indentation.
     */
    public abstract void dump(int level);
}


