package appcreator.appexample.modelo;

import java.io.StringReader;

/**
 * Created by Elvis on 26/11/2016.
 */
public class usuario {
    public String iduser;
    public String correo;
    public String password;

    public usuario(String iduser, String correo, String password){
        this.iduser = iduser;
        this.correo = correo;
        this.password = password;
    }
    public String getIdUser(){
        return iduser;
    }
    public void setIduser(String IdUser){
        this.iduser=IdUser;
    }
    public String getCorreo(){
        return correo;
    }
    public void setCorreo(String Correo){
        this.correo = Correo;
    }
    public String getPass(){
        return password;
    }
    public void setPass(String Pass){
        this.password = Pass;
    }
}
