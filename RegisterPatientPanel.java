package view;

import controller.ClinicController;
import controller.NavigationController;
import util.InvalidInputException;

import javax.swing.*;
import java.awt.*;

public class RegisterPatientPanel extends JPanel {

    private final JTextField idField = UIStyle.createTextField();
    private final JTextField nameField = UIStyle.createTextField();
    private final JTextField contactField = UIStyle.createTextField();
    private final JTextField addressField = UIStyle.createTextField();
    private final JTextField historyField = UIStyle.createTextField();
    private final JTextField dateField = UIStyle.createTextField();
    private final JLabel statusLabel = UIStyle.createLabel(" ");

    public RegisterPatientPanel(ClinicController controller, NavigationController nav) {
        setLayout(new BorderLayout());
        setBackground(UIStyle.COLOR_BACKGROUND);

        add(UIStyle.createTitle("Register Patient"), BorderLayout.NORTH);

        JPanel form = UIStyle.createScreenPanel(new GridLayout(6, 2, 10, 10));
        form.add(UIStyle.createLabel("Patient ID:"));
        form.add(idField);
        form.add(UIStyle.createLabel("Name:"));
        form.add(nameField);
        form.add(UIStyle.createLabel("Contact Number:"));
        form.add(contactField);
        form.add(UIStyle.createLabel("Address:"));
        form.add(addressField);
        form.add(UIStyle.createLabel("Medical History:"));
        form.add(historyField);
        form.add(UIStyle.createLabel("Registration Date (yyyy-MM-dd):"));
        form.add(dateField);
        add(form, BorderLayout.CENTER);

        JPanel south = new JPanel(new BorderLayout());
        south.setBackground(UIStyle.COLOR_BACKGROUND);
        statusLabel.setForeground(Color.RED);
        south.add(statusLabel, BorderLayout.NORTH);

        JPanel buttonBar = UIStyle.createButtonBar();
        JButton registerBtn = UIStyle.createButton("Register");
        JButton backBtn = UIStyle.createButton("Back to Menu");

        registerBtn.addActionListener(e -> {
            try {
                controller.registerPatient(idField.getText(), nameField.getText(), contactField.getText(),
                        addressField.getText(), historyField.getText(), dateField.getText());
                statusLabel.setForeground(new Color(0, 128, 0));
                statusLabel.setText("Patient registered successfully.");
                clearFields();
            } catch (InvalidInputException ex) {
                statusLabel.setForeground(Color.RED);
                statusLabel.setText(ex.getMessage());
            }
        });

        backBtn.addActionListener(e -> nav.navigateTo("MAIN_MENU"));

        buttonBar.add(registerBtn);
        buttonBar.add(backBtn);
        south.add(buttonBar, BorderLayout.SOUTH);
        add(south, BorderLayout.SOUTH);
    }

    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        contactField.setText("");
        addressField.setText("");
        historyField.setText("");
        dateField.setText("");
    }
}
