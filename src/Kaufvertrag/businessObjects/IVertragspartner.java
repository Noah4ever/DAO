package Kaufvertrag.businessObjects;

import Kaufvertrag.dataLayer.businessObjects.Adresse;

public interface IVertragspartner {
    public int getId();
    public void setId(int id);
    public String getVorname();
    public void setVorname(String vorname);
    public void setNachname(String nachname);
    public String getNachname();
    public String getAusweisNr();
    public void setAusweisNr(String ausweisNr);
    public IAdresse getAdresse();
    public void setAdresse(IAdresse adresse);

}
