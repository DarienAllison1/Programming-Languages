package com.darienallison.quadtreeproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * The Main class provides a command-line interface for manipulating a quadtree data structure.
 * It reads commands from a file and executes operations such as inserting, finding, deleting, updating,
 * and dumping rectangles in the quadtree.
 */
public class Main {

    /**
     * The main method reads commands from a file and processes them to perform operations on a quadtree.
     *
     * @param args the command line arguments, where the first argument should be the path to the command file.
     */
    public static void main(String[] args) {
        // Check if a file argument is provided
        if (args.length == 0) {
            System.out.println("Please provide a command file as an argument.");
            return;
        }

        // Print the provided file path to verify it's being passed correctly
        System.out.println("File path provided: " + args[0]);

        // Create a File object with the provided path and check if it exists
        File commandFile = new File(args[0]);
        if (!commandFile.exists()) {
            System.out.println("Command file not found at: " + args[0]);
            return;
        } else {
            System.out.println("Command file found. Proceeding with processing...");
        }

        // Initialize the quadtree
        Quadtree quadtree = new Quadtree();

        // Process the command file
        try (Scanner scanner = new Scanner(commandFile)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                
                // Skip blank lines
                if (line.isEmpty()) {
                    continue;
                }

                String[] command = line.split("\\s+");
                try {
                    switch (command[0]) {
                        case "Insert":
                            if (command.length < 5) {
                                System.out.println("Error: Insert requires four parameters (x, y, length, width).");
                                break;
                            }
                            quadtree.insert(
                                Double.parseDouble(command[1]), 
                                Double.parseDouble(command[2]),
                                Double.parseDouble(command[3]), 
                                Double.parseDouble(command[4])
                            );
                            break;
                        case "Find":
                            if (command.length < 3) {
                                System.out.println("Error: Find requires two parameters (x, y).");
                                break;
                            }
                            quadtree.find(Double.parseDouble(command[1]), Double.parseDouble(command[2]));
                            break;
                        case "Delete":
                            if (command.length < 3) {
                                System.out.println("Error: Delete requires two parameters (x, y).");
                                break;
                            }
                            quadtree.delete(Double.parseDouble(command[1]), Double.parseDouble(command[2]));
                            break;
                        case "Update":
                            if (command.length < 5) {
                                System.out.println("Error: Update requires four parameters (x, y, length, width).");
                                break;
                            }
                            quadtree.update(
                                Double.parseDouble(command[1]), 
                                Double.parseDouble(command[2]),
                                Double.parseDouble(command[3]), 
                                Double.parseDouble(command[4])
                            );
                            break;
                        case "Dump":
                            quadtree.dump();
                            break;
                        default:
                            System.out.println("Unknown command: " + command[0]);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number format in command: " + line);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Missing arguments in command: " + line);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Command file not found.");
        }
    }
}
