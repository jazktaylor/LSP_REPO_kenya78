package org.howard.edu.lsp.assignment3;


import java.io.*;
import java.util.*;

/**
 * The {@code ETLPipeline} class represents an Extract-Transform-Load pipeline.
 * It extracts data from a source, transforms it, and loads it into a destination.
 */
public class ETLPipeline {
    private final IExtractor extractor;
    private final ITransformer transformer;
    private final ILoader loader;

    /**
     * Interface for defining data extraction logic.
     */
    public interface IExtractor {
        /**
         * Extract data from a given source.
         *
         * @param source the input file path or data source
         * @return a list of string arrays containing the extracted rows
         * @throws IOException if an I/O error occurs
         */
        List<String[]> extract(String source) throws IOException;
    }

    /**
     * Interface for defining data transformation logic.
     */
    public interface ITransformer {
        /**
         * Transform the provided data.
         *
         * @param data the list of string arrays to transform
         * @return the transformed list of string arrays
         */
        List<String[]> transform(List<String[]> data);
    }

    /**
     * Interface for defining data loading logic.
     */
    public interface ILoader {
        /**
         * Load the transformed data into a destination.
         *
         * @param destination the output file path
         * @param data the list of transformed string arrays
         * @throws IOException if an I/O error occurs
         */
        void load(String destination, List<String[]> data) throws IOException;
    }

    /**
     * Extractor for reading CSV files.
     */
    public static class CSVExtractor implements IExtractor {
        /**
         * Default constructor for CSVExtractor.
         */
        public CSVExtractor() {}

        @Override
        public List<String[]> extract(String source) throws IOException {
            List<String[]> data = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader(source))) {
                String line;
                while ((line = br.readLine()) != null) {
                    data.add(line.split(","));
                }
            }
            return data;
        }
    }

    /**
     * Transformer that trims whitespace from CSV fields.
     */
    public static class TrimTransformer implements ITransformer {
        /**
         * Default constructor for TrimTransformer.
         */
        public TrimTransformer() {}

        @Override
        public List<String[]> transform(List<String[]> data) {
            List<String[]> transformed = new ArrayList<>();
            for (String[] row : data) {
                String[] newRow = Arrays.stream(row)
                        .map(String::trim)
                        .toArray(String[]::new);
                transformed.add(newRow);
            }
            return transformed;
        }
    }

    /**
     * Loader that writes CSV data to a file.
     */
    public static class CSVLoader implements ILoader {
        /**
         * Default constructor for CSVLoader.
         */
        public CSVLoader() {}

        @Override
        public void load(String destination, List<String[]> data) throws IOException {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(destination))) {
                for (String[] row : data) {
                    bw.write(String.join(",", row));
                    bw.newLine();
                }
            }
        }
    }

    /**
     * Constructs a new ETLPipeline.
     *
     * @param extractor the extractor component
     * @param transformer the transformer component
     * @param loader the loader component
     */
    public ETLPipeline(IExtractor extractor, ITransformer transformer, ILoader loader) {
        this.extractor = extractor;
        this.transformer = transformer;
        this.loader = loader;
    }

    /**
     * Executes the ETL pipeline on the given files.
     *
     * @param inputFile the path to the input file
     * @param outputFile the path to the output file
     * @throws IOException if an I/O error occurs
     */
    public void run(String inputFile, String outputFile) throws IOException {
        List<String[]> data = extractor.extract(inputFile);
        List<String[]> transformedData = transformer.transform(data);
        loader.load(outputFile, transformedData);
    }

    /**
     * Entry point for running the ETL pipeline.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        ETLPipeline pipeline = new ETLPipeline(
                new CSVExtractor(),
                new TrimTransformer(),
                new CSVLoader()
        );

        try {
            pipeline.run("input.csv", "output.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
