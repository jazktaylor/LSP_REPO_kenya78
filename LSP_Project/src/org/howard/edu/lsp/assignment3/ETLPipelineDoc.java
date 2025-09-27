package org.howard.edu.lsp.assignment3;



import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Orchestrates the Extract-Transform-Load (ETL) pipeline process.
 */
public class ETLPipelineDoc {

	 private IExtractor extractor;
	 private ITransformer transformer;
	 private ILoader loader;

	    /**
	     * Constructs an ETLPipeline with the given components.
	     *
	     * @param extractor the data extractor
	     * @param transformer the data transformer
	     * @param loader the data loader
	     */
	    public void ETLPipeline(IExtractor extractor, ITransformer transformer, ILoader loader) {
	        this.extractor = extractor;
	        this.transformer = transformer;
	        this.loader = loader;
	    }

	    /**
	     * Executes the ETL pipeline on a given input and output file.
	     *
	     * @param inputFile the input CSV file path
	     * @param outputFile the output CSV file path
	     * @throws IOException if an I/O error occurs during processing
	     */
	    public void run(String inputFile, String outputFile) throws IOException {
	        List<String[]> data = extractor.extract(inputFile);
	        List<String[]> transformedData = transformer.transform(data);
	        loader.load(outputFile, transformedData);
	    }

	    /**
	     * Main entry point for running the ETL pipeline.
	     *
	     * @param args unused command-line arguments
	     */
	    public static void main(String[] args) {
	        String folderName = "data";
	        File folder = new File(folderName);
	        if (!folder.exists()) {
	            folder.mkdir();
	            System.out.println("Created folder: " + folder.getAbsolutePath());
	        }

	        String inputFile = folderName + "/products.csv";
	        String outputFile = folderName + "/transformed_products.csv";

	        try {
	            File fileCheck = new File(inputFile);
	            if (!fileCheck.exists()) {
	                System.out.println("Input file not found at: " + fileCheck.getAbsolutePath());
	                System.out.println("Please add 'products.csv' to the data folder and rerun.");
	                return;
	            }

	            ETLPipelineDoc pipeline = new ETLPipeline(
	                new CSVExtractor(),
	                new TrimTransformer(),
	                new CSVLoader()
	            );
	            pipeline.run(inputFile, outputFile);

	            System.out.println("ETL process completed successfully.");
	            System.out.println("Output file created: " + outputFile);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}