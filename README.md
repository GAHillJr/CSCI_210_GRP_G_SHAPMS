Project Overview:
SHAPMS is designed to manage hospital data, including patients, doctors, appoitnments, schedules, and statistics. We applied core computer science concepts such as object-oriented programming, data structures, and testing to simulate a real world hospital management system to this project.

The system allows users to:
create and manage patient and doctor profiles
schedule and track patient appointments
organize data effienctly using appropriate data structures
analyze hospital statistics such as most consulted doctors

System Structure
core classes:
PatientProfile - stores patient info, medical history, and active appointments
DoctorProfile - stores doctor info, specialty, and weekly schedule
PatientAppointment


Individual Contribution

(Yuji Lee)
I focused on system correctness, integration, and testing along with backend logic refinement.
1. Core Logic Improvements
   - enhanced HospitalDataManager to properly synchronize patients, doctors, and appointments
   - Ensured appointments are correctly linked to patients, added to medical history, and counted toward doctor consultation statistics
   - Improved sortinga dns earching reliability without mutating original data
2. Test Class Development&Fix
   A major part of my contribution was fixing, expanding, and completing all test classes
   - corrected broken or incomplete test methods
   - added all test cases to reflect actual class behavior
   - verified edge cases such as patients with no appointments, doctors with no consultations, or invalid/missing data
   - ensured all calsses passed tests consistenly after integration
3. Debugging & Integration
   While testing, I identified and fixed multiple integration issues:
   - appointments not updating patient medical history
   - doctor consultation counts not incrementing correctly
   - name-matching inconsistencies between doctors and appointments
   - logic errors that only appeared when classes interacted together
4. Codde Validation & stability
   I transformed the project from individual working classes into a fully integrated and reliable system by
   - catching hiddne bugs through testing
   - validating logic across multiple components
   - making the project stable and presentation-ready
   - as well as writing this whole readme for the project.
