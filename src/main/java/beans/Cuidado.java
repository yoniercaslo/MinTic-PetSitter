package beans;
import java.sql.Date;
public class Cuidado {
    
    private String idCuidado;
    private String idUsuario;
    private Date fecha;

    public Cuidado(String idCuidado, String idUsuario, Date fecha) {
        this.idCuidado = idCuidado;
        this.idUsuario = idUsuario;
        this.fecha = fecha;
    }

    public String getIdCuidado() {
        return idCuidado;
    }

    public void setIdCuidado(String idCuidado) {
        this.idCuidado = idCuidado;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "cuidado{" + "idCuidado=" + idCuidado + ", idUsuario=" + idUsuario + ", fecha=" + fecha + '}';
    }
    
    
  
}
