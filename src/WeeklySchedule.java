import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * Simple weekly schedule that stores hourly slots per day.
 * <p>
 * Each day has a fixed number of hourly slots determined by {@code startHour} (inclusive)
 * and {@code endHour} (exclusive). A slot value of {@code null} means the slot is available;
 * a non\-null {@code String} contains appointment details.
 * <p>
 * This class performs defensive copying on exports and synchronizes public mutating/accessor
 * methods to be safe for simple concurrent use.
 */
public class WeeklySchedule {

    private final EnumMap<DayOfWeek, String[]> schedule;
    private final int startHour; // inclusive
    private final int endHour;   // exclusive
    private final int slotsPerDay;

    /**
     * Constructs a WeeklySchedule with default working hours (8:00 - 17:00).
     */
    public WeeklySchedule() {
        this(8, 17);
    }

    /**
     * Constructs a WeeklySchedule with specified working hours.
     *
     * @param startHour The starting hour of the working day (inclusive, 0-23).
     * @param endHour   The ending hour of the working day (exclusive, 1-24).
     * @throws IllegalArgumentException if hours are out of range or start >= end.
     */
    public WeeklySchedule(int startHour, int endHour) {
        if (startHour < 0 || endHour > 24 || startHour >= endHour) {
            throw new IllegalArgumentException("Invalid working hours");
        }
        this.startHour = startHour;
        this.endHour = endHour;
        this.slotsPerDay = endHour - startHour;
        this.schedule = new EnumMap<>(DayOfWeek.class);
        // initialize arrays for every day to avoid NPEs later
        for (DayOfWeek d : DayOfWeek.values()) {
            this.schedule.put(d, new String[slotsPerDay]);
        }
    }

    /**
     * @return the inclusive start hour of the work day.
     */
    public int getStartHour() {
        return startHour;
    }

    /**
     * @return the exclusive end hour of the work day.
     */
    public int getEndHour() {
        return endHour;
    }

    /**
     * @return number of slots per day (endHour - startHour).
     */
    public int getSlotsPerDay() {
        return slotsPerDay;
    }

    // Validate hour within working hours.
    private void validateHour(int hour) {
        if (hour < startHour || hour >= endHour) {
            throw new IllegalArgumentException("Hour must be between " + startHour + " and " + (endHour - 1));
        }
    }

    // Compute index in the per-day array for a given hour. This is for internal use only.
    private int idx(int hour) {
        return hour - startHour;
    }

    /**
     * Book an appointment detail into the specified slot if available.
     *
     * @param day     non\-null day of week
     * @param hour    hour within working hours
     * @param details non\-null, non\-empty appointment details (trimmed)
     * @return true if booked successfully, false if slot already taken
     * @throws NullPointerException     if day or details is null
     * @throws IllegalArgumentException if details is empty or hour out of range
     */
    public synchronized boolean bookAppointment(DayOfWeek day, int hour, String details) {
        Objects.requireNonNull(day, "day must not be null");
        Objects.requireNonNull(details, "details must not be null");
        String trimmed = details.trim();
        if (trimmed.isEmpty()) throw new IllegalArgumentException("details must not be empty");
        validateHour(hour);
        String[] slots = schedule.get(day);
        int i = idx(hour);
        if (slots[i] == null) {
            // store trimmed details
            slots[i] = trimmed;
            return true;
        }
        return false;
    }

    /**
     * Cancel an appointment at the given slot.
     *
     * @param day  non\-null day of week
     * @param hour hour within working hours
     * @return true if an appointment was removed, false if slot was already empty
     * @throws NullPointerException     if day is null
     * @throws IllegalArgumentException if hour out of range
     */
    public synchronized boolean cancelAppointment(DayOfWeek day, int hour) {
        Objects.requireNonNull(day, "day must not be null");
        validateHour(hour);
        String[] slots = schedule.get(day);
        int i = idx(hour);
        if (slots[i] != null) {
            slots[i] = null;
            return true;
        }
        return false;
    }

    /**
     * Checks availability of a slot.
     *
     * @param day  non\-null day of week
     * @param hour hour within working hours
     * @return true if slot is available (null), false otherwise
     * @throws NullPointerException     if day is null
     * @throws IllegalArgumentException if hour out of range
     */
    public synchronized boolean isAvailable(DayOfWeek day, int hour) {
        Objects.requireNonNull(day, "day must not be null");
        validateHour(hour);
        return schedule.get(day)[idx(hour)] == null;
    }

    /**
     * Legacy getter that may return {@code null} if the slot is available.
     *
     * @param day  non\-null day of week
     * @param hour hour within working hours
     * @return details string or {@code null} if available
     */
    public synchronized String getSlot(DayOfWeek day, int hour) {
        Objects.requireNonNull(day, "day must not be null");
        validateHour(hour);
        return schedule.get(day)[idx(hour)];
    }

    /**
     * Getter that returns an {@link Optional} to avoid nulls.
     *
     * @param day  non\-null day of week
     * @param hour hour within working hours
     * @return Optional containing the details if present
     */
    public synchronized Optional<String> getSlotOptional(DayOfWeek day, int hour) {
        return Optional.ofNullable(getSlot(day, hour));
    }

    /**
     * Exports a defensive deep copy of the schedule.
     *
     * @return a map with copies of the per-day slot arrays.
     */
    public synchronized Map<DayOfWeek, String[]> exportSchedule() {
        EnumMap<DayOfWeek, String[]> copy = new EnumMap<>(DayOfWeek.class);
        for (DayOfWeek d : DayOfWeek.values()) {
            copy.put(d, Arrays.copyOf(schedule.get(d), slotsPerDay));
        }
        return copy;
    }

    /**
     * Deep equals; compares hours and per-day slot contents.
     */
    @Override
    public boolean equals(Object otherWeeklySchedule) {
        if (this == otherWeeklySchedule) return true;
        if (otherWeeklySchedule == null || getClass() != otherWeeklySchedule.getClass()) return false;
        WeeklySchedule that = (WeeklySchedule) otherWeeklySchedule;
        if (startHour != that.startHour || endHour != that.endHour || slotsPerDay != that.slotsPerDay) return false;
        for (DayOfWeek d : DayOfWeek.values()) {
            if (!Arrays.equals(this.schedule.get(d), that.schedule.get(d))) return false;
        }
        return true;
    }

    /**
     * Hash code based on working hours and per-day slot contents.
     */
    @Override
    public int hashCode() {
        int h = Objects.hash(startHour, endHour, slotsPerDay);
        for (DayOfWeek d : DayOfWeek.values()) {
            h = 31 * h + Arrays.hashCode(schedule.get(d));
        }
        return h;
    }

    /**
     * Textual representation showing every day and slot status.
     */
    @Override
    public synchronized String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("WeeklySchedule (").append(startHour).append(":00-").append(endHour).append(":00)\n");
        for (DayOfWeek day : DayOfWeek.values()) {
            sb.append("\n--- ").append(day).append(" ---\n");
            String[] daySlots = schedule.get(day);
            for (int i = 0; i < slotsPerDay; i++) {
                int hour = startHour + i;
                sb.append(String.format("%02d:00-%02d:00: ", hour, hour + 1));
                sb.append(daySlots[i] == null ? "Available" : daySlots[i]).append('\n');
            }
        }
        return sb.toString();
    }
}