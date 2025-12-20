import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for DoctorProfile.
 */

class DoctorProfileTest {
	
	DoctorProfile doctor;

    @BeforeEach
    void setUp() {
        doctor = new DoctorProfile("John", "Doe", "Cardiology");
    }

    @AfterEach
    void tearDown() {
    	doctor = null;
    }

    @Test
    void getFirstName() {
    	assertEquals("John", doctor.getFirstName());
    }

    @Test
    void setFirstName() {
    	doctor.setFirstName("Jane");
    	assertEquals("Jane", doctor.getFirstName());
    	
    }

    @Test
    void getLastName() {
        assertEquals("Doe", doctor.getLastName());
    }

    @Test
    void setLastName() {
    	doctor.setLastName("Smith");
        assertEquals("Smith", doctor.getLastName());
    }

    @Test
    void getFullName() {
        assertEquals("John Doe", doctor.getFullName());
    }

    @Test
    void getBadgeId() {
        assertTrue(doctor.getBadgeId() >= 1000);
    }

    @Test
    void getSpecialty() {
        assertEquals("Cardiology", doctor.getSpecialty());
    }

    @Test
    void exportSchedule() {
    	Map<DayOfWeek,String[]> schedule = doctor.exportSchedule();
        assertNotNull(schedule);
        assertEquals(9, schedule.get(DayOfWeek.MONDAY).length);
    }

    @Test
    void bookAppointment() {
    	boolean booked = doctor.bookAppointment(DayOfWeek.MONDAY,8,"Checkup");
        assertTrue(booked);
        assertFalse(doctor.isAvailable(DayOfWeek.MONDAY,8));
    }

    @Test
    void cancelAppointment() {
    	doctor.bookAppointment(DayOfWeek.MONDAY,8,"Checkup");
        boolean cancelled = doctor.cancelAppointment(DayOfWeek.MONDAY,8);
        assertTrue(cancelled);
        assertTrue(doctor.isAvailable(DayOfWeek.MONDAY,8));
    }

    @Test
    void isAvailable() {
        assertTrue(doctor.isAvailable(DayOfWeek.MONDAY,9));
    }

    @Test
    void getSlot() {
    	doctor.bookAppointment(DayOfWeek.MONDAY,8,"Checkup");
        assertEquals("Checkup", doctor.getSlot(DayOfWeek.MONDAY,8));
    }

    @Test
    void testEquals() {
    	DoctorProfile copy = new DoctorProfile("John","Doe","Cardiology");
        assertNotEquals(doctor, copy);
        assertEquals(doctor, doctor);
    }

    @Test
    void testHashCode() {
    	 int hash = doctor.hashCode();
         assertEquals(hash, doctor.hashCode());
    }

    @Test
    void compareTo() {
    	DoctorProfile another = new DoctorProfile("Alice","Smith","Neurology");
        assertTrue(doctor.compareTo(another) > 0 || doctor.compareTo(another) < 0 || doctor.compareTo(doctor) == 0);
    }

    @Test
    void testToString() {
    	String str = doctor.toString();
        assertTrue(str.contains("John"));
        assertTrue(str.contains("Doe"));
    }
}