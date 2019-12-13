package Medecin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Check {
    public static boolean check(int ID) throws IOException {
        boolean Check = false;
        File Patients = new File("data/Patient.txt");
        FileReader reader = new FileReader(Patients);
        Scanner scanner = new Scanner(reader);
        while (scanner.hasNextLine()) {
            String ligne = scanner.nextLine();
            if (Integer.parseInt(ligne.substring(0, ligne.indexOf(" "))) == (ID)) {
                Check = true;
                break;
            }   else {
                Check = false;
            }
        }
        scanner.close();
        return Check;
    }
}
