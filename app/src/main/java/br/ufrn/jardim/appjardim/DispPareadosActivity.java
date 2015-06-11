package br.ufrn.jardim.appjardim;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class DispPareadosActivity extends ActionBarActivity {

    public  static String EXTRA_DEVICE_ADDRESS = "device_address";
    private static final int REQUEST_HABILITAR_BT = 1;
    private static final int REQUEST_DISPOSITIVO_PAREADO = 2;
    private static final int REQUEST_NOVO_DISPOSITIVO = 3;
    private static final int REQUEST_NOVO_VASO = 4;
    private BluetoothAdapter bluetooth;
    private ArrayAdapter disp_pareados;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disp_pareados);

        mToolbar = (Toolbar) findViewById(R.id.toolbar_disp_pareados);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        bluetooth = BluetoothAdapter.getDefaultAdapter();
        checarBluetooth();

        disp_pareados = new ArrayAdapter(this,R.layout.nome_dispositivo);

        ListView pareados_lista = (ListView)findViewById(R.id.listDispPareados);

        // Pega o conjunto de dispositivos ja pareados

        Set<BluetoothDevice> pareados = bluetooth.getBondedDevices();

        if (pareados.size() > 0) {
            for (BluetoothDevice dv : pareados) {
                disp_pareados.add(dv.getName() + "\n" + dv.getAddress());
            }
        } else {
            disp_pareados.add("Nenhum Dispositivo Pareado");
        }

        pareados_lista.setAdapter(disp_pareados);
        pareados_lista.setOnItemClickListener(disp_pareadosListener);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case REQUEST_HABILITAR_BT :
                if(resultCode == RESULT_OK){
                    //checarBluetooth();
                }
                break;
            case REQUEST_DISPOSITIVO_PAREADO:
                if(resultCode == RESULT_OK){
                    setResult(RESULT_OK,this.getIntent());
                    finish();
                }
                break;
            case REQUEST_NOVO_DISPOSITIVO:
                if(resultCode == RESULT_OK){
                    setResult(RESULT_OK,this.getIntent());
                    Intent i = new Intent(this,CadastrarVaso.class);
                    i.putExtra(this.getIntent().getStringExtra(ProcurarDispositivosBluetooth.EXTRA_DEVICE_ADDRESS),ProcurarDispositivosBluetooth.EXTRA_DEVICE_ADDRESS);
                    startActivity(i);
                    finish();
                }
                break;
            case REQUEST_NOVO_VASO:
                if(resultCode == RESULT_OK){
                    finish();
                }
                break;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_disp_pareados, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_disp_pareados) {
            startActivityForResult(new Intent(this,ProcurarDispositivosBluetooth.class),REQUEST_NOVO_DISPOSITIVO);
        }

        return super.onOptionsItemSelected(item);
    }

    private void checarBluetooth(){
        if(!bluetooth.isEnabled()){
            startActivityForResult(new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE),0);
        }
    }

    private AdapterView.OnItemClickListener disp_pareadosListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            bluetooth.cancelDiscovery();

            String info = ((TextView) view).getText().toString();
            String address = info.substring(info.length() - 17);

            Intent intent = new Intent();
            intent.putExtra(EXTRA_DEVICE_ADDRESS, address);
            setResult(RESULT_OK,intent);

            bluetooth.cancelDiscovery();
            finish();
        }
    };
}
