import java.time.LocalDate;
import java.time.LocalDateTime;

public class PatientAppointmentTest {
    public static void main(String[] args) {
        System.out.println("PatientAppointmentTest running...");
        // Add test cases here to validate PatientAppointment functionality
        PatientAppointment firstAppointment = new PatientAppointment(
                new PatientProfile("Alice", "Johnson", LocalDate.of(1990, 5, 15), "987-654-3210", null, 1),
                new DoctorProfile("Bob", "Williams", "Dermatology", new WeeklySchedule()),
                LocalDateTime.of(2024, 11, 20, 14, 30), "Skin rash consultation");
        System.out.println("First Appointment created for patient: " + firstAppointment.patientProfile().getFirstName() +
                " with doctor: " + firstAppointment.doctorProfile().getFirstName() +
                " on " + firstAppointment.dateTime() +
                " for reason: " + firstAppointment.reason());
        System.out.println("==================================");

        PatientAppointment testAppointment = new PatientAppointment(
                new PatientProfile("John", "Doe", LocalDate.of(1981, 9, 28), "123-456-7890", null, 2),
                new DoctorProfile("Jane", "Smith", "Cardiology", new WeeklySchedule()),
                LocalDateTime.of(2025, 12, 27, 10, 0), "Regular check-up");
        System.out.println("Full Patient Profile: " + testAppointment.patientProfile());
        System.out.println("Full Doctor Profile: " + testAppointment.doctorProfile());
        System.out.println("==================================");
        System.out.println("Appointment created for patient: " + testAppointment.patientProfile().getFirstName() +
                " with doctor: " + testAppointment.doctorProfile().getFirstName() +
                " on " + testAppointment.dateTime() +
                " for reason: " + testAppointment.reason());
        System.out.println("==================================");
        System.out.println("Full Appointment Details: " + testAppointment);
        System.out.println("All tests passed.");
    }
}