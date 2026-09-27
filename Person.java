package model;

import java.io.Serializable;

/**
 * Abstract base class for all people in the Community Health Clinic system.
 * Patient, Doctor and Administrator all extend this class (inheritance),
 * keeping shared fields/behaviour in one place (high cohesion, low coupling).
 */
public abstract class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    private String personId;
    private String name;
    private String contactNumber;
    private String address;

    public Person(String personId, String name, String contactNumber, String address) {
        this.personId = personId;
        this.name = name;
        this.contactNumber = contactNumber;
        this.address = address;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return name + " (ID: " + personId + ")";
    }
}
