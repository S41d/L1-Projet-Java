package Classes_principales;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Consultations {
    public static boolean Check(int ID) throws IOException {
	   boolean Check = true;
	   File Consultations = new File("data/Consultations.txt");
	   FileReader reader = new FileReader(Consultations);
	   Scanner scanner = new Scanner(reader);
	   while (scanner.hasNextLine()) {
		  String ligne = scanner.nextLine();
		  String id = ligne.substring(ligne.indexOf("ID"), ligne.indexOf("$ID"));
		  if (Integer.parseInt(id.substring(4)) == (ID)) {
			 Check = true;
			 break;
		  } else {
			 Check = false;
		  }
	   }
	   return Check;
    }

    public static ArrayList<String> getAppareil(int ID) throws FileNotFoundException {
	   ArrayList<String> list = new ArrayList<>();

	   File Appareils = new File("data/Appareils.txt");
	   FileReader reader_Appareils = new FileReader(Appareils);
	   Scanner scanner_Appareils = new Scanner(reader_Appareils);
	   while (scanner_Appareils.hasNextLine()) {
		  String ligne_Appareils = scanner_Appareils.nextLine();
		  ligne_Appareils = ligne_Appareils.replace("$A", " ");
		  String ID_de_patient_dans_Appareils = ligne_Appareils.substring(ligne_Appareils.indexOf("ID"), ligne_Appareils.indexOf("$ID"));
		  if (Integer.parseInt(ID_de_patient_dans_Appareils.substring(4)) == ID) {
			 String string = ligne_Appareils.substring(ligne_Appareils.indexOf("$ID"));
			 string = string.substring(string.indexOf(" "));
			 list.add(string);
		  }
	   }
	   return list;
    }

    public static ArrayList<String> getConsultations(int ID) throws FileNotFoundException {
	   ArrayList<String> tousLesConsultations = new ArrayList<>();
	   ArrayList<String> list = getAppareil(ID);

	   File Consultations = new File("data/Consultations.txt");
	   FileReader reader_Consultations = new FileReader(Consultations);
	   Scanner scanner_Consultations = new Scanner(reader_Consultations);
	   int i = 0;
	   while (scanner_Consultations.hasNextLine()) {
		  String ligne_Consultations = scanner_Consultations.nextLine();
		  ligne_Consultations = ligne_Consultations.replace("$A", " ");
		  String ID_de_patient_dans_Consultations = ligne_Consultations.substring(ligne_Consultations.indexOf("ID"), ligne_Consultations.indexOf("$ID"));

		  if (Integer.parseInt(ID_de_patient_dans_Consultations.substring(4)) == (ID)) {
			 int id = Integer.parseInt(ligne_Consultations.substring(0, ligne_Consultations.indexOf(" ")));
			 String Consultation = ligne_Consultations.substring(ligne_Consultations.indexOf("$ID"));
			 Consultation = Consultation.substring(Consultation.indexOf(" "));
			 tousLesConsultations.add(id + " " + Consultation);
			 tousLesConsultations.add(list.get(i));
			 i++;
		  }
	   }
	   return tousLesConsultations;
    }

    public static void creerConsultation(int ID, String consultation) {
	   File Consultations = new File("data/Consultations.txt");
	   File Temporaire = new File("data/Temporaire_consultations.txt");
	   try (FileInputStream fis = new FileInputStream(Consultations);
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
		  PrintWriter fileWriter = new PrintWriter(Consultations);
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
		  fileWriter.write(counter + " ID: " + ID + "$ID " + consultation);
		  System.out.println("Besoin d'appareil? Y pour oui, N pour non");
		  Scanner scanner_appareil = new Scanner(System.in);
		  String stringOfSwitch = scanner_appareil.nextLine();
		  switch (stringOfSwitch.toUpperCase()){
			 case "Y":
				System.out.println("Nombre d'appareil");
				int nombre_appareil = scanner_appareil.nextInt();
				scanner_appareil.nextLine();

				write_Appareil(nombre_appareil, ID, true);

				String string_appareil;
				fileWriter.write(" Appareil: ");
				for (int i = 0; i < nombre_appareil; i++) {
				    System.out.println("L'Appareil?");
				    string_appareil = scanner_appareil.nextLine() + "$A" + (i + 1) + ",\t";
				    fileWriter.write(string_appareil);
				}
				break;
			 case "N":
			     write_Appareil(0, ID, false);
				System.out.println("Pas d'appareil");
				break;
			 default:
				System.out.println("Mauvaise saisie");
		  }

		  fileWriter.println();
		  fileWriter.close();
		  scanner.close();
		  boolean bool = Temporaire.delete();
	   } catch (IOException e) {
		  e.printStackTrace();
	   }
    }

    public static void write_Appareil(int nombre, int ID, boolean boo) {
	   File Appareil = new File("data/Appareils.txt");
	   File Tempo = new File("data/AppareilTempo.txt");
	   try (FileInputStream fis = new FileInputStream(Appareil);
		   FileOutputStream fos = new FileOutputStream(Tempo)) {
		  int len;
		  byte[] buffer = new byte[4096];
		  while ((len = fis.read(buffer)) > 0) {
			 fos.write(buffer, 0, len);
		  }
	   } catch (IOException e) {
		  e.printStackTrace();
	   }
	   try {
		  FileReader reader = new FileReader(Tempo);
		  PrintWriter writer = new PrintWriter(Appareil);
		  Scanner scanner = new Scanner(reader);
		  int counter = 0;
		  while (scanner.hasNextLine()) {
			 String string = scanner.nextLine();
			 writer.println(string);
			 counter = Integer.parseInt(string.substring(0, string.indexOf(" ")));
			 counter++;
		  }
		  writer.write(counter + " ID: " + ID + "$ID");
		  if (boo){
			 writer.write(" $APPAREIL$");
		  }
		  for (int i = 0; i < nombre; i++) {
			 writer.write(" Appareil " + (i + 1) + ": instance$A");
		  }
		  writer.close();
		  scanner.close();

	   } catch (FileNotFoundException e) {
		  e.printStackTrace();
	   }
    }

    public static void modifierConsultation(int ID, int ID_Consultation, String consultation) {
	   File Consultations = new File("data/Consultations.txt");
	   File Temporaire = new File("data/Temporaire_Consultations.txt");
	   try (FileInputStream fis = new FileInputStream(Consultations);
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
		  PrintWriter fileWriter = new PrintWriter(Consultations);
		  InputStream inputStream = new FileInputStream(Temporaire);
		  InputStreamReader reader = new InputStreamReader(inputStream);
		  Scanner scanner = new Scanner(reader);
		  while (scanner.hasNextLine()) {
			 String string = scanner.nextLine();
			 int id = Integer.parseInt(string.substring(0, string.indexOf(" ")));
			 if (id != ID_Consultation) {
				fileWriter.println(string);
			 } else {
				String appareils = string;
				appareils = appareils.substring(appareils.indexOf("Appareil:"));
				fileWriter.write(id + " ID: " + ID + "$ID " + consultation);
				fileWriter.println(" " + appareils);
			 }
		  }
		  fileWriter.close();
		  scanner.close();
		  boolean bool = Temporaire.delete();
	   } catch (IOException e) {
		  e.printStackTrace();
	   }
    }

    public static void write_Ordonnance(String text, int ID) {
	   File Patients = new File("data/Patient.txt");
	   String nom = "";
	   String prenom = "";
	   boolean exist = true;
	   try {
		  FileReader reader = new FileReader(Patients);
		  Scanner scanner = new Scanner(reader);
		  while (scanner.hasNextLine()) {
			 String string = scanner.nextLine();
			 int id = Integer.parseInt(string.substring(0, string.indexOf(" ")));
			 if (id == ID) {
			     prenom = string.substring(string.indexOf("Prenom:"), string.indexOf("$Pn"));
			     prenom = prenom.substring(prenom.indexOf(" "));
				nom = string.substring(string.indexOf("Nom:"), string.indexOf("$N"));
				nom = nom.substring(nom.indexOf(" "));
				exist = true; break;
			 } else {
				exist = false;
			 }
		  }
		  if (! exist) throw new NoSuchElementException();
	   } catch (FileNotFoundException e) {
		  e.printStackTrace();
	   }
	   Date date = new Date();
	   SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
	   File ordonnance = new File("Ordonnances/" + dateFormat.format(date) + "___" + nom.toUpperCase() + "-" + prenom + ".txt");
	   try {
		  PrintWriter writer = new PrintWriter(ordonnance);
		  writer.println(text);
		  writer.close();
	   } catch (FileNotFoundException e) {
		  e.printStackTrace();
	   }
    }

    public static void exporterConsultations(ArrayList<String> list){

    }
}
