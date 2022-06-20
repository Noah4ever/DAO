package Kaufvertrag.dataLayer.dataAccessObjects;

import Kaufvertrag.businessObjects.IWare;
import Kaufvertrag.exceptions.DaoException;

import java.io.IOException;
import java.util.List;

public interface IWareDao {
    public IWare create() throws DaoException, IOException;
    public IWare create(IWare ware) throws DaoException, IOException;
    public List<IWare> read() throws DaoException;
    public IWare read(int id) throws DaoException;
    public void update(IWare ware) throws DaoException, IOException;
    public void delete(int id) throws DaoException, IOException;
}
