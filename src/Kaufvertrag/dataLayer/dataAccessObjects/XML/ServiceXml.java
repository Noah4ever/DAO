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
import org.jdom2.input.JDOMParseException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.SAXOutputter;
import org.jdom2.output.XMLOutputter;

import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Arrays;
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
            System.out.println("[Info] No file found!");
            try {
                createNewFile(pathname);
            } catch (IOException | JDOMException e) {
                e.printStackTrace();
            }
        } else {
            // File exists
            try {
                if(isValidXML(xmlFile)){ // Check if pathname is valid xml file
                    SAXBuilder builder = new SAXBuilder();
                    document = (Document) builder.build(new File(xmlFile)); // Read file
                    root = document.getRootElement(); // Get root element
                }else{
                    System.out.println("[Error] Not a valid XML file at: '%s' \n[Info] Deleting the file...".formatted(xmlFile));
                    if(file.delete()){
                        System.out.println("[Info] File successfully deleted!");
                        createNewFile(pathname);
                    }else{
                        System.out.println("[Error] File could not be deleted! Please delete the file at: '%s'".formatted(xmlFile));
                    }

                }
            } catch (JDOMException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void createNewFile(String pathname) throws IOException, JDOMException {
        System.out.println("[Info] Creating new file at: '%s'".formatted(pathname));
        new File(pathname).createNewFile();
        SAXBuilder builder = new SAXBuilder();
        document = (Document) builder.build(new StringReader("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<Kaufvertrag></Kaufvertrag>"));
        root = document.getRootElement();
        FileOutputStream fos = new FileOutputStream(pathname);
        XMLOutputter xmlOutput = new XMLOutputter(Format.getPrettyFormat());
        xmlOutput.output(document, fos);
        System.out.println("[Info] File successfully created!");
    }

    private boolean isValidXML(String pathname){ // Check if given XML path is a valid XML file
        SAXBuilder builder = new SAXBuilder();
        try{
            Document doc = builder.build(new File(pathname));
        } catch (JDOMException | IOException e) {
            return false;
        }
        return true;
    }

    public void updateVertragspartner(IVertragspartner vertragspartner) throws IOException {
        FileOutputStream fos = new FileOutputStream(pathname);

        List<Element> vertragspartnerList = root.getChildren("Vertragspartner"); // get the list of vertragspartner

        for(Element v : vertragspartnerList) { // iterate through the list
            if (v.getChildText("id").equals(String.valueOf(vertragspartner.getId()))) { // if the id is the same as the id of the vertragspartner
                v.getChild("id").setText(String.valueOf(vertragspartner.getId()));
                v.getChild("vorname").setText(vertragspartner.getVorname());
                v.getChild("nachname").setText(vertragspartner.getNachname());
                v.getChild("ausweisNr").setText(vertragspartner.getAusweisNr());
                Element adresse = v.getChild("Adresse"); // get the adresse element
                if(adresse != null) {
                    adresse.getChild("strasse").setText(vertragspartner.getAdresse().getStrasse());
                    adresse.getChild("hausNr").setText(String.valueOf(vertragspartner.getAdresse().getHausNr()));
                    adresse.getChild("plz").setText(String.valueOf(vertragspartner.getAdresse().getPlz()));
                    adresse.getChild("ort").setText(vertragspartner.getAdresse().getOrt());
                }else{
                    System.out.println("[DEBUG] adresse is null!");
                }
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
        wareElement.addContent(new Element("preis").setText(String.valueOf(ware.getPreis())));
        wareElement.addContent(new Element("beschreibung").setText(String.valueOf(ware.getBeschreibung())));
        wareElement.addContent(new Element("besonderheiten").setText(String.join(",", ware.getBesonderheiten())));
        wareElement.addContent(new Element("maengel").setText(String.join(",", ware.getMaengel())));

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
            wareElement.addContent(new Element("preis").setText(String.valueOf(preis)));
            wareElement.addContent(new Element("beschreibung").setText(beschreibung));;
            wareElement.addContent(new Element("besonderheiten").setText(String.join(",", ware.getBesonderheiten())));
            wareElement.addContent(new Element("maengel").setText(String.join(",", ware.getMaengel())));

            root.addContent(wareElement);
        }else{
            System.out.println("[Error] Max. Ware (1)!");
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
        return new Vertragspartner("",  ""); // TODO: fix? (Not empty Vertragspartner)
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
        }else{
            System.out.println("[Error] Max. Vertragspartner (2)!");
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

        List<Element> vertragspartnerElements = root.getChildren("Vertragspartner"); // get all Vertragspartner Elements
        for(Element vertragspartnerElement : vertragspartnerElements) { // iterate over all Vertragspartner Elements
            if(vertragspartnerElement.getChildText("id").equals(String.valueOf(id))) { // if id is found
                vertragspartnerElement.removeContent(); // remove Vertragspartner Element
                vertragspartnerElement.detach(); // detach Vertragspartner Element
            }
        }

        XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
        xmlOutputter.output(document, fos);
        fos.close();
    }

    public void deleteWare(int id) throws IOException {
        FileOutputStream fos = new FileOutputStream(pathname);

        List<Element> wareElements = root.getChildren("Ware"); // get all Ware Elements
        System.out.println(wareElements.size());
        for(Element ware : wareElements) { // iterate over all Ware Elements
            if(ware.getChildText("id").equals(String.valueOf(id))) { // if id is found
                ware.removeContent(); // remove Ware Element
                ware.detach(); // detach Ware Element
            }
        }

        XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
        xmlOutputter.output(document, fos);
        fos.close();
    }

    public List<IWare> Ware(){
        List<IWare> wareList = new ArrayList<>();
        List<Element> wData = root.getChildren("Ware"); // Finds the first child element called "Ware"
        for (Element w : wData) {


            try {
                String id = w.getChildText("id");
                String bezeichnung = w.getChildText("bezeichnung");
                String beschreibung = w.getChildText("beschreibung");
                String preis = w.getChildText("preis");
                List<String> besonderheiten = Arrays.asList(w.getChildText("besonderheiten").split(","));
                List<String> maengel = Arrays.asList(w.getChildText("maengel").split(","));

                if (bezeichnung != null && preis != null && isNumeric(preis) && isNumeric(id)) {

                    IWare ware = new Ware(bezeichnung, Double.parseDouble(preis));
                    ware.setId(Integer.parseInt(id));
                    if (beschreibung != null) {
                        ware.setBeschreibung(beschreibung);
                    }
                    if (besonderheiten != null) {
                        ware.getBesonderheiten().addAll(besonderheiten);
                    }
                    if (maengel != null) {
                        ware.getMaengel().addAll(maengel);
                    }
                    wareList.add(ware);

                }
            } catch (Exception e) {
                System.out.println("[Error] Ware could not be loaded properly!");
            }
        }
        return wareList;
    }

    public List<IVertragspartner>Vertragspartner(){
        List<Element> list = root.getChildren("Vertragspartner"); // Finds all children elements called "Vertragspartner"
        List<IVertragspartner> vertragspartnerList = new ArrayList<>();
        for (Element vertragspartner : list) { // iterates through all children of the root element
            try{
                String id = vertragspartner.getChildText("id");
                String vorname = vertragspartner.getChildText("vorname");
                String nachname = vertragspartner.getChildText("nachname");
                String ausweisnummer = vertragspartner.getChildText("ausweisNr");
                Element adresseElement = vertragspartner.getChild("Adresse");
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
                vertragspartner1.setId(Integer.parseInt(id)); // set the id of the Vertragspartner object

                vertragspartnerList.add(vertragspartner1); // add the Vertragspartner object to the list
            }catch(Exception e){
                System.out.println("[Error] Vertragspartner could not be loaded properly!");
            }
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
