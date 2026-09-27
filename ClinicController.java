package controller;

import model.*;
import util.*;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Task 6 (Controller Layer): coordinates the Model and View. Every screen
 * calls into this class rather than touching Clinic's ArrayLists directly.
 * It validates input, catches exceptions so the app never crashes, and
 * saves to disk after every change so data isn't lost.
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
        ValidationUtil.requireNotEmpty(id, "Patient ID");
        ValidationUtil.requireNotEmpty(name, "Name");
        ValidationUtil.requireNotEmpty(contact, "Contact number");
        ValidationUtil.requireNotEmpty(address, "Address");
        ValidationUtil.requireValidDate(regDate, "Registration date");

        Patient patient = new Patient(id, name, contact, address, medicalHistory, regDate);
        clinic.addPatient(patient);
        persistSafely();
    }

    // ---------- Doctor ----------

    public void registerDoctor(String id, String name, String contact, String address,
                                String specialization, String availableDays) throws InvalidInputException {
        ValidationUtil.requireNotEmpty(id, "Doctor ID");
        ValidationUtil.requireNotEmpty(name, "Name");
        ValidationUtil.requireNotEmpty(contact, "Contact number");
        ValidationUtil.requireNotEmpty(address, "Address");
        ValidationUtil.requireNotEmpty(specialization, "Specialization");

        Doctor doctor = new Doctor(id, name, contact, address, specialization, availableDays);
        clinic.addDoctor(doctor);
        persistSafely();
    }

    // ---------- Appointment ----------

    public void bookAppointment(String appointmentId, String patientId, String doctorId,
                                 String date, String time, String status) throws InvalidInputException {
        ValidationUtil.requireNotEmpty(appointmentId, "Appointment ID");
        ValidationUtil.requireValidDate(date, "Appointment date");
        ValidationUtil.requireNotEmpty(time, "Appointment time");

        Patient patient = findPatientById(patientId);
        if (patient == null) {
            throw new InvalidInputException("No patient found with ID " + patientId);
        }
        Doctor doctor = findDoctorById(doctorId);
        if (doctor == null) {
            throw new InvalidInputException("No doctor found with ID " + doctorId);
        }

        Appointment appointment = new Appointment(appointmentId, patient, doctor, date, time, status);
        clinic.addAppointment(appointment);
        persistSafely();
    }

    // ---------- Treatment ----------

    public void enterTreatment(String treatmentId, String appointmentId, String description,
                                String costText, String treatmentDate) throws InvalidInputException {
        ValidationUtil.requireNotEmpty(treatmentId, "Treatment ID");
        ValidationUtil.requireNotEmpty(description, "Description");
        ValidationUtil.requireValidDate(treatmentDate, "Treatment date");
        double cost = ValidationUtil.requireValidPositiveNumber(costText, "Cost");

        Appointment appointment = findAppointmentById(appointmentId);
        if (appointment == null) {
            throw new InvalidInputException("No appointment found with ID " + appointmentId);
        }

        Treatment treatment = new Treatment(treatmentId, appointment, description, cost, treatmentDate);
        clinic.addTreatment(treatment);
        persistSafely();
    }

    // ---------- Search / Sort (Task 8) ----------

    public Patient searchPatientById(String id) {
        return BinarySearchUtil.searchPatientById(clinic.getPatients(), id);
    }

    public ArrayList<Appointment> getAppointmentsSortedByDate() {
        return InsertionSortUtil.sortAppointmentsByDate(clinic.getAppointments());
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
