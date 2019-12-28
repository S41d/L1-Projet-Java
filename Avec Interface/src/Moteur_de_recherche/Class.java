package Moteur_de_recherche;

import RoundedBorders.RoundedTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.util.Scanner;

public class Class {
    public static void ui() throws Exception {
	   JFrame frame = new JFrame();
	   frame.getContentPane().setBackground(Color.darkGray);
	   frame.setSize(700, 500);

	   JTextArea textArea = new JTextArea();
	   textArea.setBackground(Color.lightGray);
	   textArea.setBounds(100, 100, 500, 300);

	   JTextField textField = new RoundedTextField();
	   textField.setText("Entrez l'ID, le nom ou le prenom du patient");
	   textField.setBackground(new Color(79, 79, 79));
	   textField.setForeground(Color.LIGHT_GRAY);
	   textField.setHorizontalAlignment(JTextField.CENTER);
	   textField.setBounds(100, 30, 500, 40);
	   textField.addFocusListener(new FocusListener() {
		  @Override
		  public void focusGained(FocusEvent focusEvent) {
			 if (textField.getText().equals("Entrez l'ID, le nom ou le prenom du patient"))
				textField.setText("");
		  }

		  @Override
		  public void focusLost(FocusEvent focusEvent) {
			 if (textField.getText().isEmpty())
				textField.setText("Entrez l'ID, le nom ou le prenom du patient");
		  }
	   });
	   textField.addKeyListener(new KeyListener() {
		  @Override
		  public void keyTyped(KeyEvent keyEvent) {
		  }
		  @Override
		  public void keyPressed(KeyEvent keyEvent) {
		  }
		  @Override
		  public void keyReleased(KeyEvent keyEvent) {
			 try{
				File Patients = new File("data/Patient.txt");
				FileInputStream inputStream = new FileInputStream(Patients);
				InputStreamReader reader = new InputStreamReader(inputStream);
				Scanner scanner = new Scanner(reader);
				StringBuilder tout = new StringBuilder();

				while (scanner.hasNextLine()) {
				    String ligne = scanner.nextLine();
				    String nom = ligne.substring(ligne.indexOf("Nom:"), ligne.indexOf("$N"));
				    String prenom = ligne.substring(ligne.indexOf("Prenom"), ligne.indexOf("$Pn"));
				    String ID = ligne.substring(0, ligne.indexOf(" "));
				    if (nom.contains(textField.getText()) || prenom.contains(textField.getText()) || ID.equals(textField.getText())){
					   ligne = ligne.replace("$A", "");
					   ligne = ligne.replace("$Pn", "");
					   ligne = ligne.replace("$N", "");
					   ligne = ligne.replace("$Date", "");
					   tout.append(ligne).append("\n");
				    }
				}
				textArea.setText(tout.toString());
			 } catch (FileNotFoundException e){
				e.printStackTrace();
			 }
		  }
	   });

	   frame.add(textField);
	   frame.add(textArea);
	   frame.setLayout(null);
	   frame.setLocationRelativeTo(null);
	   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   frame.setVisible(true);
    }

    public static void main(String[] args) throws Exception {
	   ui();
    }
}
