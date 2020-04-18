package com.luanadev.ceepapplication.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.luanadev.ceepapplication.R;
import com.luanadev.ceepapplication.model.Nota;

import java.io.Serializable;

import static com.luanadev.ceepapplication.ui.activity.ListaNotasActivity.POSITION;

public class FormularioNotaActivity extends AppCompatActivity {

    public static final String NOTA = "nota";
    private int positionRecebida;
    private Nota notaRecebida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_nota);
        Intent dadosRecebidos = getIntent();
        if (dadosRecebidos.hasExtra(NOTA)&& dadosRecebidos.hasExtra(POSITION)){
            notaRecebida = (Nota) dadosRecebidos.getSerializableExtra(NOTA);
            positionRecebida = dadosRecebidos.getIntExtra(POSITION, -1);
            TextView titulo = findViewById(R.id.formulario_nota_titulo);
            titulo.setText(notaRecebida.getTitulo());
            TextView descricao = findViewById(R.id.formulario_nota_descricao);
            descricao.setText(notaRecebida.getDescricao());
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_formulario_salva, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_formulario_salva) {
            Nota notaCriada = criaNota();
            retornaNota(notaCriada);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void retornaNota(Nota nota) {
        Intent resultadoInsercao = new Intent();
        resultadoInsercao.putExtra(NOTA, nota);
        resultadoInsercao.putExtra(POSITION, positionRecebida);
        setResult(2, resultadoInsercao);
    }

    private Nota criaNota() {
        EditText titulo = findViewById(R.id.formulario_nota_titulo);
        EditText descricao = findViewById(R.id.formulario_nota_descricao);
        return new Nota(titulo.getText().toString(), descricao.getText().toString());
    }
}
