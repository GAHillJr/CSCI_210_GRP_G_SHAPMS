import java.util.Objects;

/**
 * Represents a patient's profile in a healthcare system.
 * This class includes functionalities to:
 * Create new patients profile Objects (name, ID, age, contact info, medical history).
 * Maintain records of past visits using linked lists.
 * Track and limit the number of active appointments per patient.
 */
public class PatientProfile {
    /**
     * Patient's full name.
     */
    private String name;

    /**
     * Unique patient identifier.
     */
    private int patientId;

    /**
     * Patient's age in years.
     */
    private int age;

    /**
     * Contact information (phone, email, address).
     */
    private String contactInfo;

    /**
     * Brief medical history summary; consider storing detailed records
     * in a dedicated medical record class or database.
     */
    private String medicalHistory;

    /**
     * Constructor to initialize a PatientProfile object.
     *
     * @param name           The name of the patient.
     * @param patientId      The unique ID of the patient.
     * @param age            The age of the patient.
     * @param contactInfo    The contact information of the patient.
     * @param medicalHistory The medical history of the patient.
     */
    public PatientProfile(String name, int patientId, int age, String contactInfo, String medicalHistory) {
        this.name = name;
        this.patientId = patientId;
        this.age = age;
        this.contactInfo = contactInfo;
        this.medicalHistory = medicalHistory;
    }

    // Getters and Setters

    /**
     * Gets the name of the patient.
     *
     * @return The name of the patient.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the patient.
     *
     * @param name The name to set for the patient.
     */
    public void setName(String name) {
        this.name = name;
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
     * Sets the patient ID.
     *
     * @param patientId The patient ID to set.
     */
    public void setPatientId(int patientId) {
        this.patientId = patientId;
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
     * Sets the age of the patient.
     *
     * @param age The age to set for the patient.
     */
    public void setAge(int age) {
        this.age = age;
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
    public String getMedicalHistory() {
        return medicalHistory;
    }

    /**
     * Sets the medical history of the patient.
     *
     * @param medicalHistory The medical history to set for the patient.
     */
    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
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
        return getPatientId() == that.getPatientId() && getAge() == that.getAge() && Objects.equals(getName(), that.getName()) && Objects.equals(getContactInfo(), that.getContactInfo()) && Objects.equals(getMedicalHistory(), that.getMedicalHistory());
    }

    /**
     * Generates a hash code for the PatientProfile object.
     *
     * @return The hash code based on patient details.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPatientId(), getAge(), getContactInfo(), getMedicalHistory());
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
                "\nName: " + name +
                "\nPatient ID: " + patientId +
                "\nAge: " + age +
                "\nContact Info: " + contactInfo +
                "\nMedicalHistory: " + medicalHistory;
    }
}
