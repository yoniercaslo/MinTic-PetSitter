package beans;

public class Mascota {

    private String username;
    private String contrasena;
    private String nombre;
    private String tipoMascota;
    private String raza;
    private String tamano;
    private String color;
    private String edad;
    private String sexoMascota;
    private boolean estado;

    public Mascota(String username, String contrasena, String nombre, String tipoMascota, String raza, String tamano, String color, String edad, String sexoMascota, boolean estado) {
        this.username = username;
        this.contrasena = contrasena;
        this.nombre = nombre;
        this.tipoMascota = tipoMascota;
        this.raza = raza;
        this.tamano = tamano;
        this.color = color;
        this.edad = edad;
        this.sexoMascota = sexoMascota;
        this.estado = estado;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
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

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Mascota{" + "username=" + username + ", contrasena=" + contrasena + ", nombre=" + nombre + ", tipoMascota=" + tipoMascota + ", raza=" + raza + ", tamano=" + tamano + ", color=" + color + ", edad=" + edad + ", sexoMascota=" + sexoMascota + ", estado=" + estado + '}';
    }

}
