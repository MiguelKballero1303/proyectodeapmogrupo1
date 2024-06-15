package com.consultorio.psicologico.proyecto_deapmo_grupo_1.ui.HistoriasClinicas;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.consultorio.psicologico.proyecto_deapmo_grupo_1.R;
import com.consultorio.psicologico.proyecto_deapmo_grupo_1.entity.AppDatabase;
import com.consultorio.psicologico.proyecto_deapmo_grupo_1.entity.HistoriaClinica;
import com.consultorio.psicologico.proyecto_deapmo_grupo_1.entity.Paciente;
import com.consultorio.psicologico.proyecto_deapmo_grupo_1.entity.Tratamiento;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class EditorActivity extends AppCompatActivity {
    private EditText fechaCreacionHistoria;
    private Spinner pacienteSpinner;
    private Spinner tratamientoSpinner;
    private EditText diagnostico;
    private EditText notasSesion;
    private Button btnGuardar;
    private Calendar calendar;
    private AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor_historiaclinica);

        fechaCreacionHistoria = findViewById(R.id.fecha_creacion_historia);
        pacienteSpinner = findViewById(R.id.paciente_spinner);
        tratamientoSpinner = findViewById(R.id.tratamiento_spinner);
        diagnostico = findViewById(R.id.diagnostico);
        notasSesion = findViewById(R.id.notas_sesion);
        btnGuardar = findViewById(R.id.btn_guardar_historia_clinica);
        calendar = Calendar.getInstance();
        database = AppDatabase.getInstance(getApplicationContext());

        fechaCreacionHistoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(EditorActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                calendar.set(year, monthOfYear, dayOfMonth);
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                                fechaCreacionHistoria.setText(sdf.format(calendar.getTime()));
                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        });

        List<Paciente> pacientes = database.pacienteDao().getAll();
        List<String> nombresPacientes = new ArrayList<>();
        for (Paciente paciente : pacientes) {
            String nombreCompleto = paciente.getNombrePaciente() + " " + paciente.getApellidoPaciente();
            nombresPacientes.add(nombreCompleto);
        }
        ArrayAdapter<String> pacienteAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, nombresPacientes);
        pacienteAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pacienteSpinner.setAdapter(pacienteAdapter);

        List<Tratamiento> tratamientos = database.tratamientoDao().getAll();
        List<String> nombresTratamientos = new ArrayList<>();
        for (Tratamiento tratamiento : tratamientos) {
            nombresTratamientos.add(tratamiento.getNombreTratamiento());
        }
        ArrayAdapter<String> tratamientoAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, nombresTratamientos);
        tratamientoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tratamientoSpinner.setAdapter(tratamientoAdapter);

        if (getIntent().hasExtra("id")) {
            int historiaClinicaId = getIntent().getIntExtra("id", -1);
            HistoriaClinica historiaClinica = database.historiaClinicaDao().get(historiaClinicaId);

            fechaCreacionHistoria.setText(historiaClinica.getFechaCreacionHistoria());
            diagnostico.setText(historiaClinica.getDiagnostico());
            notasSesion.setText(historiaClinica.getNotasSesion());

            for (int i = 0; i < nombresPacientes.size(); i++) {
                if (nombresPacientes.get(i).equals(historiaClinica.getIdPaciente())) {
                    pacienteSpinner.setSelection(i);
                    break;
                }
            }

            for (int i = 0; i < nombresTratamientos.size(); i++) {
                if (nombresTratamientos.get(i).equals(historiaClinica.getIdTratamiento())) {
                    tratamientoSpinner.setSelection(i);
                    break;
                }
            }
        }

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fechaCreacionHistoria.getText().length() > 0 && diagnostico.getText().length() > 0) {
                    if (getIntent().hasExtra("id")) {
                        int historiaClinicaId = getIntent().getIntExtra("id", -1);
                        HistoriaClinica historiaClinica = database.historiaClinicaDao().get(historiaClinicaId);

                        historiaClinica.setIdPaciente(getPacienteId((String) pacienteSpinner.getSelectedItem(), pacientes));
                        historiaClinica.setIdTratamiento(getTratamientoId((String) tratamientoSpinner.getSelectedItem(), tratamientos));
                        historiaClinica.setFechaCreacionHistoria(fechaCreacionHistoria.getText().toString());
                        historiaClinica.setDiagnostico(diagnostico.getText().toString());
                        historiaClinica.setNotasSesion(notasSesion.getText().toString());

                        database.historiaClinicaDao().update(historiaClinica);
                    } else {
                        database.historiaClinicaDao().insertAll(new HistoriaClinica(
                                null,
                                getPacienteId((String) pacienteSpinner.getSelectedItem(), pacientes),
                                getTratamientoId((String) tratamientoSpinner.getSelectedItem(), tratamientos),
                                fechaCreacionHistoria.getText().toString(),
                                diagnostico.getText().toString(),
                                notasSesion.getText().toString()
                        ));
                    }
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Por favor, complete los campos obligatorios", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private int getPacienteId(String nombre, List<Paciente> pacientes) {
        for (Paciente paciente : pacientes) {
            if ((paciente.getNombrePaciente() + " " + paciente.getApellidoPaciente()).equals(nombre)) {
                return paciente.getIdPaciente();
            }
        }
        return 0;
    }

    private int getTratamientoId(String nombre, List<Tratamiento> tratamientos) {
        for (Tratamiento tratamiento : tratamientos) {
            if (tratamiento.getNombreTratamiento().equals(nombre)) {
                return tratamiento.getIdTratamiento();
            }
        }
        return 0;
    }
}

