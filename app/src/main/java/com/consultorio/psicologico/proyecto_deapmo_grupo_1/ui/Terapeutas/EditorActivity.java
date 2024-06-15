package com.consultorio.psicologico.proyecto_deapmo_grupo_1.ui.Terapeutas;


import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.consultorio.psicologico.proyecto_deapmo_grupo_1.R;
import com.consultorio.psicologico.proyecto_deapmo_grupo_1.entity.AppDatabase;
import com.consultorio.psicologico.proyecto_deapmo_grupo_1.entity.Terapeuta;

public class EditorActivity extends AppCompatActivity {
    private EditText nombreTerapeuta;
    private EditText apellidoTerapeuta;
    private Spinner spinnerEspecialidad;
    private EditText telefonoTerapeuta;
    private Spinner spinnerHorario;
    private Button btnRegistrarTerapeuta;
    private AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor_terapeuta);

        nombreTerapeuta = findViewById(R.id.nombreTerapeuta);
        apellidoTerapeuta = findViewById(R.id.apellidoTerapeuta);
        spinnerEspecialidad = findViewById(R.id.spinner_especialidad);
        telefonoTerapeuta = findViewById(R.id.telefonoTerapeuta);
        spinnerHorario = findViewById(R.id.spinner_horario);
        btnRegistrarTerapeuta = findViewById(R.id.btn_registrar_terapeuta);

        database = AppDatabase.getInstance(getApplicationContext());

        ArrayAdapter<CharSequence> especialidadesAdapter = ArrayAdapter.createFromResource(this,
                R.array.especialidades, android.R.layout.simple_spinner_item);
        especialidadesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEspecialidad.setAdapter(especialidadesAdapter);

        ArrayAdapter<CharSequence> horariosAdapter = ArrayAdapter.createFromResource(this,
                R.array.horarios_terapeuta, android.R.layout.simple_spinner_item);
        horariosAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerHorario.setAdapter(horariosAdapter);

        Bundle intentExtras = getIntent().getExtras();
        if (intentExtras != null) {
            int terapeutaId = intentExtras.getInt("id", 0);
            Terapeuta terapeuta = database.terapeutaDao().get(terapeutaId);

            nombreTerapeuta.setText(terapeuta.getNombreTerapeuta());
            apellidoTerapeuta.setText(terapeuta.getApellidoTerapeuta());
            spinnerEspecialidad.setSelection(especialidadesAdapter.getPosition(terapeuta.getEspecialidadTerapeuta()));
            telefonoTerapeuta.setText(terapeuta.getTelefonoTerapeuta());
            spinnerHorario.setSelection(horariosAdapter.getPosition(terapeuta.getHorarioTerapeuta()));
        }

        btnRegistrarTerapeuta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nombreTerapeuta.getText().length() > 0 && telefonoTerapeuta.getText().length() > 0) {
                    if (intentExtras != null) {
                        database.terapeutaDao().update(new Terapeuta(
                                intentExtras.getInt("id", 0),
                                nombreTerapeuta.getText().toString(),
                                apellidoTerapeuta.getText().toString(),
                                spinnerEspecialidad.getSelectedItem().toString(),
                                telefonoTerapeuta.getText().toString(),
                                spinnerHorario.getSelectedItem().toString()
                        ));
                    } else {
                        database.terapeutaDao().insertAll(new Terapeuta(
                                null,
                                nombreTerapeuta.getText().toString(),
                                apellidoTerapeuta.getText().toString(),
                                spinnerEspecialidad.getSelectedItem().toString(),
                                telefonoTerapeuta.getText().toString(),
                                spinnerHorario.getSelectedItem().toString()
                        ));
                    }
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Por favor, complete los datos correctamente", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
