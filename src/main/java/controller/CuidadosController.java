package controller;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;

import com.google.gson.Gson;

import beans.Cuidado;
import connection.DBConnection;

public class CuidadosController implements ICuidadosController {

    @Override
    public String listarCuidados(String username) {

        Gson gson = new Gson();

        DBConnection con = new DBConnection();

        String sql = "Select l.id, l.nombre, l.ciudad, l.estado, a.fecha from cuidador l "
                + "inner join cuidado a on l.id = a.id inner join mascota u on a.username = u.username "
                + "where a.username = '" + username + "'";

        List<String> reservaciones = new ArrayList<String>();

        try {

            Statement st = con.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String ciudad = rs.getString("ciudad");
                boolean estado = rs.getBoolean("estado");
                Date fechaAlquiler = rs.getDate("fecha");

                Cuidado cuidado = new Cuidado(id, nombre, fechaAlquiler, estado, ciudad );

                reservaciones.add(gson.toJson(cuidado));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            con.desconectar();
        }
        return gson.toJson(reservaciones);
    }
}