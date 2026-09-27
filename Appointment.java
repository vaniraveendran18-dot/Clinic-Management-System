package model;

import java.io.Serializable;

public class Appointment implements Serializable {

    private static final long serialVersionUID = 1L;

    private String appointmentId;
    private Patient patient;
    private Doctor doctor;
    private String appointmentDate; // yyyy-MM-dd
    private String appointmentTime; // HH:mm
    private String status;          // Scheduled, Completed, Cancelled

    public Appointment(String appointmentId, Patient patient, Doctor doctor,
                        String appointmentDate, String appointmentTime, String status) {
        this.appointmentId = appointmentId;
        this.patient = patient;
        this.doctor = doctor;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.status = status;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return appointmentId + " | " + patient.getName() + " with Dr. " + doctor.getName()
                + " | " + appointmentDate + " " + appointmentTime + " | " + status;
    }
}
