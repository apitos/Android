package appcreator.appexample.modelo;

/**
 * Created by Elvis on 26/11/2016.
 */
public class registro {
    public String idRegistro;
    public String Nombre;
    public String Apellido;
    public String Telefono;

    public registro(String idRegistro, String Nombre, String Apellido, String Telefono){
        this.idRegistro = idRegistro;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.Telefono = Telefono;
    }

    public String getIdRegistro(){
        return idRegistro;
    }
    public void setIdRegistro(String idregistro){
        this.idRegistro = idregistro;
    }
    public String getNombre(){
        return Nombre;
    }
    public void setNombre(String nombre){
        this.Nombre = nombre;
    }
    public String getApellido(){
        return Apellido;
    }
    public void setApellido(String apellido){
        this.Apellido = apellido;
    }
    public String getTelefono(){
        return Telefono;
    }
    public void setTelefono(String telefono){
        this.Telefono = telefono;
    }
}
