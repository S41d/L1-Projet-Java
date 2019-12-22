package super_admin;

import RoundedBorders.RoundedButton;
import RoundedBorders.RoundedFormattedTextField;
import RoundedBorders.RoundedPasswordField;
import RoundedBorders.RoundedTextField;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.*;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Scanner;

public class Create_an_account {
    public static void ui() {
	   JFrame frame = new JFrame("Créer un nouveau compte");
	   frame.setSize(600, 500);
	   frame.getContentPane().setBackground(Color.darkGray);

	   JTextField userField = new RoundedTextField();
	   userField.setBackground(new Color(79, 79, 79));
	   userField.setForeground(Color.LIGHT_GRAY);
	   userField.setBounds(150, 30, 400, 40);

	   JLabel login_Label = new JLabel("Username :");
	   login_Label.setForeground(Color.LIGHT_GRAY);
	   login_Label.setBounds(50, 30, 100, 40);

	   JPasswordField passwordField = new RoundedPasswordField();
	   passwordField.setBackground(new Color(79, 79, 79));
	   passwordField.setForeground(Color.LIGHT_GRAY);
	   passwordField.setBounds(150, 90, 400, 40);

	   JLabel pass_Label = new JLabel("Password :");
	   pass_Label.setForeground(Color.LIGHT_GRAY);
	   pass_Label.setBounds(50, 90, 100, 40);

	   JTextField passField = new RoundedPasswordField();
	   passField.setBackground(new Color(79, 79, 79));
	   passField.setForeground(Color.LIGHT_GRAY);
	   passField.setBounds(150, 150, 400, 40);

	   JLabel pass2_label = new JLabel("<html>Confirm <br>password :</html>");
	   pass2_label.setForeground(Color.LIGHT_GRAY);
	   pass2_label.setBounds(50, 150, 100, 40);

	   JTextField roleField = new RoundedTextField();
	   roleField.setText("Medecin, Agent ou Technicien");
	   roleField.setBackground(new Color(79, 79, 79));
	   roleField.setForeground(Color.LIGHT_GRAY);
	   roleField.setBounds(150, 210, 400, 40);
	   roleField.addFocusListener(new FocusListener() {
		  @Override
		  public void focusGained(FocusEvent focusEvent) {
			 if (roleField.getText().equals("Medecin, Agent ou Technicien")) {
				roleField.setText("");
			 }
		  }

		  @Override
		  public void focusLost(FocusEvent focusEvent) {
			 if (roleField.getText().isEmpty()) {
				roleField.setText("Medecin, Agent ou Technicien");
			 }
		  }
	   });

	   JLabel role_label = new JLabel("Role :");
	   role_label.setForeground(Color.LIGHT_GRAY);
	   role_label.setBounds(50, 210, 100, 40);

	   MaskFormatter maskFormatter = null;
	   try {
		  maskFormatter = new MaskFormatter("##-##-####");
		  maskFormatter.setPlaceholderCharacter(' ');
	   } catch (ParseException e) {
		  e.printStackTrace();
	   }
	   JFormattedTextField dateFeild = new RoundedFormattedTextField();
	   dateFeild.setText("jj-mm-aaaa");
	   dateFeild.setBackground(new Color(79, 79, 79));
	   dateFeild.setForeground(Color.LIGHT_GRAY);
	   dateFeild.setBounds(150, 270, 400, 40);
	   MaskFormatter finalMaskFormatter = maskFormatter;
	   dateFeild.addFocusListener(new FocusListener() {
		  @Override
		  public void focusGained(FocusEvent focusEvent) {
			 if (dateFeild.getText().equals("jj-mm-aaaa")) {
				dateFeild.setText("");
				assert finalMaskFormatter != null;
				finalMaskFormatter.install(dateFeild);
				finalMaskFormatter.setAllowsInvalid(false);
			 }
		  }

		  @Override
		  public void focusLost(FocusEvent focusEvent) {
			 if (dateFeild.getText().equals("  -  -    ")) {
				assert finalMaskFormatter != null;
				finalMaskFormatter.setAllowsInvalid(true);
				dateFeild.setText("jj-mm-aaaa");
			 }
		  }
	   });

	   JLabel date_lablel = new JLabel("Date :");
	   date_lablel.setForeground(Color.LIGHT_GRAY);
	   date_lablel.setBounds(50, 270, 100, 40);

	   JButton btnAnnuler = new RoundedButton("Annuler");
	   btnAnnuler.setBackground(new Color(240, 74, 82));
	   btnAnnuler.setForeground(Color.DARK_GRAY);
	   btnAnnuler.setBounds(320, 380, 150, 40);
	   btnAnnuler.addActionListener(actionEvent -> frame.dispose());

	   JButton create_Button = new RoundedButton("Créer compte");
	   create_Button.setBounds(160, 380, 150, 40);
	   create_Button.setBackground(new Color(105, 205, 160));
	   create_Button.addActionListener(arg0 -> {
		  try {
			 if (userField.getText().isEmpty() || passField.getText().isEmpty() || Arrays.toString(passwordField.getPassword()).isEmpty()
				    || dateFeild.getText().equals("  -  -    ") || roleField.getText().isEmpty()) {
		          empty_fields();
			 } else if (! check(userField.getText()) && passField.getText().equals(new String(passwordField.getPassword()))) {
				String password = new String(passwordField.getPassword());
				new_account(userField.getText(), password, roleField.getText(), dateFeild.getText());
			 } else if (! passField.getText().equals(new String(passwordField.getPassword()))) {
				password_dont_match();
			 }
		  } catch (FileNotFoundException e) {
			 e.printStackTrace();
		  }
	   });

	   frame.add(date_lablel);
	   frame.add(dateFeild);
	   frame.add(roleField);
	   frame.add(role_label);
	   frame.add(btnAnnuler);
	   frame.add(passField);
	   frame.add(pass2_label);
	   frame.add(userField);
	   frame.add(passwordField);
	   frame.add(login_Label);
	   frame.add(pass_Label);
	   frame.add(create_Button);
	   frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	   frame.setLocationRelativeTo(null);
	   frame.setLayout(null);
	   frame.setVisible(true);

    }


