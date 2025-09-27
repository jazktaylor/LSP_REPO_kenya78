package org.howard.edu.lsp.assignment3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Extracts data from a CSV file by reading its lines.
 */

public interface CSVExtractor {
	/**
     * Reads a CSV file and splits each line into fields.
     *
     * @param source the CSV file path
     * @return a list of string arrays representing rows of data
     * @throws IOException if the file cannot be read
     */
    public static List<String[]> extract(String source) throws IOException {
        List<String[]> data = new ArrayList<>();
        List<String> lines = Files.readAllLines(Paths.get(source));
        for (String line : lines) {
            data.add(line.split(","));
        }
        return data;
    }
}
