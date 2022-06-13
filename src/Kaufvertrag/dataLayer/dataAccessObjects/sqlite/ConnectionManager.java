package Kaufvertrag.dataLayer.dataAccessObjects.sqlite;



import java.sql.*;

import static java.sql.DriverManager.getConnection;


public class ConnectionManager {

    private static String CLASSNAME;
    private static String CONNECTIONSTRING = "jdbc:sqlite:db.sql";
    private static Connection existingConnection;
    private static boolean classLoaded;

    public static Connection getNewConnection() {
        if(existingConnection != null){
            try {
                existingConnection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        Connection connection = null;
        // Connect to sqlite
        try {
            Class.forName("org.sqlite.JDBC");
            connection = (Connection) getConnection(CONNECTIONSTRING);
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Opened database successfully");
        existingConnection = connection;
        return connection;
    }


    public static Connection getExistingConnection() {
        return existingConnection;
    }

    public static void close(ResultSet resultSet, Statement statement, Connection connection) {
        // close connection
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
