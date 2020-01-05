package Medecin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.Scanner;

public class Creer_Consultation {
    public void ui(int ID_pat, int ID_med) {
        JFrame frame = new JFrame("Nouvelle consultation");
        frame.setSize(800, 600);
        frame.getContentPane().setBackground(new Color(46, 188, 207));

        JLabel label = new JLabel("Consultation:");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBounds(50, 50, 500, 40);

        JTextArea consultationArea = new JTextArea();
        consultationArea.setBackground(new Color(226, 226, 226));
        consultationArea.setBounds(50, 100, 500, 400);

        JPanel chechboxBackground = new JPanel(null);
        chechboxBackground.setBackground(new Color(42, 126, 145));
        chechboxBackground.setBounds(600, 0, 200, 800);


        JPanel checkBoxPanel = new JPanel(new GridLayout(6, 1));
        checkBoxPanel.setBounds(0, 100, 200, 400);
        checkBoxPanel.setBackground(new Color(42, 126, 145));
        chechboxBackground.add(checkBoxPanel);

        JCheckBox Appareil_1 = new JCheckBox("Appareil 1");
        Appareil_1.setBackground(new Color(243, 144, 57));
        Appareil_1.setBorder(BorderFactory.createMatteBorder(0, 50, 0, 0, Color.DARK_GRAY));
        JCheckBox Appareil_2 = new JCheckBox("Appareil 2");
        Appareil_2.setBackground(new Color(91, 188, 148));
        Appareil_2.setBorder(BorderFactory.createMatteBorder(0, 50, 0, 0, Color.DARK_GRAY));
        JCheckBox Appareil_3 = new JCheckBox("Appareil 3");
        Appareil_3.setBackground(new Color(52, 225, 245));
        Appareil_3.setBorder(BorderFactory.createMatteBorder(0, 50, 0, 0, Color.DARK_GRAY));

        consultationArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER) {
                    Creer(Appareil_1, Appareil_2, Appareil_3, ID_pat, consultationArea);
                    frame.dispose();
                    new Dialogue.dialogue("Consultation créée");
                }
            }
        });

        JLabel dummylabel = new JLabel();
        JLabel dummylabel2 = new JLabel();
        JButton validerbtn = new JButton("Valider");
        validerbtn.addActionListener(actionEvent -> {
            Creer(Appareil_1, Appareil_2, Appareil_3, ID_pat, consultationArea);
            frame.dispose();
            new Dialogue.dialogue("Consultation créée");
        });
        validerbtn.setBackground(Color.lightGray);
        checkBoxPanel.add(Appareil_1);
        checkBoxPanel.add(Appareil_2);
        checkBoxPanel.add(Appareil_3);
        checkBoxPanel.add(dummylabel2);
        checkBoxPanel.add(dummylabel);
        checkBoxPanel.add(validerbtn);

        frame.add(label);
        frame.add(chechboxBackground);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.add(consultationArea);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void Creer(JCheckBox appareil_1, JCheckBox appareil_2, JCheckBox appareil_3, int ID_pat, JTextArea consultationArea) {
        try {
            copy();

            File Consultations = new File("data/Consultations.txt");
            File Tempo = new File("data/ConsultationsTempo.txt");
            FileInputStream inputStream = new FileInputStream(Tempo);
            InputStreamReader reader = new InputStreamReader(inputStream);
            Scanner scanner = new Scanner(reader);
            PrintWriter writer = new PrintWriter(Consultations);

            int counter = 0;
            while (scanner.hasNextLine()) {
                String ligne = scanner.nextLine();
                writer.println(ligne);
                counter = Integer.parseInt(ligne.substring(0, ligne.indexOf(" ")));
                counter++;
            }

            if (appareil_1.isSelected() || appareil_2.isSelected() || appareil_3.isSelected()) {
                writer.write(counter + " ID: " + ID_pat + "$ID " + consultationArea.getText() + " $APPAREIL$ ");
                if (appareil_1.isSelected())
                    writer.write(" $A1 " + appareil_1.getText() + "$\\A1,");
                if (appareil_2.isSelected())
                    writer.write(" $A2 " + appareil_2.getText() + "$A\\2,");
                if (appareil_3.isSelected())
                    writer.write(" $A3 " + appareil_3.getText() + "$A\\3");
            } else {
                writer.write(counter + " ID: " + ID_pat + "$ID " + consultationArea.getText());
            }

            creerDansAppareil(ID_pat, appareil_1, appareil_2, appareil_3);

            writer.close();
            Tempo.delete();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void copy() {
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

    public static void creerDansAppareil(int ID, JCheckBox appareil_1, JCheckBox appareil_2, JCheckBox appareil_3) throws FileNotFoundException {
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

        int counter = 0;
        while (scanner.hasNextLine()) {
            String ligne = scanner.nextLine();
            writer.println(ligne);
            counter = Integer.parseInt(ligne.substring(0, ligne.indexOf(" ")));
            counter++;
        }

        if (appareil_1.isSelected() || appareil_2.isSelected() || appareil_3.isSelected()) {
            writer.write(counter + " ID: " + ID + "$ID " + " $APPAREIL$ ");
            if (appareil_1.isSelected())
                writer.write(" $A1 " + appareil_1.getText() + "$\\A1,");
            if (appareil_2.isSelected())
                writer.write(" $A2 " + appareil_2.getText() + "$A\\2,");
            if (appareil_3.isSelected())
                writer.write(" $A3 " + appareil_3.getText() + "$A\\3");
        } else {
            writer.write(counter + " ID: " + ID + "$ID");
        }
        writer.close();
        Tempo.delete();
    }

    public static void main(String[] args) {
        new Creer_Consultation().ui(1, 1);
    }
}
