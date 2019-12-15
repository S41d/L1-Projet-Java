package Classes_principales;

import java.io.*;
import java.util.ArrayList;
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

    public static ArrayList<String> getConsultations(int ID) throws FileNotFoundException {
	   ArrayList<String> tousLesConsultations = new ArrayList<>();
	   File Consultations = new File("data/Consultations.txt");
	   FileReader reader = new FileReader(Consultations);
	   Scanner scanner = new Scanner(reader);
	   while (scanner.hasNextLine()) {
		  String ligne = scanner.nextLine();
		  String id_Patient = ligne.substring(ligne.indexOf("ID"), ligne.indexOf("$ID"));
		  if (Integer.parseInt(id_Patient.substring(4)) == (ID)) {
		      int id = Integer.parseInt(ligne.substring(0, ligne.indexOf(" ")));
			 String Consultation = ligne.substring(ligne.indexOf("$ID"));
			 Consultation = Consultation.substring(Consultation.indexOf(" "));
			 tousLesConsultations.add(id + " " + Consultation);
		  }
	   }
	   return tousLesConsultations;
    }

    public static void creerConsultation(int ID ,String consultation) {
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
		  System.out.println("Pas copié dans Temporaire_consultations.txt");
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
		  fileWriter.close();
		  scanner.close();
		  boolean bool = Temporaire.delete();
	   } catch (IOException e) {
		  System.out.println("failed to write back in Patient file");
	   }
    }

    public static void modifierConsultation(int ID, int ID_Consultation, String consultation){
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
		  System.out.println("Pas copié dans temporaire");
        }

	   try {
		  PrintWriter fileWriter = new PrintWriter(Consultations);
		  InputStream inputStream = new FileInputStream(Temporaire);
		  InputStreamReader reader = new InputStreamReader(inputStream);
		  Scanner scanner = new Scanner(reader);
		  while (scanner.hasNextLine()) {
			 String string = scanner.nextLine();
			 int id = Integer.parseInt(string.substring(0, string.indexOf(" ")));
			 if (id != ID_Consultation){
				fileWriter.println(string);
			 }	else {
				fileWriter.println(id + " ID: " + ID + "$ID " + consultation);
			 }
		  }
		  fileWriter.close();
		  scanner.close();
		  boolean bool = Temporaire.delete();
	   } catch (IOException e) {
		  System.out.println("failed to write back in Patient file");
	   }
    }
}
