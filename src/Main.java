import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Stack;

/**
 * Demo class showing how HospitalDataManager integrates with existing classes (dr., patient, appt.)
 */
public class HospitalSystemDemo {
    
    public static void main(String[] args) {
        // Initialize the data manager
        HospitalDataManager manager = new HospitalDataManager();
        
        System.out.println("=== Hospital Data Manager Demo ===\n");
        
        // ========== CREATE DOCTORS ==========
        System.out.println("1. Creating Doctors...");
        
        // Create doctor schedules
        WeeklySchedule schedule1 = new WeeklySchedule(9, 17);
        WeeklySchedule schedule2 = new WeeklySchedule(8, 16);
        WeeklySchedule schedule3 = new WeeklySchedule(10, 18);
        
        // Create doctor profiles
        DoctorProfile drSmith = new DoctorProfile("John", "Smith", "Cardiology", schedule1);
        DoctorProfile drJones = new DoctorProfile("Sarah", "Jones", "Pediatrics", schedule2);
        DoctorProfile drBrown = new DoctorProfile("Michael", "Brown", "Cardiology", schedule3);
        
        // Add doctors to the manager
        manager.addDoctor(drSmith);
        manager.addDoctor(drJones);
        manager.addDoctor(drBrown);
        manager.addDepartment("Cardiology");
        manager.addDepartment("Pediatrics");
        
        System.out.println("? Added 3 doctors to the system\n");
        
        // ========== CREATE PATIENTS ==========
        System.out.println("2. Creating Patients...");
        
        // Create patient profiles
        Stack<PatientAppointment> history1 = new Stack<>();
        PatientProfile patient1 = new PatientProfile(
            "Alice", "Johnson", 
            LocalDate.of(1985, 5, 15), 
            "555-0101", 
            history1
        );
        
        Stack<PatientAppointment> history2 = new Stack<>();
        PatientProfile patient2 = new PatientProfile(
            "Bob", "Williams", 
            LocalDate.of(1990, 8, 20), 
            "555-0102", 
            history2
        );
        
        Stack<PatientAppointment> history3 = new Stack<>();
        PatientProfile patient3 = new PatientProfile(
            "Carol", "Davis", 
            LocalDate.of(1978, 3, 10), 
            "555-0103", 
            history3
        );
        
        // Add patients to the manager
        manager.addPatient(patient1);
        manager.addPatient(patient2);
        manager.addPatient(patient3);
        
        System.out.println("? Added 3 patients to the system\n");
        
        // ========== CREATE APPOINTMENTS ==========
        System.out.println("3. Creating Appointments...");
        
        // Create appointments
        PatientAppointment apt1 = new PatientAppointment(
            patient1.getFirstName() + " " + patient1.getLastName(),
            drSmith.getFullName(),
            LocalDateTime.of(2024, 12, 15, 10, 0),
            "Annual checkup"
        );
        
        PatientAppointment apt2 = new PatientAppointment(
            patient2.getFirstName() + " " + patient2.getLastName(),
            drSmith.getFullName(),
            LocalDateTime.of(2024, 12, 16, 14, 0),
            "Heart consultation"
        );
        
        PatientAppointment apt3 = new PatientAppointment(
            patient3.getFirstName() + " " + patient3.getLastName(),
            drJones.getFullName(),
            LocalDateTime.of(2024, 12, 14, 9, 0),
            "Child vaccination"
        );
        
        PatientAppointment apt4 = new PatientAppointment(
            patient1.getFirstName() + " " + patient1.getLastName(),
            drSmith.getFullName(),
            LocalDateTime.of(2024, 12, 20, 11, 0),
            "Follow-up"
        );
        
        // Link appointments to patients
        manager.linkAppointmentToPatient(patient1.getPatientId(), apt1);
        manager.linkAppointmentToPatient(patient2.getPatientId(), apt2);
        manager.linkAppointmentToPatient(patient3.getPatientId(), apt3);
        manager.linkAppointmentToPatient(patient1.getPatientId(), apt4);
        
        // Also add to patient profiles and book in doctor schedules
        patient1.bookActiveAppointment(apt1);
        patient1.bookActiveAppointment(apt4);
        patient2.bookActiveAppointment(apt2);
        patient3.bookActiveAppointment(apt3);
        
        drSmith.bookAppointment(DayOfWeek.MONDAY, 10, "Alice Johnson - Annual checkup");
        drSmith.bookAppointment(DayOfWeek.TUESDAY, 14, "Bob Williams - Heart consultation");
        drSmith.bookAppointment(DayOfWeek.FRIDAY, 11, "Alice Johnson - Follow-up");
        drJones.bookAppointment(DayOfWeek.SATURDAY, 9, "Carol Davis - Child vaccination");
        
        System.out.println("? Created and linked 4 appointments\n");
        
        // ========== DEMONSTRATE SEARCH FEATURES ==========
        System.out.println("4. Testing Search Features...\n");
        
        // Hash table search (O(1))
        System.out.println("--- Hash Table Search by ID (O(1)) ---");
        PatientProfile foundPatient = manager.searchPatientById(patient1.getPatientId());
        System.out.println("Found patient by ID " + patient1.getPatientId() + ": " + 
            (foundPatient != null ? foundPatient.getFirstName() + " " + foundPatient.getLastName() : "Not found"));
        
        DoctorProfile foundDoctor = manager.searchDoctorById(drSmith.getBadgeId());
        System.out.println("Found doctor by badge ID " + drSmith.getBadgeId() + ": " + 
            (foundDoctor != null ? foundDoctor.getFullName() : "Not found"));
        System.out.println();
        
        // BST search by name (O(log n))
        System.out.println("--- Binary Search Tree Search by Name (O(log n)) ---");
        PatientProfile patientByName = manager.searchPatientByName("Johnson", "Alice");
        System.out.println("Found patient by name 'Johnson, Alice': " + 
            (patientByName != null ? "ID " + patientByName.getPatientId() : "Not found"));
        
        DoctorProfile doctorByName = manager.searchDoctorByName("Smith", "John");
        System.out.println("Found doctor by name 'Smith, John': " + 
            (doctorByName != null ? "Badge " + doctorByName.getBadgeId() : "Not found"));
        System.out.println();
        
        // Search by specialty
        System.out.println("--- Search Doctors by Specialty ---");
        List<DoctorProfile> cardiologists = manager.searchDoctorsBySpecialty("Cardiology");
        System.out.println("Cardiologists found: " + cardiologists.size());
        for (DoctorProfile doc : cardiologists) {
            System.out.println("  - " + doc.getFullName() + " (Badge: " + doc.getBadgeId() + ")");
        }
        System.out.println();
        
        // ========== DEMONSTRATE SORTING ==========
        System.out.println("5. Testing Sorting Features...\n");
        
        // Get all appointments for patient 1
        List<PatientAppointment> patient1Appointments = manager.getAppointmentsForPatient(patient1.getPatientId());
        
        System.out.println("--- Appointments for " + patient1.getFirstName() + " " + patient1.getLastName() + " ---");
        List<PatientAppointment> sortedByDate = manager.sortAppointmentsByDate(patient1Appointments);
        System.out.println("Sorted by date:");
        for (PatientAppointment apt : sortedByDate) {
            System.out.println("  " + apt.dateTime() + " with " + apt.getDoctorName());
        }
        System.out.println();
        
        // ========== DEMONSTRATE HEAP OPERATIONS ==========
        System.out.println("6. Testing Heap Operations (Most Consulted Doctors)...\n");
        
        System.out.println("--- Doctor Consultation Counts ---");
        for (DoctorProfile doc : manager.getAllDoctorsSorted()) {
            int count = manager.getDoctorConsultationCount(doc.getBadgeId());
            System.out.println(doc.getFullName() + ": " + count + " consultations");
        }
        System.out.println();
        
        System.out.println("--- Top 2 Most Consulted Doctors (Max Heap) ---");
        List<DoctorProfile> topDoctors = manager.getMostConsultedDoctors(2);
        for (int i = 0; i < topDoctors.size(); i++) {
            DoctorProfile doc = topDoctors.get(i);
            System.out.println((i + 1) + ". " + doc.getFullName() + 
                " - " + manager.getDoctorConsultationCount(doc.getBadgeId()) + " consultations");
        }
        System.out.println();
        
        DoctorProfile busiest = manager.getBusiestDoctor();
        System.out.println("Busiest Doctor: " + busiest.getFullName());
        System.out.println();
        
        // ========== DEMONSTRATE SET OPERATIONS ==========
        System.out.println("7. Testing Set Operations...\n");
        
        System.out.println("--- All Specialties ---");
        for (String specialty : manager.getAllSpecialties()) {
            System.out.println("  - " + specialty);
        }
        System.out.println();
        
        System.out.println("--- All Departments ---");
        for (String dept : manager.getAllDepartments()) {
            System.out.println("  - " + dept);
        }
        System.out.println();
        
        System.out.println("Has 'Cardiology' specialty? " + manager.hasSpecialty("Cardiology"));
        System.out.println("Has 'Neurology' specialty? " + manager.hasSpecialty("Neurology"));
        System.out.println();
        
        // ========== DEMONSTRATE SORTED LISTS ==========
        System.out.println("8. BST Traversal - All Records Sorted...\n");
        
        System.out.println("--- All Patients (Sorted by Name) ---");
        for (PatientProfile p : manager.getAllPatientsSorted()) {
            System.out.println("  " + p.getLastName() + ", " + p.getFirstName() + 
                " (ID: " + p.getPatientId() + ")");
        }
        System.out.println();
        
        System.out.println("--- All Doctors (Sorted by Name) ---");
        for (DoctorProfile d : manager.getAllDoctorsSorted()) {
            System.out.println("  " + d.getLastName() + ", " + d.getFirstName() + 
                " (Badge: " + d.getBadgeId() + ", " + d.getSpecialty() + ")");
        }
        System.out.println();
        
        // ========== DISPLAY STATISTICS ==========
        System.out.println("9. System Statistics...\n");
        manager.displayStatistics();
        
        System.out.println("\n=== Demo Complete ===");
    }
}
