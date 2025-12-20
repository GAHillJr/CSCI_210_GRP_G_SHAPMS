import java.time.DayOfWeek;
import java.util.*;

/**
 * HospitalDataManager handles search, sorting, and data storage operations. Could replace Main at some point.
 * Implements requirements for:
 * - Binary Search Trees for doctor/patient search
 * - Hash tables for fast patient/doctor access
 * - Sets for specialties and departments
 * - Maps for patient-appointment linkage
 * - Heaps for "most consulted" recommendations
 * - Sorting appointments by various criteria
 */
public class HospitalDataManager {
    
    // Hash tables for O(1) access to patient and doctor records
    private final Map<Integer, PatientProfile> patientRecords;
    private final Map<Integer, DoctorProfile> doctorRecords;
    
    // BST for searching doctors by name (uses TreeMap for sorted order)
    private final TreeMap<String, DoctorProfile> doctorsByName;
    
    // BST for searching patients by name
    private final TreeMap<String, PatientProfile> patientsByName;
    
    // Sets for managing specialties and departments
    private final Set<String> specialties;
    private final Set<String> departments;
    
    // Maps linking patients to their appointments
    private final Map<Integer, List<PatientAppointment>> patientAppointmentMap;
    
    // Map tracking doctor consultation counts for heap operations
    private final Map<Integer, Integer> doctorConsultationCount;
    
    // Central list of all appointments for easier sorting
    private final List<PatientAppointment> appointments = new ArrayList<>(); 
    
    /**
     * Constructor initializes all data structures
     */
    public HospitalDataManager() {
        this.patientRecords = new HashMap<>();
        this.doctorRecords = new HashMap<>();
        this.doctorsByName = new TreeMap<>();
        this.patientsByName = new TreeMap<>();
        this.specialties = new HashSet<>();
        this.departments = new HashSet<>();
        this.patientAppointmentMap = new HashMap<>();
        this.doctorConsultationCount = new HashMap<>();
    }
    
    // ==================== PATIENT OPERATIONS ====================
    
    /**
     * Add a patient to the system (hash table storage)
     */
    public void addPatient(PatientProfile patient) {
        Objects.requireNonNull(patient, "patient must not be null");
        patientRecords.put(patient.getPatientId(), patient);
        patientsByName.put(patient.getLastName() + ", " + patient.getFirstName(), patient);
        patientAppointmentMap.putIfAbsent(patient.getPatientId(), new ArrayList<>());
    }
    
    /**
     * Search patient by ID (O(1) hash table lookup)
     */
    public PatientProfile searchPatientById(int patientId) {
        return patientRecords.get(patientId);
    }
    
    /**
     * Search patient by name using BST (O(log n))
     */
    public PatientProfile searchPatientByName(String lastName, String firstName) {
        return patientsByName.get(lastName + ", " + firstName);
    }
    
    /**
     * Get all patients sorted by name (BST traversal)
     */
    public List<PatientProfile> getAllPatientsSorted() {
        return new ArrayList<>(patientsByName.values());
    }
    
    // ==================== DOCTOR OPERATIONS ====================
    
    /**
     * Add a doctor to the system (hash table + BST storage)
     */
    public void addDoctor(DoctorProfile doctor) {
        Objects.requireNonNull(doctor, "doctor must not be null");
        doctorRecords.put(doctor.getBadgeId(), doctor);
        doctorsByName.put(doctor.getLastName() + ", " + doctor.getFirstName(), doctor);
        specialties.add(doctor.getSpecialty());
        doctorConsultationCount.putIfAbsent(doctor.getBadgeId(), 0);
    }
    
    /**
     * Search doctor by badge ID (O(1) hash table lookup)
     */
    public DoctorProfile searchDoctorById(int badgeId) {
        return doctorRecords.get(badgeId);
    }
    
    /**
     * Search doctor by name using BST (O(log n))
     */
    public DoctorProfile searchDoctorByName(String lastName, String firstName) {
        return doctorsByName.get(lastName + ", " + firstName);
    }
    
    /**
     * Search doctors by specialty using BST
     */
    public List<DoctorProfile> searchDoctorsBySpecialty(String specialty) {
        List<DoctorProfile> result = new ArrayList<>();
        for (DoctorProfile doctor : doctorsByName.values()) {
            if (doctor.getSpecialty().equalsIgnoreCase(specialty)) {
                result.add(doctor);
            }
        }
        return result;
    }
    
    /**
     * Get all doctors sorted by name (BST traversal)
     */
    public List<DoctorProfile> getAllDoctorsSorted() {
        return new ArrayList<>(doctorsByName.values());
    }
    
    // ==================== APPOINTMENT OPERATIONS ====================
    
