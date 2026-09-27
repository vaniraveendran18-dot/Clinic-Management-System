package view;

import controller.NavigationController;

import javax.swing.*;
import java.awt.*;

public class MainMenuPanel extends JPanel {

    public MainMenuPanel(NavigationController nav) {
        setLayout(new BorderLayout());
        setBackground(UIStyle.COLOR_BACKGROUND);

        JLabel title = UIStyle.createTitle("Community Health Clinic - Main Menu");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0));
        add(title, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(5, 1, 15, 15));
        buttonPanel.setBackground(UIStyle.COLOR_BACKGROUND);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 200, 40, 200));

        JButton btnPatient = UIStyle.createButton("Register Patient");
        JButton btnDoctor = UIStyle.createButton("Register Doctor");
        JButton btnAppointment = UIStyle.createButton("Book Appointment");
        JButton btnTreatment = UIStyle.createButton("Enter Treatment");
        JButton btnReports = UIStyle.createButton("Reports / Search");

        btnPatient.addActionListener(e -> nav.navigateTo("REGISTER_PATIENT"));
        btnDoctor.addActionListener(e -> nav.navigateTo("REGISTER_DOCTOR"));
        btnAppointment.addActionListener(e -> nav.navigateTo("APPOINTMENT_BOOKING"));
        btnTreatment.addActionListener(e -> nav.navigateTo("TREATMENT_ENTRY"));
        btnReports.addActionListener(e -> nav.navigateTo("REPORTS"));

        buttonPanel.add(btnPatient);
        buttonPanel.add(btnDoctor);
        buttonPanel.add(btnAppointment);
        buttonPanel.add(btnTreatment);
        buttonPanel.add(btnReports);

        add(buttonPanel, BorderLayout.CENTER);
    }
}
