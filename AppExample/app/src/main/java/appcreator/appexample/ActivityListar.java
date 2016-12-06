package appcreator.appexample;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import appcreator.appexample.controlador.TodoCursorAdapter;
import appcreator.appexample.sqlite.controladores;

/**
 * Created by Elvis on 30/11/2016.
 */
public class ActivityListar extends ActionBarActivity {

    controladores data;
    TodoCursorAdapter todoAdapter;
    ListView lvlitems;
    Cursor c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);

        data = controladores.obtenerinstancia(getApplicationContext());
        c = data.ListarRegistros();
        lvlitems = (ListView) findViewById(R.id.lvlitems);
        lvlitems.setTextFilterEnabled(true);
        todoAdapter = new TodoCursorAdapter(this, c);
        lvlitems.setAdapter(todoAdapter);
        registerForContextMenu(lvlitems);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_listar, menu);
    }

    @Override
    protected void onResume() {
        super.onResume();
        c = data.ListarRegistros();
        todoAdapter.swapCursor(c);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()){
            case R.id.delete:
                data = controladores.obtenerinstancia(getApplicationContext());
                data.DeleteReg(String.valueOf(info.id));
                c = data.ListarRegistros();
                todoAdapter.swapCursor(c);
                return true;
            case R.id.update:
                Intent i = new Intent(getApplicationContext(), ActivityAgregar.class);
                i.putExtra("idReg", String.valueOf(info.id));
                startActivity(i);
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.add:
                Intent i = new Intent(getApplicationContext(), ActivityAgregar.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

