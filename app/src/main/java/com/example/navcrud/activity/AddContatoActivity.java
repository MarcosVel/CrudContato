package com.example.navcrud.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.navcrud.R;
import com.example.navcrud.model.Contato;
import com.example.navcrud.persistencia.ContatoDAO;

public class AddContatoActivity extends AppCompatActivity {
    private EditText edtNomeContato;
    private EditText edtTelefone;
    private Contato contatoAtual;
    private Button btnSalvar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contato);
        setTitle("Adicionar Contatos");

        edtNomeContato = findViewById(R.id.txtNome);
        edtTelefone = findViewById(R.id.txtTelefone);
        btnSalvar = findViewById(R.id.cmdSalvar);

        //Caso seja uma edição, objeto contatoAtual recebe via putExtras o contatoSelecionado
        //Selecionado na activity lstContatoActivity
        contatoAtual = (Contato) getIntent().getSerializableExtra("contatoSelecionado");

        if(contatoAtual!= null) {
            edtNomeContato.setText(contatoAtual.getNomeContato());
            edtTelefone.setText(contatoAtual.getTelContato());
        }

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContatoDAO contatoDAO = new ContatoDAO(getApplicationContext());
                // Edição - estarei alterando
                if(contatoAtual!=null){
                    String nomeContato = edtNomeContato.getText().toString();
                    String telefoneContato = edtTelefone.getText().toString();
                    if (nomeContato.isEmpty()) {
                        Contato contato = new Contato();
                        contato.setNomeContato(nomeContato);
                        contato.setTelContato(telefoneContato);
                        contato.setId(contatoAtual.getId());
                        edtNomeContato.setText(nomeContato);
                        edtTelefone.setText(telefoneContato);

                        if (contatoDAO.atualizar(contato)) {
                            finish();
                            Toast.makeText(getApplicationContext(), "Sucesso ao atualizar a lista de contatos...", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Erro ao atualizar a lista de contatos...", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    String nomeContatoI = edtNomeContato.getText().toString();
                    String telefoneContatoI = edtTelefone.getText().toString();
                    if (!nomeContatoI.isEmpty()) {
                        Contato contato = new Contato();
                        contato.setNomeContato(nomeContatoI);
                        contato.setTelContato(telefoneContatoI);
                        if (contatoDAO.salvar(contato)) {
                            finish();
                            Toast.makeText(getApplicationContext(), "Sucesso ao incluir um novo contato...", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Erro ao incluir um novo contato...", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

    }
}