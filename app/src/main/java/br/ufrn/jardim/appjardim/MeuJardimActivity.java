package br.ufrn.jardim.appjardim;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.Random;

import br.ufrn.jardim.adapters.VasoListAdapter;
import br.ufrn.jardim.dao.VasoDAO;
import br.ufrn.jardim.modelo.Vaso;


public class MeuJardimActivity extends ActionBarActivity {

    Toolbar toobarPrincipal;
    ListView listVasos;
    VasoDAO vasoDAO;


    public void inicializaComponentes(){

        toobarPrincipal = (Toolbar) findViewById(R.id.toolbar_actionbar_meu_jardim);
        setSupportActionBar(toobarPrincipal);



        listVasos = (ListView) findViewById(R.id.listVasos);

        ArrayList<Vaso> vasos = new ArrayList<Vaso>();


//        Random ram = new Random();
//
//        for(int i = 0; i < 10;i++){
//
//            Vaso vaso = new Vaso("vaso " + String.valueOf(i+1));
//            vaso.setAtLuminosidade(ram.nextInt(1024));
//            vaso.setAtTemperatura(ram.nextInt(1024));
//            vaso.setAtumidadeAr(ram.nextInt(1024));
//            vaso.setAtUmidadeSolo(ram.nextInt(1024));
//            vasos.add(vaso);
//        }



        VasoListAdapter adapter = new VasoListAdapter(this, vasos);
        listVasos.setAdapter(adapter);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meu_jardim);

        inicializaComponentes();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_meu_jardim, menu);
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
