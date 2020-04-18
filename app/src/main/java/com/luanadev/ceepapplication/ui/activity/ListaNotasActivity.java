package com.luanadev.ceepapplication.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.luanadev.ceepapplication.R;
import com.luanadev.ceepapplication.dao.NotaDAO;
import com.luanadev.ceepapplication.model.Nota;
import com.luanadev.ceepapplication.ui.adapter.ListaNotasAdapter;
import com.luanadev.ceepapplication.ui.interfaces.OnItemClickListener;

import java.util.List;

public class ListaNotasActivity extends AppCompatActivity {

    public static final String NOTA = "nota";
    public static final String POSITION = "position";
    private NotaDAO dao = new NotaDAO();
    private ListaNotasAdapter adapter;
    private Nota notaRecebida;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_notas);

        List<Nota> todasNotas = dao.todos();
        configuraRecyclerView(todasNotas);
        botaoInsereNota();

    }

    private void botaoInsereNota() {
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
        super.onActivityResult(resquetCode, resultCode, data);
        if (resquetCode == 1 && resultCode == 2 && data.hasExtra(NOTA)) {
            notaRecebida = (Nota) data.getSerializableExtra(NOTA);
            dao.insere(notaRecebida);
            adapter.adiciona(notaRecebida);
        }
        if (resquetCode == 2 && resquetCode == 2 && temNota(data) && data.hasExtra(POSITION)) {
            notaRecebida = (Nota) data.getSerializableExtra(NOTA);
            int positionRecebida = data.getIntExtra(POSITION, -1);
            dao.altera(positionRecebida, notaRecebida);
            adapter.altera(positionRecebida, notaRecebida);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void configuraRecyclerView(List<Nota> todasNotas) {
        RecyclerView listaNotas = findViewById(R.id.lista_notas_recyclerview);
        configuraAdapter(todasNotas, listaNotas);

    }

    private boolean temNota(Intent data) {
        return data.hasExtra(NOTA);
    }


    private void configuraAdapter(List<Nota> todasNotas, RecyclerView listaNotas) {
        adapter = new ListaNotasAdapter(this, todasNotas);
        listaNotas.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(Nota nota, int position) {
                Intent abreFormularioComNota = new Intent(ListaNotasActivity.this, FormularioNotaActivity.class);
                abreFormularioComNota.putExtra(NOTA, nota);
                abreFormularioComNota.putExtra(POSITION, position);
                startActivityForResult(abreFormularioComNota, 2);
            }
        });
    }
}
