package org.howard.edu.lsp.assignment3;

import java.io.IOException;
import java.util.List;

/**
 * Defines a contract for extracting data from a source.
 */

public interface IExtractor {

	    /**
	     * Extracts data from a given source file.
	     *
	     * @param source the path to the input file
	     * @return a list of string arrays representing rows of data
	     * @throws IOException if the file cannot be read
	     */
	    List<String[]> extract(String source) throws IOException;
	}


