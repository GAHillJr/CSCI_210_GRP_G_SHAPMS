import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for DoctorProfile.
 */

class PatientProfileTest {
	
	PatientProfile patient;
	PatientAppointment appt1;

    @BeforeEach
    void setUp() {
    	Stack<PatientAppointment> history = new Stack<>();
    	appt1 = new PatientAppointment("Alice", "Dr.John", LocalDate.of(2000,1,1).atTime(10,0),"Checkup");
    	history.push(appt1);
    	patient = new PatientProfile("Alice", "Smith", LocalDate.of(2000,1,1), "555-1234", history);
    }

    @AfterEach
    void tearDown() {
    	patient = null;
    	appt1= null;
    }

    @Test
    void getFirstName() {
    	assertEquals("Alice", patient.getFirstName()); 
    }

    @Test
    void getLastName() {
    	assertEquals("Smith", patient.getLastName());
    }

    @Test
    void getPatientId() {
    	assertTrue(patient.getPatientId() >= 1000); 
    }

    @Test
    void getAge() {
    	int expected = LocalDate.now().getYear() - 2000;
        assertEquals(expected, patient.getAge());
    }

    @Test
    void getDateOfBirth() {
        assertEquals(LocalDate.of(2000,1,1), patient.getDateOfBirth());
    }

    @Test
    void getContactInfo() {
        assertEquals("555-1234", patient.getContactInfo());
    }

    @Test
    void setContactInfo() {
    	patient.setContactInfo("555-5678");
        assertEquals("555-5678", patient.getContactInfo()); 
    }

    @Test
    void getMedicalHistory() {
    	List<PatientAppointment> history = patient.getMedicalHistory();
        assertEquals(1, history.size());
        assertEquals(appt1, history.get(0));
    }

    @Test
    void addToMedicalHistory() {
    	PatientAppointment appt2 = new PatientAppointment("Bob", "Dr.Jane", LocalDate.of(2000,2,2).atTime(11,0), "Consult");
        patient.addToMedicalHistory(appt2);
        assertEquals(appt2, patient.getMedicalHistory().get(0));
    }

    @Test
    void bookActiveAppointment() {
    	boolean booked = patient.bookActiveAppointment(appt1);
        assertTrue(booked);
        assertEquals(1, patient.getActiveAppointments().size());
    }

    @Test
    void cancelActiveAppointment() {
    	patient.bookActiveAppointment(appt1);
        boolean cancelled = patient.cancelActiveAppointment(appt1);
        assertTrue(cancelled);
        assertEquals(0, patient.getActiveAppointments().size());
    }

    @Test
    void getActiveAppointments() {
    	patient.bookActiveAppointment(appt1);
        List<PatientAppointment> active = patient.getActiveAppointments();
        assertEquals(1, active.size());
        assertEquals(appt1, active.get(0));
    }

    @Test
    void getMaxActiveAppointments() {
        assertEquals(3, patient.getMaxActiveAppointments());
    }

    @Test
    void testEquals() {
    	assertEquals(patient, patient);
        PatientProfile copy = new PatientProfile("Alice","Smith",LocalDate.of(2000,1,1),"555-1234", new Stack<>());
        assertNotEquals(patient, copy);
    }

    @Test
    void testHashCode() {
    	 int hash = patient.hashCode();
         assertEquals(hash, patient.hashCode());
    }

    @Test
    void testToString() {
    	String str = patient.toString();
        assertTrue(str.contains("Alice"));
        assertTrue(str.contains("Smith"));
        assertTrue(str.contains("Patient ID"));
    }
}