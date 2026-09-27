package view;

import controller.ClinicController;
import controller.NavigationController;
import model.Appointment;
import model.Doctor;
import model.Patient;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class ReportsPanel extends JPanel {

    private final JTextField searchIdField = UIStyle.createTextField();
    private final JTextArea outputArea = new JTextArea();
    private final JLabel statusLabel = UIStyle.createLabel(" ");

    public ReportsPanel(ClinicController controller, NavigationController nav) {
        setLayout(new BorderLayout());
        setBackground(UIStyle.COLOR_BACKGROUND);

        add(UIStyle.createTitle("Reports / Search / Sort"), BorderLayout.NORTH);

        JPanel top = UIStyle.createScreenPanel(new BorderLayout());

        JPanel searchRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        searchRow.setBackground(UIStyle.COLOR_BACKGROUND);
        searchRow.add(UIStyle.createLabel("Patient ID to search:"));
        searchIdField.setPreferredSize(new Dimension(140, 28));
        searchRow.add(searchIdField);
        JButton searchBtn = UIStyle.createButton("Search Patient");
        searchRow.add(searchBtn);
        top.add(searchRow, BorderLayout.NORTH);

        outputArea.setFont(UIStyle.FONT_FIELD);
        outputArea.setEditable(false);
        top.add(new JScrollPane(outputArea), BorderLayout.CENTER);
        add(top, BorderLayout.CENTER);

        searchBtn.addActionListener(e -> {
            Patient p = controller.searchPatientById(searchIdField.getText().trim());
            if (p == null) {
                outputArea.setText("No patient found with ID: " + searchIdField.getText().trim());
            } else {
                outputArea.setText("Found patient:\n" + p);
            }
        });

        JPanel south = new JPanel(new BorderLayout());
        south.setBackground(UIStyle.COLOR_BACKGROUND);
        statusLabel.setForeground(new Color(0, 128, 0));
        south.add(statusLabel, BorderLayout.NORTH);

        JPanel buttonBar = new JPanel(new GridLayout(2, 3, 10, 10));
        buttonBar.setBackground(UIStyle.COLOR_BACKGROUND);
        buttonBar.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        JButton sortBtn = UIStyle.createButton("Sort Appointments");
        JButton doctorScheduleBtn = UIStyle.createButton("Doctor Schedules");
        JButton saveBtn = UIStyle.createButton("Save Data");
        JButton loadBtn = UIStyle.createButton("Load Data");
        JButton backBtn = UIStyle.createButton("Back to Menu");

        sortBtn.addActionListener(e -> {
            ArrayList<Appointment> sorted = controller.getAppointmentsSortedByDate();
            StringBuilder sb = new StringBuilder("Appointments sorted by date:\n");
            for (Appointment a : sorted) {
                sb.append(a).append("\n");
            }
            outputArea.setText(sb.toString());
        });

        doctorScheduleBtn.addActionListener(e -> {
            StringBuilder sb = new StringBuilder("Doctor schedules:\n");
            for (Doctor d : controller.getClinic().getDoctors()) {
                sb.append(d.getName()).append(" (").append(d.getSpecialization())
                        .append(") - available: ").append(d.getAvailableDays()).append("\n");
                for (Appointment a : controller.getClinic().getAppointments()) {
                    if (a.getDoctor().getPersonId().equals(d.getPersonId())) {
                        sb.append("    -> ").append(a.getAppointmentDate()).append(" ")
                                .append(a.getAppointmentTime()).append(" with ")
                                .append(a.getPatient().getName()).append("\n");
                    }
                }
            }
            outputArea.setText(sb.toString());
        });

        saveBtn.addActionListener(e -> {
            try {
                controller.saveData();
                statusLabel.setForeground(new Color(0, 128, 0));
                statusLabel.setText("Data saved to file.");
            } catch (IOException ex) {
                statusLabel.setForeground(Color.RED);
                statusLabel.setText("Could not save: " + ex.getMessage());
            }
        });

        loadBtn.addActionListener(e -> {
            try {
                if (!controller.loadData()) {
                    statusLabel.setForeground(Color.RED);
                    statusLabel.setText("No saved data file found yet.");
                } else {
                    statusLabel.setForeground(new Color(0, 128, 0));
                    statusLabel.setText("Data loaded from file (" + controller.getClinic().getPatients().size()
                            + " patients, " + controller.getClinic().getAppointments().size() + " appointments).");
                }
            } catch (IOException | ClassNotFoundException ex) {
                statusLabel.setForeground(Color.RED);
                statusLabel.setText("Could not load: " + ex.getMessage());
            }
        });

        backBtn.addActionListener(e -> nav.navigateTo("MAIN_MENU"));

        buttonBar.add(sortBtn);
        buttonBar.add(doctorScheduleBtn);
        buttonBar.add(saveBtn);
        buttonBar.add(loadBtn);
        buttonBar.add(backBtn);
        south.add(buttonBar, BorderLayout.SOUTH);
        add(south, BorderLayout.SOUTH);
    }
}
