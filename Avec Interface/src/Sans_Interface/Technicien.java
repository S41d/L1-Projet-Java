package Sans_Interface;

import Classes_principales.Consultations;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Technicien {
    public static void main(String[] args) throws FileNotFoundException {
	   Scanner scanner = new Scanner(System.in);

	   System.out.println("ID du patient?");
	   int ID_scanner = scanner.nextInt();
	   Consultations.getConsultation_technicien(ID_scanner);

        System.out.println("\n" + "Numero de Consultation?");
        int num_Consultation = scanner.nextInt();
        System.out.println("Combien d'appareil a octroyer?");
        int nombre_d_appareil = scanner.nextInt();

        for (int i = 0; i < nombre_d_appareil; i++) {
            System.out.println("Numero d'appareil dans consultation");
            int num_Appareil = scanner.nextInt();
            Consultations.octroyer(num_Consultation, num_Appareil);
        }
    }
}
