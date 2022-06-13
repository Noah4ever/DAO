package Kaufvertrag.dataLayer.dataAccessObjects;

import Kaufvertrag.businessObjects.IVertragspartner;
import Kaufvertrag.exceptions.DaoException;

import java.util.List;

public interface IVertragspartnerDao {
    public IVertragspartner create() throws DaoException;
    public IVertragspartner create(IVertragspartner vertragspartner);
    public List<IVertragspartner> read() throws DaoException;
    public IVertragspartner read(int id) throws DaoException;
    public void update(IVertragspartner vertragspartner) throws DaoException;
    public void delete(int id) throws DaoException;
}
