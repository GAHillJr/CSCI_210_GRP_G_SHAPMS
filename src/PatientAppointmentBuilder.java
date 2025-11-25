import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.function.Predicate;

/**
 * Builder class to create PatientAppointment instances via console input.
 */
public class PatientAppointmentBuilder {

    /**
     * Builds a PatientAppointment by prompting the user for input via the console.
     *
     * @param patient The patient profile for the appointment.
     * @param doctor  The doctor profile for the appointment.
     * @return A new PatientAppointment instance based on user input.
     */
    public PatientAppointment buildFromScratchViaConsole(PatientProfile patient, DoctorProfile doctor) {
        try (Scanner userInput = new Scanner(System.in)) {
            LocalDateTime dateTime = promptDateTime(userInput);
            String reason = prompt(userInput,
                    s -> !s.isBlank(), "Reason required");

            return new PatientAppointment(patient, doctor, dateTime, reason);
        }
    }

    // Helper methods for prompting user input.
    /**
     * Prompts the user for a string input with validation.
     *
     * @param userInput Scanner for user input.
     * @param validator Predicate to validate input.
     * @param errorMsg  Error message for invalid input.
     * @return Validated user input string.
     */
    private String prompt(Scanner userInput, Predicate<String> validator, String errorMsg) {
        while (true) {
            System.out.print("Reason for appointment: ");
            String line = userInput.nextLine().trim();
            if (validator.test(line)) return line;
            System.out.println(errorMsg != null ? errorMsg : "Invalid input");
        }
    }

    /**
     * Prompts the user for a LocalDateTime input with validation.
     *
     * @param sc Scanner for user input.
     * @return Validated user input LocalDateTime.
     */
    private LocalDateTime promptDateTime(Scanner sc) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        while (true) {
            System.out.print("Appointment date/time (yyyy-MM-dd HH:mm): ");
            String line = sc.nextLine().trim();
            try {
                return LocalDateTime.parse(line, fmt);
            } catch (DateTimeParseException ignored) {}
            System.out.println("Enter a valid date/time in format yyyy-MM-dd HH:mm");
        }
    }
}