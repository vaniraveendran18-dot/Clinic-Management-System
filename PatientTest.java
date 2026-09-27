package test;

import model.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PatientTest {

    private Patient patient;

    @BeforeEach
    void setUp() {
        patient = new Patient("P001", "Sam Lee", "0400111222",
                "1 Main St, Melbourne", "No known allergies", "2026-01-10");
    }

    @Test
    void testConstructorAndGetters() {
        assertEquals("P001", patient.getPersonId());
        assertEquals("Sam Lee", patient.getName());
        assertEquals("0400111222", patient.getContactNumber());
        assertEquals("1 Main St, Melbourne", patient.getAddress());
        assertEquals("No known allergies", patient.getMedicalHistory());
        assertEquals("2026-01-10", patient.getRegistrationDate());
    }

    @Test
    void testSetters() {
        patient.setName("Samantha Lee");
        patient.setMedicalHistory("Penicillin allergy");
        assertEquals("Samantha Lee", patient.getName());
        assertEquals("Penicillin allergy", patient.getMedicalHistory());
    }

    @Test
    void testToStringContainsKeyInfo() {
        String result = patient.toString();
        assertTrue(result.contains("P001"));
        assertTrue(result.contains("Sam Lee"));
    }
}
