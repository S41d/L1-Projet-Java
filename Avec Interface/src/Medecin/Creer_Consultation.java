package Medecin;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Creer_Consultation {
    public void ui(int ID_pat, int ID_med) {
        JFrame frame = new JFrame("Nouvelle consultation");
        frame.setSize(800, 800);

        JTextArea consultationArea = new JTextArea();
        consultationArea.setBounds(100, 250, 600, 400);

        JPanel chechboxBackground = new JPanel(null);
        chechboxBackground.setBackground(Color.LIGHT_GRAY);
        chechboxBackground.setBounds(0, 0, 800, 200);

        JPanel checkBoxPanel = new JPanel(new GridLayout(1, 3));
        checkBoxPanel.setBounds(100, 0, 600, 200);
        checkBoxPanel.setBackground(Color.DARK_GRAY);
        chechboxBackground.add(checkBoxPanel);

        JCheckBox Appareil_1 = new JCheckBox("Appareil 1");
        Appareil_1.setBackground(Color.DARK_GRAY);
        JCheckBox Appareil_2 = new JCheckBox("Appareil 2");
        JCheckBox Appareil_3 = new JCheckBox("Appareil 3");

        checkBoxPanel.add(Appareil_1);
        checkBoxPanel.add(Appareil_2);
        checkBoxPanel.add(Appareil_3);

        frame.add(chechboxBackground);
        frame.setLayout(null);
        frame.add(consultationArea);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public static void main(String[] args) {
        new Creer_Consultation().ui(1, 1);
    }

    public static void NouvelleConsultation() {
        File Consultations = new File("data/Consultations.txt");
        File Tempo = new File("data/ConsultationsTempo.txt");
        try (FileInputStream fis = new FileInputStream(Consultations);
             FileOutputStream fos = new FileOutputStream(Tempo)) {
            int len;
            byte[] buffer = new byte[4096];
            while ((len = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
