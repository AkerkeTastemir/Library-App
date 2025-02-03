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
    public Connection getConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                return connection;
            }
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(connectionUrl, username, password);
            return connection;
        } catch (Exception e) {
            System.out.println("Failed to connect to Postgres: " + e.getMessage());
            return null;
        }
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