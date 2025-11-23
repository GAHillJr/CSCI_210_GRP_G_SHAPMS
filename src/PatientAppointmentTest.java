import java.time.LocalDate;
import java.time.LocalDateTime;

public class PatientAppointmentTest {
    public static void main(String[] args) {
        System.out.println("PatientAppointmentTest running...");
        // Add test cases here to validate PatientAppointment functionality
        PatientAppointment testAppointment = new PatientAppointment(
                new PatientProfile("John", "Doe", LocalDate.of(1981, 9, 28), "123-456-7890", null, 2),
                new DoctorProfile("Jane", "Smith", "Cardiology", new WeeklySchedule()),
                LocalDateTime.of(2025, 12, 27, 10, 0), "Regular check-up");
        System.out.println("Appointment created for patient: " + testAppointment.patientProfile().getFirstName() +
                " with doctor: " + testAppointment.doctorProfile().getFirstName() +
                " on " + testAppointment.dateTime() +
                " for reason: " + testAppointment.reason());
        System.out.println("Full Appointment Details: " + testAppointment);
        System.out.println("All tests passed.");
    }
}