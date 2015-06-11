package br.ufrn.jardim.appjardim;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
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

import java.util.List;


public class ProcurarDispositivosBluetooth extends ActionBarActivity {

    public  static String EXTRA_DEVICE_ADDRESS = "device_address";
    private BluetoothAdapter bluetooth;
    private ArrayAdapter novos_disp;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_procurar_dispositivos_bluetooth);

        mToolbar = (Toolbar) findViewById(R.id.toolbar_disp_novos);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        bluetooth = BluetoothAdapter.getDefaultAdapter();
        novos_disp = new ArrayAdapter(this,R.layout.nome_dispositivo);

        Procurar_Dispositivos();

        ListView disp_novos = (ListView)findViewById(R.id.listDispNovos);
        disp_novos.setAdapter(novos_disp);
        disp_novos.setOnItemClickListener(disp_pareadosListener);

        //Se registra em broadcast quando um dispositivo eh descoberto
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        this.registerReceiver(Receiver_Filter, filter);

        //Quando evento de descoberta termina
        filter = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        this.registerReceiver(Receiver_Filter, filter);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_procurar_dispositivos_bluetooth, menu);
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

    @Override
    protected void onDestroy(){
        super.onDestroy();
        if(bluetooth != null){
            bluetooth.cancelDiscovery();
        }

        this.unregisterReceiver(Receiver_Filter);

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

    private final BroadcastReceiver Receiver_Filter = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            // Quando identifica-se uma descoberta de dispositivo
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // PEGA O OBJETO BLUETOOTH DA INTENT
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

                // SE JA ESTIVER PAREADO, NAO LISTA-SE DENOVO
                if(device.getBondState() != BluetoothDevice.BOND_BONDED){
                    novos_disp.add(device.getName() + "\n" + device.getAddress());
                }

                else if(BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)){
                    if (novos_disp.getCount() == 0) {
                        novos_disp.add("Nenhum Dispositivo encontrado");
                    }
                }
            }
        }
    };

    private void Procurar_Dispositivos() {

        if(bluetooth.isDiscovering()){
            bluetooth.cancelDiscovery();
        }

        bluetooth.startDiscovery();
    }


}