package org.howard.edu.lsp.assignment2;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class ETLPipeline {
    public static void main(String[] args) {
        String folderName = "data";

        // Ensure the data folder exists
        File folder = new File(folderName);
        if (!folder.exists()) {
            folder.mkdir(); // create folder if missing
            System.out.println("Created folder: " + folder.getAbsolutePath());
        }

        String inputFile = folderName + "/products.csv";
        String outputFile = folderName + "/transformed_products.csv";

        try {
            // Check if input CSV exists
            File fileCheck = new File(inputFile);
            if (!fileCheck.exists()) {
                System.out.println("Input file not found at: " + fileCheck.getAbsolutePath());
                System.out.println("Please add 'products.csv' to the data folder and rerun.");
                return;
            }

            // Extract
            List<String[]> rawData = extract(inputFile);

            // Transform
            List<String[]> transformedData = transform(rawData);

            // Load
            load(outputFile, transformedData);

            System.out.println("ETL process completed successfully.");
            System.out.println("Output file created: " + outputFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Extract: read CSV file into a list of string arrays
    public static List<String[]> extract(String filename) throws IOException {
        List<String[]> data = new ArrayList<>();
        List<String> lines = Files.readAllLines(Paths.get(filename));
        for (String line : lines) {
            data.add(line.split(",")); // simple CSV parsing
        }
        return data;
    }

    // Transform: trim whitespace from all fields
    public static List<String[]> transform(List<String[]> data) {
        for (String[] row : data) {
            for (int i = 0; i < row.length; i++) {
                row[i] = row[i].trim();
            }
        }
        return data;
    }

    // Load: write the transformed data to CSV (creates new file if missing)
    public static void load(String filename, List<String[]> data) throws IOException {
        File outputFile = new File(filename);
        if (!outputFile.exists()) {
            outputFile.createNewFile(); // create file if it doesn't exist
        }

        BufferedWriter writer = Files.newBufferedWriter(Paths.get(filename));
        for (String[] row : data) {
            writer.write(String.join(",", row));
            writer.newLine();
        }
        writer.close();
    }
}
