import org.junit.jupiter.api.Test;

/**
 * Test class for HospitalDataManager.
 */

class HospitalDataManagerTest {
	
	HospitalDataManager manager;
    PatientProfile patient;
    DoctorProfile doctor;
    
    @BeforeEach 
    void setUp() {
    	manager = new HospitalDataManager();
        Stack<PatientAppointment> history = new Stack<>();
        patient = new PatientProfile("Alice","Smith",LocalDate.of(2000,1,1),"555-1234",history);
        doctor = new DoctorProfile("John","Doe","Cardiology");
        manager.addPatient(patient);
        manager.addDoctor(doctor);
    }

	@Test
	void addPatient() {
		 PatientProfile p2 = new PatientProfile("Bob","Brown",LocalDate.of(1990,5,5),"555-5678", new Stack<>());
	        manager.addPatient(p2);
	        assertEquals(p2, manager.searchPatientById(p2.getPatientId()));
	}

    @Test
    void searchPatientById() {
        assertEquals(patient, manager.searchPatientById(patient.getPatientId()));
    }

    @Test
    void searchPatientByName() {
        assertEquals(patient, manager.searchPatientByName("Alice","Smith"));
    }

    @Test
    void getAllPatientsSorted() {
        assertTrue(manager.getAllPatientsSorted().contains(patient));
    }

    @Test
    void addDoctor() {
    	DoctorProfile d2 = new DoctorProfile("Jane","Doe","Neurology");
        manager.addDoctor(d2);
        assertEquals(d2, manager.searchDoctorById(d2.getBadgeId()));
    }

    @Test
    void searchDoctorById() {
        assertEquals(doctor, manager.searchDoctorById(doctor.getBadgeId()));
    }

    @Test
    void searchDoctorByName() {
        assertEquals(doctor, manager.searchDoctorByName("John","Doe"));
    }

    @Test
    void searchDoctorsBySpecialty() {
        assertTrue(manager.searchDoctorsBySpecialty("Cardiology").contains(doctor));
    }

    @Test
    void getAllDoctorsSorted() {
        assertTrue(manager.getAllDoctorsSorted().contains(doctor));
    }

    @Test
    void linkAppointmentToPatient() {
    }

    @Test
    void getAppointmentsForPatient() {
    }

    @Test
    void sortAppointmentsByDate() {
    }

    @Test
    void sortAppointmentsByDoctor() {
    }

    @Test
    void sortAppointmentsByPatient() {
    }

    @Test
    void getMostConsultedDoctors() {
    }

    @Test
    void getBusiestDoctor() {
    }

    @Test
    void getDoctorConsultationCount() {
    }

    @Test
    void getAllSpecialties() {
    }

    @Test
    void addDepartment() {
    }

    @Test
    void getAllDepartments() {
    }

    @Test
    void hasSpecialty() {
    }

    @Test
    void getTotalPatients() {
    }

    @Test
    void getTotalDoctors() {
    }

    @Test
    void getTotalAppointments() {
    }

    @Test
    void displayStatistics() {
    }
}