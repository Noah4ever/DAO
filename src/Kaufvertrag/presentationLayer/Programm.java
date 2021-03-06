package Kaufvertrag.presentationLayer;

import Kaufvertrag.businessObjects.IAdresse;
import Kaufvertrag.businessObjects.IVertragspartner;
import Kaufvertrag.businessObjects.IWare;
import Kaufvertrag.dataLayer.businessObjects.Adresse;
import Kaufvertrag.dataLayer.businessObjects.Kaufvertrag;
import Kaufvertrag.dataLayer.businessObjects.Vertragspartner;
import Kaufvertrag.dataLayer.businessObjects.Ware;
import Kaufvertrag.dataLayer.dataAccessObjects.DataLayerManager;
import Kaufvertrag.dataLayer.dataAccessObjects.IDataLayer;
import Kaufvertrag.dataLayer.dataAccessObjects.XML.DataLayerXml;
import Kaufvertrag.exceptions.DaoException;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

class KaufvertragDaten {
    public static List<IVertragspartner> Vertragspartner;
    public static final String VertragspartnerInputfields[] = {"Vorname", "Nachname", "Ausweisnummer", "Strasse", "Hausnummer", "PLZ", "Ort"};

    public static List<IWare> Waren;
    public static final String WareInputfields[] = {"Bezeichnung", "Preis (Euro)", "Beschreibung", "Besonderheiten (Komma getrennte Aufzählung)", "Mängel (Komma getrennte Aufzählung)"};

}

public class Programm {
    static BufferedReader reader = new BufferedReader(new java.io.InputStreamReader(System.in));
    static IDataLayer dl = null;
    static DataLayerManager dlm = DataLayerManager.getInstance();
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
        System.out.println("[1] Neu Erstellen / Einlesen"); // Einlesen eines Kaufvertrags
        System.out.println("[X] Beenden");
        String input = getInput();
        switch (input) {
            case "1":
                Einlesen(); // Einlesen eines Kaufvertrags
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
            System.out.println("[Info] Reading...");
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
        System.out.println("[3] Ausgabe"); // Ausgabe des Kaufvertrags
        System.out.println("[0] Zurück");
        String input = getInput();
        switch (input) {
            case "1":
                VertragspartnerBearbeitenMenu(); // Bearbeiten eines Vertragspartners
                break;
            case "2":
                WareBearbeitenMenu(); // Bearbeiten einer Ware
                break;
            case "3":
                Ausgabe(); // Ausgabe des Kaufvertrags
                break;
            case "0":
                Einlesen(); // Zurück zum Hauptmenü
                break;
            default:
                System.out.println("[Error] Incorrect input!"); // Fehlermeldung
                Bearbeiten(); // Bearbeiten wiederholen
                break;
        }
    }

