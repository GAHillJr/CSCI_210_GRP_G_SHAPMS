import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.Stack;
import java.util.function.Predicate;

/**
 * Builder class to create PatientProfile instances via console input.
 */

public class PatientProfileBuilder {

    /**
     * Builds a PatientProfile by prompting the user for input via the console.
     *
     * @return A new PatientProfile instance based on user input.
     */
    public PatientProfile buildFromScratchViaConsole() {
        try (Scanner userInput = new Scanner(System.in)) {
            String first = prompt(userInput, "First name (required): ", s -> !s.isBlank(), "Name required");
            String last = prompt(userInput, "Last name (required): ", s -> !s.isBlank(), "Name required");
            LocalDate dob = promptDate(userInput, "Date of birth (yyyy-MM-dd or MM/dd/yyyy): ");
            String phoneNumber = prompt(userInput, "Phone Number: ", s -> !s.isBlank(), "Invalid phone " +
                    "Number");
            Stack<PatientAppointment> medicalHistory = new Stack<>();
            medicalHistory.push(new PatientAppointment()); // ADDED FOR HOSPITALDATAMANAGER: use push() instead of addFirst()
            return new PatientProfile(first, last, dob, phoneNumber, medicalHistory);
        }
    }

    // Helper methods for prompting user input.

    /**
     * Prompts the user for a string input with validation.
     *
     * @param userInput Scanner for user input.
     * @param message   Prompt message.
     * @param validator Predicate to validate input.
     * @param errorMsg  Error message for invalid input.
     * @return Validated user input string.
     */
    private String prompt(Scanner userInput, String message, Predicate<String> validator, String errorMsg) {
        while (true) {
            System.out.print(message);
            String line = userInput.nextLine().trim();
            if (validator.test(line)) return line;
            System.out.println(errorMsg != null ? errorMsg : "Invalid input");
        }
    }

    /**
     * Prompts the user for a date input with multiple accepted formats.
     *
     * @param userInput Scanner for user input.
     * @param message   Prompt message.
     * @return Parsed LocalDate or null if input is blank.
     */
    private LocalDate promptDate(Scanner userInput, String message) {
        DateTimeFormatter[] formatters = new DateTimeFormatter[]{
                DateTimeFormatter.ISO_LOCAL_DATE,
                DateTimeFormatter.ofPattern("M/d/yyyy"),
                DateTimeFormatter.ofPattern("M/d/yy"),
                DateTimeFormatter.ofPattern("d/M/yyyy")
        };
        while (true) {
            System.out.print(message);
            String line = userInput.nextLine().trim();
            if (line.isBlank()) return null;
            for (DateTimeFormatter f : formatters) {
                try {
                    return LocalDate.parse(line, f);
                } catch (DateTimeParseException ignored) {
                }
            }
            System.out.println("Invalid date format. Expected yyyy-MM-dd or MM/dd/yyyy or dd/MM/yyyy.");
        }
    }
}