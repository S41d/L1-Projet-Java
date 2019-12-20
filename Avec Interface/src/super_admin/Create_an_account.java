package super_admin;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.Timer;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class Create_an_account {
    public static void ui() {
        JFrame frame = new JFrame("Créer un nouveau compte");
        frame.setSize(300, 300);

        JTextField userField = new JTextField();
        userField.setBounds(110, 20, 100, 30);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(110, 75, 100, 30);

        JTextField roleField = new JTextField();
        roleField.setBounds(110, 130, 100, 30);

        JLabel login_Label = new JLabel("Username :");
        login_Label.setBounds(15, 20, 100, 30);

        JLabel pass_Label = new JLabel("Password :");
        pass_Label.setBounds(15, 75, 100, 30);

        JLabel role_label = new JLabel("Role : ");
        role_label.setBounds(15, 130, 100, 30);

        JLabel label = new JLabel();
        label.setBounds(20, 235, 270, 150);

        Timer timer = new Timer(2000, arg0 -> label.setText(""));
        Timer timer1 = new Timer(2000, arg0 -> {
            label.setText("account created");
            frame.dispose();
        });

        JButton create_Button = new JButton("Créer compte");
        create_Button.setBounds(75, 185, 150, 30);

        create_Button.addActionListener(arg0 -> {
            try {
                if (check(userField.getText())) {
                    label.setText("Username already exists");
                    timer.start();
                } else {
                    String password = new String(passwordField.getPassword());
                    new_account(userField.getText(), password, roleField.getText());
                    label.setText("Account created");
                    timer1.start();
                }
            } catch (FileNotFoundException e) {
                System.out.println("file not found exception");
            }
        });

        frame.add(roleField);
        frame.add(role_label);
        frame.add(userField);
        frame.add(passwordField);
        frame.add(login_Label);
        frame.add(pass_Label);
        frame.add(label);
        frame.add(create_Button);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);

    }



    public static boolean check(String username) throws FileNotFoundException {
        boolean account_exists = false;
        FileReader reader = new FileReader("data/Roles.txt");
        Scanner scanner = new Scanner(reader);
        while (scanner.hasNextLine()) {
            String string = scanner.nextLine();
            String usernames = string.substring(string.indexOf("Username: "), string.indexOf("$USER")); // wont work with
                                                                                                     // empty lines !!!!
            if (usernames.equals("Username: " + username)) {
                account_exists = true;
                break;
            } else {
                account_exists = false;
            }
        }
        scanner.close();
        return account_exists;
    }

    public static void new_account(String username, String password, String role) throws FileNotFoundException{
        write_temporary();
        write_file(username, password, role);
    }

    public static String write_medecin(String username, String password){
        File Medecin = new File("data/Medecin.txt");
        File Temporaire = new File("data/Medecin_Temporaire.txt");

        String string_of_return = " ";

        try (FileInputStream fis = new FileInputStream(Medecin);
           FileOutputStream fos = new FileOutputStream(Temporaire)) {
            int len;
            byte[] buffer = new byte[4096];
            while ((len = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }
        } catch (IOException e) {
            System.out.println("Pas copié dans Medecin temporaire");
        }

        try {
            PrintWriter fileWriter = new PrintWriter(Medecin);
            InputStream inputStream = new FileInputStream(Temporaire);
            InputStreamReader reader = new InputStreamReader(inputStream);
            Scanner scanner = new Scanner(reader);

            int counter = 1;

            while (scanner.hasNextLine()) {
                String string = scanner.nextLine();
                fileWriter.println(string);
                counter = Integer.parseInt(string.substring(0,string.indexOf(" ")));
                counter ++;
            }
            string_of_return = counter + " Username: " + username + "$USER Password: " + password + "$P";
            fileWriter.write(string_of_return);
            fileWriter.close();
            scanner.close();


            boolean bool = Temporaire.delete();
        } catch (IOException e) {
            System.out.println("Pas recopié dans Medecin");
        }
        return string_of_return;
    }

    public static String write_agent(String username, String password){
        File Agent = new File("data/Agents.txt");
        File Temporaire = new File("data/Agents_Temporaire.txt");

        String string_of_return = "";

        try (FileInputStream fis = new FileInputStream(Agent);
             FileOutputStream fos = new FileOutputStream(Temporaire)) {
            int len;
            byte[] buffer = new byte[4096];
            while ((len = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }
        } catch (IOException e) {
            System.out.println("Pas copié dans Agents temporaire");
        }

        try {
            PrintWriter fileWriter = new PrintWriter(Agent);
            InputStream inputStream = new FileInputStream(Temporaire);
            InputStreamReader reader = new InputStreamReader(inputStream);
            Scanner scanner = new Scanner(reader);

            int counter = 1;

            while (scanner.hasNextLine()) {
                String string = scanner.nextLine();
                fileWriter.println(string);
                counter = Integer.parseInt(string.substring(0,string.indexOf(" ")));
                counter ++;
            }
            string_of_return = counter + " Username: " + username + "$USER Password: " + password + "$P";
            fileWriter.write(string_of_return);
            fileWriter.close();
            scanner.close();


            boolean bool = Temporaire.delete();
        } catch (IOException e) {
            System.out.println("Pas recopié dans Agents");
        }
        return string_of_return;
    }

    public static String write_technicien(String username, String password){
        File Technicien = new File("data/Techniciens.txt");
        File Temporaire = new File("data/Techniciens_Temporaire.txt");

        String string_of_return = "";

        try (FileInputStream fis = new FileInputStream(Technicien);
             FileOutputStream fos = new FileOutputStream(Temporaire)) {
            int len;
            byte[] buffer = new byte[4096];
            while ((len = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }
        } catch (IOException e) {
            System.out.println("Pas copié dans Techniciens temporaire");
        }

        try {
            PrintWriter fileWriter = new PrintWriter(Technicien);
            InputStream inputStream = new FileInputStream(Temporaire);
            InputStreamReader reader = new InputStreamReader(inputStream);
            Scanner scanner = new Scanner(reader);

            int counter = 1;

            while (scanner.hasNextLine()) {
                String string = scanner.nextLine();
                fileWriter.println(string);
                counter = Integer.parseInt(string.substring(0,string.indexOf(" ")));
                counter ++;
            }
            string_of_return = counter + " Username: " + username + "$USER Password: " + password + "$P";
            fileWriter.write(string_of_return);
            fileWriter.close();
            scanner.close();


            boolean bool = Temporaire.delete();
        } catch (IOException e) {
            System.out.println("Pas recopié dans Techiniciens");
        }
        return string_of_return;
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

    static void write_file(String username, String password, String role) throws FileNotFoundException {
        File temporary = new File("data/temporary.txt");
        File file = new File("data/Roles.txt");

        boolean role_correct = false;

        PrintWriter fileWriter = new PrintWriter(file);
        InputStream inputStream = new FileInputStream(temporary);
        InputStreamReader reader = new InputStreamReader(inputStream);
        Scanner scanner = new Scanner(reader);

        String roles = "";
        switch (role) {
            case "Medecin":
                roles = write_medecin(username, password);
                role_correct = true;
                break;
            case "Technicien":
                role_correct = true;
                roles = write_technicien(username, password);
                break;
            case "Agent":
                role_correct = true;
                roles = write_agent(username, password);
                break;
            default:
                role_correct = false;
                JOptionPane.showMessageDialog(null, "Role incorrect, choississez entre Technicien, Agent, Medecin", "Error", JOptionPane.ERROR_MESSAGE);
                break;
        }

        if (role_correct){
            int counter = 1;

            while (scanner.hasNextLine()) {
                String string = scanner.nextLine();
                fileWriter.println(string);
                counter = Integer.parseInt(string.substring(0,string.indexOf(" ")));
                counter ++;
            }
            String string1 = roles + " Role : " + role + "$R";
            fileWriter.write(counter + " " + string1);

            fileWriter.close();
            scanner.close();
        }
        else {
            try (FileInputStream fis = new FileInputStream(temporary);
               FileOutputStream fos = new FileOutputStream(file)) {
                int len;
                byte[] buffer = new byte[4096];
                while ((len = fis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        boolean bool = temporary.delete();
    }
}