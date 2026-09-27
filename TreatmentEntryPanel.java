package view;

import controller.ClinicController;
import controller.NavigationController;
import util.InvalidInputException;

import javax.swing.*;
import java.awt.*;

public class TreatmentEntryPanel extends JPanel {

    private final JTextField idField = UIStyle.createTextField();
    private final JTextField appointmentIdField = UIStyle.createTextField();
    private final JTextField descriptionField = UIStyle.createTextField();
    private final JTextField costField = UIStyle.createTextField();
    private final JTextField dateField = UIStyle.createTextField();
    private final JLabel statusLabel = UIStyle.createLabel(" ");

    public TreatmentEntryPanel(ClinicController controller, NavigationController nav) {
        setLayout(new BorderLayout());
        setBackground(UIStyle.COLOR_BACKGROUND);

        add(UIStyle.createTitle("Enter Treatment"), BorderLayout.NORTH);

        JPanel form = UIStyle.createScreenPanel(new GridLayout(5, 2, 10, 10));
        form.add(UIStyle.createLabel("Treatment ID:"));
        form.add(idField);
        form.add(UIStyle.createLabel("Appointment ID:"));
        form.add(appointmentIdField);
        form.add(UIStyle.createLabel("Description:"));
        form.add(descriptionField);
        form.add(UIStyle.createLabel("Cost:"));
        form.add(costField);
        form.add(UIStyle.createLabel("Treatment Date (yyyy-MM-dd):"));
        form.add(dateField);
        add(form, BorderLayout.CENTER);

        JPanel south = new JPanel(new BorderLayout());
        south.setBackground(UIStyle.COLOR_BACKGROUND);
        statusLabel.setForeground(Color.RED);
        south.add(statusLabel, BorderLayout.NORTH);

        JPanel buttonBar = UIStyle.createButtonBar();
        JButton saveBtn = UIStyle.createButton("Save Treatment");
        JButton backBtn = UIStyle.createButton("Back to Menu");

        saveBtn.addActionListener(e -> {
            try {
                controller.enterTreatment(idField.getText(), appointmentIdField.getText(),
                        descriptionField.getText(), costField.getText(), dateField.getText());
                statusLabel.setForeground(new Color(0, 128, 0));
                statusLabel.setText("Treatment saved successfully.");
                clearFields();
            } catch (InvalidInputException ex) {
                statusLabel.setForeground(Color.RED);
                statusLabel.setText(ex.getMessage());
            }
        });

        backBtn.addActionListener(e -> nav.navigateTo("MAIN_MENU"));

        buttonBar.add(saveBtn);
        buttonBar.add(backBtn);
        south.add(buttonBar, BorderLayout.SOUTH);
        add(south, BorderLayout.SOUTH);
    }

    private void clearFields() {
        idField.setText("");
        appointmentIdField.setText("");
        descriptionField.setText("");
        costField.setText("");
        dateField.setText("");
    }
}
