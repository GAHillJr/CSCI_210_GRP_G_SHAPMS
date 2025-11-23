import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Improved DoctorProfile:
 * - badgeId is generated with a static AtomicInteger (stable, unique, final).
 * - equals/hashCode use badgeId (unique identity).
 * - input validation for names and specialty.
 * - exportSchedule returns a deep copy of the underlying schedule map/arrays.
 * - implements Comparable by badgeId for simple sorting.
 */
public class DoctorProfile implements Comparable<DoctorProfile> {

    // Static AtomicInteger to generate unique badge IDs
    private static final AtomicInteger NEXT_BADGE = new AtomicInteger(1);

    // Instance fields
    private String firstName;
    private String lastName;
    private final int badgeId;
    private final String specialty;
    private final WeeklySchedule schedule;

    // Constructor
    /**
     * Constructs a DoctorProfile with the given details.
     *
     * @param firstName The first name of the doctor.
     * @param lastName  The last name of the doctor.
     * @param specialty The medical specialty of the doctor.
     * @param schedule  The weekly schedule of the doctor.
     */
    public DoctorProfile(String firstName, String lastName, String specialty, WeeklySchedule schedule) {
        this.firstName = requireNonEmpty(firstName, "firstName");
        this.lastName = requireNonEmpty(lastName, "lastName");
        this.specialty = Objects.requireNonNull(specialty, "specialty must not be null");
        this.schedule = Objects.requireNonNull(schedule, "schedule must not be null");
        this.badgeId = NEXT_BADGE.getAndIncrement();
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
     * @return The first name of the doctor.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName The first name to set.
     */
    public void setFirstName(String firstName) {
        this.firstName = requireNonEmpty(firstName, "firstName");
    }

    /**
     * @return The last name of the doctor.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName The last name to set.
     */
    public void setLastName(String lastName) {
        this.lastName = requireNonEmpty(lastName, "lastName");
    }

    /**
     * @return The full name of the doctor.
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }

    /**
     * @return The badge ID of the doctor.
     */
    public int getBadgeId() {
        return badgeId;
    }

    /**
     * @return The specialty of the doctor.
     */
    public String getSpecialty() {
        return specialty;
    }

    /**
     * Return a deep copy of the schedule export to avoid exposing internal arrays.
     */
    public Map<DayOfWeek, String[]> exportSchedule() {
        Map<DayOfWeek, String[]> original = schedule.exportSchedule();
        Map<DayOfWeek, String[]> copy = new HashMap<>(original.size());
        for (Map.Entry<DayOfWeek, String[]> e : original.entrySet()) {
            String[] arr = e.getValue();
            copy.put(e.getKey(), arr == null ? null : Arrays.copyOf(arr, arr.length));
        }
        return copy;
    }

    // Delegations to WeeklySchedule
    public boolean bookAppointment(DayOfWeek day, int hour, String details) {
        return schedule.bookAppointment(day, hour, details);
    }

    public boolean cancelAppointment(DayOfWeek day, int hour) {
        return schedule.cancelAppointment(day, hour);
    }

    public boolean isAvailable(DayOfWeek day, int hour) {
        return schedule.isAvailable(day, hour);
    }

    public String getSlot(DayOfWeek day, int hour) {
        return schedule.getSlot(day, hour);
    }

    @Override
    public boolean equals(Object otherDoctorProfile) {
        if (this == otherDoctorProfile) return true;
        if (otherDoctorProfile == null || getClass() != otherDoctorProfile.getClass()) return false;
        DoctorProfile that = (DoctorProfile) otherDoctorProfile;
        return badgeId == that.badgeId;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(badgeId);
    }

    @Override
    public int compareTo(DoctorProfile other) {
        return Integer.compare(this.badgeId, other.badgeId);
    }

    @Override
    public String toString() {
        return "Doctor Information\n---------------------------\n" +
                "Name: " + getFullName() + "\n" +
                "Badge ID: " + badgeId + "\n" +
                "Specialty: " + specialty + "\n" +
                "Weekly Schedule: " + exportSchedule();
    }
}