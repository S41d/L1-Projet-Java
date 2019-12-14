package Medecin;

import java.io.*;
import java.util.*;
import Classes_principales.*;

public class medecin {
    public static void detailsConsultations(int numeroID) {
	   try {
		  ArrayList<String> Consultation = Consultations.getConsultations(numeroID);
		  for (String string1 :
				Consultation) {
			 System.out.println(string1);
		  }
	   }catch (FileNotFoundException i){
		  System.out.println("Consultations file not found");
	   }
    }

    public static void main(String[] args) throws IOException{
	   System.out.println("ID?");
	   Scanner scanner = new Scanner(System.in);
	   int ID = scanner.nextInt();
        if (Consultations.Check(ID)) {
		  System.out.println(Patient.recherche(ID));
            detailsConsultations(ID);
	   }
        else System.out.println("Pas de Consultations");
    }

}