package super_admin;

import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;
import java.io.*;
import java.awt.Color;
import RoundFields.*;

public class Supprimer {
    public static void ui() {
        JFrame frame = new JFrame("Supprimer");
        frame.setSize(350, 100);
        
        JTextField numField = new JTextField();
        numField.setBounds(110, 20, 100, 30);
        
        JLabel num_label = new JLabel("Numero ID :");
        num_label.setBounds(15, 20, 100, 30);
        num_label.setForeground(Color.lightGray);
        
        JButton supprime_Button = new JButton("Supprimer");
        supprime_Button.setBounds(230, 20, 100, 30);
        supprime_Button.setBackground(new Color(244, 72, 72));
        supprime_Button.setForeground(Color.darkGray);
        supprime_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    if (check(Integer.parseInt(numField.getText())))  {
                        int ans = JOptionPane.showConfirmDialog(frame, "Vous etes sur de supprimer ce compte?", "", JOptionPane.YES_NO_OPTION);
                        if (ans == 1) {
                        } else {
                            supprime_compte(Integer.parseInt(numField.getText()));
                            frame.dispose();
                        }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Ce compte n'existe pas", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (FileNotFoundException e) {
                    System.out.println("file not found exception");
                }
            }
        });

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
        FileReader reader = new FileReader("data/file.txt");
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
            
    public static void supprime_compte(int num) throws FileNotFoundException {
        write_temporary();
        write_file(num);
    }

    static void write_temporary() throws FileNotFoundException {
        File temporary = new File("data/temporary.txt");
        File file = new File("data/file.txt");

        PrintWriter tempWriter = new PrintWriter(temporary);
        InputStream inputStream = new FileInputStream(file);
        InputStreamReader reader = new InputStreamReader(inputStream);
        Scanner scanner = new Scanner(reader);

        String string = new String();
        while (scanner.hasNextLine()) {
            string = scanner.nextLine();
            tempWriter.println(string);
        }

        tempWriter.close();
        scanner.close();
    }

    static void write_file(int num) throws FileNotFoundException {
        File temporary = new File("data/temporary.txt");
        File file = new File("data/file.txt");
        
        PrintWriter fileWriter = new PrintWriter(file);
        InputStream inputStream = new FileInputStream(temporary);
        InputStreamReader reader = new InputStreamReader(inputStream);
        Scanner scanner = new Scanner(reader);
        
        String string = new String();
        
        while (scanner.hasNextLine()) {
            string = scanner.nextLine();
            if (Integer.parseInt(string.substring(0, string.indexOf(" ")))== num) {
            } else {
            fileWriter.println(string);
            }
        }
        fileWriter.close();
        scanner.close();
        temporary.delete();
    }
}