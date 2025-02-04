package app.data.interfaces;

import java.sql.Connection;

public interface IDB {

    void connect();

    Connection getConnection();
    void close();
}