package Kaufvertrag.dataLayer.dataAccessObjects.XML;

import Kaufvertrag.businessObjects.IVertragspartner;
import Kaufvertrag.dataLayer.dataAccessObjects.IVertragspartnerDao;
import Kaufvertrag.exceptions.DaoException;

import java.util.List;

public class VertragspartnerDaoXml  implements IVertragspartnerDao {

    @Override
    public IVertragspartner create() {
        return null;
    }

    @Override
    public IVertragspartner create(IVertragspartner vertragspartner) {
        return null;
    }

    @Override
    public List<IVertragspartner> read() {
        ServiceXml serviceXml = new ServiceXml("D:\\.Downloads\\Programming\\IntelliJ\\projects\\DAO\\DAO\\src\\Kaufvertrag\\files\\test.xml");
        return serviceXml.Vertragspartner();
    }

    @Override
    public IVertragspartner read(int id) throws DaoException {
        List<IVertragspartner> vertragspartner = read();
        for(IVertragspartner v : vertragspartner){
            if(v.getId() == id){
                return v;
            }
        }
        return null;
    }
    @Override
    public void update(IVertragspartner vertragspartner) {

    }

    @Override
    public void delete(int id) {

    }
}
