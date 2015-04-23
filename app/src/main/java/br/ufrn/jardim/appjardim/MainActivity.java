package br.ufrn.jardim.appjardim;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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


    public void executarMeuJardimActivity(View view){

        Intent i = new Intent(this,MeuJardimActivity.class);
        startActivity(i);

    }

    public void executarMeusAlertasActivity(View view){

        Intent i = new Intent(this,MeusAlertasActivity.class);
        startActivity(i);

    }

    public void executarSair(View view) {

       finish();

    }

    public void executarCadastroUsuarioActivity(View view){

        Intent i = new Intent(this,MeuCadastroActivity.class);
        startActivity(i);
    }

    public void executarSobreActivity (View view){

        Intent i = new Intent(this,SobreActivity.class);
        startActivity(i);

    }

    public void executarSincronizacao(View view){

        Toast.makeText(this,"Atualizando...",Toast.LENGTH_LONG).show();

    }

}
