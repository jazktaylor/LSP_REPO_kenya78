package org.howard.edu.lsp.assignment3;


import java.util.List;

/**
 * Defines a contract for transforming data.
 */


public interface ITransformer {
	/**
    * Transforms raw data into a modified format or structure.
    *
    * @param data the raw data
    * @return the transformed data
    */
   List<String[]> transform(List<String[]> data);
}
