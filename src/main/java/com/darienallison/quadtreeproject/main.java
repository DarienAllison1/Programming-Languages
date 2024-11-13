/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.darienallison.quadtreeproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Main class for running the Quadtree program. This program processes commands 
 * from a specified .cmmd file and performs actions on a Quadtree data structure.
 * Supported commands include insert, find, delete, update, and dump.
 * 
 * Usage: java Main <path to .cmmd file>
 * 
 * Each line in the command file should follow one of these formats:
 * - Insert X Y L W
 * - Find X Y
 * - Delete X Y
 * - Update X Y L W
 * - Dump
 * 
 * This class initializes the Quadtree and processes commands from the file.
 * 
 * @author BUG
 */
public class Main {

    /**
     * Main method that initializes the Quadtree, reads commands from a file,
     * and executes them on the Quadtree data structure.
     * 
     * @param args the command-line arguments. Expects a single argument: the file path to a .cmmd file.
     */
    public static void main(String[] args) {
        // Check if the program received a command-line argument
        if (args.length != 1) {
            System.out.println("Usage: java Main <path to .cmmd file>");
            return;
        }

        String filePath = args[0];
        System.out.println("Received file path: " + filePath);  // Debug statement

        Quadtree quadtree = new Quadtree();

        // Try opening and reading the file
        try (Scanner fileScanner = new Scanner(new File(filePath))) {
            System.out.println("Reading commands from file...");  // Debug statement
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();
                System.out.println("Processing command: " + line);  // Debug statement

                String[] parts = line.split("\\s+");
                if (parts.length == 0) continue;

                String command = parts[0].toLowerCase();
                switch (command) {
                    case "insert":
                        handleInsert(parts, quadtree);
                        break;

                    case "find":
                        handleFind(parts, quadtree);
                        break;

                    case "delete":
                        handleDelete(parts, quadtree);
                        break;

                    case "update":
                        handleUpdate(parts, quadtree);
                        break;

                    case "dump":
                        System.out.println("Quadtree structure:");
                        System.out.println(quadtree.dump());
                        break;

                    default:
                        System.out.println("Unknown command: " + command);
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filePath);
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid number format in file.");
        }
    }

    /**
     * Handles the insert command.
     *
     * @param parts the array of command arguments, including "insert" and rectangle details.
     * @param quadtree the Quadtree to insert the rectangle into.
     */
    private static void handleInsert(String[] parts, Quadtree quadtree) {
        if (parts.length != 5) {
            System.out.println("Invalid insert command format.");
            return;
        }
        double x = Double.parseDouble(parts[1]);
        double y = Double.parseDouble(parts[2]);
        double length = Double.parseDouble(parts[3]);
        double width = Double.parseDouble(parts[4]);
        Rectangle rect = new Rectangle(x, y, length, width);
        if (!quadtree.insert(rect)) {
            System.out.println("You cannot double insert at a position.");
            return;
        }
        System.out.println("Inserted: " + rect);
    }

    /**
     * Handles the find command.
     *
     * @param parts the array of command arguments, including "find" and coordinates.
     * @param quadtree the Quadtree to search for the rectangle in.
     */
    private static void handleFind(String[] parts, Quadtree quadtree) {
        if (parts.length != 3) {
            System.out.println("Invalid find command format.");
            return;
        }
        double x = Double.parseDouble(parts[1]);
        double y = Double.parseDouble(parts[2]);
        Rectangle found = quadtree.find(x, y);
        if (found != null) {
            System.out.println(found);
        } else {
            System.out.println("Nothing is at (" + x + ", " + y + ").");
        }
    }

    /**
     * Handles the delete command.
     *
     * @param parts the array of command arguments, including "delete" and coordinates.
     * @param quadtree the Quadtree to delete the rectangle from.
     */
    private static void handleDelete(String[] parts, Quadtree quadtree) {
        if (parts.length != 3) {
            System.out.println("Invalid delete command format.");
            return;
        }
        double x = Double.parseDouble(parts[1]);
        double y = Double.parseDouble(parts[2]);
        boolean deleted = quadtree.delete(x, y);
        if (deleted) {
            System.out.println("Deleted rectangle at (" + x + ", " + y + ")");
        } else {
            System.out.println("Nothing to delete at (" + x + ", " + y + ").");
        }
    }

    /**
     * Handles the update command.
     *
     * @param parts the array of command arguments, including "update", coordinates, and new dimensions.
     * @param quadtree the Quadtree where the rectangle to update is located.
     */
    private static void handleUpdate(String[] parts, Quadtree quadtree) {
        if (parts.length != 5) {
            System.out.println("Invalid update command format.");
            return;
        }
        double x = Double.parseDouble(parts[1]);
        double y = Double.parseDouble(parts[2]);
        double length = Double.parseDouble(parts[3]);
        double width = Double.parseDouble(parts[4]);
        Rectangle toUpdate = quadtree.find(x, y);
        if (toUpdate != null) {
            toUpdate.setLength(length);
            toUpdate.setWidth(width);
            System.out.println("Updated: " + toUpdate);
        } else {
            System.out.println("Nothing to update at (" + x + ", " + y + ").");
        }
    }
}


