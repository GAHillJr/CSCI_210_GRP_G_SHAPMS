import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;
import java.util.Stack;

/**
 * Represents a patient's profile in a healthcare system.
 * This class includes functionalities to:
 * Create new patients profile Objects (lastName, ID, age, contact info, medical history).
 * Maintain records of past visits using linked lists.
 * Track and limit the number of active appointments per patient.
 */
public class PatientProfile {

    private final String firstName;
    private final String lastName;
    private final int patientId;
    private final int age;
    private String contactInfo;
    private final Stack<PatientAppointment> medicalHistory;

    /**
     * Constructor to initialize a PatientProfile object.
     *
     * @param firstName      The firstName of the patient.
     * @param lastName       The lastName of the patient.
     * @param dateOfBirth    The date of birth of the patient.
     * @param contactInfo    The contact information of the patient.
     * @param medicalHistory The medical history of the patient.
     */
    public PatientProfile(String firstName, String lastName, LocalDate dateOfBirth, String contactInfo,
                          Stack<PatientAppointment> medicalHistory) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patientId = setPatientId();
        this.age = Period.between(dateOfBirth, LocalDate.now()).getYears();
        this.contactInfo = contactInfo;
        this.medicalHistory = medicalHistory;
    }

    // Getters and Setters
    /**
     * Gets the firstName of the patient.
     *
     * @return The firstName of the patient.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets the lastName of the patient.
     *
     * @return The lastName of the patient.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Gets the patient ID.
     *
     * @return The patient ID.
     */
    public int getPatientId() {
        return patientId;
    }

    /**
     * Sets the patient ID based on the object's hash code.
     */
    private int setPatientId() {
        return hashCode();
    }

    /**
     * Gets the age of the patient.
     *
     * @return The age of the patient.
     */
    public int getAge() {
        return age;
    }

    /**
     * Gets the contact information of the patient.
     *
     * @return The contact information of the patient.
     */
    public String getContactInfo() {
        return contactInfo;
    }

    /**
     * Sets the contact information of the patient.
     *
     * @param contactInfo The contact information to set for the patient.
     */
    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    /**
     * Gets the medical history of the patient.
     *
     * @return The medical history of the patient.
     */
    public Stack<PatientAppointment> getMedicalHistory() {
        return medicalHistory;
    }

    /**
     * Sets the medical history of the patient.
     *
     * @param newAppointment The medical history to set for the patient.
     */
    public void addToMedicalHistory(PatientAppointment newAppointment) {
        this.medicalHistory.push(newAppointment);
    }

    /**
     * Compares this PatientProfile object with another for equality.
     *
     * @param otherPatientProfile The other PatientProfile object to compare with.
     * @return True if both objects have the same patient details; otherwise, false.
     */
    @Override
    public boolean equals(Object otherPatientProfile) {
        if (otherPatientProfile == null || getClass() != otherPatientProfile.getClass()) return false;
        PatientProfile that = (PatientProfile) otherPatientProfile;
        return getPatientId() == that.getPatientId() && getAge() == that.getAge() && Objects.equals(getLastName(), that.getLastName()) && Objects.equals(getContactInfo(), that.getContactInfo()) && Objects.equals(getMedicalHistory(), that.getMedicalHistory());
    }

    /**
     * Generates a hash code for the PatientProfile object.
     *
     * @return The hash code based on patient details.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getLastName(), getAge());
    }

    /**
     * Returns a string representation of the PatientProfile object.
     *
     * @return A string containing patient details.
     */
    @Override
    public String toString() {
        return "Patient Information" +
                "\n---------------------------" +
                "\nName: " + lastName + ", " + firstName +
                "\nPatient ID: " + patientId +
                "\nAge: " + age +
                "\nContact Info: " + contactInfo +
                "\nMedicalHistory: " + medicalHistory;
    }
}
