package Classes_principales;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.NoSuchElementException;
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
        scanner.close();
        return Check;          //verifier si la consultation existe
    }

    public static ArrayList<String> getAppareil(int ID) throws FileNotFoundException {
        ArrayList<String> list = new ArrayList<>();

        File Appareils = new File("data/Appareils.txt");
        FileReader reader_Appareils = new FileReader(Appareils);
        Scanner scanner_Appareils = new Scanner(reader_Appareils);
        while (scanner_Appareils.hasNextLine()) {
            String ligne_Appareils = scanner_Appareils.nextLine();
            if (ligne_Appareils.contains("$APPAREIL$")) {
                ligne_Appareils = ligne_Appareils.replace("$APPAREIL$", "");
                ligne_Appareils = ligne_Appareils.replace("$A", "");
                String ID_de_patient_dans_Appareils = ligne_Appareils.substring(ligne_Appareils.indexOf("ID"), ligne_Appareils.indexOf("$ID"));
                if (Integer.parseInt(ID_de_patient_dans_Appareils.substring(4)) == ID) {
                    String string = ligne_Appareils.substring(ligne_Appareils.indexOf("$ID"));
                    string = string.substring(string.indexOf(" "));
                    list.add(string);
                }
            }
        }
        scanner_Appareils.close();
        return list;          //prendre les details des appareil, utilisé dans getConsultations
    }

    public static ArrayList<String> getConsultations(int ID) throws FileNotFoundException {
        ArrayList<String> tousLesConsultations = new ArrayList<>();
        ArrayList<String> list = getAppareil(ID);

        File Consultations = new File("data/Consultations.txt");
        FileReader reader_Consultations = new FileReader(Consultations);
        Scanner scanner_Consultations = new Scanner(reader_Consultations);
        int i = 0;
        while (scanner_Consultations.hasNextLine()) {
            String ligne_Consultations = scanner_Consultations.nextLine();
            String ligne = ligne_Consultations;
            ligne_Consultations = ligne_Consultations.replace("$APPAREIL$", "");
            ligne_Consultations = ligne_Consultations.replace("$A", " n°");
            String ID_de_patient_dans_Consultations = ligne_Consultations.substring(ligne_Consultations.indexOf("ID"), ligne_Consultations.indexOf("$ID"));

            if (Integer.parseInt(ID_de_patient_dans_Consultations.substring(4)) == (ID)) {
                int id = Integer.parseInt(ligne_Consultations.substring(0, ligne_Consultations.indexOf(" ")));
                String Consultation = ligne_Consultations.substring(ligne_Consultations.indexOf("$ID"));
                Consultation = Consultation.substring(Consultation.indexOf(" "));
                tousLesConsultations.add(id + " " + Consultation);
                if (ligne.contains("$APPAREIL$")) {
                    tousLesConsultations.add(list.get(i));
                    i++;
                }
            }
        }
        scanner_Consultations.close();
        return tousLesConsultations;          //details des consultation avec les detail des appareils
    }

    public static void creerConsultation(int ID, String consultation) {
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
            e.printStackTrace();
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
            System.out.println("Besoin d'appareil? Y pour oui, N pour non");
            Scanner scanner_appareil = new Scanner(System.in);
            String stringOfSwitch = scanner_appareil.nextLine();
            switch (stringOfSwitch.toUpperCase()) {
                case "Y":
                    System.out.println("Nombre d'appareil");
                    int nombre_appareil = scanner_appareil.nextInt();
                    scanner_appareil.nextLine();

                    write_Appareil(nombre_appareil, ID, true);

                    String string_appareil;
                    fileWriter.write(" $APPAREIL$ Appareil: ");
                    for (int i = 0; i < nombre_appareil; i++) {
                        System.out.println("L'Appareil?");
                        string_appareil = "$A" + (i + 1) + " " + scanner_appareil.nextLine() + "$\\A" + (i + 1) + ",\t";
                        fileWriter.write(string_appareil);
                    }
                    break;
                case "N":
                    write_Appareil(0, ID, false);
                    System.out.println("Pas d'appareil");
                    break;
                default:
                    System.out.println("Mauvaise saisie");
            }

            fileWriter.println();
            fileWriter.close();
            scanner.close();
            boolean bool = Temporaire.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }// creer une nouvelle consultation dans Consultations.txt
    // et Appareils.txt si le medecin decide de choisir des appareils aussi (pout la partie sans interface)

    public static void write_Appareil(int nombre, int ID, boolean boo) {
        File Appareil = new File("data/Appareils.txt");
        File Tempo = new File("data/AppareilTempo.txt");
        try (FileInputStream fis = new FileInputStream(Appareil);
             FileOutputStream fos = new FileOutputStream(Tempo)) {
            int len;
            byte[] buffer = new byte[4096];
            while ((len = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileReader reader = new FileReader(Tempo);
            PrintWriter writer = new PrintWriter(Appareil);
            Scanner scanner = new Scanner(reader);
            int counter = 0;
            while (scanner.hasNextLine()) {
                String string = scanner.nextLine();
                writer.println(string);
                counter = Integer.parseInt(string.substring(0, string.indexOf(" ")));
                counter++;
            }
            writer.write(counter + " ID: " + ID + "$ID");
            if (boo) {
                writer.write(" $APPAREIL$");
            }
            for (int i = 0; i < nombre; i++) {
                writer.write(" Appareil_" + (i + 1) + ": instance$A" + (i + 1));
            }
            writer.close();
            scanner.close();
            Tempo.delete();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }                    //method pour ecrire dans Appareils.txt, utilisé dans creerConsultation

    public static void modifierConsultation(int ID, int ID_Consultation, String consultation) {
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
            e.printStackTrace();
        }

        try {
            PrintWriter fileWriter = new PrintWriter(Consultations);
            InputStream inputStream = new FileInputStream(Temporaire);
            InputStreamReader reader = new InputStreamReader(inputStream);
            Scanner scanner = new Scanner(reader);
            while (scanner.hasNextLine()) {
                String string = scanner.nextLine();
                int id = Integer.parseInt(string.substring(0, string.indexOf(" ")));
                if (id != ID_Consultation) {
                    fileWriter.println(string);
                } else {
                    String appareils = string;
                    appareils = appareils.substring(appareils.indexOf("$APPAREIL$"));
                    fileWriter.write(id + " ID: " + ID + "$ID " + consultation);
                    fileWriter.println(" " + appareils);
                }
            }
            fileWriter.close();
            scanner.close();
            boolean bool = Temporaire.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }                    //reécrire une Consultation

    public static void write_Ordonnance(String text, int ID) {
        File Patients = new File("data/Patient.txt");
        String nom = "";
        String prenom = "";
        boolean exist = true;
        try {
            FileReader reader = new FileReader(Patients);
            Scanner scanner = new Scanner(reader);
            while (scanner.hasNextLine()) {
                String string = scanner.nextLine();
                int id = Integer.parseInt(string.substring(0, string.indexOf(" ")));
                if (id == ID) {
                    prenom = string.substring(string.indexOf("Prenom:"), string.indexOf("$Pn"));
                    prenom = prenom.substring(prenom.indexOf(" "));
                    nom = string.substring(string.indexOf("Nom:"), string.indexOf("$N"));
                    nom = nom.substring(nom.indexOf(" "));
                    exist = true;
                    break;
                } else {
                    exist = false;
                }
            }
            scanner.close();
            if (!exist) throw new NoSuchElementException();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
        File ordonnance = new File("Ordonnances/" + dateFormat.format(date) + "___" + nom.toUpperCase() + "-" + prenom + ".txt");
        try {
            PrintWriter writer = new PrintWriter(ordonnance);
            writer.println(text);
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }                    //creer un fichier avec le texte voulu et (nom = date + nom + prenom) dans le dossier Ordonnances


    public static void exporterConsultations(ArrayList<Integer> list, String nom_fichier) {
        try {
            File Consultations = new File("data/Consultations.txt");
            File exporter = new File("Consultations/" + nom_fichier + ".txt");
            PrintWriter writer = new PrintWriter(exporter);
            for (int i :
                    list) {
                FileReader reader_Consultations = new FileReader(Consultations);
                Scanner scanner_Consultations = new Scanner(reader_Consultations);
                while (scanner_Consultations.hasNextLine()) {
                    String string = scanner_Consultations.nextLine();
                    int id = Integer.parseInt(string.substring(0, string.indexOf(" ")));
                    if (i == id) {
                        string = string.replace("$APPAREIL$", "");
                        string = string.replace("$A", "");
                        string = string.replace("$ID", "");
                        string = string.substring(0, string.lastIndexOf(','));
                        writer.println(string);
                    }
                }
            }
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void getConsultation_technicien(int ID) throws FileNotFoundException {
        ArrayList<String> list = getAppareil(ID);

        File Consultations = new File("data/Consultations.txt");
        FileReader reader_Consultations = new FileReader(Consultations);
        Scanner scanner_Consultations = new Scanner(reader_Consultations);
        int i = 0;
        while (scanner_Consultations.hasNextLine()) {
            String ligne_Consultations = scanner_Consultations.nextLine();
            if (ligne_Consultations.contains("$APPAREIL$")) {
                ligne_Consultations = ligne_Consultations.replace("$APPAREIL$", "");
                ligne_Consultations = ligne_Consultations.replace("$A", " n°");
                String ID_de_patient_dans_Consultations = ligne_Consultations.substring(ligne_Consultations.indexOf("ID"), ligne_Consultations.indexOf("$ID"));

                if (Integer.parseInt(ID_de_patient_dans_Consultations.substring(4)) == (ID)) {
                    int id = Integer.parseInt(ligne_Consultations.substring(0, ligne_Consultations.indexOf(" ")));
                    String Consultation = ligne_Consultations.substring(ligne_Consultations.indexOf("$ID"));
                    Consultation = Consultation.substring(Consultation.indexOf(" "));
                    System.out.println(id + " " + Consultation);
                    System.out.println(list.get(i));
                    i++;
                }
            }
        }
        scanner_Consultations.close();
    }

    public static void octroyer(int ID, int Appareil) throws FileNotFoundException {
        File Appareils = new File("data/Appareils.txt");
        File temporaire = new File("data/temporaire.txt");
        try (FileInputStream fis = new FileInputStream(Appareils);
             FileOutputStream fos = new FileOutputStream(temporaire)) {
            int len;
            byte[] buffer = new byte[4096];
            while ((len = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileReader reader = new FileReader(temporaire);
        Scanner scanner = new Scanner(reader);
        PrintWriter writer = new PrintWriter(Appareils);
        while (scanner.hasNextLine()) {
            String ligne = scanner.nextLine();
            if (ID == Integer.parseInt(ligne.substring(0, ligne.indexOf(" ")))) {
                if (ligne.contains("$APPAREIL$")) {
                    ligne = ligne.replace("instance$A" + Appareil, "octroyé$A" + Appareil);
                }
            }
            writer.println(ligne);
        }
        writer.close();
        scanner.close();
        boolean bool = temporaire.delete();
    }

    public static void supprimer_consultation(int ID) {
        File Consultations = new File("data/Consultations.txt");
        File Tempo = new File("data/tempo.txt");
        supprime_appareil(ID);
        try (FileInputStream fis = new FileInputStream(Consultations);
             FileOutputStream fos = new FileOutputStream(Tempo)) {
            int len;
            byte[] buffer = new byte[4096];
            while ((len = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            PrintWriter fileWriter = new PrintWriter(Consultations);
            InputStream inputStream = new FileInputStream(Tempo);
            InputStreamReader reader = new InputStreamReader(inputStream);
            Scanner scanner = new Scanner(reader);

            while (scanner.hasNextLine()) {
                String string = scanner.nextLine();
                int counter = Integer.parseInt(string.substring(0, string.indexOf(" ")));
                if (counter != ID) {
                    fileWriter.println(string);
                }
            }
            fileWriter.close();
            scanner.close();
            boolean bool = Tempo.delete();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void supprime_appareil(int ID) {
        File Appareils = new File("data/Appareils.txt");
        File Tempo = new File("data/tempo.txt");
        try (FileInputStream fis = new FileInputStream(Appareils);
             FileOutputStream fos = new FileOutputStream(Tempo)) {
            int len;
            byte[] buffer = new byte[4096];
            while ((len = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            PrintWriter fileWriter = new PrintWriter(Appareils);
            InputStream inputStream = new FileInputStream(Tempo);
            InputStreamReader reader = new InputStreamReader(inputStream);
            Scanner scanner = new Scanner(reader);

            while (scanner.hasNextLine()) {
                String string = scanner.nextLine();
                int counter = Integer.parseInt(string.substring(0, string.indexOf(" ")));
                if (counter != ID) {
                    fileWriter.println(string);
                }
            }
            fileWriter.close();
            scanner.close();
            Tempo.delete();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
