package Medecin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.io.*;
import java.util.Scanner;

public class Supprimer {
    public void ui(int ID) {
        JFrame frame = new JFrame("Medecin/Supprimer");
        frame.setSize(820, 400);

        String[] column = {"ID", "Consultations", "Appareil 1", "Appareil 2", "Appareil 3"};
        String[][] lignes = new String[0][column.length];

        DefaultTableModel tableModel = new DefaultTableModel(lignes, column) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        try {
            File Consultations = new File("data/Consultations.txt");
            FileInputStream inputStream = new FileInputStream(Consultations);
            InputStreamReader reader = new InputStreamReader(inputStream);
            Scanner scanner_Consultations = new Scanner(reader);

            while (scanner_Consultations.hasNextLine()) {
                String ligneConsultation = scanner_Consultations.nextLine();
                int IDPatient = Integer.parseInt(ligneConsultation.substring(ligneConsultation.indexOf("ID: "), ligneConsultation.indexOf("$ID")).substring(4));

                if (IDPatient == ID) {
                    int IDConsultation = Integer.parseInt(ligneConsultation.substring(0, ligneConsultation.indexOf(" ")));
                    String Consultation = "";
                    String Appareil = "";
                    String Appareil_1 = "";
                    String Appareil_2 = "";
                    String Appareil_3 = "";
                    if (ligneConsultation.contains("$APPAREIL$")) {
                        Consultation = ligneConsultation.substring(ligneConsultation.indexOf("$ID"), ligneConsultation.indexOf("$APPAREIL$")).substring(4);
                        Appareil = detailsPatients.getAppareil(IDConsultation);
                        if (Appareil.contains("Appareil_1"))
                            Appareil_1 = Appareil.substring(Appareil.indexOf("Appareil_1"), Appareil.indexOf("$A1")).substring(12);
                        if (Appareil.contains("Appareil_2"))
                            Appareil_2 = Appareil.substring(Appareil.indexOf("Appareil_2"), Appareil.indexOf("$A2")).substring(12);
                        if (Appareil.contains("Appareil_3"))
                            Appareil_3 = Appareil.substring(Appareil.indexOf("Appareil_3"), Appareil.indexOf("$A3")).substring(12);
                    } else {
                        Consultation = ligneConsultation.substring(ligneConsultation.indexOf("$ID")).substring(4);
                    }
                    tableModel.addRow(new Object[]{IDConsultation, Consultation, Appareil_1, Appareil_2, Appareil_3});
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JTable table = new JTable();
        table.setBackground(Color.lightGray);
        table.setForeground(Color.darkGray);
        table.setModel(tableModel);
        table.setBounds(0, 20, 700, 345);

        JTableHeader header = table.getTableHeader();
        header.setBounds(0, 0, 700, 20);
        header.getColumnModel().getColumn(1).setPreferredWidth(400);
        header.getColumnModel().getColumn(0).setPreferredWidth(40);

        JButton supprimerbtn = new JButton("Supprimer");
        supprimerbtn.setBackground(new Color(250, 63, 67));
        supprimerbtn.setForeground(Color.lightGray);
        supprimerbtn.setBounds(700, 0, 120, 365);
        supprimerbtn.addActionListener(actionEvent -> {
            try {
                int id = (int) table.getValueAt(table.getSelectedRow(), 0);
                Creer_Consultation.copy();
                File Consultations = new File("data/Consultations.txt");
                File Tempo = new File("data/ConsultationsTempo.txt");
                FileInputStream inputStream = new FileInputStream(Tempo);
                InputStreamReader reader = new InputStreamReader(inputStream);
                Scanner scanner = new Scanner(reader);
                PrintWriter writer = new PrintWriter(Consultations);

                while (scanner.hasNextLine()) {
                    String ligne = scanner.nextLine();
                    if (Integer.parseInt(ligne.substring(0, ligne.indexOf(" "))) != id) {
                        supprimerAppareil(id);
                        writer.println(ligne);
                    }
                }
                writer.close();
                scanner.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        });

        frame.add(supprimerbtn);
        frame.add(header);
        frame.add(table);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public static void supprimerAppareil(int ID) {
        try {
            File Appareils = new File("data/Appareils.txt");
            File Tempo = new File("data/AppareilsTempo.txt");

            try (FileInputStream fis = new FileInputStream(Appareils);
                 FileOutputStream fos = new FileOutputStream(Tempo)) {
                int len;
                byte[] buffer = new byte[4096];
                while ((len = fis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
            } catch (IOException e) {
                // ... handle IO exception
            }


            FileInputStream inputStream = new FileInputStream(Tempo);
            InputStreamReader reader = new InputStreamReader(inputStream);
            Scanner scanner = new Scanner(reader);
            PrintWriter writer = new PrintWriter(Appareils);

            while (scanner.hasNextLine()) {
                String ligne = scanner.nextLine();
                if (Integer.parseInt(ligne.substring(0, ligne.indexOf(" "))) != ID) {
                    writer.println(ligne);
                }
            }
            writer.close();
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Supprimer().ui(1);
    }
}
