package no.ntnu.hikingstore_6;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * @author postgresqltutorial.com
 */
public class DatabaseConnection {

    private final String url = "jdbc:postgresql://localhost/store";
    private final String user = "postgres";
    private final String password = "<add your password>";

    /*
    Connect to
    the PostgreSQL
    database
    *
             *@return
    a Connection
    object
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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DatabaseConnection app = new DatabaseConnection();
        app.connect();
    }
}


