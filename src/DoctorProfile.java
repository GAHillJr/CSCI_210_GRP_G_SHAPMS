/**
 * Represents a doctor's profile with basic information.
 * This class includes functionalities to:
 * Create and manage doctor profiles Objects (ID, name, specialty, schedule).
 * Assign doctors to appointments based on specialty and availability.
 * Maintain maps of doctor schedules and patient loads.
 */
public class DoctorProfile {
    private String name;
    private int badgeId;
    private String specialty;
    private String schedule;

    // Constructor
    public DoctorProfile(String name, int badgeId, String specialty, String schedule) {
        this.name = name;
        this.badgeId = badgeId;
        this.specialty = specialty;
        this.schedule = schedule;
    }
}
