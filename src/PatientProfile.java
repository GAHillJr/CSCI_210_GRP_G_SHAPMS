import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Objects;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Improved PatientProfile:
 * - stable unique patientId generator
 * - input validation
 * - store dateOfBirth and compute age on access
 * - defensive copies and unmodifiable exports for history
 * - limited active appointments management
 */
public class PatientProfile {

    // Static AtomicInteger to generate unique patient IDs
    private static final AtomicInteger NEXT_PATIENT_ID = new AtomicInteger(1000);
    private static final int DEFAULT_MAX_ACTIVE = 3;

    // Instance fields
    private final String firstName;
    private final String lastName;
    private final int patientId;
    private final LocalDate dateOfBirth;
    private String contactInfo;

    // Using Deque for efficient add/remove from front
    private final Deque<PatientAppointment> medicalHistory;
    private final List<PatientAppointment> activeAppointments = new ArrayList<>();
    private final int maxActiveAppointments; // limit on active appointments

    // Constructor

    /**
     * Constructs a PatientProfile with the given details.
     *
     * @param firstName      The first name of the patient.
     * @param lastName       The last name of the patient.
     * @param dateOfBirth    The date of birth of the patient.
     * @param contactInfo    The contact information of the patient.
     * @param medicalHistory The medical history stack (most recent first).
     */
    public PatientProfile(String firstName, String lastName, LocalDate dateOfBirth, String contactInfo, Stack<PatientAppointment> medicalHistory) {
        this(firstName, lastName, dateOfBirth, contactInfo, medicalHistory, DEFAULT_MAX_ACTIVE);
    }

    /**
     * Constructs a PatientProfile with the given details and max active appointments.
     *
     * @param firstName             The first name of the patient.
     * @param lastName              The last name of the patient.
     * @param dateOfBirth           The date of birth of the patient.
     * @param contactInfo           The contact information of the patient.
     * @param medicalHistory        The medical history stack (most recent first).
     * @param maxActiveAppointments The maximum number of active appointments allowed.
     */
    public PatientProfile(String firstName, String lastName, LocalDate dateOfBirth, String contactInfo,
                          Stack<PatientAppointment> medicalHistory, int maxActiveAppointments) {
        this.firstName = requireNonEmpty(firstName, "firstName");
        this.lastName = requireNonEmpty(lastName, "lastName");
        this.dateOfBirth = Objects.requireNonNull(dateOfBirth, "dateOfBirth must not be null");
        this.contactInfo = Objects.requireNonNull(contactInfo, "contactInfo must not be null");
        this.patientId = NEXT_PATIENT_ID.getAndIncrement();
        this.maxActiveAppointments = Math.max(1, maxActiveAppointments);

        // Defensive copy of incoming stack (stack may be null)
        if (medicalHistory == null) {
            this.medicalHistory = new ArrayDeque<>();
        } else {
            this.medicalHistory = new ArrayDeque<>(medicalHistory);
        }
    }

    //No-args constructor for frameworks that require it (e.g., serialization)

    /**
     * No-args constructor initializing with default values.
     */
    public PatientProfile() {
        this.firstName = "John";
        this.lastName = "Doe";
        this.dateOfBirth = LocalDate.of(1970, 1, 1);
        this.contactInfo = "N/A";
        this.patientId = NEXT_PATIENT_ID.getAndIncrement();
        this.medicalHistory = new ArrayDeque<>();
        this.maxActiveAppointments = DEFAULT_MAX_ACTIVE;
    }

    /**
     * Validates that a string is non-null and non-empty after trimming.
     *
     * @param value The string to validate.
     * @param name  The name of the parameter (for error messages).
     * @return The trimmed string if valid.
     * @throws NullPointerException     if the string is null.
     * @throws IllegalArgumentException if the string is empty after trimming.
     */
    private static String requireNonEmpty(String value, String name) {
        if (value == null) throw new NullPointerException(name + " must not be null");
        String trimmed = value.trim();
        if (trimmed.isEmpty()) throw new IllegalArgumentException(name + " must not be empty");
        return trimmed;
    }

    // Getters and Setters

    /**
     * @return The first name of the patient.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @return The last name of the patient.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @return The patient ID.
     */
    public int getPatientId() {
        return patientId;
    }

    /**
     * @return The age in years.
     */
    public int getAge() {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }

    /**
     * @return The date of birth.
     */
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * @return The contact information.
     */
    public String getContactInfo() {
        return contactInfo;
    }

    /**
     * @param contactInfo The contact information to set.
     */
    public void setContactInfo(String contactInfo) {
        this.contactInfo = Objects.requireNonNull(contactInfo, "contactInfo must not be null");
    }

    /**
     * Return an unmodifiable snapshot of medical history (most recent first).
     */
    public List<PatientAppointment> getMedicalHistory() {
        return List.copyOf(medicalHistory);
    }

    /**
     * Add a new appointment to history (push to front).
     */
    public void addToMedicalHistory(PatientAppointment newAppointment) {
        Objects.requireNonNull(newAppointment, "newAppointment must not be null");
        medicalHistory.addFirst(newAppointment);
    }

    /**
     * Book an active appointment if under limit. Returns true if booked.
     */
    public boolean bookActiveAppointment(PatientAppointment appointment) {
        Objects.requireNonNull(appointment, "appointment must not be null");
        if (activeAppointments.size() >= maxActiveAppointments) return false;
        activeAppointments.add(appointment);
        return true;
    }

    /**
     * Cancel an active appointment. Returns true if removed.
     */
    public boolean cancelActiveAppointment(PatientAppointment appointment) {
        Objects.requireNonNull(appointment, "appointment must not be null");
        return activeAppointments.remove(appointment);
    }

    /**
     * Returns an unmodifiable view of current active appointments.
     */
    public List<PatientAppointment> getActiveAppointments() {
        return List.copyOf(activeAppointments);
    }

    /**
     * @return The maximum number of active appointments.
     */
    public int getMaxActiveAppointments() {
        return maxActiveAppointments;
    }

    @Override
    public boolean equals(Object otherPatientProfile) {
        if (this == otherPatientProfile) return true;
        if (otherPatientProfile == null || getClass() != otherPatientProfile.getClass())
            return false;
        PatientProfile that = (PatientProfile) otherPatientProfile;
        return patientId == that.patientId;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(patientId);
    }

    @Override
    public String toString() {
        return "Patient Information" +
                "\n---------------------------" +
                "\nName: " + lastName + ", " + firstName +
                "\nPatient ID: " + patientId +
                "\nAge: " + getAge() +
                "\nContact Info: " + contactInfo +
                "\nMedicalHistory size: " + medicalHistory.size() +
                "\nActiveAppointments: " + activeAppointments.size();
    }
}