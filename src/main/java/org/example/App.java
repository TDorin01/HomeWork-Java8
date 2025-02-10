package org.example;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        String inputFile = "C:\\Users\\Cristi\\IdeaProjects\\HomeWork-Java8\\input.csv";   // Input file path
        String outputFile = "C:\\Users\\Cristi\\IdeaProjects\\HomeWork-Java8\\output.csv"; // Output file path
        int targetMonth = 5; // Change this value to filter by another month (exemple May)

        try {
            List<String> result = Files.lines(Paths.get(inputFile))
                    .map(line -> line.split(",")) // Split CSV format (FirstName,LastName,DateOfBirth)
                    .filter(parts -> parts.length == 3) // Ensure valid format
                    .filter(parts -> isMonthMatch(parts[2], targetMonth)) // Filter by month
                    .map(parts -> parts[0] + " " + parts[1]) // Extract FirstName LastName
                    .sorted() // Sort alphabetically
                    .collect(Collectors.toList());

            // Write to output file
            Files.write(Paths.get(outputFile), result);

            System.out.println("Filtered and sorted data written to " + outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Function to check if the date of birth matches the target month
    private static boolean isMonthMatch(String dateStr, int month) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(dateStr.trim(), formatter);
            return date.getMonthValue() == month;
        } catch (Exception e) {
            System.err.println("Invalid date format: " + dateStr);
            return false;
        }
    }

}
