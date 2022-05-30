package Kaufvertrag.dataLayer.businessObjects;

import Kaufvertrag.businessObjects.IAdresse;
import Kaufvertrag.businessObjects.IVertragspartner;

public class Vertragspartner implements IVertragspartner {
    private String vorname;
    private String nachname;
    private String ausweisNr;
    private IAdresse adresse;

    public Vertragspartner(String vorname, String nachname) {
        this.vorname = vorname;
        this.nachname = nachname;
    }


    @Override
    public int getId() {
        return 0;
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
}
