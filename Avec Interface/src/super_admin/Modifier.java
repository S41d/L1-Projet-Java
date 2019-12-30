package super_admin;

import RoundedBorders.RoundedButton;
import RoundedBorders.RoundedPasswordField;
import RoundedBorders.RoundedTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.*;
import java.util.Scanner;

public class Modifier {
    public static void takeNum() {
        JFrame frame = new JFrame("ID");
        frame.setSize(300, 150);

        JTextField dummy = new JTextField();
        dummy.requestFocusInWindow();

        JTextField textField = new RoundedTextField();
        textField.setText("ID Global");
        textField.setHorizontalAlignment(JTextField.LEFT);
        textField.setBackground(new Color(79, 79, 79));
        textField.setForeground(Color.LIGHT_GRAY);
        textField.setBounds(30, 20, 240, 30);
        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent focusEvent) {
                if (textField.getText().equals("ID Global"))
                    textField.setText("");
            }

            @Override
            public void focusLost(FocusEvent focusEvent) {
                if (textField.getText().isEmpty())
                    textField.setText("ID Global");
            }
        });

        JButton btnOK = new RoundedButton("OK");
        btnOK.setBackground(new Color(105, 205, 160));
        btnOK.setForeground(Color.DARK_GRAY);
        btnOK.setBounds(70, 65, 75, 30);
        btnOK.addActionListener(actionEvent -> {
            try {
                int ID = Integer.parseInt(textField.getText());
                if (check(ID)) {
                    frame.dispose();
                    ui(ID);
                } else {
                    error_ui();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });

        JButton btnAnnuler = new RoundedButton("Annuler");
        btnAnnuler.setBackground(new Color(244, 72, 72));
        btnAnnuler.setForeground(Color.DARK_GRAY);
        btnAnnuler.setBounds(155, 65, 75, 30);
        btnAnnuler.addActionListener(actionEvent -> frame.dispose());

        frame.getContentPane().setBackground(Color.darkGray);
        frame.add(btnAnnuler);
        frame.add(btnOK);
        frame.add(dummy);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(textField);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    public static void ui(int num) throws FileNotFoundException {
        File Roles = new File("data/Roles.txt");
        FileReader roles_reader = new FileReader(Roles);
        Scanner scanner_roles = new Scanner(roles_reader);

        String USER = "";
        String PASS = "";
        String ROLE = "";

        while (scanner_roles.hasNextLine()) {
            String ligne = scanner_roles.nextLine();
            int ID = Integer.parseInt(ligne.substring(0, ligne.indexOf(" ")));
            if (ID == num) {
                USER = ligne.substring(ligne.indexOf("Username:"), ligne.indexOf("$USER"));
                USER = USER.substring(USER.indexOf(" "));
                USER = USER.substring(1);

                PASS = ligne.substring(ligne.indexOf("Password:"), ligne.indexOf("$P"));
                PASS = PASS.substring(PASS.indexOf(" "));
                PASS = PASS.substring(1);

                ROLE = ligne.substring(ligne.indexOf("Role:"), ligne.indexOf("$R"));
                ROLE = ROLE.substring(ROLE.indexOf(" "));
                ROLE = ROLE.substring(1);
            }
        }


        JFrame frame = new JFrame("Modifier");
        frame.setSize(600, 320);
        frame.getContentPane().setBackground(Color.darkGray);

        JTextField userField = new RoundedTextField();
        userField.setText(USER);
        userField.setBackground(new Color(79, 79, 79));
        userField.setForeground(Color.LIGHT_GRAY);
        userField.setHorizontalAlignment(JTextField.CENTER);
        userField.setBounds(150, 30, 400, 40);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setForeground(Color.LIGHT_GRAY);
        userLabel.setBounds(50, 30, 100, 40);


        JPasswordField passwordField = new RoundedPasswordField();
        passwordField.setText(PASS);
        passwordField.setBackground(new Color(79, 79, 79));
        passwordField.setForeground(Color.LIGHT_GRAY);
        passwordField.setHorizontalAlignment(JPasswordField.CENTER);
        passwordField.setBounds(150, 90, 400, 40);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setForeground(Color.LIGHT_GRAY);
        passLabel.setBounds(50, 90, 100, 40);


        JTextField roleField = new RoundedTextField();
        roleField.setText(ROLE);
        roleField.setBackground(new Color(79, 79, 79));
        roleField.setForeground(Color.LIGHT_GRAY);
        roleField.setHorizontalAlignment(JTextField.CENTER);
        roleField.setBounds(150, 150, 400, 40);

        JLabel roleLabel = new JLabel("Role:");
        roleLabel.setForeground(Color.LIGHT_GRAY);
        roleLabel.setBounds(50, 150, 100, 40);

        JButton button = new RoundedButton("Modifier");
        button.setBackground(new Color(243, 144, 57));
        button.setForeground(Color.DARK_GRAY);
        button.setBounds(160, 210, 150, 40);
        button.addActionListener(action -> {
            try {
                String pass = new String(passwordField.getPassword());
                modifier_compte(num, userField.getText(), pass, roleField.getText());
                reussi_Modifier_ui();
                frame.dispose();
            } catch (FileNotFoundException i) {
                i.printStackTrace();
            }
        });

        JButton btnAnnuler = new RoundedButton("Annuler");
        btnAnnuler.setBackground(new Color(240, 74, 82));
        btnAnnuler.setForeground(Color.DARK_GRAY);
        btnAnnuler.setBounds(320, 210, 150, 40);
        btnAnnuler.addActionListener(actionEvent -> frame.dispose());

        frame.add(btnAnnuler);
        frame.add(roleLabel);
        frame.add(passLabel);
        frame.add(userLabel);
        frame.add(button);
        frame.add(userField);
        frame.add(passwordField);
        frame.add(roleField);
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
            string = string.substring(0, string.indexOf(" "));
            int numero = Integer.parseInt(string);
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

    public static String modifie_role(int id, String user, String pass, String role) {
        String string = "";
        String string_of_return = "";
        switch (role) {
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

        try {
            PrintWriter fileWriter = new PrintWriter(Roles);
            InputStream inputStream = new FileInputStream(Temporaire);
            InputStreamReader reader = new InputStreamReader(inputStream);
            Scanner scanner = new Scanner(reader);

            while (scanner.hasNextLine()) {
                String ligne = scanner.nextLine();
                String string1 = ligne.substring(0, ligne.indexOf(" "));
                int ID = Integer.parseInt(string1);
                if (ID != id) fileWriter.println(ligne);
                else {
                    string_of_return = ID + " Username: " + user + "$USER Password: " + pass + "$P";
                    fileWriter.println(string_of_return);
                }
            }
            fileWriter.close();
            scanner.close();
            boolean bool = Temporaire.delete();
        } catch (FileNotFoundException i) {
            i.printStackTrace();
        }
        return string_of_return;
    }

    static void modifier_compte(int num, String user, String pass, String role) throws FileNotFoundException {
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

        while (scanner.hasNextLine()) {
            String ligne = scanner.nextLine();
            int idLigne = Integer.parseInt(ligne.substring(0, ligne.indexOf(" ")));
            String string = ligne.substring(ligne.indexOf(" "));
            string = string.substring(1);
            int id = Integer.parseInt(string.substring(0, string.indexOf(" ")));
            if (idLigne == num && ("Role: " + role).equals(ligne.substring(ligne.indexOf("Role:"), ligne.indexOf("$R")))) {
                fileWriter.println(idLigne + " " + modifie_role(id, user, pass, role) + " Role: " + role + "$R");
            } else {
                fileWriter.println(ligne);
            }
        }
        fileWriter.close();
        scanner.close();
        boolean bool = temporary.delete();
    }

    //interfaces pour les erreurs

    public static void reussi_Modifier_ui() {
        JFrame frame = new JFrame();
        frame.setSize(200, 140);
        frame.getContentPane().setBackground(Color.darkGray);

        JLabel label = new JLabel("Modifications enregistrés");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setForeground(Color.LIGHT_GRAY);
        label.setBounds(0, 20, 200, 30);

        JButton button = new RoundedButton("OK");
        button.setBounds(75, 60, 50, 30);
        button.setBackground(new Color(212, 163, 68));
        button.addActionListener(actionEvent -> frame.dispose());

        frame.add(label);
        frame.add(button);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    public static void error_ui() {
        JFrame frame = new JFrame("Erreur");
        frame.setSize(200, 140);
        frame.getContentPane().setBackground(Color.darkGray);

        JLabel label = new JLabel("ID non reconnu");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setForeground(Color.LIGHT_GRAY);
        label.setBounds(20, 20, 160, 30);

        JButton button = new RoundedButton("OK");
        button.setBounds(75, 60, 50, 30);
        button.setBackground(new Color(212, 163, 68));
        button.addActionListener(actionEvent -> frame.dispose());

        frame.add(label);
        frame.add(button);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}