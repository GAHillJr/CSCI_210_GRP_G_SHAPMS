import java.util.Objects;

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
    private final int badgeId;

    /**
     * Medical specialty (for example: `Cardiology`, `Pediatrics`).
     */
    private final String specialty;

    /**
     * Working schedule in a human-readable or encoded format
     * (for example: `Mon-Fri 09:00-17:00` or `M,W,F:09-17`).
     */
    private WeeklySchedule schedule;

    /**
     * Constructor to initialize a DoctorProfile object.
     *
     * @param name      The name of the doctor.
     * @param badgeId   The unique badge ID of the doctor.
     * @param specialty The medical specialty of the doctor.
     * @param schedule  The working schedule of the doctor.
     */
    public DoctorProfile(String name, int badgeId, String specialty, WeeklySchedule schedule) {
        this.name = name;
        this.badgeId = badgeId;
        this.specialty = specialty;
        this.schedule = schedule;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object otherDoctorProfile) {
        if (otherDoctorProfile == null || getClass() != otherDoctorProfile.getClass()) return false;
        DoctorProfile that = (DoctorProfile) otherDoctorProfile;
        return badgeId == that.badgeId && Objects.equals(getName(), that.getName()) && Objects.equals(specialty, that.specialty) && Objects.equals(schedule, that.schedule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), badgeId, specialty, schedule);
    }
}