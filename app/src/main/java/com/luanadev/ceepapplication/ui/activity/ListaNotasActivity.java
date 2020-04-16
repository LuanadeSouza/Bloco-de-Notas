package com.luanadev.ceepapplication.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.luanadev.ceepapplication.R;
import com.luanadev.ceepapplication.dao.NotaDAO;
import com.luanadev.ceepapplication.model.Nota;
import com.luanadev.ceepapplication.ui.adapter.ListaNotasAdapter;

import java.io.Serializable;
import java.util.List;

public class ListaNotasActivity extends AppCompatActivity {

    private NotaDAO dao = new NotaDAO();
    private ListaNotasAdapter adapter;
    private List<Nota> todasNotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_notas);

        todasNotas = notasExemplo();
        configuraRecyclerView(todasNotas);

        TextView insereNotaBotao = findViewById(R.id.lista_notas_insere_nota);
        insereNotaBotao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iniciaFormularioNota = new Intent(ListaNotasActivity.this, FormularioNotaActivity.class);
                startActivityForResult(iniciaFormularioNota, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int resquetCode, int resultCode, Intent data) {
        if (resquetCode == 1 && resultCode == 2 && data.hasExtra("nota")) {
            Nota notaRecebida = (Nota) data.getSerializableExtra("nota");
            new NotaDAO().insere(notaRecebida);
            adapter.adiciona(notaRecebida);
        }
        super.onActivityResult(resquetCode, resultCode, data);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private List<Nota> notasExemplo() {
        return dao.todos();
    }

    private void configuraRecyclerView(List<Nota> todasNotas) {
        RecyclerView listaNotas = findViewById(R.id.lista_notas_recyclerview);
        configuraAdapter(todasNotas, listaNotas);
    }

    private void configuraAdapter(List<Nota> todasNotas, RecyclerView listaNotas) {
        adapter = new ListaNotasAdapter(this, todasNotas);
        listaNotas.setAdapter(adapter);
    }
}
