package Kaufvertrag.dataLayer.dataAccessObjects.XML;

import Kaufvertrag.businessObjects.IAdresse;
import Kaufvertrag.businessObjects.IVertragspartner;
import Kaufvertrag.businessObjects.IWare;
import Kaufvertrag.dataLayer.businessObjects.Adresse;
import Kaufvertrag.dataLayer.businessObjects.Vertragspartner;
import Kaufvertrag.dataLayer.businessObjects.Ware;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.SAXOutputter;
import org.jdom2.output.XMLOutputter;

import java.io.Console;
import java.io.File;
import java.io.ObjectInputStream;
import java.util.List;

public class ServiceXml {
    private Element root;

    public ServiceXml(String path){
        /*String xmlFile = "D:\\.Downloads\\Programming\\IntelliJ\\projects\\DAO\\DAO\\src\\Kaufvertrag\\files\\test.xml";*/
        String xmlFile = path;
        File file = new File(xmlFile);
        try {
            SAXBuilder saxBuilder = new SAXBuilder(); // create a SAX builder
            Document document = saxBuilder.build(file); // parse the file
            Element root = document.getRootElement(); // get the root element
            this.root = root;
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //

    public List<IWare> Ware(){
        List<IWare> wareList = null;
        List<Element> wData = root.getChildren("Ware"); // Finds the first child element called "Ware"
        for (Element w : wData) {

            String id = w.getChildText("id");

            String bezeichnung = w.getChildText("bezeichnung");
            String beschreibung = w.getChildText("beschreibung");
            String preis = w.getChildText("preis");
            String besonderheiten = w.getChildText("besonderheiten");
            String maengel = w.getChildText("maengel");

            if( bezeichnung != null && preis != null && isNumeric(preis)){


                IWare ware = null;
                if(beschreibung != null){
                    ware.setBeschreibung(beschreibung);
                }
                if(besonderheiten != null) {
                    ware.getBesonderheiten().add(besonderheiten);
                }
                if(maengel != null) {
                    ware.getMaengel().add(maengel);
                }

                wareList.add(ware);
            }
        }
        return wareList;
    }

    public List<IVertragspartner>Vertragspartner(){
        List<Element> list = root.getChildren("Vertragspartner"); // Finds all children elements called "Vertragspartner"
        List<IVertragspartner> vertragspartnerList = null;
        for (Element vertragspartner : list) { // iterates through all children of the root element
            String id = vertragspartner.getChildText("id");
            String vorname = vertragspartner.getChildText("vorname");
            String nachname = vertragspartner.getChildText("nachname");
            String ausweisnummer = vertragspartner.getChildText("ausweisnummer");
            Element adresseElement = vertragspartner.getChildren("adresse").get(0);
            IAdresse adresse;
            if(adresseElement != null){ // if the element is not null
                String strasse = adresseElement.getChildText("strasse");
                String hausNr = adresseElement.getChildText("hausNr");
                String plz = adresseElement.getChildText("plz");
                String ort = adresseElement.getChildText("ort");
                adresse = new Adresse(strasse,hausNr,plz,ort); // create a new Adresse object
            }else{
                adresse = null; // if the element is null
            }
            IVertragspartner vertragspartner1 = new Vertragspartner(vorname, nachname); // create a new Vertragspartner object
            vertragspartner1.setAdresse(adresse); // set the adresse of the Vertragspartner object
            vertragspartner1.setAusweisNr(ausweisnummer); // set the ausweisnummer of the Vertragspartner object
            vertragspartnerList.add(vertragspartner1); // add the Vertragspartner object to the list

        }
        return vertragspartnerList; // return the list
    }

    private boolean isNumeric(String str) {
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

}
