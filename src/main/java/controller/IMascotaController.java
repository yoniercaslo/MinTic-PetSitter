package controller;

import java.util.Map;

public interface IMascotaController {

    public String login(String username, String contrasena);

    public String register(String username, String contrasena, 
            String nombre, String tipoMascota, String raza, String tamano, String color, String edad, String sexoMascota, boolean estado);

    public String pedir(String username);

    public String modificar(String username, String nuevaContrasena,
            String nuevoNombre, String nuevoTipoMascota, String nuevoRaza, String nuevoTamano, String nuevoColor, String nuevoEdad, String nuevoSexoMascota,
            boolean nuevoEstado);
    
    public String eliminar(String username);

    public String verCuidados(String username);

    public String cancelarCuidado(String username, Map<Integer, Integer> cuidados);

    /*public String restarDinero(String username, double nuevoSaldo);*/
}