    public static boolean check(String username) throws FileNotFoundException {
	   boolean account_exists = false;
	   FileReader reader = new FileReader("data/Roles.txt");
	   Scanner scanner = new Scanner(reader);
	   while (scanner.hasNextLine()) {
		  String string = scanner.nextLine();
		  String usernames = string.substring(string.indexOf("Username: "), string.indexOf("$USER")); // wont work with
		  // empty lines !!!!
		  if (usernames.equals("Username: " + username)) {
			 account_exists = true;
			 break;
		  } else {
			 account_exists = false;
		  }
	   }
	   scanner.close();
	   return account_exists;
    }

    public static String write_medecin(String username, String password) {
	   File Medecin = new File("data/Medecin.txt");
	   File Temporaire = new File("data/Medecin_Temporaire.txt");

	   String string_of_return = " ";

	   try (FileInputStream fis = new FileInputStream(Medecin);
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
		  PrintWriter fileWriter = new PrintWriter(Medecin);
		  InputStream inputStream = new FileInputStream(Temporaire);
		  InputStreamReader reader = new InputStreamReader(inputStream);
		  Scanner scanner = new Scanner(reader);

		  int counter = 1;

		  while (scanner.hasNextLine()) {
			 String string = scanner.nextLine();
			 fileWriter.println(string);
			 counter = Integer.parseInt(string.substring(0, string.indexOf(" ")));
			 counter++;
		  }
		  string_of_return = counter + " Username: " + username + "$USER Password: " + password + "$P";
		  fileWriter.write(string_of_return);
		  fileWriter.close();
		  scanner.close();


		  boolean bool = Temporaire.delete();
	   } catch (IOException e) {
		  e.printStackTrace();
	   }
	   return string_of_return;
    }

    public static String write_agent(String username, String password) {
	   File Agent = new File("data/Agents.txt");
	   File Temporaire = new File("data/Agents_Temporaire.txt");

	   String string_of_return = "";

	   try (FileInputStream fis = new FileInputStream(Agent);
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
		  PrintWriter fileWriter = new PrintWriter(Agent);
		  InputStream inputStream = new FileInputStream(Temporaire);
		  InputStreamReader reader = new InputStreamReader(inputStream);
		  Scanner scanner = new Scanner(reader);

		  int counter = 1;

		  while (scanner.hasNextLine()) {
			 String string = scanner.nextLine();
			 fileWriter.println(string);
			 counter = Integer.parseInt(string.substring(0, string.indexOf(" ")));
			 counter++;
		  }
		  string_of_return = counter + " Username: " + username + "$USER Password: " + password + "$P";
		  fileWriter.write(string_of_return);
		  fileWriter.close();
		  scanner.close();


		  boolean bool = Temporaire.delete();
	   } catch (IOException e) {
		  e.printStackTrace();
	   }
	   return string_of_return;
    }

    public static String write_technicien(String username, String password) {
	   File Technicien = new File("data/Techniciens.txt");
	   File Temporaire = new File("data/Techniciens_Temporaire.txt");

	   String string_of_return = "";

	   try (FileInputStream fis = new FileInputStream(Technicien);
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
		  PrintWriter fileWriter = new PrintWriter(Technicien);
		  InputStream inputStream = new FileInputStream(Temporaire);
		  InputStreamReader reader = new InputStreamReader(inputStream);
		  Scanner scanner = new Scanner(reader);

		  int counter = 1;

		  while (scanner.hasNextLine()) {
			 String string = scanner.nextLine();
			 fileWriter.println(string);
			 counter = Integer.parseInt(string.substring(0, string.indexOf(" ")));
			 counter++;
		  }
		  string_of_return = counter + " Username: " + username + "$USER Password: " + password + "$P";
		  fileWriter.write(string_of_return);
		  fileWriter.close();
		  scanner.close();


		  boolean bool = Temporaire.delete();
	   } catch (IOException e) {
		  e.printStackTrace();
	   }
	   return string_of_return;
    }

