package Roles;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Classes_principales.*;

public class medecin {
    public static void detailsConsultations(int numeroID) {
	   try {
		  ArrayList<String> Consultation = Consultations.getConsultations(numeroID);
		  for (String string1 :
				Consultation) {
			 System.out.println(string1);
		  }
	   } catch (FileNotFoundException i) {
		  System.out.println("Consultations file not found");
	   }
    }

    public static void main(String[] args) throws IOException {
	   System.out.println("Saisir: D pour details du patient et ses consultations, N pour creer une consultation," +
			 " M pour modifier une consultation, O pour creer une ordonnance");
	   Scanner scanner = new Scanner(System.in);
	   String switchString = scanner.nextLine();
	   int ID, ID_Consultation;
	   switch (switchString.toUpperCase()) {
		  case "D":
			 System.out.println("ID?");
			 ID = scanner.nextInt();
			 if (Consultations.Check(ID)) {
				System.out.println(Patient.recherche(ID));
				detailsConsultations(ID);
			 } else System.out.println("Pas de Consultations");
			 break;
		  case "N":
			 System.out.println("ID du patient?");
			 ID = scanner.nextInt();
			 scanner.nextLine();
			 if (Patient.check(ID)) {
				System.out.println("Ecrire la consultation");
				String consultation = scanner.nextLine();
				Consultations.creerConsultation(ID, consultation);
			 } else {
				System.out.println("Ce patient n'existe pas");
			 }
			 break;
		  case "M":
			 System.out.println("ID du patient?");
			 ID = scanner.nextInt();
			 if (Consultations.Check(ID)) {
				System.out.println(Patient.recherche(ID));
				detailsConsultations(ID);
				System.out.println("Numero de la consultation pour modifier?");
				ID_Consultation = scanner.nextInt();
				scanner.nextLine();
				System.out.println("Nouveau consultation?");
				String consultation = scanner.nextLine();
				Consultations.modifierConsultation(ID, ID_Consultation, consultation);
			 } else {
				System.out.println("Pas de Consultations");
			 }
			 break;
		  case "O":
			 System.out.println("ID du patient?");
			 ID = scanner.nextInt();
			 scanner.nextLine();
			 if (Patient.check(ID)) {
				System.out.println("Ecrire l'ordonnance");
				String ordonnance = scanner.nextLine();
				Consultations.write_Ordonnance(ordonnance, ID);
			 } else {
				System.out.println("Ce patient n'exist pas");
			 }
			 break;
		  default:
			 System.out.println("Mauvaise saisie"); break;
	   }

    }

}