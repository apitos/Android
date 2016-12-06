package appcreator.appexample;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import appcreator.appexample.controlador.TodoCursorAdapter;
import appcreator.appexample.modelo.registro;
import appcreator.appexample.sqlite.RegistroUsuario.*;
import appcreator.appexample.sqlite.controladores;

/**
 * Created by Elvis on 29/11/2016.
 */
public class ActivityAgregar extends ActionBarActivity {

    Button add;
    EditText name;
    EditText lastname;
    EditText phone;
    controladores data;
    CheckBox check;
    Session session = null;
    Context context = null;
    ProgressDialog pdialog = null;
    String rec, subject, textMessage;
    Cursor xc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();
        final String id = i.getStringExtra("idReg");

        name = (EditText) findViewById(R.id.nombre_input);
        lastname = (EditText) findViewById(R.id.apellido_input);
        phone = (EditText) findViewById(R.id.telefono_input);
        add = (Button) findViewById(R.id.agregar_btn);
        check = (CheckBox) findViewById(R.id.ckecksito);

        context = this;
        data = controladores.obtenerinstancia(getApplicationContext());

        //Log.d("id",id);
        if(id != null) {
            int x = Integer.valueOf(id);
            xc = data.ObtenerRegistro(x);

            name.setText(xc.getString(xc.getColumnIndexOrThrow(Registro.NOMBRE)));
            lastname.setText(xc.getString(xc.getColumnIndexOrThrow(Registro.APELLIDO)));
            phone.setText(xc.getString(xc.getColumnIndexOrThrow(Registro.TELEFONO)));
        }
    }
    public void guardar(){
        Intent i = getIntent();
        String idx = i.getStringExtra("idReg");

        if(idx != null) {
            data.UpdateReg(new registro(idx, name.getText().toString(), lastname.getText().toString(), phone.getText().toString()));
            Toast.makeText(getApplicationContext(), "Actualizado!", Toast.LENGTH_LONG).show();
            Intent in = new Intent(getApplicationContext(), ActivityListar.class);
            finish();
            startActivity(in);
        }else {
            data.InsertReg(new registro(null, name.getText().toString(), lastname.getText().toString(), phone.getText().toString()));
            Toast.makeText(getApplicationContext(), "Guardado!", Toast.LENGTH_LONG).show();
        }
        if(check.isChecked()){

            Cursor c = data.correo();
            rec = c.getString(c.getColumnIndexOrThrow("correo"));
            Log.d("Correo", rec);
            subject = "Nuevo Contacto AppExample";
            textMessage = name.getText().toString()+" "+lastname.getText().toString()+" "+phone.getText().toString();

            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");

            session = Session.getDefaultInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("apppueva@gmail.com", "appprueba123");
                }
            });

            pdialog = ProgressDialog.show(context, "", "Sending Mail...", true);

            RetreiveFeedTask task = new RetreiveFeedTask();
            task.execute();
        }else{
            name.setText("");
            lastname.setText("");
            phone.setText("");
        }
    }

    class RetreiveFeedTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            try{
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("testfrom354@gmail.com"));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(rec));
                message.setSubject(subject);
                message.setContent(textMessage, "text/html; charset=utf-8");
                Transport.send(message);
            } catch(MessagingException e) {
                e.printStackTrace();
            } catch(Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            pdialog.dismiss();
            name.setText("");
            lastname.setText("");
            phone.setText("");

            Toast.makeText(getApplicationContext(), "Enviado!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_agregar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                Log.i("ActionBar", "Atrás!");
                finish();
                return true;
            case R.id.ok:
                guardar();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
