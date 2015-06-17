package br.ufrn.jardim.appjardim;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import br.ufrn.jardim.adapters.GerenciarBluetooth;
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

    GerenciarBluetooth gerenciarBluetooth;

    int v = 0;
    Vaso vaso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_vaso);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_CadastrarVaso);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        gerenciarBluetooth  = new GerenciarBluetooth(this.getApplicationContext());


        editMac = (EditText)findViewById(R.id.editMac);
        editTemp = (EditText)findViewById(R.id.editTemp);
        editUmidSolo = (EditText)findViewById(R.id.editUmidSolo);
        editUmidAr = (EditText)findViewById(R.id.editUmidAr);
        editLuz = (EditText)findViewById(R.id.editLuz);
        editDescricao = (EditText)findViewById(R.id.editDescricao);

        if(this.getIntent().getExtras() != null) {

            v = this.getIntent().getIntExtra("tipo",0);

            if(v == 1)
            {
                vaso =  (Vaso) this.getIntent().getSerializableExtra("dv");

                editDescricao.setText(vaso.getDescricao());
                editMac.setText(vaso.getMAC());
                editTemp.setText(String.valueOf(vaso.getTemperatura()));
                editUmidSolo.setText(String.valueOf(vaso.getUmidadeSolo()));
                editLuz.setText(String.valueOf(vaso.getLuminosidade()));
                editUmidAr.setText(String.valueOf(vaso.getUmidadeAr()));

            }
            else
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
        if (id == R.id.menu_atualizar_vaso) {
            gerenciarBluetooth.AtualizarInfoVaso(vaso);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void cadastrarVaso(View view) {
        boolean semErro = true;

        if (editTemp.getText().toString().isEmpty()) {
            editTemp.setError("Campo Obrigatorio");
            semErro = false;
        }

        if (editUmidSolo.getText().toString().isEmpty()) {
            editUmidSolo.setError("Campo Obrigatorio");
            semErro = false;
        }

        if (editLuz.getText().toString().isEmpty()) {
            editLuz.setError("Campo Obrigatorio");
            semErro = false;
        }

        if (editUmidAr.getText().toString().isEmpty()) {
            editUmidAr.setError("Campo Obrigatorio");
            semErro = false;
        }

        if (semErro){
            if (v != 1) {
                Vaso vaso = new Vaso();

                vaso.setLuminosidade(Integer.valueOf(editLuz.getText().toString()));
                vaso.setTemperatura(Integer.valueOf(editTemp.getText().toString()));
                vaso.setUmidadeAr(Integer.valueOf(editUmidAr.getText().toString()));
                vaso.setUmidadeSolo(Integer.valueOf(editUmidSolo.getText().toString()));
                vaso.setDescricao(editDescricao.getText().toString());
                vaso.setMAC(editMac.getText().toString());

                VasoDAO vasoDAO = new VasoDAO(this.getApplicationContext());
                vasoDAO.inserirOuAtualizar(vaso);
                finish();
            } else {
                gerenciarBluetooth.EnviarComando(vaso);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        gerenciarBluetooth.desconectar();

    }
}
