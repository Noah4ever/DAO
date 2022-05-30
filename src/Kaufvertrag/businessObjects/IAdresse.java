package Kaufvertrag.businessObjects;

public interface IAdresse {
    public String getStrasse();
    public void setStrasse(String strasse);
    public String getHausNr();
    public void setHausNr(String hausNr);
    public String getPlz();
    public void setPlz(String plz);
    public String getOrt();
    public void setOrt(String ort);
    public String toString();
}
