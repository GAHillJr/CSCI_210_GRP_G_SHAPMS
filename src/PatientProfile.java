/**
 * Represents a patient's profile in a healthcare system.
 * This class includes functionalities to:
 *      Create new patients profile Objects (name, ID, age, contact info, medical history).
 *      Maintain records of past visits using linked lists.
 *      Track and limit the number of active appointments per patient.
 */
public class PatientProfile {
    private String name;
    private int patientId;
    private int age;
    private String contactInfo;
    private String medicalHistory;

    // Constructor
    public PatientProfile(String name, int patientId, int age, String contactInfo, String medicalHistory) {
        this.name = name;
        this.patientId = patientId;
        this.age = age;
        this.contactInfo = contactInfo;
        this.medicalHistory = medicalHistory;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }
}
