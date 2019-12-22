package super_admin;

import RoundedBorders.RoundedButton;
import RoundedBorders.RoundedTextField;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Scanner;

public class Supprimer {
    public static void ui() {
        JFrame frame = new JFrame("Supprimer");
        frame.setSize(400, 220);
        
        JTextField numField = new RoundedTextField();
        numField.setBackground(Color.LIGHT_GRAY);
        numField.setForeground(Color.DARK_GRAY);
        numField.setBounds(130, 20, 230, 35);

        JLabel num_label = new JLabel("Numero ID :");
        num_label.setBounds(30, 20, 100, 35);
        num_label.setForeground(Color.lightGray);

        JTextField Roles_Field = new RoundedTextField();
        Roles_Field.setBackground(Color.LIGHT_GRAY);
        Roles_Field.setForeground(Color.DARK_GRAY);
        Roles_Field.setBounds(130, 70, 230, 35);

        JLabel ID_Roles_label = new JLabel("Role :");
        ID_Roles_label.setBounds(30, 70, 100, 35);
        ID_Roles_label.setForeground(Color.LIGHT_GRAY);

        JButton supprime_Button = new RoundedButton("Supprimer");
        supprime_Button.setBounds(60, 125, 125, 35);
        supprime_Button.setBackground(new Color(244, 72, 72));
        supprime_Button.setForeground(Color.darkGray);
        supprime_Button.addActionListener(actionEvent -> {
            try {
                if (check(Integer.parseInt(numField.getText())))  {
                    int ans = JOptionPane.showConfirmDialog(frame, "Vous etes sur de supprimer ce compte?", "", JOptionPane.YES_NO_OPTION);
                    if (ans != 1) {
                        supprime_compte(Integer.parseInt(numField.getText()), Roles_Field.getText());
                        frame.dispose();
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Ce compte n'existe pas", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (FileNotFoundException e) {
                System.out.println("file not found exception");
            }
        });

        JButton btnAnnuler = new RoundedButton("Annuler");
        btnAnnuler.setBackground(new Color(105, 205, 160));
        btnAnnuler.setForeground(Color.DARK_GRAY);
        btnAnnuler.setBounds(210, 125, 125, 35);
        btnAnnuler.addActionListener(actionEvent -> frame.dispose());

        frame.add(btnAnnuler);
        frame.add(Roles_Field);
        frame.add(ID_Roles_label);
        frame.getContentPane().setBackground(Color.DARK_GRAY);
        frame.setResizable(false);
        frame.add(num_label);
        frame.add(numField);
        frame.add(supprime_Button);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);

    }


    public static boolean check(int num) throws FileNotFoundException {
        boolean account_exists = false;
        FileReader reader = new FileReader("data/Roles.txt");
        Scanner scanner = new Scanner(reader);
        while (scanner.hasNextLine()) {
            String string = scanner.nextLine();
            int numero = Integer.parseInt(string.substring(0, string.indexOf(" ")));
            if (numero == num) {
                account_exists = true;
                break;
            } else {
                account_exists = false;
            }
        }
        scanner.close();
        return account_exists;
    }


    public static void supprime_role(int idRole, String role){
        String string = "";
        switch (role){
            case "Medecin":
                string = "data/Medecin.txt";
                break;
            case "Agent":
                string = "data/Agents.txt";
                break;
            case "Techniciens":
                string = "data/Techniciens.txt";
                break;
        }
        File Roles = new File(string);
        File Temporaire = new File("data/Roles_Temporaire.txt");

        try (FileInputStream fis = new FileInputStream(Roles);
             FileOutputStream fos = new FileOutputStream(Temporaire)) {
            int len;
            byte[] buffer = new byte[4096];
            while ((len = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try{
            PrintWriter fileWriter = new PrintWriter(Roles);
            InputStream inputStream = new FileInputStream(Temporaire);
            InputStreamReader reader = new InputStreamReader(inputStream);
            Scanner scanner = new Scanner(reader);

            while (scanner.hasNextLine()) {
                String ligne = scanner.nextLine();
                String string1 = ligne.substring(0, ligne.indexOf(" "));
                int id = Integer.parseInt(string1);
                if (id != idRole) fileWriter.println(ligne);
            }
            fileWriter.close();
            scanner.close();
            boolean bool = Temporaire.delete();
        } catch (FileNotFoundException i){
            i.printStackTrace();
        }
    }

    static void supprime_compte(int num, String role) throws FileNotFoundException {
        File temporary = new File("data/temporary.txt");
        File file = new File("data/Roles.txt");

        try (FileInputStream fis = new FileInputStream(file);
           FileOutputStream fos = new FileOutputStream(temporary)) {
            int len;
            byte[] buffer = new byte[4096];
            while ((len = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        PrintWriter fileWriter = new PrintWriter(file);
        InputStream inputStream = new FileInputStream(temporary);
        InputStreamReader reader = new InputStreamReader(inputStream);
        Scanner scanner = new Scanner(reader);

        supprime_role(num, role);

        while (scanner.hasNextLine()) {
            String ligne = scanner.nextLine();
            String string = ligne.substring(ligne.indexOf(" "));
            string = string.substring(1);
            int id = Integer.parseInt(string.substring(0, string.indexOf(" ")));
            if (id == num && ("Role: " + role).equals(string.substring(string.indexOf("Role:"), string.indexOf("$R"))));
            else fileWriter.println(ligne);
        }
        fileWriter.close();
        scanner.close();
        boolean bool = temporary.delete();
    }
}