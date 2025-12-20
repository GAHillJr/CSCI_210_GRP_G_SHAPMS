Project Overview:
SHAPMS is designed to manage hospital data, including patients, doctors, appoitnments, schedules, and statistics. We applied core computer science concepts such as object-oriented programming, data structures, and testing to simulate a real world hospital management system to this project.

The system allows users to:
- create and manage patient and doctor profiles
- schedule and track patient appointments
- organize data effienctly using appropriate data structures
- analyze hospital statistics such as most consulted doctors


System Structure
Core Classes:

PatientProfile 
stores all patient related info, including..
- first&last name, patient ID, age, and birth date
- contact info
- medical history
- active appointments
This class manages patient state and enforcing limits such as max num of active appointments.

DoctorProfile
represents doctor in the system which includes..
- doctor's name and badge ID
- medical specialty
- weekly schedule for tracking availability
- appointment booking and cancellation logic
This class allows the system to check availability, book appointments, and track doctor activity.

WeeklySchedule
handles a doctor's availability across the week
- uses days of the week and hourly time slots
- ensures appointments cannot overlap
- provides availability checks before booking
This class abstracts scheduling logic away from the doctor profile

PatientAppointment
represents an appointment between a patient and a doctor
- patient name
- doctor name
- date & time
- reason for visit
Implements comparison logic to allow appointments to be sorted chronologically and by other criteria

PatientAppointmentBuilder
uses the Builder design pattern to..
- construct PatientAppointment objects safely
- ensure required fields are set before creation
- improve readability and reduce constructor complexity

PatientProfileBuilder
allows step by step creation of patient profiles, including
- interactive console-based input
- validation of patient data
- cleaner seperation between user input and object creation

HospitalDataManager
acts as the central controller of the system and integrates all other classes
- stroes patients and doctors using hash tables for fast access
- uses TreeMaps (BSTs) to support sorted searching by name
- links patients to their appointments
- tracks doctor consultation counts
- sorts appointments by date, doctor, or patient
- generates hospital-wide statistics
This class demonstrates use of multiple data structures working together.

Main
serves as the entry point of the program and..
- demo profile creation
- runs the system through console interaction

HospitalSystemDeo
provides a structured demo of system functionality and showcases..
- patient and doctor creation
- appointment booking
- statistics generation


Data Structures Used
- The project intentionally incorporates data structures learned in class:
   - HashMap - fast lookup for patients adn doctors by ID
   - TreeMap (BST) - sorted searching by patient and doctor names
   - ArrayList - storage of appoitnments
   - Set - managinv unique specialities and departments
   - PriorityQueue (Heap) - identifying most consulted doctors


Testing
Our project contains
- DoctorProfileTest
- PatientAppointmentTest
- PatientProfileTest
- HospitalDataManagerTest

These tests verify..
- getter and setter correctness
- equalitya nd hash code consistency
- appointment booking and cancellation
- searching and sorting bejavior
- edge cases such as empty records and invalid input

--> Testing enured system correctness and long-term stability


Individual Contribution

(Yuji Lee) - sorry! I won't be able to join the presentation due to my Robotics showcase. I tried to make a time between, but it didn't work out. I sent out an email to the professor so hopefully he replies me back before the presentation about what I can do!
- I focused on system correctness, integration, and testing along with backend logic refinement.
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
