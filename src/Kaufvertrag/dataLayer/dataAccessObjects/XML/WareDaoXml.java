package Kaufvertrag.dataLayer.dataAccessObjects.XML;

import Kaufvertrag.businessObjects.IWare;
import Kaufvertrag.dataLayer.dataAccessObjects.IWareDao;
import Kaufvertrag.exceptions.DaoException;

import java.io.IOException;
import java.util.List;

public class WareDaoXml implements IWareDao {

    private String filename = "Kaufvertrag.xml";

    @Override
    public IWare create() throws IOException {
        ServiceXml serviceXml = new ServiceXml(filename);
        serviceXml.createWare();
        return null;
    }

    @Override
    public IWare create(IWare ware) throws IOException {
        ServiceXml serviceXml = new ServiceXml(filename);
        serviceXml.createWareElement(ware);
        return null;
    }

    @Override
    public List<IWare> read() {
        ServiceXml serviceXml = new ServiceXml(filename);
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
    public void update(IWare ware) throws DaoException, IOException {
        ServiceXml serviceXml = new ServiceXml(filename);
        serviceXml.updateWare(ware);
    }

    @Override
    public void delete(int id) throws DaoException, IOException {
        ServiceXml serviceXml = new ServiceXml(filename);
        serviceXml.deleteWare(id);
    }
}
