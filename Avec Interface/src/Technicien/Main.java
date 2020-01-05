package Technicien;

import Medecin.detailsPatients;
import RoundedBorders.RoundedButton;
import RoundedBorders.RoundedTextField;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
    public void ui() {
        JFrame frame = new JFrame();
        frame.setSize(800, 400);

        JTextField userField = new RoundedTextField();
        userField.setBounds(100, 20, 600, 40);

        String[] column = {"ID", "Consultations", "Appareil 1", "Appareil 2", "Appareil 3"};
        String[][] lignes = new String[0][column.length];

        DefaultTableModel tableModel = new DefaultTableModel(lignes, column) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JButton button = new RoundedButton("Voir la list des consultations");
        button.setBounds(200, 80, 400, 40);
        button.addActionListener(actionEvent -> {
            try {
                int ID = Integer.parseInt(userField.getText().substring(0, userField.getText().indexOf(" ")));

                File Consultations = new File("data/Consultations.txt");
                FileInputStream inputStream = new FileInputStream(Consultations);
                InputStreamReader reader = new InputStreamReader(inputStream);
                Scanner scanner_Consultations = new Scanner(reader);

                while (scanner_Consultations.hasNextLine()) {
                    String ligneConsultation = scanner_Consultations.nextLine();
                    int IDPatient = Integer.parseInt(ligneConsultation.substring(ligneConsultation.indexOf("ID: "), ligneConsultation.indexOf("$ID")).substring(4));

                    if (IDPatient == ID) {
                        int IDConsultation = Integer.parseInt(ligneConsultation.substring(0, ligneConsultation.indexOf(" ")));
                        String Consultation;
                        String Appareil;
                        String Appareil_1 = "";
                        String Appareil_2 = "";
                        String Appareil_3 = "";
                        if (ligneConsultation.contains("$APPAREIL$")) {
                            Consultation = ligneConsultation.substring(ligneConsultation.indexOf("$ID"), ligneConsultation.indexOf("$APPAREIL$")).substring(4);
                            Appareil = detailsPatients.getAppareil(IDConsultation);
                            if (Appareil.contains("Appareil_1")) {
                                Appareil_1 = Appareil.substring(Appareil.indexOf("Appareil_1"), Appareil.indexOf("$A1")).substring(12);
                            }
                            if (Appareil.contains("Appareil_2")) {
                                Appareil_2 = Appareil.substring(Appareil.indexOf("Appareil_2"), Appareil.indexOf("$A2")).substring(12);
                            }
                            if (Appareil.contains("Appareil_3")) {
                                Appareil_3 = Appareil.substring(Appareil.indexOf("Appareil_3"), Appareil.indexOf("$A3")).substring(12);
                            }
                        } else {
                            Consultation = ligneConsultation.substring(ligneConsultation.indexOf("$ID")).substring(4);
                        }
                        tableModel.addRow(new Object[]{IDConsultation, Consultation, Appareil_1, Appareil_2, Appareil_3});
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });


        JTable table = new JTable();
        table.setBackground(Color.lightGray);
        table.setForeground(Color.darkGray);
        table.setModel(tableModel);
        table.setBounds(50, 150, 700, 400);

        JTableHeader header = table.getTableHeader();
        header.setBounds(50, 130, 700, 20);
        header.getColumnModel().getColumn(1).setPreferredWidth(400);
        header.getColumnModel().getColumn(0).setPreferredWidth(40);

        frame.add(button);
        frame.add(userField);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public static void main(String[] args) {
        new Main().ui();
    }
}
