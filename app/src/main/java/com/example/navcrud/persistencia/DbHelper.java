package com.example.navcrud.persistencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public static int VERSION = 1;
    public static String NOME_DB = "DB_SEUBANCO";
    public static String TABELA_CONTATO = "contato";

    public DbHelper(Context context) {
        super(context, NOME_DB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE IF NOT EXISTS "+ TABELA_CONTATO
                + " (id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + " nomeContato TEXT NOT NULL, telContato TEXT);";
        try {
            sqLiteDatabase.execSQL(sql);
            Log.i("InfoDB","Tabela criada com sucesso");
        }catch (Exception e){
            Log.e("InfoDB","Erro ao criar a tabela"+e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS "+TABELA_CONTATO + ";";
        try {
            sqLiteDatabase.execSQL(sql);
            onCreate(sqLiteDatabase);
            Log.i("InfoDB","Tabela atualizada com sucesso");
        }catch (Exception e){
            Log.e("InfoDB","Erro ao atualizar a tabela"+e.getMessage());
        }

    }
}
