package model;

public class Patient extends Person {

    private static final long serialVersionUID = 1L;

    private String medicalHistory;
    private String registrationDate;

    public Patient(String personId, String name, String contactNumber, String address,
                    String medicalHistory, String registrationDate) {
        super(personId, name, contactNumber, address);
        this.medicalHistory = medicalHistory;
        this.registrationDate = registrationDate;
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Override
    public String toString() {
        return "Patient{" + getPersonId() + ", " + getName() + ", registered " + registrationDate + "}";
    }
}
