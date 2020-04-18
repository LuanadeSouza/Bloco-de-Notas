package com.luanadev.ceepapplication.ui.helper;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.luanadev.ceepapplication.dao.NotaDAO;
import com.luanadev.ceepapplication.ui.adapter.ListaNotasAdapter;

public class NotaItemTouchHelperCallback extends ItemTouchHelper.Callback {

    private ListaNotasAdapter adapter;

    public NotaItemTouchHelperCallback(ListaNotasAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int marcacoesDeslize = ItemTouchHelper.LEFT| ItemTouchHelper.RIGHT;
        return makeMovementFlags(0,marcacoesDeslize);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        int positionNotaDeslizada = viewHolder.getAdapterPosition();
        new NotaDAO().remove(positionNotaDeslizada);
        adapter.remove(positionNotaDeslizada);
    }
}
