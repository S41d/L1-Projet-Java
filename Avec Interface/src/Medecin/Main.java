package Medecin;

import Agent.Creer_Patient;
import Agent.Modifier;
import Agent.Supprimer;
import Classes_principales.Patient;
import RoundedBorders.RoundedButton;
import RoundedBorders.RoundedTextField;

import java.awt.Color;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.text.ParseException;
import java.util.Scanner;

public class Main{
    public static void ui(){
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

    }
}