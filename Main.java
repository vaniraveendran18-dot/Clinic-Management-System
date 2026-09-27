import controller.ClinicController;
import controller.NavigationController;
import model.Clinic;
import util.FileManager;
import view.*;

import javax.swing.*;
import java.awt.*;

/**
 * Entry point. Sets up the Model (Clinic), the FileManager, the
 * Controller, and a single JFrame that swaps between the 6 screens
 * using CardLayout so every screen shares the same window/style.
 */
public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::createAndShowApp);
    }

    private static void createAndShowApp() {
        Clinic clinic = new Clinic("Northside Community Health Clinic", "12 Wellness St, Melbourne");
        FileManager fileManager = new FileManager("clinic_data.ser");

        // Try loading any previously saved data on startup.
        try {
            Clinic loaded = fileManager.load();
            if (loaded != null) {
                clinic.setPatients(loaded.getPatients());
                clinic.setDoctors(loaded.getDoctors());
                clinic.setAppointments(loaded.getAppointments());
                clinic.setAdministrators(loaded.getAdministrators());
                clinic.setTreatments(loaded.getTreatments());
            }
        } catch (Exception e) {
            System.err.println("No existing data file, starting fresh.");
        }

        ClinicController controller = new ClinicController(clinic, fileManager);

        JFrame frame = new JFrame("Community Health Clinic Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        CardLayout cardLayout = new CardLayout();
        JPanel cardPanel = new JPanel(cardLayout);
        NavigationController nav = new NavigationController(cardLayout, cardPanel);

        cardPanel.add(new MainMenuPanel(nav), "MAIN_MENU");
        cardPanel.add(new RegisterPatientPanel(controller, nav), "REGISTER_PATIENT");
        cardPanel.add(new RegisterDoctorPanel(controller, nav), "REGISTER_DOCTOR");
        cardPanel.add(new AppointmentBookingPanel(controller, nav), "APPOINTMENT_BOOKING");
        cardPanel.add(new TreatmentEntryPanel(controller, nav), "TREATMENT_ENTRY");
        cardPanel.add(new ReportsPanel(controller, nav), "REPORTS");

        frame.add(cardPanel);
        frame.setVisible(true);
    }
}
