package Kaufvertrag.dataLayer.dataAccessObjects.sqlite;

import Kaufvertrag.businessObjects.IAdresse;
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

                Adresse adresse = new Adresse(strasse, hausNr, plz, ort);

                vertragspartner = new Vertragspartner(vorname, nachname);
                vertragspartner.setAusweisNr(ausweisNr);
                vertragspartner.setAdresse(adresse);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("[Error] Fehler beim Erstellen eines Vertragspartners!");
        }
        return vertragspartner;
    }

    @Override
    public IVertragspartner create(IVertragspartner v) throws DaoException {
        Connection connection = ConnectionManager.getNewConnection();
        try {
            if(countVetragspartner(connection) < 2){
                String sql = "INSERT INTO Vertragspartner(id, vorname, nachname, ausweisNr, hausNr, plz, ort, strasse) VALUES(?,?,?,?,?,?,?,?)";
                PreparedStatement pstmt = connection.prepareStatement("INSERT INTO Vertragspartner(id, vorname, nachname, ausweisNr, hausNr, plz, ort, strasse) VALUES(?,?,?,?,?,?,?,?)");
                pstmt.setInt(1, v.getId());
                pstmt.setString(2, v.getVorname());
                pstmt.setString(3, v.getNachname());
                pstmt.setString(4, v.getAusweisNr());
                pstmt.setString(5, v.getAdresse().getHausNr());
                pstmt.setString(6, v.getAdresse().getPlz());
                pstmt.setString(7, v.getAdresse().getOrt());
                pstmt.setString(8, v.getAdresse().getStrasse());
                pstmt.executeUpdate();
            }else{
                System.out.println("[Error] Max. Vertragspartner (2)!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("[Error] Fehler beim Erstellen eines Vertragspartners!");
        }
        return new Vertragspartner("","");
    }

    private int countVetragspartner(Connection connection) throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Vertragspartner;");
        int counter = 0;
        while(rs.next()){
            counter++;
        };
        return counter;
    }

    @Override
    public List<IVertragspartner> read() throws DaoException {
        Connection connection = ConnectionManager.getNewConnection();
        List<IVertragspartner> vertragspartnerList = new ArrayList<>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Vertragspartner;");
            while (rs.next()) {

                vertragspartnerList.add(getVertragspartner(rs));

            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("[Error] Fehler beim Lesen der Vertragspartner!");
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

                vertragspartner = getVertragspartner(rs);

            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("[Error] Fehler beim Lesen eines Vertragspartners!");
        }

        return vertragspartner;
    }

    private IVertragspartner getVertragspartner(ResultSet rs) throws SQLException {
        IVertragspartner vertragspartner;
        IAdresse adresse;

        String VertragspartnerId = rs.getString("id");
        String vorname = rs.getString("vorname");
        String nachname = rs.getString("nachname");
        String ausweisNr = rs.getString("ausweisNr");

        vertragspartner = new Vertragspartner(vorname, nachname, ausweisNr);
        vertragspartner.setId(Integer.parseInt(VertragspartnerId));

        String strasse = rs.getString("strasse");
        String hausNr = rs.getString("hausNr");
        String plz = rs.getString("plz");
        String ort = rs.getString("ort");

        adresse = new Adresse(strasse, hausNr, plz, ort);

        vertragspartner.setAdresse(adresse);

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
            throw new DaoException("[Error] Fehler beim Update eines Vertragspartners!");
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
