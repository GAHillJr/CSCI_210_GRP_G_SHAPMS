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
}