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
import org.jdom2.output.Format;
import org.jdom2.output.SAXOutputter;
import org.jdom2.output.XMLOutputter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceXml {
    private Element root;
    private Document document;
    private String pathname;

    private String baseDir = "src\\Kaufvertrag\\save\\";

    public ServiceXml(String filename){
        pathname = baseDir + filename;
        String xmlFile = pathname;
        // Check if file exists and create if not

        File file = new File(xmlFile);
        if(!file.exists()){
            try {
                file.createNewFile();
                SAXBuilder builder = new SAXBuilder();
                document = (Document) builder.build(new StringReader("<Kaufvertrag></Kaufvertrag>"));
                root = document.getRootElement();
                FileOutputStream fos = new FileOutputStream(pathname);
                XMLOutputter xmlOutput = new XMLOutputter(Format.getPrettyFormat());
                xmlOutput.output(document, fos);

            } catch (IOException | JDOMException e) {
                e.printStackTrace();
            }
        } else {
            // Read file
            try {
                SAXBuilder builder = new SAXBuilder();
                document = (Document) builder.build(new File(xmlFile));
                root = document.getRootElement();
            } catch (JDOMException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateVertragspartner(IVertragspartner vertragspartner) throws IOException {
        FileOutputStream fos = new FileOutputStream(pathname);
        List<Element> vertragspartnerList = root.getChildren("Vertragspartner"); // get the list of vertragspartner
        for(Element v : vertragspartnerList) { // iterate through the list
            if (v.getAttributeValue("id").equals(String.valueOf(vertragspartner.getId()))) { // if the id is the same as the id of the vertragspartner
                v.setAttribute("id", String.valueOf(vertragspartner.getId()));
                v.setAttribute("vorname", vertragspartner.getVorname());
                v.setAttribute("nachname", vertragspartner.getNachname());

                Element adresse = v.getChild("Adresse"); // get the adresse element
                adresse.setAttribute("strasse", vertragspartner.getAdresse().getStrasse());
                adresse.setAttribute("hausNr", String.valueOf(vertragspartner.getAdresse().getHausNr()));
                adresse.setAttribute("plz", String.valueOf(vertragspartner.getAdresse().getPlz()));
                adresse.setAttribute("ort", vertragspartner.getAdresse().getOrt());
            }
        }

        XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
        xmlOutputter.output(document, fos);
        fos.close();
    }



    public void updateWare(IWare ware) throws IOException {
        FileOutputStream fos = new FileOutputStream(pathname);

        Element wareElement = root.getChild("Ware");
        wareElement.removeContent();
        wareElement.addContent(new Element("id").setText(String.valueOf(ware.getId())));
        wareElement.addContent(new Element("bezeichnung").setText(String.valueOf(ware.getBezeichnung())));
        wareElement.addContent(new Element("beschreibung").setText(String.valueOf(ware.getBeschreibung())));
        wareElement.addContent(new Element("preis").setText(String.valueOf(ware.getPreis())));
        XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
        xmlOutputter.output(document, fos);
        fos.close();
    }

    public IWare createWare() throws IOException {
        FileOutputStream fos = new FileOutputStream(pathname);
        if(root.getChild("Ware") == null) {
            Element ware = new Element("Ware");
            root.addContent(ware);
        }
        XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
        xmlOutputter.output(document, fos);
        fos.close();
        return new Ware("",  0);
    }

    public IWare createWareElement(IWare ware) throws IOException {
        FileOutputStream fos = new FileOutputStream(pathname);
        String bezeichnung = ware.getBezeichnung();
        String beschreibung = ware.getBeschreibung();
        double preis = ware.getPreis();
        if(root.getChild("Ware") == null) {
            Element wareElement = new Element("Ware");
            wareElement.addContent(new Element("id").setText(String.valueOf(ware.getId())));
            wareElement.addContent(new Element("bezeichnung").setText(bezeichnung));
            wareElement.addContent(new Element("beschreibung").setText(beschreibung));
            wareElement.addContent(new Element("preis").setText(String.valueOf(preis)));
            root.addContent(wareElement);
        }
        Ware newWare = new Ware(bezeichnung, preis);
        newWare.setBeschreibung(beschreibung);

        XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
        xmlOutputter.output(document, fos);
        fos.close();
        return newWare;
    }


    public IVertragspartner createVertragspartner() throws IOException {
        FileOutputStream fos = new FileOutputStream(pathname);
        if(root.getChildren("Vertragspartner").size() < 2) {
            Element vertragspartner = new Element("Vertragspartner");
            root.addContent(vertragspartner);
        }

        XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
        xmlOutputter.output(document, fos);
        fos.close();
        return new Vertragspartner("",  "");
    }

    public IVertragspartner createVertragspartnerElement(IVertragspartner vertragspartner) throws IOException {
        FileOutputStream fos = new FileOutputStream(pathname);
        String vorname = vertragspartner.getVorname();
        String nachmane = vertragspartner.getNachname();
        IAdresse adresse = vertragspartner.getAdresse();
        String  ausweisnummer = vertragspartner.getAusweisNr();
        if(root.getChildren("Vertragspartner").size() < 2) {
            Element vertragspartnerElement = new Element("Vertragspartner");// set the attributes
            vertragspartnerElement.addContent(new Element("id").setText(String.valueOf(vertragspartner.getId())));
            vertragspartnerElement.addContent(new Element("vorname").setText(vorname));
            vertragspartnerElement.addContent(new Element("nachname").setText(nachmane));
            vertragspartnerElement.addContent(new Element("ausweisNr").setText(ausweisnummer));
            Element adresseElement = new Element("Adresse");
            adresseElement.addContent(new Element("strasse").setText(adresse.getStrasse()));
            adresseElement.addContent(new Element("hausNr").setText(String.valueOf(adresse.getHausNr())));
            adresseElement.addContent(new Element("plz").setText(String.valueOf(adresse.getPlz())));
            adresseElement.addContent(new Element("ort").setText(adresse.getOrt()));
            vertragspartnerElement.addContent(adresseElement);
            root.addContent(vertragspartnerElement);
        }
        Vertragspartner newVertragspartner = new Vertragspartner(vorname, nachmane);
        newVertragspartner.setAusweisNr(ausweisnummer);
        newVertragspartner.setAdresse(adresse);


        XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
        xmlOutputter.output(document, fos);
        fos.close();
        return newVertragspartner;
    }

    public void deleteVertragspartner(int id) throws IOException {
        FileOutputStream fos = new FileOutputStream(pathname);
        Element vertragspartnerElement = root.getChild("Vertragspartner");
        vertragspartnerElement.removeContent();
        XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
        xmlOutputter.output(document, fos);
        fos.close();
    }

    public void deleteWare(int id) throws IOException {
        FileOutputStream fos = new FileOutputStream(pathname);
        Element wareElement = root.getChild("Ware");
        wareElement.removeContent();
        XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
        xmlOutputter.output(document, fos);
        fos.close();
    }

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
        List<IVertragspartner> vertragspartnerList = new ArrayList<>();
        for (Element vertragspartner : list) { // iterates through all children of the root element
            String id = vertragspartner.getChildText("id");
            String vorname = vertragspartner.getChildText("vorname");
            String nachname = vertragspartner.getChildText("nachname");
            String ausweisnummer = vertragspartner.getChildText("ausweisnummer");
            Element adresseElement = vertragspartner.getChildren("Adresse").get(0);
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
