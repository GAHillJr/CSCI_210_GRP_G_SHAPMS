import java.time.DayOfWeek;
import java.util.Map;
import java.util.Objects;

/**
 * Represents a doctor's profile in a healthcare system.
 * This class includes functionalities to:
 * - Create new doctor profile objects (firstName, badge ID, specialty, weekly schedule).
 * - Manage the doctor's weekly schedule using the WeeklySchedule class.
 * - Compare doctor profiles based on badge ID for sorting and searching.
 */
public class DoctorProfile {

    private String firstName;
    private String lastName;
    private final int badgeId;
    private final String specialty;
    private final WeeklySchedule schedule;

    public DoctorProfile(String firstName, String lastName, String specialty, WeeklySchedule schedule) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.badgeId = getBadgeId();
        this.specialty = Objects.requireNonNull(specialty, "specialty must not be null");
        this.schedule = Objects.requireNonNull(schedule, "schedule must not be null");
    }

    // Getters and Setters

    /**
     * Gets the firstName of the doctor.
     *
     * @return The firstName of the doctor.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the firstName of the doctor.
     *
     * @param firstName The new firstName of the doctor.
     */
    public void setFirstName(String firstName) {
        this.firstName = Objects.requireNonNull(firstName, "firstName must not be null");
    }

    /**
     * Gets the lastName of the doctor.
     *
     * @return The lastName of the doctor.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the lastName of the doctor.
     *
     * @param lastName The new lastName of the doctor.
     */
    public void setLastName(String lastName) {
        this.lastName = Objects.requireNonNull(lastName, "lastName must not be null");
    }

    /* Getters for badgeId and specialty */
    public int getBadgeId() {
        return hashCode();
    }

    /**
     * Gets the specialty of the doctor.
     *
     * @return The specialty of the doctor.
     */
    public String getSpecialty() {
        return specialty;
    }

    /**
     * Return a deep-copied export of the underlying schedule to avoid exposing internal arrays.
     */
    public Map<DayOfWeek, String[]> exportSchedule() {
        return schedule.exportSchedule();
    }

    /* Convenience delegations to WeeklySchedule */

    /**
     * Books an appointment for the specified day and hour with given details.
     *
     * @param day     The day of the week.
     * @param hour    The hour of the day.
     * @param details The details of the appointment.
     * @return true if the appointment was successfully booked, false otherwise.
     */
    public boolean bookAppointment(DayOfWeek day, int hour, String details) {
        return schedule.bookAppointment(day, hour, details);
    }

    /**
     * Cancels an appointment for the specified day and hour.
     *
     * @param day  The day of the week.
     * @param hour The hour of the day.
     * @return true if the appointment was successfully canceled, false otherwise.
     */
    public boolean cancelAppointment(DayOfWeek day, int hour) {
        return schedule.cancelAppointment(day, hour);
    }

    /**
     * Checks if a given slot is available.
     *
     * @param day  The day of the week.
     * @param hour The hour of the day.
     * @return true if the slot is available, false otherwise.
     */
    public boolean isAvailable(DayOfWeek day, int hour) {
        return schedule.isAvailable(day, hour);
    }

    /**
     * Retrieves the details of the slot for a given day and hour.
     *
     * @param day  The day of the week.
     * @param hour The hour of the day.
     * @return The details of the slot, or null if available.
     */
    public String getSlot(DayOfWeek day, int hour) {
        return schedule.getSlot(day, hour);
    }

    /* Overridden methods */

    /**
     * Compares this DoctorProfile object with another for equality.
     *
     * @param otherDoctorProfile The other object to compare with.
     * @return true if both objects have the same doctor details; otherwise, false.
     */
    @Override
    public boolean equals(Object otherDoctorProfile) {
        if (otherDoctorProfile == null || getClass() != otherDoctorProfile.getClass()) return false;
        DoctorProfile that = (DoctorProfile) otherDoctorProfile;
        return Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(specialty, that.specialty);
    }

    /**
     * Generates a hash code for the DoctorProfile object.
     *
     * @return The hash code of the object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, specialty);
    }

    /**
     * Provides a string representation of the DoctorProfile object.
     *
     * @return A string containing the doctor's details.
     */
    @Override
    public String toString() {
        return "Doctor Information" +
                "\n---------------------------" +
                "\nName: " + firstName +
                "\nBadge ID: " + badgeId +
                "\nSpecialty: " + specialty +
                "\nWeekly Schedule: " + schedule.toString();
    }
}