package br.ufrn.jardim.appjardim;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ListView;
import android.widget.Toast;
import br.ufrn.jardim.adapters.DrawerCallbacks;
import br.ufrn.jardim.adapters.Navigator_DrawerFragment;



public class MainActivity extends ActionBarActivity implements DrawerCallbacks {

    private Toolbar mToolbar;
    FragmentManager fm;

    private static final int REQUEST_ENABLE_BT = 1;
    private static final int CADASTRO_VASO = 2;
    private BluetoothAdapter bluetooth;

    //Fragments
    MeuJardimFragment fragment_MeuJardim;
    MeusAlertasFragment fragment_MeusAlertas;
    ConfiguracoesFragment fragment_Configuracoes;
    MeuCadastroFragment fragment_Cadastro;


    ListView listVasos;

    private Navigator_DrawerFragment mNavigationNavDrawerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bluetooth = BluetoothAdapter.getDefaultAdapter();

        mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mToolbar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                }
                return false;
            }
        });

        mNavigationNavDrawerFragment = (Navigator_DrawerFragment) getFragmentManager().findFragmentById(R.id.fragment_drawer);
        mNavigationNavDrawerFragment.setup(R.id.fragment_drawer, (DrawerLayout) findViewById(R.id.drawer), mToolbar);

        fm = getFragmentManager();
        fragment_MeuJardim = new MeuJardimFragment();
        addFragment(fragment_MeuJardim);

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

        switch (id){
            case R.id.menu_NovoVaso:
                Intent resultado = new Intent(this,DispPareadosActivity.class);
                startActivityForResult(resultado,CADASTRO_VASO);
                break;
            case R.id.menu_NovoAlerta:

                break;
        }

        /*/noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {

        Intent i = null;
        switch (position)
        {
            case 1:
                   mToolbar.setTitle("Meu Jardim");
                   fragment_MeuJardim = new MeuJardimFragment();
                   replaceFragment(fragment_MeuJardim);
                   break;
            case 2:
                fragment_MeusAlertas = new MeusAlertasFragment();
                replaceFragment(fragment_MeusAlertas);
                break;
            case 3:
                fragment_Cadastro = new MeuCadastroFragment();
                replaceFragment(fragment_Cadastro);
                break;
            case 4:
                fragment_Configuracoes = new ConfiguracoesFragment();
                replaceFragment(fragment_Configuracoes);
            //case 5: i = new Intent(this,Atualizar_vaso.class);break;
            case 6: i = new Intent(this,SobreActivity.class);break;
            case 7: i = null;finish();break;
            default : Toast.makeText(this, "item no: " + position + "-Selected", Toast.LENGTH_SHORT).show(); i = null;
        }

        if(i != null)
        {
            startActivity(i);
        }
    }

    @Override
    public void onBackPressed() {
        if (mNavigationNavDrawerFragment.isDrawerOpen())
            mNavigationNavDrawerFragment.closeDrawer();
        else
            super.onBackPressed();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode){
            case CADASTRO_VASO:
                    if(resultCode == RESULT_OK){

                    }
                  break;
        }

    }

    private void addFragment(Fragment fragment){
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.cenario,fragment);
        ft.commit();
    }


    private void replaceFragment(Fragment fragment){
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.cenario,fragment);
        ft.commit();
    }

    private void removeFragment(Fragment fragment){
        FragmentTransaction ft = fm.beginTransaction();
        ft.remove(fragment);
        ft.commit();
    }




}
