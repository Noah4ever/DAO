package Kaufvertrag.dataLayer.dataAccessObjects.sqlite;

import Kaufvertrag.businessObjects.IVertragspartner;
import Kaufvertrag.exceptions.DaoException;
import Kaufvertrag.dataLayer.businessObjects.Vertragspartner;
import Kaufvertrag.dataLayer.businessObjects.Adresse;
import Kaufvertrag.dataLayer.dataAccessObjects.IVertragspartnerDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VertragspartnerDaoSqlite implements IVertragspartnerDao {

    @Override
    public IVertragspartner create() throws DaoException {
        Connection connection = ConnectionManager.getNewConnection();
        IVertragspartner vertragspartner = null;

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Vertragspartner;");

            while (rs.next()) {
                String vorname = rs.getString("vorname");
                String nachname = rs.getString("nachname");
                String ausweisNr = rs.getString("ausweisNr");

                String strasse = rs.getString("strasse");
                String plz = rs.getString("plz");
                String ort = rs.getString("ort");
                String hausNr = rs.getString("hausNr");

                Adresse adresse = new Adresse(strasse, plz, ort, hausNr);

                vertragspartner = new Vertragspartner(vorname, nachname);
                vertragspartner.setAusweisNr(ausweisNr);
                vertragspartner.setAdresse(adresse);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Fehler beim Erstellen eines Vertragspartners");
        }
        return vertragspartner;
    }

    @Override
    public IVertragspartner create(IVertragspartner vertragspartner) {
        return null;
    }

    @Override
    public List<IVertragspartner> read() throws DaoException {
        Connection connection = ConnectionManager.getNewConnection();
        List<IVertragspartner> vertragspartnerList = new ArrayList<>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Vertragspartner as v LEFT JOIN Adresse as a ON a.id = v.adresseId;");

            while (rs.next()) {
                String vorname = rs.getString("vorname");
                String nachname = rs.getString("nachname");
                String ausweisNr = rs.getString("ausweisNr");

                String strasse = rs.getString("strasse");
                String plz = rs.getString("plz");
                String ort = rs.getString("ort");
                String hausNr = rs.getString("hausNr");

                Adresse adresse = new Adresse(strasse, plz, ort, hausNr);

                IVertragspartner vertragspartner = new Vertragspartner(vorname, nachname);
                vertragspartner.setAusweisNr(ausweisNr);
                vertragspartner.setAdresse(adresse);

                vertragspartnerList.add(vertragspartner);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Fehler beim Lesen der Vertragspartner");
        }
        return  vertragspartnerList;
    }

    @Override
    public IVertragspartner read(int id) throws DaoException {
        Connection connection = ConnectionManager.getNewConnection();
        IVertragspartner vertragspartner = null;

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Vertragspartner WHERE id = " + id + ";");

            while (rs.next()) {
                String vorname = rs.getString("vorname");
                String nachname = rs.getString("nachname");
                String ausweisNr = rs.getString("ausweisNr");

                String strasse = rs.getString("strasse");
                String plz = rs.getString("plz");
                String ort = rs.getString("ort");
                String hausNr = rs.getString("hausNr");

                Adresse adresse = new Adresse(strasse, plz, ort, hausNr);

                vertragspartner = new Vertragspartner(vorname, nachname);
                vertragspartner.setAusweisNr(ausweisNr);
                vertragspartner.setAdresse(adresse);

            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Fehler beim Lesen eines Vertragspartners");
        }

        return vertragspartner;
    }

    @Override
    public void update(IVertragspartner vertragspartner) throws DaoException {
        Connection connection = ConnectionManager.getNewConnection();

        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("UPDATE Vertragspartner SET vorname = '" + vertragspartner.getVorname() +
                    "', nachname = '" + vertragspartner.getNachname() +
                    "', ausweisNr = '" + vertragspartner.getAusweisNr() +
                    "', hausNr = '" + vertragspartner.getAdresse().getHausNr() +
                    "', plz = '" + vertragspartner.getAdresse().getPlz() +
                    "', ort = '" + vertragspartner.getAdresse().getOrt() +
                    "', strasse = '" + vertragspartner.getAdresse().getStrasse() +
                    "' WHERE id = " + vertragspartner.getId() + ";");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Fehler beim Update eines Vertragspartners");
        }

    }

    @Override
    public void delete(int id) throws DaoException {
        Connection connection = ConnectionManager.getNewConnection();

        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("DELETE FROM Vertragspartner WHERE id = " + id + ";");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Fehler beim Löschen eines Vertragspartners");
        }
    }
}
