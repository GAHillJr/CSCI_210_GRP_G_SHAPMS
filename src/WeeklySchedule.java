import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

/**
 * Simple weekly schedule that stores only working-hour slots per day.
 * Each day has fixed hourly slots (e.g., 8:00-9:00, 9:00-10:00, etc.).
 * Each slot can either be available (null) or booked with appointment details (String).
 */
public class WeeklySchedule {

    private final EnumMap<DayOfWeek, String[]> schedule;
    private final int startHour; // inclusive - booked from
    private final int endHour;   // exclusive - not booked
    private final int slotsPerDay;

    /**
     * Constructs a WeeklySchedule with default working hours (8:00 - 17:00).
     */
    public WeeklySchedule() {
        this(8, 17); // default 8:00 - 17:00
    }

    /**
     * Constructs a WeeklySchedule with specified working hours.
     *
     * @param startHour The starting hour of the working day (inclusive).
     * @param endHour   The ending hour of the working day (exclusive).
     */
    public WeeklySchedule(int startHour, int endHour) {
        if (startHour < 0 || endHour > 24 || startHour >= endHour) {
            throw new IllegalArgumentException("Invalid working hours");
        }
        this.startHour = startHour;
        this.endHour = endHour;
        this.slotsPerDay = endHour - startHour;
        this.schedule = new EnumMap<>(DayOfWeek.class);
        for (DayOfWeek d : DayOfWeek.values()) {
            this.schedule.put(d, new String[slotsPerDay]);
        }
    }

    /**
     * Validates that the given hour is within working hours.
     *
     * @param hour The hour to validate.
     */
    private void validateHour(int hour) {
        if (hour < startHour || hour >= endHour) {
            throw new IllegalArgumentException("Hour must be between " + startHour + " and " + (endHour - 1));
        }
    }

    /**
     * Converts an hour to the corresponding index in the slots array.
     *
     * @param hour The hour of the day.
     * @return The index in the slots array.
     */
    private int idx(int hour) {
        return hour - startHour;
    }

    /**
     * Books an appointment for a given day and hour.
     *
     * @param day     The day of the week.
     * @param hour    The hour of the day.
     * @param details The details of the appointment.
     * @return True if the appointment was successfully booked, false if the slot was already taken.
     */
    public boolean bookAppointment(DayOfWeek day, int hour, String details) {
        validateHour(hour);
        String[] slots = schedule.get(day);
        int i = idx(hour);
        if (slots[i] == null) {
            slots[i] = details;
            return true;
        }
        return false;
    }

    /**
     * Cancels an appointment for a given day and hour.
     *
     * @param day  The day of the week.
     * @param hour The hour of the day.
     * @return True if the appointment was successfully canceled, false if there was no appointment.
     */
    public boolean cancelAppointment(DayOfWeek day, int hour) {
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
     * Checks if a given slot is available.
     *
     * @param day  The day of the week.
     * @param hour The hour of the day.
     * @return True if the slot is available, false if booked.
     */
    public boolean isAvailable(DayOfWeek day, int hour) {
        validateHour(hour);
        return schedule.get(day)[idx(hour)] == null;
    }

    /**
     * Retrieves the details of the slot for a given day and hour.
     *
     * @param day  The day of the week.
     * @param hour The hour of the day.
     * @return The details of the slot, or null if available.
     */
    public String getSlot(DayOfWeek day, int hour) {
        validateHour(hour);
        return schedule.get(day)[idx(hour)];
    }

    /**
     * Exports a copy of the current schedule.
     *
     * @return A map representing the schedule with days as keys and arrays of slot details as values.
     */
    public Map<DayOfWeek, String[]> exportSchedule() {
        EnumMap<DayOfWeek, String[]> copy = new EnumMap<>(DayOfWeek.class);
        for (DayOfWeek d : DayOfWeek.values()) {
            copy.put(d, Arrays.copyOf(schedule.get(d), slotsPerDay));
        }
        return copy;
    }

    /**
     * Compares this WeeklySchedule object with another for equality.
     *
     * @param otherWeeklySchedule The other WeeklySchedule object to compare with.
     * @return True if both schedules are identical; otherwise, false.
     */
    @Override
    public boolean equals(Object otherWeeklySchedule) {
        if (otherWeeklySchedule == null || getClass() != otherWeeklySchedule.getClass()) return false;
        WeeklySchedule that = (WeeklySchedule) otherWeeklySchedule;
        return startHour == that.startHour && endHour == that.endHour && slotsPerDay == that.slotsPerDay && Objects.equals(schedule, that.schedule);
    }

    /**
     * Generates a hash code for the WeeklySchedule object.
     *
     * @return The hash code representing the WeeklySchedule.
     */
    @Override
    public int hashCode() {
        return Objects.hash(schedule, startHour, endHour, slotsPerDay);
    }

    /**
     * Provides a string representation of the weekly schedule.
     *
     * @return A formatted string showing the schedule for each day and time slot.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
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
