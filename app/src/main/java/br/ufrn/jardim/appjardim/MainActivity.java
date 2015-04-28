package br.ufrn.jardim.appjardim;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import br.ufrn.jardim.adapters.DrawerCallbacks;
import br.ufrn.jardim.adapters.Navigator_DrawerFragment;



public class MainActivity extends ActionBarActivity implements DrawerCallbacks {

    private Toolbar mToolbar;
    ListView listVasos;

    private Navigator_DrawerFragment mNavigationNavDrawerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

    @Override
    public void onNavigationDrawerItemSelected(int position) {

        Intent i;
        switch (position)
        {
            case 1: i = new Intent(this,MeuJardimActivity.class);break;
            case 2: i = new Intent(this,MeusAlertasActivity.class);break;
            case 3: i = new Intent(this,MeuCadastroActivity.class);break;
            //case 4: i = new Intent(this,Editar_vaso.class);break;
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


}
