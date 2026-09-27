package model;

import java.io.Serializable;

public class Treatment implements Serializable {

    private static final long serialVersionUID = 1L;

    private String treatmentId;
    private Appointment appointment;
    private String description;
    private double cost;
    private String treatmentDate; // yyyy-MM-dd

    public Treatment(String treatmentId, Appointment appointment, String description,
                      double cost, String treatmentDate) {
        this.treatmentId = treatmentId;
        this.appointment = appointment;
        this.description = description;
        this.cost = cost;
        this.treatmentDate = treatmentDate;
    }

    public String getTreatmentId() {
        return treatmentId;
    }

    public void setTreatmentId(String treatmentId) {
        this.treatmentId = treatmentId;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getTreatmentDate() {
        return treatmentDate;
    }

    public void setTreatmentDate(String treatmentDate) {
        this.treatmentDate = treatmentDate;
    }

    @Override
    public String toString() {
        return treatmentId + " | " + description + " | $" + cost + " | " + treatmentDate;
    }
}
