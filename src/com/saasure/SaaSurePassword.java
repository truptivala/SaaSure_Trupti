package com.saasure;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SaaSurePassword {
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Enter filename with path: ");

		Scanner scanner = null;
		File file = null;
		try {
			file = new File(br.readLine());
			// Create file Scanner object.
			scanner = new Scanner(file);

			Pattern patternVowel, pattern3ConsVowel, pattern2ConsOccurance;
			Matcher matchVowel, match3ConsVowel, match2ConsOccurance;

			// Regular expressions for three conditions.
			final String regexVowel = "[aeiou]";
			final String regex3ConsVowel = "[a-z&&[^aeiou]][a-z&&[^aeiou]][a-z&&[^aeiou]]|[aeiou][aeiou][aeiou]";
			final String regex2ConsOccurance = "([a-df-np-z])\\1";

			// Create pattern object.
			patternVowel = Pattern
					.compile(regexVowel, Pattern.CASE_INSENSITIVE);
			pattern3ConsVowel = Pattern.compile(regex3ConsVowel,
					Pattern.CASE_INSENSITIVE);
			pattern2ConsOccurance = Pattern.compile(regex2ConsOccurance,
					Pattern.CASE_INSENSITIVE);

			// Read file line by line
			while (scanner.hasNextLine()) {

				// Read next line from file.
				String input = scanner.nextLine();

				// Check if end of the file.
				if (input.equals("end"))
					break;

				matchVowel = patternVowel.matcher(input);
				match3ConsVowel = pattern3ConsVowel.matcher(input);
				match2ConsOccurance = pattern2ConsOccurance.matcher(input);

				// Check password length is greater then 0 and less then 21.
				// Password validation using regular expression.
				if (input.length() > 0 && input.length() <= 20
						&& matchVowel.find() && !match3ConsVowel.find()
						&& !match2ConsOccurance.find()) {
					System.out.println("<" + input + "> is acceptable");
				} else {
					System.out.println("<" + input + "> is not acceptable");
				}

			}

		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
		} catch (Exception e) {
			System.out.println("Some error occured, please try again.");
		} finally {
			if (scanner != null)
				scanner.close();
		}

	}
}