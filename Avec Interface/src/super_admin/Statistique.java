package super_admin;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;


public class Statistique {
    public Statistique() {
        JFrame frame = new JFrame("Statistique");
        frame.setSize(400, 300);
        frame.getContentPane().setBackground(new Color(79, 79, 79));

        JLabel label = new JLabel("Nombre de patients qui utilise:");
        label.setBounds(50, 30, 300, 40);
        label.setHorizontalAlignment(JLabel.CENTER);

        JLabel nbAppareil1 = new JLabel("Appareil 1: ");
        nbAppareil1.setBounds(50, 80, 150, 40);

        JLabel nombre1 = new JLabel(String.valueOf(appareil1()));
        nombre1.setBounds(200, 80, 150, 40);

        JLabel nbAppareil2 = new JLabel("Appareil 2: ");
        nbAppareil2.setBounds(50, 130, 150, 40);

        JLabel nombre2 = new JLabel(String.valueOf(appareil2()));
        nombre2.setBounds(200, 130, 150, 40);

        JLabel nbAppareil3 = new JLabel("Appareil 3: ");
        nbAppareil3.setBounds(50, 180, 150, 40);

        JLabel nombre3 = new JLabel(String.valueOf(appareil3()));
        nombre3.setBounds(200, 180, 150, 40);

        ArrayList<JLabel> labelList = new ArrayList<>();
        labelList.add(label);
        labelList.add(nbAppareil1);
        labelList.add(nombre1);
        labelList.add(nbAppareil2);
        labelList.add(nombre2);
        labelList.add(nbAppareil3);
        labelList.add(nombre3);

        for (JLabel jlabel :
                labelList) {
            jlabel.setForeground(Color.lightGray);
        }

        frame.add(label);
        frame.add(nbAppareil1);
        frame.add(nombre1);
        frame.add(nbAppareil2);
        frame.add(nombre2);
        frame.add(nbAppareil3);
        frame.add(nombre3);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public static int appareil1() {
        int counter = 0;
        try {
            File Consultation = new File("data/Consultations.txt");
            InputStream inputStream = new FileInputStream(Consultation);
            InputStreamReader reader = new InputStreamReader(inputStream);
            Scanner scanner = new Scanner(reader);

            while (scanner.hasNextLine()) {
                String ligne = scanner.nextLine();
                if (ligne.contains("$A1")) {
                    counter++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return counter;
    }

    public static int appareil2() {
        int counter = 0;
        try {
            File Consultation = new File("data/Consultations.txt");
            InputStream inputStream = new FileInputStream(Consultation);
            InputStreamReader reader = new InputStreamReader(inputStream);
            Scanner scanner = new Scanner(reader);

            while (scanner.hasNextLine()) {
                String ligne = scanner.nextLine();
                if (ligne.contains("$A2")) {
                    counter++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return counter;
    }

    public static int appareil3() {
        int counter = 0;
        try {
            File Consultation = new File("data/Consultations.txt");
            InputStream inputStream = new FileInputStream(Consultation);
            InputStreamReader reader = new InputStreamReader(inputStream);
            Scanner scanner = new Scanner(reader);

            while (scanner.hasNextLine()) {
                String ligne = scanner.nextLine();
                if (ligne.contains("$A3")) {
                    counter++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return counter;
    }
}
