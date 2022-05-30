package Kaufvertrag.dataLayer.dataAccessObjects;

import Kaufvertrag.businessObjects.IVertragspartner;

import java.util.List;

public interface IVertragspartnerDao {
    public IVertragspartner create();
    public IVertragspartner create(IVertragspartner vertragspartner);
    public List<IVertragspartner> read();
    public List<IVertragspartner> read(int id);
    public void update(IVertragspartner vertragspartner);
    public void delete(int id);
}
