package app.data;

import app.data.interfaces.IDB;

import java.sql.Connection;
import java.sql.DriverManager;

public class PostgresDB implements IDB {

    private final String connectionUrl;
    private final String username;
    private final String password;
    private Connection connection;

    public PostgresDB(String host, String username, String password, String dbName) {
        this.connectionUrl = host + "/" + dbName;
        this.username = username;
        this.password = password;
    } // main connection

    @Override
    public void connect() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(connectionUrl, username, password);
        } catch (Exception e) {
            System.out.println("Failed to connect to Postgres: " + e.getMessage());
        }
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            System.out.println("Error closing connection: " + e.getMessage());
        }
    }
}