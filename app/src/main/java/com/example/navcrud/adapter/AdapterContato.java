package com.example.navcrud.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.navcrud.R;
import com.example.navcrud.model.Contato;

import java.util.List;

public class AdapterContato extends RecyclerView.Adapter<AdapterContato.MyViewHolder> {
    private List<Contato> lisaContato;
    private Context mContext;

    public AdapterContato(List<Contato> lisaContato) {
        this.lisaContato = lisaContato;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_contato, parent, false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Contato contato = lisaContato.get(position);
        holder.nomeContanto.setText(contato.getNomeContato());
        holder.telContato.setText(contato.getTelContato());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nomeContanto;
        TextView telContato;
        public MyViewHolder (@NonNull View itemView) {
            super(itemView);
            nomeContanto = itemView.findViewById(R.id.textViewNome);
            telContato = itemView.findViewById(R.id.textViewTelefone);
        }

    }
}
