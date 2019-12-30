package Agent;

import Classes_principales.Patient;
import RoundedBorders.RoundedButton;
import RoundedBorders.RoundedTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.text.ParseException;

public class Main {
    String username;

    public Main() {
        username = "ID du patient";
    }

    public Main(String text) {
        username = text;
    } // pour utiliser dans moteur de recherche


    public void ui() {
        JFrame frame = new JFrame("Agent");
        frame.setSize(600, 300);
        frame.getContentPane().setBackground(Color.darkGray);

        JTextField dummy = new JTextField();
        dummy.requestFocusInWindow();

        JTextField idField = new RoundedTextField();
        idField.setText(username);
        idField.setBackground(new Color(79, 79, 79));
        idField.setForeground(Color.LIGHT_GRAY);
        idField.setHorizontalAlignment(JTextField.CENTER);
        idField.setBounds(50, 30, 500, 40);
        idField.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                try {
                    Moteur_de_recherche.Class recherche = new Moteur_de_recherche.Class();
                    recherche.ui();
                    frame.dispose();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {
            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
            }
        });

        JLabel resultatLabel = new JLabel();
        resultatLabel.setBounds(110, 180, 380, 30);
        resultatLabel.setForeground(Color.LIGHT_GRAY);

        JButton btnVerifier = new RoundedButton("Verifier");
        btnVerifier.setBounds(110, 80, 120, 30);
        btnVerifier.setBackground(new Color(51, 208, 240));
        btnVerifier.addActionListener(actionEvent -> {
            String idFieldString = idField.getText();
            assert idFieldString != null;
            int ID = Integer.parseInt(idFieldString.substring(0, idFieldString.indexOf(" ")));
            try {
                if (Patient.check(ID)) {
                    resultatLabel.setText("le patient exist");
                    frame.dispose();
                    Medecin.Main.ui();
                } else {
                    resultatLabel.setText("le patient n'existe pas");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        JButton btnModifier = new RoundedButton("Modifier");
        btnModifier.setBounds(240, 80, 120, 30);
        btnModifier.setBackground(new Color(243, 144, 57));
        btnModifier.addActionListener(actionEvent -> Modifier.takeNum());

        JButton btnSupprimer = new RoundedButton("Supprimer");
        btnSupprimer.setBounds(370, 80, 120, 30);
        btnSupprimer.setBackground(new Color(244, 72, 72));
        btnSupprimer.addActionListener(actionEvent -> {
            Supprimer.ui();
        });

        JButton btnCreer = new RoundedButton("Ajouter un nouveau patient");
        btnCreer.setBounds(110, 120, 380, 30);
        btnCreer.setBackground(Color.darkGray);
        btnCreer.setForeground(Color.LIGHT_GRAY);
        btnCreer.addActionListener(actionEvent -> {
            try {
                Creer_Patient.ui();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });

        frame.add(dummy);
        frame.add(btnVerifier);
        frame.add(btnModifier);
        frame.add(btnSupprimer);
        frame.add(btnCreer);
        frame.add(resultatLabel);
        frame.add(idField);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
