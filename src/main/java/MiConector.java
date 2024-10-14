import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MiConector {
    private static String user="root";
    private static String password="1234";
    private static String database="starwars";
    private static String url="jdbc:mysql://localhost:3306/" + database;
    private static Connection con=null;

    public  MiConector() {
        try {
            con= DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public Connection getConnection() {
        return con;
    }
    public void closeConnection() {
        try {
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}