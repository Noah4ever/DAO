package Kaufvertrag.presentationLayer;

import Kaufvertrag.businessObjects.IVertragspartner;
import Kaufvertrag.businessObjects.IWare;
import Kaufvertrag.dataLayer.businessObjects.Adresse;
import Kaufvertrag.dataLayer.businessObjects.Kaufvertrag;
import Kaufvertrag.dataLayer.businessObjects.Vertragspartner;
import Kaufvertrag.dataLayer.dataAccessObjects.DataLayerManager;
import Kaufvertrag.dataLayer.dataAccessObjects.IDataLayer;
import Kaufvertrag.dataLayer.dataAccessObjects.XML.DataLayerXml;
import Kaufvertrag.exceptions.DaoException;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.Buffer;
import java.util.List;

public class Programm {
    static BufferedReader reader = new BufferedReader(new java.io.InputStreamReader(System.in));
    static IDataLayer dl = null;
    public static void main(String[] args) {
        DataLayerManager dlm = DataLayerManager.getInstance();
        
        
        System.out.println("---< Kaufvertrag >---\n");
        System.out.println("[1] Einlesen");
        System.out.println("[2] Neu erstellen");

        int input = 0;
        try {
            input = Integer.parseInt(reader.readLine());
            if(input == 1) {
                System.out.println("Einlesen");
                System.out.println("---< Einlesen >---");
                System.out.println("[1] XML");
                System.out.println("[2] SQLite");
                int input2 = Integer.parseInt(reader.readLine());

                if (input2 == 1) {
                    dlm.getInstance().persistenceType = "xml";
                    dl = dlm.getDataLayer();
                    List<IVertragspartner> vp = dl.getVertragspartnerDao().read();
                    List<IWare> w = dl.getWareDao().read();
                    bearbeiten(vp, w);

                } else if (input2 == 2) {
                    dlm.getInstance().persistenceType = "sqlite";
                    dl = dlm.getDataLayer();

                } else {
                    System.out.println("Persistence type not supported!");
                }
                if(dl != null){

                }
            } else if(input == 2) {
                System.out.println("Neu erstellen");
                System.out.println("---< Neu erstellen >---");

            } else {
                System.out.println("Input incorrect!");
            }
        } catch (NumberFormatException | DaoException | IOException e) {
            System.out.println("Invalid input");
        }
    }

    private static void options(){
        System.out.println("[1] Bearbeiten");
        System.out.println("[2] Neu erstellen");
        System.out.println("[3] Löschen");
    }

    private static void bearbeiten(List<IVertragspartner> vp, List<IWare> w) throws IOException {
        System.out.println("---< Bearbeiten >---");
        System.out.println("[1] Vertragspartner");
        System.out.println("[2] Ware");
        int input3 = Integer.parseInt(reader.readLine());
        if (input3 == 1) {
            Vertragspartner(vp, w);
        }else if (input3 == 2) {
            System.out.println("---< Ware >---");
            options(); // Prints options for bearbeiten ware
            int input4 = Integer.parseInt(reader.readLine());
            if (input4 == 1) {
                System.out.println("---< Ware - Bearbeiten >---");
                System.out.println("[1] Bezeichnung");
                System.out.println("[2] Beschreibung");
                System.out.println("[3] Preis");
                System.out.println("[4] Besonderheiten");
                System.out.println("[4] Mängel");

            }
        }
    }

    private static void Vertragspartner(List<IVertragspartner> vp, List<IWare> w) throws IOException {
        System.out.println("---< Vertragspartner >---");
        options(); // Prints options for bearbeiten ware
        int input4 = Integer.parseInt(reader.readLine());
        if (input4 == 1) {
            VertragspartnerBearbeiten(vp, w);
        }else if(input4 == 2) {
            Vertragspartner vertragspartner = new Vertragspartner("", "");
            Adresse adresse = new Adresse("", "", "", "");
            VertragspartnerNeuErstellen(vp, w, vertragspartner, adresse);
        } else if(input4 == 3) {
            System.out.println("---< Vertragspartner - Löschen >---");
        }else {
            System.out.println("Input incorrect!");
            Vertragspartner(vp, w);
        }
    }

    private static void VertragspartnerOptions(){
        System.out.println("[1] Vorname");
        System.out.println("[2] Nachname");
        System.out.println("[3] Ausweisnummer");
        System.out.println("-- Adresse --");
        System.out.println("[4] Strasse");
        System.out.println("[5] Hausnummer");
        System.out.println("[6] PLZ");
        System.out.println("[7] Ort");
        System.out.println("\n[0] Save");
    }

