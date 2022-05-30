package Kaufvertrag.dataLayer.businessObjects;

public class Adresse {
    private String strasse;
    private String hausnummer;
    private String plz;
    private String ort;
    private String land;

    public Adresse(String strasse, String hausnummer, String plz, String ort, String land) {
        this.strasse = strasse;
        this.hausnummer = hausnummer;
        this.plz = plz;
        this.ort = ort;
        this.land = land;
    }

    public String getStrasse() {
        return strasse;
    }

    public String getHausnummer() {
        return hausnummer;
    }

    public String getPlz() {
        return plz;
    }

    public String getOrt() {
        return ort;
    }

    public String getLand() {
        return land;
    }

    public String toString() {
        return strasse + " " + hausnummer + ", " + plz + " " + ort + ", " + land;
    }
}
