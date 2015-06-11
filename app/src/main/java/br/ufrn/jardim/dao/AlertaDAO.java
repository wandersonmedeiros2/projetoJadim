package br.ufrn.jardim.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.ufrn.jardim.modelo.Alerta;

/**
 * Created by wanderson on 09/06/15.
 */
public class AlertaDAO {

    private SQLiteDatabase db;

    public AlertaDAO(Context context){
        DBHelper DBHelper = new DBHelper(context);
        db = DBHelper.getReadableDatabase();
    }

    public void inserirOuAtualizar(Alerta a){

        ContentValues valores = new ContentValues(2);
        valores.put("DESCRICAO", a.getDescricao());
        valores.put("ATIVO",a.getAtivo());


        if(a.getID() > 0){
            db.update("ALERTA",valores,"ID = ?",new String[]{"" + a.getID()});
        }else{
            db.insert("ALERTA",null,valores);
        }
    }

    public void remover(Alerta a){

        String[] id = {String.valueOf(a.getID())};
        db.delete("ALERTA","ID=?",id);
    }

    public List<Alerta> listar(){
        List<Alerta> alertas = new ArrayList<Alerta>();
        Cursor c = db.query("ALERTA",Alerta.COLUNAS,null,null,null,null,"DESCRICAO");

        if(c.moveToFirst()){
            do{
                Alerta alerta = new Alerta();
                alerta.setID(c.getInt(c.getColumnIndex("ID")));
                alerta.setDescricao(c.getString(c.getColumnIndex("DESCRICAO")));
                alerta.setAtivo(c.getInt(c.getColumnIndex("ATIVO")) == 1);
                alertas.add(alerta);
            }while(c.moveToNext());
        }
        c.close();
        return alertas;
    }

    public Alerta buscarPorChavePrimaria(int id){

        Alerta alerta = new Alerta();
        Cursor c = db.query("ALERTA",Alerta.COLUNAS,"ID="+id,null,null,null,null);
        if(c.moveToFirst()){
            alerta.setID(c.getInt(c.getColumnIndex("ID")));
            alerta.setDescricao(c.getString(c.getColumnIndex("DESCRICAO")));
            alerta.setAtivo(c.getInt(c.getColumnIndex("ATIVO")) == 1);
        }
        c.close();
        return alerta;
    }

}
