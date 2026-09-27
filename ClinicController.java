package controller;

import model.*;
import util.*;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Task 6 (Controller Layer): coordinates the Model and View. Every screen
 * calls into this class rather than touching Clinic's ArrayLists or the
 * FileManager directly. It validates input, catches exceptions so the app
 * never crashes, and saves to disk after every change so data isn't lost.
 */
public class ClinicController {

    private final Clinic clinic;
    private final FileManager fileManager;

    public ClinicController(Clinic clinic, FileManager fileManager) {
        this.clinic = clinic;
        this.fileManager = fileManager;
    }

    public Clinic getClinic() {
        return clinic;
    }

    // ---------- Patient ----------

    public void registerPatient(String id, String name, String contact, String address,
                                String medicalHistory, String regDate) throws InvalidInputException {
        validatePatient(id, name, contact, address, regDate);
        if (findPatientById(id.trim()) != null) {
            throw new InvalidInputException("Patient ID " + id.trim() + " already exists.");
        }
        clinic.addPatient(new Patient(id.trim(), name.trim(), contact.trim(), address.trim(),
                medicalHistory.trim(), regDate.trim()));
        persistSafely();
    }

    /** Update: finds the patient by ID and replaces their details. */
    public void updatePatient(String id, String name, String contact, String address,
                              String medicalHistory, String regDate) throws InvalidInputException {
        validatePatient(id, name, contact, address, regDate);
        Patient p = findPatientById(id.trim());
        if (p == null) {
            throw new InvalidInputException("No patient found with ID " + id.trim());
        }
        p.setName(name.trim());
        p.setContactNumber(contact.trim());
        p.setAddress(address.trim());
        p.setMedicalHistory(medicalHistory.trim());
        p.setRegistrationDate(regDate.trim());
        persistSafely();
    }

    /** Delete: blocked if the patient still has appointments. */
    public void deletePatient(String id) throws InvalidInputException {
        ValidationUtil.requireNotEmpty(id, "Patient ID");
        String key = id.trim();
        if (findPatientById(key) == null) {
            throw new InvalidInputException("No patient found with ID " + key);
        }
        for (Appointment a : clinic.getAppointments()) {
            if (a.getPatient().getPersonId().equals(key)) {
                throw new InvalidInputException("Cannot delete: patient " + key
                        + " still has appointment " + a.getAppointmentId() + ".");
            }
        }
        clinic.removePatientById(key);
        persistSafely();
    }

    private void validatePatient(String id, String name, String contact, String address,
                                 String regDate) throws InvalidInputException {
        ValidationUtil.requireNotEmpty(id, "Patient ID");
        ValidationUtil.requireNotEmpty(name, "Name");
        ValidationUtil.requireValidPhone(contact, "Contact number");
        ValidationUtil.requireNotEmpty(address, "Address");
        ValidationUtil.requireValidDate(regDate, "Registration date");
    }

    // ---------- Doctor ----------

    public void registerDoctor(String id, String name, String contact, String address,
                               String specialization, String availableDays) throws InvalidInputException {
        ValidationUtil.requireNotEmpty(id, "Doctor ID");
        ValidationUtil.requireNotEmpty(name, "Name");
        ValidationUtil.requireValidPhone(contact, "Contact number");
        ValidationUtil.requireNotEmpty(address, "Address");
        ValidationUtil.requireNotEmpty(specialization, "Specialization");
        if (findDoctorById(id.trim()) != null) {
            throw new InvalidInputException("Doctor ID " + id.trim() + " already exists.");
        }
        // stops "Dr. Dr." appearing, because Appointment.toString already adds "Dr."
        String cleanName = name.trim().replaceFirst("(?i)^dr\\.?\\s+", "");
        clinic.addDoctor(new Doctor(id.trim(), cleanName, contact.trim(), address.trim(),
                specialization.trim(), availableDays.trim()));
        persistSafely();
    }

    // ---------- Appointment ----------

    public void bookAppointment(String appointmentId, String patientId, String doctorId,
                                String date, String time, String status) throws InvalidInputException {
        ValidationUtil.requireNotEmpty(appointmentId, "Appointment ID");
        ValidationUtil.requireValidDate(date, "Appointment date");
        ValidationUtil.requireValidTime(time, "Appointment time");
        if (findAppointmentById(appointmentId.trim()) != null) {
            throw new InvalidInputException("Appointment ID " + appointmentId.trim() + " already exists.");
        }
        Patient patient = findPatientById(patientId.trim());
        if (patient == null) {
            throw new InvalidInputException("No patient found with ID " + patientId.trim());
        }
        Doctor doctor = findDoctorById(doctorId.trim());
        if (doctor == null) {
            throw new InvalidInputException("No doctor found with ID " + doctorId.trim());
        }
        clinic.addAppointment(new Appointment(appointmentId.trim(), patient, doctor,
                date.trim(), time.trim(), status));
        persistSafely();
    }

