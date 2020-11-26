package com.example.navcrud.persistencia;

import com.example.navcrud.model.Contato;

import java.util.List;

public interface IContatoDAO {
    public boolean salvar(Contato contato);
    public boolean atualizar(Contato contato);
    public boolean deletar(Contato contato);
    public List<Contato> listar();
}
