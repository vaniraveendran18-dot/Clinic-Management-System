package view;

import controller.ClinicController;
import controller.NavigationController;
import util.InvalidInputException;

import javax.swing.*;
import java.awt.*;

public class AppointmentBookingPanel extends JPanel {

    private final JTextField idField = UIStyle.createTextField();
    private final JTextField patientIdField = UIStyle.createTextField();
    private final JTextField doctorIdField = UIStyle.createTextField();
    private final JTextField dateField = UIStyle.createTextField();
    private final JTextField timeField = UIStyle.createTextField();
    private final JComboBox<String> statusBox = new JComboBox<>(new String[]{"Scheduled", "Completed", "Cancelled"});
    private final JLabel statusLabel = UIStyle.createLabel(" ");

    public AppointmentBookingPanel(ClinicController controller, NavigationController nav) {
        setLayout(new BorderLayout());
        setBackground(UIStyle.COLOR_BACKGROUND);

        add(UIStyle.createTitle("Book Appointment"), BorderLayout.NORTH);

        statusBox.setFont(UIStyle.FONT_FIELD);

        JPanel form = UIStyle.createScreenPanel(new GridLayout(6, 2, 10, 10));
        form.add(UIStyle.createLabel("Appointment ID:"));
        form.add(idField);
        form.add(UIStyle.createLabel("Patient ID:"));
        form.add(patientIdField);
        form.add(UIStyle.createLabel("Doctor ID:"));
        form.add(doctorIdField);
        form.add(UIStyle.createLabel("Date (yyyy-MM-dd):"));
        form.add(dateField);
        form.add(UIStyle.createLabel("Time (HH:mm):"));
        form.add(timeField);
        form.add(UIStyle.createLabel("Status:"));
        form.add(statusBox);
        add(form, BorderLayout.CENTER);

        JPanel south = new JPanel(new BorderLayout());
        south.setBackground(UIStyle.COLOR_BACKGROUND);
        statusLabel.setForeground(Color.RED);
        south.add(statusLabel, BorderLayout.NORTH);

        JPanel buttonBar = UIStyle.createButtonBar();
        JButton bookBtn = UIStyle.createButton("Book");
        JButton backBtn = UIStyle.createButton("Back to Menu");

        bookBtn.addActionListener(e -> {
            try {
                controller.bookAppointment(idField.getText(), patientIdField.getText(), doctorIdField.getText(),
                        dateField.getText(), timeField.getText(), (String) statusBox.getSelectedItem());
                statusLabel.setForeground(new Color(0, 128, 0));
                statusLabel.setText("Appointment booked successfully.");
                clearFields();
            } catch (InvalidInputException ex) {
                statusLabel.setForeground(Color.RED);
                statusLabel.setText(ex.getMessage());
            }
        });

        backBtn.addActionListener(e -> nav.navigateTo("MAIN_MENU"));

        buttonBar.add(bookBtn);
        buttonBar.add(backBtn);
        south.add(buttonBar, BorderLayout.SOUTH);
        add(south, BorderLayout.SOUTH);
    }

    private void clearFields() {
        idField.setText("");
        patientIdField.setText("");
        doctorIdField.setText("");
        dateField.setText("");
        timeField.setText("");
        statusBox.setSelectedIndex(0);
    }
}
