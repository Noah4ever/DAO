package Kaufvertrag.dataLayer.dataAccessObjects.XML;

public class ServiceXml {

    public static String readXMLFile(String fileName) {
        String xmlString = "";

        try {
            xmlString = new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(fileName)));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return xmlString;

    }

}
