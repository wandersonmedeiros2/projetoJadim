package br.ufrn.jardim.appjardim;


import android.app.ListFragment;
import android.content.Intent;
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
import br.ufrn.jardim.dao.VasoDAO;
import br.ufrn.jardim.modelo.Vaso;


/**
 * A simple {@link Fragment} subclass.
 */
public class MeuJardimFragment extends Fragment {


    ListView lvVasos;
    VasoDAO vasoDAO;
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

        vasoDAO = new VasoDAO(this.getActivity().getApplicationContext());


        lvVasos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Vaso v = (Vaso) adapter.getItem(position);

                Intent i = new Intent(MeuJardimFragment.this.getActivity(),VisualizarVaso.class);

                i.putExtra("vaso",v);
                startActivity(i);
            }
        });

        return view;
    }


    @Override
    public void onResume() {
        super.onResume();

        List<Vaso> vasos = new ArrayList<Vaso>();
        vasos = vasoDAO.listar();

        adapter = new VasoListAdapter(this.getActivity().getApplicationContext(),vasos);

        lvVasos.setAdapter(adapter);

    }
}
