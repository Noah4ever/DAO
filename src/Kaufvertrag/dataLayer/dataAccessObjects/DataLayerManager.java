package Kaufvertrag.dataLayer.dataAccessObjects;

public class DataLayerManager {
    private static DataLayerManager instance = null;
    private String persistenceType;


    private DataLayerManager() {
        persistenceType = "";
    }

    public static DataLayerManager getInstance() {
        if (instance == null) {
            instance = new DataLayerManager();
        }
        return instance;
    }

    public IDataLayer getDataLayer(){
       return null; // TODO: FIX
    }

    private String readPersistenceType() {
        return persistenceType;
    }
}
