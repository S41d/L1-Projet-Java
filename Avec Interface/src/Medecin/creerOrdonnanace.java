package Medecin;

import Classes_principales.Consultations;

import javax.swing.*;
import java.awt.*;

public class creerOrdonnanace {
    public void ui(int ID) {
        JFrame frame = new JFrame("Medecin/Ordonnance");
        frame.setSize(600, 400);
        frame.getContentPane().setBackground(new Color(52, 225, 245));

        JLabel label = new JLabel("L'ordonnance:");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBounds(0, 30, 600, 40);

        JTextArea textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setBackground(new Color(46, 188, 207));
        textArea.setBounds(50, 70, 500, 200);

        JButton button = new JButton("Creer");
        button.setBackground(Color.white);
        button.setBounds(50, 290, 500, 30);
        button.addActionListener(actionEvent -> {
            Consultations.write_Ordonnance(textArea.getText(), ID);
        });

        frame.setResizable(false);
        frame.add(button);
        frame.add(label);
        frame.add(textArea);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
