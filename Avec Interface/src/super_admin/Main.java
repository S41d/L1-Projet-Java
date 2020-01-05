package super_admin;

import Dialogue.dialogue;
import RoundedBorders.RoundedButton;
import RoundedBorders.RoundedPasswordField;
import RoundedBorders.RoundedTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Login");
        frame.setSize(600, 320);
        frame.getContentPane().setBackground(Color.darkGray);

        JTextField dummy = new JTextField();
        dummy.requestFocusInWindow();  // pour que ca focus pas directement sur les autres textefields

        JTextField textField = new RoundedTextField();
        textField.setText("Username");
        textField.setBackground(new Color(79, 79, 79));
        textField.setForeground(Color.lightGray);
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setBounds(50, 30, 500, 40);
        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent focusEvent) {
                if (textField.getText().toUpperCase().equals("USERNAME"))
                    textField.setText("");
            }

            @Override
            public void focusLost(FocusEvent focusEvent) {
                if (textField.getText().isEmpty())
                    textField.setText("Username");
            }
        });

        JPasswordField passwordField = new RoundedPasswordField();
        passwordField.setText("Password");
        passwordField.setBackground(new Color(79, 79, 79));
        passwordField.setForeground(Color.lightGray);
        char Char = passwordField.getEchoChar();
        passwordField.setEchoChar((char) 0);
        passwordField.setHorizontalAlignment(JPasswordField.CENTER);
        passwordField.setBounds(50, 90, 500, 40);
        passwordField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent focusEvent) {
                String password = String.valueOf(passwordField.getPassword());
                if (password.toUpperCase().equals("PASSWORD")) {
                    passwordField.setText("");
                    passwordField.setEchoChar(Char);
                }
            }

            @Override
            public void focusLost(FocusEvent focusEvent) {
                String password = String.valueOf(passwordField.getPassword());
                if (password.isEmpty()) {
                    passwordField.setEchoChar((char) 0);
                    passwordField.setText("Password");
                }
            }
        });

        JButton login_button = new RoundedButton("Login"); // Logging in button
        login_button.setBounds(110, 150, 120, 40);
        login_button.setBackground(new Color(51, 208, 240));
        login_button.setForeground(Color.darkGray);
        login_button.addActionListener(arg0 -> {
            String username = textField.getText();
            String password = new String(passwordField.getPassword());

            File passwords_file = new File("data/Roles.txt");
            FileReader reader;
            try {
                reader = new FileReader(passwords_file);
                Scanner scanner = new Scanner(reader);

                while (scanner.hasNextLine()) {
                    String string = scanner.nextLine();
                    String IDROLE = string.substring(string.indexOf(" "));
                    IDROLE = IDROLE.substring(1);
                    int IDRole = Integer.parseInt(IDROLE.substring(0, IDROLE.indexOf(" ")));

                    String usernames = string.substring(string.indexOf("Username: "), string.indexOf("$USER"));
                    String passwords = string.substring(string.indexOf("Password: "), string.indexOf("$P"));
                    String roles = string.substring(string.indexOf("Role: "), string.indexOf("$R"));

                    if (usernames.contains(username) && passwords.equals("Password: " + password) && roles.equals("Role: " + "Medecin")) {
                        frame.dispose();
                        new Medecin.Main(IDRole).ui();
                        break;
                    } else if (usernames.contains(username) && passwords.equals("Password: " + password) && roles.equals("Role: " + "Agent")) {
                        frame.dispose();
                        Agent.Main agent = new Agent.Main();
                        agent.ui();
                        break;
                    } else if (usernames.contains(username) && passwords.equals("Password: " + password) && roles.equals("Role: " + "Technicien")) {
                        break;
                    }
                }
                scanner.close();
            } catch (FileNotFoundException e) {
                new dialogue("File not Founde", Color.LIGHT_GRAY, Color.lightGray);
            } catch (StringIndexOutOfBoundsException i) {
                i.printStackTrace();
                new dialogue("Mot de pass ou Username incorrect", Color.LIGHT_GRAY, Color.darkGray);
            }
        }); // Logging in button

        JButton create_an_account_Button = new RoundedButton("Creer un nouveau compte"); // Creating a new account
        create_an_account_Button.setBackground(new Color(79, 79, 79));
        create_an_account_Button.setForeground(Color.lightGray);
        create_an_account_Button.setBounds(110, 200, 380, 40);
        create_an_account_Button.addActionListener(arg0 -> Creer_Compte.ui());

        JButton modifier_Button = new RoundedButton("Modifier");
        modifier_Button.setBounds(240, 150, 120, 40);
        modifier_Button.setBackground(new Color(243, 144, 57));
        modifier_Button.setForeground(Color.darkGray);
        modifier_Button.addActionListener(arg0 -> Modifier.takeNum());

        JButton supprimer_Button = new RoundedButton("Supprimer");
        supprimer_Button.setBounds(370, 150, 120, 40);
        supprimer_Button.setBackground(new Color(244, 72, 72));
        supprimer_Button.setForeground(Color.darkGray);
        supprimer_Button.addActionListener(e -> Supprimer.ui());

        frame.add(textField);
        frame.add(passwordField);
        frame.add(dummy);
        frame.add(create_an_account_Button);
        frame.add(modifier_Button);
        frame.add(supprimer_Button);
        frame.add(login_button);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}