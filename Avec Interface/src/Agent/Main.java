package Agent;

import Classes_principales.Patient;
import RoundedBorders.RoundedPasswordField;
import RoundedBorders.RoundedTextField;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
    public static void ui() {
	   JFrame frame = new JFrame("Agent");
	   frame.setSize(600, 300);
	   frame.getContentPane().setBackground(Color.darkGray);

	   JTextField idField = new RoundedTextField();
	   idField.setBounds(110, 30, 380, 30);
	   idField.setForeground(Color.DARK_GRAY);

	   JLabel idLabel = new JLabel("ID : ");
	   idLabel.setBounds(50, 30, 30, 30);
	   idLabel.setForeground(Color.LIGHT_GRAY);

	   JLabel resultatLabel = new JLabel();
	   resultatLabel.setBounds(110, 180, 380, 30);
	   resultatLabel.setForeground(Color.LIGHT_GRAY);

	   JButton btnVerifier = new JButton("Verifier");
	   btnVerifier.setBounds(110, 80, 120, 30);
	   btnVerifier.setBackground(new Color(51, 208, 240));
	   btnVerifier.addActionListener(actionEvent -> {
				int ID = Integer.parseInt(idField.getText());
				try {
				    if (Patient.check(ID)) {
					   resultatLabel.setText("le patient exist");
				    } else {
					   resultatLabel.setText("le patient n'existe pas");
				    }
				} catch (IOException e) {
				    e.printStackTrace();
				}
			 }
	   );

	   JButton btnModifier = new JButton("Modifier");
	   btnModifier.setBounds(240, 80, 120, 30);
	   btnModifier.setBackground(new Color(243, 144, 57));
	   btnModifier.addActionListener(actionEvent -> Modifier.ui());

	   JButton btnSupprimer = new JButton("Supprimer");
	   btnSupprimer.setBounds(370, 80, 120, 30);
	   btnSupprimer.setBackground(new Color(244, 72, 72));

	   JButton btnCreer = new JButton("Creer un nouveau compte patient");
	   btnCreer.setBounds(110, 120, 380, 30);
	   btnCreer.setBackground(Color.darkGray);
	   btnCreer.setForeground(Color.LIGHT_GRAY);

	   frame.add(btnVerifier); frame.add(btnModifier); frame.add(btnSupprimer); frame.add(btnCreer);
	   frame.add(resultatLabel);
	   frame.add(idField); frame.add(idLabel); frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	   frame.setLayout(null); frame.setLocationRelativeTo(null); frame.setVisible(true);
    }

    public static void main(String[] args) {
	   ui();
    }
}
