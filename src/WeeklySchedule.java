import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a weekly schedule with days of the week and available time slots.
 * This class can be expanded to include functionalities such as:
 * - Create and manage weekly schedules.
 * - Assign time slots for appointments or events.
 * - Check availability for specific days and times.
 */
public class WeeklySchedule {

    /**
     * A map representing the schedule for each day of the week.
     * Each day maps to an array of 24 strings representing hourly time slots.
     */
    private final Map<DayOfWeek, String[]> schedule;

    public WeeklySchedule() {
        this.schedule = new HashMap<>();
        for (DayOfWeek day : DayOfWeek.values()) { // Initialize each day with 24 hourly slots
            this.schedule.put(day, new String[24]);
        }
    }

    /**
     * Sets availability for a specific day and hour.
     *
     * @param day  The day of the week. 1= Monday, 7 = Sunday.
     * @param hour The hour of the day (0-23).
     * @param task The task description.
     */
    public void setAvailability(DayOfWeek day, int hour, String task) {
        if (hour >= 8 && hour < 17) { // Assuming working hours are 8 AM to 5 PM
            this.schedule.get(day)[hour] = task;
        } else {
            System.out.println("Invalid hour. Must be between 8 and 17.");
        }
    }

    /**
     * Sets an appointment for a specific day and hour.
     *
     * @param day                The day of the week.
     * @param hour               The hour of the day (0-23).
     * @param appointmentDetails The details of the appointment.
     */
    public void setAppointment(DayOfWeek day, int hour, String appointmentDetails) {
        setAvailability(day, hour, appointmentDetails);
    }

    /**
     * Displays the full weekly schedule in a readable format.
     */
    public void displaySchedule() {
        for (DayOfWeek day : DayOfWeek.values()) {
            System.out.println("\n--- " + day + " ---");
            String[] dailySchedule = this.schedule.get(day);
            for (int hour = 0; hour < 24; hour++) {
                String task = dailySchedule[hour];
                String timeFormat = String.format("%02d:00-%02d:00", hour, hour + 1);
                if (task != null && !task.isEmpty()) {
                    System.out.println(timeFormat + ": " + task);
                } else {
                    // Optional: print available slots
                    System.out.println(timeFormat + ": Available");
                }
            }
        }
    }
}
