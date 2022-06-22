package Kaufvertrag.presentationLayer;

import Kaufvertrag.businessObjects.IAdresse;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

class KaufvertragDaten {
    public static List<IVertragspartner> Vertragspartner;
    public static final String VertragspartnerInputfields[] = {"Vorname", "Nachname", "Ausweisnummer", "Strasse", "Hausnummer", "PLZ", "Ort"};

    public static List<IWare> Waren;
    public static final String WareInputfields[] = {"Bezeichnung", "Beschreibung", "Preis", "Besonderheiten", "Mängel"};

}

public class Programm {
    static BufferedReader reader = new BufferedReader(new java.io.InputStreamReader(System.in));
    static IDataLayer dl = null;
    static DataLayerManager dlm = DataLayerManager.getInstance();
    static final String VertragspartnerInputFields[] = {"Vorname", "Nachname", "Ausweisnummer", "Strasse", "Hausnummer", "PLZ", "Ort"};
    static int idCounter = 0;
    static boolean hasChanged = false; // Wenn ein benutzer etwas an einem Vertragspartner oder ware ändert (Nur für aussehen)
    public static void main(String[] args) throws DaoException, IOException {
        MainMenu(); // Hauptmenü aufrufen
    }


    // Input eines Strings von der Konsole
    private static String getInput() {
        String input = "";
        System.out.print("> ");
        try {
            input = reader.readLine(); // Eingabe von der Konsole
        } catch (IOException e) {
            e.printStackTrace();
        }
        return input; // Return der Eingabe
    }


    private static void MainMenu() throws DaoException, IOException {
        System.out.println("---< Kaufvertrag >---");
        System.out.println("[1] Einlesen"); // Einlesen eines Kaufvertrags
        System.out.println("[2] Neu erstellen"); // Erstellen eines neuen Kaufvertrags
        System.out.println("[X] Beenden");
        String input = getInput();
        switch (input) {
            case "1":
                Einlesen(); // Einlesen eines Kaufvertrags
                break;
            case "2":
                NeuErstellen(); // Erstellen eines neuen Kaufvertrags
                break;
            case "x":
            case "X":
                break;
            default:
                System.out.println("[Error] Incorrect input!"); // Fehlermeldung
                MainMenu(); // Hauptmenü aufrufen
                break;
        }
    }

    private static void Einlesen() throws DaoException, IOException {
        System.out.println("---< Kaufvertrag - Einlesen >---");
        System.out.println("[1] XML"); // Einlesen eines Kaufvertrags aus XML
        System.out.println("[2] SQLite"); // Einlesen eines Kaufvertrags aus SQLite
        System.out.println("[0] Zurück"); // Zurück zum Hauptmenü
        String input = getInput();
        switch (input) {
            case "1":
                EinlesenXML(); // Einlesen eines Kaufvertrags aus XML
                break;
            case "2":
                EinlesenSQLite(); // Einlesen eines Kaufvertrags aus SQLite
                break;
            case "0":
                MainMenu(); // Zurück zum Hauptmenü
                break;
            default:
                System.out.println("[Error] Incorrect input!"); // Fehlermeldung
                Einlesen(); // Einlesen wiederholen
                break;
        }
    }

    // Einlesen eines Kaufvertrags aus XML
    private static void EinlesenXML() throws DaoException, IOException {
        dateiEinlesen("xml", true);
        Bearbeiten();
    }

    // Einlesen eines Kaufvertrags aus SQLite
    private static void EinlesenSQLite() throws DaoException, IOException {
        dateiEinlesen("sqlite", true);
        Bearbeiten();
    }

    private static void dateiEinlesen(String persistenceType, boolean verbose) throws DaoException, IOException {
        if(verbose)
            System.out.println("[Info] Read file...");
        dlm.getInstance().persistenceType = persistenceType;
        dl = dlm.getDataLayer();
        List<IVertragspartner> vp = dl.getVertragspartnerDao().read(); // Einlesen aller Vertragspartner
        List<IWare> w = dl.getWareDao().read(); // Einlesen der Waren
        KaufvertragDaten.Vertragspartner = vp; // Vertragspartner speichern
        KaufvertragDaten.Waren = w; // Waren speichern
        if(verbose)
            System.out.println("[Info] Finished reading!");
    }

