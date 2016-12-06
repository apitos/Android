package appcreator.appexample.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import appcreator.appexample.modelo.registro;
import appcreator.appexample.modelo.usuario;
import appcreator.appexample.sqlite.BD.TABLAS;
import appcreator.appexample.sqlite.RegistroUsuario.Usuario;
import appcreator.appexample.sqlite.RegistroUsuario.Registro;
/**
 * Created by Elvis on 28/11/2016.
 */
public class controladores {
    private static BD bd;
    private static controladores instacia = new controladores();

    private controladores(){
    }

    public static controladores obtenerinstancia(Context context){
        if (bd == null){
            bd = new BD(context);
        }
        return instacia;
    }
    public Cursor ObtenerUsuario(){
        SQLiteDatabase db = bd.getReadableDatabase();
        String sql = String.format("SELECT * From %s", TABLAS.USUARIOS);
        return db.rawQuery(sql, null);
    }

    public Cursor correo(){
        SQLiteDatabase db = bd.getWritableDatabase();
        String query = String.format("SELECT * FROM %s", TABLAS.USUARIOS);
        Cursor c = db.rawQuery(query, null);

        if (c != null) {
            c.moveToFirst();
        }

        return c;
    }

    public String InsertUser(usuario user){
        SQLiteDatabase db = bd.getWritableDatabase();
        ContentValues val = new ContentValues();

        val.put(Usuario.CORREO, user.correo);
        val.put(Usuario.PASS, user.password);

        db.insertOrThrow(TABLAS.USUARIOS, null, val);
        return null;
    }

    public boolean UpdateUser(usuario user){
        SQLiteDatabase db = bd.getWritableDatabase();
        ContentValues val = new ContentValues();

        val.put(Usuario.CORREO, user.correo);
        val.put(Usuario.PASS, user.password);
        String whereClasue = String.format("%s=?", Usuario.ID);
        String [] WhereArgs = {user.iduser};

        int res = db.update(TABLAS.USUARIOS, val, whereClasue, WhereArgs);

        return res>0;
    }

    public boolean DeleteUser(String idUser){
        SQLiteDatabase db =bd.getWritableDatabase();
        String whereClause = String.format("%s = ?", Usuario.ID);
        String [] whereArgs = {idUser};
        int res = db.delete(TABLAS.USUARIOS, whereClause, whereArgs);
        return res>0;
    }
    public Cursor ObtenerRegistro(int id){
        SQLiteDatabase db = bd.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s WHERE %s = %s", TABLAS.REGISTROS, Registro.ID, id);
        Cursor c= db.rawQuery(sql, null);
        if(c!=null){
            c.moveToFirst();
        }
        return c;
    }

    public Cursor ListarRegistros(){
        SQLiteDatabase db = bd.getReadableDatabase();
        String query = String.format("SELECT * FROM %s WHERE 1 ORDER BY %s",TABLAS.REGISTROS, Registro.APELLIDO);
        Cursor c = db.rawQuery(query, null);

        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public String InsertReg(registro reg){
        SQLiteDatabase db = bd.getWritableDatabase();
        ContentValues val = new ContentValues();

        val.put(Registro.NOMBRE, reg.Nombre);
        val.put(Registro.APELLIDO, reg.Apellido);
        val.put(Registro.TELEFONO, reg.Telefono);

        db.insertOrThrow(TABLAS.REGISTROS, null, val);
        return null;
    }

    public void UpdateReg(registro reg){
        SQLiteDatabase db = bd.getWritableDatabase();
        ContentValues val = new ContentValues();

        val.put(Registro.NOMBRE, reg.Nombre);
        val.put(Registro.APELLIDO, reg.Apellido);
        val.put(Registro.TELEFONO, reg.Telefono);
        String whereClasue = String.format("%s=?", Registro.ID);
        String [] WhereArgs = {reg.idRegistro};

        db.update(TABLAS.REGISTROS, val, whereClasue, WhereArgs);
    }

    public void DeleteReg(String idReg){
        SQLiteDatabase db =bd.getWritableDatabase();
        db.execSQL(String.format("DELETE FROM %s WHERE %s = %s", TABLAS.REGISTROS, Registro.ID, idReg));
        db.close();
    }

    public SQLiteDatabase getDb() {
        return bd.getWritableDatabase();
    }

}
