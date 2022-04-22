package no.ntnu.hikingstore_6;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBConnection {

    private final String url = "jdbc:postgresql://10.212.26.242/store";
    private final String user = "postgres";
    private final String password = "postgres";


    /* Connect to the PostgreSQL database
     * @return a Connection object
     */
    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public JDBConnection() {
        connect();
    }
}
