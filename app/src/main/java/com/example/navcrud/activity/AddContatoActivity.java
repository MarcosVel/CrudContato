package com.example.navcrud.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.navcrud.R;

public class AddContatoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contato);
        setTitle("Adicionar Contato");
    }
}