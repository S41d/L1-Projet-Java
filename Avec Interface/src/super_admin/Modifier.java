package super_admin;

import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.util.Scanner;

public class Modifier {
    public static void ui() {
	   JFrame frame = new JFrame("Modifier");
	   frame.setSize(300, 400);

	   JPanel panel = new JPanel();
	   panel.setSize(300, 400);

	   JTextField numField = new JTextField();
	   numField.setBounds(110, 20, 100, 30);

	   JLabel numLabel = new JLabel("Numero ID :");
	   numLabel.setBounds(15, 20, 100, 30);

	   JTextField userField = new JTextField();
	   userField.setBounds(110, 75, 100, 30);

	   JPasswordField passwordField = new JPasswordField();
	   passwordField.setBounds(110, 130, 100, 30);

	   JTextField roleField = new JTextField();
	   roleField.setBounds(110, 185, 100, 30);

	   JLabel login_Label = new JLabel("Username :");
	   login_Label.setBounds(15, 75, 100, 30);

	   JLabel pass_Label = new JLabel("Password :");
	   pass_Label.setBounds(15, 130, 100, 30);

	   JLabel role_label = new JLabel("Role :");
	   role_label.setBounds(15, 185, 100, 30);

	   JLabel label = new JLabel();
	   label.setBounds(20, 290, 270, 150);

	   Timer timer = new Timer(2000, new ActionListener() {
		  @Override
		  public void actionPerformed(ActionEvent arg0) {
			 label.setText("");
		  }
	   });
	   Timer timer1 = new Timer(2000, new ActionListener() {
		  @Override
		  public void actionPerformed(ActionEvent arg0) {
			 label.setText("Compte modifié");
			 frame.dispose();
		  }
	   });

	   JButton button = new JButton("Modifier");
	   button.setBounds(70, 235, 150, 30);
	   button.addActionListener(action -> {
		  try {
			 String pass = new String(passwordField.getPassword());
			 if (check(Integer.parseInt(numField.getText()))) {
				modifier_compte(Integer.parseInt(numField.getText()), userField.getText(), pass, roleField.getText());
				timer1.start();
			 } else {
				label.setText("Ce compte n'existe pas");
				timer.start();
			 }
		  } catch (FileNotFoundException i) {
			 System.out.println("Exception i");
		  }

	   });

	   panel.add(button);
	   panel.add(numField);
	   panel.add(numLabel);
	   panel.add(userField);
	   panel.add(passwordField);
	   panel.add(roleField);
	   panel.add(login_Label);
	   panel.add(pass_Label);
	   panel.add(role_label);
	   panel.add(label);
	   panel.setLayout(null);

	   frame.add(panel);
	   frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	   frame.setLocationRelativeTo(null);
	   frame.setLayout(null);
	   frame.setVisible(true);
    }

    public static boolean check(int num) throws FileNotFoundException {
	   boolean account_exists = false;
	   FileReader reader = new FileReader("data/Roles.txt");
	   Scanner scanner = new Scanner(reader);
	   while (scanner.hasNextLine()) {
		  String string = scanner.nextLine();
		  string = string.substring(string.indexOf(" "));
		  string = string.substring(1);
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

    public static void modifier_compte(int num, String user, String pass, String role) throws FileNotFoundException {
	   write_temporary();
	   write_file(num, user, pass, role);
    }

    public static String modifie_role(int id, String user, String pass, String role) {
	   String string = "";
	   String string_of_return = "";
	   switch (role) {
		  case "Medecin":
			 string = "data/Medecin.txt";
			 break;
		  case "Agent":
			 string = "data/Agents.txt";
			 break;
		  case "Techniciens":
			 string = "data/Techniciens.txt";
			 break;
	   }
	   File Roles = new File(string);
	   File Temporaire = new File("data/Roles_Temporaire.txt");

	   try (FileInputStream fis = new FileInputStream(Roles);
		   FileOutputStream fos = new FileOutputStream(Temporaire)) {
		  int len;
		  byte[] buffer = new byte[4096];
		  while ((len = fis.read(buffer)) > 0) {
			 fos.write(buffer, 0, len);
		  }
	   } catch (IOException e) {
		  e.printStackTrace();
	   }

	   try {
		  PrintWriter fileWriter = new PrintWriter(Roles);
		  InputStream inputStream = new FileInputStream(Temporaire);
		  InputStreamReader reader = new InputStreamReader(inputStream);
		  Scanner scanner = new Scanner(reader);

		  while (scanner.hasNextLine()) {
			 String ligne = scanner.nextLine();
			 String string1 = ligne.substring(0, ligne.indexOf(" "));
			 int ID = Integer.parseInt(string1);
			 if (ID != id) fileWriter.println(ligne);
			 else {
			     string_of_return = ID + " Username: " + user + "$USER Password: " + pass + "$P";
			     fileWriter.println(string_of_return);
                }
		  }
		  fileWriter.close();
		  scanner.close();
		  boolean bool = Temporaire.delete();
	   } catch (FileNotFoundException i) {
		  i.printStackTrace();
	   }
	   return string_of_return;
    }

    static void write_temporary() throws FileNotFoundException {
	   File temporary = new File("data/temporary.txt");
	   File file = new File("data/Roles.txt");

	   PrintWriter tempWriter = new PrintWriter(temporary);
	   InputStream inputStream = new FileInputStream(file);
	   InputStreamReader reader = new InputStreamReader(inputStream);
	   Scanner scanner = new Scanner(reader);

	   while (scanner.hasNextLine()) {
		  String string = scanner.nextLine();
		  tempWriter.println(string);
	   }
	   tempWriter.close();
	   scanner.close();
    }

    static void write_file(int num, String user, String pass, String role) throws FileNotFoundException {
	   File temporary = new File("data/temporary.txt");
	   File file = new File("data/Roles.txt");

	   PrintWriter fileWriter = new PrintWriter(file);
	   InputStream inputStream = new FileInputStream(temporary);
	   InputStreamReader reader = new InputStreamReader(inputStream);
	   Scanner scanner = new Scanner(reader);

	   while (scanner.hasNextLine()) {
		  String ligne = scanner.nextLine();
		  int idLigne = Integer.parseInt(ligne.substring(0, ligne.indexOf(" ")));
		  String string = ligne.substring(ligne.indexOf(" "));
		  string = string.substring(1);
		  int id = Integer.parseInt(string.substring(0, string.indexOf(" ")));
		  if (id == num && ("Role: " + role).equals(string.substring(string.indexOf("Role:"), string.indexOf("$R")))) {
                fileWriter.println(idLigne + " " + modifie_role(num, user, pass, role) + " Role: " + role + "$R");
		  } else {
			 fileWriter.println(ligne);
		  }
	   }
	   fileWriter.close();
	   scanner.close();
	   boolean bool = temporary.delete();
    }

}