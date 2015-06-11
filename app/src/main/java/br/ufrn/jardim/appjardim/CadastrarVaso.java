package br.ufrn.jardim.appjardim;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import br.ufrn.jardim.dao.VasoDAO;
import br.ufrn.jardim.modelo.Vaso;


public class CadastrarVaso extends ActionBarActivity {

    private Toolbar mToolbar;
    private EditText editMac;
    private EditText editTemp;
    private EditText editUmidSolo;
    private EditText editUmidAr;
    private EditText editLuz;
    private EditText editDescricao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_vaso);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_CadastrarVaso);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        editMac = (EditText)findViewById(R.id.editMac);
        editTemp = (EditText)findViewById(R.id.editTemp);
        editUmidSolo = (EditText)findViewById(R.id.editUmidSolo);
        editUmidAr = (EditText)findViewById(R.id.editUmidAr);
        editLuz = (EditText)findViewById(R.id.editLuz);
        editDescricao = (EditText)findViewById(R.id.editDescricao);

        if(this.getIntent().getExtras() != null) {
            editMac.setText(this.getIntent().getStringExtra(DispPareadosActivity.EXTRA_DEVICE_ADDRESS));
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cadastrar_vaso, menu);
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

    public void cadastrarVaso(View view) {

        Vaso vaso = new Vaso();

        vaso.setLuminosidade(Integer.valueOf(editLuz.getText().toString()));
        vaso.setTemperatura(Integer.valueOf(editTemp.getText().toString()));
        vaso.setUmidadeAr(Integer.valueOf(editUmidAr.getText().toString()));
        vaso.setAtUmidadeSolo(Integer.valueOf(editUmidSolo.getText().toString()));
        vaso.setDescricao(editDescricao.getText().toString());
        vaso.setMAC(editMac.getText().toString());

        VasoDAO vasoDAO = new VasoDAO(this.getApplicationContext());
        vasoDAO.inserirOuAtualizar(vaso);
        finish();
    }
}
