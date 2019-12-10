package super_admin;

import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;
import java.io.*;

public class Supprimer {
    public static void ui() {
        JFrame frame = new JFrame("Supprimer");
        frame.setSize(300, 300);
        
        JTextField numField = new JTextField();
        numField.setBounds(110, 20, 100, 30);
        
        JLabel num_label = new JLabel("Numero ID :");
        num_label.setBounds(15, 20, 100, 30);
        
        JLabel label = new JLabel();
        label.setBounds(20, 50, 270, 30);
        
        Timer timer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                label.setText("");
            }
        });
        Timer timer1 = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                label.setText("Compte supprimé");
                frame.dispose();
            }
        });
        
        JButton create_Button = new JButton("Supprimer");
        create_Button.setBounds(75, 75, 150, 30);
        
        create_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    if (check(Integer.parseInt(numField.getText())))  {
                        label.setText("marche");
                        supprime_compte(Integer.parseInt(numField.getText()));
                        timer1.start();
                    } else {
                        label.setText("Ce compte n'existe pas");
                        timer.start();
                    }
                } catch (FileNotFoundException e) {
                    System.out.println("file not found exception");
                }
            }
        });

        frame.add(num_label);
        frame.add(numField);
        frame.add(label);
        frame.add(create_Button);
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