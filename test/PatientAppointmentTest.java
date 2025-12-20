import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for PatientAppointment.
 */
class PatientAppointmentTest {
	
	PatientAppointment appt;

    @BeforeEach
    void setUp() {
        appt = new PatientAppointment("Alice", "Dr.John", LocalDateTime.of(2025,12,20,10,0), "Checkup");
    }

    @AfterEach
    void tearDown() {
    	appt = null;
    }

    @Test
    void setPatientName() {
    	appt.setPatientName("Bob");
        assertEquals("Bob", appt.getPatientName());
    }

    @Test
    void getPatientName() {
        assertEquals("Alice", appt.getPatientName());
    }

    @Test
    void setDoctorName() {
    	appt.setDoctorName("Dr.Jane");
        assertEquals("Dr.Jane", appt.getDoctorName());
    }

    @Test
    void getDoctorName() {
        assertEquals("Dr.John", appt.getDoctorName());
    }

    @Test
    void dateTime() {
        assertEquals(LocalDateTime.of(2025,12,20,10,0), appt.dateTime());
    }

    @Test
    void getReason() {
        assertEquals("Checkup", appt.getReason());
    }

    @Test
    void testEquals() {
    	assertEquals(appt, appt);
        PatientAppointment other = new PatientAppointment("Alice","Dr.John",LocalDateTime.of(2025,12,20,10,0),"Checkup");
        assertEquals(appt, other);
        PatientAppointment diff = new PatientAppointment("Alice","Dr.John",LocalDateTime.of(2025,12,20,11,0),"Checkup");
        assertNotEquals(appt, diff);
    }

    @Test
    void compareTo() {
    	PatientAppointment later = new PatientAppointment("Alice","Dr.John",LocalDateTime.of(2025,12,20,11,0),"Followup");
        assertTrue(appt.compareTo(later) < 0);
    }

    @Test
    void testHashCode() {
        assertEquals(appt.hashCode(), appt.hashCode());
    }

    @Test
    void testToString() {
    	String str = appt.toString();
        assertTrue(str.contains("Alice"));
        assertTrue(str.contains("Dr.John"));
        assertTrue(str.contains("Checkup"));
    }
}