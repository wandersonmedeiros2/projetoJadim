package br.ufrn.jardim.appjardim;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import br.ufrn.jardim.adapters.GerenciarBluetooth;
import br.ufrn.jardim.dao.VasoDAO;
import br.ufrn.jardim.modelo.Vaso;


public class VisualizarVaso extends ActionBarActivity {

    TextView labelDescricao;
    TextView labelTemp;
    TextView labelLuz;
    TextView labelUmidSolo;
    TextView labelUmidAr;
    Toolbar mToolbar;
    Vaso vaso;
    VasoDAO vasoDAO;
    GerenciarBluetooth gerenciarBluetooth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_vaso);

        vasoDAO = new VasoDAO(this.getApplicationContext());

        mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar_visualiza_vaso);
        setSupportActionBar(mToolbar);

        gerenciarBluetooth = new GerenciarBluetooth(this.getApplicationContext());

        labelDescricao = (TextView) findViewById(R.id.textView10);
        labelTemp = (TextView) findViewById(R.id.labelTemp);
        labelLuz = (TextView) findViewById(R.id.Labelluminosidade);
        labelUmidSolo = (TextView) findViewById(R.id.labelUmidadeSolo);
        labelUmidAr = (TextView) findViewById(R.id.labelUmidadeAr);

        if(this.getIntent().getExtras() != null) {
                vaso =  (Vaso) this.getIntent().getSerializableExtra("vaso");
                labelDescricao.setText(vaso.getDescricao().toString());
                labelTemp.setText(String.valueOf(vaso.getTemperatura()));
                labelUmidSolo.setText(String.valueOf(vaso.getUmidadeSolo()));
                labelLuz.setText(String.valueOf(vaso.getLuminosidade()));
                labelUmidAr.setText(String.valueOf(vaso.getUmidadeAr()));

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_visualizar_vaso, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        switch (id){

            case R.id.action_excluir:
                vasoDAO.remover(vaso);
                finish();
                break;
            case R.id.action_Regar:
                gerenciarBluetooth.EnviarComando(vaso);
                break;
            case R.id.action_atualizar:
                gerenciarBluetooth.AtualizarInfoVaso(vaso);
                labelDescricao.setText(vaso.getDescricao().toString());
                labelTemp.setText(String.valueOf(vaso.getTemperatura()));
                labelUmidSolo.setText(String.valueOf(vaso.getUmidadeSolo()));
                labelLuz.setText(String.valueOf(vaso.getLuminosidade()));
                labelUmidAr.setText(String.valueOf(vaso.getUmidadeAr()));
                break;
        }


        return super.onOptionsItemSelected(item);
    }
}
