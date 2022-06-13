package Kaufvertrag.dataLayer.dataAccessObjects;

import Kaufvertrag.businessObjects.IWare;
import Kaufvertrag.exceptions.DaoException;

import java.util.List;

public interface IWareDao {
    public IWare create() throws DaoException;
    public IWare create(IWare ware);
    public List<IWare> read() throws DaoException;
    public IWare read(int id) throws DaoException;
    public void update(IWare ware) throws DaoException;
    public void delete(int id) throws DaoException;
}
