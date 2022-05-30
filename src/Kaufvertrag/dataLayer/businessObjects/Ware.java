package Kaufvertrag.dataLayer.businessObjects;

import Kaufvertrag.businessObjects.IWare;

import java.util.List;

public class Ware implements IWare {
    private int id;
    private String bezeichnung;
    private String beschreibung;
    private double preis;
    private List<String> besonderheiten;
    private List<String> maengel;


    public Ware(String bezeichnung, double preis) {
        this.bezeichnung = bezeichnung;
        this.preis = preis;
        this.beschreibung = "";
        this.besonderheiten = null;
        this.maengel = null;

    }

    public void setId(int id){
        this.id = id;
    }

    public String toString() {
        return bezeichnung + ": " + preis + "€" + "\nBeschreibung: " + beschreibung + "\nBesonderheiten: " + besonderheiten + "\nMängel: " + maengel;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getBezeichnung() {
        return bezeichnung;
    }
    @Override
    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    @Override
    public String getBeschreibung() {
        return beschreibung;
    }
    @Override
    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    @Override
    public double getPreis() {
        return preis;
    }
    @Override
    public void setPreis(double preis) {
        this.preis = preis;
    }

    @Override
    public List<String> getBesonderheiten() {
        return besonderheiten;
    }

    @Override
    public List<String> getMaengel() {
        return maengel;
    }
}
