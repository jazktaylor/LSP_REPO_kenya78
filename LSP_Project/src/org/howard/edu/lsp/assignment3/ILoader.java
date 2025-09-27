package org.howard.edu.lsp.assignment3;

import java.io.IOException;
import java.util.List;

/**
 * Defines a contract for loading data into a destination.
 */
public interface ILoader {
	 /**
     * Loads transformed data into the given destination.
     *
     * @param destination the output file path
     * @param data the transformed data
     * @throws IOException if the file cannot be written
     */
    void load(String destination, List<String[]> data) throws IOException;

}
