package Kaufvertrag.dataLayer.dataAccessObjects.XML;

import Kaufvertrag.businessObjects.IVertragspartner;
import Kaufvertrag.dataLayer.dataAccessObjects.IVertragspartnerDao;
import Kaufvertrag.exceptions.DaoException;

import java.io.IOException;
import java.util.List;

public class VertragspartnerDaoXml  implements IVertragspartnerDao {

    private String filename = "Kaufvertrag.xml";

    @Override
    public IVertragspartner create() throws IOException {
        ServiceXml serviceXml = new ServiceXml(filename);
        serviceXml.createVertragspartner();
        return null;
    }

    @Override
    public IVertragspartner create(IVertragspartner vertragspartner) throws IOException {
        ServiceXml serviceXml = new ServiceXml(filename);
        serviceXml.createVertragspartnerElement(vertragspartner);
        return null;
    }

    @Override
    public List<IVertragspartner> read() {
        ServiceXml serviceXml = new ServiceXml(filename);
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
    public void update(IVertragspartner vertragspartner) throws IOException {
        ServiceXml serviceXml = new ServiceXml(filename);
        serviceXml.updateVertragspartner(vertragspartner);
    }

    @Override
    public void delete(int id) throws IOException {
        ServiceXml serviceXml = new ServiceXml(filename);
        serviceXml.deleteVertragspartner(id);
    }
}
