package super_admin;

import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.util.Scanner;

public class login {
    public static void login_screen () {
        JFrame frame = new JFrame("Login");
        frame.setSize(300, 400);

        JTextField textField = new JTextField();
        textField.setBounds(110, 20, 100, 30);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(110, 75, 100, 30);

        JLabel login_Label = new JLabel("Username :");
        login_Label.setBounds(15, 20, 100, 30);

        JLabel pass_Label = new JLabel("Password :");
        pass_Label.setBounds(15, 75, 100, 30);
        
        JLabel label = new JLabel();
        label.setBounds(20, 290, 270, 30);
        Timer timer = new Timer(2000, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                label.setText("");
            }            
        });

        JButton login_button = new JButton("Login"); // Logging in button
        login_button.setBounds(100, 120, 80, 30);
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
                            label.setText("loggin in as medecin");
                            timer.start();
                            break;
                        } else if (usernames.contains(username) && passwords.equals("Password : " + password) && roles.equals("Role : " + "Agent")) {
                            label.setText("loggin in as agent");
                            timer.start();
                            break;
                        }   else if (usernames.contains(username) && passwords.equals("Password : " + password) && roles.equals("Role : " + "Technicien")) {
                            label.setText("loggin in as IT");
                            timer.start();
                            break;
                        }   else {
                            label.setText("wrong username or password");
                            timer.start();
                        }

                    }
                    scanner.close();
                } catch (FileNotFoundException e) {
                    System.out.println("file not found");
                }
            }
        }); // Logging in button

        JButton create_an_account_Button = new JButton("Create a new account"); // Creating a new account
        create_an_account_Button.setBounds(50, 160, 200, 30);
        create_an_account_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                Create_an_account.ui();
            }
        });


        JButton modifier_Button = new JButton("Modifier");
        modifier_Button.setBounds(90, 200, 100, 30);
        modifier_Button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Modifier.ui();
            }
        });

        JButton supprimer_Button = new JButton("Supprimer");
        supprimer_Button.setBounds(90, 240, 100, 30);
        supprimer_Button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Supprimer.ui();
            }
        });
        
        frame.add(textField);   frame.add(passwordField);   frame.add(login_Label);    frame.add(pass_Label);     frame.add(label);     frame.add(login_button);
        frame.add(create_an_account_Button); frame.add(modifier_Button);    frame.add(supprimer_Button);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   frame.setLocationRelativeTo(null);      frame.setLayout(null);  frame.setVisible(true); 
        
    }
}