    private static void Bearbeiten() throws DaoException, IOException {
        System.out.println("---< Kaufvertrag - %s >---".formatted(dlm.persistenceType.toUpperCase(Locale.ROOT)));
        System.out.println("[1] Vertragspartner"); // Bearbeiten eines Vertragspartners
        System.out.println("[2] Ware"); // Bearbeiten einer Ware
        System.out.println("[0] Zurück");
        String input = getInput();
        switch (input) {
            case "1":
                VertragspartnerBearbeitenMenu(); // Bearbeiten eines Vertragspartners
                break;
            case "2":
                WareBearbeitenMenu(); // Bearbeiten einer Ware
                break;
            case "0":
                MainMenu(); // Zurück zum Hauptmenü
                break;
            default:
                System.out.println("[Error] Incorrect input!"); // Fehlermeldung
                Bearbeiten(); // Bearbeiten wiederholen
                break;
        }
    }

    private static void VertragspartnerBearbeitenMenu() throws DaoException, IOException {
        dateiEinlesen(dlm.persistenceType, false);
        System.out.println("---< Kaufvertrag - %s - Vertragspartner >---".formatted(dlm.persistenceType.toUpperCase(Locale.ROOT)));
        System.out.println("[1] Neu erstellen"); // Erstellen eines neuen Vertragspartners
        System.out.println("[2] Bearbeiten"); // Bearbeiten eines Vertragspartners
        System.out.println("[3] Löschen"); // Löschen eines Vertragspartners
        System.out.println("[0] Zurück");
        String input = getInput();
        switch (input) {
            case "1":
                VertragspartnerNeuErstellen(); // Erstellen eines neuen Vertragspartners
                break;
            case "2":
                VertragspartnerBearbeitenList(); // Bearbeiten eines Vertragspartners
                break;
            case "3":
                VertragspartnerLoeschen(); // Löschen eines Vertragspartners
                break;
            case "0":
                Bearbeiten(); // Zurück zum Hauptmenü
                break;
            default:
                System.out.println("[Error] Incorrect input!"); // Fehlermeldung
                VertragspartnerBearbeitenMenu(); // Bearbeiten wiederholen
                break;
        }
    }

    private static void VertragspartnerNeuErstellen() throws IOException, DaoException {
        System.out.println("---< Kaufvertrag - %s - Vertragspartner - Neu erstellen >---".formatted(dlm.persistenceType.toUpperCase(Locale.ROOT)));
        String[] inputfields = KaufvertragDaten.VertragspartnerInputfields; // Input felder zum erstellen eines Vertragspartners (Vorname, Nachname, AusweisNr, Strasse, ...)
        List<String> input = getFastInput(inputfields);
        // TODO: Modular machen. Nicht get (index) sondern von VertragspartnerInputfields getIndex "Vorname" -> newVp Vorname
        IVertragspartner newVp = new Vertragspartner(input.get(0), input.get(1), input.get(2)); // Erstellen eines neuen Vertragspartners
        IAdresse newA = new Adresse(input.get(3), input.get(4), input.get(5), input.get(6)); // Erstellen einer neuen Adresse
        newVp.setAdresse(newA); // Adresse zum Vertragspartner hinzufügen
        newVp.setId(idCounter);
        idCounter++;
        System.out.println(newVp.toString());
        dl.getVertragspartnerDao().create(newVp); // Vertragspartner zum Kaufvertrag hinzufügen
        VertragspartnerBearbeitenMenu();
    }

    private static List<String> getFastInput(String[] inputFields) {
        List<String> input = new ArrayList<>(); // Liste für die Eingabe
        for(int counter = 0; counter < inputFields.length; counter++) {
            System.out.print("(%d/%d) ".formatted(counter+1, inputFields.length) + inputFields[counter] + " "); // Ausgabe der Eingabefelder
            input.add(getInput()); // Einlesen und speichern der Eingaben
        }
        return input; // Liste mit Eingaben zurückgeben
    }

