package com.hemebiotech.analytics;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of ISymptomReader that reads symptom data from a file.
 * Each line of the file is expected to contain one symptom.
 * Duplicates are not removed or processed by this implementation.
 */
public class ReadSymptomDataFromFile implements ISymptomReader {

	private String filepath;

	/**
	 * Constructor to initialize the file path.
	 * 
	 * @param filepath The full or partial path to the file containing symptom
	 *                 strings, one per line.
	 */
	public ReadSymptomDataFromFile(String filepath) {
		this.filepath = filepath;
	}

	/**
	 * Reads symptoms from the file and returns them as a list of strings.
	 * Each line in the file corresponds to one symptom. Duplicates are not removed.
	 * 
	 * @return A list of symptoms read from the file. If an error occurs, an empty
	 *         list is returned.
	 */
	@Override
	public List<String> getSymptoms() {
		ArrayList<String> result = new ArrayList<>();

		try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
			String line;
			while ((line = reader.readLine()) != null) {
				result.add(line);
			}
		} catch (Exception e) {
			System.err.println("Erreur lors de la lecture du fichier : " + e.getMessage());
		}
		return result;
	}

}