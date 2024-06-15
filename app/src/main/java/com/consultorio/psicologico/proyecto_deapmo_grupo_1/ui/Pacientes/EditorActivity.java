package com.consultorio.psicologico.proyecto_deapmo_grupo_1.ui.Pacientes;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.consultorio.psicologico.proyecto_deapmo_grupo_1.R;
import com.consultorio.psicologico.proyecto_deapmo_grupo_1.entity.AppDatabase;
import com.consultorio.psicologico.proyecto_deapmo_grupo_1.entity.Paciente;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class EditorActivity extends AppCompatActivity {

    private EditText dniPaciente;
    private EditText nombrePaciente;
    private EditText apellidoPaciente;
    private EditText fechaNacimientoPaciente;
    private EditText direccionPaciente;
    private EditText telefonoPaciente;
    private Button btnregistrarpaciente;
    private AppDatabase database;
    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor_paciente);

        dniPaciente = findViewById(R.id.dniPaciente);
        nombrePaciente = findViewById(R.id.nombrePaciente);
        apellidoPaciente = findViewById(R.id.apellidoPaciente);
        fechaNacimientoPaciente = findViewById(R.id.fechaNacimientoPaciente);
        direccionPaciente = findViewById(R.id.direccionPaciente);
        telefonoPaciente = findViewById(R.id.telefonoPaciente);
        btnregistrarpaciente = findViewById(R.id.btn_registrar_paciente);
        calendar = Calendar.getInstance();
        database = AppDatabase.getInstance(getApplicationContext());

        Bundle intentExtras = getIntent().getExtras();
        if (intentExtras != null) {
            int pacienteId = intentExtras.getInt("id", 0);
            Paciente paciente = database.pacienteDao().get(pacienteId);

            dniPaciente.setText(paciente.getDniPaciente());
            nombrePaciente.setText(paciente.getNombrePaciente());
            apellidoPaciente.setText(paciente.getApellidoPaciente());
            fechaNacimientoPaciente.setText(paciente.getFechaNacimientoPaciente());
            direccionPaciente.setText(paciente.getDireccionPaciente());
            telefonoPaciente.setText(paciente.getTelefonoPaciente());
        }

        fechaNacimientoPaciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        btnregistrarpaciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dniPaciente.getText().length() > 0 && nombrePaciente.getText().length() > 0 && telefonoPaciente.getText().length() > 0) {
                    if (intentExtras != null) {
                        database.pacienteDao().update(new Paciente(
                                intentExtras.getInt("id", 0),
                                dniPaciente.getText().toString(),
                                nombrePaciente.getText().toString(),
                                apellidoPaciente.getText().toString(),
                                fechaNacimientoPaciente.getText().toString(),
                                direccionPaciente.getText().toString(),
                                telefonoPaciente.getText().toString()
                        ));
                    } else {
                        database.pacienteDao().insertAll(new Paciente(
                                null,
                                dniPaciente.getText().toString(),
                                nombrePaciente.getText().toString(),
                                apellidoPaciente.getText().toString(),
                                fechaNacimientoPaciente.getText().toString(),
                                direccionPaciente.getText().toString(),
                                telefonoPaciente.getText().toString()
                        ));
                    }
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Por favor, complete los datos correctamente", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void showDatePickerDialog() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateDate();
            }
        };

        new DatePickerDialog(this, dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void updateDate() {
        String myFormat = "dd/MM/yyyy"; // Formato de fecha
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.getDefault());

        fechaNacimientoPaciente.setText(sdf.format(calendar.getTime()));
    }
}
