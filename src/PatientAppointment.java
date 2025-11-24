import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import static java.time.Duration.*;

/**
 * Represents a scheduled appointment between a patient and a doctor.
 * Instances are immutable: once created the core fields cannot be changed.
 * Comparable is implemented to allow sorting by appointment date/time (earlier first).
 */
public record PatientAppointment(PatientProfile patientProfile, DoctorProfile doctorProfile, LocalDateTime dateTime,
                                 String reason) implements Comparable<PatientAppointment> {

    private static final DateTimeFormatter DISPLAY_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Create a new appointment.
     *
     * @param patientProfile the patient; must not be null
     * @param doctorProfile  the doctor; must not be null
     * @param dateTime       appointment date/time; must not be null
     * @param reason         reason for visit; must not be null or empty
     * @throws NullPointerException     if any required parameter is null
     * @throws IllegalArgumentException if reason is empty
     */
    public PatientAppointment(PatientProfile patientProfile,
                              DoctorProfile doctorProfile,
                              LocalDateTime dateTime,
                              String reason) {
        this.patientProfile = Objects.requireNonNull(patientProfile, "patientProfile must not be null");
        this.doctorProfile = Objects.requireNonNull(doctorProfile, "doctorProfile must not be null");
        this.dateTime = Objects.requireNonNull(dateTime, "dateTime must not be null");
        this.reason = requireNonEmpty(reason);
    }

    //No-args constructor for frameworks that require it (e.g., serialization)
    public PatientAppointment() {
        this(new PatientProfile(),
                new DoctorProfile(),
                LocalDateTime.now().plusDays(5),
                "Debug Testing Appointment");
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
        if (value == null) throw new NullPointerException("reason" + " must not be null");
        String trimmed = value.trim();
        if (trimmed.isEmpty()) throw new IllegalArgumentException("reason" + " must not be empty");
        return trimmed;
    }

    /**
     * @return the patient profile for this appointment
     */
    @Override
    public PatientProfile patientProfile() {
        return patientProfile;
    }

    /**
     * @return the doctor profile for this appointment
     */
    @Override
    public DoctorProfile doctorProfile() {
        return doctorProfile;
    }

    /**
     * @return appointment date/time
     */
    @Override
    public LocalDateTime dateTime() {
        return dateTime;
    }

    /**
     * @return reason for the appointment
     */
    @Override
    public String reason() {
        return reason;
    }

    /**
     * Convenience accessor for the patient id (delegates to PatientProfile).
     *
     * @return patient id
     */
    public int getPatientId() {
        return patientProfile.getPatientId();
    }

    /**
     * Convenience accessor for the doctor badge id (delegates to DoctorProfile).
     *
     * @return doctor badge id
     */
    public int getDoctorBadgeId() {
        return doctorProfile.getBadgeId();
    }

    // Safely get patient name, handling potential nulls
    private String safePatientName() {
        try {
            return patientProfile.getLastName() + ", " + patientProfile.getFirstName();
        } catch (Exception e) {
            return "Unknown Patient";
        }
    }

    // Safely get doctor name, handling potential nulls
    private String safeDoctorName() {
        try {
            return doctorProfile.getLastName() + ", " + doctorProfile.getFirstName();
        } catch (Exception e) {
            return "Unknown Doctor";
        }
    }

    @Override
    public boolean equals(Object otherPatientAppointment) {
        if (this == otherPatientAppointment) return true;
        if (otherPatientAppointment == null || getClass() != otherPatientAppointment.getClass()) return false;

        PatientAppointment that = (PatientAppointment) otherPatientAppointment;

        // Use stable identifiers and date/time for equality
        return getPatientId() == that.getPatientId()
                && getDoctorBadgeId() == that.getDoctorBadgeId()
                && Objects.equals(dateTime, that.dateTime);
    }

    /**
     * Compare by appointment date/time, then patient id, then doctor id.
     *
     * @param other other appointment to compare
     * @return negative if this is earlier, positive if later, zero if equal
     */
    @Override
    public int compareTo(PatientAppointment other) {
        int cmp = this.dateTime.compareTo(other.dateTime);
        if (cmp != 0) return cmp;
        cmp = Integer.compare(this.getPatientId(), other.getPatientId());
        if (cmp != 0) return cmp;
        return Integer.compare(this.getDoctorBadgeId(), other.getDoctorBadgeId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPatientId(), getDoctorBadgeId(), dateTime);
    }

    @Override
    public String toString() {
        String patientName = safePatientName();
        String doctorName = safeDoctorName();
        String formatted = dateTime == null ? "N/A" : dateTime.format(DISPLAY_FORMATTER);
        return "Appointment Details:" +
                "\n---------------------------" +
                "\nPatient: " + patientName +
                "\nDoctor: " + doctorName +
                "\nDate/Time: " + formatted +
                "\nReason: " + reason;
    }

}