package beans;
import java.sql.Date;
public class Cuidado {
    
    private int id;
    private String username;
    private Date fecha;
    private boolean novedad;
    private String ciudad;

    public Cuidado(int id, String username, Date fecha, boolean novedad, String ciudad) {
        this.id = id;
        this.username = username;
        this.fecha = fecha;
        this.novedad = novedad;
        this.ciudad = ciudad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean isNovedad() {
        return novedad;
    }

    public void setNovedad(boolean novedad) {
        this.novedad = novedad;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    @Override
    public String toString() {
        return "Cuidado{" + "id=" + id + ", username=" + username + ", fecha=" + fecha + ", novedad=" + novedad + ", ciudad=" + ciudad + '}';
    }

            
}
