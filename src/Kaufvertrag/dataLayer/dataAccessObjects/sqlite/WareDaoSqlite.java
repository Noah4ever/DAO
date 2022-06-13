package Kaufvertrag.dataLayer.dataAccessObjects.sqlite;

import Kaufvertrag.businessObjects.IWare;
import Kaufvertrag.dataLayer.dataAccessObjects.IWareDao;
import Kaufvertrag.exceptions.DaoException;

import java.util.List;

public class WareDaoSqlite implements IWareDao {


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
        return null;
    }

    @Override
    public IWare read(int id) throws DaoException {
        return null;
    }

    @Override
    public void update(IWare ware) throws DaoException {

    }

    @Override
    public void delete(int id) throws DaoException {

    }
}
