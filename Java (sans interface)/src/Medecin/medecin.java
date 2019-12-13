package Medecin;

import java.io.*;
import java.util.*;

public class medecin {
    public static String recherche(int ID) throws FileNotFoundException {
        File Consultations = new File("data/Patient.txt");
        FileReader reader = new FileReader(Consultations);
        Scanner scanner = new Scanner(reader);
        String details = "";

        while (scanner.hasNextLine()) {
            String ligne = scanner.nextLine();
            String nom = ligne.substring(ligne.indexOf("Nom: "), ligne.indexOf("$N"));
            String prenom = ligne.substring(ligne.indexOf("Prenom: "), ligne.indexOf("$Pn"));
            String adresse = ligne.substring(ligne.indexOf("Adresse: "), ligne.indexOf("$A"));
            String ddn = ligne.substring(ligne.indexOf("Date de naissance: "), ligne.indexOf("$Date"));
            if (Integer.parseInt(ligne.substring(0, ligne.indexOf(" "))) == (ID)) {
                String Nom = nom.substring(nom.indexOf(" "));
                String Prenom = prenom.substring(prenom.indexOf(" "));
                String Adresse = adresse.substring(adresse.indexOf(" "));
                String DDN = ddn.substring(ddn.lastIndexOf(" "));
                details = "ID : " + ID + " ,Nom :" + Nom + " ,Prenom :" + Prenom + " ,Adresse :" + Adresse + ", Date de naissance : " + DDN;
                break;
            }
        }
        scanner.close();
        return details;
    }

    public static void main(String[] args) throws Exception {
        System.out.println(recherche(1)); 
    }
}