package br.ufrn.jardim.appjardim;


import android.app.ListFragment;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.xml.validation.ValidatorHandler;

import br.ufrn.jardim.adapters.VasoListAdapter;
import br.ufrn.jardim.modelo.Vaso;


/**
 * A simple {@link Fragment} subclass.
 */
public class MeuJardimFragment extends Fragment {


    ListView lvVasos;
    VasoListAdapter adapter;

    public MeuJardimFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_meu_jardim, container, false);
        lvVasos = (ListView) view.findViewById(R.id.listVasos);

        List<Vaso> vasos = new ArrayList<Vaso>();

        Random ram = new Random();

        for(int i = 0; i < 10;i++){

            Vaso vaso = new Vaso("vaso " + String.valueOf(i+1));
            vaso.setAtLuminosidade(ram.nextInt(1024));
            vaso.setAtTemperatura(ram.nextInt(1024));
            vaso.setAtumidadeAr(ram.nextInt(1024));
            vaso.setAtUmidadeSolo(ram.nextInt(1024));
            vasos.add(vaso);
        }

        final VasoListAdapter adapter = new VasoListAdapter(this.getActivity().getApplicationContext(),vasos);

        lvVasos.setAdapter(adapter);

        lvVasos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Vaso v = (Vaso) adapter.getItem(position);
                Toast.makeText( MeuJardimFragment.this.getActivity(),v.getDescricao(),Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }


}
