package test;
import beans.Usuario;
import connection.DBConnection;
import java.sql.ResultSet;
import java.sql.Statement;
public class OperacionesBD {
    
    public static void main(String[] args) {
        //actualizarUsuario("1088007022","Terror/Terror");
        listarUsuario();
    }
    
    public static void actualizarUsuario(String cedula, String primerNombre){
        DBConnection con = new DBConnection();
        String sql = "UPDATE usuario SET primerNombre = ' " + primerNombre + " ' WHERE cedula =  " + cedula;
        try {
            Statement st = con.getConnection().createStatement();
            st.executeUpdate(sql);
        } catch (Exception ex) {
            System.out.println(ex.getMessage() );
        }
        finally{
            con.desconectar();
        }
    }
    
    public static void listarUsuario(){
        DBConnection con = new DBConnection();
        String sql = "SELECT * FROM usuario";
        try {
            Statement st = con.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                String cedula = rs.getString("cedula");
                String primerNombre = rs.getString("primerNombre");
                String segundoNombre = rs.getString("segundoNombre");
                String primerApellido = rs.getString("primerApellido");
                String segundoApellido = rs.getString("segundoApellido");
                String ciudad = rs.getString("ciudad");
                String direccion = rs.getString("direccion");
                String telefono = rs.getString("telefono");
                String correo = rs.getString("correo");
                String contrasena = rs.getString("contrasena");
                String tipoUsuario = rs.getString("tipoUsuario");
                boolean estado = rs.getBoolean("estado");
                
                Usuario usuarios = new Usuario(cedula, primerNombre, segundoNombre, primerApellido, segundoApellido, ciudad, direccion, telefono, correo, contrasena, tipoUsuario, estado);
                System.out.println(usuarios.toString());
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage() );
        }
        finally{
            con.desconectar();
        }
    } 
    
}
