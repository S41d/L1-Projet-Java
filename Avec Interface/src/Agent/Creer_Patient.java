package Agent;

import Classes_principales.Patient;
import Dialogue.dialogue;
import RoundedBorders.RoundedButton;
import RoundedBorders.RoundedFormattedTextField;
import RoundedBorders.RoundedTextField;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.ParseException;

public class Creer_Patient {
    public static void ui() throws ParseException {
        JFrame frame = new JFrame("Créer un nouveau compte");
        frame.setSize(650, 400);
        frame.getContentPane().setBackground(new Color(72, 201, 176));

        JTextField userField = new RoundedTextField();
        userField.setBackground(new Color(69, 179, 157));
        userField.setForeground(Color.darkGray);
        userField.setBounds(150, 30, 400, 40);

        JLabel login_Label = new JLabel("Nom :");
        login_Label.setForeground(Color.darkGray);
        login_Label.setBounds(50, 30, 100, 40);

        JTextField prenomField = new RoundedTextField();
        prenomField.setBackground(new Color(69, 179, 157));
        prenomField.setForeground(Color.darkGray);
        prenomField.setBounds(150, 90, 400, 40);

        JLabel pass2_label = new JLabel("Prenom :");
        pass2_label.setForeground(Color.darkGray);
        pass2_label.setBounds(50, 90, 100, 40);

        MaskFormatter adresseFormatter = new MaskFormatter("#####");
        JFormattedTextField adresseField = new RoundedFormattedTextField();
        adresseFormatter.install(adresseField);
        adresseField.setBackground(new Color(69, 179, 157));
        adresseField.setForeground(Color.darkGray);
        adresseField.setBounds(150, 150, 400, 40);

        JLabel adresse_label = new JLabel("<html>Code <br>postal :</html>");
        adresse_label.setForeground(Color.darkGray);
        adresse_label.setBounds(50, 150, 100, 40);

        MaskFormatter dateFormatter = new MaskFormatter("##-##-####");
        dateFormatter.setPlaceholderCharacter(' ');
        JFormattedTextField dateFeild = new RoundedFormattedTextField();
        dateFeild.setText("jj-mm-aaaa");
        dateFeild.setBackground(new Color(69, 179, 157));
        dateFeild.setForeground(Color.darkGray);
        dateFeild.setBounds(150, 210, 400, 40);
        dateFeild.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent focusEvent) {
                if (dateFeild.getText().equals("jj-mm-aaaa")) {
                    dateFeild.setText("");
                    dateFormatter.install(dateFeild);
                    dateFormatter.setAllowsInvalid(false);
                }
            }

            @Override
            public void focusLost(FocusEvent focusEvent) {
                if (dateFeild.getText().equals("  -  -    ")) {
                    dateFormatter.setAllowsInvalid(true);
                    dateFeild.setText("jj-mm-aaaa");
                }
            }
        });

        JLabel date_lablel = new JLabel("Date :");
        date_lablel.setForeground(Color.darkGray);
        date_lablel.setBounds(50, 210, 100, 40);

        JButton btnAnnuler = new RoundedButton("Annuler");
        btnAnnuler.setBackground(new Color(240, 74, 82));
        btnAnnuler.setForeground(Color.DARK_GRAY);
        btnAnnuler.setBounds(335, 300, 150, 40);
        btnAnnuler.addActionListener(actionEvent -> frame.dispose());

        JButton create_Button = new RoundedButton("Créer compte");
        create_Button.setBounds(175, 300, 150, 40);
        create_Button.setBackground(new Color(105, 205, 160));
        create_Button.setBackground(Color.lightGray);
        create_Button.addActionListener(arg0 -> {
            if (userField.getText().isEmpty() || prenomField.getText().isEmpty() || dateFeild.getText().equals("  -  -    ") || adresseField.getText().isEmpty()) {
                new dialogue("Un ou plusieurs champs est vide");
            } else {
                Patient.nouveauPatient(userField.getText(), prenomField.getText(), Integer.parseInt(adresseField.getText()), dateFeild.getText());
            }
        });

        frame.add(date_lablel);
        frame.add(dateFeild);
        frame.add(adresseField);
        frame.add(adresse_label);
        frame.add(btnAnnuler);
        frame.add(prenomField);
        frame.add(pass2_label);
        frame.add(userField);
        frame.add(login_Label);
        frame.add(create_Button);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}
