package com.consultorio.psicologico.proyecto_deapmo_grupo_1.ui.Terapeutas;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.consultorio.psicologico.proyecto_deapmo_grupo_1.R;
import com.consultorio.psicologico.proyecto_deapmo_grupo_1.entity.AppDatabase;
import com.consultorio.psicologico.proyecto_deapmo_grupo_1.entity.Terapeuta;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TerapeutasFragment extends Fragment {
    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private TerapeutaAdapter adapter;
    private List<Terapeuta> list = new ArrayList<>();
    private AppDatabase database;
    private EditText searchBar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_terapeutas, container, false);

        recyclerView = root.findViewById(R.id.rv_terapeuta);
        fab = root.findViewById(R.id.btn_añadir);
        searchBar = root.findViewById(R.id.search_bar);
        database = AppDatabase.getInstance(requireContext());
        adapter = new TerapeutaAdapter(list, new TerapeutaAdapter.Dialog() {
            @Override
            public void onClick(int position) {
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(requireContext());
                dialogBuilder.setTitle(list.get(position).getNombreTerapeuta());
                dialogBuilder.setItems(R.array.item_option, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            Intent intent = new Intent(requireContext(), EditorActivity.class);
                            intent.putExtra("id", list.get(position).getIdTerapeuta());
                            startActivity(intent);
                        } else if (which == 1) {
                            database.terapeutaDao().delete(list.get(position));
                            getData();
                            Toast.makeText(requireContext(), "Terapeuta eliminado", Toast.LENGTH_SHORT).show();
                        } else {
                            dialog.dismiss();
                        }
                    }
                });
                AlertDialog dialogView = dialogBuilder.create();
                dialogView.show();
            }
        });

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(requireContext(), EditorActivity.class));
            }
        });

        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        getData();
    }

    private void getData() {
        list.clear();
        list.addAll(database.terapeutaDao().getAll());
        adapter.notifyDataSetChanged();
    }

    private void filter(String text) {
        List<Terapeuta> filteredList = list.stream()
                .filter(terapeuta -> terapeuta.getNombreTerapeuta().toLowerCase().contains(text.toLowerCase()) ||
                        terapeuta.getApellidoTerapeuta().toLowerCase().contains(text.toLowerCase()) ||
                        terapeuta.getEspecialidadTerapeuta().toLowerCase().contains(text.toLowerCase()))
                .collect(Collectors.toList());
        adapter.updateList(filteredList);
    }
}