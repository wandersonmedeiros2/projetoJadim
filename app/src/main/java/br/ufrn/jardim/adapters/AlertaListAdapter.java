package br.ufrn.jardim.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.ufrn.jardim.appjardim.R;
import br.ufrn.jardim.modelo.Alerta;


/**
 * Created by wanderson on 21/04/15.
 */
public class AlertaListAdapter extends ArrayAdapter<Alerta> {

    private Context context;
    private List<Alerta> values;

    public AlertaListAdapter(Context context, List<Alerta> alertas) {
        super(context, R.layout.alerta_adapter_layout, alertas);

        this.context = context;
        this.values = alertas;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View linhaVasoAdapter = inflater.inflate(R.layout.alerta_adapter_layout,parent,false);

        ImageView iconeAlerta   = (ImageView) linhaVasoAdapter.findViewById(R.id.iconeAlerta);


        TextView lbDescrcao = (TextView) linhaVasoAdapter.findViewById(R.id.labelDescricao);
        TextView lbDescricaovaso   = (TextView) linhaVasoAdapter.findViewById(R.id.labelDescricaoVaso);
        TextView lbAtivo = (TextView) linhaVasoAdapter.findViewById(R.id.labelAtivo);


        Alerta alerta = this.values.get(position);

        if(alerta.getImagem() == ""){
            iconeAlerta.setImageResource(R.drawable.flag);
        }
        else
            iconeAlerta.setImageResource(R.drawable.flag);

        lbDescrcao.setText(alerta.getDescricao());

        if(alerta.isAtivo())
            lbAtivo.setText("Ativo");
        else
            lbAtivo.setText("Inativo");

        if(alerta.getVaso() == null) {
            lbDescricaovaso.setText("Vaso Não Informado");
        }
        else
            lbDescricaovaso.setText(String.valueOf(alerta.getVaso().getDescricao()));


        return linhaVasoAdapter;

    }
}
