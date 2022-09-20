package controller;

import java.sql.ResultSet;
import java.sql.Statement;
import com.google.gson.Gson;

import beans.Usuario;
import connection.DBConnection;

public class UsuarioController implements IUsuarioController {

    @Override
    public String login(String correo, String contrasena) {

        Gson gson = new Gson();

        DBConnection con = new DBConnection();

        String sql = "Select * from usuario where correo = '" + correo
                + "' and contrasena = '" + contrasena + "'";
        try {
            Statement st = con.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                String cedula = rs.getString("cedula");
                String primerNombre = rs.getString("primerNombre");
                String segundoNombre = rs.getString("segundoNombre");
                String primerApellido = rs.getString("primerApellido");
                String segundoApellido = rs.getString("segundoApellido");
                String ciudad = rs.getString("ciudad");
                String direccion = rs.getString("direccion");
                String telefono = rs.getString("telefono");
                String tipoUsuario = rs.getString("tipoUsuario");
                boolean estado = rs.getBoolean("estado");

                Usuario usuario
                        = new Usuario(cedula, primerNombre, segundoNombre, primerApellido, segundoApellido, ciudad, direccion, telefono, correo, contrasena, tipoUsuario, estado);
                return gson.toJson(usuario);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            con.desconectar();
        }

        return "false";
    }

}
