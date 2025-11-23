import java.util.Objects;

/**
 * This class represents a patient appointment in a hospital management system.
 * It includes functionalities to:
 * Create appointment (patient ID, doctor ID, date/time, reason).
 * Maintain appointment details and provide accessors.
 */
public class PatientAppointment {
    public PatientProfile PatientProfile;
    public DoctorProfile DoctorProfile;
    public String dateTime;
    public String reason;

    /**
     * Constructor to initialize a PatientAppointment object.
     *
     * @param patientProfile The profile of the patient.
     * @param doctorProfile  The profile of the doctor.
     * @param dateTime       The date and time of the appointment.
     * @param reason         The reason for the appointment.
     */
    public PatientAppointment(PatientProfile patientProfile, DoctorProfile doctorProfile, String dateTime, String reason) {
        this.PatientProfile = patientProfile;
        this.DoctorProfile = doctorProfile;
        this.dateTime = dateTime;
        this.reason = reason;
    }
    // Getters and Setters

    /**
     * Gets the patient profile.
     *
     * @return The patient profile.
     */
    public PatientProfile getPatientProfile() {
        return PatientProfile;
    }

    /**
     * Sets the patient profile.
     *
     * @param patientProfile The patient profile to set.
     */
    public void setPatientProfile(PatientProfile patientProfile) {
        PatientProfile = patientProfile;
    }

    /**
     * Gets the doctor profile.
     *
     * @return The doctor profile.
     */
    public DoctorProfile getDoctorProfile() {
        return DoctorProfile;
    }

    /**
     * Sets the doctor profile.
     *
     * @param doctorProfile The doctor profile to set.
     */
    public void setDoctorProfile(DoctorProfile doctorProfile) {
        DoctorProfile = doctorProfile;
    }

    /**
     * Gets the date and time of the appointment.
     *
     * @return The date and time of the appointment.
     */
    public String getDateTime() {
        return dateTime;
    }

    /**
     * Sets the date and time of the appointment.
     *
     * @param dateTime The date and time to set for the appointment.
     */
    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * Gets the reason for the appointment.
     *
     * @return The reason for the appointment.
     */
    public String getReason() {
        return reason;
    }

    /**
     * Sets the reason for the appointment.
     *
     * @param reason The reason to set for the appointment.
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    // Equals and HashCode based on all fields
    public boolean equals(Object otherPatientAppointment) {
        if (otherPatientAppointment == null || getClass() != otherPatientAppointment.getClass()) return false;
        PatientAppointment that = (PatientAppointment) otherPatientAppointment;
        return Objects.equals(getPatientProfile(), that.getPatientProfile()) && Objects.equals(getDoctorProfile(), that.getDoctorProfile()) && Objects.equals(getDateTime(), that.getDateTime()) && Objects.equals(getReason(), that.getReason());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPatientProfile(), getDoctorProfile(), getDateTime(), getReason());
    }

    @Override
    // String representation of the appointment
    public String toString() {
        return "Appointment Details:" +
                "\n---------------------------" +
                "\nPatient Name: " + PatientProfile.getName() +
                "\nDoctor Name: " + DoctorProfile.getName() +
                "\nDate/Time: " + dateTime +
                "\nReason for Visit: " + reason;
    }
}