    private static void VertragspartnerBearbeitenList() throws DaoException, IOException {
        hasChanged = false;
        System.out.println("---< Kaufvertrag - %s - Vertragspartner - Bearbeiten >---".formatted(dlm.persistenceType.toUpperCase(Locale.ROOT)));

        for(int i = 0; i < KaufvertragDaten.Vertragspartner.size(); i++) {
            IVertragspartner vp = KaufvertragDaten.Vertragspartner.get(i); // Vertragspartner aus Liste holen
            IAdresse a = vp.getAdresse(); // Adresse des Vertragspartners aus Liste holen
            System.out.println("[%d] %s, %s, %s, %s, %s, %s, %s".formatted(i+1, vp.getVorname(), vp.getNachname(), vp.getAusweisNr(), a.getStrasse(), a.getHausNr(), a.getPlz(), a.getOrt())); // Ausgabe der Vertragspartner
        }
        System.out.println("[0] Zurück");
        String input = getInput();
        switch (input) {
            case "1":
            case "2":
                VertragspartnerBearbeiten(KaufvertragDaten.Vertragspartner.get(Integer.parseInt(input)-1));
                break;
            case "0":
                VertragspartnerBearbeitenMenu(); // Zurück zum Hauptmenü
                break;
            default:
                System.out.println("[Error] Incorrect input!"); // Fehlermeldung
                VertragspartnerBearbeitenList(); // Bearbeiten wiederholen
                break;
        }
    }

    

    private static void VertragspartnerBearbeiten(IVertragspartner vp) throws DaoException, IOException {
        IAdresse a = vp.getAdresse();
        System.out.println("---< Kaufvertrag - %s - Vertragspartner - Bearbeiten - %s, %s >---".formatted(dlm.persistenceType.toUpperCase(Locale.ROOT), vp.getVorname(), vp.getNachname()));
        System.out.println("VP: " + vp.toString());
        System.out.println("ID: " + vp.getId());
        String[] inputfields = KaufvertragDaten.VertragspartnerInputfields;
        String[] vpData = {vp.getVorname(), vp.getNachname(), vp.getAusweisNr(), a.getStrasse(), a.getHausNr(), a.getPlz(), a.getOrt()};
        for(int counter = 0; counter < inputfields.length; counter++){
            System.out.println("[%d] %s (%s)".formatted((counter+1), inputfields[counter], vpData[counter]));
        }
        System.out.print("[0] Zurück");
        if(hasChanged) {
            System.out.print(" / Save");
        }
        System.out.println();

        String input = getInput();
        switch (input) {
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
                hasChanged = true;
                int numberInput = Integer.parseInt(input);
                System.out.print("%s (%s): ".formatted(inputfields[numberInput-1], vpData[numberInput-1]));
                String updatedInput = getInput();
                switch (inputfields[numberInput-1]){
                    case "Vorname": vp.setVorname(updatedInput); break;
                    case "Nachname": vp.setNachname(updatedInput); break;
                    case "Ausweisnummer": vp.setAusweisNr(updatedInput); break;
                    case "Strasse": vp.getAdresse().setStrasse(updatedInput); break;
                    case "Hausnummer": vp.getAdresse().setHausNr(updatedInput); break;
                    case "PLZ": vp.getAdresse().setPlz(updatedInput); break;
                    case "Ort": vp.getAdresse().setOrt(updatedInput); break;
                }
                VertragspartnerBearbeiten(vp);
                break;
            case "0":
                System.out.println("VP: " + vp.toString());
                System.out.println("ID: " + vp.getId());
                dl.getVertragspartnerDao().update(vp);
                VertragspartnerBearbeitenList(); // Zurück zum Hauptmenü
                break;
            default:
                System.out.println("Incorrect input!"); // Fehlermeldung
                VertragspartnerBearbeiten(vp); // Bearbeiten wiederholen
                break;
        }

    }

    private static void VertragspartnerLoeschen() {

    }


    private static void WareBearbeitenMenu() {
    }

    private static void NeuErstellen() {
        System.out.println("---< Kaufvertrag - Neu erstellen >---");
        System.out.println("[1] XML"); // Erstellen eines neuen Kaufvertrags mit XML
        System.out.println("[2] SQLite"); // Erstellen eines neuen Kaufvertrags mit SQLite
        System.out.println("[0] Zurück"); // Zurück zum Hauptmenü

    }

}
