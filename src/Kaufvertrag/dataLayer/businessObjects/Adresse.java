package Kaufvertrag.dataLayer.businessObjects;

import Kaufvertrag.businessObjects.IAdresse;

public class Adresse implements IAdresse {
    private String strasse;
    private String hausNr;
    private String plz;
    private String ort;

    public Adresse(String strasse, String hausNr, String plz, String ort) {
        this.strasse = strasse;
        this.hausNr = hausNr;
        this.plz = plz;
        this.ort = ort;
    }

    public String getStrasse() {
        return strasse;
    }

    @Override
    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    @Override
    public String getHausNr() {
        return hausNr;
    }

    @Override
    public void setHausNr(String hausNr) {
        this.hausNr = hausNr;
    }

    public String getPlz() {
        return plz;
    }

    @Override
    public void setPlz(String plz) {
        this.plz = plz;
    }

    public String getOrt() {
        return ort;
    }

    @Override
    public void setOrt(String ort) {
        this.ort = ort;
    }


    public String toString() {
        return "Adresse{" +
                "strasse='" + strasse + '\'' +
                ", hausNr='" + hausNr + '\'' +
                ", plz='" + plz + '\'' +
                ", ort='" + ort + '\'' +
                '}';
    }

}
