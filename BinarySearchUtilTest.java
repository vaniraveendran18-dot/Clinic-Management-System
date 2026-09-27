package test;

import model.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.BinarySearchUtil;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchUtilTest {

    private ArrayList<Patient> patients;

    @BeforeEach
    void setUp() {
        patients = new ArrayList<>();
        patients.add(new Patient("P003", "Chris", "0400", "Addr", "None", "2026-01-01"));
        patients.add(new Patient("P001", "Amy", "0401", "Addr", "None", "2026-01-02"));
        patients.add(new Patient("P004", "Dee", "0402", "Addr", "None", "2026-01-03"));
        patients.add(new Patient("P002", "Ben", "0403", "Addr", "None", "2026-01-04"));
    }

    @Test
    void testFindExistingPatientInUnsortedList() {
        Patient result = BinarySearchUtil.searchPatientById(patients, "P002");
        assertNotNull(result);
        assertEquals("Ben", result.getName());
    }

    @Test
    void testFindFirstAndLastIds() {
        assertEquals("Amy", BinarySearchUtil.searchPatientById(patients, "P001").getName());
        assertEquals("Dee", BinarySearchUtil.searchPatientById(patients, "P004").getName());
    }

    @Test
    void testIdNotFoundReturnsNull() {
        Patient result = BinarySearchUtil.searchPatientById(patients, "P999");
        assertNull(result);
    }

    @Test
    void testEmptyListReturnsNull() {
        assertNull(BinarySearchUtil.searchPatientById(new ArrayList<>(), "P001"));
    }
}
