package Kaufvertrag.dataLayer.dataAccessObjects.XML;

import Kaufvertrag.dataLayer.businessObjects.Ware;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.SAXOutputter;
import org.jdom2.output.XMLOutputter;

import java.io.Console;
import java.io.File;
import java.util.List;

public class ServiceXml {
    public static void main(String[] args) {
        new ServiceXml("D:\\.Downloads\\Programming\\IntelliJ\\projects\\DAO\\DAO\\src\\Kaufvertrag\\files\\test.xml");
        Ware();
    }
    private static Element root;

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

    public static void Ware(){
        Element wData = root.getChild("Ware"); // Finds the first child element called "Ware"
        String id = wData.getChildText("id");
        String bezeichnung = wData.getChildText("bezeichnung");
        String beschreibung = wData.getChildText("beschreibung");
        String preis = wData.getChildText("preis");
        String besonderheiten = wData.getChildText("besonderheiten");
        String maengel = wData.getChildText("maengel");
        if( bezeichnung != null && preis != null){
            // Check if preis is number
            try{
                Ware ware = new Ware(bezeichnung, Integer.parseInt(preis));
            }catch (NumberFormatException e){
                System.out.println("Preis ist keine Zahl");
            }


        }

    }

    public List<Element>Vertragspartner(){
        List<Element> list = root.getChildren("Vertragspartner"); // Finds all children elements called "Vertragspartner"
        return list;
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
