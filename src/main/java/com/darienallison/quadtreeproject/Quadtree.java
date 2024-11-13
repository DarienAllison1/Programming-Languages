package com.darienallison.quadtreeproject;

import java.util.ArrayList;
import java.util.List;

/**
 * The Quadtree class represents a data structure for spatial partitioning using nodes.
 * It supports operations such as insert, find, delete, update, and dump.
 */
public class Quadtree {

    private Node root;

    // Define the initial dimensions for the quadtree's root node
    public Quadtree() {
        this.root = new LeafNode(-50, 50, -50, 50);
    }

    /**
     * Inserts a new rectangle into the quadtree.
     *
     * @param x the x-coordinate of the rectangle
     * @param y the y-coordinate of the rectangle
     * @param length the length of the rectangle
     * @param width the width of the rectangle
     */
    public void insert(double x, double y, double length, double width) {
        Rectangle rect = new Rectangle(x, y, length, width);
        root = root.insert(rect); // Update root in case of a split
    }

    /**
     * Finds a rectangle at the specified coordinates in the quadtree.
     *
     * @param x the x-coordinate of the rectangle to find
     * @param y the y-coordinate of the rectangle to find
     */
    public void find(double x, double y) {
        Rectangle found = root.find(x, y);
        if (found != null) {
            System.out.println(found);
        } else {
            System.out.println("Nothing found at (" + x + ", " + y + ")");
        }
    }

    /**
     * Deletes a rectangle at the specified coordinates from the quadtree.
     *
     * @param x the x-coordinate of the rectangle to delete
     * @param y the y-coordinate of the rectangle to delete
     */
    public void delete(double x, double y) {
        root = root.delete(x, y);
    }

    /**
     * Updates the dimensions of a rectangle at the specified coordinates.
     *
     * @param x the x-coordinate of the rectangle to update
     * @param y the y-coordinate of the rectangle to update
     * @param length the new length of the rectangle
     * @param width the new width of the rectangle
     */
    public void update(double x, double y, double length, double width) {
        Rectangle rect = root.find(x, y);
        if (rect != null) {
            rect.setDimensions(length, width);
            System.out.println("Updated rectangle at (" + x + ", " + y + ")");
        } else {
            System.out.println("Nothing to update at (" + x + ", " + y + ")");
        }
    }

    /**
     * Prints the structure of the entire quadtree.
     */
    public void dump() {
        root.dump(0);
    }

    // Inner classes for Node, LeafNode, and Rectangle
    private abstract class Node {
        protected double xMin, xMax, yMin, yMax;

        Node(double xMin, double xMax, double yMin, double yMax) {
            this.xMin = xMin;
            this.xMax = xMax;
            this.yMin = yMin;
            this.yMax = yMax;
        }

        abstract Node insert(Rectangle rect);

        abstract Rectangle find(double x, double y);

        abstract Node delete(double x, double y);

        abstract void dump(int level);
    }

    private class LeafNode extends Node {
        private List<Rectangle> rectangles = new ArrayList<>();
        private static final int MAX_CAPACITY = 5;

        LeafNode(double xMin, double xMax, double yMin, double yMax) {
            super(xMin, xMax, yMin, yMax);
        }

        @Override
        Node insert(Rectangle rect) {
            if (rectangles.size() < MAX_CAPACITY) {
                rectangles.add(rect);
                return this;
            } else {
                return split().insert(rect);
            }
        }

        @Override
        Rectangle find(double x, double y) {
            for (Rectangle rect : rectangles) {
                if (rect.contains(x, y)) {
                    return rect;
                }
            }
            return null;
        }

        @Override
        Node delete(double x, double y) {
            rectangles.removeIf(rect -> rect.contains(x, y));
            return this;
        }

        private InternalNode split() {
            InternalNode newNode = new InternalNode(xMin, xMax, yMin, yMax);
            for (Rectangle rect : rectangles) {
                newNode.insert(rect);
            }
            return newNode;
        }

        @Override
        void dump(int level) {
            for (int i = 0; i < level; i++) System.out.print("\t");
            System.out.println("LeafNode: " + rectangles);
        }
    }

    private class InternalNode extends Node {
        private Node topLeft, topRight, bottomLeft, bottomRight;

        InternalNode(double xMin, double xMax, double yMin, double yMax) {
            super(xMin, xMax, yMin, yMax);
            double xMid = (xMin + xMax) / 2;
            double yMid = (yMin + yMax) / 2;
            topLeft = new LeafNode(xMin, xMid, yMid, yMax);
            topRight = new LeafNode(xMid, xMax, yMid, yMax);
            bottomLeft = new LeafNode(xMin, xMid, yMin, yMid);
            bottomRight = new LeafNode(xMid, xMax, yMin, yMid);
        }

        @Override
        Node insert(Rectangle rect) {
            if (rect.x < (xMin + xMax) / 2) {
                if (rect.y < (yMin + yMax) / 2) {
                    bottomLeft = bottomLeft.insert(rect);
                } else {
                    topLeft = topLeft.insert(rect);
                }
            } else {
                if (rect.y < (yMin + yMax) / 2) {
                    bottomRight = bottomRight.insert(rect);
                } else {
                    topRight = topRight.insert(rect);
                }
            }
            return this;
        }

        @Override
        Rectangle find(double x, double y) {
            if (x < (xMin + xMax) / 2) {
                if (y < (yMin + yMax) / 2) {
                    return bottomLeft.find(x, y);
                } else {
                    return topLeft.find(x, y);
                }
            } else {
                if (y < (yMin + yMax) / 2) {
                    return bottomRight.find(x, y);
                } else {
                    return topRight.find(x, y);
                }
            }
        }

        @Override
        Node delete(double x, double y) {
            // Call delete on appropriate quadrant
            return this;
        }

        @Override
        void dump(int level) {
            for (int i = 0; i < level; i++) System.out.print("\t");
            System.out.println("InternalNode");
            topLeft.dump(level + 1);
            topRight.dump(level + 1);
            bottomLeft.dump(level + 1);
            bottomRight.dump(level + 1);
        }
    }

    private static class Rectangle {
        private double x, y, length, width;

        Rectangle(double x, double y, double length, double width) {
            this.x = x;
            this.y = y;
            this.length = length;
            this.width = width;
        }

        boolean contains(double px, double py) {
            return (px >= x && px <= x + length && py >= y && py <= y + width);
        }

        void setDimensions(double length, double width) {
            this.length = length;
            this.width = width;
        }

        @Override
        public String toString() {
            return String.format("Rectangle at (%.2f, %.2f): %.2fx%.2f", x, y, length, width);
        }
    }
}


