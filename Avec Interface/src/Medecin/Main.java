package Medecin;

import RoundedBorders.RoundedTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;

public class Main {
    String userString;
    int IDMedecin;

    public Main(int IDmedecin) {
        IDMedecin = IDmedecin;
        userString = "ID du patient?";
    }

    public Main(String text, int IDmedecin) {
        userString = text;
        IDMedecin = IDmedecin;
    }

    public void ui() {
        System.out.print(IDMedecin);
        JFrame frame = new JFrame("Medecin");
        frame.setSize(600, 300);
        frame.getContentPane().setBackground(new Color(46, 188, 207));

        JTextField dummy = new JTextField();
        dummy.requestFocusInWindow();

        JTextField userField = new RoundedTextField();
        userField.setBounds(50, 30, 500, 40);
        userField.setText(userString);
        userField.setBackground(new Color(226, 226, 226));
        userField.setForeground(Color.darkGray);
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


        frame.add(dummy);
        frame.add(userField);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}