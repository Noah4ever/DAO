package Kaufvertrag.dataLayer.businessObjects;

import Kaufvertrag.businessObjects.IAdresse;
import Kaufvertrag.businessObjects.IVertragspartner;

import java.util.Arrays;

public class Vertragspartner implements IVertragspartner {
    private int id;
    private String vorname;
    private String nachname;
    private String ausweisNr;
    private IAdresse adresse;
    private String array;

    public Vertragspartner(String vorname, String nachname) {
        this.vorname = vorname;
        this.nachname = nachname;
    }
    public Vertragspartner(String vorname, String nachname, String ausweisNr) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.ausweisNr = ausweisNr;
    }


    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id){
        this.id = id;
    }

    @Override
    public String getVorname() {
        return vorname;
    }

    @Override
    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    @Override
    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    @Override
    public String getNachname() {
        return nachname;
    }

    @Override
    public String getAusweisNr() {
        return ausweisNr;
    }

    @Override
    public void setAusweisNr(String ausweisNr) {
        this.ausweisNr = ausweisNr;
    }

    @Override
    public IAdresse getAdresse() {
        return adresse;
    }

    @Override
    public void setAdresse(IAdresse adresse) {
        this.adresse = adresse;
    }

    public String toString() {
        return "Vertragspartner{vorname='%s', nachname='%s', ausweisNr='%s', \n".formatted(vorname, nachname, ausweisNr) + adresse.toString() + "}";
    }

}
