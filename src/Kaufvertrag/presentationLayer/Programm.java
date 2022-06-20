package Kaufvertrag.presentationLayer;

import Kaufvertrag.dataLayer.dataAccessObjects.DataLayerManager;
import Kaufvertrag.dataLayer.dataAccessObjects.IDataLayer;
import Kaufvertrag.dataLayer.dataAccessObjects.XML.DataLayerXml;
import Kaufvertrag.exceptions.DaoException;

public class Programm {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        DataLayerManager dlm = DataLayerManager.getInstance();

        System.out.println("---< Kaufvertrag >---\n");
        System.out.println("[1] Einlesen");
        System.out.println("[2] Neu erstellen");

        int input = 0;
        try {
            input = Integer.parseInt(System.console().readLine());
            if(input == 1) {
                System.out.println("Einlesen");
                System.out.println("---< Einlesen >---");
                System.out.println("[1] XML");
                System.out.println("[2] SQLite");
                int input2 = Integer.parseInt(System.console().readLine());
                IDataLayer dl;
                if (input2 == 1) {
                    dlm.getInstance().persistenceType = "xml";
                    dl = dlm.getDataLayer();
                } else if (input2 == 2) {
                    dlm.getInstance().persistenceType = "sqlite";
                    dl = dlm.getDataLayer();
                } else {
                    System.out.println("Persistence type not supported!");
                }
            } else if(input == 2) {
                System.out.println("Neu erstellen");
                System.out.println("---< Neu erstellen >---");


            } else {
                System.out.println("Input incorrect!");
            }
        } catch (NumberFormatException | DaoException e) {
            System.out.println("Invalid input");
        }

    }
}
