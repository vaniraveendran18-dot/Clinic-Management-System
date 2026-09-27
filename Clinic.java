package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Top-level container holding all data in memory (ArrayLists) while the
 * app runs. GUI/controller classes never touch these lists directly —
 * they go through Clinic's methods, which keeps the Model layer
 * self-contained and loosely coupled from the View/Controller layers.
 */
public class Clinic implements Serializable {

    private static final long serialVersionUID = 1L;

    private String clinicName;
    private String clinicAddress;
    private ArrayList<Patient> patients;
    private ArrayList<Doctor> doctors;
    private ArrayList<Appointment> appointments;
    private ArrayList<Administrator> administrators;
    private ArrayList<Treatment> treatments;

    public Clinic(String clinicName, String clinicAddress) {
        this.clinicName = clinicName;
        this.clinicAddress = clinicAddress;
        this.patients = new ArrayList<>();
        this.doctors = new ArrayList<>();
        this.appointments = new ArrayList<>();
        this.administrators = new ArrayList<>();
        this.treatments = new ArrayList<>();
    }

    public String getClinicName() {
        return clinicName;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }

    public String getClinicAddress() {
        return clinicAddress;
    }

    public void setClinicAddress(String clinicAddress) {
        this.clinicAddress = clinicAddress;
    }

    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
    }

    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    public void addAdministrator(Administrator administrator) {
        administrators.add(administrator);
    }

    public boolean removePatientById(String id) {
        return patients.removeIf(p -> p.getPersonId().equals(id));
    }

    public boolean removeDoctorById(String id) {
        return doctors.removeIf(d -> d.getPersonId().equals(id));
    }

    public boolean removeAppointmentById(String id) {
        return appointments.removeIf(a -> a.getAppointmentId().equals(id));
    }

    public void addTreatment(Treatment treatment) {
        treatments.add(treatment);
    }

    public boolean removeTreatmentById(String id) {
        return treatments.removeIf(t -> t.getTreatmentId().equals(id));
    }

    public ArrayList<Patient> getPatients() {
        return patients;
    }

    public void setPatients(ArrayList<Patient> patients) {
        this.patients = patients;
    }

    public ArrayList<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(ArrayList<Doctor> doctors) {
        this.doctors = doctors;
    }

    public ArrayList<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(ArrayList<Appointment> appointments) {
        this.appointments = appointments;
    }

    public ArrayList<Administrator> getAdministrators() {
        return administrators;
    }

    public void setAdministrators(ArrayList<Administrator> administrators) {
        this.administrators = administrators;
    }

    public ArrayList<Treatment> getTreatments() {
        return treatments;
    }

    public void setTreatments(ArrayList<Treatment> treatments) {
        this.treatments = treatments;
    }
}
