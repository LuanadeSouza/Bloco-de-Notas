package com.luanadev.ceepapplication.ui.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.luanadev.ceepapplication.R;
import com.luanadev.ceepapplication.dao.NotaDAO;
import com.luanadev.ceepapplication.model.Nota;
import com.luanadev.ceepapplication.ui.adapter.ListaNotaAdapter;

import java.util.List;

public class ListaNotasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_notas);

        RecyclerView listaNotas = findViewById(R.id.lista_nptas_recyclerview);
        NotaDAO dao = new NotaDAO();
        for (int i = 1; i <= 10000; i++) {
            dao.insere(new Nota("Titulo " + i, "Descrição" + i));
        }

        List<Nota> todasNotas = dao.todos();
        listaNotas.setAdapter(new ListaNotaAdapter(this, todasNotas));
        LinearLayoutManager manager = new LinearLayoutManager(this);
        listaNotas.setLayoutManager(manager);


    }
}
