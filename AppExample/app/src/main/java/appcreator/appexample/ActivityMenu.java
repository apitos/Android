package appcreator.appexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Elvis on 29/11/2016.
 */
public class ActivityMenu extends ActionBarActivity {

    Button agregar;
    Button listar;
    Button eliminar;
    Button editar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        agregar = (Button) findViewById(R.id.agregarbtn);
        listar = (Button) findViewById(R.id.listarbtn);
        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ActivityAgregar.class);
                startActivity(i);
            }
        });

        listar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ActivityListar.class);
                startActivity(i);
            }
        });
    }
}
