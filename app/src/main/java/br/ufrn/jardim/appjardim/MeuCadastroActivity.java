package br.ufrn.jardim.appjardim;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class MeuCadastroActivity extends ActionBarActivity {

    private static final int FOTO = 1;

    Button btConfirmar;
    ImageView imageFoto;

    Toolbar toobarPrincipal;


    public void inicializaComponentes(){

        btConfirmar = (Button) findViewById(R.id.btConfirmar);
        imageFoto = (ImageView) findViewById(R.id.imageFoto);
        imageFoto.setImageResource(R.drawable.user_green);

        toobarPrincipal = (Toolbar) findViewById(R.id.toolbar_actionbar_cadastro);
        setSupportActionBar(toobarPrincipal);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meu_cadastro);

        inicializaComponentes();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_meu_cadastro, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case FOTO:
                if(data != null) {
                    Bitmap foto = (Bitmap) data.getExtras().get("data");
                    imageFoto.setImageBitmap(foto);
                }
                break;

        }

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
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){


        }

    }



    public void confirmarDialog(){

        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("Atenção");
        alerta.setMessage("Deseja realmente cadastrar?");
        alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //codigo para salvar no banco

                finish();
            }
        });

        alerta.setNegativeButton("Não",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alerta.show();
    }


    public void confirmarCadastro(View view){

        confirmarDialog();

    }

    public void carregarFoto(View view){

        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, FOTO);
    }
}