    private static void Ausgabe() throws IOException, DaoException {
        dateiEinlesen(dlm.persistenceType, false);
        System.out.println("---< Kaufvertrag - %s - Ausgabe >---\n".formatted(dlm.persistenceType.toUpperCase(Locale.ROOT)));

        String[] vHeadingText = {"Vorname", "Nachname", "Ausweisnummer", "Strasse", "Hausnummer", "PLZ", "Ort"};
        int[] headingSpaces = getMaxLengths(vHeadingText, true);

        int[] colLength = new int[headingSpaces.length]; // Spalten breite

        getHorizontalLine(vHeadingText, headingSpaces); // Horizontale Linie mit korrekten Abständen
        for(int counter = 0; counter < vHeadingText.length; counter++){ // Heading Text mit korrekten Leerzeichen
            int spaceCount = headingSpaces[counter] - vHeadingText[counter].length();
            colLength[counter] = vHeadingText[counter].length() + (spaceCount > 0 ? spaceCount : 0);
            System.out.format("| %s%s ", vHeadingText[counter], getStringRepeat(" ", spaceCount));
        }
        System.out.println("|"); // Letztes "|" mit "\n" danach
        getHorizontalLine(vHeadingText, headingSpaces); // Horizontale Linie mit korrekten Abständen

        String leftAlignFormatVertragspartner = "";
        for(int counter = 0; counter < vHeadingText.length; counter++){
            leftAlignFormatVertragspartner += "| %-" + colLength[counter] + "s ";
        }
        leftAlignFormatVertragspartner += "|%n";
        for(IVertragspartner v : KaufvertragDaten.Vertragspartner){
            IAdresse a = v.getAdresse();
            System.out.format(leftAlignFormatVertragspartner, v.getVorname(), v.getNachname(), v.getAusweisNr(), a.getStrasse(), a.getHausNr(), a.getPlz(), a.getOrt());
        }
        if(KaufvertragDaten.Vertragspartner.size() == 0){ // Wenn kein Vertragspartner vorhanden ist
            System.out.format(leftAlignFormatVertragspartner, "-", "-", "-", "-", "-", "-", "-");
        }
        getHorizontalLine(vHeadingText, headingSpaces); // Horizontale Linie mit korrekten Abständen


        System.out.println(""); // New line --------------------


        String[] wHeadingText = {"Bezeichnung", "Preis", "Beschreibung", "Besonderheiten", "Mängel"};
        headingSpaces = getMaxLengths(wHeadingText, false);

        colLength = new int[headingSpaces.length]; // Spalten breite

        getHorizontalLine(wHeadingText, headingSpaces); // Horizontale Linie mit korrekten Abständen
        for(int counter = 0; counter < wHeadingText.length; counter++){ // Heading Text mit korrekten Leerzeichen
            int spaceCount = headingSpaces[counter] - wHeadingText[counter].length();
            colLength[counter] = wHeadingText[counter].length() + (spaceCount > 0 ? spaceCount : 0);
            System.out.format("| %s%s ", wHeadingText[counter], getStringRepeat(" ", spaceCount));
        }
        System.out.println("|"); // Letztes "|" mit "\n" danach
        getHorizontalLine(wHeadingText, headingSpaces); // Horizontale Linie mit korrekten Abständen

        String leftAlignFormatWare = "";
        for(int counter = 0; counter < wHeadingText.length; counter++){
            leftAlignFormatWare += "| %-" + colLength[counter] + "s ";
        }
        leftAlignFormatWare += "|%n";
        for(IWare w : KaufvertragDaten.Waren){
            System.out.format(leftAlignFormatWare, w.getBezeichnung(), w.getPreis(), w.getBeschreibung(), String.join(",", w.getBesonderheiten()), String.join(",", w.getMaengel()));
        }
        if(KaufvertragDaten.Waren.size() == 0){ // Wenn kein Vertragspartner vorhanden ist
            System.out.format(leftAlignFormatWare, "-", "-", "-", "-", "-");
        }
        getHorizontalLine(wHeadingText, headingSpaces); // Horizontale Linie mit korrekten Abständen

        System.out.print("> ");
        reader.readLine(); // Eingabe von der Konsole
        Bearbeiten();
    }

    private static void getHorizontalLine(String[] vHeadingText, int[] headingSpaces) {
        for(int counter = 0; counter < vHeadingText.length; counter++){
            int spaceCount = headingSpaces[counter] - vHeadingText[counter].length();
            System.out.format("+%s--", getStringRepeat("-", vHeadingText[counter].length() + (spaceCount > 0 ? spaceCount : 0)));
        }
        System.out.println("+");
    }

    private static String getStringRepeat(String str, int count){
        if(count < 0){ // If count smaller than 0
            return "";
        }
        return str.repeat(count);
    }

    private static int[] getMaxLengths(String[] headingText, boolean vertragspartner){
        int[] maxLengths = new int[headingText.length];

        List<String[]> vData = new ArrayList<>();
        if(vertragspartner) {
            for (IVertragspartner v : KaufvertragDaten.Vertragspartner) {
                IAdresse a = v.getAdresse();
                vData.add(new String[]{v.getVorname(), v.getNachname(), v.getAusweisNr(), a.getStrasse(), a.getHausNr(), a.getPlz(), a.getOrt()});
            }
        }else{
            for(IWare w : KaufvertragDaten.Waren){
                vData.add(new String[]{w.getBezeichnung(), String.valueOf(w.getPreis()), w.getBeschreibung(), String.join(",", w.getBesonderheiten()), String.join(",", w.getMaengel())});
            }
        }

        for(int counter = 0; counter < headingText.length; counter++){
            int vMax = 0;
            if(vData.size() == 2){
                vMax = Math.max(vData.get(0)[counter].length(), vData.get(1)[counter].length());
            }else if(vData.size() == 1){
                vMax = vData.get(0)[counter].length();
            }
            maxLengths[counter] = vMax;
        }

        return maxLengths;
    }

    private static void VertragspartnerBearbeitenMenu() throws DaoException, IOException {
        dateiEinlesen(dlm.persistenceType, false);
        System.out.println("---< Kaufvertrag - %s - Vertragspartner >---".formatted(dlm.persistenceType.toUpperCase(Locale.ROOT)));
        System.out.println("[1] Neu erstellen"); // Erstellen eines neuen Vertragspartners
        System.out.println("[2] Bearbeiten"); // Bearbeiten eines Vertragspartners
        System.out.println("[0] Zurück");
        String input = getInput();
        switch (input) {
            case "1":
                VertragspartnerNeuErstellen(); // Erstellen eines neuen Vertragspartners
                break;
            case "2":
                VertragspartnerBearbeitenList(); // Bearbeiten eines Vertragspartners
                break;
            case "0":
                Bearbeiten(); // Zurück zum Bearbeiten
                break;
            default:
                System.out.println("[Error] Incorrect input!"); // Fehlermeldung
                VertragspartnerBearbeitenMenu(); // Bearbeiten wiederholen
                break;
        }
    }