    /**
     * Link an appointment to a patient (map storage)
     */
    public void linkAppointmentToPatient(int patientId, PatientAppointment appointment) {
        Objects.requireNonNull(appointment, "appointment must not be null");
        
        appointments.add(appointment);
        patientAppointmentMap.computeIfAbsent(patientId, k -> new ArrayList<>()).add(appointment);
        
        // add to patient medical history
        PatientProfile patient = patientRecords.get(patientId); 
        if (patient != null) { 
            patient.addToMedicalHistory(appointment); 
            patient.bookActiveAppointment(appointment); 
        }
        
        // add to doctor schedule
        for (DoctorProfile doctor : doctorRecords.values()) { 
        	if (appointment.getDoctorName().equals(doctor.getFullName())) { 
        		doctor.getSchedule().bookAppointment(
        			    appointment.dateTime().getDayOfWeek(),
        			    appointment.dateTime().getHour(),
        			    appointment.getReason()
        		);

                // increment doctor consultation count
                doctorConsultationCount.merge(doctor.getBadgeId(), 1, Integer::sum); 
                break; 
            }
        }
    }
    
    /**
     * Get all appointments for a patient
     */
    public List<PatientAppointment> getAppointmentsForPatient(int patientId) {
        return new ArrayList<>(patientAppointmentMap.getOrDefault(patientId, Collections.emptyList()));
    }
    
    /**
     * Sort appointments by date/time (chronologically)
     */
    public List<PatientAppointment> sortAppointmentsByDate(List<PatientAppointment> appointments) {
        List<PatientAppointment> sorted = new ArrayList<>(appointments);
        Collections.sort(sorted); // Uses PatientAppointment's compareTo method
        return sorted;
    }
    
    /**
     * Sort appointments by doctor name
     */
    public List<PatientAppointment> sortAppointmentsByDoctor(List<PatientAppointment> appointments) {
        List<PatientAppointment> sorted = new ArrayList<>(appointments);
        sorted.sort(Comparator.comparing(PatientAppointment::getDoctorName));
        return sorted;
    }
    
    /**
     * Sort appointments by patient name
     */
    public List<PatientAppointment> sortAppointmentsByPatient(List<PatientAppointment> appointments) {
        List<PatientAppointment> sorted = new ArrayList<>(appointments);
        sorted.sort(Comparator.comparing(PatientAppointment::getPatientName));
        return sorted;
    }
    
    // ==================== HEAP OPERATIONS (Most Consulted Doctors) ====================
    
    /**
     * Get top N most consulted doctors using a max heap
     */
    public List<DoctorProfile> getMostConsultedDoctors(int topN) {
        // Create a max heap based on consultation count
        PriorityQueue<Map.Entry<Integer, Integer>> maxHeap = new PriorityQueue<>(
            (a, b) -> b.getValue().compareTo(a.getValue())
        );
        
        maxHeap.addAll(doctorConsultationCount.entrySet());
        
        List<DoctorProfile> result = new ArrayList<>();
        for (int i = 0; i < topN && !maxHeap.isEmpty(); i++) {
            Map.Entry<Integer, Integer> entry = maxHeap.poll();
            DoctorProfile doctor = doctorRecords.get(entry.getKey());
            if (doctor != null) {
                result.add(doctor);
            }
        }
        
        return result;
    }
    
    /**
     * Get busiest doctor (most consultations)
     */
    public DoctorProfile getBusiestDoctor() {
        List<DoctorProfile> topDoctors = getMostConsultedDoctors(1);
        return topDoctors.isEmpty() ? null : topDoctors.get(0);
    }
    
    /**
     * Get consultation count for a doctor
     */
    public int getDoctorConsultationCount(int badgeId) {
        return doctorConsultationCount.getOrDefault(badgeId, 0);
    }
    
    // ==================== SET OPERATIONS ====================
    
    /**
     * Get all specialties (set)
     */
    public Set<String> getAllSpecialties() {
        return new HashSet<>(specialties);
    }
    
    /**
     * Add a department
     */
    public void addDepartment(String department) {
        if (department != null && !department.trim().isEmpty()) {
            departments.add(department.trim());
        }
    }
    
    /**
     * Get all departments (set)
     */
    public Set<String> getAllDepartments() {
        return new HashSet<>(departments);
    }
    
    /**
     * Check if a specialty exists
     */
    public boolean hasSpecialty(String specialty) {
        return specialties.contains(specialty);
    }
    
    // ==================== STATISTICS & REPORTS ====================
    
    /**
     * Get total number of patients
     */
    public int getTotalPatients() {
        return patientRecords.size();
    }
    
    /**
     * Get total number of doctors
     */
    public int getTotalDoctors() {
        return doctorRecords.size();
    }
    
    /**
     * Get total number of appointments across all patients
     */
    public int getTotalAppointments() {
        return patientAppointmentMap.values()
            return appointments.size();
    }
    
    /**
     * Display system statistics
     */
    public void displayStatistics() {
        System.out.println("\n=== Hospital System Statistics ===");
        System.out.println("Total Patients: " + getTotalPatients());
        System.out.println("Total Doctors: " + getTotalDoctors());
        System.out.println("Total Appointments: " + getTotalAppointments());
        System.out.println("Specialties Available: " + specialties.size());
        System.out.println("Departments: " + departments.size());
        
        DoctorProfile busiest = getBusiestDoctor();
        if (busiest != null) {
            System.out.println("\nBusiest Doctor: " + busiest.getFullName() + 
                " (" + getDoctorConsultationCount(busiest.getBadgeId()) + " consultations)");
        }
    }
}