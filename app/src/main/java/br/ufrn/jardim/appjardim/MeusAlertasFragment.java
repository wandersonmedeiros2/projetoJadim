package br.ufrn.jardim.appjardim;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.ufrn.jardim.adapters.AlertaListAdapter;
import br.ufrn.jardim.adapters.VasoListAdapter;
import br.ufrn.jardim.modelo.Alerta;


/**
 * A simple {@link Fragment} subclass.
 */
public class MeusAlertasFragment extends Fragment {

    ListView lvAlertas;
    VasoListAdapter adapter;

    public MeusAlertasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_meus_alertas, container, false);

        lvAlertas = (ListView) view.findViewById(R.id.listAlertas);
        List<Alerta> alertas = new ArrayList<Alerta>();

        Random ram = new Random();

        for(int i = 0; i < 10;i++){

            Alerta alerta = new Alerta("alerta " + String.valueOf(i+1));

            if(ram.nextInt(1024) % 2 == 0)
                alerta.setAtivo(true);
            else
                alerta.setAtivo(false);

            alertas.add(alerta);
        }

        AlertaListAdapter adapter = new AlertaListAdapter(this.getActivity().getApplicationContext(), alertas);
        lvAlertas.setAdapter(adapter);

        return view;
    }


}
