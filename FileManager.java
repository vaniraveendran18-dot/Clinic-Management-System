package util;

import model.Clinic;

import java.io.*;

/**
 * Task 7 (Data Management): saves the Clinic object (and everything in
 * its ArrayLists) to disk and reads it back using Java serialization.
 * Update and Delete change the ArrayLists through ClinicController,
 * which then calls save() so every change is written to the file.
 */
public class FileManager {

    private final String filePath;

    public FileManager(String filePath) {
        this.filePath = filePath;
    }

    /** Save - also called after every add, update and delete. */
    public void save(Clinic clinic) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePath))) {
            out.writeObject(clinic);
        }
    }

    /** Load the Clinic object back from disk. Returns null if no file exists yet. */
    public Clinic load() throws IOException, ClassNotFoundException {
        File file = new File(filePath);
        if (!file.exists()) {
            return null;
        }
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePath))) {
            return (Clinic) in.readObject();
        }
    }
}
