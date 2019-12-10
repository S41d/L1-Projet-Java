package super_admin;

import java.awt.Color;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.util.Scanner;
import RoundFields.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Login");
        frame.setSize(600, 300);

        JTextField textField = new RoundJTextField();
        textField.setBounds(110, 30, 380, 30);

        JPasswordField passwordField = new RoundPasswordField();
        passwordField.setBounds(110, 75, 380, 30);

        JLabel login_Label = new JLabel("Username :");
        login_Label.setForeground(Color.LIGHT_GRAY);
        login_Label.setBounds(35, 30, 380, 30);

        JLabel pass_Label = new JLabel("Password  :");
        pass_Label.setForeground(Color.lightGray);
        pass_Label.setBounds(35, 75, 380, 30);
        
        JLabel label = new JLabel();
        label.setForeground(Color.lightGray);
        label.setBounds(0, 210, 600, 30);
        Timer timer = new Timer(2000, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                label.setText("");
            }            
        });

        JButton login_button = new JButton("Login"); // Logging in button
        login_button.setBounds(110, 130, 120, 30);
        login_button.setBackground(new Color(51, 208, 240));
        login_button.setForeground(Color.darkGray);
        login_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String username = textField.getText();
                String password = new String(passwordField.getPassword());

                File passwords_file = new File("data/file.txt");
                FileReader reader;
                try {
                    reader = new FileReader(passwords_file);
                    Scanner scanner = new Scanner(reader);

                    while (scanner.hasNextLine()) {
                        String string = scanner.nextLine();
                        String usernames = string.substring(string.indexOf("Username : "), string.indexOf(","));
                        String passwords = string.substring(string.indexOf("Password : "));
                        String roles = string.substring(string.indexOf("Role : "), string.indexOf("$R"));

                        if (usernames.contains(username) && passwords.equals("Password : " + password) && roles.equals("Role : " + "Medecin")) {
                            label.setHorizontalAlignment(SwingConstants.CENTER);
                            label.setText("loggin in as medecin");
                            timer.start();
                            break;
                        } else if (usernames.contains(username) && passwords.equals("Password : " + password) && roles.equals("Role : " + "Agent")) {
                            label.setHorizontalAlignment(SwingConstants.CENTER);
                            label.setText("loggin in as agent");
                            timer.start();
                            break;
                        }   else if (usernames.contains(username) && passwords.equals("Password : " + password) && roles.equals("Role : " + "Technicien")) {
                            label.setHorizontalAlignment(SwingConstants.CENTER);
                            label.setText("loggin in as IT");
                            timer.start();
                            break;
                        }   else {
                            label.setHorizontalAlignment(SwingConstants.CENTER);
                            JOptionPane.showMessageDialog(frame, "Mot de passe ou pseudo incorrect", "Error", JOptionPane.ERROR_MESSAGE);
                            break;
                        }

                    }
                    scanner.close();
                } catch (FileNotFoundException e) {
                    System.out.println("file not found");
                }
            }
        }); // Logging in button

        JButton create_an_account_Button = new RoundButton("Creer un nouveau compte"); // Creating a new account
        create_an_account_Button.setBackground(Color.darkGray);
        create_an_account_Button.setForeground(Color.lightGray);
        create_an_account_Button.setBounds(110, 170, 380, 30);
        create_an_account_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                Create_an_account.ui();
            }
        });


        JButton modifier_Button = new JButton("Modifier");
        modifier_Button.setBounds(240, 130, 120, 30);
        modifier_Button.setBackground(new Color(243, 144, 57));
        modifier_Button.setForeground(Color.darkGray);
        modifier_Button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Modifier.ui();
            }
        });

        JButton supprimer_Button = new JButton("supprimer");
        supprimer_Button.setBounds(370, 130, 120, 30);
        supprimer_Button.setBackground(new Color(244, 72, 72));
        supprimer_Button.setForeground(Color.darkGray);
        supprimer_Button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Supprimer.ui();
            }
        });
        
        frame.add(textField);   frame.add(passwordField);   frame.add(login_Label);    frame.add(pass_Label);     frame.add(label);     frame.add(login_button);
        frame.add(create_an_account_Button); frame.add(modifier_Button);    frame.add(supprimer_Button);
        frame.getContentPane().setBackground(Color.DARK_GRAY); frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   frame.setLocationRelativeTo(null);      frame.setLayout(null);  frame.setVisible(true); 
    }
}