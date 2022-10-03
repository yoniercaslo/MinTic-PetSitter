package controller;

import java.sql.ResultSet;
import java.sql.Statement;
import com.google.gson.Gson;

import beans.Mascota;
import connection.DBConnection;
import java.util.HashMap;
import java.util.Map;

public class MascotaController implements IMascotaController {

    @Override
    public String login(String username, String contrasena) {

        Gson gson = new Gson();

        DBConnection con = new DBConnection();

        String sql = "Select * from mascota where username = '" + username
                + "' and contrasena = '" + contrasena + "'";
        try {
            Statement st = con.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String tipoMascota = rs.getString("tipoMascota");
                String raza = rs.getString("raza");
                String tamano = rs.getString("tamano");
                String color = rs.getString("color");
                String edad = rs.getString("edad");
                String sexoMascota = rs.getString("sexoMascota");
                boolean estado = rs.getBoolean("estado");

                Mascota mascota
                        = new Mascota(username, contrasena, nombre, tipoMascota, raza, tamano, color, edad, sexoMascota, estado);
                return gson.toJson(mascota);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            con.desconectar();
        }

        return "false";
    }

    @Override
    public String register(String username, String contrasena, String nombre, String tipoMascota, String raza, String tamano, String color,
            String edad, String sexoMascota, boolean estado) {

        Gson gson = new Gson();

        DBConnection con = new DBConnection();
        String sql = "Insert into mascota values('" + username + "', '" + contrasena + "', '" + nombre
                + "', '" + tipoMascota + "', '" + raza + "', '" + tamano + "', '" + color + "', '" + edad + "', '" + sexoMascota + "', " + estado + ")";

        try {
            Statement st = con.getConnection().createStatement();
            st.executeUpdate(sql);

            Mascota mascota = new Mascota(username, contrasena, nombre, tipoMascota, raza, tamano, color, edad, sexoMascota, estado);

            st.close();

            return gson.toJson(mascota);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        } finally {
            con.desconectar();
        }

        return "false";

    }

    @Override
    public String pedir(String username) {

        Gson gson = new Gson();

        DBConnection con = new DBConnection();
        String sql = "Select * from mascota where username = '" + username + "'";

        try {

            Statement st = con.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                String contrasena = rs.getString("contrasena");
                String nombre = rs.getString("nombre");
                String tipoMascota = rs.getString("tipoMascota");
                String raza = rs.getString("raza");
                String tamano = rs.getString("tamano");
                String color = rs.getString("color");
                String edad = rs.getString("edad");
                String sexoMascota = rs.getString("sexoMascota");
                boolean estado = rs.getBoolean("estado");

                Mascota mascota
                        = new Mascota(username, contrasena, nombre, tipoMascota, raza, tamano, color, edad, sexoMascota, estado);
                return gson.toJson(mascota);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            con.desconectar();
        }

        return "false";
    }

    @Override
    public String modificar(String username, String nuevaContrasena,
            String nuevoNombre, String nuevoTipoMascota, String nuevoRaza, String nuevoTamano, String nuevoColor, String nuevoEdad, String nuevoSexoMascota,
            boolean nuevoEstado) {

        DBConnection con = new DBConnection();
        
        //String sql = "Update mascota set contrasena = '" + nuevaContrasena + "', nombre = '" + nuevoNombre + "', " + "apellidos = '" + nuevosApellidos + "', email = '" + nuevoEmail + "', saldo = " + nuevoSaldo + ", premium = ";

        String sql = "Update mascota set contrasena = '" + nuevaContrasena
                + "', nombre = '" + nuevoNombre + "', "
                + "tipoMascota = '" + nuevoTipoMascota + "', " 
                + "raza = '" + nuevoRaza + "', "
                + "tamano = '" + nuevoTamano + "', "
                + "color = '" + nuevoColor + "', "
                + " edad = '" + nuevoEdad + "', "
                + "sexoMascota = '" + nuevoSexoMascota + "', "
                + "estado = ";

        if (nuevoEstado == true) {
            sql += " 1 ";
        } else {
            sql += " 0 ";
        }

        sql += " where username = '" + username + "'";

        try {

            Statement st = con.getConnection().createStatement();
            st.executeUpdate(sql);

            return "true";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            con.desconectar();
        }

        return "false";

    }

    public String eliminar(String username) {

        DBConnection con = new DBConnection();

        String sql1 = "Delete from cuidador where username = '" + username + "'";
        String sql2 = "Delete from mascota where username = '" + username + "'";

        try {
            Statement st = con.getConnection().createStatement();
            st.executeUpdate(sql1);
            st.executeUpdate(sql2);

            return "true";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            con.desconectar();
        }

        return "false";
    }

    @Override
    public String verCuidados(String username) {

        DBConnection con = new DBConnection();
        String sql = "Select id,count(*) as num_cuidados from cuidado where username = '"
                + username + "' group by id;";

        Map<Integer, Integer> cuidados = new HashMap<Integer, Integer>();

        try {

            Statement st = con.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                int num_cuidados = rs.getInt("num_cuidados");

                cuidados.put(id, num_cuidados);
            }

            cancelarCuidado(username, cuidados);

            return "true";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            con.desconectar();
        }

        return "false";

    }

    @Override
    public String cancelarCuidado(String username, Map<Integer, Integer> cuidados) {

        DBConnection con = new DBConnection();

        try {
            for (Map.Entry<Integer, Integer> cuidador : cuidados.entrySet()) {
                int id = cuidador.getKey();
                int num_cuidados = cuidador.getValue();

                String sql = "Update cuidador set cuidados = (Select cuidados + " + num_cuidados
                        + " from cuidador where id = " + id + ") where id = " + id;

                Statement st = con.getConnection().createStatement();
                st.executeUpdate(sql);

            }

            this.eliminar(username);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            con.desconectar();
        }
        return "false";
    }

    /*@Override
    public String restarDinero(String username, double nuevoSaldo) {

        DBConnection con = new DBConnection();
        String sql = "Update usuario set saldo = " + nuevoSaldo + " where username = '" + username + "'";

        try {

            Statement st = con.getConnection().createStatement();
            st.executeUpdate(sql);

            return "true";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            con.desconectar();
        }

        return "false";
    }*/
}
