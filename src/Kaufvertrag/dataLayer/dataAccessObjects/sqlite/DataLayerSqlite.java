package Kaufvertrag.dataLayer.dataAccessObjects.sqlite;

import Kaufvertrag.dataLayer.dataAccessObjects.IDataLayer;
import Kaufvertrag.dataLayer.dataAccessObjects.IVertragspartnerDao;
import Kaufvertrag.dataLayer.dataAccessObjects.IWareDao;

public class DataLayerSqlite implements IDataLayer {

    @Override
    public IVertragspartnerDao getVertragspartnerDao() {
        return new VertragspartnerDaoSqlite();
    }

    @Override
    public IWareDao getWareDao() {
        return new WareDaoSqlite();
    }

}
