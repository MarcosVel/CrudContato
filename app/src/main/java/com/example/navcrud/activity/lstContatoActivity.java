package com.example.navcrud.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.navcrud.R;
import com.example.navcrud.adapter.AdapterContato;
import com.example.navcrud.model.Contato;
import com.example.navcrud.persistencia.ContatoDAO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class lstContatoActivity extends AppCompatActivity {
    Button btnNovo;
    private RecyclerView rv;
    private AdapterContato contatoAdapter;
    private List<Contato> listaContato = new ArrayList<>();
    private Contato contatoSelecionado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lst_contato);
        setTitle("Lista de Contatos");

        rv = findViewById(R.id.rclvContato);
        rv.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), rv, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                // Recuperar os contatos para edição
                Contato contatoSelecionado = listaContato.get(position);
                // Criar uma Intent para enviar os objeto contato para a tela AddContato.
                Intent intent = new Intent(lstContatoActivity.this, AddContatoActivity.class);
                intent.putExtra("contatoSelecionado", (Serializable) contatoSelecionado);
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(View view, int position) {
                // Recuperar o contato que será incluido
                AlertDialog.Builder dialog = new AlertDialog.Builder(lstContatoActivity.this);
                // Configurar nosso dialogo

                dialog.setTitle("Confirmar Exclusão");
                dialog.setMessage("Deseja excluir o contato " + contatoSelecionado.getNomeContato() + "?");
                dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ContatoDAO contatoDAO = new ContatoDAO(getApplicationContext());
                        if (contatoDAO.deletar(contatoSelecionado)) {
                            carregarListaContatos();
                            Toast.makeText(getApplicationContext(), "Sucesso ao excluir o contato", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Erro ao excluir o contato", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                dialog.setNegativeButton("Não", null);
                dialog.create();
                dialog.show();
            }

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        }));

        btnNovo = (Button) findViewById(R.id.cmdNovo);
        btnNovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(lstContatoActivity.this, AddContatoActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        carregarListaContatos();
        super.onStart();
    }

    private void carregarListaContatos() {
        ContatoDAO contatoDAO = new ContatoDAO(getApplicationContext());
        // Exibir a lista de contatos no RecyclerView
        listaContato = contatoDAO.listar();
        // Configurar adapter
        contatoAdapter = new AdapterContato(listaContato);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(layoutManager);
        rv.setHasFixedSize(true);
        rv.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        rv.setAdapter(contatoAdapter);
    }
}