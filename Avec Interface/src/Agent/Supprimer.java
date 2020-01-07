package Agent;

import Classes_principales.Patient;
import Dialogue.dialogue;
import Dialogue.yes_no_option;
import RoundedBorders.RoundedButton;
import RoundedBorders.RoundedTextField;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Supprimer {
    public static void ui() {
        JFrame frame = new JFrame("Supprimer");
        frame.setSize(400, 180);

        JTextField numField = new RoundedTextField();
        numField.setBackground(new Color(69, 179, 157));
        numField.setForeground(Color.DARK_GRAY);
        numField.setBounds(130, 20, 230, 35);

        JLabel num_label = new JLabel("Numero ID :");
        num_label.setBounds(30, 20, 100, 35);
        num_label.setForeground(Color.darkGray);

        JButton supprime_Button = new RoundedButton("Supprimer");
        supprime_Button.setBounds(60, 75, 125, 35);
        supprime_Button.setBackground(new Color(244, 72, 72));
        supprime_Button.setForeground(Color.darkGray);
        supprime_Button.addActionListener(actionEvent -> {
            try {
                String numFieldSelectedItem = numField.getText();
                assert numFieldSelectedItem != null;
                if (Patient.check(Integer.parseInt(numFieldSelectedItem))) {
                    new yes_no_option("Vous êtes sûr de supprimer?");
                    yes_no_option.YES.addActionListener(actionEvent1 -> {
                        Patient.supprimerPatient(Integer.parseInt(numFieldSelectedItem));
                        frame.dispose();
                    });
                } else {
                    new dialogue("Ce compte n'exist pas");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        JButton btnAnnuler = new RoundedButton("Annuler");
        btnAnnuler.setBackground(new Color(105, 205, 160));
        btnAnnuler.setForeground(Color.DARK_GRAY);
        btnAnnuler.setBounds(210, 75, 125, 35);
        btnAnnuler.addActionListener(actionEvent -> frame.dispose());

        frame.add(btnAnnuler);
        frame.getContentPane().setBackground(new Color(72, 201, 176));
        frame.setResizable(false);
        frame.add(num_label);
        frame.add(numField);
        frame.add(supprime_Button);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}
