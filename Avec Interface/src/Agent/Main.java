package Agent;

import Classes_principales.Patient;
import RoundedBorders.RoundedButton;
import RoundedBorders.RoundedTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void ui() throws FileNotFoundException {
	   JFrame frame = new JFrame("Agent");
	   frame.setSize(600, 300);
	   frame.getContentPane().setBackground(Color.darkGray);

	   JTextField dummy = new JTextField();
	   dummy.requestFocusInWindow();

	   JTextField idField = new RoundedTextField();
	   idField.setText("ID du patient");
	   idField.setBackground(new Color(79, 79, 79));
	   idField.setForeground(Color.LIGHT_GRAY);
	   idField.setHorizontalAlignment(JTextField.CENTER);
	   idField.setBounds(50, 30, 500, 40);
	   idField.addFocusListener(new FocusListener() {
		  @Override
		  public void focusGained(FocusEvent focusEvent) {
			 if (idField.getText().toUpperCase().equals("ID DU PATIENT"))
				idField.setText("");
		  }

		  @Override
		  public void focusLost(FocusEvent focusEvent) {
			 if (idField.getText().isEmpty())
				idField.setText("ID du patient");
		  }
	   });

	   JLabel resultatLabel = new JLabel();
	   resultatLabel.setBounds(110, 180, 380, 30);
	   resultatLabel.setForeground(Color.LIGHT_GRAY);

	   JButton btnVerifier = new RoundedButton("Verifier");
	   btnVerifier.setBounds(110, 80, 120, 30);
	   btnVerifier.setBackground(new Color(51, 208, 240));
	   btnVerifier.addActionListener(actionEvent -> {
		  String idFieldString = idField.getText();
		  assert idFieldString != null;
		  int ID = Integer.parseInt(idFieldString);
		  try {
			 if (Patient.check(ID)) {
				resultatLabel.setText("le patient exist");
				frame.dispose();
				Medecin.Main.ui();
			 } else {
				resultatLabel.setText("le patient n'existe pas");
			 }
		  } catch (IOException e) {
			 e.printStackTrace();
		  }
	   });

	   JButton btnModifier = new RoundedButton("Modifier");
	   btnModifier.setBounds(240, 80, 120, 30);
	   btnModifier.setBackground(new Color(243, 144, 57));
	   btnModifier.addActionListener(actionEvent -> Modifier.ui());

	   JButton btnSupprimer = new RoundedButton("Supprimer");
	   btnSupprimer.setBounds(370, 80, 120, 30);
	   btnSupprimer.setBackground(new Color(244, 72, 72));
	   btnSupprimer.addActionListener(actionEvent -> {
		  Supprimer.ui();
	   });

	   JButton btnCreer = new RoundedButton("Ajouter un nouveau patient");
	   btnCreer.setBounds(110, 120, 380, 30);
	   btnCreer.setBackground(Color.darkGray);
	   btnCreer.setForeground(Color.LIGHT_GRAY);
	   btnCreer.addActionListener(actionEvent -> {
		  try {
			 Creer_Patient.ui();
		  } catch (ParseException e) {
			 e.printStackTrace();
		  }
	   });

	   frame.add(dummy);
	   frame.add(btnVerifier);
	   frame.add(btnModifier);
	   frame.add(btnSupprimer);
	   frame.add(btnCreer);
	   frame.add(resultatLabel);
	   frame.add(idField);
	   frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	   frame.setLayout(null);
	   frame.setLocationRelativeTo(null);
	   frame.setVisible(true);
    }

    public static void main(String[] args) throws FileNotFoundException {
	   ui();
    }
}
