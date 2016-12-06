package appcreator.appexample.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.provider.BaseColumns;
import android.util.Log;

import appcreator.appexample.sqlite.RegistroUsuario.Registro;
import appcreator.appexample.sqlite.RegistroUsuario.Usuario;

/**
 * Created by Elvis on 26/11/2016.
 */
public class BD extends SQLiteOpenHelper{
    private static final String DBNAME = "prueba.db";
    private static final int VERSION = 1;

    interface TABLAS{
        String USUARIOS = "usuarios";
        String REGISTROS = "registros";
    }
    interface Referecias{
        String ID_USER  = String.format("REFERENCES %s(%s) ON DELETE CASCADE",
                TABLAS.USUARIOS, Usuario.ID);
        String ID_REG =  String.format("REFERENCES %s(%s)",
                TABLAS.REGISTROS, Registro.ID);
    }

    public BD(Context contexto){
        super(contexto, DBNAME, null, VERSION);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                db.setForeignKeyConstraintsEnabled(true);
            } else {
                db.execSQL("PRAGMA foreign_keys=ON");
            }
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                "%s TEXT NOT NULL, %s TEXT NOT NULL)",
                TABLAS.USUARIOS, Usuario.ID,
                Usuario.CORREO, Usuario.PASS));
        db.execSQL(String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "%s TEXT NOT NULL, %s TEXT NOT NULL, %s TEXT NOT NULL)",
                TABLAS.REGISTROS, Registro.ID,
                Registro.NOMBRE, Registro.APELLIDO, Registro.TELEFONO));

        Log.i(this.getClass().toString(), "Base de Datos Creada");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST " + TABLAS.USUARIOS);
        db.execSQL("DROP TABLE IF EXIST " + TABLAS.REGISTROS);
        onCreate(db);
    }
}


