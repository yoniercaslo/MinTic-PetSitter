package beans;

public class Mascota {
    
    private String idMascota;
    private String nombre;
    private String tipoMascota;
    private String raza;
    private String tamano;
    private String color;
    private String edad;
    private String sexoMascota;
    private String cedulaUsuario;
    private boolean estado;

    public Mascota(String idMascota, String nombre, String tipoMascota, String raza, String tamano, String color, String edad, String sexoMascota, String cedulaUsuario, boolean estado) {
        this.idMascota = idMascota;
        this.nombre = nombre;
        this.tipoMascota = tipoMascota;
        this.raza = raza;
        this.tamano = tamano;
        this.color = color;
        this.edad = edad;
        this.sexoMascota = sexoMascota;
        this.cedulaUsuario = cedulaUsuario;
        this.estado = estado;
    }

    public String getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(String idMascota) {
        this.idMascota = idMascota;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoMascota() {
        return tipoMascota;
    }

    public void setTipoMascota(String tipoMascota) {
        this.tipoMascota = tipoMascota;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getTamano() {
        return tamano;
    }

    public void setTamano(String tamano) {
        this.tamano = tamano;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getSexoMascota() {
        return sexoMascota;
    }

    public void setSexoMascota(String sexoMascota) {
        this.sexoMascota = sexoMascota;
    }

    public String getCedulaUsuario() {
        return cedulaUsuario;
    }

    public void setCedulaUsuario(String cedulaUsuario) {
        this.cedulaUsuario = cedulaUsuario;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "mascota{" + "idMascota=" + idMascota + ", nombre=" + nombre + ", tipoMascota=" + tipoMascota + ", raza=" + raza + ", tamano=" + tamano + ", color=" + color + ", edad=" + edad + ", sexoMascota=" + sexoMascota + ", cedulaUsuario=" + cedulaUsuario + ", estado=" + estado + '}';
    }
    
    
    
}
