package model;

public class Doctor extends Person {

    private static final long serialVersionUID = 1L;

    private String specialization;
    private String availableDays;

    public Doctor(String personId, String name, String contactNumber, String address,
                   String specialization, String availableDays) {
        super(personId, name, contactNumber, address);
        this.specialization = specialization;
        this.availableDays = availableDays;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getAvailableDays() {
        return availableDays;
    }

    public void setAvailableDays(String availableDays) {
        this.availableDays = availableDays;
    }

    @Override
    public String toString() {
        return "Doctor{" + getPersonId() + ", " + getName() + ", " + specialization + "}";
    }
}
