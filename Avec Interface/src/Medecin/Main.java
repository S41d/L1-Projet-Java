package Medecin;

import RoundedBorders.RoundedTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class Main {
    public static void ui() {
        JFrame frame = new JFrame();
        frame.setSize(600, 300);

        JTextField userField = new RoundedTextField();
        userField.setBounds(50, 30, 500, 40);
        userField.setText("ID du patient?");
        userField.setBackground(new Color(79, 79, 79));
        userField.setForeground(Color.LIGHT_GRAY);
        userField.setHorizontalAlignment(JTextField.CENTER);
        userField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent focusEvent) {
                if (userField.getText().toUpperCase().equals("ID DU PATIENT?"))
                    userField.setText("");
            }

            @Override
            public void focusLost(FocusEvent focusEvent) {
                if (userField.getText().isEmpty())
                    userField.setText("ID du patient?");
            }
        });

        frame.add(userField);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}