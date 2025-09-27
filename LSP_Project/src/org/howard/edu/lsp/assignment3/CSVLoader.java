package org.howard.edu.lsp.assignment3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Loads data into a CSV file by writing rows of fields.
 */

public interface CSVLoader {
    /**
     * Writes data to the specified destination file in CSV format.
     *
     * @param destination the output CSV file path
     * @param data the transformed data
     * @throws IOException if the file cannot be written
     */
    public static void load(String destination, List<String[]> data) throws IOException {
        File outputFile = new File(destination);
        if (!outputFile.exists()) {
            outputFile.createNewFile();
        }

        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(destination))) {
            for (String[] row : data) {
                writer.write(String.join(",", row));
                writer.newLine();
            }
        }
    }
}



