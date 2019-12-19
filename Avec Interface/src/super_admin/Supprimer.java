package super_admin;

import java.awt.event.*;
import java.util.*;
import javax.management.relation.Role;
import javax.swing.*;
import javax.swing.Timer;
import java.io.*;
import java.awt.Color;
import RoundFields.*;

public class Supprimer {
    public static void ui() {
        JFrame frame = new JFrame("Supprimer");
        frame.setSize(350, 150);
        
        JTextField numField = new JTextField();
        numField.setBounds(110, 20, 100, 30);
        
        JLabel num_label = new JLabel("Numero ID :");
        num_label.setBounds(15, 20, 100, 30);
        num_label.setForeground(Color.lightGray);

        JTextField Roles_Field = new JTextField();
        Roles_Field.setBounds(110, 60, 100, 30);

        JLabel ID_Roles_label = new JLabel("Role :");
        ID_Roles_label.setBounds(15, 60, 100, 30);
        ID_Roles_label.setForeground(Color.LIGHT_GRAY);

        JButton supprime_Button = new JButton("Supprimer");
        supprime_Button.setBounds(230, 40, 100, 30);
        supprime_Button.setBackground(new Color(244, 72, 72));
        supprime_Button.setForeground(Color.darkGray);
        supprime_Button.addActionListener(arg0 -> {
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

    public static void supprime_compte(int num, String role) throws FileNotFoundException {
        write_temporary();
        write_file(num, role);
    }

    static void write_temporary() throws FileNotFoundException {
        File temporary = new File("data/temporary.txt");
        File file = new File("data/Roles.txt");

        PrintWriter tempWriter = new PrintWriter(temporary);
        InputStream inputStream = new FileInputStream(file);
        InputStreamReader reader = new InputStreamReader(inputStream);
        Scanner scanner = new Scanner(reader);

        while (scanner.hasNextLine()) {
            String string = scanner.nextLine();
            tempWriter.println(string);
        }

        tempWriter.close();
        scanner.close();
    }

    static void write_file(int num, String role) throws FileNotFoundException {
        File temporary = new File("data/temporary.txt");
        File file = new File("data/Roles.txt");
        
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