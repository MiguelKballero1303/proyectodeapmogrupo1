package com.consultorio.psicologico.proyecto_deapmo_grupo_1.ui.Tratamientos;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.consultorio.psicologico.proyecto_deapmo_grupo_1.R;
import com.consultorio.psicologico.proyecto_deapmo_grupo_1.entity.AppDatabase;
import com.consultorio.psicologico.proyecto_deapmo_grupo_1.entity.Tratamiento;
import java.util.Calendar;

public class EditorActivity extends AppCompatActivity {

    private EditText nombreTratamiento;
    private EditText descripcionTratamiento;
    private EditText duracionEstimada;
    private EditText costo;
    private Button btnRegistrarTratamiento;
    private AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor_tratamiento);

        nombreTratamiento = findViewById(R.id.nombreTratamiento);
        descripcionTratamiento = findViewById(R.id.descripcionTratamiento);
        duracionEstimada = findViewById(R.id.duracionEstimada);
        costo = findViewById(R.id.costo);
        btnRegistrarTratamiento = findViewById(R.id.btn_registrar_tratamiento);
        database = AppDatabase.getInstance(getApplicationContext());

        Bundle intentExtras = getIntent().getExtras();
        if (intentExtras != null) {
            int tratamientoId = intentExtras.getInt("id", 0);
            Tratamiento tratamiento = database.tratamientoDao().get(tratamientoId);

            nombreTratamiento.setText(tratamiento.getNombreTratamiento());
            descripcionTratamiento.setText(tratamiento.getDescripcionTratamiento());
            duracionEstimada.setText(tratamiento.getDuracionEstimada());
            costo.setText(String.valueOf(tratamiento.getCosto()));
        }

        btnRegistrarTratamiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nombreTratamiento.getText().length() > 0 && descripcionTratamiento.getText().length() > 0 && duracionEstimada.getText().length() > 0 && costo.getText().length() > 0) {
                    if (intentExtras != null) {
                        database.tratamientoDao().update(new Tratamiento(
                                intentExtras.getInt("id", 0),
                                nombreTratamiento.getText().toString(),
                                descripcionTratamiento.getText().toString(),
                                duracionEstimada.getText().toString(),
                                Double.parseDouble(costo.getText().toString())
                        ));
                    } else {
                        database.tratamientoDao().insertAll(new Tratamiento(
                                null,
                                nombreTratamiento.getText().toString(),
                                descripcionTratamiento.getText().toString(),
                                duracionEstimada.getText().toString(),
                                Double.parseDouble(costo.getText().toString())
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
