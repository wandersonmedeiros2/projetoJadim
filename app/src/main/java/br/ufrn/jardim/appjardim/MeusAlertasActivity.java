package br.ufrn.jardim.appjardim;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Random;

import br.ufrn.jardim.adapters.AlertaListAdapter;
import br.ufrn.jardim.modelo.Alerta;


public class MeusAlertasActivity extends ActionBarActivity {
    Toolbar toobarPrincipal;
    ListView listAlertas;


    public void inicializaComponentes(){

        toobarPrincipal = (Toolbar) findViewById(R.id.toolbarPincipal);
        setSupportActionBar(toobarPrincipal);

        listAlertas = (ListView) findViewById(R.id.listAlertas);

        ArrayList<Alerta> alertas = new ArrayList<Alerta>();

        Random ram = new Random();

        for(int i = 0; i < 10;i++){

            Alerta alerta = new Alerta("alerta " + String.valueOf(i+1));

            if(ram.nextInt(1024) % 2 == 0)
                alerta.setAtivo(true);
            else
                alerta.setAtivo(false);

            alertas.add(alerta);
        }



        AlertaListAdapter adapter = new AlertaListAdapter(this, alertas);
        listAlertas.setAdapter(adapter);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meus_alertas);

        inicializaComponentes();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_meus_alertas, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
