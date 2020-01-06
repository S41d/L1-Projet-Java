package Sans_Interface;

import Classes_principales.Consultations;
import Classes_principales.Patient;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class medecin {
    public static void detailsConsultations(int numeroID) {
        try {
            ArrayList<String> Consultation = Consultations.getConsultations(numeroID);
            for (String string1 :
                    Consultation) {
                System.out.println(string1);
            }
        } catch (FileNotFoundException i) {
            i.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Saisir: D pour details du patient et ses consultations, N pour creer une consultation," + "\n" +
                " M pour modifier une consultation, O pour creer une ordonnance, E pour exporter des consultations, S pour supprimer une consultation");
        Scanner scanner = new Scanner(System.in);
        String switchString = scanner.nextLine();
        int ID, ID_Consultation;
        switch (switchString.toUpperCase()) {
            case "D":
                System.out.println("ID du patient?");
                ID = scanner.nextInt();
                if (Consultations.Check(ID)) {
                    System.out.println(Patient.recherche(ID));
                    detailsConsultations(ID);
                } else if (Patient.check(ID)) {
                    System.out.println(Patient.recherche(ID));
                    System.out.println("Pas de Consultations");
                } else System.out.println("ce patient n'est pas dans la liste");
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
                    Consultations.modifierConsultation(ID_Consultation, consultation);
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
            case "E":
                ArrayList<Integer> num_exporter = new ArrayList<>();
                System.out.println("ID du patient?");
                ID = scanner.nextInt();
                if (Patient.check(ID) && Consultations.Check(ID)) {
                    detailsConsultations(ID);
                    System.out.println("Combien de consultations?");
                    int nombre_de_consultation = scanner.nextInt();
                    for (int i = 0; i < nombre_de_consultation; i++) {
                        System.out.println("numero de consultations?");
                        int num_de_consultation = scanner.nextInt();
                        num_exporter.add(num_de_consultation);
                    }
                    scanner.nextLine();
                    System.out.println("Nom de fichier?");
                    String nom = scanner.nextLine();
                    Consultations.exporterConsultations(num_exporter, nom);
                } else {
                    System.out.println("Le patient n'est pas dans la liste des patients ou n'a pas encore de consultations");
                }
                break;
            case "S":
                System.out.println("ID du patient");
                ID = scanner.nextInt();
                if (Patient.check(ID) && Consultations.Check(ID)) {
                    detailsConsultations(ID);
                    System.out.println("Numero de la consultations?");
                    int num = scanner.nextInt();
                    Consultations.supprimer_consultation(num);
                    scanner.nextLine();
                } else {
                    System.out.println("Le patient n'est pas dans la liste des patients ou n'a pas encore de consultations");
                }
                break;
            default:
                System.out.println("Mauvaise saisie");
                break;
        }

    }

}