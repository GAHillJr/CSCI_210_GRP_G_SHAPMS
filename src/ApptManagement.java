/**
 * This class is responsible for managing appointments in the application.
 * It includes functionalities to:
 * Add, edit, or cancel appointments (patient ID, doctor ID, date/time, reason).
 * Display appointments chronologically, by doctor, or by department.
 * Detect and handle scheduling conflicts using recursion.
 * Manage appointment queues for walk-ins or emergency patients.
 */
public class ApptManagement {
    /**
     * Unique identifier of the patient for this appointment.
     */
    public int patientId;

    /**
     * Unique identifier of the doctor assigned to this appointment.
     */
    public int doctorId;

    /**
     * Date and time of the appointment. Recommended format: ISO-8601.
     */
    public String dateTime;

    /**
     * Reason or description for the appointment.
     */
    public String reason;

    /**
     * Constructor to initialize an ApptManagement object.
     *
     * @param patientId The unique ID of the patient.
     * @param doctorId  The unique ID of the doctor.
     * @param dateTime  The date and time of the appointment (ISO-8601 recommended).
     * @param reason    The reason for the appointment.
     */
    public ApptManagement(int patientId, int doctorId, String dateTime, String reason) {
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.dateTime = dateTime;
        this.reason = reason;
    }

    /**
     * Returns the patient ID.
     *
     * @return the patientId
     */
    public int getPatientId() {
        return patientId;
    }

    /**
     * Sets the patient ID.
     *
     * @param patientId the patientId to set
     */
    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    /**
     * Returns the doctor ID.
     *
     * @return the doctorId
     */
    public int getDoctorId() {
        return doctorId;
    }

    /**
     * Sets the doctor ID.
     *
     * @param doctorId the doctorId to set
     */
    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    /**
     * Returns the appointment date and time.
     *
     * @return the dateTime
     */
    public String getDateTime() {
        return dateTime;
    }

    /**
     * Sets the appointment date and time.
     *
     * @param dateTime the dateTime to set
     */
    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * Returns the reason for the appointment.
     *
     * @return the reason
     */
    public String getReason() {
        return reason;
    }
}