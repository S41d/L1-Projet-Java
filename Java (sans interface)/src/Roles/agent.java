package Roles;

import Classes_principales.Patient;

import java.io.IOException;
import java.util.Scanner;

public class agent {
    public static void Nouveau() {
	   Scanner sc = new Scanner(System.in);

	   System.out.println("Nom");
	   String nom = sc.nextLine();

	   System.out.println("Prenom?");
	   String prenom = sc.nextLine();

	   System.out.println("Adresse");
	   int Adresse = sc.nextInt();

	   System.out.println("Date de Naissance");

	   System.out.println("Jour?");
	   int Jour = sc.nextInt();

	   System.out.println("Mois?");
	   int Mois = sc.nextInt();

	   System.out.println("Année");
	   int Annee = sc.nextInt();

	   sc.close();
	   String Date = Jour + "/" + Mois + "/" + Annee;
	   Patient.nouveauPatient(nom, prenom, Adresse, Date);
    }

    public static void Verifier() throws IOException {
	   System.out.println("ID de Patient");
	   Scanner scanner = new Scanner(System.in);
	   int ID = scanner.nextInt();
	   if (Patient.check(ID)) {
		  System.out.println("Le patient exist");
	   } else {
		  System.out.println("Le patient n'existe pas");
	   }
    }

    public static void Supprimer() throws IOException {
	   System.out.println("ID de Patient");
	   Scanner scanner = new Scanner(System.in);
	   int ID = scanner.nextInt();
	   if (Patient.check(ID)) {
		  Patient.supprimerPatient(ID);
	   }
    }

    public static void Modifier() throws IOException {
	   Scanner sc = new Scanner(System.in);

	   System.out.println("ID?");
	   int ID = sc.nextInt();
	   sc.nextLine();

	   System.out.println("Nom");
	   String nom = sc.nextLine();

	   System.out.println("Prenom?");
	   String prenom = sc.nextLine();

	   System.out.println("Adresse");
	   int Adresse = sc.nextInt();

	   System.out.println("Date de Naissance");

	   System.out.println("Jour?");
	   int Jour = sc.nextInt();

	   System.out.println("Mois?");
	   int Mois = sc.nextInt();

	   System.out.println("Année");
	   int Annee = sc.nextInt();

	   sc.close();
	   String Date = Jour + "/" + Mois + "/" + Annee;
	   Patient.modifier(ID, nom, prenom, Adresse, Date);
    }

    public static void main(String[] args) throws IOException {
	   Scanner scanner = new Scanner(System.in);
	   System.out.println("Saisir : N pour enregistrer un nouveau patient, V verifier que le patient est dans la liste des patients, S pour Supprimer, M pour modifier");
	   String switchString = scanner.nextLine();
	   switch (switchString.toUpperCase()) {
		  case "N":
			 Nouveau(); break;
		  case "V":
			 Verifier();
			 medecin.main(null);break;
		  case "S":
			 Supprimer(); break;
		  case "M":
			 Modifier(); break;
		  default:
			 System.out.println("Mauvaise saisie"); break;
	   }
    }
}
