package com.hemebiotech.analytics;

/**
 * Main class
 */
public class Main {

    /**
     * The main method which serves as the entry point of the application.
     * It sets up the necessary components for reading, processing, and writing
     * symptom data.
     * 
     * @param args Command-line arguments (not used in this case).
     */
    public static void main(String[] args) {

        try {
            // Create the reader instance that reads symptoms from a file
            // "symptoms.txt" contains the symptom data, one symptom per line
            ISymptomReader symptomsDataReader = new ReadSymptomDataFromFile("symptoms.txt");

            // Create the writer instance that writes the results to a file
            // "result.txt" will store the symptoms with their counts
            ISymptomWriter symptomDataWriter = new WriteSymptomDataToFile("result.txt");

            // Create the AnalyticsCounter instance, passing the reader and writer
            // This object will be responsible for processing the symptoms
            AnalyticsCounter analyticsCounter = new AnalyticsCounter(symptomsDataReader, symptomDataWriter);

            // Execute the analysis: read symptoms, count, sort, and write the results
            analyticsCounter.exe();

        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }
    }

}