/**
 * Represents a doctor's profile with basic information.
 * This class includes functionalities to:
 * Create and manage doctor profiles Objects (ID, name, specialty, schedule).
 * Assign doctors to appointments based on specialty and availability.
 * Maintain maps of doctor schedules and patient loads.
 */
public class DoctorProfile {
    /**
     * Doctor's full name.
     */
    private String name;

    /**
     * Unique badge identifier assigned to the doctor.
     */
    private int badgeId;

    /**
     * Medical specialty (for example: `Cardiology`, `Pediatrics`).
     */
    private String specialty;

    /**
     * Working schedule in a human-readable or encoded format
     * (for example: `Mon-Fri 09:00-17:00` or `M,W,F:09-17`).
     */
    private String schedule;

    /**
     * Constructor to initialize a DoctorProfile object.
     *
     * @param name      The name of the doctor.
     * @param badgeId   The unique badge ID of the doctor.
     * @param specialty The medical specialty of the doctor.
     * @param schedule  The working schedule of the doctor.
     */
    public DoctorProfile(String name, int badgeId, String specialty, String schedule) {
        this.name = name;
        this.badgeId = badgeId;
        this.specialty = specialty;
        this.schedule = schedule;
    }
    // Getters and Setters

    /**
     * Gets the name of the doctor.
     *
     * @return The name of the doctor.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the doctor.
     *
     * @param name The name to set for the doctor.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the badge ID of the doctor.
     *
     * @return The badge ID of the doctor.
     */
    public int getBadgeId() {
        return badgeId;
    }
}