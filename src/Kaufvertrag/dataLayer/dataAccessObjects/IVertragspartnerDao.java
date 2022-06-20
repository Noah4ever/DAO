package Kaufvertrag.dataLayer.dataAccessObjects;

import Kaufvertrag.businessObjects.IVertragspartner;
import Kaufvertrag.exceptions.DaoException;

import java.io.IOException;
import java.util.List;

public interface IVertragspartnerDao {
    public IVertragspartner create() throws DaoException, IOException;
    public IVertragspartner create(IVertragspartner vertragspartner) throws IOException;
    public List<IVertragspartner> read() throws DaoException;
    public IVertragspartner read(int id) throws DaoException;
    public void update(IVertragspartner vertragspartner) throws DaoException, IOException;
    public void delete(int id) throws DaoException, IOException;
}
