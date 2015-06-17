package br.ufrn.jardim.adapters;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import br.ufrn.jardim.dao.VasoDAO;
import br.ufrn.jardim.modelo.Vaso;

/**
 * Created by wanderson on 6/16/15.
 */
public class GerenciarBluetooth {
    private BluetoothAdapter bluetooth;
    private BluetoothSocket Socket = null;
    private BluetoothDevice device = null;
    private InputStream InStream = null;
    private OutputStream OutStream = null;
    private String address;
    private UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private byte[] read = new byte[1024];
    private int bytes;
    private byte[] msg = null;
    private Context contexto;

    public GerenciarBluetooth(Context context){

        this.contexto = context;
        bluetooth = BluetoothAdapter.getDefaultAdapter();

    }

    public void EnviarComando(Vaso vaso) {
        if(Socket == null) {
            address = vaso.getMAC();
            conectar(address);
        }
        msg = "P".getBytes();
        try {
            OutStream.write(msg);
            bytes = InStream.read(read);
            if (read != null) {
                String readMessage = new String(read);
                if(bytes < 2){
                    bytes = InStream.read(read);
                    readMessage += new String(read);
                }
                Toast.makeText(contexto, readMessage, Toast.LENGTH_SHORT).show();
                read = new byte[1024];
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void AtualizarInfoVaso(Vaso vaso) {

        String readMessage = "";

        if(Socket == null) {
            address = vaso.getMAC();
            conectar(address);
        }
        msg = "G".getBytes();
        try {
            OutStream.write(msg);
            bytes = InStream.read(read);
            if (read != null) {
                readMessage = new String(read);
                if (bytes < 2) {
                    bytes = InStream.read(read);
                    readMessage += new String(read);
                }
                //readMessage = readMessage.subSequence(0,3).toString();
                read = new byte[1024];
            }

            int aux = readMessage.indexOf("\r\n");
            vaso.setLuminosidade(Integer.valueOf(readMessage.substring(0,aux)));
            int aux2 = readMessage.indexOf("\r\n");
            vaso.setTemperatura(Integer.valueOf(readMessage.substring(aux+1,aux2)));
            int aux3 = readMessage.indexOf("\r\n");
            vaso.setUmidadeAr(Integer.valueOf(readMessage.substring(aux2+1,aux3)));
            int aux4 = readMessage.indexOf("\r\n");
            vaso.setUmidadeSolo(Integer.valueOf(readMessage.substring(aux3+1,aux4)));

            VasoDAO vasoDAO = new VasoDAO(contexto);
            vasoDAO.inserirOuAtualizar(vaso);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void conectar(String address) {

        // Obtem o dispositivo bluetooth
        device = bluetooth.getRemoteDevice(address);
        try{
            Socket = device.createRfcommSocketToServiceRecord(MY_UUID);
            Socket.connect(); //conectado
            InStream = Socket.getInputStream();
            OutStream = Socket.getOutputStream();
        }
        catch (IOException e){
            Toast.makeText(contexto, "Ocorreu um erro!" + e, Toast.LENGTH_LONG).show();
        }
    }

    public void desconectar() {
        if(Socket != null) {
            try {
                if (InStream != null) {
                    InStream.close();
                }
                if (OutStream != null) {
                    OutStream.close();
                }
                if (Socket.isConnected()) {
                    Socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}