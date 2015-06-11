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
            "DESCRICAO TEXT NOT NULL, " +
            "UMIDADE_AR INTEGER DEFAULT 0," +
            "UMIDADE_SOLO INTEGER DEFAULT 0," +
            "TEMP INTEGER DEFAULT 0," +
            "LUZ INTEGER DEFAULT 0," +
            "MAC TEXT NULL) ";

    public static final String ALERTA_TABLE  = "CREATE TABLE ALERTA(" +
            "ID INTEGER NOT NULL PRIMARY KEY autoincrement, " +
            "DESCRICAO TEXT NOT NULL, " +
            "ATIVO INTEGER DEFAULT 1) ";

    public DBHelper(Context context){
        super(context,NOME_BANCO,null,VERSAO_BANCO);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(VASO_TABLE);
        db.execSQL(ALERTA_TABLE);

        db.execSQL("INSERT INTO VASO (DESCRICAO) VALUES ('Vaso 1')");
        db.execSQL("INSERT INTO VASO (DESCRICAO) VALUES ('Vaso 2')");
        db.execSQL("INSERT INTO VASO (DESCRICAO) VALUES ('Vaso 3')");
        db.execSQL("INSERT INTO VASO (DESCRICAO) VALUES ('Vaso 4')");
        db.execSQL("INSERT INTO VASO (DESCRICAO) VALUES ('Vaso 5')");

        db.execSQL("INSERT INTO ALERTA (DESCRICAO) VALUES ('Alerta 1')");
        db.execSQL("INSERT INTO ALERTA (DESCRICAO) VALUES ('Alerta 2')");
        db.execSQL("INSERT INTO ALERTA (DESCRICAO) VALUES ('Alerta 3')");
        db.execSQL("INSERT INTO ALERTA (DESCRICAO) VALUES ('Alerta 4')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS VASO");
        db.execSQL("DROP TABLE IF EXISTS ALERTA");
        onCreate(db);
    }


}
