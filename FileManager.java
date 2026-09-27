package util;

import model.Clinic;

import java.io.*;

/**
 * Task 7 (Data Management): handles persisting the Clinic object (and
 * everything inside its ArrayLists) to disk and reading it back, using
 * Java object serialization. Update/Delete are done by modifying the
 * in-memory ArrayLists on the Clinic object (via its own methods) and
 * then calling save() again to write the change to disk.
 */
public class FileManager {

    private final String filePath;

    public FileManager(String filePath) {
        this.filePath = filePath;
    }

    /** Save (and also used for Update, after the caller edits the Clinic object). */
    public void save(Clinic clinic) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePath))) {
            out.writeObject(clinic);
        }
    }

    /** Load the Clinic object back from disk. */
    public Clinic load() throws IOException, ClassNotFoundException {
        File file = new File(filePath);
        if (!file.exists()) {
            return null;
        }
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePath))) {
            return (Clinic) in.readObject();
        }
    }

    /**
     * Delete removes an entity from the Clinic's ArrayLists (via Clinic's
     * own removeXById methods) then re-saves the file so the change is
     * persisted. Example usage: deletePatient(clinic, "P001").
     */
    public void deletePatient(Clinic clinic, String patientId) throws IOException {
        clinic.removePatientById(patientId);
        save(clinic);
    }

    public void deleteAppointment(Clinic clinic, String appointmentId) throws IOException {
        clinic.removeAppointmentById(appointmentId);
        save(clinic);
    }

    public void deleteTreatment(Clinic clinic, String treatmentId) throws IOException {
        clinic.removeTreatmentById(treatmentId);
        save(clinic);
    }
}
