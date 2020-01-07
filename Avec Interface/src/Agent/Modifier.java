package Agent;

import Classes_principales.Patient;
import RoundedBorders.RoundedButton;
import RoundedBorders.RoundedFormattedTextField;
import RoundedBorders.RoundedTextField;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public class Modifier {
    public static void takeNum() {
        JFrame frame = new JFrame("ID");
        frame.setSize(300, 150);
        frame.getContentPane().setBackground(new Color(72, 201, 176));

        JTextField dummy = new JTextField();
        dummy.requestFocusInWindow();

        JTextField textField = new RoundedTextField();
        textField.setText("ID Global");
        textField.setHorizontalAlignment(JTextField.LEFT);
        textField.setBackground(new Color(69, 179, 157));
        textField.setForeground(Color.darkGray);
        textField.setBounds(30, 20, 240, 30);
        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent focusEvent) {
                if (textField.getText().equals("ID Global"))
                    textField.setText("");
            }

            @Override
            public void focusLost(FocusEvent focusEvent) {
                if (textField.getText().isEmpty())
                    textField.setText("ID Global");
            }
        });

        JButton btnOK = new RoundedButton("OK");
        btnOK.setBackground(new Color(42, 126, 145));
        btnOK.setForeground(Color.darkGray);
        btnOK.setBounds(70, 65, 75, 30);
        btnOK.addActionListener(actionEvent -> {
            try {
                int ID = Integer.parseInt(textField.getText());
                if (Patient.check(ID)) {
                    frame.dispose();
                    ui(ID);
                } else {
                    new Dialogue.dialogue("Ce patient n'est pas encore dans la liste");
                }
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            } catch (NumberFormatException i) {
                new Dialogue.dialogue("Veuillez mettre un code patient");
            }
        });

        JButton btnAnnuler = new RoundedButton("Annuler");
        btnAnnuler.setBackground(new Color(244, 72, 72));
        btnAnnuler.setForeground(Color.DARK_GRAY);
        btnAnnuler.setBounds(155, 65, 75, 30);
        btnAnnuler.addActionListener(actionEvent -> frame.dispose());

        frame.add(btnAnnuler);
        frame.add(btnOK);
        frame.add(dummy);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(textField);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    public static void ui(int num) throws FileNotFoundException, ParseException {
        File file = new File("data/Patient.txt");
        FileReader roles_reader = new FileReader(file);
        Scanner scanner_roles = new Scanner(roles_reader);

        String NOM = "";
        String PRENOM = "";
        String ADRESSE = "";
        String DATE = "";

        while (scanner_roles.hasNextLine()) {
            String ligne = scanner_roles.nextLine();
            int ID = Integer.parseInt(ligne.substring(0, ligne.indexOf(" ")));
            if (ID == num) {
                NOM = ligne.substring(ligne.indexOf("Nom:"), ligne.indexOf("$N"));
                NOM = NOM.substring(NOM.indexOf(" "));
                NOM = NOM.substring(1);

                PRENOM = ligne.substring(ligne.indexOf("Prenom:"), ligne.indexOf("$Pn"));
                PRENOM = PRENOM.substring(PRENOM.indexOf(" "));
                PRENOM = PRENOM.substring(1);

                ADRESSE = ligne.substring(ligne.indexOf("Adresse:"), ligne.indexOf("$A"));
                ADRESSE = ADRESSE.substring(ADRESSE.indexOf(" "));
                ADRESSE = ADRESSE.substring(1);

                DATE = ligne.substring(ligne.indexOf("Date de naissance:"), ligne.indexOf("$Date"));
                DATE = DATE.substring(DATE.lastIndexOf(" "));
                DATE = DATE.substring(1);
            }
        }


        JFrame frame = new JFrame();
        frame.setSize(600, 400);
        frame.getContentPane().setBackground(new Color(72, 201, 176));

        JTextField dummy = new JTextField();
        dummy.requestFocusInWindow();

        JLabel nom_lable = new JLabel("Nom:");
        nom_lable.setForeground(Color.darkGray);
        nom_lable.setBounds(50, 30, 100, 40);

        JTextField nomField = new RoundedTextField();
        nomField.setText(NOM);
        nomField.setBackground(new Color(69, 179, 157));
        nomField.setForeground(Color.darkGray);
        nomField.setHorizontalAlignment(JTextField.CENTER);
        nomField.setBounds(150, 30, 400, 40);

        JLabel prenom_label = new JLabel("Prenom:");
        prenom_label.setForeground(Color.darkGray);
        prenom_label.setBounds(50, 90, 100, 40);

        JTextField prenomField = new RoundedTextField();
        prenomField.setText(PRENOM);
        prenomField.setBackground(new Color(69, 179, 157));
        prenomField.setForeground(Color.darkGray);
        prenomField.setHorizontalAlignment(JTextField.CENTER);
        prenomField.setBounds(150, 90, 400, 40);

        JLabel adresse_label = new JLabel("Code Postale:");
        adresse_label.setForeground(Color.darkGray);
        adresse_label.setBounds(50, 150, 100, 40);

        JTextField adresseField = new RoundedTextField();
        adresseField.setText(ADRESSE);
        adresseField.setBackground(new Color(69, 179, 157));
        adresseField.setForeground(Color.darkGray);
        adresseField.setHorizontalAlignment(JTextField.CENTER);
        adresseField.setBounds(150, 150, 400, 40);

        JLabel date_label = new JLabel("Date:");
        date_label.setForeground(Color.darkGray);
        date_label.setBounds(50, 210, 100, 40);

        MaskFormatter dateFormatter = new MaskFormatter("##-##-####");
        JFormattedTextField dateField = new RoundedFormattedTextField();
        dateFormatter.install(dateField);
        dateField.setText(DATE);
        dateField.setBackground(new Color(69, 179, 157));
        dateField.setForeground(Color.darkGray);
        dateField.setHorizontalAlignment(JTextField.CENTER);
        dateField.setBounds(150, 210, 400, 40);

        JButton btnModifier = new RoundedButton("Modifier");
        btnModifier.setBackground(new Color(243, 144, 57));
        btnModifier.setForeground(Color.DARK_GRAY);
        btnModifier.setBounds(160, 270, 150, 40);
        btnModifier.addActionListener(actionEvent -> {
            String finalNOM = nomField.getText();
            String finalPRENOM = prenomField.getText();
            int finalADRESSE = Integer.parseInt(adresseField.getText());
            String finalDATE = dateField.getText();
            Patient.modifier(num, finalNOM, finalPRENOM, finalADRESSE, finalDATE);
            frame.dispose();
            new Dialogue.dialogue("Modifications enregistrés", Color.darkGray, new Color(72, 201, 176), Color.lightGray, new Color(41, 41, 41));
        });

        JButton btnAnnuler = new RoundedButton("Annuler");
        btnAnnuler.setBackground(new Color(240, 74, 82));
        btnAnnuler.setForeground(Color.DARK_GRAY);
        btnAnnuler.setBounds(320, 270, 150, 40);
        btnAnnuler.addActionListener(actionEvent -> frame.dispose());

        frame.add(nomField);
        frame.add(nom_lable);
        frame.add(prenomField);
        frame.add(prenom_label);
        frame.add(adresseField);
        frame.add(adresse_label);
        frame.add(dateField);
        frame.add(date_label);
        frame.add(btnModifier);
        frame.add(btnAnnuler);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);

    }
}
