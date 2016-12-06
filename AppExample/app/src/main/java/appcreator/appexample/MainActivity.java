package appcreator.appexample;

import android.content.Intent;
import android.database.DatabaseUtils;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;

import appcreator.appexample.modelo.registro;
import appcreator.appexample.modelo.usuario;
import appcreator.appexample.sqlite.controladores;

public class MainActivity extends AppCompatActivity {

    controladores data;
    EditText email;
    EditText pass;
    Button aceptar;
    /*public class PruebaDatos extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {

            try {

                data.getDb().beginTransaction();
                //Insertar User
                String user1 = data.InsertUser( new usuario(null, "appexample@hotmail.com", "123456"));
                String user2 = data.InsertUser( new usuario(null, "elivis@hotmail.com", "142536"));

                //Insertar Registro
                String reg1 = data.InsertReg(new registro(null,"Elvis", "Segovia", "0985788833"));
                String reg2 = data.InsertReg(new registro(null,"App", "Example", "0994612520"));

                data.getDb().setTransactionSuccessful();

            }finally {
                data.getDb().endTransaction();
            }

            Log.d("Usuario", "Usuarios");
            DatabaseUtils.dumpCursor(data.ObtenerUsuario());
            Log.d("Registro", "Registros");
            DatabaseUtils.dumpCursor(data.ObtenerRegistro());

            return null;
        }
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        email = (EditText) findViewById(R.id.email_input);
        pass = (EditText) findViewById(R.id.pass_input);
        aceptar = (Button) findViewById(R.id.aceptar_btn);

        //getApplicationContext().deleteDatabase("prueba.db");
        data = controladores.obtenerinstancia(getApplicationContext());
        //new PruebaDatos().execute();
        if (data.ObtenerUsuario().moveToNext()){
            //finish();
            Intent i = new Intent(this, ActivityListar.class);
            startActivity(i);
        }else {
            aceptar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    data.InsertUser(new usuario(null, email.getText().toString(), pass.getText().toString()));
                    Intent i = new Intent(getApplicationContext(), ActivityMenu.class);
                    startActivity(i);
                }
            });
        }
    }
}
