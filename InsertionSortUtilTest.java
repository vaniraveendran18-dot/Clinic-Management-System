package test;

import model.Appointment;
import model.Doctor;
import model.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.InsertionSortUtil;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class InsertionSortUtilTest {

    private ArrayList<Appointment> appointments;
    private Patient patient;
    private Doctor doctor;

    @BeforeEach
    void setUp() {
        patient = new Patient("P001", "Sam", "0400", "Addr", "None", "2026-01-01");
        doctor = new Doctor("D001", "Dr. Iyer", "0401", "Addr", "General", "Mon-Fri");

        appointments = new ArrayList<>();
        appointments.add(new Appointment("A003", patient, doctor, "2026-03-15", "09:00", "Scheduled"));
        appointments.add(new Appointment("A001", patient, doctor, "2026-01-05", "10:00", "Scheduled"));
        appointments.add(new Appointment("A002", patient, doctor, "2026-02-10", "11:00", "Scheduled"));
    }

    @Test
    void testAppointmentsAreSortedByDateAscending() {
        ArrayList<Appointment> sorted = InsertionSortUtil.sortAppointmentsByDate(appointments);
        assertEquals("A001", sorted.get(0).getAppointmentId());
        assertEquals("A002", sorted.get(1).getAppointmentId());
        assertEquals("A003", sorted.get(2).getAppointmentId());
    }

    @Test
    void testOriginalListIsUnchanged() {
        InsertionSortUtil.sortAppointmentsByDate(appointments);
        assertEquals("A003", appointments.get(0).getAppointmentId());
    }

    @Test
    void testSingleElementListStaysSame() {
        ArrayList<Appointment> single = new ArrayList<>();
        single.add(appointments.get(0));
        ArrayList<Appointment> sorted = InsertionSortUtil.sortAppointmentsByDate(single);
        assertEquals(1, sorted.size());
    }
}
