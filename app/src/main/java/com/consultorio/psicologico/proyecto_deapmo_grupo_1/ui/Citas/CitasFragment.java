package com.consultorio.psicologico.proyecto_deapmo_grupo_1.ui.Citas;

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
import com.consultorio.psicologico.proyecto_deapmo_grupo_1.entity.Cita;
import com.consultorio.psicologico.proyecto_deapmo_grupo_1.entity.Paciente;
import com.consultorio.psicologico.proyecto_deapmo_grupo_1.entity.Terapeuta;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CitasFragment extends Fragment {
    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private CitaAdapter adapter;
    private List<Cita> list = new ArrayList<>();
    private List<Paciente> pacientes = new ArrayList<>();
    private List<Terapeuta> terapeutas = new ArrayList<>();
    private AppDatabase database;
    private EditText searchBar;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflamos el layout del fragmento
        View root = inflater.inflate(R.layout.fragment_citas, container, false);

        // Inicializamos las vistas
        recyclerView = root.findViewById(R.id.rv_cita);
        fab = root.findViewById(R.id.btn_añadir);
        searchBar = root.findViewById(R.id.search_bar);
        database = AppDatabase.getInstance(requireContext());

        // Obtenemos pacientes y terapeutas de la base de datos
        getPacientesAndTerapeutas();

        // Configuramos el adaptador para el RecyclerView
        adapter = new CitaAdapter(list, pacientes, terapeutas);
        adapter.setDialog(new CitaAdapter.Dialog() {
            @Override
            public void onClick(int position) {
                // Creamos un diálogo de alerta para mostrar opciones de edición y eliminación
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(requireContext());
                dialogBuilder.setTitle(list.get(position).getFechaHoraCita());
                dialogBuilder.setItems(R.array.item_option, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            // Opción para editar la cita
                            Intent intent = new Intent(requireContext(), EditorActivity.class);
                            intent.putExtra("id", list.get(position).getIdCita());
                            startActivity(intent);
                        } else if (which == 1) {
                            // Opción para eliminar la cita
                            database.citaDao().delete(list.get(position));
                            getData();
                            Toast.makeText(requireContext(), "Cita eliminada", Toast.LENGTH_SHORT).show();
                        } else {
                            // Cancelar la acción
                            dialog.dismiss();
                        }
                    }
                });
                AlertDialog dialogView = dialogBuilder.create();
                dialogView.show();
            }
        });

        // Configuramos el RecyclerView
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));

        // Configuramos el botón flotante para añadir nuevas citas
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(requireContext(), EditorActivity.class));
            }
        });

        // Añadimos un TextWatcher al buscador para filtrar las citas
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
        // Actualizamos los datos cuando el fragmento se reanuda
        getData();
    }

    // Método para obtener todas las citas de la base de datos
    private void getData() {
        list.clear();
        list.addAll(database.citaDao().getAll());
        adapter.notifyDataSetChanged();
    }

    // Método para obtener todos los pacientes y terapeutas de la base de datos
    private void getPacientesAndTerapeutas() {
        pacientes.clear();
        pacientes.addAll(database.pacienteDao().getAll());
        terapeutas.clear();
        terapeutas.addAll(database.terapeutaDao().getAll());
    }

    // Método para filtrar la lista de citas según el texto ingresado
    private void filter(String text) {
        List<Cita> filteredList = list.stream()
                .filter(cita -> cita.getEstadoCita().toLowerCase().contains(text.toLowerCase()))
                .collect(Collectors.toList());
        adapter.updateList(filteredList);
    }
}