    /** Update: change date, time and status of an existing appointment. */
    public void updateAppointment(String appointmentId, String date, String time, String status)
            throws InvalidInputException {
        ValidationUtil.requireNotEmpty(appointmentId, "Appointment ID");
        ValidationUtil.requireValidDate(date, "Appointment date");
        ValidationUtil.requireValidTime(time, "Appointment time");
        Appointment a = findAppointmentById(appointmentId.trim());
        if (a == null) {
            throw new InvalidInputException("No appointment found with ID " + appointmentId.trim());
        }
        a.setAppointmentDate(date.trim());
        a.setAppointmentTime(time.trim());
        a.setStatus(status);
        persistSafely();
    }

    /** Delete: blocked if a treatment is linked to the appointment. */
    public void deleteAppointment(String appointmentId) throws InvalidInputException {
        ValidationUtil.requireNotEmpty(appointmentId, "Appointment ID");
        String key = appointmentId.trim();
        if (findAppointmentById(key) == null) {
            throw new InvalidInputException("No appointment found with ID " + key);
        }
        for (Treatment t : clinic.getTreatments()) {
            if (t.getAppointment().getAppointmentId().equals(key)) {
                throw new InvalidInputException("Cannot delete: treatment " + t.getTreatmentId()
                        + " is linked to this appointment.");
            }
        }
        clinic.removeAppointmentById(key);
        persistSafely();
    }

    // ---------- Treatment ----------

    public void enterTreatment(String treatmentId, String appointmentId, String description,
                               String costText, String treatmentDate) throws InvalidInputException {
        ValidationUtil.requireNotEmpty(treatmentId, "Treatment ID");
        ValidationUtil.requireNotEmpty(description, "Description");
        ValidationUtil.requireValidDate(treatmentDate, "Treatment date");
        double cost = ValidationUtil.requireValidPositiveNumber(costText, "Cost");
        for (Treatment t : clinic.getTreatments()) {
            if (t.getTreatmentId().equals(treatmentId.trim())) {
                throw new InvalidInputException("Treatment ID " + treatmentId.trim() + " already exists.");
            }
        }
        Appointment appointment = findAppointmentById(appointmentId.trim());
        if (appointment == null) {
            throw new InvalidInputException("No appointment found with ID " + appointmentId.trim());
        }
        clinic.addTreatment(new Treatment(treatmentId.trim(), appointment, description.trim(),
                cost, treatmentDate.trim()));
        appointment.setStatus("Completed");
        persistSafely();
    }

    // ---------- Search / Sort (Task 8) ----------

    public Patient searchPatientById(String id) {
        return BinarySearchUtil.searchPatientById(clinic.getPatients(), id == null ? null : id.trim());
    }

    public ArrayList<Appointment> getAppointmentsSortedByDate() {
        return InsertionSortUtil.sortAppointmentsByDate(clinic.getAppointments());
    }

    // ---------- File I/O (Task 7) ----------

    public void saveData() throws IOException {
        fileManager.save(clinic);
    }

    /** Loads the file into the current clinic. Returns false if no file exists yet. */
    public boolean loadData() throws IOException, ClassNotFoundException {
        Clinic loaded = fileManager.load();
        if (loaded == null) {
            return false;
        }
        clinic.setPatients(loaded.getPatients());
        clinic.setDoctors(loaded.getDoctors());
        clinic.setAppointments(loaded.getAppointments());
        clinic.setAdministrators(loaded.getAdministrators());
        clinic.setTreatments(loaded.getTreatments());
        return true;
    }

    // ---------- Helpers ----------

    private Patient findPatientById(String id) {
        for (Patient p : clinic.getPatients()) {
            if (p.getPersonId().equals(id)) {
                return p;
            }
        }
        return null;
    }

    private Doctor findDoctorById(String id) {
        for (Doctor d : clinic.getDoctors()) {
            if (d.getPersonId().equals(id)) {
                return d;
            }
        }
        return null;
    }

    private Appointment findAppointmentById(String id) {
        for (Appointment a : clinic.getAppointments()) {
            if (a.getAppointmentId().equals(id)) {
                return a;
            }
        }
        return null;
    }

    /** Wraps file I/O so a save failure never crashes the GUI. */
    private void persistSafely() {
        try {
            fileManager.save(clinic);
        } catch (IOException e) {
            System.err.println("Warning: could not save data to file - " + e.getMessage());
        }
    }
}
