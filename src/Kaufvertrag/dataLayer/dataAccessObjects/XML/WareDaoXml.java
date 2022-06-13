package Kaufvertrag.dataLayer.dataAccessObjects.XML;

import Kaufvertrag.businessObjects.IWare;
import Kaufvertrag.dataLayer.dataAccessObjects.IWareDao;
import Kaufvertrag.exceptions.DaoException;

import java.util.List;

public class WareDaoXml implements IWareDao {


    @Override
    public IWare create() {
        return null;
    }

    @Override
    public IWare create(IWare ware) {
        return null;
    }

    @Override
    public List<IWare> read() {
        ServiceXml serviceXml = new ServiceXml("D:\\.Downloads\\Programming\\IntelliJ\\projects\\DAO\\DAO\\src\\Kaufvertrag\\files\\test.xml");
        return serviceXml.Ware();
    }

    @Override
    public IWare read(int id) throws DaoException {
        List<IWare> ware = read();
        for(IWare w : ware){
            if(w.getId() == id){
                return w;
            }
        }
        return null;
    }

    @Override
    public void update(IWare ware) throws DaoException {

    }

    @Override
    public void delete(int id) throws DaoException {

    }
}
