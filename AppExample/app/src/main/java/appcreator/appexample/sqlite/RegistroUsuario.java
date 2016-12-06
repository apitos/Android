package appcreator.appexample.sqlite;

import java.util.UUID;

/**
 * Created by Elvis on 26/11/2016.
 */
public class RegistroUsuario {
    interface ColumnasRegistro{
        String ID = "_id";
        String NOMBRE = "nombre";
        String APELLIDO = "apellido";
        String TELEFONO = "telefono";
    }
    interface ColumnasUsuario{
        String ID = "_id";
        String CORREO = "correo";
        String PASS = "password";
    }
    public static class Registro implements ColumnasRegistro{
        public static String generarIdRegistro(){
            return "REG-"+ UUID.randomUUID().toString();
        }
    }
    public static class Usuario implements ColumnasUsuario{
        public static String generarIdUsuario(){
            return "USR-"+ UUID.randomUUID().toString();
        }
    }

    private RegistroUsuario(){
    }
}
