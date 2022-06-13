package Kaufvertrag.dataLayer.dataAccessObjects;

import Kaufvertrag.dataLayer.dataAccessObjects.XML.DataLayerXml;
import Kaufvertrag.dataLayer.dataAccessObjects.sqlite.DataLayerSqlite;
import Kaufvertrag.exceptions.DaoException;

public class DataLayerManager  {
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

    public IDataLayer getDataLayer() throws DaoException { // TODO: FIX
       if(persistenceType == "xml"){
           return new DataLayerXml();
       }else if(persistenceType == "sqlite"){
           return new DataLayerSqlite();
       }else{
           throw new DaoException("Persistence type not supported!");
       }
    }

    private String readPersistenceType() {
        return persistenceType;
    }
}
