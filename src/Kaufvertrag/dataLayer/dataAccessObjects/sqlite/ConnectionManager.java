package Kaufvertrag.dataLayer.dataAccessObjects.sqlite;

import com.sun.jdi.connect.spi.Connection;

import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectionManager {


    private Connection existingConnection;
    private boolean classLoaded;

    public Connection getNewConnection() {

    }

    public Connection getExistingConnection() {

    }

    public void close(ResultSet resultSet, Statement statement, Connection connection) {

    }
}
