package com.example.navcrud.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.navcrud.model.Contato;

import java.util.ArrayList;
import java.util.List;

public class ContatoDAO implements IContatoDAO {
    private final String TABLE_EMISSORES = "Contato";
    private SQLiteDatabase escreve;
    private SQLiteDatabase le;

    public ContatoDAO(Context context) {
        DbHelper db = new DbHelper(context);
        escreve = db.getWritableDatabase();
        le = db.getReadableDatabase();
    }

    @Override
    public boolean salvar(Contato contato) {
        ContentValues cv = new ContentValues();
        cv.put("nomeContato", contato.getNomeContato());
        cv.put("telContato", contato.getTelContato());

        try{
            escreve.insert(DbHelper.TABELA_CONTATO,null,cv);
            Log.i("INFO", "Sucesso ao salvar a tarefa");
        }catch (Exception e){
            Log.e("INFO", "Erro ao salvar a tarefa"+e.getMessage());
            return  false;
        }
        return  true;
    }

    @Override
    public boolean atualizar(Contato contato) {
        ContentValues cv = new ContentValues();
        cv.put("nomeContato", contato.getNomeContato());
        cv.put("telContato", contato.getTelContato());

        try{
            String[] args = {contato.getId().toString()};
            escreve.update(DbHelper.TABELA_CONTATO,cv,"id=?",args);
            Log.i("INFO", "Sucesso ao atualizar a tarefa");
        }catch (Exception e){
            Log.e("INFO", "Erro ao atualizar a tarefa"+e.getMessage());
            return  false;
        }
        return  true;
    }

    @Override
    public boolean deletar(Contato contato) {
        try{
            String[] args = {contato.getId().toString()};
            escreve.delete(DbHelper.TABELA_CONTATO,"id=?",args);
            Log.i("INFO", "Sucesso ao excluir a tarefa");
        }catch (Exception e){
            Log.e("INFO", "Erro ao excluir a tarefa"+e.getMessage());
            return  false;
        }
        return  true;
    }

    @Override
    public List<Contato> listar() {
        List<Contato> lstcontatos = new ArrayList<>();
        String sql = "SELECT * FROM "+DbHelper.TABELA_CONTATO+";";
        Cursor c = le.rawQuery(sql, null);

        while(c.moveToNext()){
            Contato contato = new Contato();
            Long id = c.getLong(c.getColumnIndex("id"));
            String nomeContato = c.getString(c.getColumnIndex("nomeContato"));
            String telContato = c.getString(c.getColumnIndex("telContato"));

            contato.setId(id);
            contato.setNomeContato(nomeContato);
            contato.setTelContato(telContato);

            lstcontatos.add(contato);
            Log.i("INFO", "Sucesso ao pesquisar os registros");
        }

        return lstcontatos;
    }
}
