package Moteur_de_recherche;

import RoundedBorders.RoundedTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Class {
    public void ui_agent() {
        JDialog frame = new JDialog();
        frame.getContentPane().setBackground(new Color(72, 201, 176));
        frame.setSize(600, 400);

        JPanel textAreaPanel = new JPanel(null);
        textAreaPanel.setBounds(0, 100, 600, 300);
        textAreaPanel.setBackground(new Color(69, 179, 157));

        JTextArea textArea = new JTextArea();
        textArea.setBackground(new Color(69, 179, 157));
        textArea.setForeground(Color.darkGray);
        textArea.setBounds(50, 10, 500, 300);

        JTextField textField = new RoundedTextField();
        textField.setText("Entrez l'ID, le nom ou le prenom du patient");
        textField.setBackground(new Color(41, 41, 41));
        textField.setForeground(Color.LIGHT_GRAY);
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setBounds(50, 30, 500, 40);
        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent focusEvent) {
                if (textField.getText().equals("Entrez l'ID, le nom ou le prenom du patient"))
                    textField.setText("");
            }

            @Override
            public void focusLost(FocusEvent focusEvent) {
                if (textField.getText().isEmpty())
                    textField.setText("Entrez l'ID, le nom ou le prenom du patient");
            }
        });
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER) {
                    String string_of_return = textArea.getText();
                    if (string_of_return.indexOf("Nom") != string_of_return.lastIndexOf("Nom")) {
                        new Dialogue.dialogue("Mauvaise saisie");
                    } else {
                        frame.dispose();
                        Agent.Main agent = new Agent.Main(textArea.getText());
                        agent.ui();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
                try {
                    File Patients = new File("data/Patient.txt");
                    FileInputStream inputStream = new FileInputStream(Patients);
                    InputStreamReader reader = new InputStreamReader(inputStream);
                    Scanner scanner = new Scanner(reader);
                    StringBuilder tout = new StringBuilder();

                    while (scanner.hasNextLine()) {
                        String ligne = scanner.nextLine();
                        String nom = ligne.substring(ligne.indexOf("Nom:"), ligne.indexOf("$N"));
                        String prenom = ligne.substring(ligne.indexOf("Prenom:"), ligne.indexOf("$Pn"));
                        String ID = ligne.substring(0, ligne.indexOf(" "));
                        if (nom.contains(textField.getText()) || prenom.contains(textField.getText()) || ID.equals(textField.getText())) {
                            ligne = ligne.replace("$A", "");
                            ligne = ligne.replace("$Pn", "");
                            ligne = ligne.replace("$N", "");
                            ligne = ligne.replace("$Date", "");
                            tout.append(ligne).append("\n");
                        }
                    }
                    textArea.setText(tout.toString());

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        textAreaPanel.add(textArea);
        frame.add(textField);
        frame.add(textAreaPanel);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new Agent.Main().ui();
                super.windowClosing(e);
            }
        });
        frame.setVisible(true);
    }

    public void ui_medecin() {
        JDialog frame = new JDialog();
        frame.getContentPane().setBackground(new Color(46, 188, 207));
        frame.setSize(600, 400);

        JTextArea textArea = new JTextArea();
        textArea.setBackground(new Color(46, 188, 207));
        textArea.setForeground(Color.darkGray);
        textArea.setBounds(50, 100, 500, 300);

        JTextField textField = new RoundedTextField();
        textField.setText("Entrez l'ID, le nom ou le prenom du patient");
        textField.setBackground(new Color(226, 226, 226));
        textField.setForeground(Color.darkGray);
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setBounds(50, 30, 500, 40);
        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent focusEvent) {
                if (textField.getText().equals("Entrez l'ID, le nom ou le prenom du patient"))
                    textField.setText("");
            }

            @Override
            public void focusLost(FocusEvent focusEvent) {
                if (textField.getText().isEmpty())
                    textField.setText("Entrez l'ID, le nom ou le prenom du patient");
            }
        });
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER) {
                    String string_of_return = textArea.getText();
                    if (string_of_return.indexOf("Nom") != string_of_return.lastIndexOf("Nom")) {
                        new Dialogue.dialogue("Mauvaise saisie");
                    } else {
                        frame.dispose();
                        new Medecin.Main(textArea.getText()).ui();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
                try {
                    File Patients = new File("data/Patient.txt");
                    FileInputStream inputStream = new FileInputStream(Patients);
                    InputStreamReader reader = new InputStreamReader(inputStream);
                    Scanner scanner = new Scanner(reader);
                    StringBuilder tout = new StringBuilder();

                    while (scanner.hasNextLine()) {
                        String ligne = scanner.nextLine();
                        String nom = ligne.substring(ligne.indexOf("Nom:"), ligne.indexOf("$N"));
                        String prenom = ligne.substring(ligne.indexOf("Prenom:"), ligne.indexOf("$Pn"));
                        String ID = ligne.substring(0, ligne.indexOf(" "));
                        if (nom.contains(textField.getText()) || prenom.contains(textField.getText()) || ID.equals(textField.getText())) {
                            ligne = ligne.replace("$A", "");
                            ligne = ligne.replace("$Pn", "");
                            ligne = ligne.replace("$N", "");
                            ligne = ligne.replace("$Date", "");
                            tout.append(ligne).append("\n");
                        }
                    }
                    textArea.setText(tout.toString());

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        frame.add(textField);
        frame.add(textArea);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new Medecin.Main();
                super.windowClosing(e);
            }
        });
        frame.setVisible(true);
    }

    public void ui_technicien() {
        JDialog frame = new JDialog();
        frame.setSize(600, 400);
        frame.getContentPane().setBackground(new Color(255, 240, 95));

        JPanel panel = new JPanel();
        panel.setBackground(new Color(178, 164, 75));
        panel.setBounds(0, 100, 600, 300);

        JTextArea textArea = new JTextArea();
        textArea.setBackground(new Color(178, 164, 75));
        textArea.setBounds(50, 10, 500, 290);

        JTextField textField = new RoundedTextField();
        textField.setText("Entrez l'ID, le nom ou le prenom du patient");
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setBackground(new Color(178, 164, 75));
        textField.setBounds(50, 30, 500, 40);
        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent focusEvent) {
                if (textField.getText().equals("Entrez l'ID, le nom ou le prenom du patient"))
                    textField.setText("");
            }

            @Override
            public void focusLost(FocusEvent focusEvent) {
                if (textField.getText().isEmpty())
                    textField.setText("Entrez l'ID, le nom ou le prenom du patient");
            }
        });
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER) {
                    String string_of_return = textArea.getText();
                    if (string_of_return.indexOf("Nom") != string_of_return.lastIndexOf("Nom")) {
                        new Dialogue.dialogue("Mauvaise saisie");
                    } else {
                        frame.dispose();
                        try {
                            new Technicien.Main(textArea.getText()).ui();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
                try {
                    File Patients = new File("data/Patient.txt");
                    FileInputStream inputStream = new FileInputStream(Patients);
                    InputStreamReader reader = new InputStreamReader(inputStream);
                    Scanner scanner = new Scanner(reader);
                    StringBuilder tout = new StringBuilder();

                    while (scanner.hasNextLine()) {
                        String ligne = scanner.nextLine();
                        String nom = ligne.substring(ligne.indexOf("Nom:"), ligne.indexOf("$N"));
                        String prenom = ligne.substring(ligne.indexOf("Prenom:"), ligne.indexOf("$Pn"));
                        String ID = ligne.substring(0, ligne.indexOf(" "));
                        if (nom.contains(textField.getText()) || prenom.contains(textField.getText()) || ID.equals(textField.getText())) {
                            ligne = ligne.replace("$A", "");
                            ligne = ligne.replace("$Pn", "");
                            ligne = ligne.replace("$N", "");
                            ligne = ligne.replace("$Date", "");
                            tout.append(ligne).append("\n");
                        }
                    }
                    textArea.setText(tout.toString());

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        frame.add(panel);
        frame.add(textField);
        panel.add(textArea);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    new Technicien.Main().ui();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
                super.windowClosing(e);
            }
        });
        frame.setVisible(true);
    }
}
