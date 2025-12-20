import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for DoctorProfile.
 */

class DoctorProfileTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
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
    }

    @Test
    void setLastName() {
    }

    @Test
    void getFullName() {
    }

    @Test
    void getBadgeId() {
    }

    @Test
    void getSpecialty() {
    }

    @Test
    void exportSchedule() {
    	Map<DayOfWeek, String[]> copy = doctor.exportSchedule();
    	assertNotNull(copy); 
    	
    }

    @Test
    void bookAppointment() {
    }

    @Test
    void cancelAppointment() {
    }

    @Test
    void isAvailable() {
    }

    @Test
    void getSlot() {
    }
daf
    @Test
    void testEquals() {
    }

    @Test
    void testHashCode() {
    }

    @Test
    void compareTo() {
    }

    @Test
    void testToString() {
    }
}