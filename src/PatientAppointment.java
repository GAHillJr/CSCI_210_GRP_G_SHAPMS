import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Represents a scheduled appointment between a patient and a doctor.
 * Instances are immutable: once created the core fields cannot be changed.
 * Comparable is implemented to allow sorting by appointment date/time (earlier first).
 */
public class PatientAppointment implements Comparable<PatientAppointment> {
    // Core fields.
    String patientName;
    String doctorName;
    LocalDateTime dateTime;
    String reason;

    // Formatter for displaying date/time.
    private static final DateTimeFormatter DISPLAY_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Constructs a PatientAppointment with the given details.
     *
     * @param patientName The name of the patient.
     * @param doctorName  The name of the doctor.
     * @param dateTime    The date and time of the appointment.
     * @param reason      The reason for the appointment.
     * @throws NullPointerException     if any argument is null.
     * @throws IllegalArgumentException if reason is empty.
     */
    public PatientAppointment(String patientName, String doctorName, LocalDateTime dateTime, String reason) {
        this.patientName = Objects.requireNonNull(patientName, "patientName must not be null");
        this.doctorName = Objects.requireNonNull(doctorName, "doctorName must not be null");
        this.dateTime = Objects.requireNonNull(dateTime, "dateTime must not be null");
        this.reason = requireNonEmpty(reason);
    }

    //No-args constructor for frameworks that require it (e.g., serialization)
    public PatientAppointment() {
        this("Unknown Patient", "Unknown Doctor", LocalDateTime.now(), "No getReason provided");
    }

    /**
     * Validates that a string is non-null and non-empty after trimming.
     *
     * @param value The string to validate.
     * @return The trimmed string if valid.
     * @throws NullPointerException     if the string is null.
     * @throws IllegalArgumentException if the string is empty after trimming.
     */
    private static String requireNonEmpty(String value) {
        if (value == null) throw new NullPointerException("getReason" + " must not be null");
        String trimmed = value.trim();
        if (trimmed.isEmpty())
            throw new IllegalArgumentException("getReason" + " must not be empty");
        return trimmed;
    }

    // Setters and Getters.

    /**
     * @param patientName patient name
     */
    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }


    /**
     * @return patient name
     */
    public String getPatientName() {
        return patientName;
    }

    /**
     * @param doctorName doctor name
     */
    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    /**
     * @return doctor name
     */
    public String getDoctorName() {
        return doctorName;
    }

    /**
     * @return appointment date/time
     */
    public LocalDateTime dateTime() {
        return dateTime;
    }

    /**
     * @return getReason for the appointment
     */
    public String getReason() {
        return reason;
    }


    @Override
    public boolean equals(Object otherPatientAppointment) {
        if (this == otherPatientAppointment) return true;
        if (otherPatientAppointment == null || getClass() != otherPatientAppointment.getClass())
            return false;

        PatientAppointment that = (PatientAppointment) otherPatientAppointment;

        // Use stable identifiers and date/time for equality
        return Objects.equals(this.patientName, that.patientName) &&
                Objects.equals(this.doctorName, that.doctorName) &&
                Objects.equals(this.dateTime, that.dateTime);
    }

    @Override
    public int compareTo(PatientAppointment other) {
        int cmp = this.dateTime.compareTo(other.dateTime);
        if (cmp != 0) return cmp;
        cmp = this.patientName.compareTo(other.patientName);
        if (cmp != 0) return cmp;
        return this.doctorName.compareTo(other.doctorName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(patientName, doctorName, dateTime);
    }

    @Override
    public String toString() {
        String formatted = dateTime == null ? "N/A" : dateTime.format(DISPLAY_FORMATTER);
        return "Appointment Details:" +
                "\n---------------------------" +
                "\nPatient: " + patientName +
                "\nDoctor: " + doctorName +
                "\nDate/Time: " + formatted +
                "\nReason: " + reason;
    }

}