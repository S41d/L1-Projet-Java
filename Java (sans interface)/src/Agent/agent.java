package Agent;

import Medecin.*;

import javax.swing.*;
import java.io.*;
import java.util.Scanner;

public class agent {
    public static void cree_patient(String nom, String prenom, String adresse,String date_de_naissance ) throws FileNotFoundException {
        write_temporary();
        write_file(nom, prenom, adresse, date_de_naissance);
    }
    static void write_temporary() throws FileNotFoundException {
        File temporary = new File("data/temporary.txt");
        File file = new File("data/Patient.txt");

        PrintWriter tempWriter = new PrintWriter(temporary);
        InputStream inputStream = new FileInputStream(file);
        InputStreamReader reader = new InputStreamReader(inputStream);
        Scanner scanner = new Scanner(reader);


        while (scanner.hasNextLine()) {
            String string = scanner.nextLine();
            tempWriter.println(string);
        }

        tempWriter.close();
        scanner.close();
    }
    static void write_file(String nom, String prenom, String adresse, String date_de_naissance) throws FileNotFoundException {
        File temporary = new File("data/temporary.txt");
        File file = new File("data/Patient.txt");

        PrintWriter fileWriter = new PrintWriter(file);
        InputStream inputStream = new FileInputStream(temporary);
        InputStreamReader reader = new InputStreamReader(inputStream);
        Scanner scanner = new Scanner(reader);

        String ligne = "Nom: " + nom + "$N\t\tPrenom: " + prenom + "$Pn\t\tAdresse: " + adresse + "$A\t\tDate de Naissance: " + date_de_naissance + "$Date";

        int counter = 1;

        while (scanner.hasNextLine()) {
            String string = scanner.nextLine();
            fileWriter.println(string);
            counter = Integer.parseInt(string.substring(0,string.indexOf(" ")));
            counter++;
        }
        fileWriter.write(counter + " " + ligne);

        fileWriter.close();
        scanner.close();
        temporary.delete();
    }

    public static void main(String[] args) throws  FileNotFoundException{
        cree_patient("said", "password", "37000", "21/03/1999");
    }
}
