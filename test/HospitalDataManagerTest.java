import org.junit.jupiter.api.Test;

/**
 * Test class for HospitalDataManager.
 */

class HospitalDataManagerTest {

	@Test
	void addPatient() {
	    HospitalDataManager manager = new HospitalDataManager();
	    PatientProfile p = new PatientProfile("Alice","Smith",LocalDate.of(2000,1,1),"555-1234", new Stack<>());
	    manager.addPatient(p);
	    assertEquals(p, manager.searchPatientById(p.getPatientId()));
	}

    @Test
    void searchPatientById() {
    }

    @Test
    void searchPatientByName() {
    }

    @Test
    void getAllPatientsSorted() {
    }

    @Test
    void addDoctor() {
    }

    @Test
    void searchDoctorById() {
    }

    @Test
    void searchDoctorByName() {
    }

    @Test
    void searchDoctorsBySpecialty() {
    }

    @Test
    void getAllDoctorsSorted() {
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