
package com.hemebiotech.analytics;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * The AnalyticsCounter class manages the entire process of symptom analysis:
 * - Reading symptoms from a data source.
 * - Counting the occurrences of each symptom.
 * - Sorting symptoms alphabetically.
 * - Writing the results to a destination (a file).
 */
public class AnalyticsCounter {

	// Interfaces for reading and writing symptom data
	private ISymptomReader reader;
	private ISymptomWriter writer;

	/**
	 * Constructor for the AnalyticsCounter class.
	 * 
	 * @param reader The instance of ISymptomReader used to read symptoms.
	 * @param writer The instance of ISymptomWriter used to write the results.
	 */
	public AnalyticsCounter(ISymptomReader reader, ISymptomWriter writer) {
		this.reader = reader;
		this.writer = writer;
	}

	/**
	 * Method to read symptoms from the data source.
	 * 
	 * @return A list of symptoms as strings.
	 */
	public List<String> getSymptoms() {
		return reader.getSymptoms();
	}

	/**
	 * Method to count the occurrences of each symptom in the list.
	 * 
	 * @param symptoms The list of symptoms to analyze.
	 * @return A Map associating each symptom with its count (occurrences).
	 */
	public Map<String, Integer> countSymptoms(List<String> symptoms) {
		Map<String, Integer> counters = new HashMap<>();
		symptoms.forEach(symptom -> {
			counters.put(symptom, counters.getOrDefault(symptom, 0) + 1);
		});

		return counters;
	}

	/**
	 * Method to sort the symptoms alphabetically by their names.
	 * 
	 * @param symptoms The Map of symptoms to be sorted.
	 * @return A sorted Map of symptoms, with symptoms as keys.
	 */
	public Map<String, Integer> sortSymptoms(Map<String, Integer> symptoms) {
		return new TreeMap<>(symptoms);
	}

	/**
	 * Method to write the symptom results to the output (e.g., file).
	 * 
	 * @param symptoms The Map of symptoms to write to the destination.
	 */
	public void writeSymptoms(Map<String, Integer> symptoms) {
		writer.writeSymptoms(symptoms);
	}

	/**
	 * The main method that orchestrates the entire symptom analysis process.
	 * It reads, counts, sorts, and writes the symptoms to the destination.
	 */
	public void exe() {
		try {
			List<String> symptoms = getSymptoms();

			if (symptoms == null || symptoms.isEmpty()) {
				throw new Exception("No symptoms found.");
			}

			Map<String, Integer> countedSymptoms = countSymptoms(symptoms);
			Map<String, Integer> sortedSymptoms = sortSymptoms(countedSymptoms);
			writeSymptoms(sortedSymptoms);

			System.out.println("Analysis completed. Results are saved in the result file.");

		} catch (Exception e) {
			System.err.println("An error occurred during the analysis.");
		}
	}
}
