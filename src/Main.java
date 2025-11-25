/**
 * Main class to demonstrate the PatientProfileBuilder functionality.
 */
public class Main {
    public static void main(String[] args) {
        PatientProfileBuilder builder = new PatientProfileBuilder();
        PatientProfile profile = builder.buildFromScratchViaConsole();
        System.out.println("\nCreated profile:");
        System.out.println(profile);
    }
}
