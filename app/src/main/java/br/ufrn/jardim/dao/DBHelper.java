package br.ufrn.jardim.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by wanderson on 09/06/15.
 */
public class DBHelper extends SQLiteOpenHelper {


    public static final String NOME_BANCO = "dbConnectedGarden";
    public static final int VERSAO_BANCO = 1;
    public static final String VASO_TABLE  = "CREATE TABLE VASO(" +
            "ID INTEGER NOT NULL PRIMARY KEY autoincrement, " +
            "DESCRICAO TEXT, " +
            "UMIDADE_AR INTEGER," +
            "UMIDADE_SOLO INTEGER," +
            "TEMP INTEGER," +
            "LUZ INTEGER) ";

    public static final String ALERTA_TABLE  = "CREATE TABLE ALERTA(" +
            "ID INTEGER NOT NULL PRIMARY KEY autoincrement, " +
            "DESCRICAO TEXT, " +
            "ATIVO INTEGER) ";

    public DBHelper(Context context){
        super(context,NOME_BANCO,null,VERSAO_BANCO);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(VASO_TABLE);
        db.execSQL(ALERTA_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS VASO");
        onCreate(db);
    }


}
