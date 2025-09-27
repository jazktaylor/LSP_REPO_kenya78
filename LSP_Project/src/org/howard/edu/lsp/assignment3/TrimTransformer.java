package org.howard.edu.lsp.assignment3;

import java.util.List;

/**
 * Transforms data by trimming whitespace from all fields.
 */
public interface TrimTransformer {
    /**
     * Removes leading and trailing whitespace from each field in the dataset.
     *
     * @param data the raw data
     * @return the transformed data with trimmed fields
     */
    public static List<String[]> transform(List<String[]> data) {
        for (String[] row : data) {
            for (int i = 0; i < row.length; i++) {
                row[i] = row[i].trim();
            }
        }
        return data;
    }
}