    private static void VertragspartnerNeuErstellen(List<IVertragspartner> vp, List<IWare> w, Vertragspartner vertragspartner, Adresse adresse) throws IOException {
        System.out.println("---< Vertragspartner - Neu erstellen >---");
        VertragspartnerOptions();
        int input5 = Integer.parseInt(reader.readLine());
        if (input5 == 1) {
            System.out.println("Vorname: ");
            String vorname = reader.readLine();
            vertragspartner.setVorname(vorname);
        } else if (input5 == 2) {
            System.out.println("Nachname: ");
            String nachname = reader.readLine();
            vertragspartner.setNachname(nachname);
        } else if (input5 == 3) {
            System.out.println("Ausweisnummer: ");
            String ausweisnummer = reader.readLine();
            vertragspartner.setAusweisNr(ausweisnummer);
        } else if (input5 == 4) {
            System.out.println("Strasse: ");
            String strasse = reader.readLine();
            adresse.setStrasse(strasse);
        } else if (input5 == 5) {
            System.out.println("Hausnummer: ");
            String hausnummer = reader.readLine();
            adresse.setHausNr(hausnummer);
        } else if (input5 == 6) {
            System.out.println("PLZ: ");
            String plz = reader.readLine();
            adresse.setPlz(plz);
        } else if (input5 == 7) {
            System.out.println("Ort: ");
            String ort = reader.readLine();
            adresse.setOrt(ort);
        } else if(input5 == 0){
            vertragspartner.setAdresse(adresse);
            dl.getVertragspartnerDao().create(vertragspartner);
            System.out.println("Vertragspartner erstellt:");
            System.out.println(vertragspartner.toString());
            Vertragspartner(vp, w);
        } else {
            System.out.println("Input incorrect!");
        }
        VertragspartnerNeuErstellen(vp, w, vertragspartner, adresse);
    }

    private static void VertragspartnerBearbeiten(List<IVertragspartner> vp, List<IWare> w) throws IOException {
        System.out.println("---< Vertragspartner - Bearbeiten >---");
        if(vp.size() == 0){
            System.out.println("Keine Vertragspartner vorhanden");
            Vertragspartner(vp, w);
        }else if(vp.size() >= 1) {
            for (int i = 0; i < vp.size(); i++) {
                System.out.println("[" + (i + 1) + "] " + vp.get(i).getNachname() + ", " + vp.get(i).getVorname());
            }
        }

        int input5 = Integer.parseInt(reader.readLine());
        if(input5 == 1){
            VertragspartnerBearbeiten1(vp.get(0), w);
        } else if(input5 == 2){
            VertragspartnerBearbeiten1(vp.get(1), w);
        } else {
            System.out.println("Input incorrect!");
            VertragspartnerBearbeiten(vp, w);
        }
    }

    private static void VertragspartnerBearbeiten1(IVertragspartner vp, List<IWare> w) throws IOException {
        System.out.println("---< Vertragspartner - Bearbeiten - " + vp.getNachname() + ", " + vp.getVorname() + " >---");

        VertragspartnerOptions();

        int input6 = Integer.parseInt(reader.readLine());
        if(input6 == 1){
            System.out.println("Neuer Vorname (" + vp.getVorname() + "): ");
            String vorname = reader.readLine();
            vp.setVorname(vorname);
        } else if(input6 == 2){
            System.out.println("Neuer Nachname (" + vp.getNachname() + "): ");
            String nachname = reader.readLine();
            vp.setNachname(nachname);
        } else if(input6 == 3){
            System.out.println("Neue Ausweisnummer (" + vp.getAusweisNr() + "): ");
            String ausweisnummer = reader.readLine();
            vp.setAusweisNr(ausweisnummer);
        } else if(input6 == 4){
            System.out.println("Neue Strasse (" + vp.getAdresse().getStrasse() + "): ");
            String strasse = reader.readLine();
            vp.getAdresse().setStrasse(strasse);
        } else if(input6 == 5){
            System.out.println("Neue Hausnummer (" + vp.getAdresse().getHausNr() + "): ");
            String hausnummer = reader.readLine();
            vp.getAdresse().setHausNr(hausnummer);
        } else if(input6 == 6){
            System.out.println("Neue PLZ (" + vp.getAdresse().getPlz() + "): ");
            String plz = reader.readLine();
            vp.getAdresse().setPlz(plz);
        } else if(input6 == 7){
            System.out.println("Neuer Ort (" + vp.getAdresse().getOrt() + "): ");
            String ort = reader.readLine();
            vp.getAdresse().setOrt(ort);
        } else {
            System.out.println("Invalid input");
        }
        VertragspartnerBearbeiten1(vp, w);
    }

}
