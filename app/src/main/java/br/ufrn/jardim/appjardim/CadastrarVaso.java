package br.ufrn.jardim.appjardim;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

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

    BluetoothAdapter bluetooth;
    private BluetoothSocket Socket = null;
    private BluetoothDevice device = null;
    private InputStream InStream = null;
    private OutputStream OutStream = null;
    String address;
    private  UUID MY_UUID = /*UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");/*/ UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    byte[] read = new byte[1024];
    int bytes;
    private byte[] msg = null;

    int v = 0;
    Vaso vaso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_vaso);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_CadastrarVaso);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        bluetooth  = BluetoothAdapter.getDefaultAdapter();


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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void cadastrarVaso(View view) {

        if(editTemp.getText().toString().isEmpty())
        {
            editTemp.setError("Campo Obrigatorio");
        }

        if(editTemp.getText().toString().isEmpty())
        {
            editUmidSolo.setError("Campo Obrigatorio");
        }

        if(editTemp.getText().toString().isEmpty())
        {
            editLuz.setError("Campo Obrigatorio");
        }

        if(editTemp.getText().toString().isEmpty())
        {
            editUmidAr.setError("Campo Obrigatorio");
        }

        if(v != 1) {
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
        else {

            EnviarComando();

        }

    }

    private void EnviarComando2() {
        address = vaso.getMAC();
        conectar();
        msg = "P".getBytes();
        try {
            OutStream.write(msg);
            for(int i =0;i<4;i++) {
                bytes = InStream.read(read);
                if (read != null) {
                    String readMessage = new String(read);
                    Toast.makeText(this, readMessage, Toast.LENGTH_SHORT).show();
                    read = new byte[1024];d
                    //Ou tentar desta maneira: list_view.setAdapter(null); list_view.setAdapter(adapter);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void EnviarComando() {
        address = vaso.getMAC();
        conectar();
        msg = "P".getBytes();
        try {
            OutStream.write(msg);
            bytes = InStream.read(read);
            if(read != null) {
                String readMessage = new String(read);
                Toast.makeText(this, readMessage, Toast.LENGTH_SHORT).show();
                read = new byte[1024];
                //Ou tentar desta maneira: list_view.setAdapter(null); list_view.setAdapter(adapter);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void conectar() {

        // Obtem o dispositivo bluetooth
        device = bluetooth.getRemoteDevice(address);
        Toast.makeText(this, "Endereço" + address, Toast.LENGTH_LONG).show();
        try{
            //MY_UUID = UUID.randomUUID();
            Socket = device.createRfcommSocketToServiceRecord(MY_UUID);
            Socket.connect(); //conectado
            InStream = Socket.getInputStream();
            OutStream = Socket.getOutputStream();
        }
        catch (IOException e){
            Toast.makeText(this, "Ocorreu um erro!" + e, Toast.LENGTH_LONG).show();
        }
    }
}
