package Kaufvertrag.dataLayer.dataAccessObjects.sqlite;

import Kaufvertrag.businessObjects.IWare;
import Kaufvertrag.dataLayer.businessObjects.Ware;
import Kaufvertrag.dataLayer.dataAccessObjects.IWareDao;
import Kaufvertrag.exceptions.DaoException;
import Kaufvertrag.dataLayer.dataAccessObjects.DataLayerManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WareDaoSqlite implements IWareDao {

    @Override
    public IWare create() throws DaoException {
        Connection connection = ConnectionManager.getNewConnection();
        Ware ware = null;

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Ware;");

            while (rs.next()) {
                String bezeichnung = rs.getString("bezeichnung");
                String preis = rs.getString("preis");
                String beschreibung = rs.getString("beschreibung");
                String maengel = rs.getString("maengel");
                String besonderheiten = rs.getString("besonderheiten");

                ware = new Ware(bezeichnung, Double.parseDouble(preis));
                ware.setBeschreibung(beschreibung);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Fehler beim Erstellen einer Ware");
        }

        return ware;
    }

    @Override
    public IWare create(IWare ware) throws DaoException {
        // SAve to sql
        Connection connection = ConnectionManager.getNewConnection();
        try {
            String besonderheitenString = "";
            for (String besonderheit : ware.getBesonderheiten()) {
                besonderheitenString += besonderheit + ";";
            }

            String maengelString = "";
            for (String maengel : ware.getMaengel()) {
                maengelString += maengel + ";";
            }
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO Ware (bezeichnung, preis, beschreibung, maengel, besonderheiten) VALUES (?, ?, ?, ?, ?);");
            stmt.setString(1, ware.getBezeichnung());
            stmt.setString(2, String.valueOf(ware.getPreis()));
            stmt.setString(3, ware.getBeschreibung());
            stmt.setString(4, besonderheitenString);
            stmt.setString(5, maengelString);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Fehler beim Erstellen einer Ware");
        }
        return ware;
    }

    @Override
    public List<IWare> read() throws DaoException{
        Connection connection = ConnectionManager.getNewConnection();
        List<IWare> wareList = new ArrayList<>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Ware;");

            while (rs.next()) {
                String bezeichnung = rs.getString("bezeichnung");
                String preis = rs.getString("preis");
                String beschreibung = rs.getString("beschreibung");

                IWare ware = new Ware(bezeichnung, Double.parseDouble(preis));
                ware.setBeschreibung(beschreibung);
                wareList.add(ware);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Fehler beim Lesen der Datenbank");
        }

        return wareList;
    }

    @Override
    public IWare read(int id) throws DaoException {
        Connection connection = ConnectionManager.getNewConnection();
        IWare ware = null;
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Ware WHERE id =" + id + ";");

            while (rs.next()) {
                String bezeichnung = rs.getString("bezeichnung");
                String preis = rs.getString("preis");
                String beschreibung = rs.getString("beschreibung");

                ware = new Ware(bezeichnung, Double.parseDouble(preis));
                ware.setBeschreibung(beschreibung);

            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("WareDaoSqlite: read(int id)");
        }
        return ware;
    }

    @Override
    public void update(IWare ware) throws DaoException {
        Connection connection = ConnectionManager.getNewConnection();
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("UPDATE Ware SET bezeichnung = '" + ware.getBezeichnung() + "', preis = " + ware.getPreis() + ", beschreibung = '" + ware.getBeschreibung() + "', maengel = '" + ware.getMaengel() + "', besonderheiten = '" + ware.getBesonderheiten() + "' WHERE id = " + ware.getId() + ";");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Ware konnte nicht aktualisiert werden");
        }
    }

    @Override
    public void delete(int id) throws DaoException {
        Connection connection = ConnectionManager.getNewConnection();
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("DELETE FROM Ware WHERE id = " + id + ";");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Ware konnte nicht gelöscht werden");
        }
    }
}
