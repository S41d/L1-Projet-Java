package Medecin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;

public class Main {
    String userString;

    public Main() {
        userString = "ID du patient?";
    }

    public Main(String text) {
        userString = text;
    }

    public void ui() {
        JFrame frame = new JFrame("Medecin");
        frame.setSize(608, 300);
        frame.getContentPane().setBackground(new Color(52, 225, 245));

        JTextField dummy = new JTextField();
        dummy.requestFocusInWindow();

        JTextField userField = new JTextField();
        userField.setBounds(55, 30, 504, 40);
        userField.setText(userString);
        userField.setBackground(new Color(46, 188, 207));
        userField.setForeground(Color.white);
        userField.setHorizontalAlignment(JTextField.CENTER);
        userField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        int ID = Integer.parseInt(userField.getText().substring(0, userField.getText().indexOf(" ")));
                        new detailsPatients(ID);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                }
            }
        });

        userField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (userField.getText().isEmpty() || userField.getText().equals("ID du patient?")) {
                    frame.dispose();
                    new Moteur_de_recherche.Class().ui_medecin();
                }
            }
        });

        JButton details = new JButton("Détails");
        details.setBackground(new Color(106, 250, 168));
        details.setBounds(54, 90, 168, 60);
        details.addActionListener(actionEvent -> {
            try {
                new detailsPatients(Integer.parseInt(userField.getText().substring(0, userField.getText().indexOf(" "))));
            } catch (FileNotFoundException e) {
                new Dialogue.dialogue("Mauvaise saisie", Color.darkGray, Color.white, Color.darkGray, new Color(46, 188, 207));
            }
        });

        JButton modifier = new JButton("Modifier");
        modifier.setBackground(new Color(250, 177, 64));
        modifier.setBounds(222, 90, 168, 60);
        modifier.addActionListener(actionEvent -> {
            try {
                new Modifier().ui(Integer.parseInt(userField.getText().substring(0, userField.getText().indexOf(" "))));
            } catch (NumberFormatException e) {
                new Dialogue.dialogue("Mauvaise saisie", Color.darkGray, Color.white, Color.darkGray, new Color(46, 188, 207));
            }
        });

        JButton supprimer = new JButton("Supprimer");
        supprimer.setBackground(new Color(250, 63, 67));
        supprimer.setBounds(390, 90, 168, 60);
        supprimer.addActionListener(actionEvent -> {
            try {
                new Supprimer().ui(Integer.parseInt(userField.getText().substring(0, userField.getText().indexOf(" "))));
            } catch (NumberFormatException e) {
                new Dialogue.dialogue("Mauvaise saisie", Color.darkGray, Color.white, Color.darkGray, new Color(46, 188, 207));
            }
        });

        String creerbtnstring = "Creer une\n consultation";
        JButton creer = new JButton("<html>" + creerbtnstring.replaceAll("\\n", "<br>") + "</html>");
        creer.setBackground(new Color(69, 179, 157));
        creer.setBounds(54, 150, 168, 60);
        creer.addActionListener(actionEvent -> {
            try {
                new Creer_Consultation().ui(Integer.parseInt(userField.getText().substring(0, userField.getText().indexOf(" "))));
            } catch (NumberFormatException e) {
                new Dialogue.dialogue("Mauvaise saisie", Color.darkGray, Color.white, Color.darkGray, new Color(46, 188, 207));
            }
        });

        String exporterbtnstring = "Exporter des\n consultations";
        JButton exporter = new JButton("<html>" + exporterbtnstring.replaceAll("\\n", "<br>") + "</html>");
        exporter.setBackground(new Color(250, 196, 94));
        exporter.setBounds(222, 150, 168, 60);
        exporter.addActionListener(actionEvent -> {
            try {
                new exporterConsultation().ui(Integer.parseInt(userField.getText().substring(0, userField.getText().indexOf(" "))));
            } catch (NumberFormatException e) {
                new Dialogue.dialogue("Mauvaise saisie", Color.darkGray, Color.white, Color.darkGray, new Color(46, 188, 207));
            }
        });

        String ordonnancebtnstring = "Creer une\n ordonnance";
        JButton ordonnance = new JButton("<html>" + ordonnancebtnstring.replaceAll("\\n", "<br>") + "</html>");
        ordonnance.setBackground(new Color(255, 240, 95));
        ordonnance.setBounds(390, 150, 168, 60);
        ordonnance.addActionListener(actionEvent -> {
            try {
                new creerOrdonnanace().ui(Integer.parseInt(userField.getText().substring(0, userField.getText().indexOf(" "))));
            } catch (NumberFormatException e) {
                new Dialogue.dialogue("Mauvaise saisie", Color.darkGray, Color.white, Color.darkGray, new Color(46, 188, 207));
            }
        });

        frame.add(ordonnance);
        frame.add(exporter);
        frame.add(modifier);
        frame.add(supprimer);
        frame.add(creer);
        frame.add(details);
        frame.add(dummy);
        frame.add(userField);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}