package br.ufrn.jardim.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.ufrn.jardim.modelo.Vaso;

/**
 * Created by wanderson on 09/06/15.
 */
public class VasoDAO {

    private SQLiteDatabase db;

    public VasoDAO(Context context){
        DBHelper DBHelper = new DBHelper(context);
        db = DBHelper.getReadableDatabase();
    }

    public void inserirOuAtualizar(Vaso v){

        ContentValues valores = new ContentValues(2);
        valores.put("DESCRICAO", v.getDescricao());
        valores.put("UMIDADE_AR",v.getUmidadeAr());
        valores.put("TEMP",v.getTemperatura());
        valores.put("UMIDADE_SOLO",v.getUmidadeSolo());
        valores.put("LUZ",v.getLuminosidade());
        valores.put("MAC",v.getMAC());

        if(v.getID() > 0){
            db.update("VASO",valores,"id = ?",new String[]{"" + v.getID()});
        }else{
            db.insert("VASO",null,valores);
        }
    }

    public void remover(Vaso v){

        String[] id = {String.valueOf(v.getID())};
        db.delete("VASO","ID=?",id);
    }

    public List<Vaso> listar(){
        List<Vaso> vasos = new ArrayList<Vaso>();
        Cursor c = db.query("VASO",Vaso.COLUNAS,null,null,null,null,"DESCRICAO");

        if(c.moveToFirst()){
            do{
                Vaso vaso = new Vaso();
                vaso.setID(c.getInt(c.getColumnIndex("ID")));
                vaso.setDescricao(c.getString(c.getColumnIndex("DESCRICAO")));
                vaso.setLuminosidade(c.getInt(c.getColumnIndex("LUZ")));
                vaso.setTemperatura(c.getInt(c.getColumnIndex("TEMP")));
                vaso.setUmidadeAr(c.getInt(c.getColumnIndex("UMIDADE_AR")));
                vaso.setUmidadeSolo(c.getInt(c.getColumnIndex("UMIDADE_SOLO")));
                vaso.setMAC(c.getString(c.getColumnIndex("MAC")));
                vasos.add(vaso);
            }while(c.moveToNext());
        }
        c.close();
        return vasos;
    }

    public Vaso buscarPorChavePrimaria(int id){

        Vaso vaso = new Vaso();
        Cursor c = db.query("VASO",Vaso.COLUNAS,"ID="+id,null,null,null,null);
        if(c.moveToFirst()){
            vaso.setID(c.getInt(c.getColumnIndex("ID")));
            vaso.setDescricao(c.getString(c.getColumnIndex("DESCRICAO")));
            vaso.setLuminosidade(c.getInt(c.getColumnIndex("LUZ")));
            vaso.setTemperatura(c.getInt(c.getColumnIndex("TEMP")));
            vaso.setUmidadeAr(c.getInt(c.getColumnIndex("UMIDADE_AR")));
            vaso.setUmidadeSolo(c.getInt(c.getColumnIndex("UMIDADE_SOLO")));
            vaso.setMAC(c.getString(c.getColumnIndex("MAC")));
        }
        c.close();
        return vaso;
    }

}
