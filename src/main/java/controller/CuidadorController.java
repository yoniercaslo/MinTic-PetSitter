package controller;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import beans.Cuidador;
import connection.DBConnection;

public class CuidadorController implements ICuidadorController {

    @Override
    public String listar(boolean ordenar, String orden) {

        Gson gson = new Gson();

        DBConnection con = new DBConnection();
        String sql = "Select * from cuidador";

        if (ordenar == true) {
            sql += " order by ciudad " + orden;
        }

        List<String> cuidador = new ArrayList<String>();

        try {

            Statement st = con.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                int id = rs.getInt("id");
                String cedula = rs.getString("cedula");
                String nombre = rs.getString("nombre");
                String apellidos = rs.getString("apellidos");
                String ciudad = rs.getString("ciudad");
                String direccion = rs.getString("direccion");
                String telefono = rs.getString("telefono");
                String correo = rs.getString("correo"); 
                String contrasena = rs.getString("contrasena");
                int horas = rs.getInt("horas");
                boolean estado = rs.getBoolean("estado");

                Cuidador cuidadores = new Cuidador(id, cedula, nombre, apellidos, ciudad, direccion, telefono, correo,contrasena, horas, estado);

                cuidador.add(gson.toJson(cuidadores));

            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            con.desconectar();
        }

        return gson.toJson(cuidador);

    }

    @Override
    public String devolver(int id, String username) {

        DBConnection con = new DBConnection();
        String sql = "Delete from cuidado where id= " + id + " and username = '"
                + username + "' limit 1";

        try {
            Statement st = con.getConnection().createStatement();
            st.executeQuery(sql);

            this.sumarCantidad(id);

            return "true";
        } catch (Exception ex) {
            System.out.println(ex.toString());
        } finally {
            con.desconectar();
        }

        return "false";
    }

    @Override
    public String sumarCantidad(int id) {

        DBConnection con = new DBConnection();

        String sql = "Update cuidado set copias = (Select copias from peliculas where id = "
                + id + ") + 1 where id = " + id;

        try {
            Statement st = con.getConnection().createStatement();
            st.executeUpdate(sql);

            return "true";
        } catch (Exception ex) {
            System.out.println(ex.toString());
        } finally {
            con.desconectar();
        }

        return "false";

    }

    @Override
    public String reservar(int id, String username) {

        Timestamp fecha = new Timestamp(new Date().getTime());
        DBConnection con = new DBConnection();
        String sql = "Insert into cuidado values ('" + id + "', '" + username + "', '" + fecha + "')";

        try {
            Statement st = con.getConnection().createStatement();
            st.executeUpdate(sql);

            String modificar = modificar(id);

            if (modificar.equals("true")) {
                return "true";
            }

        } catch (Exception ex) {
            System.out.println(ex.toString());
        } finally {
            con.desconectar();
        }
        return "false";
    }

    @Override
    public String modificar(int id) {

        DBConnection con = new DBConnection();
        String sql = "Update cuidado set copias = (copias - 1) where id = " + id;

        try {
            Statement st = con.getConnection().createStatement();
            st.executeUpdate(sql);

            return "true";
        } catch (Exception ex) {
            System.out.println(ex.toString());
        } finally {
            con.desconectar();
        }

        return "false";

    }

}
