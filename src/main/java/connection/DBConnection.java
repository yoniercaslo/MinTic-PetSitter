
package connection;
import java.sql.Connection;
import java.sql.DriverManager;
public class DBConnection {
    
    Connection connection;
    static String bd = "railway";
    static String port = "7139";
    static String login = "root";
    static String password = "85PVNcnF8bxJcNEdYg1Z";
    static String ip = "containers-us-west-48.railway.app";

    public DBConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://" + DBConnection.ip + ":" + DBConnection.port + "/" + DBConnection.bd;
            //String url = "jdbc:mysql://localhost:" + this.port + "/" + this.bd;
            connection = DriverManager.getConnection(url, this.login, this.password);
            System.out.println("Conexión Establecida");
        } catch (Exception ex) {
            System.out.println("Error de la Conexión");
        }
    }
    
    public Connection getConnection(){
        return connection;
    }
    
    public void desconectar(){
        connection = null;
    }
    
}
