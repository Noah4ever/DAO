package Kaufvertrag.dataLayer.dataAccessObjects.sqlite;



import java.sql.*;

import static java.sql.DriverManager.getConnection;


public class ConnectionManager {

    private String CLASSNAME;
    private String CONNECTIONSTRING;
    private Connection existingConnection;
    private boolean classLoaded;

    public static void main(String[] args) {
        getNewConnection();
    }
    public static Connection getNewConnection() {
        Connection connection = null;
        // Connect to sqlite
        try {
            Class.forName("org.sqlite.JDBC");
            connection = (Connection) getConnection("jdbc:sqlite:db.sql");
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Opened database successfully");

        return connection;
    }

    /*
    public Connection getExistingConnection() {

    }

    public void close(ResultSet resultSet, Statement statement, Connection connection) {

    }*/
}
