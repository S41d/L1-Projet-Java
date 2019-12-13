package super_admin;

import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;
import java.io.*;

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
            String usernames = string.substring(string.indexOf("Username : "), string.indexOf(",")); // wont work with
                                                                                                     // empty lines !!!!
            if (usernames.equals("Username : " + username)) {
                account_exists = true;
                break;
            } else {
                account_exists = false;
            }
        }
        scanner.close();
        return account_exists;
    }



    public static void new_account(String username, String password, String role) throws FileNotFoundException {
        write_temporary();
        write_file(username, password, role);
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
        
        PrintWriter fileWriter = new PrintWriter(file);
        InputStream inputStream = new FileInputStream(temporary);
        InputStreamReader reader = new InputStreamReader(inputStream);
        Scanner scanner = new Scanner(reader);
        
        String string1 = "Username : " + username + ", Role : " + role + "$R Password : " + password;
        
        int counter = 1;
        
        while (scanner.hasNextLine()) {
            String string = scanner.nextLine();
            fileWriter.println(string);
            counter = Integer.parseInt(string.substring(0,string.indexOf(" ")));
            counter ++;

        }
        fileWriter.write(counter + " " + string1);

        fileWriter.close();
        scanner.close();
        temporary.delete();
    }
}