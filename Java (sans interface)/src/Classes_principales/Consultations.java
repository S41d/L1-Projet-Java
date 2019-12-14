package Classes_principales;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
		  String id = ligne.substring(ligne.indexOf("ID"), ligne.indexOf("$ID"));
		  if (Integer.parseInt(id.substring(4)) == (ID)) {
			 String Consultation = ligne.substring(ligne.indexOf("$ID"));
			 Consultation = Consultation.substring(Consultation.indexOf(" "));
			 tousLesConsultations.add(Consultation);
		  }
	   }
	   return tousLesConsultations;
    }
}