    static void new_account(String username, String password, String role, String date) throws FileNotFoundException {
	   File temporary = new File("data/temporary.txt");
	   File file = new File("data/Roles.txt");

	   boolean role_correct = false;
	   String roles = "";
	   switch (role) {
		  case "Medecin":
			 roles = write_medecin(username, password);
			 role_correct = true;
			 break;
		  case "Technicien":
			 role_correct = true;
			 roles = write_technicien(username, password);
			 break;
		  case "Agent":
			 role_correct = true;
			 roles = write_agent(username, password);
			 break;
		  default:
			 //TO DO
			 break;
	   }

	   if (role_correct) {
		  try (FileInputStream fis = new FileInputStream(file);
			  FileOutputStream fos = new FileOutputStream(temporary)) {
			 int len;
			 byte[] buffer = new byte[4096];
			 while ((len = fis.read(buffer)) > 0) {
				fos.write(buffer, 0, len);
			 }
		  } catch (IOException e) {
			 e.printStackTrace();
		  }

		  PrintWriter fileWriter = new PrintWriter(file);
		  InputStream inputStream = new FileInputStream(temporary);
		  InputStreamReader reader = new InputStreamReader(inputStream);
		  Scanner scanner = new Scanner(reader);

		  int counter = 1;

		  while (scanner.hasNextLine()) {
			 String string = scanner.nextLine();
			 fileWriter.println(string);
			 counter = Integer.parseInt(string.substring(0, string.indexOf(" ")));
			 counter++;
		  }
		  String final_string = roles + " Role : " + role + "$R Date: " + date +"$DATE";
		  fileWriter.write(counter + " " + final_string);

		  fileWriter.close();
		  scanner.close();
	   } else {
		  role_incorrect();
	   }
	   boolean bool = temporary.delete();
    }

    // les interfaces pour les erreurs

    public static void password_dont_match() {
	   JFrame frame = new JFrame();
	   frame.setSize(350, 130);
	   frame.getContentPane().setBackground(Color.darkGray);

	   JLabel label = new JLabel("Les mots de passes ne sont pas le meme");
	   label.setHorizontalAlignment(JLabel.CENTER);
	   label.setForeground(Color.LIGHT_GRAY);
	   label.setBounds(0, 20, 350, 30);

	   JButton button = new RoundedButton("OK");
	   button.setBounds(145, 60, 60, 30);
	   button.setBackground(new Color(79, 79, 79));
	   button.setForeground(Color.LIGHT_GRAY);
	   button.addActionListener(actionEvent -> frame.dispose());

	   frame.add(label);
	   frame.add(button);
	   frame.setLocationRelativeTo(null);
	   frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	   frame.setLayout(null);
	   frame.setVisible(true);
    }

    public static void role_incorrect() {
	   JFrame frame = new JFrame();
	   frame.setSize(350, 130);
	   frame.getContentPane().setBackground(Color.darkGray);

	   JLabel label = new JLabel("<html><div style='text-align: center;'>Role incorrect<br>" +
			 "Choisir entre Medecin,Agent et Technicien</html>");
	   label.setForeground(Color.LIGHT_GRAY);
	   label.setBounds(20, 20, 350, 30);

	   JButton button = new RoundedButton("OK");
	   button.setBounds(145, 60, 60, 30);
	   button.setBackground(new Color(79, 79, 79));
	   button.setForeground(Color.LIGHT_GRAY);
	   button.addActionListener(actionEvent -> frame.dispose());

	   frame.add(label);
	   frame.add(button);
	   frame.setLocationRelativeTo(null);
	   frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	   frame.setLayout(null);
	   frame.setVisible(true);
    }

    public static void empty_fields() {
	   JFrame frame = new JFrame();
	   frame.setSize(350, 130);
	   frame.getContentPane().setBackground(Color.darkGray);

	   JLabel label = new JLabel("Un ou plusieurs champs est vide");
	   label.setForeground(Color.LIGHT_GRAY);
	   label.setBounds(20, 20, 350, 30);

	   JButton button = new RoundedButton("OK");
	   button.setBounds(145, 60, 60, 30);
	   button.setBackground(new Color(79, 79, 79));
	   button.setForeground(Color.LIGHT_GRAY);
	   button.addActionListener(actionEvent -> frame.dispose());

	   frame.add(label);
	   frame.add(button);
	   frame.setLocationRelativeTo(null);
	   frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	   frame.setLayout(null);
	   frame.setVisible(true);
    }
}