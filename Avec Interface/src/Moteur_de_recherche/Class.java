package Moteur_de_recherche;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Class {
    public static void ui() throws Exception {
	   String[] list = list_patient_recherche();
	   JFrame frame = new JFrame();
	   frame.setSize(700, 500);

	   JLabel label = new JLabel("label");
	   label.setBounds(15, 20, 100, 30);

	   JTextArea textArea = new JTextArea();
	   textArea.setBounds(110, 100, 500, 300);

	   JTextField textField = new JTextField();
	   textField.setBounds(110, 20, 400, 30);

	   JButton button = new JButton("Cherche");
	   button.setBounds(550, 20, 100, 30);
	   button.addActionListener(arg0 -> {
		  StringBuilder tout  = new StringBuilder();
		  for (String s : list) {
		      if (s.contains(textArea.getText())) {
				tout.append(s);
			 }
		  }
		  textArea.setText(tout.toString());
	   });

	   frame.add(button);
	   frame.add(label);
	   frame.add(textField);
	   frame.add(textArea);
	   frame.setLayout(null);
	   frame.setLocationRelativeTo(null);
	   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   frame.setVisible(true);
    }

    public static String[] list_patient_recherche() throws FileNotFoundException {
	   File Patients = new File("data/Patient.txt");
	   FileInputStream inputStream = new FileInputStream(Patients);
	   InputStreamReader reader = new InputStreamReader(inputStream);
	   Scanner scanner = new Scanner(reader);

	   ArrayList<String> list = new ArrayList<>();
	   while (scanner.hasNextLine()) {
		  String ligne = scanner.nextLine();
		  ligne = ligne.replace("$N", "");
		  ligne = ligne.replace("$Pn", "");
		  ligne = ligne.replace("$A", "");
		  ligne = ligne.replace("$Date", "");
		  list.add(ligne);
	   }
	   Object[] list_dans_array = list.toArray();
	   String[] resultat = new String[list.size()];
	   for (int i = 0; i < list_dans_array.length; i++) {
		  resultat[i] = list_dans_array[i].toString();
	   }
	   return resultat;
    }

    public static void main(String[] args) throws Exception {
	   ui();
    }
}
