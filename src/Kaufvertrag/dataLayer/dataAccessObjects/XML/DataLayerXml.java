package Kaufvertrag.dataLayer.dataAccessObjects.XML;

import Kaufvertrag.businessObjects.IWare;
import Kaufvertrag.dataLayer.dataAccessObjects.IDataLayer;
import Kaufvertrag.dataLayer.dataAccessObjects.IVertragspartnerDao;
import Kaufvertrag.dataLayer.dataAccessObjects.IWareDao;
import Kaufvertrag.exceptions.DaoException;

import java.util.List;

public class DataLayerXml implements IDataLayer {

    @Override
    public IVertragspartnerDao getVertragspartnerDao() {
        return new VertragspartnerDaoXml();
    }

    @Override
    public IWareDao getWareDao() {
        return new WareDaoXml();
    }



}