    private static void VertragspartnerNeuErstellen() throws IOException, DaoException {
        System.out.println("---< Kaufvertrag - %s - Vertragspartner - Neu erstellen >---".formatted(dlm.persistenceType.toUpperCase(Locale.ROOT))); // Heading

        String[] inputfields = KaufvertragDaten.VertragspartnerInputfields; // Input felder zum erstellen eines Vertragspartners (Vorname, Nachname, AusweisNr, Strasse, ...)
        List<String> input = getFastInput(inputfields); // Eingabe der Inputfelder List = [Vorname, Nachname, AusweisNr, Strasse, ...]

        // TODO: Modular machen. Nicht .get(index) sondern von VertragspartnerInputfields getIndex "Vorname" -> newVp Vorname
        // 0 = Vorname, 1 = Nachname, 2 = AusweisNr --- 3 = Strasse, 4 = Hausnummer, 5 = PLZ, 6 = Ort
        IVertragspartner newVp = new Vertragspartner(input.get(0), input.get(1), input.get(2)); // Erstellen eines neuen Vertragspartners
        IAdresse newA = new Adresse(input.get(3), input.get(4), input.get(5), input.get(6)); // Erstellen einer neuen Adresse

        newVp.setAdresse(newA); // Adresse zum Vertragspartner hinzufügen

        newVp.setId(idCounter); // Set id to new id counter (So that it is unique)
        idCounter++; // Increment id counter

        dl.getVertragspartnerDao().create(newVp); // Vertragspartner zum Kaufvertrag hinzufügen
        VertragspartnerBearbeitenMenu(); // Zurück zum Vertragspartner-Bearbeiten-Menü
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
        dateiEinlesen(dlm.persistenceType, false); // Einlesen der Daten
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
                VertragspartnerBearbeitenMenu(); // Zurück zum Vertragspartner Bearbeiten Menu
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
        String[] inputfields = KaufvertragDaten.VertragspartnerInputfields;
        String[] vpData = {vp.getVorname(), vp.getNachname(), vp.getAusweisNr(), a.getStrasse(), a.getHausNr(), a.getPlz(), a.getOrt()};
        for(int counter = 0; counter < inputfields.length; counter++){
            System.out.println("[%d] %s (%s)".formatted((counter+1), inputfields[counter], vpData[counter]));
        }
        System.out.println("[Delete] Löschen");
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
            case "delete":
            case "Delete":
                dl.getVertragspartnerDao().delete(vp.getId());
                VertragspartnerBearbeitenList(); // Zurück zum Vertragspartner Bearbeiten Liste
                break;
            case "0":
                dl.getVertragspartnerDao().update(vp);
                VertragspartnerBearbeitenList(); // Zurück zum Vertragspartner Bearbeiten Liste
                break;
            default:
                System.out.println("Incorrect input!"); // Fehlermeldung
                VertragspartnerBearbeiten(vp); // Bearbeiten wiederholen
                break;
        }

    }

    private static void WareBearbeitenMenu() throws IOException, DaoException {
        dateiEinlesen(dlm.persistenceType, false); // Einlesen der Daten
        System.out.println("---< Kaufvertrag - %s - Ware >---".formatted(dlm.persistenceType.toUpperCase(Locale.ROOT)));
        System.out.println("[1] Neue erstellen");
        System.out.println("[2] Bearbeiten");
        System.out.println("[0] Zurück");
        String input = getInput();
        switch (input) {
            case "1":
                WareNeuErstellen();
                break;
            case "2":
                WareBearbeitenList();
                break;
            case "0":
                Bearbeiten();
                break;
            default:
                System.out.println("Incorrect input!"); // Fehlermeldung
                WareBearbeitenMenu(); // Bearbeiten wiederholen
                break;
        }
    }

    private static void WareBearbeitenList() throws IOException, DaoException {
        dateiEinlesen(dlm.persistenceType, false); // Einlesen der Daten
        hasChanged = false;
        System.out.println("---< Kaufvertrag - %s - Ware - Bearbeiten >---".formatted(dlm.persistenceType.toUpperCase(Locale.ROOT)));
        for(int i = 0; i < KaufvertragDaten.Waren.size(); i++) {
            IWare w = KaufvertragDaten.Waren.get(i); // Ware aus Liste holen
            System.out.println("[%d] %s, %s, %s, %s, %s".formatted(i + 1, w.getBezeichnung(), w.getPreis(), w.getBeschreibung(), w.getBesonderheiten(), w.getMaengel())); // Ausgabe der Waren
        }
        System.out.println("[0] Zurück");
        String input = getInput();
        switch (input) {
            case "1":
                WareBearbeiten(KaufvertragDaten.Waren.get(Integer.parseInt(input)-1));
                break;
            case "0":
                WareBearbeitenMenu(); // Zurück zum Ware-Bearbeiten-Menu
                break;
            default:
                System.out.println("Incorrect input!"); // Fehlermeldung
                WareBearbeitenList(); // Bearbeiten wiederholen
                break;
        }
    }

    private static void WareBearbeiten(IWare ware) throws IOException, DaoException {
        System.out.println("---< Kaufvertrag - %s - Ware - Bearbeiten - %s >---".formatted(dlm.persistenceType.toUpperCase(Locale.ROOT), ware.getBezeichnung()));
        String[] inputfields = KaufvertragDaten.WareInputfields;
        String[] wData = {ware.getBezeichnung(), String.valueOf(ware.getPreis()), ware.getBeschreibung(), String.join(",", ware.getBesonderheiten()), String.join(",", ware.getMaengel())};
        for(int counter = 0; counter < inputfields.length; counter++){
            System.out.println("[%d] %s (%s)".formatted((counter+1), inputfields[counter], wData[counter]));
        }
        System.out.println("[Delete] Löschen");
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
                hasChanged = true;
                int numberInput = Integer.parseInt(input);
                System.out.print("%s (%s): ".formatted(inputfields[numberInput-1], wData[numberInput-1]));
                String updatedInput = getInput();

                switch (inputfields[numberInput-1]){
                    case "Bezeichnung": ware.setBezeichnung(updatedInput); break;
                    case "Preis (Euro)": {
                        if (isNumeric(updatedInput)) {
                            ware.setPreis(Double.parseDouble(updatedInput));
                        } else {
                            System.out.println("[Error] Preis muss eine Zahl sein! Tip: (, -> .)");
                        }
                        break;
                    }
                    case "Beschreibung": ware.setBeschreibung(updatedInput); break;
                    case "Besonderheiten (Komma getrennte Aufzählung)": ware.getBesonderheiten().clear(); ware.getBesonderheiten().addAll(Arrays.asList(updatedInput.split(","))); break;
                    case "Mängel (Komma getrennte Aufzählung)": ware.getMaengel().clear(); ware.getMaengel().addAll(Arrays.asList(updatedInput.split(","))); break;
                }
                WareBearbeiten(ware);
                break;
            case "delete":
            case "Delete":
                dl.getWareDao().delete(ware.getId()); // Löschen eines Vertragspartners
                WareBearbeitenList(); // Zurück zum Vertragspartner Bearbeiten Liste
                break;
            case "0":
                dl.getWareDao().update(ware);
                WareBearbeitenList(); // Zurück zum Vertragspartner Bearbeiten Liste
                break;
            default:
                System.out.println("Incorrect input!"); // Fehlermeldung
                WareBearbeiten(ware); // Bearbeiten wiederholen
                break;
        }
    }

    private static void WareNeuErstellen() throws IOException, DaoException {
        dateiEinlesen(dlm.persistenceType, false); // Einlesen der Daten
        System.out.println("---< Kaufvertrag - %s - Ware - Neu erstellen >---".formatted(dlm.persistenceType.toUpperCase(Locale.ROOT))); // Heading

        String[] inputfields = KaufvertragDaten.WareInputfields; // Input felder zum erstellen eines Vertragspartners (Bezeichnung, Preis, Beschreibung, Besonderheiten, ...)
        List<String> input = new ArrayList<>();
        do {
            input = getFastInput(inputfields); // Eingabe der Inputfelder List = [Bezeichnung, Preis, Beschreibung, Besonderheiten, ...]
            if(!isNumeric(input.get(1))){
                System.out.println("[Error] Preis muss eine Zahl sein! Tip: (',' -> '.')");
            }
        }while(!isNumeric(input.get(1))); // Prüfung, ob der Preis eine Zahl ist

        IWare newW = new Ware(input.get(0), Double.parseDouble(input.get(1))); // Ware erstellen
        newW.setBeschreibung(input.get(2));
        // input.get(3) comma separated list of besonderheiten
        String[] besonderheiten = input.get(3).split(",");
        for(String besonderheit : besonderheiten) {
            newW.getBesonderheiten().add(besonderheit.trim());
        }
        // input.get(4) comma separated list of maengel
        String[] maengel = input.get(4).split(",");
        for(String maengelString : maengel) {
            newW.getMaengel().add(maengelString.trim());
        }

        newW.setId(idCounter); // Set id to new id counter (So that it is unique)
        idCounter++; // Increment id counter

        dl.getWareDao().create(newW); // Ware speichern
        WareBearbeitenMenu(); // Zurück zum Vertragspartner-Bearbeiten-Menü
    }

    private static boolean isNumeric(String str) {
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

}
