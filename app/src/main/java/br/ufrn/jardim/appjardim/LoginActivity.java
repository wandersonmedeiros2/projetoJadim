package br.ufrn.jardim.appjardim;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class LoginActivity extends ActionBarActivity {

    private static final int FOTO = 1;
    EditText login, senha;
    TextView esqueci,cadastrar_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SharedPreferences prefs = this.getApplicationContext().getSharedPreferences("PREFERENCIAS", Context.MODE_PRIVATE);

        login = (EditText)findViewById(R.id.etLogin);
        senha = (EditText)findViewById(R.id.etSenha);
        esqueci = (TextView)findViewById(R.id.tvEsqueciSenha);
        cadastrar_login = (TextView)findViewById(R.id.tvLogin);

        if(prefs.getInt("conectado",0) == 1)
        {
            login.setText(prefs.getString("senha",""));
            login.setText(prefs.getString("senha",""));
        }



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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



    public void cadastrar(View v){
        Intent i = new Intent(this,MeuCadastroActivity.class);
        startActivity(i);
    }

    public void esqueciSenha(View v){
        //Intent i = new Intent(this,RecuperarSenhaActivity.class);
        //startActivity(i);
    }

    public void menuPrincipal(View view) {
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }
}
